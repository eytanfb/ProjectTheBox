package GUI.Customer;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class CustomerPlaceOrderScreen extends JPanel
{
	private JTextField textField;
	private JTable resultTable;
	private JTable filmListTable;

	/**
	 * Create the panel.
	 */
	public CustomerPlaceOrderScreen()
	{
		setLayout(new BorderLayout(0, 10));
		setSize(new Dimension(547, 311));
		
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
		
		JButton btnSearch = new JButton("Search");
		panel.add(btnSearch);
		
		//BorderLayout CENTER
		JPanel infoPanel = new JPanel();
		infoPanel.setVisible(true);
		add(infoPanel, BorderLayout.CENTER);
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Table for search results
		resultTable = new JTable();
		resultTable.setEnabled(false);
		resultTable.setCellSelectionEnabled(true);
		infoPanel.add(resultTable);
		
		//Store Information Panel
		JPanel storeInfoPanel = new JPanel();
		storeInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		storeInfoPanel.setVisible(true);
		infoPanel.add(storeInfoPanel);
		storeInfoPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblStoreName = new JLabel("Store Information");
		lblStoreName.setHorizontalAlignment(SwingConstants.CENTER);
		lblStoreName.setEnabled(false);
		storeInfoPanel.add(lblStoreName);
		
		filmListTable = new JTable();
		filmListTable.setCellSelectionEnabled(true);
		filmListTable.setEnabled(false);
		filmListTable.setVisible(true);
		storeInfoPanel.add(filmListTable);
		
		//Film information panel
		JPanel filmInfoPanel = new JPanel();
		filmInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		filmInfoPanel.setVisible(true);
		infoPanel.add(filmInfoPanel);
		filmInfoPanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JLabel lblFilmName = new JLabel("");
		lblFilmName.setEnabled(false);
		lblFilmName.setHorizontalAlignment(SwingConstants.CENTER);
		filmInfoPanel.add(lblFilmName);
		
		JPanel directorPanel = new JPanel();
		filmInfoPanel.add(directorPanel);
		directorPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDirector = new JLabel("Director:");
		lblDirector.setEnabled(false);
		directorPanel.add(lblDirector);
		
		JLabel directorData = new JLabel("");
		directorData.setEnabled(false);
		directorPanel.add(directorData);
		
		JPanel actorListPanel = new JPanel();
		filmInfoPanel.add(actorListPanel);
		actorListPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblActorList = new JLabel("Actor List:");
		lblActorList.setEnabled(false);
		actorListPanel.add(lblActorList);
		
		JLabel actorListData = new JLabel("");
		actorListData.setEnabled(false);
		actorListPanel.add(actorListData);
		
		JPanel genrePanel = new JPanel();
		filmInfoPanel.add(genrePanel);
		genrePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setEnabled(false);
		genrePanel.add(lblGenre);
		
		JLabel genreData = new JLabel("");
		genreData.setEnabled(false);
		genrePanel.add(genreData);
		
		JPanel placeOrderButtonpanel = new JPanel();
		add(placeOrderButtonpanel, BorderLayout.SOUTH);
		
		JButton btnPlaceOrder = new JButton("Place Order");
		placeOrderButtonpanel.add(btnPlaceOrder);

	}

}
