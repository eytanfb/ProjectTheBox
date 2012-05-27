package Driver;

import java.awt.EventQueue;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import GUI.LoginScreen;
import GUI.MainWindow;

public class MainDriver
{
	
	public static Connection connection = null;
	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		try
		{
			connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/XE", "TheBox", "sTevu9Ub");
		}
		catch(Exception e)
		{
			System.out.println("Connection failed" + "\n" + e.getMessage());
			System.exit(1);
		}
		
		MainWindow frame = new MainWindow();
		frame.setVisible(true);
		frame.setContentPaneFromOutside(new LoginScreen(frame, connection));
		
//		EventQueue.invokeLater(new Runnable()
//		{
//			public void run()
//			{
//				try
//				{
//					
//				} catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//		});	
	}
}
