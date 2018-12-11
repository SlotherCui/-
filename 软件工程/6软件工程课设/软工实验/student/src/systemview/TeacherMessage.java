package systemview;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
public class TeacherMessage extends JInternalFrame
{
	public JLabel f_id=new JLabel("教师工号:");
	public JLabel f_name=new JLabel("教师姓名:");
	public JLabel f_sex=new JLabel("性别:");
	public JLabel f_age=new JLabel("年龄:");
	public JLabel f_institute=new JLabel("学院:");
	public JLabel t_id=new JLabel();
	public JLabel t_name=new JLabel();
	public JLabel t_sex=new JLabel();
	public JLabel t_age=new JLabel();
	public JLabel t_institute=new JLabel();
	public TeacherMessage(String s_id)
	{
		setTitle("学生信息");
		setIconifiable(true);
		setClosable(true);
		setBounds(100,100,400,300);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		getContentPane().setLayout(null);
		f_id.setBounds(10,10,70,25);
		t_id.setBounds(80,10,70,25);
		f_name.setBounds(200,10,70,25);
		t_name.setBounds(280,10,70,25);
		f_sex.setBounds(10,50,70,25);
		t_sex.setBounds(80,50,70,25);
		f_age.setBounds(200,50,70,25);
		t_age.setBounds(280,50,70,25);
		f_institute.setBounds(10,90,70,25);
		t_institute.setBounds(80,90,70,25);
		getMessage(s_id);
		getContentPane().add(f_id);
		getContentPane().add(t_id);
		getContentPane().add(f_name);
		getContentPane().add(t_name);
		getContentPane().add(f_sex);
		getContentPane().add(t_sex);
		getContentPane().add(f_age);
		getContentPane().add(t_age);
		getContentPane().add(f_institute);
		getContentPane().add(t_institute);
	}
	public void getMessage(String s_id)
	{
		try{
			String sql="select *from db_teacher where t_id='"+s_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next()){
			t_id.setText(set.getString("t_id"));
			t_name.setText(set.getString("t_name"));
			t_sex.setText(set.getString("t_sex"));
			t_age.setText(set.getInt("t_age")+"");
			t_institute.setText(set.getString("s_institute"));
		}}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
	}

}
