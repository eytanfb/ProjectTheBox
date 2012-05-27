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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import Driver.MainDriver;
import GUI.MainWindow;
import GUI.MyTableModel;
import GUI.Owner.OwnerAccount;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Store extends JPanel
{
	private JTextField storeNameTextField;
	private JTextField storeAddressTextField;
	private String filmName;

	/**
	 * Create the panel.
	 * @param mainWindow 
	 */
	public Store(final MainWindow frame, final String username, final String storeName)
	{
		setLayout(new BorderLayout(0, 0));
		setSize(838, 784);
		
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
		
		final JSpinner rentalSpinner = new JSpinner();
		storeInfoPanel.add(rentalSpinner);
		
		JLabel lblLateFee = new JLabel("Late Fee ($/day):");
		storeInfoPanel.add(lblLateFee);
		
		final JSpinner latePerDaySpinner = new JSpinner();
		storeInfoPanel.add(latePerDaySpinner);
		
		JLabel lblUserRentAllowance = new JLabel("User Rent Allowance:");
		storeInfoPanel.add(lblUserRentAllowance);
		
		final JSpinner allowanceSpinner = new JSpinner();
		storeInfoPanel.add(allowanceSpinner);
		
		JLabel lblHiringPeriod = new JLabel("Hiring Period:");
		storeInfoPanel.add(lblHiringPeriod);
		
		final JSpinner hiringPeriodSpinner = new JSpinner();
		storeInfoPanel.add(hiringPeriodSpinner);
		
		JPanel panel = new JPanel();
		storeInfoPanel.add(panel);
		
		JButton btnBackToAccount = new JButton("Back to Account");
		btnBackToAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPaneFromOutside(new OwnerAccount(frame, username));
			}
		});
		panel.add(btnBackToAccount);
		
		JPanel panel_1 = new JPanel();
		storeInfoPanel.add(panel_1);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String change = "UPDATE store SET address = '" + storeAddressTextField.getText() 
						+ "', rental = " + Integer.parseInt(rentalSpinner.getModel().getValue().toString()) 
						+ ", late = " + Integer.parseInt(latePerDaySpinner.getModel().getValue().toString()) 
						+ ", maxrent = " + Integer.parseInt(allowanceSpinner.getModel().getValue().toString()) 
						+ ", hire = " + Integer.parseInt(hiringPeriodSpinner.getModel().getValue().toString()) 
						+ " WHERE name = '" + storeName + "'";
				
				try
				{
					Statement stmt = MainDriver.connection.createStatement();
					stmt.executeQuery(change);
					
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
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
		
		
		String[] columns = {"Film"};
		Object[][] data = {};
		filmListPanel.setLayout(new BoxLayout(filmListPanel, BoxLayout.X_AXIS));
		final JTable filmListTable = new JTable(new MyTableModel(data, columns));
		
		JScrollPane scrollPane = new JScrollPane(filmListTable);
		filmListPanel.add(scrollPane);

		final String filmlist = "SELECT film FROM filmlist WHERE store = '" + storeName + "' ORDER BY film ASC";
		String[] possibleColumns = {"Film"};
		Object[][] possibleData = {};
		final JTable possibleFilms = new JTable(new MyTableModel(possibleData, possibleColumns));
		JScrollPane scrollPane_1 = new JScrollPane(possibleFilms);
		possibleFilms.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			
			public void valueChanged(ListSelectionEvent e)
			{
				filmName = (String) possibleFilms.getModel().getValueAt(e.getLastIndex(),
						0);
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		filmListPanel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String add = "INSERT INTO filmlist (store, film) VALUES ('" + storeName + "', '" + possibleFilms.getModel().getValueAt(possibleFilms.getSelectedRow(), 0).toString() + "')";
				insertFilm(filmListTable, filmlist, possibleFilms, add);
			}

			
		});
		panel_2.add(button);
		
		JButton button_1 = new JButton("-");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String remove = "DELETE FROM filmlist WHERE film = '" + filmListTable.getModel().getValueAt(filmListTable.getSelectedRow(), 0) + "' and store = '" + storeName + "'";
				deleteFilm(filmListTable, filmlist, possibleFilms, remove);
			}
		});
		panel_2.add(button_1);
		
		String[] bestSellerColumns = {"Film Name", "Ordered how many times"};
		Object[][] bestSellerData = {};
		filmListPanel.add(scrollPane_1);
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
		Object[][] ordersTableData = {};
		ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.X_AXIS));
		JTable ordersTable = new JTable(new MyTableModel(ordersTableData, ordersTableColumns));
		
		JScrollPane ordersScrollPane = new JScrollPane(ordersTable);
		ordersPanel.add(ordersScrollPane);
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnDeleteStore = new JButton("Delete Store");
		btnDeleteStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showConfirmDialog(frame, "Are You Sure? (This action cannot be undone)");
				String deleteStore = "DELETE FROM store WHERE name = '" + storeName + "'";
				
				Statement stmt;
				try
				{
					stmt = MainDriver.connection.createStatement();
					stmt.executeQuery(deleteStore);
					frame.setContentPaneFromOutside(new OwnerAccount(frame, username));
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		buttonPanel.add(btnDeleteStore);
		
		showStoreInfo(storeName, rentalSpinner, latePerDaySpinner, allowanceSpinner,
				hiringPeriodSpinner);
		
		String kolay = "SELECT title FROM film ORDER BY title ASC";
		
		showPossibleFilms(kolay, possibleFilms);
		
		
		showPossibleFilms(filmlist, filmListTable);
		
		String orderlar = "SELECT id, film, cost FROM orderlist	WHERE store = '" + storeName + "' ORDER BY id DESC";
		
		showPossibleFilms(orderlar, ordersTable);
		
		String bestSellerStatement = "SELECT film, count(film) as x FROM orderlist GROUP BY store, film HAVING store = '" + storeName + "' ORDER BY x DESC";
		MyTableModel dataModel = null;
		try
		{
			Statement stmt = MainDriver.connection.createStatement();
			ResultSet rset = stmt.executeQuery(bestSellerStatement);
			
			dataModel = new MyTableModel();
			ResultSetMetaData mdata = rset.getMetaData();
		    int colCount = mdata.getColumnCount();
		    String[] colNames = new String[colCount];
		    for (int i = 1; i <= colCount; i++) {
		    	colNames[i - 1] = mdata.getColumnName(i);
		    }
		    dataModel.setColumnIdentifiers(colNames);
		 
		    //now populate the data
		    while (rset.next()) 
		    {
		    	String[] rowData = new String[colCount];
		        for (int i = 1; i <= colCount; i++) {
		          rowData[i - 1] = rset.getString(i);
	        }
	        dataModel.addRow(rowData);
	        bestSellerTable.setModel(dataModel);
	      }
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	}

	private void showPossibleFilms(String sql, JTable possibleFilms)
	{
		
		try
		{
			Statement stmt = MainDriver.connection.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			
			MyTableModel dataModel = new MyTableModel();
			ResultSetMetaData mdata = rset.getMetaData();
		    int colCount = mdata.getColumnCount();
		    String[] colNames = new String[colCount];
		    for (int i = 1; i <= colCount; i++) {
		    	colNames[i - 1] = mdata.getColumnName(i);
		    }
		    dataModel.setColumnIdentifiers(colNames);
		 
		    //now populate the data
		    while (rset.next()) 
		    {
		    	String[] rowData = new String[colCount];
		        for (int i = 1; i <= colCount; i++) {
		          rowData[i - 1] = rset.getString(i);
	        }
	        dataModel.addRow(rowData);
	        if(dataModel != null)
	        {
	        	possibleFilms.setModel(dataModel);
	        }
		    }
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}

	private void showStoreInfo(final String storeName, JSpinner rentalSpinner,
			JSpinner latePerDaySpinner, JSpinner allowanceSpinner, JSpinner hiringPeriodSpinner)
	{
		try
		{
			Statement stmt = MainDriver.connection.createStatement();
			ResultSet rset = stmt.executeQuery("select * from store where name = '" + storeName + "'");
			
			while(rset.next())
			{
				storeNameTextField.setText(rset.getString("name"));
				storeAddressTextField.setText(rset.getString("address"));
				rentalSpinner.setValue(rset.getInt("rental"));
				latePerDaySpinner.setValue(rset.getInt("late"));
				allowanceSpinner.setValue(rset.getInt("maxrent"));
				hiringPeriodSpinner.setValue(rset.getInt("hire"));
			}
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void insertFilm(final JTable filmListTable, final String filmlist,
			final JTable possibleFilms, String add)
	{
		if(possibleFilms.getSelectedRowCount() > 0)
		{
	
		try
		{
			Statement stmt = MainDriver.connection.createStatement();
			stmt.executeQuery(add);
			
			showPossibleFilms(filmlist, filmListTable);
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}
	
	private void deleteFilm(final JTable filmListTable, final String filmlist,
			final JTable possibleFilms, String add)
	{
		if(filmListTable.getSelectedRowCount() > 0)
		{
	
		try
		{
			Statement stmt = MainDriver.connection.createStatement();
			stmt.executeQuery(add);
			
			showPossibleFilms(filmlist, filmListTable);
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}

}
