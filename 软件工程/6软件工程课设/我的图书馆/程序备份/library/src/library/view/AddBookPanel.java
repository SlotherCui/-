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
public class AddBookPanel extends JPanel implements ActionListener{
	JLabel jlIsbn,jlName,jlAuthor,jlTranslator,jlPrice,jlType,jlPress,jlLib,
	       jlDescribtion,jlIntime,jlPublishTime,jlWord,jlLanguage,jlTitle;
	JTextField jtfIsbn,jtfName,jtfAuthor,jtfTranslator,jtfPrice,jtfPress,jtfIntime,
	           jtfPublishTime,jtfWord;
	JTextArea jtaDescribtion;
	JComboBox jcbType,jcbLib,jcbLanguage;
	JPanel jp0,jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8;
	JButton jbAdd,jbReset;
	JScrollPane jsp;
	String nowdate;
	Vector<TypeBean> type1;
	Vector<LibraryBean> lib;
	public AddBookPanel(){
		jp0=new JPanel();
		jlTitle=new JLabel("��  ��  ͼ  ��");
		jlTitle.setFont(ToolFont.f4);
		jlTitle.setForeground(Color.BLUE);
		jp0.add(jlTitle);
		
		jp1=new JPanel();
		jlIsbn=new JLabel("I S B N:");
		jtfIsbn=new JTextField(15);
		jtfIsbn.setDocument(new MyDocument(13));
		jtfIsbn.addKeyListener(new NumberListener());
		jlName=new JLabel("               ��   ��:");
		jtfName=new JTextField(15);
		jp1.add(jlIsbn);
		jp1.add(jtfIsbn);
		jp1.add(jlName);
		jp1.add(jtfName);
		
		jp2=new JPanel();
		jlAuthor=new JLabel("��   ��:");
		jtfAuthor=new JTextField(15);
		jlTranslator=new JLabel("               ��   ��:");
		jtfTranslator=new JTextField(15);
		jp2.add(jlAuthor);
		jp2.add(jtfAuthor);
		jp2.add(jlTranslator);
		jp2.add(jtfTranslator);
		
		jp3=new JPanel();
		jlPrice=new JLabel("��   ��:");
		jtfPrice=new JTextField(15);
		jtfPrice.addKeyListener(new NumberListener());
		jlWord=new JLabel("               ��   ��:");
		jtfWord=new JTextField(15);
		jtfWord.addKeyListener(new NumberListener());
		jp3.add(jlPrice);
		jp3.add(jtfPrice);
		jp3.add(jlWord);
		jp3.add(jtfWord);
		
		jp4=new JPanel();
		jlPress=new JLabel("�� �� ��:");
		jtfPress=new JTextField(15);
		jlLanguage=new JLabel("               ��   ��:");
		String []lan={"����ͼ��","����ͼ��"};
		jcbLanguage=new JComboBox(lan);
		jcbLanguage.setPreferredSize(new Dimension(170,25));
		jp4.add(jlPress);
		jp4.add(jtfPress);
		jp4.add(jlLanguage);
		jp4.add(jcbLanguage);
		
		jp5=new JPanel();
		jlType=new JLabel("��   ��:");	
//		String []type={"��̼���","��ѧ","����ϵͳ","���ݿ�"};
		type1=new Dao().getAllType();
		String []type=new String[type1.size()];
		for(int i=0;i<type1.size();i++)
			type[i]=type1.get(i).getName();
		jcbType=new  JComboBox(type);
		jcbType.setPreferredSize(new Dimension(170,25));
		jlLib=new JLabel("           �� �� ��:");
//		String []lib={"���԰�ֹ�","����ֹ�","��ͻȪ�ֹ�"};
		lib=new Dao().getAllLibrary();
		String []library=new String[lib.size()];
		for(int i=0;i<lib.size();i++){
			library[i]=lib.get(i).getName();
		}
		jcbLib=new JComboBox(library);
		jcbLib.setPreferredSize(new Dimension(170,25));
		jp5.add(jlType);
		jp5.add(jcbType);
		jp5.add(jlLib);
		jp5.add(jcbLib);

		jp6=new JPanel();
		jlIntime=new JLabel("���ʱ��:");
		jtfIntime=new JTextField(15);
		Date date=new Date();
		SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
		nowdate=matter1.format(date);
		jtfIntime.setEditable(false);
		jtfIntime.addMouseListener(new CalendarListener());
		jtfIntime.setText(nowdate);
		jlPublishTime=new JLabel("          ����ʱ��:");
		jtfPublishTime=new JTextField(15);
		jtfPublishTime.setEditable(false);
		jtfPublishTime.addMouseListener(new CalendarListener());
		jp6.add(jlIntime);
		jp6.add(jtfIntime);
		jp6.add(jlPublishTime);
		jp6.add(jtfPublishTime);
		
		jp7=new JPanel();
		jlDescribtion=new JLabel("��   ��:");
		jsp=new JScrollPane();
		jsp.setPreferredSize(new Dimension(450,40));
//		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jtaDescribtion=new JTextArea();
		jtaDescribtion.setLineWrap(true);
		jsp.setViewportView(jtaDescribtion);
		jp7.add(jlDescribtion);
		jp7.add(jsp);
		
		jp8=new JPanel();
		jbAdd=new JButton("ȷ  ��");
		jbAdd.addActionListener(this);
		jbReset=new JButton("��  ��");
		jbReset.addActionListener(this);
		jp8.add(jbAdd);
		jp8.add(jbReset);
	
		this.setLayout(new GridLayout(10,1));
		this.add(jp0);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.add(jp8);
	}
	public void clear(){
		jtfIsbn.setText("");
		jtfName.setText("");
		jtfAuthor.setText("");
		jtfTranslator.setText("");
		jtfPrice.setText("");
		jtfPress.setText("");
		jtfIntime.setText(nowdate);
        jtfPublishTime.setText("");
        jtfWord.setText("");
        jtaDescribtion.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jbAdd){//����û���Ϣ
			String isbn=jtfIsbn.getText();
			if(isbn.length()!=13){
				JOptionPane.showMessageDialog(null, "ISBN����Ϊ13λ");
                return;
			}
			String name=jtfName.getText();
			if(name.equals("")){
				JOptionPane.showMessageDialog(null, "����������Ϊ��");
                return;
			}
			String author=jtfAuthor.getText();
			if(author.equals("")){
				JOptionPane.showMessageDialog(null, "���߲�����Ϊ��");
                return;
			}
			String translator=jtfTranslator.getText();
			String stringPrice=jtfPrice.getText();
			if(stringPrice.equals("")){
				JOptionPane.showMessageDialog(null, "�۸񲻿���Ϊ��");
                return;
			}
			int price=Integer.parseInt(stringPrice);
			String stringWord=jtfWord.getText();
			int word=0;
			if(!stringWord.equals("")){
				word=Integer.parseInt(jtfWord.getText());
			}
			String press=jtfPress.getText();
			if(press.equals("")){
				JOptionPane.showMessageDialog(null, "�����粻����Ϊ��");
                return;
			}
			String language=jcbLanguage.getSelectedItem().toString();
			String typeId=type1.get(jcbType.getSelectedIndex()).getId();
			String libId=lib.get(jcbLib.getSelectedIndex()).getId();
			System.out.println(libId+"   "+typeId);
			String intime=jtfIntime.getText();
			String publishTime=jtfPublishTime.getText();
			if(publishTime.equals("")){
				JOptionPane.showMessageDialog(null, "�������ڲ�����Ϊ��");
                return;
			}
			String describtion=jtaDescribtion.getText();
			BookBean bookbean=new BookBean();
			bookbean.setIsbn(isbn);
			bookbean.setName(name);
			bookbean.setAuthor(author);
			bookbean.setTranslator(translator);
			bookbean.setPrice(price);
			bookbean.setTypeId(typeId);
			bookbean.setPress(press);
			bookbean.setLibId(libId);
			bookbean.setDescribtion(describtion);
			bookbean.setInTime(intime);
			bookbean.setPublishTime(publishTime);
			bookbean.setWord(word);
			bookbean.setLanguage(language);
			boolean result=new Dao().addBook(bookbean);
			if(result){
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				this.clear();
			}
		}else if(arg0.getSource()==jbReset){//����
			this.clear();
		}
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.add(new AddBookPanel());
		jf.pack();
		jf.setLocation(0, 0);
		jf.setVisible(true);
	}

}
