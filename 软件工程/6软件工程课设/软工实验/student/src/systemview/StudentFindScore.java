package systemview;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
public class StudentFindScore extends JInternalFrame
{
	public Vector<String>tableHeadName=new Vector<String>();
	public Vector<Vector<String>>tableData=new Vector<Vector<String>>();
	public StudentFindScore(String f_id)
	{
		setTitle("ѧ���ɼ�");
		setIconifiable(true);
		setClosable(true);
		setSize(500,400);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		tableHeadName.add("�γ̺�");
		tableHeadName.add("�γ���");
		tableHeadName.add("�ڿ���ʦ");
		tableHeadName.add("�ɼ�");
		DefaultTableModel tableModel=new DefaultTableModel();
		tableModel.setDataVector(tableData, tableHeadName);
		JTable table=new JTable(tableModel);
		getMessage(f_id);
		table.setEnabled(false);
		table.setRowSorter(new TableRowSorter(tableModel));
		table.setRowHeight(20);
		JScrollPane scrollPane=new JScrollPane(table);
		getContentPane().add(scrollPane);
	}
	public void getMessage(String f_id)
	{
		try{
			String sql="select *from db_choose natural join db_course where s_id='"+f_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next())
			{
				Vector<String> cell=new Vector<String>();
				cell.add(set.getString("c_id"));
				cell.add(set.getString("c_name"));
				cell.add(set.getString("c_teacher"));
				cell.add(set.getString("s_score"));
				tableData.add(cell);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
	}

}
