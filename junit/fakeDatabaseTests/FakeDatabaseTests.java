package fakeDatabaseTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import website4.database.Triplet;

public class FakeDatabaseTests {
	
	long mils_time;
	int userid;
	String posttext;
	
	List<Triplet<Long, Integer, String>> postsList = new ArrayList<Triplet<Long, Integer, String>>();
	
	Triplet<Long, Integer, String> posts; 

	@Before
	public void setup() throws Exception{
		
		//Populates a list of Triplets containing the information for testAddPost
		for(int i = 0; i < 10; i++) {
			
			mils_time = i;
			userid = i+1;
			posttext = Integer.toString(i+2);
			
			posts = new Triplet<Long, Integer, String>(mils_time, userid, posttext);
			
			postsList.add(posts);
		}
	
	}
	
	@Test
	public void testAddPost() {
		
		//10 elements were added so the size should be 10
		assertEquals(postsList.size(), 10);
		
		//Checks all the values within the list
		for(int i = 0; i < 10; i ++) {
		
			long f = postsList.get(i).getFirst();
			int s = postsList.get(i).getSecond();
			String t = postsList.get(i).getThird();
		
		assertEquals(f, i);
		assertEquals(s, i + 1);
		assertEquals(t, Integer.toString(i + 2));
			
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
