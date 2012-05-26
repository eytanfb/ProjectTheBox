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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					frame.setContentPaneFromOutside(new LoginScreen());
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});	
	}
}
