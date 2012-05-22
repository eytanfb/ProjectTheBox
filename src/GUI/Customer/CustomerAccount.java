package GUI.Customer;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
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

public class CustomerAccount extends JPanel
{
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public CustomerAccount()
	{
		setLayout(new GridLayout(2, 0, 0, 0));
		setSize(547, 311);
		
		JPanel accountInfoPanel = new JPanel();
		add(accountInfoPanel);
		accountInfoPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblEditAccountInfo = new JLabel("Edit Account Info");
		accountInfoPanel.add(lblEditAccountInfo);
		
		JPanel userNamePanel = new JPanel();
		accountInfoPanel.add(userNamePanel);
		userNamePanel.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel lblUsername = new JLabel("Username:");
		userNamePanel.add(lblUsername);
		
		userNameTextField = new JTextField();
		userNamePanel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		userNamePanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		userNamePanel.add(passwordField);
		
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
		accountInfoPanel.add(buttonPanel);
		
		JButton btnChange = new JButton("Change");
		buttonPanel.add(btnChange);
		
		JPanel otherInfoPanel = new JPanel();
		add(otherInfoPanel);
		
		JPanel favoritesPanel = new JPanel();
		otherInfoPanel.add(favoritesPanel);
		favoritesPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblFavoriteStores = new JLabel("Favorite Stores");
		lblFavoriteStores.setEnabled(false);
		lblFavoriteStores.setHorizontalAlignment(SwingConstants.CENTER);
		favoritesPanel.add(lblFavoriteStores);
		
		JList favoritesList = new JList();
		favoritesList.setEnabled(false);
		favoritesList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Ortakoy", "Tarabya", "Besiktas", "Ulus", "Etiler"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		favoritesPanel.add(favoritesList);
		
		JPanel recentOrdersPanel = new JPanel();
		otherInfoPanel.add(recentOrdersPanel);
		recentOrdersPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblRecentOrders = new JLabel("Recent Orders");
		recentOrdersPanel.add(lblRecentOrders);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setEnabled(false);
		recentOrdersPanel.add(table);
		
		
	}

}
