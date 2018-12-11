package library.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import library.vo.*;

public class ResultSetTableModel extends AbstractTableModel{
	
	public ResultSetTableModel(Vector vector,String[] columnNames){
		this.vector=vector;
		this.columnNames=columnNames;
	}

	
	public String getColumnName(int c){
			return columnNames[c];	
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
			return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
			return vector.size();
	}

	@Override
	public Object getValueAt(int r, int c) {
		// TODO Auto-generated method stub
			return ((Vector)vector.get(r)).get(c);
	}
	
	public Class<?> getColumnClass(int c){
		return getValueAt(0,c).getClass();
	}
	public void setValueAt(Object obj,int r,int c){
		((Vector) vector.get(r)).remove(c);
		 ((Vector) vector.get(r)).add(c,obj);
		 this.fireTableCellUpdated(r, c);

	}
	public boolean isCellEditable(int r,int c){
		return c==7;
	}
	private Vector vector;
	private String[] columnNames;
	

}
