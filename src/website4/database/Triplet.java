package website4.database;


//This class allows us to relate 3 things today similarly to how we used Pair<> in Lab06
//The application of this class is in the FakeDatabase
public class Triplet <T, U, V> {
	

	    private final T first;
	    private final U second;
	    private final V third;

	    public Triplet(T first, U second, V third) {
	        this.first = first;
	        this.second = second;
	        this.third = third;
	    }

	    public T getFirst() { return first; }
	    public U getSecond() { return second; }
	    public V getThird() { return third; }
	    
	}

//This code was taken from stackoverflow by username Osguima3

//https://stackoverflow.com/questions/6010843/java-how-to-store-data-triple-in-a-list?utm_medium=or
//ganic&utm_source=google_rich_qa&utm_campaign=google_rich_qa