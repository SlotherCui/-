package systemview;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
public class Addlesson extends JInternalFrame
{
	public JButton add=new JButton("添加");
	public JButton reset=new JButton("重置");
	public JLabel c_id=new JLabel("课程号");
	public JLabel c_name=new JLabel("课程名");
	public JLabel c_time=new JLabel("上课时间");
	public JLabel c_teacher=new JLabel("授课教师");
	public JTextField t_id=new JTextField(12);
	public JTextField t_name=new JTextField(12);
	public JTextField t_time=new JTextField(20);
	public JTextField t_teacher=new JTextField(12);
	public Addlesson()
	{
		super();
		setTitle("添加新课程");
		getContentPane().setLayout(null);
		setIconifiable(true);
		setClosable(true);
		setBounds(100,100,400,260);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		//JPanel panel=new JPanel(new GridLayout(4,2,5,10));
		c_id.setBounds(80, 20, 70,25 );
		c_name.setBounds(80, 60, 70, 25);
		c_teacher.setBounds(80, 100, 70, 25);
		c_time.setBounds(80, 140, 70, 25);
		t_id.setBounds(180, 20, 100, 25);
		t_name.setBounds(180, 60, 100, 25);
		t_teacher.setBounds(180, 100, 100, 25);
		t_time.setBounds(180, 140, 100, 25);
		add.setBounds(100,180,70,30);
		reset.setBounds(220, 180, 70, 30);
		getContentPane().add(c_id);
		getContentPane().add(t_id);
		getContentPane().add(c_name);
		getContentPane().add(t_name);
		getContentPane().add(c_teacher);
		getContentPane().add(t_teacher);
		getContentPane().add(c_time);
		getContentPane().add(t_time);
		getContentPane().add(add);
		getContentPane().add(reset);
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int r=addlessons();
				if(r<=0)
				{
					JOptionPane.showMessageDialog(null, "添加新课程失败！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "添加新课程成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
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
	public int addlessons()
	{
		int i=0;
		String id=t_id.getText();
		String name=t_name.getText();
		String time=t_time.getText();
		String teacher=t_teacher.getText();
		if(id.length()==0||name.length()==0||time.length()==0||teacher.length()==0)
		{
			JOptionPane.showMessageDialog(null, "不能有信息为空！","友情提示",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		if(id.length()!=6)
		{
			JOptionPane.showMessageDialog(null, "课程号输入错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		try{
			String sql="insert into db_course(c_id,c_name,c_teacher,c_time) values('"+id+"','"+name+"','"+teacher+"','"+time+"')";
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
		t_time.setText("");
		t_teacher.setText("");
	}

}
