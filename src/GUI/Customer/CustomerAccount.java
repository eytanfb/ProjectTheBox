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
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.border.TitledBorder;

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
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public CustomerAccount(final MainWindow frame)
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
		
		textField = new JTextField();
		userNamePanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		userNamePanel.add(lblLastName);
		
		textField_1 = new JTextField();
		userNamePanel.add(textField_1);
		textField_1.setColumns(10);
		
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
		buttonPanel.add(btnChange);
		
		JButton btnBackToOrdering = new JButton("Back to Ordering Screen");
		btnBackToOrdering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPaneFromOutside(new CustomerPlaceOrderScreen(frame));
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
		
		String[] faves = {"Ortakoy", "Ulus", "Bebek", "Besiktas", "Taksim", "Levent"};
		JList favoritesList = new JList(faves);
		favoritesList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Ortakoy", "Ulus", "Bebek", "Besiktas", "Taksim", "Levent", "Sariyer", "Tarabya", "Maslak", "Zekeriyakoy", "Akatlar", "Kadikoy"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JScrollPane favoritesScrollPane = new JScrollPane(favoritesList);
		favoritesScrollPane.setEnabled(false);
		favoritesPanel.add(favoritesScrollPane);
		
		JButton btnRemoveFromFavorites = new JButton("Remove from Favorites");
		favoritesPanel.add(btnRemoveFromFavorites);
		
		JPanel recentOrdersPanel = new JPanel();
		otherInfoPanel.add(recentOrdersPanel);
		recentOrdersPanel.setLayout(new BoxLayout(recentOrdersPanel, BoxLayout.Y_AXIS));
		
		JLabel lblRecentOrders = new JLabel("Recent Orders");
		recentOrdersPanel.add(lblRecentOrders);
		
		String[] recentOrdersTableColumns = {"Order ID", "Store", "Film", "Total Cost"};
		Object[][] data = {{"12312423", "Ortakoy", "Gladiator", "$5"}};
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
		
		String[] months = new String[12];
		for(int i = 0; i < 12; i++)
		{
			months[i] = String.valueOf(i+1);
		}
		
		String[] years = new String[10];
		for(int i = 0; i < 10; i++)
		{
			years[i] = String.valueOf(i+2010);
		}
		
		JLabel lblFrom = new JLabel("From:");
		panel.add(lblFrom);
		
		JComboBox fromDayComboBox = new JComboBox(days);
		fromDayComboBox.setSelectedIndex(0);
		fromDayComboBox.setMaximumRowCount(31);
		panel.add(fromDayComboBox);
		
		JComboBox fromMonthComboBox = new JComboBox(months);
		fromMonthComboBox.setSelectedIndex(0);
		panel.add(fromMonthComboBox);
		
		JComboBox fromYearComboBox = new JComboBox(years);
		fromYearComboBox.setSelectedIndex(0);
		panel.add(fromYearComboBox);
		
		JLabel lblTo = new JLabel("To:");
		panel.add(lblTo);
		
		JComboBox toDayComboBox = new JComboBox(days);
		toDayComboBox.setSelectedIndex(30);
		panel.add(toDayComboBox);
		
		JComboBox toMonthComboBox = new JComboBox(months);
		toMonthComboBox.setSelectedIndex(11);
		panel.add(toMonthComboBox);
		
		JComboBox toYearComboBox = new JComboBox(years);
		toYearComboBox.setSelectedIndex(9);
		panel.add(toYearComboBox);
		
		JButton btnShowOrders = new JButton("Show Orders");
		panel.add(btnShowOrders);
	}

}
