package Helper;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public String getDate()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return formatter.format(date);
	}
	
	public boolean isDigit(String s) {
		int len = s.length();
		for(int i=0;i<len;i++)
		{
			if(!Character.isDigit(s.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	
}
