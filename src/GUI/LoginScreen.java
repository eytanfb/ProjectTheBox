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
		setSize(new Dimension(406, 119));
		setVisible(true);
		
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
		add(btnClear);
		
		btnSubmit = new JButton("Submit");
		add(btnSubmit);
		
	}

}
