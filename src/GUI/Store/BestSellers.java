package GUI.Store;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import GUI.MyTableModel;

public class BestSellers extends JPanel
{

	/**
	 * Create the panel.
	 */
	public BestSellers()
	{
		setLayout(new BorderLayout(0, 0));
		
		JPanel storeNamePanel = new JPanel();
		add(storeNamePanel, BorderLayout.NORTH);
		
		JLabel lblStoreName = new JLabel("Store Name:");
		storeNamePanel.add(lblStoreName);
		
		JLabel lblNewLabel = new JLabel("Murat's Shop 1");
		storeNamePanel.add(lblNewLabel);
		
		String[] columns = {"Film Name", "Ordered how many times"};
		Object[][] data = {{"Gladiator", "5"}, {"Pirates of the Carribean", "4"}, {"Lord of the Rings", "3"}};
		
		JTable bestSellerTable = new JTable(new MyTableModel(data, columns));
		bestSellerTable.setRowSelectionAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(bestSellerTable);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);

	}

}
