package systemview;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
public class Addstudent extends JInternalFrame 
{
	public JButton add=new JButton("添加");
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
	public Addstudent()
	{
		super();
		setTitle("添加新学生用户");
		getContentPane().setLayout(null);
		setIconifiable(true);
		setClosable(true);
		setBounds(100,50,500,400);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
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
		add.setBounds(150, 320, 70, 30);
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
		getContentPane().add(add);
		getContentPane().add(reset);
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int r=addstudent();
				if(r<=0)
				{
					JOptionPane.showMessageDialog(null, "添加新用户失败！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "添加新用户成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
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
	public int addstudent()
	{
		int i=0;
		String id=t_id.getText();
		String name=t_name.getText();
		String password=t_password.getText();
		String sex=t_sex.getText();
	    String institute=t_institute.getText();
	    String major=t_major.getText();
	    int age=0;
	    if(t_age.getText().length()!=0)
             age=Integer.parseInt(t_age.getText());
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
			String sql="insert into db_student(s_id,s_name,s_age,s_sex,s_institute,s_major,s_password,s_RP) values('"+id+"','"+name+"',"+age+",'"+sex+"','"+institute+"','"+major+"','"+password+"','"+RP+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}
		Dao.close();
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
