package website4.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import website4.model.post;
import website4.model.usser;
import website4.sqldemo.DBUtil;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;


	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	private void loadpost(post post, ResultSet resultSet, int index) throws SQLException {
		index++;
		post.setuserid(resultSet.getInt(index++));
		post.setusername(resultSet.getString(index++));
		post.setmils_time(resultSet.getLong(index++));
		post.setpost(resultSet.getString(index++));
		
		
	}
	
	private void loaduser(usser user, ResultSet resultSet, int index) throws SQLException {
		user.setuserid(resultSet.getInt(index++));
		user.setusername(resultSet.getString(index++));
		user.setpassword(resultSet.getString(index++));
		user.setcoins(resultSet.getInt(index++));
		user.setemail(resultSet.getString(index++));
		
		
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table users (" +
									"	userid integer primary key, " +
									"	username varchar(32) , " +
									"	password varchar(64)," +
									"	coins integer ," +
									"   email varchar(64)" +
									//"   isguset integer "+
									")"
					);	
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							
							"create table posts (" +
							"	postid integer primary key " +
							"	generated always as identity (start with 1, increment by 1), " +
							"	userid integer constraint userid references users, " +		
							"  	username  varchar(32),  "+
							"	timeposted bigint," +
							"	posttext varchar(512)" +
							")"

					);
					stmt2.executeUpdate(); 
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				List<post> postlist;
				List<usser> userlist;
				
				try {
					postlist = InitialData.getposts();
					userlist = InitialData.getusers();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertpost = null;
				PreparedStatement insertuser  = null;

				try {
					
					
					//insertuser = conn.prepareStatement("insert into users (userid, username, password, coins, email,isguset) values (?, ?, ?, ?, ?,?)");
					insertuser = conn.prepareStatement("insert into users (userid, username, password, coins, email) values (?, ?, ?, ?, ?)");
					for (usser user : userlist) {
				
						
						insertuser.setInt(1, user.getuserid());
						insertuser.setString(2, user.getusername());
						insertuser.setString(3, user.getpassword());
						insertuser.setInt(4, user.getcoins());
						insertuser.setString(5, user.getemail());
						//insertuser.setBoolean(6, user.getisguest());
						
					
						insertuser.addBatch();
					}
					insertuser.executeBatch();

					// populate authors table (do authors first, since author_id is foreign key in books table)
					insertpost = conn.prepareStatement("insert into posts (userid, username ,  timeposted , posttext) values (?, ?,?, ?)");
					
					
					for (post post : postlist) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this

						
						insertpost.setInt(1, post.getuserid());
						insertpost.setString(2, post.Getusername());
						insertpost.setLong(3, post.Getmils_time());
						insertpost.setString(4, post.Getpost());
						
						insertpost.addBatch();
					}
					insertpost.executeBatch();
					
					// populate books table (do this after authors table,
					// since author_id must exist in authors table before inserting book)
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertuser);
					DBUtil.closeQuietly(insertpost);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}

	/**
	 * this method retrives    (# numposts)  posts starting   (# chatindex) up from the last post currently without any blacklist
	 * 
	 */
	public List<post> getposts_no_blacklist(final int chatindex,final int numposts) {
		// TODO Auto-generated method stub
		
	
		return executeTransaction(new Transaction<List<post>>() {
			public List<post> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"SELECT MAX(postid) " +
							"FROM posts"
							
					);//gets the largest(newest) post id
					
					resultSet = stmt.executeQuery();

						int totalposts=0 ;
						if (resultSet.next()) 
							totalposts =   (Integer) resultSet.getObject(1);//asumes the largest post index is also the number of posts

						int bottomindexindb = totalposts-chatindex;   // bottom index is the newest post that is requested
						int topindexindb = bottomindexindb - numposts;  //top index is the oldest post requested 
					
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);

					stmt = conn.prepareStatement(
							"select * from posts " 
							+"where postid >= ? " + 
							" and postid <= ? "
					);//selects everything between top and bottom
					stmt.setInt(1, topindexindb);
					stmt.setInt(2, bottomindexindb);
					
					
					ArrayList<post> result=new ArrayList<post>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						post post = new post();
						loadpost(post, resultSet, 1);
						
						
						result.add(post);
						
					}
					
					// check if any posts were found
					if (!found) {
						System.out.println("no posts found");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public void addpost(final long mils_time, final int userid, final String posttext) {
		 executeTransaction(new Transaction<post>() {
				public post execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
						
						stmt = conn.prepareStatement(
								"select username   "
								+"  from users  "
								+"  where 	users.userid =  ?  "
						);

						stmt.setInt(1, userid);
						
						
						resultSet = stmt.executeQuery();
						
						String username = null ;
						if (resultSet.next()) 
							username =  (String) resultSet.getObject(1);
						System.out.println("in db username        "+username);
						
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						
						if(username==null) {
							
							return null;
						}

					stmt = conn.prepareStatement(
	
							"insert into posts (userid, username, timeposted, posttext)"
							+" values( ? , ?, ?, ?)"
					);

					stmt.setInt(1, userid);
					stmt.setString(2, username);
					stmt.setLong(3, mils_time);
					stmt.setString(4, posttext);
					// execute the query
					
					stmt.executeUpdate();

					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
					return null;
				}
			});

	}

	public usser getuser_by_id(final int id) {
		// TODO Auto-generated method stub
		
		 return executeTransaction(new Transaction<usser>() {
			 usser user = new usser(null, null );
			 
				public usser execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
						stmt = conn.prepareStatement(
								"select *   "
								+"  from users  "
								+"  where 	users.userid =  ?  "
						);

						stmt.setInt(1, id);

						resultSet = stmt.executeQuery();

						Boolean found = false;
						
						if (resultSet.next()) {
							found = true;
							loaduser(user, resultSet, 1);
	
						}
						
						if (!found) {
							System.out.println("no posts found");
							return null;
						}
						
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						
						
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
					return user;
				}
			});
		

	}

	public void addusertodb(final int userid, final String username,final String password,final String email,final int coins) {
		// TODO Auto-generated method stubfinal
		
		executeTransaction(new Transaction<post>() {
			public post execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {

						stmt = conn.prepareStatement(

								"insert into users (userid, username, password, coins, email) values (?, ?, ?, ?, ?)"

						);
				
						stmt.setInt(1, userid);
						stmt.setString(2, username);
						stmt.setString(3, password);
						stmt.setInt(4, coins);
						stmt.setString(5, email);
						
						// execute the query
				
						stmt.executeUpdate();
				
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return null;
			}
		});
		
	}

	public boolean checkdbcontainsuserid(final int id) {
		// TODO Auto-generated method stub
		
		return executeTransaction(new Transaction<Boolean >() {
			Boolean found = false;
				public Boolean execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
						stmt = conn.prepareStatement(
								"select *   "
								+"  from users  "
								+"  where 	users.userid =  ?  "		
							
						);
						stmt.setInt(1, id);
						
						
						resultSet = stmt.executeQuery();

						if (resultSet.next()) {
							found = true;
							
							
						}
						
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						
						return found;
				
						
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});

	}

	public boolean checkdbcontainsusername(final String username) {
		// TODO Auto-generated method stub
		
		return executeTransaction(new Transaction<Boolean >() {
			Boolean found = false;
				public Boolean execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
						stmt = conn.prepareStatement(
								"select *   "
								+"  from users  "
								+"  where 	users.username =  ?  "

						);

						stmt.setString(1, username);
						
						resultSet = stmt.executeQuery();
						
						if (resultSet.next()) {
							found = true;
						}

						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);

						
						return found;

						
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});

	}

	}
