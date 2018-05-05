package website4.database;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.Instant;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import website4.controller.chatcontroler;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import website4.model.per_game_scores;
import website4.model.per_user_scores;
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
		//post.setusername(resultSet.getString(index++));
		post.setmils_time(resultSet.getLong(index++));
		post.setpost(resultSet.getString(index++));
		post.setusername(resultSet.getString(index++));
		
		
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
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
				PreparedStatement stmt7 = null;
				PreparedStatement stmt8 = null;
				PreparedStatement stmt9 = null;
				PreparedStatement stmt0 = null;
				PreparedStatement stmt10 = null;
				PreparedStatement stmt11 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table users (" +
									"	userid integer primary key, " +
									"	username varchar(64) , " +
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
							"	userid integer , " +		
							//"  	username  varchar(64),  "+
							"	timeposted bigint," +
							"	posttext varchar(512), "
							+ " chatname  integer" +
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
							"   username varchar(64)"+///remove this latter
							")"

					);
					stmt4.executeUpdate(); 
					
					stmt5 = conn.prepareStatement(
							"create table guests (" +
									"	userid integer primary key, " +
									"	logedin bigint" +
									")"
					);	
					stmt5.executeUpdate();
					
					stmt6 = conn.prepareStatement(
							"create table chatnames (" +
									"	chatid integer primary key "
									+ "	generated always as identity (start with 0, increment by 1), " +
									"	chatname varchar(32)" +
									")"
					);	
					stmt6.executeUpdate();
					
					stmt8 = conn.prepareStatement(
							"create table usercreatedchats (" +
									"	chats integer primary key " +
									"	generated always as identity (start with 0, increment by 1), " +
									"	userid integer, "
									+ " chatid integer " +
									")"
					);	
					stmt8.executeUpdate();
					
					
					
					stmt7 = conn.prepareStatement(
							"create table blacklist (" +
									" id integer primary key "
									+ " generated always as identity (start with 0, increment by 1),  " +
									"	blocker_id integer, " +
									"	blockee_id integer" +
									")"
					);	
					stmt7.executeUpdate();
					
					
					stmt9 = conn.prepareStatement(
							"create table pmlist (" +
									"	pmid integer primary key "
									+ " generated always as identity (start with 0, increment by 1),  " +
									"	user1id integer,"
									+ " user1lastacess bigint," +
									"	user2id integer,"
									+ " user2lastacess bigint" +
									")"
					);	
					stmt9.executeUpdate();
					
					
					stmt0 = conn.prepareStatement(
							"create table pmchats (" +
									"	postid integer primary key " +
									"	generated always as identity (start with 1, increment by 1), " +
									"	userid integer , " +	
									"	timeposted bigint," +
									"	posttext varchar(512), "
									+ " pmid  integer" +
									")"
					);	
					stmt0.executeUpdate();
					
					stmt10 = conn.prepareStatement(
							"create table userchattimetable (" +
									"	userid integer  primary key, " +	
									"	lastused bigint," +
									"	chatid integer "+
									")"
					);	
					stmt10.executeUpdate();
					
					stmt11 = conn.prepareStatement(
							"create table userimg (" +
									"	userid integer  primary key, " +	
									"	imgbolb blob "+
									")"
					);	
					stmt11.executeUpdate();
					
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
					DBUtil.closeQuietly(stmt8);
					DBUtil.closeQuietly(stmt9);
					DBUtil.closeQuietly(stmt0);
					DBUtil.closeQuietly(stmt10);
					DBUtil.closeQuietly(stmt11);
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
				List<Map.Entry<String, Integer>> chatid;
				
				try {
					postlist = InitialData.getposts();
					userlist = InitialData.getusers();
					userscoreslist = InitialData.getuserscores();
					gamescoreslist= InitialData.getgamecores();
					chatid=InitialData.getchatids();
							
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertpost = null;
				PreparedStatement insertuser  = null;
				PreparedStatement insertuserscores  = null;
				PreparedStatement insertgamescores = null;
				PreparedStatement insertchatnames = null;
				
				
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

				///////
					//insertpost = conn.prepareStatement("insert into posts (userid, username ,  timeposted , posttext) values (?, ?,?, ?)");
					insertpost = conn.prepareStatement("insert into posts (userid, timeposted , posttext,chatname) values (?, ?, ?,?)");
					
					for (post post : postlist) {
//						

						insertpost.setInt(1, post.getuserid());
						//insertpost.setString(2, post.Getusername());
						insertpost.setLong(2, post.Getmils_time());
						insertpost.setString(3, post.Getpost());
						insertpost.setInt(4, 0);//default chat has id=0
						
						insertpost.addBatch();
					}
					insertpost.executeBatch();
					
				///////
					
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
					
				///////
					
					insertgamescores = conn.prepareStatement("insert into per_game_hs (hs, nameofthegame ,  userid,username ) values (?, ?,?,?)");
					
					
					for (per_game_scores score : gamescoreslist) {
						
						insertgamescores.setString(2, score.getnameofgame());
						insertgamescores.setInt(1, score.getscore());
						insertgamescores.setInt(3, score.getusid());
						insertgamescores.setString(4, score.getusername());						
							
						
						
						insertgamescores.addBatch();
					}
					insertgamescores.executeBatch();
					///////
					
					insertchatnames = conn.prepareStatement("insert into chatnames ( chatname  ) values ( ?)");
					
					
					for (Entry<String, Integer> name : chatid) {
						
						
						
						insertchatnames.setString(1, name.getKey());
							
						
						
						insertchatnames.addBatch();
					}
					insertchatnames.executeBatch();
					
					
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertuser);
					DBUtil.closeQuietly(insertpost);
					DBUtil.closeQuietly(insertuserscores);
					DBUtil.closeQuietly(insertgamescores);
					DBUtil.closeQuietly(insertchatnames);
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
	public List<post> getposts_no_blacklist(final int chatindex,final int numposts , final String Chatname,final int gettinguserid) {
		// TODO Auto-generated method stub  general
		
	
		return executeTransaction(new Transaction<List<post>>() {
			public List<post> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					int chatindex=0;
					ArrayList<Integer> blacklist= new ArrayList<Integer>();
					chatindex=chatnametoid(Chatname);
					
					
					
					stmt = conn.prepareStatement(
							"SELECT blockee_id " +
							"FROM blacklist"
							+ " where blocker_id = ? "
							
					);//gets the largest(newest) post id 83214
					stmt.setInt(1, gettinguserid);
					
					resultSet = stmt.executeQuery();
					System.out.println("start blacklist for   "+ gettinguserid);
					while (resultSet.next()) {
						blacklist.add(resultSet.getInt(1));
						System.out.println("   id blocked   "+resultSet.getInt(1) );
					}
					
					
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					String stt="";
					for(int i=0;i<blacklist.size();i++) {
						stt+=" and userid <> ? ";
						
						
					}
					
					stmt = conn.prepareStatement(
							"SELECT postid " + 
							"FROM posts "
							+ "where chatname= ? "+stt
							
					);//gets the largest(newest) post id
					stmt.setInt(1, chatindex);
					for(int i=0;i<blacklist.size();i++) {
						stmt.setInt(2+i, blacklist.get(i));
						
						
					}
					
					resultSet = stmt.executeQuery();
						//int totalposts=0 ;
						ArrayList<Integer> postids=new 	ArrayList<Integer>();
						while (resultSet.next()) {
							postids.add(resultSet.getInt(1));
						}
							//totalposts =   (Integer) resultSet.getObject(1);//asumes the largest post index is also the number of posts
						
						//Collections.sort(postids);
						
						int bottomindexindb = 0;   // bottom index is the newest post that is requested
						if(postids.size()>0) {
							 bottomindexindb = postids.get(postids.size()-1); 
						}
						int topindexindb = 0;  //top index is the oldest post requested 
						if(postids.size()>numposts+2) {
							topindexindb = postids.get(postids.size()-(numposts+1));
						}
					
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
			
					stt="";
					for(int i=0;i<blacklist.size();i++) {
						stt+=" and posts.userid <> ? ";
						
						
					}
					stmt = conn.prepareStatement(
							"select posts.postid , posts.userid , posts.timeposted, posts.posttext, users.username  from users , posts " 
							+"where postid >= ? " + 
							" and postid <= ? "
							+ "and posts.userid = users.userid "
							+ " and chatname= ? "+stt
					);//selects everything between top and bottom
					stmt.setInt(1, topindexindb);
					stmt.setInt(2, bottomindexindb);
					stmt.setInt(3, chatindex);
					for(int i=0;i<blacklist.size();i++) {
						stmt.setInt(4+i, blacklist.get(i));
						
						
					}
					
					
					ArrayList<post> result=new ArrayList<post>();
					//ArrayList<Integer> ids=new ArrayList<Integer>();
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned 
				
					while (resultSet.next()) {
						
						post post = new post();
						loadpost(post, resultSet, 1);
						//post.setguest(isguest(resultSet.getInt(2)));
						//ids.add(resultSet.getInt(2));
						result.add(post);
						
					}
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
					for(int i=0; i<result.size();i++ ) {
						result.get(i).setguest(isguest(result.get(i).getuserid()));
						//System.out.println(result.get(i).getguest());
					}
					
					
					
					//Collections.sort(result);
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}  
			}
		});
	}
	
	public void createUser(final String userName, final String password, final String email, final String userid) throws SQLException {
		executeTransaction(new Transaction<post>() {
			
			private Connection conn2;
			
			public post execute(Connection conn) throws SQLException {
				System.out.println(" ______ email  "+email);
		if (userName != null && password != null && email != null) {
			boolean validInfo = isValid(userName, password, email);
			PreparedStatement insertNewID = null;
			conn = null;
			System.out.println(" ______ email  "+email);
			if (validInfo == true) {
			
				String newID = "insert into users(username, password, email, userid) values (?, ?, ?, ?)";
				conn2 = conn;
				insertNewID = conn2.prepareStatement(newID);
				insertNewID.setString(1, userName);
				insertNewID.setString(2, password);
				insertNewID.setString(3, email);
				insertNewID.setString(4, userid);
				insertNewID.execute();

			}
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
	
	public void addpost(final long mils_time, final int userid, final String posttext , final String Chatname) {
		 executeTransaction(new Transaction<post>() {
				public post execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					int chatindex=0;
					try {
					chatindex=chatnametoid(Chatname);

					stmt = conn.prepareStatement(
	
							"insert into posts (userid,  timeposted, posttext,Chatname)"
							+" values( ? ,  ?, ?,?)"

					);

					stmt.setInt(1, userid);
					//stmt.setString(2, username);
					stmt.setLong(2, mils_time);
					stmt.setString(3, posttext);
					stmt.setInt(4, chatindex);
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
							user.setisguest(isguest(user.getuserid()));
							
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
					//System.out.println(" ______ email  "+email);
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

	public List<Map.Entry<String, Integer>> getper_game_scores(final String nameofthegame) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<List<Map.Entry<String, Integer>>>() {
			public List<Map.Entry<String, Integer>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
			
				try {
					stmt = conn.prepareStatement(  
							
							"SELECT per_game_hs.hs , users.username "+
							"FROM users, per_game_hs "+
							"where per_game_hs.nameofthegame = ? "
							+ " and per_game_hs.userid = users.userid "+
							"ORDER BY per_game_hs.hs DESC"

					);//gets the 
					stmt.setString(1, nameofthegame);
					
					resultSet = stmt.executeQuery();

					
					List<Map.Entry<String, Integer>> result=new ArrayList<Map.Entry<String, Integer>>();
					//List<Map.Entry<String, Integer>> result=new LinkedList(Map.entrySet());
					
					// for testing that a result was returned
				
					while (resultSet.next()) {
						
						// Entry<Integer, Integer> skore = null;
						int index=1;
						
						int score=resultSet.getInt(index++);
						String usid=resultSet.getString(index++);		
						
						Map.Entry<String,Integer> skore =new AbstractMap.SimpleEntry<String, Integer>(usid, score);
						result.add(skore);
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

		return getper_game_scores(nameofthegame);
	}

	public List<Integer> getperuserscores(final String nameofthegame, final int userid) {
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
							"where  per_user_hs.userid= ? and nameofthegame= ?"
					);
					stmt.setInt(1,userid);
					stmt.setString(2, nameofthegame);
					
					resultSet = stmt.executeQuery();
					List<Integer> result= new ArrayList<Integer>();
						if (resultSet.next()) {
							for(int i=1;i<11;i++) {
								int score=resultSet.getInt(i);
								result.add(score);
							}
							
						}
							

					
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);

					
					
					
					
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

					
					existingscores.add(score);
					Collections.sort(existingscores);
					Collections.reverse(existingscores);
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					if(existingscores.size()<=10) {
						for(int i=0;i<(25-existingscores.size());i++ ) {
							existingscores.add(0);
						}
					}
					try {
						
						stmt = conn.prepareStatement(
								"select hs0   "
								+"FROM per_user_hs  "+
								" where  per_user_hs.userid= ? and nameofthegame= ? "
						);

						stmt.setInt(1, userid);
						stmt.setString(2, nameofthegame);
						
						resultSet = stmt.executeQuery();
						
						Integer test = null;
						if (resultSet.next()) 
							test =  resultSet.getInt(1);
						
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						
						if (test==null) {
							stmt = conn.prepareStatement(
									
									"insert into per_user_hs (userid, nameofthegame,  "
									+ "hs0 , hs1, hs2, hs3, hs4, hs5, hs6, hs7, hs8, hs9) values (?, ?,    ?,?,?,?,?,?,?,?,?,?) "
									
											
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
						

					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
					return null;
				}
			});
		
		
		
		
	}

	public void addtoguestlist(final int userid, final long timelogedin) {
		// TODO Auto-generated method stub
		
		executeTransaction(new Transaction<post>() {
			public post execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { 

						stmt = conn.prepareStatement(

								"insert into guests (userid, logedin) values (?, ?)"

						);
				
						stmt.setInt(1, userid);
						stmt.setLong(2, timelogedin);
						
						
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

	/**
	 * !!!WIP do not use
	 * 
	 * 
	 */
	public void updateguestlist(final long now) {
		// TODO Auto-generated method stub
		
		
		executeTransaction(new Transaction<post>() {
			public post execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { 
					ArrayList<Map.Entry<Integer, Long>> guestlist=(ArrayList<Entry<Integer, Long>>) getguestlist();
					ArrayList<Integer> remove=new ArrayList<Integer>();
					Long cutoff=now-86400000;
					for(int i=0; i<guestlist.size();i++) {
						if (guestlist.get(i).getValue()<cutoff) {
							remove.add(guestlist.get(i).getKey());
							usser tmpp=getuser_by_id(guestlist.get(i).getKey());
							stmt = conn.prepareStatement(

									"insert into wasguest (userid, username) values (?, ?)"

							);
					
							stmt.setInt(1, tmpp.getuserid());
							stmt.setString(2, tmpp.getusername());
							
							stmt.executeUpdate();
							DBUtil.closeQuietly(stmt);
							
						}
					}
					
					for(int i=0;i<remove.size();i++) {
						stmt = conn.prepareStatement(
							
							"DELETE "+
							"FROM users "+
							"where users.userid = ?  "
						);
						
						stmt.setInt(1, remove.get(i));
						stmt.executeUpdate();
						DBUtil.closeQuietly(stmt);
					}
					for(int i=0;i<remove.size();i++) {
						stmt = conn.prepareStatement(
							
							"DELETE "+
							"FROM guests "+
							"where guests.userid = ?  "
						);
						
						stmt.setInt(1, remove.get(i));
						stmt.executeUpdate();
						DBUtil.closeQuietly(stmt);
					}
					

				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return null;
			}
		});
	}

	public List<Entry<Integer, Long>> getguestlist() {
		
		return executeTransaction(new Transaction<List<Map.Entry<Integer, Long>>>() {
			public List<Map.Entry<Integer, Long>> execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { 
						stmt = conn.prepareStatement(
								"select * from guests "
						);
						resultSet = stmt.executeQuery();

						List<Map.Entry<Integer, Long>> result=new ArrayList<Map.Entry<Integer, Long>>();
						
						while (resultSet.next()) {
							int index=1;
							
							int score=resultSet.getInt(index++);
							Long usid=resultSet.getLong(index++);	
							
							Map.Entry<Integer,Long> skore =new AbstractMap.SimpleEntry<Integer, Long>(score, usid);
							result.add(skore);
						}
						
						return  result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public boolean isguest(final int userid) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { 
						stmt = conn.prepareStatement(
								"select * from guests "
								+ " where userid= ? "
						);
						stmt.setInt(1, userid);
						resultSet = stmt.executeQuery();
						
						boolean found=false;
						//System.out.println("   not guest "+userid );
						if (resultSet.next()) {
							//System.out.println("   not guest"+resultSet.getInt(1));
							found=true;
						}
						return  found;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public int chatnametoid(final String chatname) {
		// TODO Auto-generated method stub chatnames
		
		return executeTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { 
						stmt = conn.prepareStatement(
								"select chatid from chatnames "
								+ " where chatname= ? "
						);
						stmt.setString(1, chatname);
						resultSet = stmt.executeQuery();

						int chatid=-1;
						
						if (resultSet.next()) {
							chatid=resultSet.getInt(1);
						}
						return  chatid;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			
			}
		});
		
	}

	public usser loguserin(final String username, final String password) {
		// TODO Auto-generated method stub
		
		if (checkdbcontainsusername(username)) {
			return executeTransaction(new Transaction<usser>() {
				public usser execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					usser user = new usser(null,null);
					try { 
						
					
							stmt = conn.prepareStatement(
									"select * from users "
									+ " where username= ?  and password =? "
							);
							stmt.setString(1, username);
							stmt.setString(2, password);
							resultSet = stmt.executeQuery();
							int num=0;
							
							while (resultSet.next()) {
								num++;
								loaduser(user, resultSet, 1);
								System.out.println(resultSet.getInt(4));
							}
							if(num==1) {
								return  user;
							}
							else {
								return  null;
							}
						
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				
				}
			});
		}
		else {
			return null;
		}
	}

	public List<Integer> getuserchataxcess(final int userid) {
		// TODO Auto-generated method stub
		
		return executeTransaction(new Transaction<List<Integer>>() {
			public List<Integer> execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { 
						stmt = conn.prepareStatement(
								"select chatid from usercreatedchats "
								+ " where userid= ? "
						);
						stmt.setInt(1, userid);
						resultSet = stmt.executeQuery();

						ArrayList<Integer> result=new ArrayList<Integer>();
						
						while (resultSet.next()) {
							result.add(resultSet.getInt(1));
						}
						return  result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			
			}
		});

		
	}

	public List<String> getuserchatnames(int userid) {
		// TODO Auto-generated method stub
		List<Integer> ids= getuserchataxcess(userid);
		ArrayList<String> result=new ArrayList<String>();
		for (int id : ids) {
			result.add(chatidtoname(id));
		}
		
		
		return result;
	}

	public String chatidtoname(final int chatid) {
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { 
						stmt = conn.prepareStatement(
								"select chatname from chatnames "
								+ " where chatid= ? "
						);
						stmt.setInt(1, chatid);
						resultSet = stmt.executeQuery();

						String chatid=null;
						
						if (resultSet.next()) {
							chatid=resultSet.getString(1);
						}
						return  chatid;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			
			}
		});
	}

	public void addusertochat(final String chatname, final int userid) {
		// TODO Auto-generated method stub
		executeTransaction(new Transaction<post>() {
			public post execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try { 
						Integer chatid=chatnametoid(chatname);
						
						if(chatid==null||chatid==-1) {
							String eror="the chat -";
							eror+=chatname+"- does not exsits \ntry creating it";
							throw new NoSuchElementException(eror);
						}
						else {
							stmt = conn.prepareStatement(

									"insert into usercreatedchats (chatid,userid) values ( ?,?) "

							);
					
							stmt.setInt(1, chatid);
							stmt.setInt(2, userid);
							stmt.executeUpdate();
							
						}
				
						
						
						
						
						// execute the query
				
						
				
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return null;
			}
		});
		
		
		
		
	}

	public void createchat(final String chatname, final int userid) {
		// TODO Auto-generated method stub
		executeTransaction(new Transaction<post>() {
			public post execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { //chatnames
						Integer chatid=chatnametoid(chatname);
						
						if(chatid==null||chatid==-1) {
							
							stmt = conn.prepareStatement(

									"select MAX(chatid) from chatnames  "

							);
							
							resultSet = stmt.executeQuery();

							Integer chatidd=0;
							
							if (resultSet.next()) {
								chatidd=resultSet.getInt(1);
							}
							DBUtil.closeQuietly(resultSet);
							DBUtil.closeQuietly(stmt);
							
							stmt = conn.prepareStatement(

									"insert into chatnames (chatname) values ( ?) "

							);
							chatidd++;
							stmt.setString(1, chatname);
							
							stmt.executeUpdate();
							
							DBUtil.closeQuietly(stmt);
							
							stmt = conn.prepareStatement(

									"insert into usercreatedchats (chatid,userid) values ( ?,?)"

							);
					
							stmt.setInt(1, chatidd);
							stmt.setInt(2, userid);
							stmt.executeUpdate();
						}
						else {
							String eror="the chat ";
							eror+=chatname+" already exsits ";
							throw new NoSuchElementException(eror);
							
						}
						// execute the query
				
					
				
					
				} finally {
					DBUtil.closeQuietly(stmt);
				}
				return null;
			}
		});
	}
	

	public Integer creatpm(final int user1, final int user2) {
		return executeTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException {
				
				if (user2==-1) {
					return null;
				}
				PreparedStatement stmt = null;
			//	ResultSet resultSet = null;
				Integer pmid=-1;
				long now=Instant.now().toEpochMilli();
				try { //chatnames
						pmid=getpmid(user1,user2);
						
						if(pmid==null||pmid==-1) {
							stmt = conn.prepareStatement(

									"insert into pmlist (user1id,user1lastacess ,user2id, user2lastacess) values ( ?,?,?,?) "

							);
							
							stmt.setInt(1, user1);
							stmt.setLong(2, now);
							stmt.setInt(3, user2);
							stmt.setLong(4, 0l);
							stmt.executeUpdate();
														
						}
						else {
							//String eror="the chat ";
							//eror+=" already exsits ";
							//throw new NoSuchElementException(eror);
							
						}
						// execute the query
				
					
				
					
				} finally {
					DBUtil.closeQuietly(stmt);
				}
				
				return pmid;
			}
		});
	}

	public List<post> getpm(final int numposts ,final int pmid,final int retriverid) {

		return executeTransaction(new Transaction<List<post>>() {
			public List<post> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
					stmt = conn.prepareStatement(
							"SELECT postid " + 
							"FROM pmchats "
							+ "where pmid= ?"
							
					);//gets all the posts with this pm id
					stmt.setInt(1, pmid);
					
					resultSet = stmt.executeQuery();

						//int totalposts=0 ;
						ArrayList<Integer> postids=new 	ArrayList<Integer>();
						while (resultSet.next()) {
							postids.add(resultSet.getInt(1));
							
						}
							
						//Collections.sort(postids);
						
						int bottomindexindb = 0;   // bottom index is the newest post that is requested
						if(postids.size()>0) {
							 bottomindexindb = postids.get(postids.size()-1); 
						}
						int topindexindb = 0;  //top index is the oldest post requested 
						if(postids.size()>numposts+2) {
							topindexindb = postids.get(postids.size()-(numposts+1));
						}
					
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
			
					
					stmt = conn.prepareStatement(
							"select pmchats.postid , pmchats.userid , pmchats.timeposted, pmchats.posttext, users.username  from users , pmchats " 
							+"where postid >= ? " + 
							" and postid <= ? "
							+ "and pmchats.userid = users.userid "
							+ " and pmid= ?"
					);//selects everything between top and bottom
					stmt.setInt(1, topindexindb);
					stmt.setInt(2, bottomindexindb);
					stmt.setInt(3, pmid);
					ArrayList<post> result=new ArrayList<post>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned 
				
					while (resultSet.next()) {
						
						post post = new post();
						loadpost(post, resultSet, 1);
						
						//System.out.println(post);
						result.add(post);
						
					}
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
						
			
					Collections.sort(result);
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					System.out.println(pmid);
					//updatepmacesstime(retriverid,pmid);
				}  
			}
		});
	}
	
	
	
	

	public List<post> posttopm(final long mils_time, final String posttext, final int senderid, final int pmid) {
		// TODO Auto-generated method stub
		 executeTransaction(new Transaction<post>() {
				public post execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
					
					stmt = conn.prepareStatement(
	
							"insert into pmchats (userid,  timeposted, posttext,pmid)"
							+" values( ? ,  ?, ?,?)"

					);

					stmt.setInt(1, senderid);
					//stmt.setString(2, username);
					stmt.setLong(2, mils_time);
					stmt.setString(3, posttext);
					stmt.setInt(4, pmid);
					// execute the query
					
					stmt.executeUpdate();

					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
					return null;
				}
			});

		
		
		
		
		 //updatepmacesstime(senderid,pmid);
		return null;
	}

	public int getpmid(final int user1, final int user2) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { 
						stmt = conn.prepareStatement(//user1id   user2id
								"select pmid from pmlist "
								+ " where (user1id= ? and user2id= ? ) or (user1id= ? and user2id= ? )"
						);
						stmt.setInt(1, user1);
						stmt.setInt(2, user2);
						stmt.setInt(3, user2);
						stmt.setInt(4, user1);
						resultSet = stmt.executeQuery();

						int chatid=-1;
						
						if (resultSet.next()) {
							chatid=resultSet.getInt(1);
						}
						return  chatid;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
				}
			
			}
		});
		
	}
	
	
	public List<Triplet<String, Integer,Integer>> getpmlist(final int userid) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<List<Triplet<String, Integer,Integer>>>() {
			public List<Triplet<String, Integer,Integer>> execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { 
						stmt = conn.prepareStatement(//user1id   user2id
								"select pmid, user1id, user2id, user1lastacess, user2lastacess from pmlist "
								+ " where (user1id= ? ) or (user2id= ? )"
						);
						stmt.setInt(1, userid);
						stmt.setInt(2, userid);
						
						resultSet = stmt.executeQuery();

						List<Triplet<String, Integer,Integer>> result=new ArrayList<Triplet<String, Integer,Integer>>();
						//List<Map.Entry<String, Integer>> result=new LinkedList(Map.entrySet());
						
						// for testing that a result was returned
						
						ArrayList<Triplet<Long, Integer,Integer>> otherusers=new ArrayList<Triplet<Long, Integer,Integer>>();
						
						//key pmchat id
						//value other userid
						int pmid,user1,user2,userf;
						long lastacess;
						while(resultSet.next()) {
							 pmid=resultSet.getInt(1);
							 user1=resultSet.getInt(2);
							 user2=resultSet.getInt(3);
							if(user1!=userid) {
								userf=user1;
								lastacess=resultSet.getLong(5);
							}
							else  {
								userf=user2;
								lastacess=resultSet.getLong(4);
							}
							if(userf>0) {
								//Entry<Integer,Integer> tmp =new AbstractMap.SimpleEntry<Integer, Integer>(pmid, userf);
								Triplet<Long, Integer,Integer> tmp=new Triplet<Long, Integer,Integer>(lastacess,pmid,userf);
								otherusers.add(tmp);
							}
							//first last acessed
							//key pmid    second
							//value  final userid   third
							
						}
						String username=null;
						for (Triplet<Long, Integer,Integer> thing : otherusers) {
							username=getusernamebyid(thing.getThird());
							int num=getunreadpms(userid,  thing.getSecond(),thing.getFirst());
							Triplet<String, Integer,Integer> tmp =new Triplet<String, Integer,Integer>(username, thing.getSecond(),num);

							result.add(tmp);
							
						}
						
						return  result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			
			}
		});
		

	}

	public String getusernamebyid(final int userid) {
		 return executeTransaction(new Transaction<String>() {
				public String execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					String username=null;
					try {
						stmt = conn.prepareStatement(
								"select username   "
								+"  from users  "
								+"  where 	users.userid =  ?  "
						);

						stmt.setInt(1, userid);

						resultSet = stmt.executeQuery();
						
						if (resultSet.next()) {
							
							username=resultSet.getString(1);
						}
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
					return username;
				}
			});
		
		
		
		
		
		// TODO Auto-generated method stub
	}

	public void updatepmacesstime(final int userid,final int pmid) {
		// TODO Auto-generated method stub
		//System.out.println("    in db   ubdating pm acces time     "+pmid);
		executeTransaction(new Transaction<post>() {
			public post execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				//System.out.println("eror");
				Integer user1=null,user2=null ;
				long now=Instant.now().toEpochMilli();
				
				try { 
					stmt = conn.prepareStatement(//user1id   user2id
							"select user1id, user2id  from pmlist "
							+ " where pmid= ?"
					);
					stmt.setInt(1, pmid);
					
					
					resultSet = stmt.executeQuery();
					boolean found=false;
					if (resultSet.next()) {
						found=true;
						user1=resultSet.getInt(1);
						user2=resultSet.getInt(2);
					}
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					System.out.println("    in db  user1  "+user1+"     "+user2+"   "+pmid);
					if (found) {
						if(userid==user1) {
							System.out.println("    in db ubdating user1  ");
							stmt2 = conn.prepareStatement(
									"UPDATE pmlist "+
									" set user1lastacess= ?  "
									+ "where pmid = ? "
							);

							
							
							stmt2.setLong(1, now);
							stmt2.setInt(2, pmid);
							stmt2.executeUpdate();
							
						}
						else if(userid==user2){
							System.out.println("    in db ubdating user2  ");
							stmt2 = conn.prepareStatement(
									"UPDATE pmlist "+
									" set user2lastacess= ?  "
									+ "where pmid = ? "
							);

							
							
							stmt2.setLong(1, now);
							stmt2.setInt(2, pmid);
							stmt2.executeUpdate();
						}
					}
					
					
						
						
						
						// execute the query
				
						
				
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
				}
				return null;
			}
		});
		
	}

	public int getunreadpms(final int retriverid, final int pmid, final long lastacessed) {
		return executeTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try { 
						//System.out.println("    getunreadpms    "+ pmid+"   "+retriverid);
						stmt = conn.prepareStatement(//user1id   user2id  timeposted 
								"select timeposted from pmchats "
								+ " where pmid= ? and userid <> ? "
						);
						stmt.setInt(1, pmid);
						//stmt.setLong(2, lastacessed);
						//stmt.setLong(2, 0l);
						stmt.setInt(2, retriverid);
						resultSet = stmt.executeQuery();

						int num=0;
						long tmp;
						//System.out.println("    getunreadpmsresuluy    "+ resultSet.getInt(1));
						while (resultSet.next()) {
							tmp=resultSet.getLong(1);
							if (tmp>lastacessed) {
								num++;
								//System.out.println("__--getunreadpms    "+ (tmp-lastacessed));
							}
						}
						return  num;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
				}
			
			}
		});
		
	}

	public void addtoblacklist(final int blockerid, final int blockieid) {		
		executeTransaction(new Transaction<post>() {
			public post execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try { 
				
					stmt = conn.prepareStatement(

							"insert into blacklist (blocker_id,blockee_id) values ( ?,?)"

					);
			
					stmt.setInt(1, blockerid);
					stmt.setInt(2, blockieid);
					stmt.executeUpdate();
						
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return null;
			}
		});
		
		
		
		
	}

	public long getlastposttime(final int chatid) {

		return executeTransaction(new Transaction<Long>() {
			public Long execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					int lastindex = 0;
					Long lasttime = null;
					
					stmt = conn.prepareStatement(
							"SELECT MAX (postid) " + 
							"FROM posts "
							+ "where chatname= ? "
							
					);//gets the largest(newest) post id
					stmt.setInt(1, chatid);
				
					
					resultSet = stmt.executeQuery();
						
						if (resultSet.next()) {
							lastindex=resultSet.getInt(1);
						}
					
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
			
					
					stmt = conn.prepareStatement(
							"select timeposted from posts " 
							+"where postid = ? " 
							+ " and chatname= ? "
					);//selects everything between top and bottom
					stmt.setInt(1, lastindex);
					stmt.setInt(2, chatid);

					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned 
				
					if (resultSet.next()) {
						
						lasttime=resultSet.getLong(1);
						
					}
					
					//Collections.sort(result);
					
					return lasttime;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}  
			}
		});
	}

	public void storeimg(final int userid, final Blob ingfile) {
		// TODO Auto-generated method stub
		executeTransaction(new Transaction<post>() {
			public post execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Integer dbusid=null;
				try { 
					stmt = conn.prepareStatement(

							"select userid from userimg where userid=? "

					);
					stmt.setInt(1, userid);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned 
				
					if (resultSet.next()) {
						
						dbusid=resultSet.getInt(1);
						
					}
					
					
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
					if(dbusid==null) {
						stmt = conn.prepareStatement(
	
								"insert into userimg (userid , imgbolb) values ( ? , ?)"
	
						);
				
						stmt.setInt(1, userid);
						stmt.setBlob(2, ingfile);
						
						stmt.executeUpdate();
					}	
					else {
						stmt = conn.prepareStatement(
								
								"update userimg  set imgbolb =? where userid=?"
	
						);
				
						stmt.setInt(2, userid);
						stmt.setBlob(1, ingfile);
						
						stmt.executeUpdate();
					}
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return null;
			}
		});
		
		
	}
	/*
	 * 
					stmt11 = conn.prepareStatement(
							"create table userimg (" +
									"	userid integer  primary key, " +	
									"	imgbolb LONGBLOB "+
									")"
					);	
					stmt11.executeUpdate();
					
	 * 
	 * (non-Javadoc)
	 * @see website4.database.IDatabase#getimg(int)
	 */
	public String getimg(final int userid) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Blob img;
				String out = null;
				img=conn.createBlob();
				
				try { 
					stmt = conn.prepareStatement(

							"select imgbolb from userimg where userid=? "

					);
					stmt.setInt(1, userid);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned 
					
					if (resultSet.next()) {
						
						img=resultSet.getBlob(1);
						System.out.println("ayayayaya");
						out =new String(img.getBytes(1, (int) img.length()), "UTF-8");
					}
					
					
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return out;
			}
		});
		
	
	}

	
	
}
	


