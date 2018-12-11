package systemview;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class StudentPanel extends JPanel
{
	public JButton addstudent=new JButton("�����µ�ѧ��");
	public JButton deletestudent=new JButton("ɾ��ѧ��");
	//public JButton findstudent=new JButton("����ѧ��");
	public JButton modifystudent=new JButton("�޸�ѧ����Ϣ");
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
				frame.setTitle("ɾ��ѧ��");
				frame.setVisible(true);
				frame.getContentPane().setLayout(null);
				frame.setIconifiable(true);
				frame.setClosable(true);
				frame.setBounds(100,100,300,200);
				JLabel label=new JLabel("������ѧ��ѧ��");
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
						if(text.getText().length()!=12)
						{
							JOptionPane.showMessageDialog(null, "�����ѧ�Ŵ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						try{
							String sql="delete  from db_student where s_id='"+text.getText()+"'";
							int i=Dao.executeUpdate(sql);
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
		modifystudent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JInternalFrame frame=new JInternalFrame();
				deskPane.add(frame);
				frame.setTitle("����");
				frame.setVisible(true);
				frame.getContentPane().setLayout(null);
				frame.setIconifiable(true);
				frame.setClosable(true);
				frame.getContentPane().setLayout(null);
				frame.setBounds(100,100,300,200);
				JLabel label=new JLabel("������ѧ��ѧ��");
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
						if(text.getText().length()!=12)
						{
							JOptionPane.showMessageDialog(null, "�����ѧ�Ŵ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
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
