package GUI.Store;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUI.MyTableModel;

public class BestSellers extends JPanel
{

	/**
	 * Create the panel.
	 * @param storeNameForObject 
	 */
	public BestSellers(DefaultTableModel tableModel, String storeNameForObject)
	{
		setLayout(new BorderLayout(0, 0));
		setSize(400, 500);
		
		JPanel storeNamePanel = new JPanel();
		add(storeNamePanel, BorderLayout.NORTH);
		
		JLabel lblStoreName = new JLabel("Store Name:");
		storeNamePanel.add(lblStoreName);
		
		JLabel lblNewLabel = new JLabel(storeNameForObject);
		storeNamePanel.add(lblNewLabel);
		
		
		JTable bestSellerTable = new JTable(tableModel);
		bestSellerTable.setRowSelectionAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(bestSellerTable);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);

	}

}
