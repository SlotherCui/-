package systemview;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
public class Addmajor extends JInternalFrame
{
	public JLabel i_id=new JLabel("ѧԺ��");
	public JLabel m_id=new JLabel("רҵ��");
	public JLabel m_name=new JLabel("רҵ����");
	public JTextField t_i_id=new JTextField(12);
	public JTextField t_m_id=new JTextField(12);
	public JTextField t_name=new JTextField(12);
	public JButton add=new JButton("�޸�");
	public JButton reset=new JButton("����");
	public Addmajor()
	{
		super();
		setTitle("�����רҵ");
		getContentPane().setLayout(null);
		setIconifiable(true);
		setClosable(true);
		setBounds(100,100,400,260);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		i_id.setBounds(10,10,70 ,25 );
		m_id.setBounds(10,50,70 ,25 );
		m_name.setBounds(10,90,70 ,25 );
		t_i_id.setBounds(100,10,150 ,25 );
		t_m_id.setBounds(100,50,150 ,25 );
		t_name.setBounds(100,90,150 ,25 );
		add.setBounds(130,160,70 ,30 );
		reset.setBounds(220,160,70 ,30 );
		getContentPane().add(i_id);
		getContentPane().add(m_id);
		getContentPane().add(m_name);
		getContentPane().add(t_i_id);
		getContentPane().add(t_m_id);
		getContentPane().add(t_name);
		getContentPane().add(add);
		getContentPane().add(reset);
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int r=addmajor();
				if(r<=0)
				{
					JOptionPane.showMessageDialog(null, "�����רҵʧ�ܣ�","������ʾ",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "�����רҵ�ɹ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				}
			}
		});
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				reset();
			}
		});
	}
	public int addmajor()
	{
		String I_id=t_i_id.getText();
		String M_id=t_m_id.getText();
		String name=t_name.getText();
		int i=0;
		if(I_id.length()==0||M_id.length()==0||name.length()==0)
		{
			JOptionPane.showMessageDialog(null, "��������ϢΪ�գ�","������ʾ",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		if(I_id.length()!=6)
		{
			JOptionPane.showMessageDialog(null, "ѧԺ�Ŵ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		if(M_id.length()!=6)
		{
			JOptionPane.showMessageDialog(null, "רҵ�Ŵ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		try{
			String sql="insert into db_major(i_id,m_id,m_name) values('"+I_id+"','"+M_id+"','"+name+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public void reset()
	{
		t_i_id.setText("");
		t_name.setText("");
		t_m_id.setText("");
	}

}
