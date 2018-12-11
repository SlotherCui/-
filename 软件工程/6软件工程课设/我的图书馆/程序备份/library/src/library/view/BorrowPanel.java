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
 * 借阅图书面板
 * @author wangtianzhi
 *
 */
public class BorrowPanel extends JPanel implements ActionListener{
	private int bookTime;//用户能借多久
	private int bookNum;//用户能借多少书
	
	JLabel jlUser,jlBook;
	JButton jbBorrow;
	JTextField jtfUser,jtfBook;
	public BorrowPanel(){		
		jlUser=new JLabel("用户号:");
		jtfUser=new JTextField(20);
		jtfUser.addKeyListener(new NumberListener());
		jlBook=new JLabel("书号:");
		jtfBook=new JTextField(20);
		jtfBook.addKeyListener(new NumberListener());
		jbBorrow=new JButton("借阅");
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
//            System.out.println("输入的是数字  "+m.group(1));
            List list=new Dao().getUserBorrowInfo(userId);
            if(list.size()==0){
            	message="此用户不存在!"+"\n";
            	JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.WARNING_MESSAGE);
            	return;
            }else{
            	bookNum=(Integer)list.get(1);
            	bookTime=(Integer)list.get(2);
            	message="用户名:"+list.get(0)+"\n";
            }
        } else {
        	message="用户号含有字母:"+userId+"\n";
        	JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int borrowingNum=new Dao().getBorrowNum(userId);
        boolean isOverTime=new Dao().isOvertime(userId);
        if(isOverTime){
        	//有超期的书
        	message+="有超期的书，不能借阅!";
        	JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
		String bookId=jtfBook.getText().trim();
		Matcher book=Pattern.compile("(\\d{"+bookId.length()+"})").matcher(bookId);
        if(book.matches()){
//            System.out.println("输入的是数字  "+book.group(1));            
            List list=new Dao().getbookBorrowInfo(Integer.parseInt(bookId));
            if(list.size()==0){
            	JOptionPane.showMessageDialog(null, "没有此书,您输入的书编号可能有误!");
            	return;
            }
            message+="书名:"+list.get(0)+"\n";
            if((list.get(1).equals("借出"))){
            	//此书已经借出，输入的书号有误
            	message+="此书已借出，您输入的书号有误吧!\n";
            	JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }else if(list.get(1).equals("被预约")){
            	message+="此书被预约,还未到馆";
            	JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }else if(list.get(1).equals("预约到馆")){
            	//此书被预约，若不是此用户预约的，不能借
            	int id=Integer.parseInt(bookId);
            	if(!new Dao().isUserOrdered(userId, id)){
            		//不是此用户预约的，不能借
            		message+="此书已被预约，不能借";
                	JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.WARNING_MESSAGE);
                    return;
            	}else{
            		//此书是此用户预约的 ，可以借
            		int response=JOptionPane.showConfirmDialog(null, message, "图书借阅确认", JOptionPane.YES_NO_OPTION);
            	    if(response==0){
            	    	//按下yes按钮
            	    	System.out.println("预约yes");
            	    	new Dao().clearOrdered(userId, id);
                		new Dao().borrow(userId, id, bookTime);
                		return;
            	    }else{
            	    	System.out.println("预约no");
            	    	//按下no按钮
            	    	return;
            	    }
            		
            	}
            }else if(borrowingNum>=bookNum){
            	//已经借满，不能再借
            	message+="已经借满不能再借！";
            	JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }else{
//            System.out.println("输入的含有字母" + bookId+"\n");
            message+="书编号含有字母:"+bookId;
        	JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //满足借阅的所有条件，且此书在馆（不是此用户预约的）,可以借阅图书
		int response=JOptionPane.showConfirmDialog(null, message, "图书借阅确认", JOptionPane.YES_NO_OPTION);
	    if(response==0){
//	    	System.out.println("按下了yes按钮");
	    	new Dao().borrow(userId, Integer.parseInt(bookId), bookTime);
	    }else if(response==1){
//	    	System.out.println("按下了no按钮");
	    }
	}
	

}
