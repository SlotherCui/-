package systemview;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;
import java.sql.*;
public class LessonPanel extends JPanel
{
	public JButton addlesson=new JButton("��ӿγ�");
	public JButton deletelesson=new JButton("ɾ���γ�");
	public JButton findlesson=new JButton("���ҿγ�");
	public JButton showlesson=new JButton("�޸Ŀγ���Ϣ");
	public static JDesktopPane deskPane=new JDesktopPane();
	public LessonPanel()
	{
		super();
		setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		panel.add(addlesson);
		panel.add(deletelesson);
		panel.add(findlesson);
		panel.add(showlesson);
		add(deskPane,BorderLayout.CENTER);
		add(panel,BorderLayout.NORTH);
		addlesson.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Addlesson AS=new Addlesson();
				deskPane.add(AS);
			}
		});
		findlesson.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				FindLesson FL=new FindLesson();
				deskPane.add(FL);
			}
		});
		deletelesson.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JInternalFrame frame=new JInternalFrame();
				deskPane.add(frame);
				frame.setTitle("ɾ���γ�");
				frame.setVisible(true);
				frame.getContentPane().setLayout(null);
				frame.setIconifiable(true);
				frame.setClosable(true);
				frame.setBounds(100,100,300,200);
				JLabel label=new JLabel("������γ̺�");
				JTextField text=new JTextField(12);
				label.setBounds(20, 30, 100, 25);
				text.setBounds(130, 30, 120, 30);
				JButton button=new JButton("ȷ��");
				button.setBounds(100, 100,70 , 30);
				frame.getContentPane().add(button);
				frame.getContentPane().add(label);
				frame.getContentPane().add(text);
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(text.getText().length()!=6)
						{
							JOptionPane.showMessageDialog(null, "����Ŀγ̺Ŵ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						try{
							String sql="delete from db_course where c_id='"+text.getText()+"'";
							int j=Dao.executeUpdate(sql);
							if(j<=0)
								return;
							String sqls="delete from db_choose where c_id='"+text.getText()+"'";
							int i=Dao.executeUpdate(sqls);
							if(i>0)
							{
								JOptionPane.showMessageDialog(null, "ɾ���ɹ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
								frame.setClosed(true);
							}else{
								JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�","������ʾ",JOptionPane.INFORMATION_MESSAGE);
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
		showlesson.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JInternalFrame frame=new JInternalFrame();
				deskPane.add(frame);
				frame.setTitle("����");
				frame.setVisible(true);
				frame.getContentPane().setLayout(null);
				frame.setIconifiable(true);
				frame.setClosable(true);
				frame.setBounds(100,100,300,200);
				JLabel label=new JLabel("������γ̺�");
				JTextField text=new JTextField(12);
				label.setBounds(20, 30, 100, 25);
				text.setBounds(130, 30, 120, 30);
				JButton button=new JButton("ȷ��");
				button.setBounds(100, 100,70 , 30);
				frame.getContentPane().add(button);
				frame.getContentPane().add(label);
				frame.getContentPane().add(text);
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(text.getText().length()!=6)
						{
							JOptionPane.showMessageDialog(null, "����Ŀγ̺Ŵ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						try{
						frame.setClosed(true);
						ModifyLesson ML=new ModifyLesson(text.getText());
						deskPane.add(ML);}catch(Exception ee){
							ee.printStackTrace();
						}
					}
				});
			}
		});
	}

}
