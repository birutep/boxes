package lt.world.app.boxes;

public class BoxId {

	private static Long id = 0L;
	
	public static Long getNext() {		
		return ++id;
	}
	
}
