package systemview;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class ModifyMajor extends JInternalFrame
{
	public JLabel input_i_id=new JLabel("请输入学院号");
	public JLabel input_m_id=new JLabel("请输入专业号");
	public JTextField t_input_m_id=new JTextField(12);
	public JLabel i_id=new JLabel("学院号");
	public JLabel m_id=new JLabel("专业号");
	public JLabel m_name=new JLabel("专业名称");
	public JTextField t_input_i_id=new JTextField(12);
	public JTextField t_i_id=new JTextField(12);
	public JTextField t_name=new JTextField(12);
	public JTextField t_m_id=new JTextField(12);
	public JButton modify=new JButton("修改");
	public JButton reset=new JButton("重置");
	public JButton find=new JButton("查找");
	public ModifyMajor()
	{
		super();
		setTitle("修改专业信息");
		getContentPane().setLayout(null);
		setIconifiable(true);
		setClosable(true);
		setBounds(100,100,400,260);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		input_i_id.setBounds(10, 10, 100, 25);
		input_m_id.setBounds(10, 40, 100, 25);
		i_id.setBounds(10, 80, 70, 25);
		t_i_id.setBounds(90, 80, 100, 25);
		m_id.setBounds(10, 120, 70, 25);
		t_m_id.setBounds(90, 120,100, 25);
		t_input_i_id.setBounds(90, 10, 100, 25);
		t_input_m_id.setBounds(90, 40, 100, 25);
		reset.setBounds(120, 160, 70, 30);
		modify.setBounds(20, 160, 70, 30);
		find.setBounds(220, 20, 70, 30);
		m_name.setBounds(200, 120, 70, 25);
		t_name.setBounds(280, 120, 100, 25);
		getContentPane().add(i_id);
		getContentPane().add(input_i_id);
		getContentPane().add(input_m_id);
		getContentPane().add(t_i_id);
		getContentPane().add(t_m_id);
		getContentPane().add(m_id);
		getContentPane().add(t_input_i_id);
		getContentPane().add(t_input_m_id);
		getContentPane().add(modify);
		getContentPane().add(reset);
		getContentPane().add(find);
		getContentPane().add(m_name);
		getContentPane().add(t_name);
		modify.setEnabled(false);
		reset.setEnabled(false);
		find.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String f_i_id=t_input_i_id.getText();
				String f_m_id=t_input_m_id.getText();
				if(f_i_id.length()!=6)
				{
					JOptionPane.showMessageDialog(null, "学院号错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(f_m_id.length()!=6)
				{
					JOptionPane.showMessageDialog(null, "专业号错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				getMessage(f_i_id,f_m_id);
				modify.setEnabled(true);
				reset.setEnabled(true);
			}
		});
		modify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String f_i_id=t_input_i_id.getText();
				String f_m_id=t_input_m_id.getText();
				int r=modifymessage(f_i_id,f_m_id);
				if(r<=0)
				{
					JOptionPane.showMessageDialog(null, "添加新专业失败！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "添加新专业成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
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
	public void getMessage(String f_i_id,String f_m_id)
	{
		try{
			String sql="select *from db_major where i_id='"+f_i_id+"' and m_id= '"+f_m_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next()){
			t_i_id.setText(set.getString("i_id"));
			t_m_id.setText(set.getString("m_id"));
			t_name.setText(set.getString("m_name"));}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
	}
	public int modifymessage(String f_i_id,String f_m_id)
	{
		int i=0;
		String I_id=t_i_id.getText();
		String M_id=t_m_id.getText();
		String name=t_name.getText();
		if(I_id.length()==0||M_id.length()==0||name.length()==0)
		{
			JOptionPane.showMessageDialog(null, "不能有信息为空！","友情提示",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		if(I_id.length()!=6)
		{
			JOptionPane.showMessageDialog(null, "学院号错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		if(M_id.length()!=6)
		{
			JOptionPane.showMessageDialog(null, "专业号错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		try{
			String sql="update db_major set i_id='"+I_id+"', m_id='"+M_id+"',"+"m_name='"+name+"'where i_id='"+I_id+"'and m_id='"+M_id+"'";
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
		t_i_id.setText("");
		t_name.setText("");
		t_m_id.setText("");
	}

}
