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
import library.vo.*;
/**
 * ���ͼ�����
 * @author wangtianzhi
 *
 */
public class AddTypePanel extends JPanel implements ActionListener{
	JLabel jlTitle,jlId,jlName,jlDescribtion;
	JTextField jtfId,jtfName;
	JTextArea jta;
	JButton jbAdd,jbReset;
	JPanel jp0,jp1,jp2,jp3,jp4;
	JScrollPane jsp;
	public AddTypePanel(){
		jp0=new JPanel();
		jp0.setPreferredSize(new Dimension(700,100));
		jlTitle=new JLabel("���ͼ�������Ϣ");
		jlTitle.setFont(ToolFont.f4);
		jlTitle.setForeground(Color.BLUE);
		jp0.add(jlTitle);
		this.add(jp0);
		
		jp1=new JPanel();
		jp1.setPreferredSize(new Dimension(700,30));
		jlId=new JLabel("�� �� ��:");
		jtfId=new JTextField(25);
		jtfId.setDocument(new MyDocument(10));
//		jtfId.addKeyListener(new NumberListener());//����ſ��Է�����
		jp1.add(jlId);
		jp1.add(jtfId);
		this.add(jp1);
		
		jp2=new JPanel();
		jp2.setPreferredSize(new Dimension(700,30));
		jlName=new JLabel("�� �� ��:");
		jtfName=new JTextField(25);
		jp2.add(jlName);
		jp2.add(jtfName);
		this.add(jp2);
		
		jp3=new JPanel();
//		jp3.setPreferredSize(new Dimension(700,30));
		jlDescribtion=new JLabel("��   ��:");
		jsp=new JScrollPane();
		jsp.setPreferredSize(new Dimension(300,60));
		jta=new JTextArea();
		jsp.setViewportView(jta);
		jp3.add(jlDescribtion);
		jp3.add(jsp);
		this.add(jp3);
		
		jp4=new JPanel();
		jp4.setPreferredSize(new Dimension(700,30));
		jbAdd=new JButton("��   ��");
		jbAdd.addActionListener(this);
		jbReset=new JButton("��   ��");
		jbReset.addActionListener(this);
		jp4.add(jbAdd);
		jp4.add(jbReset);
		this.add(jp4);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jbAdd){//���ͼ������
			String id=jtfId.getText().trim();
			if(id.equals("")){
				JOptionPane.showMessageDialog(null, "ID�Ų���Ϊ��!");
				return;
			}
			String name=jtfName.getText().trim();
			if(name.equals("")){
				JOptionPane.showMessageDialog(null, "����������Ϊ��!");
				return;
			}
			String describtion=jta.getText();
			TypeBean type=new TypeBean();
			type.setId(id);
			type.setName(name);
			type.setDescribtion(describtion);
			boolean result=new Dao().addType(type);
			if(result){
				JOptionPane.showMessageDialog(null, "   ��ӳɹ�!");
				this.clear();
			}
			else 
				JOptionPane.showMessageDialog(null, "���ʧ�ܣ�����ID���ظ�!");
		}else if(arg0.getSource()==jbReset)
			this.clear();
	}
	public void clear(){
		jtfId.setText("");
		jtfName.setText("");
		jta.setText("");
	}

}
