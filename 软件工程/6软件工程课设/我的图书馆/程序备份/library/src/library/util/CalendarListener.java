package library.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

//���ڳ������ڵ��ı��򵥻���굯������ѡ��Ի���˫�����ڼ���������д���ı���
public class CalendarListener extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		new DateUI((JTextField)arg0.getSource());
	}
}
