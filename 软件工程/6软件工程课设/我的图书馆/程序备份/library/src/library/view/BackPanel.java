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
 * �������
 * @author wangtianzhi
 *
 */
public class BackPanel extends JPanel implements ActionListener{
//	final int punishforOneDay=1;//����ÿ��ͷ�1��
	JPanel jp,jp1;
	JLabel jl;
	JTextField jtf;
	JButton jb1,jb2,jb3,jb4;
	JTable table;
	TableModel model;
	Object[][] cells;
	JScrollPane scrollPane;
	Vector<Vector> vector;
	private String[] columnNames={"�����","����","ISBN","����","��������","Ӧ������","�ݲص�","ѡ��"};
	String userId;
	public BackPanel(){
		this.setLayout(new BorderLayout());
		jp=new JPanel();
		jl=new JLabel("�û���:");
		jtf=new JTextField(20);
		jtf.addKeyListener(new NumberListener());
		jp.add(jl);
		jp.add(jtf);
		jb1=new JButton("ȷ��");
		jb1.addActionListener(this);
		jp.add(jb1);	
		
		jp1=new JPanel();
		jb2=new JButton("�黹��ѡ�鼮");
		jb2.addActionListener(this);
		jb3=new JButton("��ʧ");
		jb3.addActionListener(this);
		jb4=new JButton("��");
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
		//����ȷ����ť
		if(e.getSource()==jb1){
			userId=jtf.getText().trim();
			List list=new Dao().backInfo(userId);
			if(list.size()>0){//�˶����н���
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
				JOptionPane.showMessageDialog(null, "û�д˶��߻�˶���û�н���", "��ʾ", JOptionPane.WARNING_MESSAGE);
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
				String punishMessage="���ڷ��\n";
				for(int i=0;i<model.getRowCount();i++){
					if((Boolean)model.getValueAt(i, 7)){//ѡ�е�Ҫ�黹����
						//�ж��Ƿ���
						selected.add((Integer)model.getValueAt(i, 1));
						removeLine.add(i);
						Date returnTime=(Date)model.getValueAt(i, 5);
//						System.out.println("returnTime:"+returnTime);						
//						int borrowTime=date.getDate()-java.sql.Timestamp.valueOf(model.getValueAt(i, 4).toString().trim()).getDate();						
						long daysBetween=(date.getTime()-returnTime.getTime()+1000000)/(3600*24*1000);						
						if(daysBetween>0){
							overtime=true;
						    punishMessage+=(model.getValueAt(i, 3)+":"+daysBetween+"��\n");
							money+=daysBetween*1;//����ÿ��ÿ�췣1ëǮ
							Durty durty=new Durty();
							durty.setBorrowId((Integer)model.getValueAt(i, 0));
							durty.setMoney(daysBetween*1);
							durty.setDescribtion("���ڷ���");
							list.add(durty);
//						System.out.println("daysBetween"+daysBetween);
						}
					}
				}
				if(selected.size()>0){//��ѡ��ĳЩ��					  					
					//�г��ڵ�
					if(overtime){
						punishMessage+=("�ܹ�����:"+money+"��");
						JOptionPane.showMessageDialog(null, punishMessage, "������Ϣ", JOptionPane.WARNING_MESSAGE);
					}
					new Dao().sendBack(selected,list);
					
					//ע�⣺Ҫ�Ӻ���ǰɾ
					for(int j=removeLine.size()-1;j>=0;j--){
//						System.out.println("size"+vector.size());
						vector.removeElementAt(removeLine.get(j));					
					}
					//���±��
					model=new ResultSetTableModel(vector,columnNames);
					table.setModel(model);				
				}else
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�黹����", "��ʾ", JOptionPane.WARNING_MESSAGE);				
			}
		}
	}

}
