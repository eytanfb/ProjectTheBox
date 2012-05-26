package GUI.Store;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import GUI.MainWindow;
import GUI.MyTableModel;
import GUI.Owner.OwnerAccount;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Store extends JPanel
{
	private JTextField storeNameTextField;
	private JTextField storeAddressTextField;

	/**
	 * Create the panel.
	 * @param mainWindow 
	 */
	public Store(final MainWindow frame)
	{
		setLayout(new BorderLayout(0, 0));
		setSize(600, 682);
		
		JPanel storeInfoPanel = new JPanel();
		add(storeInfoPanel, BorderLayout.NORTH);
		storeInfoPanel.setLayout(new GridLayout(7, 2, 0, 0));
		
		JLabel lblName = new JLabel("Name:");
		storeInfoPanel.add(lblName);
		
		storeNameTextField = new JTextField();
		storeNameTextField.setEditable(false);
		storeInfoPanel.add(storeNameTextField);
		storeNameTextField.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		storeInfoPanel.add(lblAddress);
		
		storeAddressTextField = new JTextField();
		storeInfoPanel.add(storeAddressTextField);
		storeAddressTextField.setColumns(10);
		
		JLabel lblday = new JLabel("$/day:");
		storeInfoPanel.add(lblday);
		
		JSpinner rentalSpinner = new JSpinner();
		storeInfoPanel.add(rentalSpinner);
		
		JLabel lblLateFee = new JLabel("Late Fee ($/day):");
		storeInfoPanel.add(lblLateFee);
		
		JSpinner latePerDaySpinner = new JSpinner();
		storeInfoPanel.add(latePerDaySpinner);
		
		JLabel lblUserRentAllowance = new JLabel("User Rent Allowance:");
		storeInfoPanel.add(lblUserRentAllowance);
		
		JSpinner allowanceSpinner = new JSpinner();
		storeInfoPanel.add(allowanceSpinner);
		
		JLabel lblHiringPeriod = new JLabel("Hiring Period:");
		storeInfoPanel.add(lblHiringPeriod);
		
		JSpinner hiringPeriodSpinner = new JSpinner();
		storeInfoPanel.add(hiringPeriodSpinner);
		
		JPanel panel = new JPanel();
		storeInfoPanel.add(panel);
		
		JButton btnBackToAccount = new JButton("Back to Account");
		btnBackToAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPaneFromOutside(new OwnerAccount(frame));
			}
		});
		panel.add(btnBackToAccount);
		
		JPanel panel_1 = new JPanel();
		storeInfoPanel.add(panel_1);
		
		JButton btnChange = new JButton("Change");
		panel_1.add(btnChange);
		
		JPanel otherInfoPanel = new JPanel();
		add(otherInfoPanel, BorderLayout.CENTER);
		otherInfoPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel filmsInfoPanel = new JPanel();
		otherInfoPanel.add(filmsInfoPanel);
		filmsInfoPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel filmListPanel = new JPanel();
		filmListPanel.setBorder(new TitledBorder(null, "Film List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		filmsInfoPanel.add(filmListPanel);
		filmListPanel.setLayout(new BoxLayout(filmListPanel, BoxLayout.Y_AXIS));
		
		String[] columns = {"Film"};
		Object[][] data = {};
		JTable filmListTable = new JTable(new MyTableModel(data, columns));
		
		JScrollPane scrollPane = new JScrollPane(filmListTable);
		filmListPanel.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		filmListPanel.add(panel_2);
		
		JButton button = new JButton("+");
		panel_2.add(button);
		
		JButton button_1 = new JButton("-");
		panel_2.add(button_1);
		
		String[] bestSellerColumns = {"Film Name", "Ordered how many times"};
		Object[][] bestSellerData = {{"Gladiator", "5"}, {"Pirates of the Carribean", "4"}, {"Lord of the Rings", "3"}};
		JTable bestSellerTable = new JTable(new MyTableModel(bestSellerData, bestSellerColumns));
		
		JPanel bestSellerPanel = new JPanel();
		bestSellerPanel.setBorder(new TitledBorder(null, "Best Sellers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		filmsInfoPanel.add(bestSellerPanel);
		bestSellerPanel.setLayout(new BoxLayout(bestSellerPanel, BoxLayout.X_AXIS));
		
		JScrollPane bestSellerScrollPane = new JScrollPane(bestSellerTable);
		bestSellerPanel.add(bestSellerScrollPane);
		
		JPanel ordersPanel = new JPanel();
		ordersPanel.setBorder(new TitledBorder(null, "Orders", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		otherInfoPanel.add(ordersPanel);
		
		String[] ordersTableColumns = {"ID", "Film", "Total Cost"};
		Object[][] ordersTableData = {{"1023341", "Gladiator", "$14"}};
		ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.X_AXIS));
		JTable ordersTable = new JTable(new MyTableModel(ordersTableData, ordersTableColumns));
		
		JScrollPane ordersScrollPane = new JScrollPane(ordersTable);
		ordersPanel.add(ordersScrollPane);
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnDeleteStore = new JButton("Delete Store");
		btnDeleteStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(frame, "Are You Sure? (This action cannot be undone)");
			}
		});
		buttonPanel.add(btnDeleteStore);

	}

}
