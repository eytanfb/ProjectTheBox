package GUI.Customer;

import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFormattedTextField;
import javax.swing.border.TitledBorder;

import Driver.MainDriver;
import GUI.MainWindow;
import GUI.MyTableModel;

import javax.swing.JComboBox;

public class CustomerAccount extends JPanel
{
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTable recentOrdersTable;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;

	/**
	 * Create the panel.
	 */
	public CustomerAccount(final MainWindow frame, final String username)
	{
		setLayout(new GridLayout(2, 0, 0, 0));
		setSize(906, 381);
		
		JPanel accountInfoPanel = new JPanel();
		add(accountInfoPanel);
		accountInfoPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel userNamePanel = new JPanel();
		userNamePanel.setBorder(new TitledBorder(null, "Edit Account Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		accountInfoPanel.add(userNamePanel);
		userNamePanel.setLayout(new GridLayout(6, 2, 0, 0));
		
		JLabel lblUsername = new JLabel("Username:");
		userNamePanel.add(lblUsername);
		
		userNameTextField = new JTextField();
		userNameTextField.setEditable(false);
		userNamePanel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		userNamePanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		userNamePanel.add(passwordField);
		
		JLabel lblName = new JLabel("Name:");
		userNamePanel.add(lblName);
		
		firstNameTextField = new JTextField();
		userNamePanel.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		userNamePanel.add(lblLastName);
		
		lastNameTextField = new JTextField();
		userNamePanel.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		userNamePanel.add(lblAddress);
		
		addressTextField = new JTextField();
		userNamePanel.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		userNamePanel.add(lblPhone);
		
		phoneTextField = new JTextField();
		userNamePanel.add(phoneTextField);
		phoneTextField.setColumns(10);
		
		JPanel buttonPanel = new JPanel();
		accountInfoPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwd = new String(passwordField.getPassword());
				String changeStatement = "UPDATE userlist SET password = '" + pwd + "', name = '" 
				+ firstNameTextField.getText() + "', surname = '" 
						+ lastNameTextField.getText() 
						+ "', address = '" + addressTextField.getText() 
						+ "', phone = " + phoneTextField.getText() 
						+ " WHERE username = '" + username + "'";
				
				if(passwordField.getPassword().length < 6)
					JOptionPane.showMessageDialog(frame, "Password has to be at least 6 alphanumeric characters");
				else
				{
				
					try
					{
						Statement stmt = MainDriver.connection.createStatement();
						stmt.executeQuery(changeStatement);
					
					} catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
			}
		});
		buttonPanel.add(btnChange);
		
		JButton btnBackToOrdering = new JButton("Back to Ordering Screen");
		btnBackToOrdering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPaneFromOutside(new CustomerPlaceOrderScreen(frame, username));
			}
		});
		buttonPanel.add(btnBackToOrdering);
		
		JPanel otherInfoPanel = new JPanel();
		add(otherInfoPanel);
		otherInfoPanel.setLayout(new BoxLayout(otherInfoPanel, BoxLayout.X_AXIS));
		
		JPanel favoritesPanel = new JPanel();
		otherInfoPanel.add(favoritesPanel);
		favoritesPanel.setLayout(new BoxLayout(favoritesPanel, BoxLayout.Y_AXIS));
		
		JLabel lblFavoriteStores = new JLabel("Favorite Stores");
		lblFavoriteStores.setHorizontalAlignment(SwingConstants.CENTER);
		favoritesPanel.add(lblFavoriteStores);
		
		final Vector<String> faves = new Vector<String>();
		final JList favoritesList = new JList(faves);
		
		showFavorites(username, faves, favoritesList);
		
		JScrollPane favoritesScrollPane = new JScrollPane(favoritesList);
		favoritesScrollPane.setEnabled(false);
		favoritesPanel.add(favoritesScrollPane);
		
		JButton btnRemoveFromFavorites = new JButton("Remove from Favorites");
		btnRemoveFromFavorites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String storeName = (String)favoritesList.getSelectedValue();
				
				Statement stmt;
				try
				{
					stmt = MainDriver.connection.createStatement();
					stmt.executeQuery("DELETE FROM favourite WHERE username = '" + username + "' and store = '" + storeName + "'");
					
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				showFavorites(username, faves, favoritesList);
			}
		});
		favoritesPanel.add(btnRemoveFromFavorites);
		
		JPanel recentOrdersPanel = new JPanel();
		otherInfoPanel.add(recentOrdersPanel);
		recentOrdersPanel.setLayout(new BoxLayout(recentOrdersPanel, BoxLayout.Y_AXIS));
		
		JLabel lblRecentOrders = new JLabel("Recent Orders");
		recentOrdersPanel.add(lblRecentOrders);
		
		String[] recentOrdersTableColumns = {"Order ID", "Store", "Film", "Total Cost"};
		Object[][] data = {};
		recentOrdersTable = new JTable(new MyTableModel(data, recentOrdersTableColumns));
		recentOrdersTable.setEnabled(true);
		recentOrdersTable.setFillsViewportHeight(true);
		recentOrdersTable.setCellSelectionEnabled(true);
		
		JScrollPane recentOrdersScrollPane = new JScrollPane(recentOrdersTable);	
		recentOrdersPanel.add(recentOrdersScrollPane);
		
		JPanel panel = new JPanel();
		recentOrdersPanel.add(panel);
		
		String[] days = new String[31];
		for(int i = 0; i < 31; i++)
		{
			days[i] = String.valueOf(i+1);
		}
		
		String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
		
		
		String[] years = new String[10];
		for(int i = 0; i < 10; i++)
		{
			years[i] = String.valueOf(i+10);
		}
		
		JLabel lblFrom = new JLabel("From:");
		panel.add(lblFrom);
		
		final JComboBox fromDayComboBox = new JComboBox(days);
		fromDayComboBox.setSelectedIndex(0);
		fromDayComboBox.setMaximumRowCount(31);
		panel.add(fromDayComboBox);
		
		final JComboBox fromMonthComboBox = new JComboBox(months);
		fromMonthComboBox.setSelectedIndex(0);
		panel.add(fromMonthComboBox);
		
		final JComboBox fromYearComboBox = new JComboBox(years);
		fromYearComboBox.setSelectedIndex(0);
		panel.add(fromYearComboBox);
		
		JLabel lblTo = new JLabel("To:");
		panel.add(lblTo);
		
		final JComboBox toDayComboBox = new JComboBox(days);
		toDayComboBox.setSelectedIndex(30);
		panel.add(toDayComboBox);
		
		final JComboBox toMonthComboBox = new JComboBox(months);
		toMonthComboBox.setSelectedIndex(11);
		panel.add(toMonthComboBox);
		
		final JComboBox toYearComboBox = new JComboBox(years);
		toYearComboBox.setSelectedIndex(9);
		panel.add(toYearComboBox);
		
		JButton btnShowOrders = new JButton("Show Orders");
		btnShowOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String fromDate = fromDayComboBox.getSelectedItem().toString() + "-" + (String)fromMonthComboBox.getSelectedItem() + "-" + fromYearComboBox.getSelectedItem().toString();
				String toDate = toDayComboBox.getSelectedItem().toString() + "-" + (String)toMonthComboBox.getSelectedItem().toString() + "-" + toYearComboBox.getSelectedItem().toString();
				
				showRecentOrders(username, fromDate, toDate);
			}
		});
		panel.add(btnShowOrders);
		
		try
		{
			Statement stmt = MainDriver.connection.createStatement();
			ResultSet rset = stmt.executeQuery("select * from userlist where username = '" + username + "'");
			
			while(rset.next())
			{
				userNameTextField.setText(rset.getString("username"));
				passwordField.setText(rset.getString("password"));
				addressTextField.setText(rset.getString("address"));
				phoneTextField.setText(rset.getString("phone"));
				firstNameTextField.setText(rset.getString("name"));
				lastNameTextField.setText(rset.getString("surname"));				
			}
			
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String fromDate = fromDayComboBox.getSelectedItem().toString() + "-" + (String)fromMonthComboBox.getSelectedItem() + "-" + fromYearComboBox.getSelectedItem().toString();
		String toDate = toDayComboBox.getSelectedItem().toString() + "-" + (String)toMonthComboBox.getSelectedItem().toString() + "-" + toYearComboBox.getSelectedItem().toString();
		showRecentOrders(username, fromDate, toDate);
	}

	private void showRecentOrders(final String username, String fromDate, String toDate)
	{
		String ordersBetweenDates = "SELECT id, store, film, cost FROM orderlist WHERE customer = '" + username + "' and begin between '" + fromDate + "' and '" + toDate + "'";	
		
		try
		{
			Statement stmt = MainDriver.connection.createStatement();
			ResultSet rset = stmt.executeQuery(ordersBetweenDates);
			
			System.out.println(fromDate);
			System.out.println(toDate);
			
			MyTableModel dataModel = new MyTableModel();
			ResultSetMetaData mdata = rset.getMetaData();
		    int colCount = mdata.getColumnCount();
		    String[] colNames = new String[colCount];
		    for (int i = 1; i <= colCount; i++) 
		    {
		    	colNames[i - 1] = mdata.getColumnName(i);
		    }
		    dataModel.setColumnIdentifiers(colNames);
		 
		    //now populate the data
		    while (rset.next()) 
		    {
		    	//System.out.println(rset.getString("film"));
		    	String[] rowData = new String[colCount];
		        for (int i = 1; i <= colCount; i++) 
		        {
		          rowData[i - 1] = rset.getString(i);
		        }
		        dataModel.addRow(rowData);
		        if(dataModel != null)
		        	recentOrdersTable.setModel(dataModel);
			
		    }
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void showFavorites(final String username, Vector<String> faves, JList list)
	{
		faves = new Vector<String>();
		try
		{
			Statement stmt = MainDriver.connection.createStatement();
			ResultSet rset = stmt.executeQuery("select store from favourite where username = '" + username + "'");
			
			while(rset.next())
			{
				faves.add(rset.getString("store"));
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultListModel model = new DefaultListModel();
		for(String s: faves)
			model.addElement(s);
		list.setModel(model);
	}

}
