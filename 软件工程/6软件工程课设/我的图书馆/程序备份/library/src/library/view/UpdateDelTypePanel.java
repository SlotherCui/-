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
 * 更新删除图书类型
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
	String column[]={"分类号","类名","描述"};
	String [][]value;
	public UpdateDelTypePanel(){
		jp0=new JPanel();
		jlTitle=new JLabel("更新删除图书类别信息");
		jlTitle.setFont(ToolFont.f4);
		jlTitle.setForeground(Color.BLUE);
		jp0.add(jlTitle);
		this.add(jp0);
		
//		String value[][]={{"001","软件工程","软件开发必须掌握的一门课"}};
		value=this.getValues(new Dao().getAllType());
		table=new JTable(value,column);		
		table.addMouseListener(new TableListener());
		jspTable=new JScrollPane();
		jspTable.setPreferredSize(new Dimension(840,250));
		jspTable.setViewportView(table);
		this.add(jspTable);
		
		jp1=new JPanel();
		jlId=new JLabel("分 类 号:");
		jtfId=new JTextField(15);
		jtfId.setEditable(false);
		jtfId.setDocument(new MyDocument(10));//最长10位
		jlName=new JLabel("                    分 类 名:");
		jtfName=new JTextField(25);
		jp1.add(jlId);
		jp1.add(jtfId);
		jp1.add(jlName);
		jp1.add(jtfName);
		this.add(jp1);
		
		jp2=new JPanel();
		jlDescribtion=new JLabel("描  述:");
		jspDescribtion=new JScrollPane();
		jspDescribtion.setPreferredSize(new Dimension(620,60));
		jta=new JTextArea();
		jspDescribtion.setViewportView(jta);
		jp2.add(jlDescribtion);
		jp2.add(jspDescribtion);
		this.add(jp2);
		
		jp3=new JPanel();
		jbUpdate=new JButton("更        新");
		jbUpdate.addActionListener(this);
		jbDelete=new JButton("删        除");
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
				JOptionPane.showMessageDialog(null, "分类名不能为空!");
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
			int response=JOptionPane.showConfirmDialog(null, "确定要删除ID号为"+id+"的图书类型吗？删除时此类型的图书也将全部删除!", "删除图书类型确认", JOptionPane.YES_NO_OPTION);
		    if(response==0){
//		    	System.out.println("按下了yes按钮");
		    	new Dao().deleteType(id);
		    	this.refresh();
		    }else if(response==1){
//		    	System.out.println("按下了no按钮");
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
