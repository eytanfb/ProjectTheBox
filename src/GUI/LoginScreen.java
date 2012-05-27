package GUI;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import GUI.Customer.CustomerAccount;
import GUI.Customer.CustomerPlaceOrderScreen;
import GUI.Owner.OwnerAccount;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginScreen extends JPanel
{
	private JTextField userNameTextField;
	private JButton btnClear;
	private JButton btnSubmit;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginScreen(final MainWindow frame, final Connection conn)
	{
		setLayout(new GridLayout(3, 2, 10, 10));
		setSize(new Dimension(406, 150));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblUsername);
		
		userNameTextField = new JTextField();
		userNameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		add(userNameTextField);
		userNameTextField.setColumns(40);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		add(passwordField);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextFields();
			}
		});
		add(btnClear);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passwordString = new String(passwordField.getPassword());
				String loginStatement = "SELECT type FROM userlist WHERE username = '" + userNameTextField.getText() + "' and password = '" + passwordString + "'";
	
				try
				{
					Statement stmt = conn.createStatement();
					ResultSet rset = stmt.executeQuery(loginStatement);
					
					if(rset.next())
					{
						if((rset.getInt("type") == 1))
						{
							frame.setContentPaneFromOutside(new OwnerAccount(frame, userNameTextField.getText()));
						}
						else if(rset.getInt("type") == 2)
						{
							frame.setContentPaneFromOutside(new CustomerPlaceOrderScreen(frame, userNameTextField.getText()));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Invalid Username/Password");
						clearTextFields();
					}
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
			}
		});
		add(btnSubmit);
	}

	private void clearTextFields()
	{
		userNameTextField.setText("");
		passwordField.setText("");
	}

}
