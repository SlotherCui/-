package systemview;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class ModifyLesson extends JInternalFrame
{
	public JButton LModify=new JButton("确认修改");
	public JButton reset=new JButton("重置");
	public JLabel c_id=new JLabel("课程号");
	public JLabel c_name=new JLabel("课程名");
	public JLabel c_time=new JLabel("上课时间");
	public JLabel c_teacher=new JLabel("授课教师");
	public JTextField t_id=new JTextField(12);
	public JTextField t_name=new JTextField(12);
	public JTextField t_time=new JTextField(20);
	public JTextField t_teacher=new JTextField(12);
	public ModifyLesson(String f_id)
	{
		setTitle("修改课程信息");
		getContentPane().setLayout(null);
		setIconifiable(true);
		setClosable(true);
		setBounds(100,100,400,260);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		getMessage(f_id);
		c_id.setBounds(80, 20, 70,25 );
		c_name.setBounds(80, 60, 70, 25);
		c_teacher.setBounds(80, 100, 70, 25);
		c_time.setBounds(80, 140, 70, 25);
		t_id.setBounds(180, 20, 100, 25);
		t_name.setBounds(180, 60, 100, 25);
		t_teacher.setBounds(180, 100, 100, 25);
		t_time.setBounds(180, 140, 100, 25);
		LModify.setBounds(100,180,70,30);
		reset.setBounds(220, 180, 70, 30);
		getContentPane().add(c_id);
		getContentPane().add(t_id);
		getContentPane().add(c_name);
		getContentPane().add(t_name);
		getContentPane().add(c_teacher);
		getContentPane().add(t_teacher);
		getContentPane().add(c_time);
		getContentPane().add(t_time);
		getContentPane().add(LModify);
		getContentPane().add(reset);
		LModify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int r=modifyMessage(f_id);
				if(r<=0)
				{
					JOptionPane.showMessageDialog(null, "修改课程信息失败！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "修改课程信息成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
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
	public void getMessage(String f_id)
	{
		try{
			String sql="select * from db_course where c_id='"+f_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next()){
			t_id.setText(set.getString("c_id"));
			t_name.setText(set.getString("c_name"));
			t_teacher.setText(set.getString("c_teacher"));
			t_time.setText(set.getString("c_time"));}
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
			String sql="update db_course set c_id='"+id+"',"+"c_name='"+name+"',"+"c_teacher='"+teacher+"',"+"c_time='"+time+"'where c_id='"+f_id+"'";
			i=Dao.executeUpdate(sql);
		}catch (Exception e)
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
		t_time.setText("");
		t_teacher.setText("");
	}

}
