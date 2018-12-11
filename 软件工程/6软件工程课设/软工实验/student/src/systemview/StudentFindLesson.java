package systemview;
import java.awt.event.*;
import javax.swing.*;
public class StudentFindLesson extends JInternalFrame
{
	public JLabel f_id=new JLabel("课程号");
	public JLabel f_name=new JLabel("课程名称");
	public JLabel f_teacher=new JLabel("授课老师");
	public JTextField t_id=new JTextField(12);
	public JTextField t_name=new JTextField(12);
	public JTextField t_teacher=new JTextField(12);
	public JButton find=new JButton("查询");
	public JButton reset=new JButton("重置");
	public StudentFindLesson()
	{
		super();
		setTitle("课程查询");
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
			StudentInterface.deskPane.add(SL);
			SL.toFront();
			setVisible(false);
			return;
		}
		if(name.length()!=0&&teacher.length()==0)
		{
			ShowLesson SL=new ShowLesson(name,1);
			StudentInterface.deskPane.add(SL);
			SL.toFront();
			setVisible(false);
			return;
		}
		if(name.length()==0&&teacher.length()!=0)
		{
			ShowLesson SL=new ShowLesson(teacher,0);
			StudentInterface.deskPane.add(SL);
			SL.toFront();
			setVisible(false);
			return;
		}
		if(name.length()==0&&teacher.length()==0)
		{
			ShowLesson SL=new ShowLesson();
			StudentInterface.deskPane.add(SL);
			SL.toFront();
			setVisible(false);
			return;
		}
		if(name.length()!=0&&teacher.length()!=0)
		{
			ShowLesson SL=new ShowLesson(teacher,name);
			StudentInterface.deskPane.add(SL);
			SL.toFront();
			setVisible(false);
			return;
		}
	}
	public void reset()
	{
		t_id.setText("");
		t_name.setText("");
		t_teacher.setText("");
	}

}
