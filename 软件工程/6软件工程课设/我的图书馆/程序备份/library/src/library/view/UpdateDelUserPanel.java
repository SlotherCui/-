package library.view;
import java.awt.*;
import java.awt.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.*;

import library.db.Dao;
import library.util.*;
import library.vo.*;
/**
 * ���º�ɾ���û���Ϣ���
 * @author wangtianzhi
 *
 */
public class UpdateDelUserPanel extends JPanel implements ActionListener{
	JButton jbSearch,jbUpdate,jbDelete;
	JLabel jlName,jlPasswd,jlBirthday,jlSex,jlQuestion,jlAnswer,jlPurview,jlDept,jlEmail;
	JTextField jtfSearch,jtfName,jtfPasswd,jtfBirthday,jtfQuestion,jtfAnswer,jtfEmail;
	JComboBox jcbSearch,jcbPurview,jcbDept;
	ButtonGroup bg;
	JRadioButton jrbMale,jrbFemale;
	JTable table;
	JScrollPane jsp;
	JPanel jpSearch,jptable,jpButton,jp1,jp2,jp3;
	Vector<PurviewBean> vector=new Dao().getAllPurviewName();
	String column[]={"�û���","����","����","��������","�Ա�","����","��","Ȩ��","Ժϵ","����"};
	String value[][];
	String searchWay,searchText;
	public UpdateDelUserPanel(){
		searchWay="�û���";
		searchText="";
		jpSearch=new JPanel();
		String way[]={"�û���","��   ��","��   ��"};
		jcbSearch=new JComboBox(way);
		jtfSearch=new JTextField(15);
		jbSearch=new JButton("��   ��");
		jbSearch.addActionListener(this);
		jpSearch.add(jcbSearch);
		jpSearch.add(jtfSearch);
		jpSearch.add(jbSearch);
		this.add(jpSearch);
		
//		DefaultTableModel model=new DefaultTableModel();
		
//		String value[][]={{"200900301236","wangtianzhi","362413","1990-01-01","��","����˭????????????","wangtianzhi","������","���ѧԺ","wangtianzhi@163.com"}};
		value=this.getValues(new Dao().getUserInfo("", "�û���"));
		table=new JTable(value,column);		
		table.addMouseListener(new TableListener());
		jsp=new JScrollPane();
		jsp.setPreferredSize(new Dimension(840,230));
//		jsp.add(table);
		jsp.setViewportView(table);
		this.add(jsp);
		
		jp1=new JPanel();
		jlName=new JLabel("��   ��:");
		jp1.add(jlName);
		jtfName=new JTextField(15);
		jtfName.setDocument(new MyDocument(20));
		jp1.add(jtfName);
		jlPasswd=new JLabel("          ��   ��:");
		jp1.add(jlPasswd);
		jtfPasswd=new JTextField(15);
		jtfPasswd.setDocument(new MyDocument(20));//�����20λ
		jtfPasswd.addKeyListener(new NumberListener());//ֻ������
		jp1.add(jtfPasswd);
		jlBirthday=new JLabel("          ��   ��:");
		jp1.add(jlBirthday);
		jtfBirthday=new JTextField(15);
		jtfBirthday.setEditable(false);
		jtfBirthday.addMouseListener(new CalendarListener());
		jp1.add(jtfBirthday);
		this.add(jp1);
		
		jp2=new JPanel();
		jlSex=new JLabel("��   ��:         ");
		jp2.add(jlSex);
		bg=new ButtonGroup();
		jrbMale=new JRadioButton("��");
		jp2.add(jrbMale);
		jrbFemale=new JRadioButton("Ů");
		jp2.add(jrbFemale);
		bg.add(jrbMale);
		bg.add(jrbFemale);
		jlQuestion=new JLabel("                           ��   ��:");
		jp2.add(jlQuestion);
		jtfQuestion=new JTextField(15);
		jtfQuestion.setDocument(new MyDocument(20));
		jp2.add(jtfQuestion);
		jlAnswer=new JLabel("         ��   ��:");
		jp2.add(jlAnswer);
		jtfAnswer=new JTextField(15);
		jtfAnswer.setDocument(new MyDocument(20));
		jp2.add(jtfAnswer);
		this.add(jp2);
		
		jp3=new JPanel();
		jlPurview=new JLabel("Ȩ   ��:");
		jp3.add(jlPurview);
		
		String[] purview=new String[vector.size()];
		for(int i=0;i<vector.size();i++){
			purview[i]=vector.get(i).getName();
		}
		jcbPurview=new JComboBox(purview);
		jcbPurview.setPreferredSize(new Dimension(170,25));
		jp3.add(jcbPurview);
		jlDept=new JLabel("         Ժ   ϵ:");
		jp3.add(jlDept);
		String[] dept={"���ѧԺ","�������ѧ�뼼��ѧԺ","ҽѧԺ","����ѧԺ","��ѧԺ","����ѧԺ","�����ѧԺ"};
		jcbDept=new JComboBox(dept);
		jcbDept.setPreferredSize(new Dimension(170,25));
		jp3.add(jcbDept);
		jlEmail=new JLabel("         ��   ��:");
		jp3.add(jlEmail);
		jtfEmail=new JTextField(15);
		jtfEmail.setDocument(new MyDocument(30));//�����30λ
		jp3.add(jtfEmail);
		this.add(jp3);
		
		jpButton=new JPanel();		
		jbUpdate=new JButton("��    ��");
		jbUpdate.addActionListener(this);
		jbDelete=new JButton("ɾ    ��");
		jbDelete.addActionListener(this);
		jpButton.add(jbUpdate);
		jpButton.add(jbDelete);
		this.add(jpButton);		
	}
	public String[][] getValues(Vector<UserBean> result){
		String [][]value=new String[result.size()][10];
		for(int i=0;i<result.size();i++){
			UserBean ub=result.get(i);
			value[i][0]=ub.getUser_id();
			value[i][1]=ub.getUser_name();
			value[i][2]=ub.getUser_passwd();
			String birthday=ub.getUser_birthday();
			if(birthday==null)
				birthday="";
			value[i][3]=birthday;
			if(ub.getUser_sex().equals("0"))
				value[i][4]="��";
			else 
				value[i][4]="Ů";
			String question=ub.getUser_question();
			if(question==null)
				question="";
			value[i][5]=question;
			String answer=ub.getUser_answer();
			if(answer==null)
				answer="";
			value[i][6]=answer;
			String purview=ub.getUser_purview_id();
			for(int j=0;j<vector.size();j++){
				if(vector.get(j).getId().equals(purview)){
					value[i][7]=vector.get(j).getName();
					break;
				}
			}
			
			String dept=ub.getUser_dept();
			if(dept==null)
				dept="";
			value[i][8]=dept;
			String email=ub.getUser_email();
			if(email==null)
				email="";
			value[i][9]=email;
		}
		return value;
	}
	public class TableListener extends MouseAdapter{
		public void mouseClicked(final MouseEvent e) {
			int selRow = table.getSelectedRow();
			//jtfSearch.setText(table.getValueAt(selRow, 0).toString().trim());
			jtfName.setText(table.getValueAt(selRow, 1).toString().trim());
			jtfPasswd.setText(table.getValueAt(selRow, 2).toString());
			jtfBirthday.setText(table.getValueAt(selRow, 3).toString());
			if(table.getValueAt(selRow, 4).toString().equals("��"))
				jrbMale.setSelected(true);
			else 
				jrbFemale.setSelected(true);
			jtfQuestion.setText(table.getValueAt(selRow, 5).toString());
			jtfAnswer.setText(table.getValueAt(selRow, 6).toString());
			jcbPurview.setSelectedItem(table.getValueAt(selRow, 7).toString());
			jcbDept.setSelectedItem(table.getValueAt(selRow, 8).toString());
			jtfEmail.setText(table.getValueAt(selRow, 9).toString());
	
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jbUpdate){
			String id=table.getValueAt(table.getSelectedRow(), 0).toString();
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
			new Dao().UpdateUser(ub);
			this.refresh();
				 
		}else if(arg0.getSource()==jbDelete){
			String id=table.getValueAt(table.getSelectedRow(), 0).toString();
			int borrowNum=new Dao().getBorrowNum(id);
			if(borrowNum>0){
				JOptionPane.showMessageDialog(null, "���û���δ��ͼ�飬����ɾ��!", "��ʾ", JOptionPane.WARNING_MESSAGE);
			}else{
				new Dao().deleteUser(id);
				this.refresh();
			}
		}else if(arg0.getSource()==jbSearch){
			searchWay=jcbSearch.getSelectedItem().toString();
			searchText=jtfSearch.getText();
			this.refresh();
		}
	}
	public void refresh(){
		String results[][]= getValues(new Dao().getUserInfo(searchText,searchWay));
		DefaultTableModel model=new DefaultTableModel();					
		table.setModel(model);
		model.setDataVector(results, column);
	}

}
