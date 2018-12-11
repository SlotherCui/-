package systemview;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class ModifyInstitute extends JInternalFrame
{
	public JLabel input_id=new JLabel("请输入学院号");
	public JLabel i_id=new JLabel("学院号");
	public JLabel i_name=new JLabel("学院名称");
	public JTextField t_input_id=new JTextField(12);
	public JTextField t_id=new JTextField(12);
	public JTextField t_name=new JTextField(12);
	public JButton modify=new JButton("修改");
	public JButton reset=new JButton("重置");
	public JButton find=new JButton("查找");
	public ModifyInstitute()
	{
		super();
		setTitle("修改学院信息");
		getContentPane().setLayout(null);
		setIconifiable(true);
		setClosable(true);
		setBounds(100,100,400,260);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		input_id.setBounds(10, 10, 100, 30);
		t_input_id.setBounds(140, 10, 100, 30);
		i_id.setBounds(10, 50, 70, 25);
		t_id.setBounds(140, 50, 100, 30);
		i_name.setBounds(10, 90, 70, 25);
		t_name.setBounds(140, 90, 100, 30);
		find.setBounds(300, 10, 70, 30);
		modify.setBounds(130, 130, 70, 30);
		reset.setBounds(220, 130, 70, 30);
		getContentPane().add(input_id);
		getContentPane().add(t_input_id);
		getContentPane().add(i_id);
		getContentPane().add(t_id);
		getContentPane().add(i_name);
		getContentPane().add(t_name);
		getContentPane().add(find);
		getContentPane().add(modify);
		getContentPane().add(reset);
		modify.setEnabled(false);
		reset.setEnabled(false);
		find.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String f_id=t_input_id.getText();
				if(f_id.length()!=6)
				{
					JOptionPane.showMessageDialog(null, "学院号信息错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					return ;
				}
				getMessage(f_id);
				modify.setEnabled(true);
				reset.setEnabled(true);
			}
		});
		modify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String f_id=t_input_id.getText();
				int r=modifymessage(f_id);
				if(r<=0)
				{
					JOptionPane.showMessageDialog(null, "修改学院信息失败！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "修改学院信息成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				}
			}
		});
	}
	public void getMessage(String f_id)
	{
		try{
			String sql="select *from db_institute where i_id='"+f_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next()){
			t_id.setText(set.getString("i_id"));
			t_name.setText(set.getString("i_name"));}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
	}
	public int modifymessage(String f_id)
	{
		int i=0;
		String id=t_id.getText();
		String name=t_name.getText();
		if(id.length()==0||name.length()==0)
		{
			JOptionPane.showMessageDialog(null, "不能有信息为空！","友情提示",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		if(id.length()!=6)
		{
			JOptionPane.showMessageDialog(null, "学院号错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		try{
			String sql="update db_institute set i_id='"+id+"',"+"i_name='"+name+"'where i_id='"+f_id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public void reset()
	{
		t_id.setText("");
		t_name.setText("");
	}

}
