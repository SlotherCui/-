package library.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberListener extends KeyAdapter{
	public void keyTyped(KeyEvent e) {
		String numStr="0123456789"+(char)8;
		if(numStr.indexOf(e.getKeyChar())<0){
			e.consume();
		}
	}

}
