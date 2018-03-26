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
	
	//private void loadBook(usser book, ResultSet resultSet, int index) throws SQLException {
	//	book.setBookId(resultSet.getInt(index++));
	//	book.setAuthorId(resultSet.getInt(index++));
	//	book.setTitle(resultSet.getString(index++));
	//	book.setIsbn(resultSet.getString(index++));
	//	book.setPublished(resultSet.getInt(index++));		
	//}
	
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
					
					
					insertuser = conn.prepareStatement("insert into users (userid, username, password, coins, email) values (?, ?, ?, ?, ?)");
					for (usser user : userlist) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
						
						insertuser.setInt(1, user.getuserid());
						insertuser.setString(2, user.getusername());
						insertuser.setString(3, user.getpassword());
						insertuser.setInt(4, user.getcoins());
						insertuser.setString(5, user.getemail());
						
						
					
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


	public List<post> getposts_no_blacklist() {
		// TODO Auto-generated method stub
		
	
		return executeTransaction(new Transaction<List<post>>() {
			public List<post> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"select * from posts " 
							
					);
					
					
					
					
					ArrayList<post> result=new ArrayList<post>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						// create new Author object
						// retrieve attributes from resultSet starting with index 1
						post post = new post();
						loadpost(post, resultSet, 1);
						
						
						result.add(post);
						
					}
					
					// check if the title was found
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

	


		
		
		
		
		
		
		
		
		
	}
	
	
	
	
