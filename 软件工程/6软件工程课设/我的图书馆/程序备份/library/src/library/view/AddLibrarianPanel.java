package library.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import library.db.Dao;
import library.util.*;
import library.view.AddUserPanel.PasswdWithIdListener;
import library.vo.*;
/**
 * 添加图书管理员面板
 * @author wangtianzhi
 *
 */
public class AddLibrarianPanel extends JPanel implements ActionListener{
	JLabel jlId,jlName,jlPasswd,jlBirthday,jlSex,jlEmail,jlTel,jlWorkPlace,jlTitle;
    JTextField jtfId,jtfName,jtfBirthday,jtfPasswd,jtfEmail,jtfTel;
    JButton jbAdd,jbReset;
    JComboBox jcbWorkPlace;
    JPanel jp0,jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp9;
    JRadioButton jrbMale,jrbFemale;
    ButtonGroup bg;
    Vector<LibraryBean> lib;
	public AddLibrarianPanel(){
		this.setLayout(new GridLayout(10,1));
		
		jp0=new JPanel();
		jlTitle=new JLabel("添加图书管理员");
		jlTitle.setFont(ToolFont.f4);
		jlTitle.setForeground(Color.BLUE);
		jp0.add(jlTitle);
		this.add(jp0);
		
		jp1=new JPanel();
		jlId=new JLabel("ID      号:");
		jtfId=new JTextField(20);
		jtfId.setDocument(new MyDocument(12));//设置最大长度
		jtfId.addKeyListener(new PasswdWithIdListener());//设置只能输入数字,且密码框同步
		jp1.add(jlId);
		jp1.add(jtfId);
		this.add(jp1);
		
		jp2=new JPanel();
		jlName=new JLabel("姓       名:");
		jtfName=new JTextField(20);
		jp2.add(jlName);
		jp2.add(jtfName);
		this.add(jp2);
		
		jp3=new JPanel();
		FlowLayout flow=new FlowLayout();
		flow.setHgap(70);
		jp3.setLayout(flow);
		jlSex=new JLabel("性       别:");
		jrbMale=new JRadioButton("男",true);
		jrbFemale=new JRadioButton("女",false);
		bg=new ButtonGroup();
		bg.add(jrbMale);
		bg.add(jrbFemale);
		jp3.add(jlSex);
		jp3.add(jrbMale);
		jp3.add(jrbFemale);
		this.add(jp3);
		
		jp4=new JPanel();
		jlBirthday=new JLabel("出生日期:");
		jtfBirthday=new JTextField(20);
		jtfBirthday.setEditable(false);
		jtfBirthday.addMouseListener(new CalendarListener());
		jp4.add(jlBirthday);
		jp4.add(jtfBirthday);
		this.add(jp4);
		
		jp5=new JPanel();
		jlPasswd=new JLabel("密       码:");
		jtfPasswd=new JTextField(20);
		jtfPasswd.setDocument(new MyDocument(20));//密码最长20位
		jtfPasswd.addKeyListener(new NumberListener());//只能数字
		jp5.add(jlPasswd);
		jp5.add(jtfPasswd);
		this.add(jp5);
		
		jp6=new JPanel();
		jlEmail=new JLabel("邮        箱:");
		jtfEmail=new JTextField(20);
		jtfEmail.setDocument(new MyDocument(30));//邮箱最长30位
		jp6.add(jlEmail);
		jp6.add(jtfEmail);
		this.add(jp6);
		
		jp7=new JPanel();
		jlTel=new JLabel("手       机:");
		jtfTel=new JTextField(20);
		jtfTel.setDocument(new MyDocument(11));//手机号为11位
		jtfTel.addKeyListener(new NumberListener());
		jp7.add(jlTel);
		jp7.add(jtfTel);
		this.add(jp7);
		
		jp8=new JPanel();
		jlWorkPlace=new JLabel("工作地点:");
//		String []wp={"软件园分馆","文理分馆","工学分馆"};
		lib=new Dao().getAllLibrary();
		String []library=new String[lib.size()];
		for(int i=0;i<lib.size();i++){
			library[i]=lib.get(i).getName();
		}
		jcbWorkPlace=new JComboBox(library);
		jcbWorkPlace.setPreferredSize(new Dimension(220,25));
		jp8.add(jlWorkPlace);
		jp8.add(jcbWorkPlace);
		this.add(jp8);
		
		jp9=new JPanel();
		jbAdd=new JButton("添    加");
		jbAdd.addActionListener(this);
		jbReset=new JButton("重    置");
		jbReset.addActionListener(this);
		jp9.add(jbAdd);
		jp9.add(jbReset);
		this.add(jp9);
		
		
	}
	/**
	 * 用户号和密码同步
	 */
	public class PasswdWithIdListener extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
		public void keyReleased(KeyEvent e){
			jtfPasswd.setText(jtfId.getText());
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jbAdd){
			String id=jtfId.getText().trim();
			if(id.equals("")){
				JOptionPane.showMessageDialog(null, "ID号不能为空!");
				return;
			}
			String name=jtfName.getText().trim();
			if(name.equals("")){
				JOptionPane.showMessageDialog(null, "管理员姓名不能为空!");
				return;
			}
			String sex;
			if(jrbMale.isSelected())
				sex="0";
			else
				sex="1";
			String birthday=jtfBirthday.getText().trim();
//			System.out.println(birthday);
			String passwd=jtfPasswd.getText().trim();
			if(passwd.equals("")){
				JOptionPane.showMessageDialog(null, "密码不能为空!");
				return;
			}
			String email=jtfEmail.getText().trim();
			if(email.equals("")){
				JOptionPane.showMessageDialog(null, "邮箱不能为空!");
	            return;
			}
			//判断邮箱格式是否正确
			Pattern p=Pattern.compile("[^@\\s]+@[^@\\s]+\\.[^@\\s]+"); 
			Matcher m=p.matcher(email); 
			if(!(m.matches())) 
			{ 
				JOptionPane.showMessageDialog(null, "邮箱格式有误!");
	            return;
			}
			String tel=jtfTel.getText().trim();
			if(tel.length()!=11){
				JOptionPane.showMessageDialog(null, "手机号必须为11位!");
	            return;
			}
			String workplaceid=lib.get(jcbWorkPlace.getSelectedIndex()).getId();
			LibrarianBean librarian=new LibrarianBean();
			librarian.setLibrarian_id(id);
			librarian.setLibrarian_name(name);
			librarian.setLibrarian_sex(sex);
			librarian.setLibrarian_bir(birthday);
			librarian.setLibrarian_passwd(passwd);
			librarian.setLibrarian_email(email);
			librarian.setLibrarian_tel(tel);
			librarian.setLibrarian_work_place(workplaceid);
			if(new Dao().addLibrarian(librarian))
				JOptionPane.showMessageDialog(null, "添加成功!");
			else
				JOptionPane.showMessageDialog(null, "添加不成功，可能ID号重复!");
		}else if(arg0.getSource()==jbReset){
			this.clear();
		}
			
		
	}
	public void clear(){
		jtfId.setText("");
		jtfName.setText("");
		jtfBirthday.setText("");
		jtfPasswd.setText("");
		jtfEmail.setText("");
		jtfTel.setText("");
	}

}
