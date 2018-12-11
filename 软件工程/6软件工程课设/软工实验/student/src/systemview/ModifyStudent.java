package systemview;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
public class ModifyStudent extends JInternalFrame
{
	public JButton SModify=new JButton("确认修改");
	public JButton reset=new JButton("重置");
	public JLabel s_id=new JLabel("学号");
	public JLabel s_name=new JLabel("姓名");
	public JLabel s_sex=new JLabel("性别");
	public JLabel s_age=new JLabel("年龄");
	public JLabel s_password=new JLabel("密码");
	public JLabel s_institute=new JLabel("学院");
	public JLabel s_major=new JLabel("专业");
	public JLabel s_RP=new JLabel("奖惩情况");
	public JTextField t_id=new JTextField(12);
	public JTextField t_name=new JTextField(12);
	public JTextField t_sex=new JTextField(12);
	public JTextField t_age=new JTextField(12);
	public JTextField t_password=new JTextField(12);
	public JTextField t_institute=new JTextField(12);
	public JTextField t_major=new JTextField(12);
	public JTextArea t_RP=new JTextArea(4,12);
	public ModifyStudent(String f_id)
	{
		setTitle("修改学生信息");
		getContentPane().setLayout(null);
		setIconifiable(true);
		setClosable(true);
		setBounds(100,50,500,400);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		getMessage(f_id);
		s_id.setBounds(10, 10, 70, 25);
		s_name.setBounds(230, 10, 70, 25);
		s_age.setBounds(10, 50, 70, 25);
		s_sex.setBounds(230, 50, 70, 25);
		s_institute.setBounds(10, 90, 70, 25);
		s_major.setBounds(230, 90, 70, 25);
		s_password.setBounds(10, 130, 70, 25);
		s_RP.setBounds(10, 170, 70, 25);
		t_id.setBounds(110, 10, 100, 25);
		t_name.setBounds(330, 10, 100, 25);
		t_age.setBounds(110, 50, 100, 25);
		t_sex.setBounds(330, 50, 100, 25);
		t_institute.setBounds(110, 90, 100, 25);
		t_major.setBounds(330, 90, 100, 25);
		t_password.setBounds(110, 130, 100, 25);
		t_RP.setBounds(110, 170, 300, 100);
		SModify.setBounds(150, 320, 70, 30);
		reset.setBounds(250,320,70,30);
		getContentPane().add(s_id);
		getContentPane().add(t_id);
		getContentPane().add(s_name);
		getContentPane().add(t_name);
		getContentPane().add(s_age);
		getContentPane().add(t_age);
		getContentPane().add(s_sex);
		getContentPane().add(t_sex);
		getContentPane().add(s_institute);
		getContentPane().add(t_institute);
		getContentPane().add(s_major);
		getContentPane().add(t_major);
		getContentPane().add(s_password);
		getContentPane().add(t_password);
		getContentPane().add(s_RP);
		getContentPane().add(t_RP);
		getContentPane().add(SModify);
		getContentPane().add(reset);
		SModify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int r=modifyMessage(f_id);
				if(r<=0)
				{
					JOptionPane.showMessageDialog(null, "修改学生信息失败！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "修改学生信息成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);;
				}
			}
		});
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				reset();
			}
		});
	}
	public void getMessage(String f_id)
	{
		try{
			String sql="select * from db_student where s_id='"+f_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next()){
			t_id.setText(set.getString("s_id"));
			t_name.setText(set.getString("s_name"));
			t_sex.setText(set.getString("s_sex"));
			t_password.setText(set.getString("s_password"));
			t_age.setText(String.valueOf(set.getInt("s_age")));
			t_institute.setText(set.getString("s_institute"));
			t_major.setText(set.getString("s_major"));
			t_RP.setText(set.getString("s_RP"));}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
	}
	public int modifyMessage(String f_id)
	{
		int i=0;
		String id=t_id.getText();
		String name=t_name.getText();
		String password=t_password.getText();
		String sex=t_sex.getText();
	    String institute=t_institute.getText();
	    String major=t_major.getText();
	    int age=Integer.parseInt(t_age.getText());
	    String RP=t_RP.getText();
		if(id.length()==0||name.length()==0||sex.length()==0||password.length()==0||institute.length()==0||major.length()==0||t_age.getText().length()==0)
		{
			JOptionPane.showMessageDialog(null, "不能有信息为空！","友情提示",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		if(id.length()!=12)
		{
			JOptionPane.showMessageDialog(null, "学号输入错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		try{
			String sql="update db_student set s_id='"+id+"',"+"s_name='"+name+"',"+"s_age="+age+","+"s_sex='"+sex+"',"+"s_institute='"+institute+"',"+"s_major='"+major+"',"+"s_password='"+password+"',"+"s_RP='"+RP+"'where s_id='"+f_id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	public void reset()
	{
		t_id.setText("");
		t_name.setText("");
		t_age.setText("");
		t_sex.setText("");
		t_RP.setText("");
		t_institute.setText("");
		t_major.setText("");
		t_password.setText("");
	}

}
