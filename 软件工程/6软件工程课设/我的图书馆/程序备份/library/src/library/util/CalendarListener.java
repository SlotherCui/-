package library.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

//当在出生日期的文本框单击鼠标弹出日历选择对话框，双击日期即将此日期写到文本框
public class CalendarListener extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		new DateUI((JTextField)arg0.getSource());
	}
}
