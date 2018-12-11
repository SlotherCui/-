package systemview;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class RememberRP extends JInternalFrame
{
	public JLabel r_input=new JLabel("������Ҫѧ����ѧ��");
	public JLabel r_id=new JLabel("ѧ��");
	public JLabel r_name=new JLabel("����");
	public JLabel p_id=new JLabel();
	public JLabel p_name=new JLabel();
	public JLabel s_RP=new JLabel("�������");
	public JTextField t_input=new JTextField(12);
	public JTextArea t_RP=new JTextArea(4,12);
	public JButton find=new JButton("����");
	public JButton remember=new JButton("ȷ��");
	public JButton reset=new JButton("����");
	public RememberRP()
	{
		super();
		setTitle("��¼ѧ���������");
		getContentPane().setLayout(null);
		setIconifiable(true);
		setClosable(true);
		setBounds(100,50,400,300);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		r_input.setBounds(10, 10, 150, 25);
		t_input.setBounds(160, 10, 100, 25);
		find.setBounds(310, 10, 70, 25);
		r_id.setBounds(10,40 , 50, 25);
		p_id.setBounds(60,40 , 120, 25);
		r_name.setBounds(200,40 ,70 ,25 );
		p_name.setBounds(290, 40, 70, 25);
		s_RP.setBounds(10, 80, 70, 25);
		t_RP.setBounds(100, 80,240 ,100 );
		remember.setBounds(70,200 , 70, 30);
		reset.setBounds(240,200 ,70 ,30 );
		getContentPane().add(r_input);
		getContentPane().add(t_input);
		getContentPane().add(find);
		getContentPane().add(r_id);
		getContentPane().add(p_id);
		getContentPane().add(r_name);
		getContentPane().add(p_name);
		getContentPane().add(s_RP);
		getContentPane().add(t_RP);
		getContentPane().add(remember);
		getContentPane().add(reset);
		remember.setEnabled(false);
		reset.setEnabled(false);
		find.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String f_id=t_input.getText();
				if(f_id.length()==0)
				{
					JOptionPane.showMessageDialog(null, "������ѧ��ѧ�ţ�","������ʾ",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				getMessage(f_id);
				remember.setEnabled(true);
				reset.setEnabled(true);
			}
		});
		remember.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int r=modifyRP();
				if(r<=0)
				{
					JOptionPane.showMessageDialog(null, "��¼�������ʧ�ܣ�","������ʾ",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "��¼��������ɹ���","������ʾ",JOptionPane.INFORMATION_MESSAGE);
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
	public void getMessage(String f_id)
	{
		try{
			String sql="select * from db_student where s_id='"+f_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next()){
			p_id.setText(set.getString("s_id"));
			p_name.setText(set.getString("s_name"));
			if(set.getString("s_RP").length()!=0)
			{
				t_RP.setText(set.getString("s_RP"));
			}}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Dao.close();
	}
	public int modifyRP()
	{
		int i=0;
		String RP=t_RP.getText();
		try{
			String sql="update db_student set s_RP='"+RP+"'where s_id='"+p_id.getText()+"'";
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
		t_RP.setText("");
	}

}
