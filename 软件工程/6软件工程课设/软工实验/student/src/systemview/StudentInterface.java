package systemview;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class StudentInterface extends JFrame
{
	public JSplitPane jspane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	public JButton findlesson=new JButton("���ҿγ�");
	public JButton findscore=new JButton("�ɼ���ѯ");
	public JButton message=new JButton("ѧ����Ϣ");
	public JButton chooselesson=new JButton("ѧ��ѡ��");
	public static JDesktopPane deskPane=new JDesktopPane();
	public StudentInterface(String s_id)
	{
		setTitle("ѧ��ѡ��ϵͳ-ѧ��");
		setSize(800,600);
		Container c=getContentPane();
		JPanel panel=new JPanel(new GridLayout(12,1,5,10));
		panel.add(message);
		panel.add(chooselesson);
		panel.add(findlesson);
		panel.add(findscore);
		jspane.setLeftComponent(panel);
		jspane.setRightComponent(deskPane);
		jspane.setDividerLocation(100);
		c.add(jspane);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		findscore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				StudentFindScore SFS=new StudentFindScore(s_id);
				deskPane.add(SFS);
				SFS.toFront();
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
				StudentMessage SM=new StudentMessage(s_id);
				deskPane.add(SM);
				SM.toFront();
			}
		});
		chooselesson.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				StudentChooseLesson SCL=new StudentChooseLesson(s_id);
				deskPane.add(SCL);
				SCL.toFront();
			}
		});
	}

}
