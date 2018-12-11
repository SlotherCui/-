package library.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import library.db.Dao;
import library.util.*;
import library.vo.PurviewBean;
import library.vo.UserBean;
/**
 * 添加用户的面板
 * @version 2011.07
 * @author wangtianzhi
 *
 */
public class AddUserPanel extends JPanel implements ActionListener{
	JLabel jlId,jlName,jlPasswd,jlBirthday,jlSex,jlQuestion,jlAnswer,jlPurview,jlDept,jlEmail,jltitle,jlcalendar;
	JTextField jtfId,jtfName,jtfPasswd,jtfBirthday,jtfQuestion,jtfAnswer,jtfEmail;
	JRadioButton jrbMale,jrbFemale;
	JPanel jp,jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp9,jp10,jp11;
	ButtonGroup bg;
	JComboBox jcbPurview,jcbDept;
	JButton jb,reset;
	Vector<PurviewBean> vector;
	public AddUserPanel(){
		GridLayout glo=new GridLayout(13,1);
		
//		glo.setHgap(5);
//		glo.setVgap(5);
		this.setLayout(glo);
//		jp.setPreferredSize(new Dimension(450, 450));
		
		jltitle=new JLabel("添加用户信息");		
		jltitle.setFont(ToolFont.f4);
		jltitle.setForeground(Color.BLUE);
		jlId=new JLabel("用户号:");
		jlName=new JLabel("姓   名:");
		jlPasswd=new JLabel("密   码");
		jlBirthday=new JLabel("生   日:");
		jlSex=new JLabel("性   别:");
		jlQuestion=new JLabel("问   题:");
		jlAnswer=new JLabel(" 答   案:");
		jlPurview=new JLabel("权   限:");
		jlDept=new JLabel("院   系:");
		jlEmail=new JLabel("邮   箱:");
//		jlcalendar=new JLabel(new ImageIcon("image/calendar.png"));
		
		
		jtfId=new JTextField(20);
		jtfId.setDocument(new MyDocument(12));//设置最大长度
		jtfId.addKeyListener(new PasswdWithIdListener());//设置只能输入数字,且密码框同步
		jtfName=new JTextField(20);
		jtfName.setDocument(new MyDocument(20));
		jtfPasswd=new JTextField(20);
		jtfPasswd.setDocument(new MyDocument(20));//密码最长20位
		jtfPasswd.addKeyListener(new NumberListener());//只能数字
		jtfBirthday=new JTextField(20);
		jtfBirthday.setEditable(false);
		jtfBirthday.addMouseListener(new CalendarListener());
		jtfQuestion=new JTextField(20);
		jtfQuestion.setDocument(new MyDocument(20));
		jtfAnswer=new JTextField(20);
		jtfAnswer.setDocument(new MyDocument(20));
		jtfEmail=new JTextField(20);
		jtfEmail.setDocument(new MyDocument(30));//邮箱最长30位
		
		jrbMale=new JRadioButton("男",true);
		jrbFemale=new JRadioButton("女",false);
		bg=new ButtonGroup();
		bg.add(jrbMale);
		bg.add(jrbFemale);		
		
		vector=new Dao().getAllPurviewName();
		String[] purview=new String[vector.size()];
		for(int i=0;i<vector.size();i++){
			purview[i]=vector.get(i).getName();
		}
		jcbPurview=new JComboBox(purview);
		jcbPurview.setPreferredSize(new Dimension(220, 25));
		String[] dept={"软件学院","计算机科学与技术学院","医学院","经济学院","数学院","物理学院","外国语学院"};
		jcbDept=new JComboBox(dept);
		jcbDept.setPreferredSize(new Dimension(220,25));
		
		jb=new JButton("提 交");
		jb.addActionListener(this);
		reset=new JButton("重 置");
		reset.addActionListener(this);
		
		jp=new JPanel();
		jp.add(jltitle);
		this.add(jp);
		jp1=new JPanel();
		jp1.add(jlId);
		jp1.add(jtfId);
		this.add(jp1);
		jp2=new JPanel();
		jp2.add(jlName);
		jp2.add(jtfName);
		this.add(jp2);
		jp3=new JPanel();
		jp3.add(jlPasswd);
		jp3.add(jtfPasswd);
		this.add(jp3);
		jp4=new JPanel();
		jp4.add(jlBirthday);
		jp4.add(jtfBirthday);
//		jp4.add(jlcalendar);
		this.add(jp4);
		FlowLayout flow=new FlowLayout();
		flow.setHgap(70);
		jp5=new JPanel();
		jp5.setLayout(flow);
		jp5.add(jlSex);
		jp5.add(jrbMale);
		jp5.add(jrbFemale);
		this.add(jp5);
		jp6=new JPanel();
		jp6.add(jlQuestion);
		jp6.add(jtfQuestion);
		this.add(jp6);
		jp7=new JPanel();
		jp7.add(jlAnswer);
		jp7.add(jtfAnswer);
		this.add(jp7);
		jp8=new JPanel();
		jp8.add(jlPurview);
		jp8.add(jcbPurview);
		this.add(jp8);
		jp9=new JPanel();
		jp9.add(jlDept);
		jp9.add(jcbDept);
		this.add(jp9);
		jp10=new JPanel();
		jp10.add(jlEmail);
		jp10.add(jtfEmail);
		this.add(jp10);
		jp11=new JPanel();
		jp11.add(jb);
		jp11.add(reset);
		this.add(jp11);
				
	}
	//清空文本输入框内容
	public void clear(){
		jtfId.setText("");
		jtfName.setText("");
		jtfPasswd.setText("");
		jtfBirthday.setText("");
		jtfQuestion.setText("");
		jtfAnswer.setText("");
		jtfEmail.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb){
			String id=jtfId.getText();
			if(id.length()!=12){
				JOptionPane.showMessageDialog(null, "用户号必须为12位!");
				return;
			}
			String name=jtfName.getText().trim();
			if(name.equals("")){
				JOptionPane.showMessageDialog(null, "用户名不能为空!");
				return;
			}
			
			String email=jtfEmail.getText().trim();
			//判断邮箱格式是否正确
			Pattern p=Pattern.compile("[^@\\s]+@[^@\\s]+\\.[^@\\s]+"); 
			Matcher m=p.matcher(email); 
			if(!email.equals("")&&!(m.matches())) 
			{ 
				JOptionPane.showMessageDialog(null, "邮箱格式有误!", "提示", JOptionPane.WARNING_MESSAGE);
	            return;
			}		
			String passwd=jtfPasswd.getText();
			if(passwd.equals("")){
				JOptionPane.showMessageDialog(null, "密码不能为空!");
				return;
			}
				
			String birthday=jtfBirthday.getText();
			String sex;
			if(jrbMale.isSelected())
				sex="0";
			else
				sex="1";
			String question=jtfQuestion.getText().trim();
			String answer=jtfAnswer.getText().trim();
			String purview=vector.get(jcbPurview.getSelectedIndex()).getId();
			String dept=(String)jcbDept.getSelectedItem();
			System.out.println(purview);
			UserBean ub=new UserBean();
			ub.setUser_id(id);
			ub.setUser_name(name);
			ub.setUser_passwd(passwd);
			ub.setUser_birthday(birthday);
			ub.setUser_sex(sex);
			ub.setUser_question(question);
			ub.setUser_answer(answer);
			ub.setUser_purview_id(purview);
			ub.setUser_dept(dept);
			ub.setUser_email(email);
			boolean b=new Dao().AddUser(ub);
			if(!b){
				JOptionPane.showMessageDialog(null, "添加不成功，可能学号重复!");
				return;
			}
		    this.clear();			
		}else if(e.getSource()==reset){
			this.clear();
		}

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


}
