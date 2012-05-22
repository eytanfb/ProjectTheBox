package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.sound.sampled.ReverbType;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import GUI.Customer.CustomerAccount;
import GUI.Customer.CustomerPlaceOrderScreen;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainWindow extends JFrame
{
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainWindow()
	{
		setTitle("The Box");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnScreens = new JMenu("Screens");
		menuBar.add(mnScreens);
		
		JMenuItem mntmLoginScreen = new JMenuItem("Login Screen");
		mntmLoginScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPaneFromOutside(new LoginScreen());
			}
		});
		mnScreens.add(mntmLoginScreen);
		
		JMenuItem mntmCustomerPlaceOrder = new JMenuItem("Customer Place Order Screen");
		mntmCustomerPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPaneFromOutside(new CustomerPlaceOrderScreen());
			}
		});
		mnScreens.add(mntmCustomerPlaceOrder);
		
		JMenuItem mntmCustomerAccountScreen = new JMenuItem("Customer Account Screen");
		mntmCustomerAccountScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPaneFromOutside(new CustomerAccount());
			}
		});
		mnScreens.add(mntmCustomerAccountScreen);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void setContentPaneFromOutside(JPanel panel)
	{
		getContentPane().removeAll();
		contentPane.add(panel);
		setBounds(getX(), getY(), panel.getWidth(), panel.getHeight());
		validate();
	}
}
