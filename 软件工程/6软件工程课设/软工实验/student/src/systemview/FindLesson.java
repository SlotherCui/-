package systemview;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class FindLesson extends JInternalFrame
{
	public JLabel f_id=new JLabel("�γ̺�");
	public JLabel f_name=new JLabel("�γ�����");
	public JLabel f_teacher=new JLabel("�ڿ���ʦ");
	public JTextField t_id=new JTextField(12);
	public JTextField t_name=new JTextField(12);
	public JTextField t_teacher=new JTextField(12);
	public JButton find=new JButton("��ѯ");
	public JButton reset=new JButton("����");
	public FindLesson()
	{
		super();
		setTitle("�γ̲�ѯ");
		setIconifiable(true);
		setClosable(true);
		setBounds(100,100,300,250);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		getContentPane().setLayout(null);
		f_id.setBounds(40, 10, 70, 25);
		f_name.setBounds(40, 50, 70, 25);
		f_teacher.setBounds(40, 90, 70, 25);
		t_id.setBounds(130, 10, 100, 25);
		t_name.setBounds(130, 50, 100, 25);
		t_teacher.setBounds(130, 90, 100, 25);
		find.setBounds(60, 130, 70, 30);
		reset.setBounds(150, 130, 70, 30);
		getContentPane().add(f_id);
		getContentPane().add(f_name);
		getContentPane().add(f_teacher);
		getContentPane().add(t_id);
		getContentPane().add(t_name);
		getContentPane().add(t_teacher);
		getContentPane().add(find);
		getContentPane().add(reset);
		find.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				findlesson();
			}
		});
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				reset();
			}
		});
	}
	public void findlesson()
	{
		String id=t_id.getText();
		String name=t_name.getText();
		String teacher=t_teacher.getText();
		if(id.length()!=0)
		{
			ShowLesson SL=new ShowLesson(id);
			LessonPanel.deskPane.add(SL);
			setVisible(false);
		}
		if(name.length()!=0&&teacher.length()==0)
		{
			ShowLesson SL=new ShowLesson(name,1);
			LessonPanel.deskPane.add(SL);
			setVisible(false);
		}
		if(name.length()==0&&teacher.length()!=0)
		{
			ShowLesson SL=new ShowLesson(teacher,0);
			LessonPanel.deskPane.add(SL);
			setVisible(false);
		}
		if(name.length()==0&&teacher.length()==0)
		{
			ShowLesson SL=new ShowLesson();
			LessonPanel.deskPane.add(SL);
			setVisible(false);
		}
		if(name.length()!=0&&teacher.length()!=0)
		{
			ShowLesson SL=new ShowLesson(teacher,name);
			LessonPanel.deskPane.add(SL);
			setVisible(false);
		}
	}
	public void reset()
	{
		t_id.setText("");
		t_name.setText("");
		t_teacher.setText("");
	}

}
