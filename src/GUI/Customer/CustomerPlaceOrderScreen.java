package GUI.Customer;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;

import Driver.MainDriver;
import GUI.MainWindow;
import GUI.MyTableModel;
import GUI.Store.BestSellers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class CustomerPlaceOrderScreen extends JPanel
{
	private JTextField searchStoreTextField;
	private JTable storeInfoTable;
	private JTextField directorData;
	private JTextField genreData;
	private JTextField storeAddressTextField;
	private JTextField storeRentalTextField;
	private JTextField storeLateTextField;
	private JTextField storeMaxRentTextField;
	private JTextField filmNameField;
	private JTextField actorsTextField;
	private JTextField directorsTextField;
	private JTextField genreTextField;
	final private JTable resultsTable;
	private int storePrice, storeLate, storeHire;
	private String storeNameForObject, filmNameForObject;
	private JTextField daysAvailableTextField;
	private JTextArea actorListTextArea;
	private final JSpinner hiringDaysSpinner;
	
	/**
	 * Create the panel.
	 * @param frame 
	 * @param username 
	 */
	public CustomerPlaceOrderScreen(final MainWindow frame, final String username)
	{
		setLayout(new BorderLayout(0, 10));
		setSize(new Dimension(675, 565));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel searchStorePanel = new JPanel();
		panel.add(searchStorePanel, BorderLayout.WEST);
		searchStorePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		searchStorePanel.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.PAGE_AXIS));
		
		//BorderLayout NORTH
		searchStoreTextField = new JTextField();
		panel_8.add(searchStoreTextField);
		searchStoreTextField.setHorizontalAlignment(SwingConstants.LEFT);
		searchStoreTextField.setColumns(10);
		
		
		
		JPanel panel_7 = new JPanel();
		searchStorePanel.add(panel_7, BorderLayout.SOUTH);
		
		JButton btnEditAccount = new JButton("Edit Account");
		panel_7.add(btnEditAccount);
		btnEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPaneFromOutside(new CustomerAccount(frame, username));
			}
		});
		
		
		JPanel searchFilmPanel = new JPanel();
		panel.add(searchFilmPanel, BorderLayout.CENTER);
		searchFilmPanel.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel lblName = new JLabel("Name:");
		searchFilmPanel.add(lblName);
		
		filmNameField = new JTextField();
		searchFilmPanel.add(filmNameField);
		filmNameField.setColumns(10);
		
		JLabel lblActors = new JLabel("Actors:");
		searchFilmPanel.add(lblActors);
		
		actorsTextField = new JTextField();
		searchFilmPanel.add(actorsTextField);
		actorsTextField.setColumns(10);
		
		JLabel lblDirectors = new JLabel("Directors:");
		searchFilmPanel.add(lblDirectors);
		
		directorsTextField = new JTextField();
		searchFilmPanel.add(directorsTextField);
		directorsTextField.setColumns(10);
		
		JLabel lblGenre_1 = new JLabel("Genre:");
		searchFilmPanel.add(lblGenre_1);
		
		genreTextField = new JTextField();
		searchFilmPanel.add(genreTextField);
		genreTextField.setColumns(10);
		
		JButton btnSearchFilms = new JButton("Search Films");
		btnSearchFilms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//resultsTable.setModel(new MyTableModel());

				String filmSearchStatement = "SELECT film, store, rental FROM filmlist, store, film WHERE filmlist.film like '%" 
			+ filmNameField.getText() + "%' and film.director like '%" + directorsTextField.getText() 
			+ "%'and film.actors like '%" + actorsTextField.getText() + "%'and film.genre like '%" 
			+ genreTextField.getText() + "%' and filmlist.store = store.name and filmlist.film = film.title";
				
				try
				{
					Statement stmt = MainDriver.connection.createStatement();
					ResultSet rset = stmt.executeQuery(filmSearchStatement);
					
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
				    	//System.out.println(rset.getString("film"));
				    	String[] rowData = new String[colCount];
				        for (int i = 1; i <= colCount; i++) {
				          rowData[i - 1] = rset.getString(i);
			        }
			        dataModel.addRow(rowData);
			        if(dataModel != null)
			        	resultsTable.setModel(dataModel);
			        			        
			      }
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		searchFilmPanel.add(btnSearchFilms);
		
		//BorderLayout CENTER
		JPanel infoPanel = new JPanel();
		infoPanel.setVisible(true);
		add(infoPanel, BorderLayout.CENTER);
		
		//Store Information Panel
		JPanel storeInfoPanel = new JPanel();
		storeInfoPanel.setBorder(new TitledBorder(null, "Store Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		storeInfoPanel.setVisible(true);
		infoPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		//BorderLayout WEST
		JPanel resultsPanel = new JPanel();
		resultsPanel.setBorder(new TitledBorder(null, "Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoPanel.add(resultsPanel);
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
		
		final String[] columnNamesForResultsTable = {"Film", "Store", "Price"};
		Object[][] data = {};
		resultsTable = new JTable(new MyTableModel(data, columnNamesForResultsTable));
		resultsTable.setCellSelectionEnabled(true);
		resultsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (resultsTable.getModel().getRowCount() > 0)
				{
					String filmName = (String) resultsTable.getModel().getValueAt(e.getLastIndex(),
							0);
					String storeName = (String) resultsTable.getModel().getValueAt(
							e.getLastIndex(), 1);
					try
					{
						Statement stmt = MainDriver.connection.createStatement();
						ResultSet rset = stmt.executeQuery("select * from film where title = '" +
								filmName + "'");

						while (rset.next())
						{
							directorData.setText(rset.getString("director"));
							actorListTextArea.setText(rset.getString("actors"));
							genreData.setText(rset.getString("genre"));
							filmNameForObject = rset.getString("title");
						}

						rset = stmt.executeQuery("select * from store where name = '" + storeName +
								"'");

						while (rset.next())
						{
							storeAddressTextField.setText(rset.getString("address"));
							storeLateTextField.setText(String.valueOf(rset.getInt("late")));
							storeRentalTextField.setText(String.valueOf(rset.getInt("rental")));
							storePrice = rset.getInt("rental");
							storeLate = rset.getInt("late");
							storeMaxRentTextField.setText(String.valueOf(rset.getInt("maxrent")));
							daysAvailableTextField.setText(String.valueOf(rset.getInt("hire")));
							storeHire = rset.getInt("hire");
							storeNameForObject = rset.getString("name");
						}
						
					} catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JScrollPane resultsScrollPane = new JScrollPane(resultsTable);
		resultsPanel.add(resultsScrollPane);
		infoPanel.add(storeInfoPanel);
		storeInfoPanel.setLayout(new GridLayout(6, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		storeInfoPanel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.PAGE_AXIS));
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(lblAddress);
		
		storeAddressTextField = new JTextField();
		storeAddressTextField.setEditable(false);
		panel_2.add(storeAddressTextField);
		storeAddressTextField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		storeInfoPanel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JLabel lblMaxRents = new JLabel("Max Rents");
		panel_3.add(lblMaxRents);
		
		storeMaxRentTextField = new JTextField();
		storeMaxRentTextField.setEditable(false);
		panel_3.add(storeMaxRentTextField);
		storeMaxRentTextField.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		storeInfoPanel.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));
		
		JLabel lblDaysAvailable = new JLabel("Days Available");
		panel_10.add(lblDaysAvailable);
		
		daysAvailableTextField = new JTextField();
		daysAvailableTextField.setEditable(false);
		panel_10.add(daysAvailableTextField);
		daysAvailableTextField.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		storeInfoPanel.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_9.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel lblday = new JLabel("$/day");
		panel_4.add(lblday);
		
		storeRentalTextField = new JTextField();
		storeRentalTextField.setEditable(false);
		panel_4.add(storeRentalTextField);
		storeRentalTextField.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_9.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JLabel lblLateFees = new JLabel("Late Fees ($/day)");
		panel_5.add(lblLateFees);
		
		storeLateTextField = new JTextField();
		storeLateTextField.setEditable(false);
		panel_5.add(storeLateTextField);
		storeLateTextField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		storeInfoPanel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JButton btnAddToFavorites = new JButton("Add to Favorites");
		btnAddToFavorites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newFavorite = "INSERT INTO favourite (username, store) VALUES ('" + username + "', '" + storeNameForObject + "')";
				
				try
				{
					Statement stmt = MainDriver.connection.createStatement();
					stmt.executeQuery(newFavorite);
					
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		panel_1.add(btnAddToFavorites);
		
		JButton btnShowBestSellers = new JButton("Show Best Sellers");
		btnShowBestSellers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bestSellerStatement = "SELECT film, count(film) as x FROM orderlist GROUP BY store, film HAVING store = '" + storeNameForObject + "' ORDER BY x DESC";
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
			      }
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(frame, new BestSellers(dataModel, storeNameForObject));
			}
		});
		panel_1.add(btnShowBestSellers);
		
		//Film information panel
		JPanel filmInfoPanel = new JPanel();
		filmInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		filmInfoPanel.setVisible(true);
		infoPanel.add(filmInfoPanel);
		filmInfoPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel allPanel = new JPanel();
		allPanel.setBorder(new TitledBorder(null, "Film Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		filmInfoPanel.add(allPanel, BorderLayout.CENTER);
		allPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFilmName = new JLabel("Film Name");
		lblFilmName.setHorizontalAlignment(SwingConstants.LEFT);
		allPanel.add(lblFilmName, BorderLayout.NORTH);
		
		JPanel directorPanel = new JPanel();
		allPanel.add(directorPanel, BorderLayout.NORTH);
		directorPanel.setLayout(new BoxLayout(directorPanel, BoxLayout.Y_AXIS));
		
		JLabel lblDirector = new JLabel("Director:");
		directorPanel.add(lblDirector);
		
		directorData = new JTextField();
		directorData.setEditable(false);
		directorData.setColumns(10);
		
		JScrollPane directorScrollPane = new JScrollPane(directorData);
		directorPanel.add(directorScrollPane);
		
		JPanel actorListPanel = new JPanel();
		allPanel.add(actorListPanel, BorderLayout.CENTER);
		actorListPanel.setLayout(new BoxLayout(actorListPanel, BoxLayout.Y_AXIS));
		
		JLabel lblActorList = new JLabel("Actor List:");
		actorListPanel.add(lblActorList);
		
		actorListTextArea = new JTextArea();
		actorListTextArea.setEditable(false);
		actorListTextArea.setLineWrap(true);
		
		JScrollPane actorListScrollPane = new JScrollPane(actorListTextArea);
		actorListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		actorListPanel.add(actorListScrollPane);
		
		
		JPanel genrePanel = new JPanel();
		allPanel.add(genrePanel, BorderLayout.SOUTH);
		genrePanel.setLayout(new BoxLayout(genrePanel, BoxLayout.Y_AXIS));
		
		JLabel lblGenre = new JLabel("Genre:");
		genrePanel.add(lblGenre);
		
		genreData = new JTextField();
		genreData.setEditable(false);
		genrePanel.add(genreData);
		genreData.setColumns(10);
		
		final JLabel totalCostData = new JLabel();
		JPanel placeOrderButtonpanel = new JPanel();
		add(placeOrderButtonpanel, BorderLayout.SOUTH);
		placeOrderButtonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String placeOrderStatement = "INSERT INTO orderlist (store, customer, film, cost, begin, end) VALUES ('" 
				+ storeNameForObject + "', '" 
						+ username + "', '" 
				+ filmNameForObject + "', " 
						+ Integer.parseInt(totalCostData.getText())
						+ ", current_timestamp, current_timestamp + " 
						+ storeHire + ")";
				
				try
				{
					Statement stmt = MainDriver.connection.createStatement();
					stmt.executeQuery(placeOrderStatement);
					
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				clearEverything();
				btnPlaceOrder.setEnabled(false);
			}
		});
		
		JLabel lblHiringDays = new JLabel("Hiring Days");
		placeOrderButtonpanel.add(lblHiringDays);
		
		hiringDaysSpinner = new JSpinner(new SpinnerNumberModel(0,0,60,1));
		hiringDaysSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(((SpinnerNumberModel) hiringDaysSpinner.getModel()).getNumber().intValue() < 0)
					hiringDaysSpinner.getModel().setValue((int)0);
				else
				{
					btnPlaceOrder.setEnabled(true);
					int extraDays = 0;
					int numOfDays = ((SpinnerNumberModel) hiringDaysSpinner.getModel()).getNumber().intValue();
					int totalCost = 0;
					if(numOfDays > storeHire)
					{
						extraDays = numOfDays - storeHire;
						totalCost = (storeHire*storePrice) + (extraDays*storeLate);
					}
					else
					{
						totalCost = (numOfDays*storePrice);
					}
					totalCostData.setText(String.valueOf(totalCost));	
				}
				
			}
		});
		lblHiringDays.setLabelFor(hiringDaysSpinner);
		placeOrderButtonpanel.add(hiringDaysSpinner);
		
		JLabel lblTotalCost = new JLabel("Total Cost: $");
		placeOrderButtonpanel.add(lblTotalCost);
		
		
		lblTotalCost.setLabelFor(totalCostData);
		placeOrderButtonpanel.add(totalCostData);
		
		
		btnPlaceOrder.setEnabled(false);
		placeOrderButtonpanel.add(btnPlaceOrder);
		
		JButton btnSearch = new JButton("Search Store");
		panel_8.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//resultsTable.setModel(new MyTableModel());
				String searchStoreStatement = "SELECT film, store, rental FROM filmlist, store WHERE filmlist.store like '%" + searchStoreTextField.getText() + "%' and filmlist.store = store.name";
				
				try
				{
					Statement stmt = MainDriver.connection.createStatement();
					ResultSet rset = stmt.executeQuery(searchStoreStatement);
					
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
			        	resultsTable.setModel(dataModel);
			        }
			      }
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	
	private void clearEverything()
	{
		searchStoreTextField.setText("");
		filmNameField.setText("");
		actorsTextField.setText("");
		directorsTextField.setText("");
		genreTextField.setText("");
		hiringDaysSpinner.getModel().setValue(0);
	}
}
