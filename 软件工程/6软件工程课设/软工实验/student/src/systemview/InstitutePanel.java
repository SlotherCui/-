package systemview;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;
import java.sql.*;
public class InstitutePanel extends JPanel
{
	public JButton addmajor=new JButton("增加新的专业");
	public JButton deletemajor=new JButton("删除专业");
	public JButton modifymajor=new JButton("修改专业信息");
	public JButton modifyinstitute=new JButton("修改学院信息");
	public JDesktopPane deskPane=new JDesktopPane();
	public InstitutePanel()
	{
		super();
		setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		panel.add(addmajor);
		panel.add(deletemajor);
		panel.add(modifymajor);
		panel.add(modifyinstitute);
		add(deskPane,BorderLayout.CENTER);
		add(panel,BorderLayout.NORTH);
		addmajor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Addmajor AM=new Addmajor();
				deskPane.add(AM);
			}
		});
		modifyinstitute.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				ModifyInstitute MI=new ModifyInstitute();
				deskPane.add(MI);
			}
		});
		deletemajor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JInternalFrame frame=new JInternalFrame();
				deskPane.add(frame);
				frame.setTitle("删除专业");
				frame.setVisible(true);
				frame.getContentPane().setLayout(null);
				frame.setIconifiable(true);
				frame.setClosable(true);
				frame.setBounds(100,100,300,200);
				JLabel label1=new JLabel("请输入学院号");
				JTextField text1=new JTextField(12);
				JLabel label2=new JLabel("请输入专业号");
				JTextField text2=new JTextField(12);
				label1.setBounds(20, 30, 100, 25);
				text1.setBounds(130, 30, 120, 30);
				label2.setBounds(20, 70, 100, 25);
				text2.setBounds(130, 70, 120, 30);
				JButton button=new JButton("确认");
				button.setBounds(100, 120,70 , 30);
				frame.getContentPane().add(button);
				frame.getContentPane().add(label1);
				frame.getContentPane().add(text1);
				frame.getContentPane().add(label2);
				frame.getContentPane().add(text2);
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(text1.getText().length()!=6||text2.getText().length()!=6)
						{
							JOptionPane.showMessageDialog(null, "输入的学员号或专业号错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						try{
							String sql="delete from db_major where i_id='"+text1.getText()+"' and m_id= '"+text2.getText()+"'";
							int i=Dao.executeUpdate(sql);
							if(i>0)
							{
								JOptionPane.showMessageDialog(null, "删除成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
								frame.setClosed(true);
							}else{
								JOptionPane.showMessageDialog(null, "删除失败！","友情提示",JOptionPane.INFORMATION_MESSAGE);
								text1.setText("");
								text2.setText("");
								return;
							}
						}catch (Exception ee)
						{
							ee.printStackTrace();
						}
						Dao.close();
					}
				});
			}
		});
		modifymajor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				ModifyMajor MM=new ModifyMajor();
				deskPane.add(MM);
			}
		});
		
		
	}

}
