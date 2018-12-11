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
import library.util.CalendarListener;
import library.util.DateUI;
import library.util.MyDocument;
import library.util.NumberListener;
import library.util.ToolFont;
import library.view.UpdateDelBookPanel.TableListener;
import library.vo.*;
/**
 * 更新删除管理员面板
 * @author wangtianzhi
 *
 */
public class UpdateDelLibrarianPanel extends JPanel implements ActionListener{
	JLabel jlId,jlName,jlPasswd,jlBirthday,jlSex,jlEmail,jlTel,jlWorkPlace,jlTitle,jlBlank;
    JTextField jtfId,jtfName,jtfBirthday,jtfPasswd,jtfEmail,jtfTel;
    JButton jbUpdate,jbDelete;
    JComboBox jcbWorkPlace;
    JPanel jp0,jp1,jp2,jp3,jp4;
    JRadioButton jrbMale,jrbFemale;
    ButtonGroup bg;
    JTable table;
	JScrollPane jsp;
	String [][]value;
	Vector<LibraryBean> lib;
	final String []column={"ID号","姓名","性别","出生日期","密码","Email","手机","工作地点"}; 
	public UpdateDelLibrarianPanel(){
		lib=new Dao().getAllLibrary();
		
		jp0=new JPanel();
		jlTitle=new JLabel("更新删除图书管理员信息");
		jlTitle.setFont(ToolFont.f4);
		jlTitle.setForeground(Color.BLUE);
		jp0.add(jlTitle);
		this.add(jp0);
		
//		String [][]value={{"2009001","wangwang","男","1990-01-01","362413","990534489@qq.com","15216400000","软件园分馆"}};
		value=this.getValues(new Dao().getAllLibrarian());
		table=new JTable(value,column);		
		table.addMouseListener(new TableListener());
		jsp=new JScrollPane();
		jsp.setPreferredSize(new Dimension(840,200));
		jsp.setViewportView(table);
		this.add(jsp);
		
		jp1=new JPanel();
		jlId=new JLabel("ID      号:");
		jtfId=new JTextField(15);
		jtfId.setEditable(false);//ID号不能修改
		jlName=new JLabel("          姓       名:");
		jtfName=new JTextField(15);
		jlSex=new JLabel("          性       别:                      ");
		jrbMale=new JRadioButton("男",true);
		jrbFemale=new JRadioButton("女",false);
		bg=new ButtonGroup();
		bg.add(jrbMale);
		bg.add(jrbFemale);
		jp1.add(jlId);
		jp1.add(jtfId);
		jp1.add(jlName);
		jp1.add(jtfName);
		jp1.add(jlSex);
		jp1.add(jrbMale);
		jp1.add(jrbFemale);		
		this.add(jp1);
		
		jp2=new JPanel();
		jlBirthday=new JLabel("出生日期:");
		jtfBirthday=new JTextField(15);
		jtfBirthday.setEditable(false);
		jtfBirthday.addMouseListener(new CalendarListener());
		jlPasswd=new JLabel("          密       码:");
		jtfPasswd=new JTextField(15);
		jtfPasswd.setDocument(new MyDocument(20));//密码最长20位
		jtfPasswd.addKeyListener(new NumberListener());//只能数字
		jlEmail=new JLabel("          邮        箱:");
		jtfEmail=new JTextField(15);
		jtfEmail.setDocument(new MyDocument(30));//邮箱最长30位
		jp2.add(jlBirthday);
		jp2.add(jtfBirthday);
		jp2.add(jlPasswd);
		jp2.add(jtfPasswd);
		jp2.add(jlEmail);
		jp2.add(jtfEmail);
		this.add(jp2);
		
		jp3=new JPanel();
		jlTel=new JLabel("手       机:");
		jtfTel=new JTextField(15);
		jtfTel.setDocument(new MyDocument(11));//手机号为11位
		jtfTel.addKeyListener(new NumberListener());
		jlWorkPlace=new JLabel("          工作地点:");
//		String []wp={"软件园分馆","文理分馆","工学分馆"};
		
		String []library=new String[lib.size()];
		for(int i=0;i<lib.size();i++){
			library[i]=lib.get(i).getName();
		}
		jcbWorkPlace=new JComboBox(library);
		jcbWorkPlace.setPreferredSize(new Dimension(170,25));
		jlBlank=new JLabel();
		jlBlank.setPreferredSize(new Dimension(257,25));
		jp3.add(jlTel);
		jp3.add(jtfTel);
		jp3.add(jlWorkPlace);
		jp3.add(jcbWorkPlace);
		jp3.add(jlBlank);
		this.add(jp3);
		
		jp4=new JPanel();
		jbUpdate=new JButton("更    新");
		jbUpdate.addActionListener(this);
		jbDelete=new JButton("删    除");
		jbDelete.addActionListener(this);
		jp4.add(jbUpdate);
		jp4.add(jbDelete);
		this.add(jp4);
	}
	public String[][] getValues(Vector<LibrarianBean> result){
		String [][]value=new String[result.size()][8];
		for(int i=0;i<result.size();i++){
			LibrarianBean librarian=result.get(i);
			value[i][0]=librarian.getLibrarian_id();
			value[i][1]=librarian.getLibrarian_name();
			if(librarian.getLibrarian_sex().equals("0"))
				value[i][2]="男";
			else 
				value[i][2]="女";
			value[i][3]=librarian.getLibrarian_bir();
			value[i][4]=librarian.getLibrarian_passwd();
			value[i][5]=librarian.getLibrarian_email();
			value[i][6]=librarian.getLibrarian_tel();
			String workplaceid=librarian.getLibrarian_work_place();
			for(int j=0;j<lib.size();j++){
				if(workplaceid.equals(lib.get(j).getId())){
					value[i][7]=lib.get(j).getName();
					break;
				}
			}
		}
		return value;
	}
	public void refresh(){
		String results[][]= getValues(new Dao().getAllLibrarian());
		DefaultTableModel model=new DefaultTableModel();					
		table.setModel(model);
		model.setDataVector(results, column);
	}
	public class TableListener extends MouseAdapter{
		public void mouseClicked(final MouseEvent e){
			int selRow = table.getSelectedRow();
			jtfId.setText(table.getValueAt(selRow, 0).toString());
			jtfName.setText(table.getValueAt(selRow, 1).toString());
			if(table.getValueAt(selRow, 2).toString().equals("男"))
				jrbMale.setSelected(true);
			else 
				jrbFemale.setSelected(true);
			jtfBirthday.setText(table.getValueAt(selRow, 3).toString());
			jtfPasswd.setText(table.getValueAt(selRow, 4).toString());
			jtfEmail.setText(table.getValueAt(selRow, 5).toString());
			jtfTel.setText(table.getValueAt(selRow, 6).toString());
			jcbWorkPlace.setSelectedItem(table.getValueAt(selRow, 7));
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jbUpdate){//更新图书管理员信息
			String id=jtfId.getText().trim();
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
			new Dao().updateLibrarian(librarian);
			this.refresh();
		}else if(arg0.getSource()==jbDelete){//删除选中的图书管理员信息
			String id=jtfId.getText().trim();
			new Dao().deleteLibrarian(id);
			this.refresh();
		}
	}

}
