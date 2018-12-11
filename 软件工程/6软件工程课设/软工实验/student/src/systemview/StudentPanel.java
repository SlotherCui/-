package systemview;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class StudentPanel extends JPanel
{
	public JButton addstudent=new JButton("增加新的学生");
	public JButton deletestudent=new JButton("删除学生");
	//public JButton findstudent=new JButton("查找学生");
	public JButton modifystudent=new JButton("修改学生信息");
	public JDesktopPane deskPane=new JDesktopPane();
	public StudentPanel()
	{
		super();
		setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		panel.add(addstudent);
		panel.add(deletestudent);
		//panel.add(findstudent);
		panel.add(modifystudent);
		add(deskPane,BorderLayout.CENTER);
		add(panel,BorderLayout.NORTH);
		addstudent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Addstudent AS=new Addstudent();
				deskPane.add(AS);
			}
		});
		deletestudent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JInternalFrame frame=new JInternalFrame();
				deskPane.add(frame);
				frame.setTitle("删除学生");
				frame.setVisible(true);
				frame.getContentPane().setLayout(null);
				frame.setIconifiable(true);
				frame.setClosable(true);
				frame.setBounds(100,100,300,200);
				JLabel label=new JLabel("请输入学生学号");
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
						if(text.getText().length()!=12)
						{
							JOptionPane.showMessageDialog(null, "输入的学号错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						try{
							String sql="delete  from db_student where s_id='"+text.getText()+"'";
							int i=Dao.executeUpdate(sql);
							if(i>0)
							{
								JOptionPane.showMessageDialog(null, "删除成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
								frame.setClosed(true);
							}else{
								JOptionPane.showMessageDialog(null, "删除失败！","友情提示",JOptionPane.INFORMATION_MESSAGE);
								text.setText("");
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
		modifystudent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JInternalFrame frame=new JInternalFrame();
				deskPane.add(frame);
				frame.setTitle("输入");
				frame.setVisible(true);
				frame.getContentPane().setLayout(null);
				frame.setIconifiable(true);
				frame.setClosable(true);
				frame.getContentPane().setLayout(null);
				frame.setBounds(100,100,300,200);
				JLabel label=new JLabel("请输入学生学号");
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
						if(text.getText().length()!=12)
						{
							JOptionPane.showMessageDialog(null, "输入的学号错误！","友情提示",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						try{
						ModifyStudent MS=new ModifyStudent(text.getText());
						frame.setClosed(true);
						deskPane.add(MS);}catch(Exception ee){
							ee.printStackTrace();
						}
					}
				});
			}
		});
	}

}
