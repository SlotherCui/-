package systemview;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
public class LoginView extends JFrame
{
	private JButton loginbutton=new JButton("��¼");
	private JButton resetbutton=new JButton("����");
	private JLabel loginlabel=new JLabel("�û���");
	private JLabel loginpassword=new JLabel("����");
	private JTextField name=new JTextField(12);
	private JPasswordField password=new JPasswordField(12);
	private String[] items={"����Ա","ѧ��"};
	private JComboBox person=new JComboBox(items);
	public LoginView()
	{
		setTitle("ѧ��ѡ��ϵͳ");
		getContentPane().setLayout(null);
		setSize(400,300);
		Container c=getContentPane();
		loginlabel.setBounds(120, 90, 50, 25);
		loginpassword.setBounds(120, 120, 50, 25);
		name.setBounds(170, 90, 100, 25);
		password.setBounds(170, 120, 100, 25);
		loginbutton.setBounds(100, 160, 70, 30);
		resetbutton.setBounds(220, 160, 70, 30);
		person.setBounds(145,45 , 100, 30);
		c.add(loginbutton);
		c.add(person);
		c.add(loginlabel);
		c.add(loginpassword);
		c.add(name);
		c.add(password);
		c.add(resetbutton);
		loginbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int user;
				if(person.getSelectedItem().equals("ѧ��"))
				{
					user=1;
				}else{
					user=0;
				}
				if(name.getText().length()==0||password.getPassword().length==0)
				{
					JOptionPane.showMessageDialog(null, "�������û��������룡","������ʾ",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String inputpassword=String.valueOf(password.getPassword());
				String inputname=name.getText();
				if(user==0)
				{
					try{
					String sql="select * from db_operator where id='"+inputname+"'";
					ResultSet rs =Dao.executeQuery(sql);
					String password;
					while(rs.next()){
					password=rs.getString("password");
					if(password.equals(inputpassword))
					{
						dispose();
						AdministratorInterface c=new AdministratorInterface(inputname);
					}else{
						JOptionPane.showMessageDialog(null, "�û�����������������","������ʾ",JOptionPane.INFORMATION_MESSAGE);
						resetbutton.doClick();
						return;
					}}
					}catch(SQLException ee)
					{
						ee.printStackTrace();
					}
					Dao.close();
				}else{
					try{
					String sql="select * from db_student where s_id='"+inputname+"'";
					ResultSet rs=Dao.executeQuery(sql);
					String password;
					while(rs.next()){
					password=rs.getString("s_password");
					if(password.equals(inputpassword))
					{
						dispose();
						StudentInterface c=new StudentInterface(inputname);
					}else{
						JOptionPane.showMessageDialog(null, "�û�����������������","������ʾ",JOptionPane.INFORMATION_MESSAGE);
						resetbutton.doClick();
						return;
					}}
					}catch(SQLException ee)
					{
						ee.printStackTrace();
					}
					Dao.close();
				}
			}
		});
		resetbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				name.setText("");
				password.setText("");
			}
		});
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	public static void main(String[] srgs)
	{
		LoginView c=new LoginView();
	}

}
