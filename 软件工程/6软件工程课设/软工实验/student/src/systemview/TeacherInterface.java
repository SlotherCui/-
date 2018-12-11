package systemview;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class TeacherInterface extends JFrame
{
	
	private JLabel name=new JLabel("����");
	private JLabel id=new JLabel("�̹���");
	private JLabel sex=new JLabel("�Ա�");
	private JLabel o_name=new JLabel();
	private JLabel o_id=new JLabel();
	private JLabel o_sex=new JLabel();
	private JLabel label=new JLabel();
	public JSplitPane jspane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	public static JDesktopPane deskPane=new JDesktopPane();
	public JButton findlesson=new JButton("���������ڿγ�");
	public JButton addscore=new JButton("�ɼ�����");
	public JButton findstudent=new JButton("��ѯѧ����Ϣ");
	public JButton message=new JButton("��ʦ��Ϣ");
	public TeacherInterface(String t_id)
	{
		setTitle("ѧ��ѡ��ϵͳ-��ʦ");
		setSize(800,600);
		Container c=getContentPane();
		JPanel panel=new JPanel(new GridLayout(12,1,5,10));
		panel.add(message);
		panel.add(addscore);
		panel.add(findlesson);
		panel.add(findstudent);
		jspane.setLeftComponent(panel);
		jspane.setRightComponent(deskPane);
		jspane.setDividerLocation(100);
		c.add(jspane);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addscore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JInternalFrame frame=new JInternalFrame();
				deskPane.add(frame);
				frame.setTitle("�Ǽǳɼ�");
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
						Addscore AS=new Addscore(text.getText());
						deskPane.add(AS);}catch(Exception ee){
							ee.printStackTrace();
						}
						//Dao.close();
					}
				});
			}
		});
		findlesson.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				StudentFindLesson SFL=new StudentFindLesson();
				deskPane.add(SFL);
				SFL.toFront();
			}
		});
		message.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				TeacherMessage TM=new TeacherMessage(t_id);
				deskPane.add(TM);
				TM.toFront();
			}
		});
		findstudent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				StudentChooseLesson SCL=new StudentChooseLesson(t_id);
				deskPane.add(SCL);
				SCL.toFront();
			}
		});
	}

}
