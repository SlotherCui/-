package systemview;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class Addscore extends JInternalFrame
{
	//public JButton find =new JButton("����");
	public JButton add =new JButton("���");
	//public JLabel input_id=new JLabel("�γ̺�");
	//public JTextField t_input_id=new JTextField(12);
	public Vector<String>tableHeadName=new Vector<String>();
	public Vector<Vector<String>>tableData=new Vector<Vector<String>>();
	public JTable table;
	public int count=0;
	public Addscore(String f_id)
	{
		super();
		setTitle("ѧ���ɼ��Ǽ�");
		getContentPane().setLayout(new BorderLayout());
		setIconifiable(true);
		setClosable(true);
		setSize(500,400);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		JPanel panel=new JPanel();
		//panel.add(input_id);
		//panel.add(t_input_id);
		//panel.add(find);
		//add.setEnabled(true);
		tableHeadName.add("�γ�ID");
		tableHeadName.add("�γ���");
		tableHeadName.add("ѧ��ID");
		tableHeadName.add("ѧ������");
		tableHeadName.add("ѧ���ɼ�");
		getMessage(f_id);
		DefaultTableModel tableModel=new DefaultTableModel();
		tableModel.setDataVector(tableData, tableHeadName);
		table=new JTable(tableModel);
		table.setRowSorter(new TableRowSorter(tableModel));
		table.setRowHeight(20);
		JScrollPane scrollPane=new JScrollPane(table);
		getContentPane().add(panel,BorderLayout.NORTH);
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		getContentPane().add(add,BorderLayout.SOUTH);
		/*find.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(t_input_id.getText().length()!=6)
				{
					JOptionPane.showMessageDialog(null, "�γ̺Ŵ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				getMessage(t_input_id.getText());
				repaint();
				add.setEnabled(true);
			}
		});*/
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int r=addscore();
				if(r!=count)
				{
					JOptionPane.showMessageDialog(null, "��ӳɼ�ʧ�ܣ�","������ʾ",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "��ӳɼ��ɹ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	public void getMessage(String f_id)
	{
		try{
			String sql="select *from db_choose natural join db_student natural join db_course where db_choose.c_id='"+f_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next())
			{
				Vector<String> cell=new Vector<String>();
				cell.add(set.getString("c_id"));
				cell.add(set.getString("c_name"));
				cell.add(set.getString("s_id"));
				cell.add(set.getString("s_name"));
			    cell.add(set.getString("s_score"));
				tableData.add(cell);
				count++;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
	}
	public int addscore()
	{
		int i=0;
		int t=0;
		try{
			for(int n=0;n<count;n++)
			{
				if(table.getValueAt(n, 4).toString().length()==0)
					continue;
				String sql="update db_choose set s_score='"+table.getValueAt(n, 4).toString()+"' where s_id='"+table.getValueAt(n, 2)+"' and c_id='"+table.getValueAt(n, 0)+"'";
				i=Dao.executeUpdate(sql);
				if(i>0)
					t++;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
		return t;
	}

}
