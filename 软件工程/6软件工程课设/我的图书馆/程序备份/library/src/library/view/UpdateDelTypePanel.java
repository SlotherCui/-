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
import library.view.UpdateDelBookPanel.TableListener;
import library.vo.*;
/**
 * ����ɾ��ͼ������
 * @author wangtianzhi
 *
 */
public class UpdateDelTypePanel extends JPanel implements ActionListener{
	JLabel jlTitle,jlId,jlName,jlDescribtion;
	JTextField jtfId,jtfName;
	JTextArea jta;
	JButton jbUpdate,jbDelete;
	JPanel jp0,jp1,jp2,jp3;
	JTable table;
	JScrollPane jspTable,jspDescribtion;
	String column[]={"�����","����","����"};
	String [][]value;
	public UpdateDelTypePanel(){
		jp0=new JPanel();
		jlTitle=new JLabel("����ɾ��ͼ�������Ϣ");
		jlTitle.setFont(ToolFont.f4);
		jlTitle.setForeground(Color.BLUE);
		jp0.add(jlTitle);
		this.add(jp0);
		
//		String value[][]={{"001","�������","��������������յ�һ�ſ�"}};
		value=this.getValues(new Dao().getAllType());
		table=new JTable(value,column);		
		table.addMouseListener(new TableListener());
		jspTable=new JScrollPane();
		jspTable.setPreferredSize(new Dimension(840,250));
		jspTable.setViewportView(table);
		this.add(jspTable);
		
		jp1=new JPanel();
		jlId=new JLabel("�� �� ��:");
		jtfId=new JTextField(15);
		jtfId.setEditable(false);
		jtfId.setDocument(new MyDocument(10));//�10λ
		jlName=new JLabel("                    �� �� ��:");
		jtfName=new JTextField(25);
		jp1.add(jlId);
		jp1.add(jtfId);
		jp1.add(jlName);
		jp1.add(jtfName);
		this.add(jp1);
		
		jp2=new JPanel();
		jlDescribtion=new JLabel("��  ��:");
		jspDescribtion=new JScrollPane();
		jspDescribtion.setPreferredSize(new Dimension(620,60));
		jta=new JTextArea();
		jspDescribtion.setViewportView(jta);
		jp2.add(jlDescribtion);
		jp2.add(jspDescribtion);
		this.add(jp2);
		
		jp3=new JPanel();
		jbUpdate=new JButton("��        ��");
		jbUpdate.addActionListener(this);
		jbDelete=new JButton("ɾ        ��");
		jbDelete.addActionListener(this);
		jp3.add(jbUpdate);
		jp3.add(jbDelete);
		this.add(jp3);
	}
	public String[][] getValues(Vector<TypeBean> result){
		String [][]value=new String[result.size()][3];
		for(int i=0;i<result.size();i++){
			TypeBean type=result.get(i);
			value[i][0]=type.getId();
			value[i][1]=type.getName();
			value[i][2]=type.getDescribtion();
		}
		return value;
	}
	public class TableListener extends MouseAdapter{
		public void mouseClicked(final MouseEvent e){
			int selRow = table.getSelectedRow();
			jtfId.setText(table.getValueAt(selRow, 0).toString());
			jtfName.setText(table.getValueAt(selRow, 1).toString());
			jta.setText(table.getValueAt(selRow, 2).toString());
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jbUpdate){
			String id=jtfId.getText().trim();
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
			new Dao().updateType(type);
			this.refresh();
		}else if(arg0.getSource()==jbDelete){
			String id=jtfId.getText().trim();
			int response=JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��ID��Ϊ"+id+"��ͼ��������ɾ��ʱ�����͵�ͼ��Ҳ��ȫ��ɾ��!", "ɾ��ͼ������ȷ��", JOptionPane.YES_NO_OPTION);
		    if(response==0){
//		    	System.out.println("������yes��ť");
		    	new Dao().deleteType(id);
		    	this.refresh();
		    }else if(response==1){
//		    	System.out.println("������no��ť");
		    }
		}
	}
	public void refresh(){
		String results[][]= getValues(new Dao().getAllType());
		DefaultTableModel model=new DefaultTableModel();					
		table.setModel(model);
		model.setDataVector(results, column);
	}

}
