package systemview;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
public class StudentChooseLesson extends JInternalFrame
{
	public Vector<String>tableHeadName=new Vector<String>();
	public Vector<Vector<String>>tableData=new Vector<Vector<String>>();
	public JLabel input=new JLabel("请输入课程号");
	public JButton add=new JButton("确定");
	public JButton delete =new JButton("删除");
	public JTextField t_id=new JTextField(12);
	public StudentChooseLesson(String s_id)
	{
		setTitle("学生选课");
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new BorderLayout());
		setBounds(0,0,600,400);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		tableHeadName.add("课程号");
		tableHeadName.add("课程名");
		tableHeadName.add("授课老师");
		tableHeadName.add("上课时间");
		tableHeadName.add("");
		getlesson(s_id);
		DefaultTableModel tableModel=new DefaultTableModel();
		tableModel.setDataVector(tableData, tableHeadName);
		JTable table=new JTable(tableModel);
		table.getColumnModel().getColumn(4).setCellRenderer(new TableCellRenderer(){
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSleected,boolean hasFocus,int row,int column){
				JCheckBox ck=new JCheckBox();
				ck.setSelected(isSleected);
				ck.setHorizontalAlignment((int)0.5f);
				return ck;
			}
		});
		table.setEnabled(true);
		table.setRowSorter(new TableRowSorter(tableModel));
		table.setRowHeight(20);
		JScrollPane scrollPane=new JScrollPane(table);
		JPanel panel=new JPanel();
		panel.add(input);
		panel.add(t_id);
		panel.add(add);
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		getContentPane().add(panel,BorderLayout.NORTH);
		getContentPane().add(delete,BorderLayout.SOUTH);
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String c_id=t_id.getText();
				if(c_id.length()!=6)
				{
					JOptionPane.showMessageDialog(null, "课程号错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				tableModel.addRow(addlesson(c_id,s_id));
				int r=chooselesson(c_id,s_id);
				if(r<=0)
				{
					JOptionPane.showMessageDialog(null, "选课失败！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "选课成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int row=table.getSelectedRow();
				String c_id=table.getValueAt(row, 0)+"";
				int r=deletelesson(c_id,s_id);
				if(r<=0)
				{
					JOptionPane.showMessageDialog(null, "删除失败！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "删除成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					tableModel.removeRow(row);
				}
			}
		});
	}
	public Vector<String> addlesson(String c_id,String s_id)
	{
		Vector<String> cell=new Vector<String>();
		try{
			String sql="select * from db_course where c_id='"+c_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next()){
			cell.add(set.getString("c_id"));
			cell.add(set.getString("c_name"));
			cell.add(set.getString("c_teacher"));
			cell.add(set.getString("c_time"));}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
		return cell;
	}
	public int chooselesson(String c_id,String s_id)
	{
		int i=0;
		try{
			String sqls="insert into db_choose(s_id,c_id,s_score) values('"+s_id+"','"+c_id+"',"+"null"+")";
			i=Dao.executeUpdate(sqls);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public int deletelesson(String c_id,String s_id)
	{
		int i=0;
		try{
			String sql="delete from db_choose where c_id='"+c_id+"'and s_id='"+s_id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	public void getlesson(String s_id)
	{
		try{
			String sql="select * from db_course natural join db_choose where s_id='"+s_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next()){
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
		Dao.close();
	}

}
