package systemview;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class ScorePanel extends JPanel
{
	public JButton addScore=new JButton("学生成绩");
	public JButton RP=new JButton("学生奖惩");
	public JDesktopPane deskPane=new JDesktopPane();
	public ScorePanel()
	{
		super();
		setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		panel.add(addScore);
		panel.add(RP);
		add(deskPane,BorderLayout.CENTER);
		add(panel,BorderLayout.NORTH);
		RP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				RememberRP rp=new RememberRP();
				deskPane.add(rp);
			}
		});
		addScore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JInternalFrame frame=new JInternalFrame();
				deskPane.add(frame);
				frame.setTitle("登记成绩");
				frame.setVisible(true);
				frame.getContentPane().setLayout(null);
				frame.setIconifiable(true);
				frame.setClosable(true);
				frame.setBounds(100,100,300,200);
				JLabel label=new JLabel("请输入课程号");
				JTextField text=new JTextField(12);
				label.setBounds(20, 30, 100, 25);
				text.setBounds(130, 30, 120, 30);
				JButton button=new JButton("确认");
				button.setBounds(100, 100,70 , 30);
				frame.getContentPane().add(button);
				frame.getContentPane().add(label);
				frame.getContentPane().add(text);
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(text.getText().length()!=6)
						{
							JOptionPane.showMessageDialog(null, "输入的课程号错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						try{
						frame.setClosed(true);
						Addscore AS=new Addscore(text.getText());
						deskPane.add(AS);}catch(Exception ee){
							ee.printStackTrace();
						}
						//Dao.close();
					}
				});
			}
		});
	}
	public int rememberRP(String f_id)
	{
		int i=0;
		try{
			String sql="select * from db_Student where s_id='"+f_id+"'";
			i=Dao.executeUpdate(sql);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

}
