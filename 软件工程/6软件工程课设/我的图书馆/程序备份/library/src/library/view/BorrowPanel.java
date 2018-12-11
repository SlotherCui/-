package library.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import library.db.*;
import library.util.*;
import java.util.List;
/**
 * ����ͼ�����
 * @author wangtianzhi
 *
 */
public class BorrowPanel extends JPanel implements ActionListener{
	private int bookTime;//�û��ܽ���
	private int bookNum;//�û��ܽ������
	
	JLabel jlUser,jlBook;
	JButton jbBorrow;
	JTextField jtfUser,jtfBook;
	public BorrowPanel(){		
		jlUser=new JLabel("�û���:");
		jtfUser=new JTextField(20);
		jtfUser.addKeyListener(new NumberListener());
		jlBook=new JLabel("���:");
		jtfBook=new JTextField(20);
		jtfBook.addKeyListener(new NumberListener());
		jbBorrow=new JButton("����");
		jbBorrow.addActionListener(this);
		this.add(jlUser);
		this.add(jtfUser);
		this.add(jlBook);
		this.add(jtfBook);
		this.add(jbBorrow);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String message="";
		boolean isError=false;
		String userId=jtfUser.getText().trim();
        Matcher m=Pattern.compile("(\\d{"+userId.length()+"})").matcher(userId);
        if(m.matches()){
//            System.out.println("�����������  "+m.group(1));
            List list=new Dao().getUserBorrowInfo(userId);
            if(list.size()==0){
            	message="���û�������!"+"\n";
            	JOptionPane.showMessageDialog(null, message, "��ʾ", JOptionPane.WARNING_MESSAGE);
            	return;
            }else{
            	bookNum=(Integer)list.get(1);
            	bookTime=(Integer)list.get(2);
            	message="�û���:"+list.get(0)+"\n";
            }
        } else {
        	message="�û��ź�����ĸ:"+userId+"\n";
        	JOptionPane.showMessageDialog(null, message, "��ʾ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int borrowingNum=new Dao().getBorrowNum(userId);
        boolean isOverTime=new Dao().isOvertime(userId);
        if(isOverTime){
        	//�г��ڵ���
        	message+="�г��ڵ��飬���ܽ���!";
        	JOptionPane.showMessageDialog(null, message, "��ʾ", JOptionPane.WARNING_MESSAGE);
            return;
        }
		String bookId=jtfBook.getText().trim();
		Matcher book=Pattern.compile("(\\d{"+bookId.length()+"})").matcher(bookId);
        if(book.matches()){
//            System.out.println("�����������  "+book.group(1));            
            List list=new Dao().getbookBorrowInfo(Integer.parseInt(bookId));
            if(list.size()==0){
            	JOptionPane.showMessageDialog(null, "û�д���,����������ſ�������!");
            	return;
            }
            message+="����:"+list.get(0)+"\n";
            if((list.get(1).equals("���"))){
            	//�����Ѿ������������������
            	message+="�����ѽ�������������������!\n";
            	JOptionPane.showMessageDialog(null, message, "��ʾ", JOptionPane.WARNING_MESSAGE);
                return;
            }else if(list.get(1).equals("��ԤԼ")){
            	message+="���鱻ԤԼ,��δ����";
            	JOptionPane.showMessageDialog(null, message, "��ʾ", JOptionPane.WARNING_MESSAGE);
                return;
            }else if(list.get(1).equals("ԤԼ����")){
            	//���鱻ԤԼ�������Ǵ��û�ԤԼ�ģ����ܽ�
            	int id=Integer.parseInt(bookId);
            	if(!new Dao().isUserOrdered(userId, id)){
            		//���Ǵ��û�ԤԼ�ģ����ܽ�
            		message+="�����ѱ�ԤԼ�����ܽ�";
                	JOptionPane.showMessageDialog(null, message, "��ʾ", JOptionPane.WARNING_MESSAGE);
                    return;
            	}else{
            		//�����Ǵ��û�ԤԼ�� �����Խ�
            		int response=JOptionPane.showConfirmDialog(null, message, "ͼ�����ȷ��", JOptionPane.YES_NO_OPTION);
            	    if(response==0){
            	    	//����yes��ť
            	    	System.out.println("ԤԼyes");
            	    	new Dao().clearOrdered(userId, id);
                		new Dao().borrow(userId, id, bookTime);
                		return;
            	    }else{
            	    	System.out.println("ԤԼno");
            	    	//����no��ť
            	    	return;
            	    }
            		
            	}
            }else if(borrowingNum>=bookNum){
            	//�Ѿ������������ٽ�
            	message+="�Ѿ����������ٽ裡";
            	JOptionPane.showMessageDialog(null, message, "��ʾ", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }else{
//            System.out.println("����ĺ�����ĸ" + bookId+"\n");
            message+="���ź�����ĸ:"+bookId;
        	JOptionPane.showMessageDialog(null, message, "��ʾ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //������ĵ������������Ҵ����ڹݣ����Ǵ��û�ԤԼ�ģ�,���Խ���ͼ��
		int response=JOptionPane.showConfirmDialog(null, message, "ͼ�����ȷ��", JOptionPane.YES_NO_OPTION);
	    if(response==0){
//	    	System.out.println("������yes��ť");
	    	new Dao().borrow(userId, Integer.parseInt(bookId), bookTime);
	    }else if(response==1){
//	    	System.out.println("������no��ť");
	    }
	}
	

}
