package GUI.Owner;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import Driver.MainDriver;
import GUI.MainWindow;
import GUI.Store.NewStoreForm;
import GUI.Store.Store;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Area;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OwnerAccount extends JPanel
{
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;

	/**
	 * Create the panel.
	 */
	public OwnerAccount(final MainWindow frame, final String username)
	{
		setLayout(new GridLayout(2, 0, 0, 0));
		setSize(721, 381);
		
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel storesPanel = new JPanel();
		storesPanel.setBorder(new TitledBorder(null, "Stores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(storesPanel);
		storesPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel storeSelectionPanel = new JPanel();
		storeSelectionPanel.setBorder(null);
		storesPanel.add(storeSelectionPanel);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		storeSelectionPanel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel());
		
		JPanel goToStorePanel = new JPanel();
		storesPanel.add(goToStorePanel);
		
		JButton btnGoToStore = new JButton("Go To Store");
		btnGoToStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPaneFromOutside(new Store(frame, username,  comboBox.getSelectedItem().toString()));
			}
		});
		goToStorePanel.add(btnGoToStore);
		
		JPanel otherOptionsPanel = new JPanel();
		storesPanel.add(otherOptionsPanel);
		
		JButton btnCreateNewStore = new JButton("Create New Store");
		btnCreateNewStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPaneFromOutside(new NewStoreForm(frame, username));
			}
		});
		otherOptionsPanel.add(btnCreateNewStore);
		
////		JPanel bestSellingPanel = new JPanel();
////		bestSellingPanel.setBorder(new TitledBorder(null, "Best Selling", TitledBorder.LEADING, TitledBorder.TOP, null, null));
////		panel.add(bestSellingPanel);
//		
//		String[] columns = {"Film", "How Many Orders"};
//		Object[][] data = {};
//		bestSellingPanel.setLayout(new BoxLayout(bestSellingPanel, BoxLayout.Y_AXIS));
//		JTable bestSellingTable = new JTable(data, columns);
//		
//		JScrollPane bestSellingScrollPane = new JScrollPane(bestSellingTable);
//		bestSellingPanel.add(bestSellingScrollPane);
		
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
			stmt.close();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String ownerStoresStatement = "SELECT name FROM store WHERE owner = '" + username + "'";
		
		try
		{
			Statement stmt = MainDriver.connection.createStatement();
			ResultSet rset = stmt.executeQuery(ownerStoresStatement);
			
			ArrayList<String> stores = new ArrayList<String>();
			while(rset.next())
			{
				stores.add(rset.getString("name"));
			}
			String[] storesArray = new String[stores.size()];
			for(int i = 0; i < stores.size(); i++)
			{
				storesArray[i] = stores.get(i);
			}
			comboBox.setModel(new DefaultComboBoxModel(storesArray));
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
