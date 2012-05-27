package GUI;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel
{
	public MyTableModel()
	{
		super();
	}
	
	public MyTableModel(Object[][] data, Object[] columns)
	{
		super(data, columns);
	}
	
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}