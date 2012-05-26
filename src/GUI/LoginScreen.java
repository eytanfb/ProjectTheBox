package GUI;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginScreen extends JPanel
{
	private JTextField userNameTextField;
	private JButton btnClear;
	private JButton btnSubmit;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginScreen()
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
				//TODO: Sql Statement to check username password and grant entry to appropriate place
				/** 
				 * Statement stmt = conn.createStatement();
				 * ResultSet rset = stmt.executeQuery("select usertype from userlist where username ='" 
				 * + userNameTextField.getText() + "' and password ='" + passwordField.getText() + "'");
				 * while(rset.next())
				 * {
				 * 	 string loginName = rset.getString("type");
				 * 	if(loginName.equals("0"))
				 * 		goToCustomerScreen(userNameTextField.getText());
				 *  else if(loginName == "1")
				 *  	goToOwnerScreen(userNameTextField.getText());
				 *  else
				 *  	System.out.println("FAIL!!");
				 * }
				 * 
				 * */
				
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
