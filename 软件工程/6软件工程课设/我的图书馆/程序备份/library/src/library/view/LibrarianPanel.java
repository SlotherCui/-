package library.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import library.vo.*;
import library.db.*;
import library.util.*;
/**
 * 图书管理员个人信息管理面板
 * @author wangtianzhi
 *
 */
public class LibrarianPanel extends JPanel implements ActionListener{
	JLabel idlabel,namelabel,birthdaylabel,emaillabel,workplacelabel,tellabel,pwlabel,pwlabel1;
	JLabel id,name,birthday,workplace;
	JButton pwbutton,pwbutton1,mailbutton,telbutton;
	JTextField jtfmail,jtftel;
	JPasswordField jpwf,jpwf1;
	JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp9,jp10,jp11,jp12;
	String librarianId;
	public LibrarianPanel(String librarianId){
		this.librarianId=librarianId;
		this.setLayout(new GridLayout(10,2));
		LibrarianBean lb=new Dao().getLibrarian(librarianId);
		
		idlabel=new JLabel("ID  号:   ");
		idlabel.setFont(ToolFont.f2);
		jp1=new JPanel();
		jp1.setLayout(new BorderLayout());
		jp1.add(idlabel,BorderLayout.EAST);
		id=new JLabel(lb.getLibrarian_id());
		id.setFont(ToolFont.f2);
		
		namelabel=new JLabel("姓  名:   ");
		namelabel.setFont(ToolFont.f2);
		jp2=new JPanel();
		jp2.setLayout(new BorderLayout());
		jp2.add(namelabel,BorderLayout.EAST);
		name=new JLabel(lb.getLibrarian_name());
		name.setFont(ToolFont.f2);
		
		birthdaylabel=new JLabel("出生日期:   ");
		birthdaylabel.setFont(ToolFont.f2);
		jp3=new JPanel();
		jp3.setLayout(new BorderLayout());
		jp3.add(birthdaylabel,BorderLayout.EAST);
		birthday=new JLabel(lb.getLibrarian_bir().toString());
		birthday.setFont(ToolFont.f2);
		
		workplacelabel=new JLabel("工作地点:   ");
		workplacelabel.setFont(ToolFont.f2);
		jp4=new JPanel();
		jp4.setLayout(new BorderLayout());
		jp4.add(workplacelabel,BorderLayout.EAST);
		String libraryId=lb.getLibrarian_work_place();
		workplace=new JLabel(libraryId);
		workplace.setFont(ToolFont.f2);
		
		emaillabel=new JLabel("E_mail:   ");
		emaillabel.setFont(ToolFont.f2);
		jp5=new JPanel();
		jp5.setLayout(new BorderLayout());
		jp5.add(emaillabel,BorderLayout.EAST);
		jp7=new JPanel();
		jp7.setLayout(new FlowLayout(FlowLayout.LEFT));
		jtfmail=new JTextField(15);
		jtfmail.setText(lb.getLibrarian_email());
		mailbutton=new JButton("修改邮箱");
		mailbutton.addActionListener(this);
		jp7.add(jtfmail);
		jp7.add(mailbutton);
		
		tellabel=new JLabel("手 机 号:   ");
		tellabel.setFont(ToolFont.f2);
		jp6=new JPanel();
		jp6.setLayout(new BorderLayout());
		jp6.add(tellabel,BorderLayout.EAST);
		jp8=new JPanel();
		jp8.setLayout(new FlowLayout(FlowLayout.LEFT));
		jtftel=new JTextField(15);
		jtftel.setText(lb.getLibrarian_tel());
		telbutton=new JButton("修改手机号");
		telbutton.addActionListener(this);
		jp8.add(jtftel);
		jp8.add(telbutton);
		
		pwlabel=new JLabel("密  码:   ");
		pwlabel.setFont(ToolFont.f2);
		jp9=new JPanel();
		jp9.setLayout(new BorderLayout());
		jp9.add(pwlabel,BorderLayout.EAST);
		jp10=new JPanel();
		jp10.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpwf=new JPasswordField(15);
		jpwf.setText(lb.getLibrarian_passwd());
		pwbutton=new JButton("修改密码");
		pwbutton.addActionListener(this);
		jp10.add(jpwf);
		jp10.add(pwbutton);
		
		pwlabel1=new JLabel("再次输入新密码:   ");
		pwlabel1.setFont(ToolFont.f2);
//		pwlabel1.setVisible(false);
		jp11=new JPanel();
		jp11.setLayout(new BorderLayout());
		jp11.add(pwlabel1, BorderLayout.EAST);
		jp11.setVisible(false);
		jp12=new JPanel();
		jp12.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpwf1=new JPasswordField(15);
//		jpwf1.setVisible(false);
		pwbutton1=new JButton("确认新密码");
		pwbutton1.addActionListener(this);
//		pwbutton1.setVisible(false);
		jp12.add(jpwf1);
		jp12.add(pwbutton1);
		jp12.setVisible(false);
		
		this.add(jp1);
		this.add(id);
		this.add(jp2);
		this.add(name);		
		this.add(jp3);
		this.add(birthday);
		this.add(jp4);
		this.add(workplace);
		this.add(jp5);
		this.add(jp7);
		this.add(jp6);
		this.add(jp8);
		this.add(jp9);
		this.add(jp10);
		this.add(jp11);
		this.add(jp12);
		
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==mailbutton){
			//修改邮箱
			String mail=jtfmail.getText().trim();
			if(mail.equals("")){
				JOptionPane.showMessageDialog(null, "邮箱不能为空!");
			    return;
			}
			Pattern p=Pattern.compile("[^@\\s]+@[^@\\s]+\\.[^@\\s]+"); 
			Matcher m=p.matcher(mail); 
			if(!(m.matches())) 
			{ 
				JOptionPane.showMessageDialog(null, "邮箱格式有误!", "提示", JOptionPane.WARNING_MESSAGE);
	            return;
			}
			int update=new Dao().updateLibrarianMail(librarianId, mail);
			System.out.println(update);
		}else if(arg0.getSource()==telbutton){
			//修改手机号
			String tel=jtftel.getText().trim();
			if(tel.length()!=11){
				JOptionPane.showMessageDialog(null, "手机号的位数必须为11位!");
				return;
			}
			int update=new Dao().updateLibrarianTel(librarianId, tel);
		}else if(arg0.getSource()==pwbutton){
			//修改密码(单击修改密码按钮弹出再次输入新密码框)
//			if(jpwf.getPassword().equals("")){
//				JOptionPane.showMessageDialog(null, "密码不能为空!");
//				return;
//			}
			jp11.setVisible(true);
			jp12.setVisible(true);
			
		}else if(arg0.getSource()==pwbutton1){
			//确认新密码
			String newpw=new String(jpwf.getPassword());
			String newpw1=new String(jpwf1.getPassword());
			if(newpw.equals(newpw1)){
				//修改成功
				int update=new Dao().updateLibrarianPW(librarianId, newpw);
				jp11.setVisible(false);
				jp12.setVisible(false);
				jpwf1.setText("");
			}else{
				//两次输入的密码不相符
				JOptionPane.showMessageDialog(null, "两次输入的密码不相符!", "提示", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
