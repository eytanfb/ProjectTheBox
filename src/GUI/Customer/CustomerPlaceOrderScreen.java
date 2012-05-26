package GUI.Customer;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import GUI.MainWindow;
import GUI.MyTableModel;
import GUI.Store.BestSellers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerPlaceOrderScreen extends JPanel
{
	private JTextField textField;
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
	
	/**
	 * Create the panel.
	 * @param frame 
	 */
	public CustomerPlaceOrderScreen(final MainWindow frame)
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
		textField = new JTextField();
		panel_8.add(textField);
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search Store");
		panel_8.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JPanel panel_7 = new JPanel();
		searchStorePanel.add(panel_7, BorderLayout.SOUTH);
		
		JButton btnEditAccount = new JButton("Edit Account");
		panel_7.add(btnEditAccount);
		btnEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPaneFromOutside(new CustomerAccount(frame));
			}
		});
		
		ButtonGroup rdbtnGroup = new ButtonGroup();
		
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
		
		String[] columnNamesForResultsTable = {"Film", "Store", "Price"};
		Object[][] data = {};
		JTable resultsTable = new JTable(new MyTableModel(data, columnNamesForResultsTable));
		resultsTable.setCellSelectionEnabled(true);
		
		JScrollPane resultsScrollPane = new JScrollPane(resultsTable);
		resultsPanel.add(resultsScrollPane);
		infoPanel.add(storeInfoPanel);
		storeInfoPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
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
		
		JPanel panel_4 = new JPanel();
		storeInfoPanel.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel lblday = new JLabel("$/day");
		panel_4.add(lblday);
		
		storeRentalTextField = new JTextField();
		storeRentalTextField.setEditable(false);
		panel_4.add(storeRentalTextField);
		storeRentalTextField.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		storeInfoPanel.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JLabel lblLateFees = new JLabel("Late Fees ($/day)");
		panel_5.add(lblLateFees);
		
		storeLateTextField = new JTextField();
		storeLateTextField.setEditable(false);
		panel_5.add(storeLateTextField);
		storeLateTextField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		storeInfoPanel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JLabel lblMaxRents = new JLabel("Max Rents");
		panel_3.add(lblMaxRents);
		
		storeMaxRentTextField = new JTextField();
		storeMaxRentTextField.setEditable(false);
		panel_3.add(storeMaxRentTextField);
		storeMaxRentTextField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		storeInfoPanel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JButton btnAddToFavorites = new JButton("Add to Favorites");
		panel_1.add(btnAddToFavorites);
		
		JButton btnShowBestSellers = new JButton("Show Best Sellers");
		btnShowBestSellers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, new BestSellers());
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
		directorData.setText("Scott Ridley");
		directorData.setEditable(false);
		directorData.setColumns(10);
		
		JScrollPane directorScrollPane = new JScrollPane(directorData);
		directorPanel.add(directorScrollPane);
		
		JPanel actorListPanel = new JPanel();
		allPanel.add(actorListPanel, BorderLayout.CENTER);
		actorListPanel.setLayout(new BoxLayout(actorListPanel, BoxLayout.Y_AXIS));
		
		JLabel lblActorList = new JLabel("Actor List:");
		actorListPanel.add(lblActorList);
		
		JTextArea actorListTextArea = new JTextArea();
		actorListTextArea.setEditable(false);
		actorListTextArea.setLineWrap(true);
		actorListTextArea.setText("Russel Crowe, Joaquin Phoenix");
		
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
		
		JPanel placeOrderButtonpanel = new JPanel();
		add(placeOrderButtonpanel, BorderLayout.SOUTH);
		placeOrderButtonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblHiringDays = new JLabel("Hiring Days");
		placeOrderButtonpanel.add(lblHiringDays);
		
		JSpinner hiringDaysSpinner = new JSpinner();
		lblHiringDays.setLabelFor(hiringDaysSpinner);
		placeOrderButtonpanel.add(hiringDaysSpinner);
		
		JLabel lblTotalCost = new JLabel("Total Cost:");
		placeOrderButtonpanel.add(lblTotalCost);
		
		JLabel totalCostData = new JLabel("$");
		lblTotalCost.setLabelFor(totalCostData);
		placeOrderButtonpanel.add(totalCostData);
		
		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.setEnabled(false);
		placeOrderButtonpanel.add(btnPlaceOrder);

	}
}
