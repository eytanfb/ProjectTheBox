package GUI.Store;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

import Driver.MainDriver;
import GUI.MainWindow;
import GUI.Owner.OwnerAccount;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;

public class NewStoreForm extends JPanel
{
	private JTextField newStoreNameField;
	private JTextField addressField;
	private JSpinner rentalSpinner;
	private JSpinner lateFeeSpinner;
	private JSpinner maxRentSpinner;
	private JSpinner hiringPeriodSpinner;

	/**
	 * Create the panel.
	 */
	public NewStoreForm(final MainWindow frame, final String username)
	{
		setLayout(new GridLayout(7, 2, 0, 0));
		setSize(450, 330);
		
		JLabel lblName = new JLabel("Name:");
		add(lblName);
		
		newStoreNameField = new JTextField();
		add(newStoreNameField);
		newStoreNameField.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		add(lblAddress);
		
		addressField = new JTextField();
		add(addressField);
		addressField.setColumns(10);
		
		JLabel lblday = new JLabel("$/day:");
		add(lblday);
		
		rentalSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 60, 1));
		add(rentalSpinner);
		
		JLabel lblLateFeeday = new JLabel("Late Fee ($/day):");
		add(lblLateFeeday);
		
		lateFeeSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 60, 1));
		add(lateFeeSpinner);
		
		JLabel lblHiringPeriod = new JLabel("Hiring Period:");
		add(lblHiringPeriod);
		
		hiringPeriodSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
		add(hiringPeriodSpinner);
		
		JLabel lblMaxRent = new JLabel("Max Rent:");
		add(lblMaxRent);
		
		maxRentSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
		add(maxRentSpinner);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearEverything();
			}
		});
		panel.add(btnClear);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JButton btnCreateStore = new JButton("Create Store");
		btnCreateStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String insertStore	= "INSERT INTO store (name, owner, address, rental, late, maxrent, hire) VALUES ('" 
			+ newStoreNameField.getText() + "', '" 
						+ username + "', '" 
			+ addressField.getText() + "', " 
						+ Integer.parseInt(rentalSpinner.getModel().getValue().toString()) + ", " 
			+ Integer.parseInt(lateFeeSpinner.getModel().getValue().toString()) + ", " 
						+ Integer.parseInt(maxRentSpinner.getModel().getValue().toString()) + ", " 
			+ Integer.parseInt(hiringPeriodSpinner.getModel().getValue().toString()) + ")";
				
				try
				{
					Statement stmt = MainDriver.connection.createStatement();
					int insert = stmt.executeUpdate(insertStore);
					if(insert > 0)
					{
						JOptionPane.showMessageDialog(null, "New Store Created");
						frame.setContentPaneFromOutside(new OwnerAccount(frame, username));
					}
					else
						JOptionPane.showMessageDialog(null, "FAIL!");
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panel_1.add(btnCreateStore);
		
		

	}

	protected void clearEverything()
	{
		newStoreNameField.setText("");
		addressField.setText("");
		rentalSpinner.setValue(0);
		lateFeeSpinner.setValue(0);
		maxRentSpinner.setValue(0);
		hiringPeriodSpinner.setValue(0);
	}

}
