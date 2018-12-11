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
import library.view.UpdateDelUserPanel.TableListener;
import library.vo.*;
/**
 * 更新或删除图书面板
 * @author wangtianzhi
 *
 */
public class UpdateDelBookPanel extends JPanel implements ActionListener{
	JLabel jlIsbn,jlName,jlAuthor,jlTranslator,jlPrice,jlType,jlPress,jlLib,
           jlDescribtion,jlIntime,jlPublishTime,jlWord,jlLanguage,jlState;
	JTextField jtfIsbn,jtfName,jtfAuthor,jtfTranslator,jtfPrice,jtfPress,jtfIntime,
    jtfPublishTime,jtfWord,jtfSearch,jtfDescribtion;
	JComboBox jcbType,jcbLib,jcbLanguage,jcbSearch,jcbState;
	JButton jbSearch,jbUpdate,jbDelete;
	JTable table;
	JScrollPane jsp;
	JPanel jpSearch,jp1,jp2,jp3,jp4,jp5,jp6;
	String []column={"书编号","ISBN","书名","作者","译者","价格","类型","出版社","馆藏地",
			"入库时间","出版时间","字数","语言","状态","描述"};
	Vector<TypeBean> type1;
	Vector<LibraryBean> lib;
	Object value[][];
	String searchWay,searchText;
	public UpdateDelBookPanel(){
		searchWay="书名";
		searchText="";
		type1=new Dao().getAllType();
		lib=new Dao().getAllLibrary();
		
		jpSearch=new JPanel();
		String []searchWay={"书名","ISBN","书编号"};
		jcbSearch=new JComboBox(searchWay);
		jtfSearch=new JTextField(20);
		jbSearch=new JButton("搜  索");
		jbSearch.addActionListener(this);
		jpSearch.add(jcbSearch);
		jpSearch.add(jtfSearch);
		jpSearch.add(jbSearch);
		this.add(jpSearch);
		
//		String [][]value={{"1","9787111213826","java编程思想","Bruce Eckel","陈昊鹏","108","1001","机械工业出版社","001","2011-01-01","2007-06-01","1000","中文图书","借出","java经典书籍"}};
		value=this.getValues(new Dao().getBookInfo("", "书名"));
		table=new JTable(value,column);		
		table.addMouseListener(new TableListener());
		jsp=new JScrollPane();
		jsp.setPreferredSize(new Dimension(840,200));
		jsp.setViewportView(table);
		this.add(jsp);
		
		jp1=new JPanel();
		jlIsbn=new JLabel("I S B N:");
		jtfIsbn=new JTextField(15);
		jtfIsbn.setDocument(new MyDocument(13));
		jtfIsbn.addKeyListener(new NumberListener());
		jlName=new JLabel("     书     名:");
		jtfName=new JTextField(15);
		jlWord=new JLabel("     字        数:");
		jtfWord=new JTextField(15);
		jtfWord.addKeyListener(new NumberListener());
		jp1.add(jlIsbn);
		jp1.add(jtfIsbn);
		jp1.add(jlName);
		jp1.add(jtfName);
		jp1.add(jlWord);
		jp1.add(jtfWord);
		this.add(jp1);
		
		jp2=new JPanel();
		jlAuthor=new JLabel("作     者:");
		jtfAuthor=new JTextField(15);
		jlTranslator=new JLabel("     译     者:");
		jtfTranslator=new JTextField(15);
		jlPrice=new JLabel("     价        格:");
		jtfPrice=new JTextField(15);
		jtfPrice.addKeyListener(new NumberListener());
		jp2.add(jlAuthor);
		jp2.add(jtfAuthor);
		jp2.add(jlTranslator);
		jp2.add(jtfTranslator);
		jp2.add(jlPrice);
		jp2.add(jtfPrice);
		this.add(jp2);
		
		jp3=new JPanel();
		jlType=new JLabel("类     型:");
//		String []type={"软件开发","操作系统","计算机体系结构","英语"};
		
		String []type=new String[type1.size()];
		for(int i=0;i<type1.size();i++)
			type[i]=type1.get(i).getName();
		jcbType=new JComboBox(type);
		jcbType.setPreferredSize(new Dimension(170,25));
		jlLib=new JLabel("     馆 藏 地:");
//		String []lib={"软件园分馆","文理分馆","工学分馆","兴隆山分馆"};
		
		String []library=new String[lib.size()];
		for(int i=0;i<lib.size();i++){
			library[i]=lib.get(i).getName();
		}
		jcbLib=new JComboBox(library);
		jcbLib.setPreferredSize(new Dimension(170,25));
		jlLanguage=new JLabel("     语        言:");
		String []lan={"中文图书","西文图书"};
		jcbLanguage=new JComboBox(lan);
		jcbLanguage.setPreferredSize(new Dimension(170,25));
		jp3.add(jlType);
		jp3.add(jcbType);
		jp3.add(jlLib);
		jp3.add(jcbLib);
		jp3.add(jlLanguage);
		jp3.add(jcbLanguage);
		this.add(jp3);
		
		jp4=new JPanel();
		jlPress=new JLabel("出版社:");
		jtfPress=new JTextField(15);
		jlIntime=new JLabel("     入库时间:");
		jtfIntime=new JTextField(15);
		jtfIntime.setEditable(false);
		jtfIntime.addMouseListener(new CalendarListener());
		jlPublishTime=new JLabel("     出版时间:");
		jtfPublishTime=new JTextField(15);
		jtfPublishTime.setEditable(false);
		jtfPublishTime.addMouseListener(new CalendarListener());
		jp4.add(jlPress);
		jp4.add(jtfPress);
		jp4.add(jlIntime);
		jp4.add(jtfIntime);
		jp4.add(jlPublishTime);
		jp4.add(jtfPublishTime);
		this.add(jp4);
		
		jp5=new JPanel();
		jlState=new JLabel("状     态:");
		String state[]={"在馆","借出","被预约","预约到馆"};
		jcbState=new JComboBox(state);
		jcbState.setPreferredSize(new Dimension(170,25));
		jlDescribtion=new JLabel("     描     述:");
		jtfDescribtion=new JTextField(38);
		jp5.add(jlState);
		jp5.add(jcbState);
		jp5.add(jlDescribtion);
		jp5.add(jtfDescribtion);
		this.add(jp5);
		
		jp6=new JPanel();
		jbUpdate=new JButton("更         新");
		jbUpdate.addActionListener(this);
		jbDelete=new JButton("删         除");
		jbDelete.addActionListener(this);
		jp6.add(jbUpdate);
		jp6.add(jbDelete);
		this.add(jp6);
		
	}
	public Object[][] getValues(Vector<BookBean> result){
		Object [][]value=new Object[result.size()][15];
		for(int i=0;i<result.size();i++){
			BookBean bb=result.get(i);
			value[i][0]=bb.getId();
			value[i][1]=bb.getIsbn();
			value[i][2]=bb.getName();
			value[i][3]=bb.getAuthor();
			String trans=bb.getTranslator();
			if(trans==null)
				trans="";
			value[i][4]=trans;
			value[i][5]=bb.getPrice();
			String typeid=bb.getTypeId();
			for(int j=0;j<type1.size();j++){
				if(type1.get(j).getId().equals(typeid)){
					value[i][6]=type1.get(j).getName();
					break;
				}
					
			}
			value[i][7]=bb.getPress();
			String libid=bb.getLibId();
			for(int j=0;j<lib.size();j++){
				if(lib.get(j).getId().equals(libid)){
					value[i][8]=lib.get(j).getName();
					break;
				}
			}
			value[i][9]=bb.getInTime();
			value[i][10]=bb.getPublishTime();
			value[i][11]=bb.getWord();
			value[i][12]=bb.getLanguage();
			value[i][13]=bb.getState();
			value[i][14]=bb.getDescribtion();
		}
		return value;
	}
	/**
	 * 表格监听器，当选中一行，将其信息显示在下面的文本框中
	 * @author wangtianzhi
	 *
	 */
	public class TableListener extends MouseAdapter{
		public void mouseClicked(final MouseEvent e){
			int selRow = table.getSelectedRow();
			jtfIsbn.setText(table.getValueAt(selRow, 1).toString().trim());
			jtfName.setText(table.getValueAt(selRow, 2).toString().trim());
			jtfAuthor.setText(table.getValueAt(selRow, 3).toString().trim());
			jtfTranslator.setText(table.getValueAt(selRow, 4).toString().trim());
			jtfPrice.setText(table.getValueAt(selRow, 5).toString().trim());
			jcbType.setSelectedItem(table.getValueAt(selRow, 6).toString().trim());
			jtfPress.setText(table.getValueAt(selRow, 7).toString().trim());
			jcbLib.setSelectedItem(table.getValueAt(selRow, 8).toString().trim());
			jtfIntime.setText(table.getValueAt(selRow, 9).toString());
			jtfPublishTime.setText(table.getValueAt(selRow, 10).toString().trim());
			jtfWord.setText(table.getValueAt(selRow, 11).toString().trim());
			jcbLanguage.setSelectedItem(table.getValueAt(selRow, 12).toString());
			jcbState.setSelectedItem(table.getValueAt(selRow, 13).toString());
			jtfDescribtion.setText(table.getValueAt(selRow, 14).toString());
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jbSearch){
			searchWay=jcbSearch.getSelectedItem().toString();
			searchText=jtfSearch.getText();
			this.refresh();
		}else if(arg0.getSource()==jbUpdate){
			String isbn=jtfIsbn.getText();
			if(isbn.length()!=13){
				JOptionPane.showMessageDialog(null, "ISBN必须为13位");
                return;
			}
			String name=jtfName.getText();
			if(name.equals("")){
				JOptionPane.showMessageDialog(null, "书名不可以为空");
                return;
			}
			String author=jtfAuthor.getText();
			if(author.equals("")){
				JOptionPane.showMessageDialog(null, "作者不可以为空");
                return;
			}
			String translator=jtfTranslator.getText();
			String stringPrice=jtfPrice.getText();
			if(stringPrice.equals("")){
				JOptionPane.showMessageDialog(null, "价格不可以为空");
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
				JOptionPane.showMessageDialog(null, "出版社不可以为空");
                return;
			}
			String language=jcbLanguage.getSelectedItem().toString();
			String typeId=type1.get(jcbType.getSelectedIndex()).getId();
			String libId=lib.get(jcbLib.getSelectedIndex()).getId();
			String intime=jtfIntime.getText();
			String publishTime=jtfPublishTime.getText();
//			if(publishTime.equals("")){
//				JOptionPane.showMessageDialog(null, "出版日期不可以为空");
//                return;
//			}
			String describtion=jtfDescribtion.getText();
			int id=(Integer)table.getValueAt(table.getSelectedRow(), 0);
			String state=jcbState.getSelectedItem().toString();
			BookBean bookbean=new BookBean();
			bookbean.setId(id);
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
			bookbean.setState(state);
			new Dao().updateBook(bookbean);
			this.refresh();
		}else if(arg0.getSource()==jbDelete){
			new Dao().deleteBook(table.getValueAt(table.getSelectedRow(), 0).toString());
		    this.refresh();
		}
	}
	//刷新表格内容
	public void refresh(){
		Object results[][]= getValues(new Dao().getBookInfo(searchText,searchWay));
		DefaultTableModel model=new DefaultTableModel();					
		table.setModel(model);
		model.setDataVector(results, column);
	}

}
