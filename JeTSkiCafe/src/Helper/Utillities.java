package Helper;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import Database.Connect;
import Page.AlertWindow;
import javafx.scene.control.Alert.AlertType;

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
	public boolean isEmailExists(String str)
	{
		Connect connect = Connect.getConnection();
		String query = "SELECT * FROM user";
		ResultSet rs = connect.executeQuery(query);
		try {
			while(rs.next())
			{
				String email = rs.getString("userEmail");
				if(email.equals(str))
				{
					return true;
				}
			}
		} catch (Exception e) {
			new AlertWindow(AlertType.ERROR, "Failed to check email exists from Database");
		}
		return false;
	}
}
