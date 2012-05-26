package GUI.Owner;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import GUI.MainWindow;
import GUI.Store.NewStoreForm;
import GUI.Store.Store;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OwnerAccount extends JPanel
{
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public OwnerAccount(final MainWindow frame)
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		storeSelectionPanel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ortakoy", "Besiktas", "Maslak"}));
		comboBox.setSelectedIndex(0);
		
		JPanel goToStorePanel = new JPanel();
		storesPanel.add(goToStorePanel);
		
		JButton btnGoToStore = new JButton("Go To Store");
		btnGoToStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPaneFromOutside(new Store(frame));
			}
		});
		goToStorePanel.add(btnGoToStore);
		
		JPanel otherOptionsPanel = new JPanel();
		storesPanel.add(otherOptionsPanel);
		
		JButton btnCreateNewStore = new JButton("Create New Store");
		btnCreateNewStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPaneFromOutside(new NewStoreForm());
			}
		});
		otherOptionsPanel.add(btnCreateNewStore);
		
		JPanel bestSellingPanel = new JPanel();
		bestSellingPanel.setBorder(new TitledBorder(null, "Best Selling", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(bestSellingPanel);
		
		String[] columns = {"Film", "How Many Orders"};
		Object[][] data = {};
		bestSellingPanel.setLayout(new BoxLayout(bestSellingPanel, BoxLayout.Y_AXIS));
		JTable bestSellingTable = new JTable(data, columns);
		
		JScrollPane bestSellingScrollPane = new JScrollPane(bestSellingTable);
		bestSellingPanel.add(bestSellingScrollPane);
	}

}
