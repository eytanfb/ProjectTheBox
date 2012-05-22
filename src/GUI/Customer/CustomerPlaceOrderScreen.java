package GUI.Customer;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
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
import javax.swing.DropMode;

import GUI.MainWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerPlaceOrderScreen extends JPanel
{
	private JTextField textField;
	private JTable storeInfoTable;
	private JTextField directorData;
	private JTextField genreData;
	
	/**
	 * Create the panel.
	 */
	public CustomerPlaceOrderScreen()
	{
		setLayout(new BorderLayout(0, 10));
		setSize(new Dimension(547, 420));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		//BorderLayout NORTH
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnStore = new JRadioButton("Store");
		panel.add(rdbtnStore);
		
		JRadioButton rdbtnFilm = new JRadioButton("Film");
		panel.add(rdbtnFilm);
		
		ButtonGroup rdbtnGroup = new ButtonGroup();
		rdbtnGroup.add(rdbtnStore);
		rdbtnGroup.add(rdbtnFilm);
		
		JButton btnSearch = new JButton("Search");
		panel.add(btnSearch);
		
		JButton btnEditAccount = new JButton("Edit Account");
		btnEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Driver.MainDriver.frame.setContentPaneFromOutside(new CustomerAccount());
			}
		});
		panel.add(btnEditAccount);
		
		//BorderLayout CENTER
		JPanel infoPanel = new JPanel();
		infoPanel.setVisible(true);
		add(infoPanel, BorderLayout.CENTER);
		
		//Store Information Panel
		JPanel storeInfoPanel = new JPanel();
		storeInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		storeInfoPanel.setVisible(true);
		infoPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		//BorderLayout WEST
		JPanel resultsPanel = new JPanel();
		resultsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		infoPanel.add(resultsPanel);
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
		
		JLabel lblResults = new JLabel("Results");
		resultsPanel.add(lblResults);
		
		String[] columnNamesForResultsTable = {"Film", "Store", "Price"};
		Object[][] data = {};
		JTable resultsTable = new JTable(data, columnNamesForResultsTable);
		
		JScrollPane resultsScrollPane = new JScrollPane(resultsTable);
		lblResults.setLabelFor(resultsScrollPane);
		resultsPanel.add(resultsScrollPane);
		infoPanel.add(storeInfoPanel);
		storeInfoPanel.setLayout(new BoxLayout(storeInfoPanel, BoxLayout.Y_AXIS));
		
		JLabel lblStoreName = new JLabel("Store Information");
		lblStoreName.setHorizontalAlignment(SwingConstants.CENTER);
		storeInfoPanel.add(lblStoreName);

		String[] columnNamesForStoreInfoTable = {"Address", "Phone", "$/day", "Late Fees"};
		Object[][] data2 = {};
		storeInfoTable = new JTable(data2, columnNamesForStoreInfoTable);
		storeInfoTable.setCellSelectionEnabled(true);
		storeInfoTable.setEnabled(false);
		storeInfoTable.setVisible(true);
		
		JScrollPane filmListTableScrollPane = new JScrollPane(storeInfoTable);
		storeInfoPanel.add(filmListTableScrollPane);
		
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
