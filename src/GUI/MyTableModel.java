package GUI;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel
{
	public MyTableModel(Object[][] data, Object[] columns)
	{
		super(data, columns);
	}
	
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}