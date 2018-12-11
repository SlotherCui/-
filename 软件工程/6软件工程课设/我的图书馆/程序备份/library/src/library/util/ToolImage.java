package library.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ToolImage extends JPanel {
	Image im;
	
	public ToolImage(Image im) {
//		try {
//			im = ImageIO.read(new File("image/bj.jpg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		this.im = im;
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(width,height);
	}
	
	public void paintComponent(Graphics g) {
	//public void paint(Graphics g) {
		super.paintComponent(g);
		//super.paint(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
