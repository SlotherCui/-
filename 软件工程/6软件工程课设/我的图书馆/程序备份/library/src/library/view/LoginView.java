package library.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import library.db.*;
//import com.jtattoo.plaf.mcwin.*;
import com.birosoft.liquid.*;;
/**
 * 用户登入界面
 * @author wangtianzhi
 *
 */
public class LoginView extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	private JPanel pan;
	private ImageIcon ii;
	private JLabel lab;
	private JTextField name;
	private JPasswordField password;
	private Choice choice;
	private JButton button;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 try {
			
//			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格
//			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java风格 
			//1.
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			
			//2.
//			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
//			LiquidLookAndFeel.setLiquidDecorations(true, "mac");
//			
			//3.
//			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
//			LiquidLookAndFeel.setLiquidDecorations(true);
			
			//4.
//			setDefaultLookAndFeelDecorated(true);
//			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			
			//SwingUtilities.updateComponentTreeUI(this);
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		new LoginView().setVisible(true);

	}
	public LoginView(){		
		Container ct = this.getContentPane();
		this.setLayout(null);
		pan=new JPanel();
		pan.setLayout(null);
		
		choice=new Choice();
		choice.add("图书管理员");
		choice.add("系统管理员");
		choice.setBounds(455, 270, 160, 30);
		pan.add(choice);
		
		name=new JTextField(15);
		name.setBorder(BorderFactory.createLoweredBevelBorder());
		name.setBounds(455, 320, 160, 30);
		//ct.add(name);
		pan.add(name);
		
		password=new JPasswordField(15);
		password.setBorder(BorderFactory.createLoweredBevelBorder());
		password.setBounds(455, 380, 160, 30);	
		//ct.add(password);
		pan.add(password);
		
		button=new JButton(new ImageIcon("image/button.JPG"));
		button.setBounds(460, 445, 94, 36);
		button.addActionListener(this);
		pan.add(button);//登入按钮
		
		
		ii=new ImageIcon("image/login.gif");
		lab=new JLabel(ii);
		lab.setBounds(0,0, ii.getIconWidth(), ii.getIconHeight());
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		this.setContentPane(pan);

		pan.setOpaque(false); 
		this.setSize(ii.getIconWidth(), ii.getIconHeight());
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-500,height/2-350);
		this.setTitle("图书信息管理系统");
		//this.setUndecorated(true);//去除边框方法
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button){
			
			String role=choice.getSelectedItem();
			String user=name.getText().trim();
			String passwd=new String(password.getPassword());
			if(role.equals("图书管理员")){
				System.out.println("图书管理员");
				if(new LoginCl().librarianLogin(user, passwd)){
					new LibrarianView(user).setVisible(true);
					this.setVisible(false);
				}
			
			}else{
				System.out.println("系统管理员");
				if(new LoginCl().adminLogin(user, passwd)){
					new AdminView().setVisible(true);
					this.setVisible(false);
				}
			}
			System.out.println("user:"+user);
			System.out.println("passwd:"+passwd);
		}
		
	}
	

}
