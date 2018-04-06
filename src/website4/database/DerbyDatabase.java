package website4.database;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

<<<<<<< HEAD
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import website4.model.per_game_scores;
import website4.model.per_user_scores;
import website4.controller.UserController;
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
	private Connection conn;


	
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
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
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
					
					
					stmt3= conn.prepareStatement(
							
							"create table per_user_hs (" +
							"	hs_id integer primary key " +
							"	generated always as identity (start with 1, increment by 1), " +
							"	userid integer , " +		
							"	nameofthegame varchar(64)," +
							"	hs0 integer," +
							"	hs1 integer," +
							"	hs2 integer," +
							"	hs3 integer," +
							"	hs4 integer," +
							"	hs5 integer," +
							"	hs6 integer," +
							"	hs7 integer," +
							"	hs8 integer," +
							"	hs9 integer" +
							")"

					);
					stmt3.executeUpdate(); 
					
					
					
					
					stmt4= conn.prepareStatement(//the per game scores will be stored in a treemap with the score as the key( for esay ordering)
												//to reconstruct the treemap from the db all the entries with a certian nameofthegame
												// will be placed into the map
							"create table per_game_hs (" +
							"	hs_id integer primary key " +
							"	generated always as identity (start with 1, increment by 1), " +	
							"	hs integer," +
							"	nameofthegame varchar(64)," +
							
							"	userid integer,  " +
							"   username varchar(64)"+
							")"

					);
					stmt4.executeUpdate(); 
					
					
					
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
				List<per_user_scores>  userscoreslist;    
				List<per_game_scores>  gamescoreslist;
				
				try {
					postlist = InitialData.getposts();
					userlist = InitialData.getusers();
					userscoreslist = InitialData.getuserscores();
					gamescoreslist= InitialData.getgamecores();
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertpost = null;
				PreparedStatement insertuser  = null;
				PreparedStatement insertuserscores  = null;
				PreparedStatement insertgamescores = null;
				
				
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
					
	
					
					insertuserscores = conn.prepareStatement("insert into per_user_hs (userid, nameofthegame,  "
							+ "hs0 , hs1, hs2, hs3, hs4, hs5, hs6, hs7, hs8, hs9) values (?, ?,    ?,?,?,?,?,?,?,?,?,?)");
					
					
					for (per_user_scores score : userscoreslist) {
						ArrayList<Integer> skors=score.getscores();

						insertuserscores.setInt(1, score.getusid());
						insertuserscores.setString(2, score.getnameofgame());
						for(int i=0;i<skors.size();i++) {
							insertuserscores.setInt(i+3, skors.get(i));
						}
						
						insertuserscores.addBatch();
					}
					insertuserscores.executeBatch();
					
					
					
					insertgamescores = conn.prepareStatement("insert into per_game_hs (hs, nameofthegame ,  userid,username ) values (?, ?,?,?)");
					
					
					for (per_game_scores score : gamescoreslist) {
						//System.out.println(score.getscore());
						insertgamescores.setString(2, score.getnameofgame());
						insertgamescores.setInt(1, score.getscore());
						insertgamescores.setInt(3, score.getusid());
						insertgamescores.setString(4, score.getusername());						
							
						
						
						insertgamescores.addBatch();
					}
					insertgamescores.executeBatch();
					
					
					
					
					
					
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
	
	public void createUser(final String userName, final String password, final String email) throws SQLException {
		executeTransaction(new Transaction<post>() {
			private Connection conn2;

			public post execute(Connection conn) throws SQLException {
		
		if (userName != null && password != null && email != null) {
			boolean validInfo = isValid(userName, password, email);
			PreparedStatement insertNewID = null;
			conn = null;
			if (validInfo == true) {
				// Add information to database
				//user.add(new usser(userName, password, email));
				//Finish this implementation after we have a database		

				String newID = "insert into users(username, password) values (?, ?)";
				conn2 = conn;
				insertNewID = conn2.prepareStatement(newID);
				insertNewID.setString(1, userName);
				insertNewID.setString(2, password);
				insertNewID.execute();

			}
			
			else {
				//send error to user that account already exists with user or email
				
			}
			
		}
		else {
			//send error to user that information entered was not valid
		}
		return null;
		
			}
			});
	}
	
	
	
	
	
	
	public boolean isValid(final String username, String password, final String email) {
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {

				
					stmt = conn.prepareStatement("select username, email from users where username = ? and email = ?");
					stmt.setString(1, username);
					stmt.setString(2, email);
				
					resultSet = stmt.executeQuery();
				
					int rowsReturned = 0;

					while (resultSet.next()) {
						rowsReturned++;
					}
					
					// indicate if the query returned nothing
					if (rowsReturned == 0) {
						return true;
					}
					
				// end of try block
	
			} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return false;
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
<<<<<<< HEAD
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

<<<<<<< HEAD
					stmt = conn.prepareStatement(
	
							"insert into posts (userid, username, timeposted, posttext)"
							+" values( ? , ?, ?, ?)"
<<<<<<< HEAD
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
<<<<<<< HEAD

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
<<<<<<< HEAD
						);

						stmt.setInt(1, id);

						resultSet = stmt.executeQuery();
<<<<<<< HEAD

						Boolean found = false;
						
						if (resultSet.next()) {
							found = true;
							loaduser(user, resultSet, 1);
<<<<<<< HEAD
	
						}
						
						if (!found) {
							System.out.println("no posts found");
							return null;
						}
						
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						
<<<<<<< HEAD
						
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
						
<<<<<<< HEAD
						
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

	public List<Map.Entry<String, Integer>> getper_game_scores(final String nameofthegame) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<List<Map.Entry<String, Integer>>>() {
			public List<Map.Entry<String, Integer>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							
							"SELECT per_game_hs.hs , per_game_hs.username "+
							"FROM per_game_hs "+
							"where per_game_hs.nameofthegame = ? "+
							"ORDER BY per_game_hs.hs DESC"

					);//gets the 
					stmt.setString(1, nameofthegame);
					
					resultSet = stmt.executeQuery();

					
					List<Map.Entry<String, Integer>> result=new ArrayList<Map.Entry<String, Integer>>();
					//List<Map.Entry<String, Integer>> result=new LinkedList(Map.entrySet());
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						
						// Entry<Integer, Integer> skore = null;
						found = true;
						int index=1;
						
						int score=resultSet.getInt(index++);
						String usid=resultSet.getString(index++);		
						
						Map.Entry<String,Integer> skore =new AbstractMap.SimpleEntry<String, Integer>(usid, score);
						//System.out.println( "map score        "+skore);
						result.add(skore);
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

	public List<Entry<String, Integer>> addscoretogmaedb(final String nameofthegame, final int userid, final int score, final String username) {
		 executeTransaction(new Transaction<post>() {
				public post execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {

					stmt = conn.prepareStatement(
	
							"insert into per_game_hs (hs, nameofthegame ,  userid,username)"
							+" values( ? , ?, ?, ?)"
					);

					stmt.setInt(1, score);
					stmt.setString(2, nameofthegame);
					stmt.setInt(3, userid);
					stmt.setString(4, username);
					// execute the query
					
					stmt.executeUpdate();
					
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
					stmt = conn.prepareStatement(
							
							"SELECT per_game_hs.hs "+
							"FROM per_game_hs "+
							"where per_game_hs.nameofthegame = ? "+
							"ORDER BY per_game_hs.hs DESC"

					);//gets the 
					stmt.setString(1, nameofthegame);
					
					resultSet = stmt.executeQuery();
					ArrayList<Integer> scored=new ArrayList<Integer>();
					while (resultSet.next()) {
						int index=1;	
						scored.add(resultSet.getInt(index++));
					}
					ArrayList<Integer> remove=new ArrayList<Integer>();
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					if(scored.size()>10) {
						remove.addAll(scored.subList(10, scored.size()-1));
						for(int i=0;i<remove.size();i++) {
							stmt = conn.prepareStatement(
								
								"DELETE "+
								"FROM per_game_hs "+
								"where per_game_hs.nameofthegame = ? and per_game_hs.hs= ? "
							);
							stmt.setString(1, nameofthegame);
							stmt.setInt(2, remove.get(i));
						
							stmt.executeUpdate();
							
							DBUtil.closeQuietly(stmt);
						}
					}
					
					
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
					return null;
				}
			});
<<<<<<< HEAD
		return getper_game_scores(nameofthegame);
	}

	public List<Integer> getperuserscores(String nameofthegame, final int userid) {
		// TODO Auto-generated method stub

		//final usser user=getuser_by_id(userid);
		
		return executeTransaction(new Transaction<List<Integer>>() {
			public List<Integer> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"SELECT hs0 , hs1, hs2, hs3, hs4, hs5, hs6, hs7, hs8, hs9 " +
							"FROM per_user_hs  "+
							"where  per_user_hs.userid= ?"
					);
					stmt.setInt(1,userid);
					
					
					resultSet = stmt.executeQuery();
					List<Integer> result= new ArrayList<Integer>();
					Boolean found = false;
						if (resultSet.next()) {
							found=true;
							for(int i=1;i<11;i++) {
								int score=resultSet.getInt(i);
								result.add(score);
							}
							
						}
							

					
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);

					
					
					// check if any posts were found
					if (!found) {
						System.out.println("no user score found");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public void addtouserscores(final String nameofthegame, final int userid, final int score) {
		// TODO Auto-generated method stub
		
		 executeTransaction(new Transaction<post>() {
				public post execute(Connection conn) throws SQLException {
					
					
					
					List<Integer> existingscores= getperuserscores(nameofthegame,userid);
					//Gson gson = new GsonBuilder().create();
					//System.out.println(gson.toJson(existingscores));
					existingscores.add(score);
					Collections.sort(existingscores);
					//System.out.println(gson.toJson(existingscores));
					Collections.reverse(existingscores);
					//System.out.println(gson.toJson(existingscores));
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					if(existingscores.size()<=10) {
						for(int i=0;i<(25-existingscores.size());i++ ) {
							existingscores.add(0);
						}
						//System.out.println(gson.toJson(existingscores));
					}
					try {
						
						stmt = conn.prepareStatement(
								"select hs0   "
								+"FROM per_user_hs  "+
								" where  per_user_hs.userid= ?"
						);

						stmt.setInt(1, userid);
						
						
						resultSet = stmt.executeQuery();
						
						Integer test = null;
						if (resultSet.next()) 
							test =  resultSet.getInt(1);
						
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						
						if (test==null) {
							stmt = conn.prepareStatement(
									
									"insert into per_user_hs (userid, nameofthegame,  "
											+ "hs0 , hs1, hs2, hs3, hs4, hs5, hs6, hs7, hs8, hs9) values (?, ?,    ?,?,?,?,?,?,?,?,?,?)"
											
							);
							stmt.setInt(1, userid);
							stmt.setString(2, nameofthegame);
							for(int v=0;v<10;v++) {
								stmt.setInt(3+v,existingscores.get(v) );
								
							}
							stmt.executeUpdate();
						}
						else {
					
						stmt = conn.prepareStatement(
									
							"UPDATE per_user_hs "+
							" set hs0= ? , hs1= ? , hs2= ? , hs3= ? , hs4= ? , hs5= ? , hs6= ? , hs7= ? , hs8= ? , hs9= ?  "
							+ "where userid = ? and nameofthegame = ?  "
									
									
						);
						for(int v=1;v<=10;v++) {
							stmt.setInt(v,existingscores.get(v-1) );
							
						}
						stmt.setInt(11, userid);
						stmt.setString(12, nameofthegame);
						
						stmt.executeUpdate();
						}
						
						
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
	
	}
	
	}
	

}
