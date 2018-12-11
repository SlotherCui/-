package systemview;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
public class ShowLesson extends JInternalFrame
{
	public Vector<String>tableHeadName=new Vector<String>();
	public Vector<Vector<String>>tableData=new Vector<Vector<String>>();
	public ShowLesson()
	{
		super();
		setTitle("�γ̲�ѯ");
		setIconifiable(true);
		setClosable(true);
		setSize(500,400);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		tableHeadName.add("�γ̺�");
		tableHeadName.add("�γ���");
		tableHeadName.add("�ڿ���ʦ");
		tableHeadName.add("�Ͽ�ʱ��");
		try{
			String sql="select *from db_course ";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next())
			{
				Vector<String> cell=new Vector<String>();
				cell.add(set.getString("c_id"));
				cell.add(set.getString("c_name"));
				cell.add(set.getString("c_teacher"));
				cell.add(set.getString("c_time"));
				tableData.add(cell);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DefaultTableModel tableModel=new DefaultTableModel();
		tableModel.setDataVector(tableData, tableHeadName);
		JTable table=new JTable(tableModel);
		table.setEnabled(false);
		table.setRowSorter(new TableRowSorter(tableModel));
		table.setRowHeight(20);
		JScrollPane scrollPane=new JScrollPane(table);
		getContentPane().add(scrollPane);
	}
	public ShowLesson(String c_id)
	{
		setTitle("�γ̲�ѯ");
		setIconifiable(true);
		setClosable(true);
		setSize(500,400);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		tableHeadName.add("�γ̺�");
		tableHeadName.add("�γ���");
		tableHeadName.add("�ڿ���ʦ");
		tableHeadName.add("�Ͽ�ʱ��");
		try{
			String sql="select *from db_course where c_id='"+c_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next())
			{
				Vector<String> cell=new Vector<String>();
				cell.add(set.getString("c_id"));
				cell.add(set.getString("c_name"));
				cell.add(set.getString("c_teacher"));
				cell.add(set.getString("c_time"));
				tableData.add(cell);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DefaultTableModel tableModel=new DefaultTableModel();
		tableModel.setDataVector(tableData, tableHeadName);
		JTable table=new JTable(tableModel);
		table.setEnabled(false);
		table.setRowSorter(new TableRowSorter(tableModel));
		table.setRowHeight(20);
		JScrollPane scrollPane=new JScrollPane(table);
		getContentPane().add(scrollPane);
	}
	public ShowLesson(String c_teacher,int i)
	{
		setTitle("�γ̲�ѯ");
		setIconifiable(true);
		setClosable(true);
		setSize(500,400);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		tableHeadName.add("�γ̺�");
		tableHeadName.add("�γ���");
		tableHeadName.add("�ڿ���ʦ");
		tableHeadName.add("�Ͽ�ʱ��");
		if(i==0){
		try{
			String sql="select *from db_course where c_name='"+c_teacher+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next())
			{
				Vector<String> cell=new Vector<String>();
				cell.add(set.getString("c_id"));
				cell.add(set.getString("c_name"));
				cell.add(set.getString("c_teacher"));
				cell.add(set.getString("c_time"));
				tableData.add(cell);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}}else{
			try{
				String sql="select *from db_course where s_id='"+c_teacher+"'";
				ResultSet set=Dao.executeQuery(sql);
				while(set.next())
				{
					Vector<String> cell=new Vector<String>();
					cell.add(set.getString("c_id"));
					cell.add(set.getString("c_name"));
					cell.add(set.getString("c_teacher"));
					cell.add(set.getString("c_time"));
					tableData.add(cell);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		DefaultTableModel tableModel=new DefaultTableModel();
		tableModel.setDataVector(tableData, tableHeadName);
		JTable table=new JTable(tableModel);
		table.setEnabled(false);
		table.setRowSorter(new TableRowSorter(tableModel));
		table.setRowHeight(20);
		JScrollPane scrollPane=new JScrollPane(table);
		getContentPane().add(scrollPane);
	}
	public ShowLesson(String c_teacher,String c_name)
	{
		setTitle("�γ̲�ѯ");
		setIconifiable(true);
		setClosable(true);
		setSize(500,400);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		tableHeadName.add("�γ̺�");
		tableHeadName.add("�γ���");
		tableHeadName.add("�ڿ���ʦ");
		tableHeadName.add("�Ͽ�ʱ��");
		try{
			String sql="select *from db_course where c_name='"+c_name+"'and c_teacher='"+c_teacher+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next())
			{
				Vector<String> cell=new Vector<String>();
				cell.add(set.getString("c_id"));
				cell.add(set.getString("c_name"));
				cell.add(set.getString("c_teacher"));
				cell.add(set.getString("c_time"));
				tableData.add(cell);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DefaultTableModel tableModel=new DefaultTableModel();
		tableModel.setDataVector(tableData, tableHeadName);
		JTable table=new JTable(tableModel);
		table.setEnabled(false);
		table.setRowSorter(new TableRowSorter(tableModel));
		table.setRowHeight(20);
		JScrollPane scrollPane=new JScrollPane(table);
		getContentPane().add(scrollPane);
	}

}
