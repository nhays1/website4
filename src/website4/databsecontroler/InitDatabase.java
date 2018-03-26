package website4.databsecontroler;

import java.util.Scanner;

import website4.database.DatabaseProvider;
import website4.database.DerbyDatabase;
import website4.database.FakeDatabase;



public class InitDatabase {
	/**
	 * 1 for real 0 for fake
	 * 
	 * 
	 * 
	 * 
	 * @param which
	 */
	public static void init(int which) {
		//System.out.print("Which database (0=fake, 1=derby): ");
		
		//which = Integer.parseInt(keyboard.nextLine());
		//which=1;
		if (which == 0) {
			DatabaseProvider.setInstance(new FakeDatabase());
		} else if (which == 1) {
			DatabaseProvider.setInstance(new DerbyDatabase());
		} else {
			throw new IllegalArgumentException("Invalid choice: " + which);
		}
	}
}
