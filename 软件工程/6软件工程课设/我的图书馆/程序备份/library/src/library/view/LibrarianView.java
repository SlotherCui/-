package library.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.imageio.*;
import java.io.*;
import java.sql.*;

import library.util.*;
import library.db.*;
import library.vo.*;

import com.birosoft.liquid.LiquidLookAndFeel;
/**
 * 图书管理员操作界面
 * @author wangtianzhi
 *
 */
public class LibrarianView extends JFrame implements MouseListener{

	String librarianId; 
	JPanel jp1,jp2,jp3,jp4;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtfUser,jtfBook,reviewUser;
	//ImageIcon ii;
	Image top,bottom,center;
	JButton borrow,revert;
	JTabbedPane jtp;
	JTable table;
	JLabel exit;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			setDefaultLookAndFeelDecorated(true);
//			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
//			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
//			LiquidLookAndFeel.setLiquidDecorations(true);
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//LiquidLookAndFeel.setLiquidDecorations(true);
		new LibrarianView("2009001").setVisible(true);

	}
	public LibrarianView(String id){
		this.librarianId=id;
		Container con=this.getContentPane();
		try {
			top=ImageIO.read(new File("image/bookshelf2.jpg"));
			bottom=ImageIO.read(new File("image/top.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ToolImage topPanel=new ToolImage(top);
//		topPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(800,100));
		FlowLayout flow=new FlowLayout();
		flow.setAlignment(FlowLayout.RIGHT);
		topPanel.setLayout(flow);
//		JPanel exitPanel=new JPanel();		
//		exitPanel.setLayout(new BorderLayout());		
		exit=new JLabel("EXIT");
		exit.addMouseListener(this);
		exit.setFont(ToolFont.f4);
		exit.setForeground(Color.BLUE);
//		exitPanel.add(exit,BorderLayout.EAST);
		topPanel.add(exit);
//		jl1=new JLabel(new ImageIcon("image/title.JPG"));
//		topPanel.add(jl1);
		ToolImage bottomPanel=new ToolImage(bottom);		
		jtp=new JTabbedPane();
		
		jp1=new BorrowPanel();
		jtp.add("借书功能", jp1);
		
		jp2=new BackPanel();
		jtp.add("还书功能", jp2);
		
		jp4=new LibrarianPanel(librarianId);
		jtp.add("个人信息维护", jp4);

		con.add(jtp,BorderLayout.CENTER);
		con.add(topPanel,BorderLayout.NORTH);
		con.add(bottomPanel,BorderLayout.SOUTH);
		
		this.setSize(1003, 613);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-500,height/2-350);
		this.setTitle("图书信息管理系统");
		//this.setUndecorated(true);//去除边框方法		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==exit)
			exit.setForeground(Color.BLACK);
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==exit)
			exit.setForeground(Color.BLUE);
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==exit){
			//退出，返回到登入界面
			new LoginView().setVisible(true);
			this.setVisible(false);
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
