package realDBTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import website4.controller.UserController;
import website4.database.Triplet;
import website4.model.usser;

public class RealDatabaseUserTests {
	UserController ctrl;
	usser isindbl,guest;
	String teststring;
	String preEscaped;

	@Before
	public void setup() throws Exception{
		ctrl=new UserController();
		
		isindbl=new usser("jake","123",2,"notMyEmail@hotmail.gov",999999);
		
		guest=ctrl.createguestuser();
		
		teststring="<div) &dsdh>><ndo *&<>d";
		
		preEscaped="&lt;div) &amp;dsdh&gt;&gt;&lt;ndo *&amp;&lt;&gt;d";
		
		
	
	}
	
	@Test
	public void testlogin() {
		usser second = ctrl.loguserin(isindbl.getusername(), isindbl.getpassword());
		assertEquals(isindbl.getusername(),second.getusername());
		assertEquals(isindbl.getemail(),second.getemail());
		assertEquals(isindbl.getcoins(),second.getcoins());
		
		assertEquals(false,isindbl.getisguest() );
		
		
	}
	
	
	@Test
	public void testguestconstructor() {
		
		usser tmp=ctrl.createguestuser();
		usser t2= ctrl.getuserbyid(tmp.getuserid());
		
		assertEquals(true,t2.getisguest() );
		
		assertEquals("none",t2.getemail());
		
		
	}
	
	
	@Test
	public void testescapestring() {
		
		String ecape=ctrl.escapestring(teststring);
		
		assertEquals(preEscaped,ecape );
	}
	
	@Test
	public void testisguest() {
		assertEquals(true,ctrl.isguest(guest.getuserid()) );
		
		assertEquals(false,ctrl.isguest(isindbl.getuserid()) );
		
	}
	
	
	

	
	
	
	
	
	
	
}
