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
 * ����û������
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
		
		jltitle=new JLabel("����û���Ϣ");		
		jltitle.setFont(ToolFont.f4);
		jltitle.setForeground(Color.BLUE);
		jlId=new JLabel("�û���:");
		jlName=new JLabel("��   ��:");
		jlPasswd=new JLabel("��   ��");
		jlBirthday=new JLabel("��   ��:");
		jlSex=new JLabel("��   ��:");
		jlQuestion=new JLabel("��   ��:");
		jlAnswer=new JLabel(" ��   ��:");
		jlPurview=new JLabel("Ȩ   ��:");
		jlDept=new JLabel("Ժ   ϵ:");
		jlEmail=new JLabel("��   ��:");
//		jlcalendar=new JLabel(new ImageIcon("image/calendar.png"));
		
		
		jtfId=new JTextField(20);
		jtfId.setDocument(new MyDocument(12));//������󳤶�
		jtfId.addKeyListener(new PasswdWithIdListener());//����ֻ����������,�������ͬ��
		jtfName=new JTextField(20);
		jtfName.setDocument(new MyDocument(20));
		jtfPasswd=new JTextField(20);
		jtfPasswd.setDocument(new MyDocument(20));//�����20λ
		jtfPasswd.addKeyListener(new NumberListener());//ֻ������
		jtfBirthday=new JTextField(20);
		jtfBirthday.setEditable(false);
		jtfBirthday.addMouseListener(new CalendarListener());
		jtfQuestion=new JTextField(20);
		jtfQuestion.setDocument(new MyDocument(20));
		jtfAnswer=new JTextField(20);
		jtfAnswer.setDocument(new MyDocument(20));
		jtfEmail=new JTextField(20);
		jtfEmail.setDocument(new MyDocument(30));//�����30λ
		
		jrbMale=new JRadioButton("��",true);
		jrbFemale=new JRadioButton("Ů",false);
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
		String[] dept={"���ѧԺ","�������ѧ�뼼��ѧԺ","ҽѧԺ","����ѧԺ","��ѧԺ","����ѧԺ","�����ѧԺ"};
		jcbDept=new JComboBox(dept);
		jcbDept.setPreferredSize(new Dimension(220,25));
		
		jb=new JButton("�� ��");
		jb.addActionListener(this);
		reset=new JButton("�� ��");
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
	//����ı����������
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
				JOptionPane.showMessageDialog(null, "�û��ű���Ϊ12λ!");
				return;
			}
			String name=jtfName.getText().trim();
			if(name.equals("")){
				JOptionPane.showMessageDialog(null, "�û�������Ϊ��!");
				return;
			}
			
			String email=jtfEmail.getText().trim();
			//�ж������ʽ�Ƿ���ȷ
			Pattern p=Pattern.compile("[^@\\s]+@[^@\\s]+\\.[^@\\s]+"); 
			Matcher m=p.matcher(email); 
			if(!email.equals("")&&!(m.matches())) 
			{ 
				JOptionPane.showMessageDialog(null, "�����ʽ����!", "��ʾ", JOptionPane.WARNING_MESSAGE);
	            return;
			}		
			String passwd=jtfPasswd.getText();
			if(passwd.equals("")){
				JOptionPane.showMessageDialog(null, "���벻��Ϊ��!");
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
				JOptionPane.showMessageDialog(null, "��Ӳ��ɹ�������ѧ���ظ�!");
				return;
			}
		    this.clear();			
		}else if(e.getSource()==reset){
			this.clear();
		}

	}
	/**
	 * �û��ź�����ͬ��
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
