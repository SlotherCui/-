package library.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.*;

import library.vo.*;
import library.model.*;
import library.db.*;
import library.util.*;

/**
 * 还书面板
 * @author wangtianzhi
 *
 */
public class BackPanel extends JPanel implements ActionListener{
//	final int punishforOneDay=1;//超期每天惩罚1角
	JPanel jp,jp1;
	JLabel jl;
	JTextField jtf;
	JButton jb1,jb2,jb3,jb4;
	JTable table;
	TableModel model;
	Object[][] cells;
	JScrollPane scrollPane;
	Vector<Vector> vector;
	private String[] columnNames={"借书号","书编号","ISBN","书名","借阅日期","应还日期","馆藏地","选择"};
	String userId;
	public BackPanel(){
		this.setLayout(new BorderLayout());
		jp=new JPanel();
		jl=new JLabel("用户号:");
		jtf=new JTextField(20);
		jtf.addKeyListener(new NumberListener());
		jp.add(jl);
		jp.add(jtf);
		jb1=new JButton("确定");
		jb1.addActionListener(this);
		jp.add(jb1);	
		
		jp1=new JPanel();
		jb2=new JButton("归还所选书籍");
		jb2.addActionListener(this);
		jb3=new JButton("丢失");
		jb3.addActionListener(this);
		jb4=new JButton("损坏");
		jb4.addActionListener(this);		
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
		
		this.add(jp,BorderLayout.NORTH);
		this.add(jp1, BorderLayout.SOUTH);
		
		vector=new Vector<Vector>();
		scrollPane = new JScrollPane();
		table=new JTable();
		table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//单击确定按钮
		if(e.getSource()==jb1){
			userId=jtf.getText().trim();
			List list=new Dao().backInfo(userId);
			if(list.size()>0){//此读者有借书
				vector.clear();
				for(int i=0;i<list.size();i++){
					Back back=(Back)list.get(i);
					Vector vec=new Vector();
					vec.add(back.getBorrowId());
					vec.add(back.getBookId());
					vec.add(back.getBookIsbn());
					vec.add(back.getBookName());
					vec.add(back.getBorrowTime());
					vec.add(back.getReturnTime());
					vec.add(back.getLibraryName());
					vec.add(false);
					vector.add(vec);
				}
				
				model=new ResultSetTableModel(vector,columnNames);
				//model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				table.setCellSelectionEnabled(true);
				this.add(scrollPane, BorderLayout.CENTER);
			}else{
				JOptionPane.showMessageDialog(null, "没有此读者或此读者没有借书", "提示", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource()==jb2){
			if(model.getRowCount()>0){
				Date date=new Date();
				long money=0;
				boolean overtime=false;
				List<Integer> selected=new ArrayList<Integer>();
				selected.clear();
				List<Integer> removeLine=new ArrayList<Integer>();
				removeLine.clear();
				List<Durty> list=new ArrayList<Durty>();
				list.clear();
				String punishMessage="超期罚款：\n";
				for(int i=0;i<model.getRowCount();i++){
					if((Boolean)model.getValueAt(i, 7)){//选中的要归还的书
						//判断是否超期
						selected.add((Integer)model.getValueAt(i, 1));
						removeLine.add(i);
						Date returnTime=(Date)model.getValueAt(i, 5);
//						System.out.println("returnTime:"+returnTime);						
//						int borrowTime=date.getDate()-java.sql.Timestamp.valueOf(model.getValueAt(i, 4).toString().trim()).getDate();						
						long daysBetween=(date.getTime()-returnTime.getTime()+1000000)/(3600*24*1000);						
						if(daysBetween>0){
							overtime=true;
						    punishMessage+=(model.getValueAt(i, 3)+":"+daysBetween+"角\n");
							money+=daysBetween*1;//超期每本每天罚1毛钱
							Durty durty=new Durty();
							durty.setBorrowId((Integer)model.getValueAt(i, 0));
							durty.setMoney(daysBetween*1);
							durty.setDescribtion("超期罚款");
							list.add(durty);
//						System.out.println("daysBetween"+daysBetween);
						}
					}
				}
				if(selected.size()>0){//有选中某些书					  					
					//有超期的
					if(overtime){
						punishMessage+=("总共罚款:"+money+"角");
						JOptionPane.showMessageDialog(null, punishMessage, "罚款信息", JOptionPane.WARNING_MESSAGE);
					}
					new Dao().sendBack(selected,list);
					
					//注意：要从后往前删
					for(int j=removeLine.size()-1;j>=0;j--){
//						System.out.println("size"+vector.size());
						vector.removeElementAt(removeLine.get(j));					
					}
					//更新表格
					model=new ResultSetTableModel(vector,columnNames);
					table.setModel(model);				
				}else
					JOptionPane.showMessageDialog(null, "请选择要归还的书", "提示", JOptionPane.WARNING_MESSAGE);				
			}
		}
	}

}
