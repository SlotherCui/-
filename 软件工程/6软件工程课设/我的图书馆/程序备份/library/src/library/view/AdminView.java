package library.view;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import library.util.*;
import com.birosoft.liquid.LiquidLookAndFeel;

/**
 * 系统管理员操作界面
 * @author wangtianzhi
 *
 */
public class AdminView extends JFrame implements MouseListener{
	JPanel jp1,jp2,jp3,jp4;
	JLabel jl1,jlleft,line;
	JLabel user1,type1,book1,librarian1,user2,type2,book2,librarian2,user3,type3,
	       book3,librarian3,user4,type4,book4,librarian4;
	Image top,bottom,left;
	JLabel userUpdate,userAdd,typeUpdate,typeAdd,
	bookUpdate,bookAdd,librarianUpdate,librarianAdd;
	JLabel exit;
	JPanel userPanel,bookPanel,typePanel,librarianPanel;
	JPanel addUserPanel,addBookPanel,
	       addTypePanel,addLibrarianPanel;
	UpdateDelUserPanel updateDelUserPanel;
	UpdateDelBookPanel updateDelBookPanel;
	UpdateDelLibrarianPanel updateDelLibrarianPanel;
	UpdateDelTypePanel updateDelTypePanel;
	CardLayout card,card1;
	ImageIcon userIcon,bookIcon,librarianIcon,typeIcon;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			setDefaultLookAndFeelDecorated(true);
//			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//LiquidLookAndFeel.setLiquidDecorations(true);
		new AdminView().setVisible(true);

	}
	public AdminView(){
		Container con=this.getContentPane();
		try {
			top=ImageIO.read(new File("image/bookshelf2.jpg"));
			bottom=ImageIO.read(new File("image/top.jpg"));
			left=ImageIO.read(new File("image/p1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ToolImage leftPanel=new ToolImage(left);
		leftPanel.setLayout(new BorderLayout());
		//leftPanel.setLayout(new CardLayout());
		//leftPanel.setLayout(new GridLayout(12,1));
	    jp1=new JPanel();
	    card=new CardLayout();
	    jp1.setLayout(card);
	    
	    userIcon=new ImageIcon("image/user.jpg");
	    bookIcon=new ImageIcon("image/book.jpg");
	    librarianIcon=new ImageIcon("image/librarian.jpg");
	    typeIcon=new ImageIcon("image/type.jpg");
	    
		//第一张卡片 用户卡片
		userPanel=new ToolImage(left);
		userPanel.setLayout(new GridLayout(7,1));
		user1=new JLabel(userIcon);
//		userDelete=new JLabel("删 除 用 户",new ImageIcon("image/Icon_04.gif"),0);
//		userDelete.setFont(ToolFont.f2);
//		userDelete.setForeground(Color.GRAY);
//		userDelete.addMouseListener(this);
		userUpdate=new JLabel("更新删除用户",new ImageIcon("image/Icon_05.gif"),0);
		userUpdate.setFont(ToolFont.f2);
		userUpdate.setForeground(Color.GRAY);
		userUpdate.addMouseListener(this);
		userAdd=new JLabel("添 加 用 户",new ImageIcon("image/Icon_06.gif"),0);
		userAdd.setFont(ToolFont.f2);
		userAdd.setForeground(Color.GRAY);
		userAdd.addMouseListener(this);
		book1=new JLabel(bookIcon);
		book1.addMouseListener(this);
		type1=new JLabel(typeIcon);
		type1.addMouseListener(this);
		librarian1=new JLabel(librarianIcon);
		librarian1.addMouseListener(this);
		userPanel.add(user1);
//		userPanel.add(userDelete);
		userPanel.add(userUpdate);
		userPanel.add(userAdd);
		userPanel.add(book1);
		userPanel.add(type1);
		userPanel.add(librarian1);
		jp1.add(userPanel,"0");
		
		//第二张卡片（图书管理卡片）
		bookPanel=new ToolImage(left);
		bookPanel.setLayout(new GridLayout(7,1));
		user2=new JLabel(userIcon);
		user2.addMouseListener(this);
		book2=new JLabel(bookIcon);
//		bookDelete=new JLabel("删除图书",new ImageIcon("image/Icon_07.gif"),0);
//		bookDelete.setFont(ToolFont.f2);
//		bookDelete.setForeground(Color.GRAY);
//		bookDelete.addMouseListener(this);
		bookUpdate=new JLabel("更新删除图书",new ImageIcon("image/Icon_07.gif"),0);
		bookUpdate.setFont(ToolFont.f2);
		bookUpdate.setForeground(Color.GRAY);
		bookUpdate.addMouseListener(this);
		bookAdd=new JLabel("添 加 图 书",new ImageIcon("image/Icon_13.gif"),0);
		bookAdd.setFont(ToolFont.f2);
		bookAdd.setForeground(Color.GRAY);
		bookAdd.addMouseListener(this);
		type2=new JLabel(typeIcon);
		type2.addMouseListener(this);
		librarian2=new JLabel(librarianIcon);
		librarian2.addMouseListener(this);
		bookPanel.add(user2);
		bookPanel.add(book2);
//		bookPanel.add(bookDelete);
		bookPanel.add(bookUpdate);
		bookPanel.add(bookAdd);
		bookPanel.add(type2);
		bookPanel.add(librarian2);
		jp1.add(bookPanel, "1");
		
		//第三张卡片（图书类别管理）
		typePanel=new ToolImage(left);
		typePanel.setLayout(new GridLayout(7,1));
		user3=new JLabel(userIcon);
		user3.addMouseListener(this);
		book3=new JLabel(bookIcon);
		book3.addMouseListener(this);
		type3=new JLabel(typeIcon);
//		typeDelete=new JLabel("删除图书类型",new ImageIcon("image/Icon_15.gif"),0);
//		typeDelete.setFont(ToolFont.f2);
//		typeDelete.setForeground(Color.GRAY);
//		typeDelete.addMouseListener(this);
		typeUpdate=new JLabel("更新删除图书类型",new ImageIcon("image/Icon_20.gif"),0);
		typeUpdate.setFont(ToolFont.f2);
		typeUpdate.setForeground(Color.GRAY);
		typeUpdate.addMouseListener(this);
		typeAdd=new JLabel(" 添加图书类型 ",new ImageIcon("image/Icon_24.gif"),0);
		typeAdd.setFont(ToolFont.f2);
		typeAdd.setForeground(Color.GRAY);
		typeAdd.addMouseListener(this);
		librarian3=new JLabel(librarianIcon);
		librarian3.addMouseListener(this);
		typePanel.add(user3);
		typePanel.add(book3);
		typePanel.add(type3);
//		typePanel.add(typeDelete);
		typePanel.add(typeUpdate);
		typePanel.add(typeAdd);
		typePanel.add(librarian3);
		jp1.add(typePanel,"2");
		
		//第四张卡片（图书管理员管理）
		librarianPanel=new ToolImage(left);
		librarianPanel.setLayout(new GridLayout(7,1));
		user4=new JLabel(userIcon);
		user4.addMouseListener(this);
		book4=new JLabel(bookIcon);
		book4.addMouseListener(this);
		type4=new JLabel(typeIcon);
		type4.addMouseListener(this);
		librarian4=new JLabel(librarianIcon);
//		librarianDelete=new JLabel("删除图书管理员",new ImageIcon("image/Icon_26.gif"),0);
//		librarianDelete.setFont(ToolFont.f2);
//		librarianDelete.setForeground(Color.GRAY);
//		librarianDelete.addMouseListener(this);
		librarianUpdate=new JLabel("更新删除图书管理员",new ImageIcon("image/Icon_32.gif"),0);
		librarianUpdate.setFont(ToolFont.f2);
		librarianUpdate.setForeground(Color.GRAY);
		librarianUpdate.addMouseListener(this);
		librarianAdd=new JLabel("  添加图书管理员  ",new ImageIcon("image/Icon_37.gif"),0);
		librarianAdd.setFont(ToolFont.f2);
		librarianAdd.setForeground(Color.GRAY);
		librarianAdd.addMouseListener(this);
		librarianPanel.add(user4);
		librarianPanel.add(book4);
		librarianPanel.add(type4);
		librarianPanel.add(librarian4);
//		librarianPanel.add(librarianDelete);
		librarianPanel.add(librarianUpdate);
		librarianPanel.add(librarianAdd);
		jp1.add(librarianPanel,"3");
		
		//line=new JLabel(new ImageIcon("image/line.gif"));
		
		//leftPanel.add(line,BorderLayout.EAST);
		leftPanel.add(jp1, BorderLayout.NORTH);
		leftPanel.setOpaque(false);

		ToolImage topPanel=new ToolImage(top);
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
		//topPanel.add(jl1);
		ToolImage bottomPanel=new ToolImage(bottom);
		
//		jlleft=new JLabel();
		jp4=new JPanel();
		card1=new CardLayout();
		jp4.setLayout(card1);
		addUserPanel=new AddUserPanel();
		jp4.add(addUserPanel,"0");
		updateDelUserPanel=new UpdateDelUserPanel();
		jp4.add(updateDelUserPanel, "1");
		addBookPanel=new AddBookPanel();
		jp4.add(addBookPanel, "2");
		updateDelBookPanel=new UpdateDelBookPanel();
		jp4.add(updateDelBookPanel,"3");
		addTypePanel=new AddTypePanel();
		jp4.add(addTypePanel,"4");
		updateDelTypePanel=new UpdateDelTypePanel();
		jp4.add(updateDelTypePanel,"5");
		addLibrarianPanel=new AddLibrarianPanel();
		jp4.add(addLibrarianPanel,"6");
		updateDelLibrarianPanel=new UpdateDelLibrarianPanel();
		jp4.add(updateDelLibrarianPanel, "7");
		con.add(topPanel,BorderLayout.NORTH);
		con.add(bottomPanel,BorderLayout.SOUTH);
		con.add(leftPanel,BorderLayout.WEST);
		con.add(jp4, BorderLayout.CENTER);
		
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
		if(arg0.getSource()==userAdd){
			card1.show(jp4, "0");
		}else if(arg0.getSource()==userUpdate){
			updateDelUserPanel.refresh();
			card1.show(jp4, "1");
		}else if(arg0.getSource()==bookAdd){
			card1.show(jp4, "2");
		}else if(arg0.getSource()==bookUpdate){
			updateDelBookPanel.refresh();
			card1.show(jp4, "3");
		}
		else if(arg0.getSource()==typeAdd)
			card1.show(jp4, "4");
		else if(arg0.getSource()==typeUpdate){
			updateDelTypePanel.refresh();
			card1.show(jp4, "5");
		}
		else if(arg0.getSource()==librarianAdd){
			card1.show(jp4, "6");
		}
		else if(arg0.getSource()==librarianUpdate){
			updateDelLibrarianPanel.refresh();
			card1.show(jp4, "7");
		}
			
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==userUpdate)
			userUpdate.setForeground(null);
		else if(e.getSource()==userAdd)
			userAdd.setForeground(null);
//		else if(e.getSource()==bookDelete)
//			bookDelete.setForeground(null);
		else if(e.getSource()==bookUpdate)
			bookUpdate.setForeground(null);
		else if(e.getSource()==bookAdd)
			bookAdd.setForeground(null);
//		else if(e.getSource()==typeDelete)
//			typeDelete.setForeground(null);
		else if(e.getSource()==typeUpdate)
			typeUpdate.setForeground(null);
		else if(e.getSource()==typeAdd)
			typeAdd.setForeground(null);
//		else if(e.getSource()==librarianDelete)
//			librarianDelete.setForeground(null);
		else if(e.getSource()==librarianUpdate)
			librarianUpdate.setForeground(null);
		else if(e.getSource()==librarianAdd)
			librarianAdd.setForeground(null);
		else if(e.getSource()==exit)
			exit.setForeground(Color.BLACK);
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==userUpdate)
			userUpdate.setForeground(Color.GRAY);
		else if(e.getSource()==userAdd)
			userAdd.setForeground(Color.GRAY);
//		else if(e.getSource()==bookDelete)
//			bookDelete.setForeground(Color.GRAY);
		else if(e.getSource()==bookUpdate)
			bookUpdate.setForeground(Color.GRAY);
		else if(e.getSource()==bookAdd)
			bookAdd.setForeground(Color.GRAY);
//		else if(e.getSource()==typeDelete)
//			typeDelete.setForeground(Color.GRAY);
		else if(e.getSource()==typeUpdate)
			typeUpdate.setForeground(Color.GRAY);
		else if(e.getSource()==typeAdd)
			typeAdd.setForeground(Color.GRAY);
//		else if(e.getSource()==librarianDelete)
//			librarianDelete.setForeground(Color.GRAY);
		else if(e.getSource()==librarianUpdate)
			librarianUpdate.setForeground(Color.GRAY);
		else if(e.getSource()==librarianAdd)
			librarianAdd.setForeground(Color.GRAY);
		else if(e.getSource()==exit)
			exit.setForeground(Color.BLUE);
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==book1||arg0.getSource()==book3||arg0.getSource()==book4){
			card.show(jp1, "1");
		}
		else if(arg0.getSource()==user2||arg0.getSource()==user3||arg0.getSource()==user4){
			card.show(jp1, "0");
		}
		else if(arg0.getSource()==type1||arg0.getSource()==type2||arg0.getSource()==type4){
			card.show(jp1, "2");
		}else if(arg0.getSource()==librarian1||arg0.getSource()==librarian2||arg0.getSource()==librarian3){
			card.show(jp1, "3");
		}
		else if(arg0.getSource()==exit){
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
	


