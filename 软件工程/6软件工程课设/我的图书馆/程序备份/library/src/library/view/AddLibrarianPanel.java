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
 * ���ͼ�����Ա���
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
		jlTitle=new JLabel("���ͼ�����Ա");
		jlTitle.setFont(ToolFont.f4);
		jlTitle.setForeground(Color.BLUE);
		jp0.add(jlTitle);
		this.add(jp0);
		
		jp1=new JPanel();
		jlId=new JLabel("ID      ��:");
		jtfId=new JTextField(20);
		jtfId.setDocument(new MyDocument(12));//������󳤶�
		jtfId.addKeyListener(new PasswdWithIdListener());//����ֻ����������,�������ͬ��
		jp1.add(jlId);
		jp1.add(jtfId);
		this.add(jp1);
		
		jp2=new JPanel();
		jlName=new JLabel("��       ��:");
		jtfName=new JTextField(20);
		jp2.add(jlName);
		jp2.add(jtfName);
		this.add(jp2);
		
		jp3=new JPanel();
		FlowLayout flow=new FlowLayout();
		flow.setHgap(70);
		jp3.setLayout(flow);
		jlSex=new JLabel("��       ��:");
		jrbMale=new JRadioButton("��",true);
		jrbFemale=new JRadioButton("Ů",false);
		bg=new ButtonGroup();
		bg.add(jrbMale);
		bg.add(jrbFemale);
		jp3.add(jlSex);
		jp3.add(jrbMale);
		jp3.add(jrbFemale);
		this.add(jp3);
		
		jp4=new JPanel();
		jlBirthday=new JLabel("��������:");
		jtfBirthday=new JTextField(20);
		jtfBirthday.setEditable(false);
		jtfBirthday.addMouseListener(new CalendarListener());
		jp4.add(jlBirthday);
		jp4.add(jtfBirthday);
		this.add(jp4);
		
		jp5=new JPanel();
		jlPasswd=new JLabel("��       ��:");
		jtfPasswd=new JTextField(20);
		jtfPasswd.setDocument(new MyDocument(20));//�����20λ
		jtfPasswd.addKeyListener(new NumberListener());//ֻ������
		jp5.add(jlPasswd);
		jp5.add(jtfPasswd);
		this.add(jp5);
		
		jp6=new JPanel();
		jlEmail=new JLabel("��        ��:");
		jtfEmail=new JTextField(20);
		jtfEmail.setDocument(new MyDocument(30));//�����30λ
		jp6.add(jlEmail);
		jp6.add(jtfEmail);
		this.add(jp6);
		
		jp7=new JPanel();
		jlTel=new JLabel("��       ��:");
		jtfTel=new JTextField(20);
		jtfTel.setDocument(new MyDocument(11));//�ֻ���Ϊ11λ
		jtfTel.addKeyListener(new NumberListener());
		jp7.add(jlTel);
		jp7.add(jtfTel);
		this.add(jp7);
		
		jp8=new JPanel();
		jlWorkPlace=new JLabel("�����ص�:");
//		String []wp={"���԰�ֹ�","����ֹ�","��ѧ�ֹ�"};
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
		jbAdd=new JButton("��    ��");
		jbAdd.addActionListener(this);
		jbReset=new JButton("��    ��");
		jbReset.addActionListener(this);
		jp9.add(jbAdd);
		jp9.add(jbReset);
		this.add(jp9);
		
		
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jbAdd){
			String id=jtfId.getText().trim();
			if(id.equals("")){
				JOptionPane.showMessageDialog(null, "ID�Ų���Ϊ��!");
				return;
			}
			String name=jtfName.getText().trim();
			if(name.equals("")){
				JOptionPane.showMessageDialog(null, "����Ա��������Ϊ��!");
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
				JOptionPane.showMessageDialog(null, "���벻��Ϊ��!");
				return;
			}
			String email=jtfEmail.getText().trim();
			if(email.equals("")){
				JOptionPane.showMessageDialog(null, "���䲻��Ϊ��!");
	            return;
			}
			//�ж������ʽ�Ƿ���ȷ
			Pattern p=Pattern.compile("[^@\\s]+@[^@\\s]+\\.[^@\\s]+"); 
			Matcher m=p.matcher(email); 
			if(!(m.matches())) 
			{ 
				JOptionPane.showMessageDialog(null, "�����ʽ����!");
	            return;
			}
			String tel=jtfTel.getText().trim();
			if(tel.length()!=11){
				JOptionPane.showMessageDialog(null, "�ֻ��ű���Ϊ11λ!");
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
				JOptionPane.showMessageDialog(null, "��ӳɹ�!");
			else
				JOptionPane.showMessageDialog(null, "��Ӳ��ɹ�������ID���ظ�!");
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
