package Helper;

public class Utillities {

	private static Utillities util;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public static Utillities getInstance()
	{
		if(util == null)
			util = new Utillities();
		return util;
	}
	
}
