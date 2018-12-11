package systemview;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class AdministratorInterface extends JFrame
{
	private JTabbedPane tabel=new JTabbedPane();
	private JPanel xinxi=new JPanel();
	private LessonPanel lesson=new LessonPanel();
	private ScorePanel score=new ScorePanel();
	private InstitutePanel institute=new InstitutePanel();
	private StudentPanel student=new StudentPanel();
	private JLabel name=new JLabel("姓名");
	private JLabel id=new JLabel("工号");
	private JLabel sex=new JLabel("性别");
	private JLabel o_name=new JLabel();
	private JLabel o_id=new JLabel();
	private JLabel o_sex=new JLabel();
	private JLabel label=new JLabel();
	public AdministratorInterface(String f_id)
	{
		setTitle("学生选课系统-管理员");
		setSize(800,600);
		Container c=getContentPane();
		tabel.add("基本信息", xinxi);
		tabel.add("课程管理",lesson );
		tabel.add("成绩管理", score);
		tabel.add("学生管理",student);
		tabel.add("学院管理",institute);
		c.add(tabel);
		getMessage(f_id);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		xinxi.setLayout(null);
		label.setBounds(200, 100, 400, 60);
		name.setBounds(200, 200, 100, 60);
		o_name.setBounds(400, 200, 100, 60);
		id.setBounds(200, 300, 100, 60);
		o_id.setBounds(400, 300, 100, 60);
		sex.setBounds(200, 400, 100, 60);
		o_sex.setBounds(400, 400, 100, 60);
		xinxi.add(label);
		xinxi.add(name);
		xinxi.add(o_name);
		xinxi.add(id);
		xinxi.add(o_id);
		xinxi.add(sex);
		xinxi.add(o_sex);
		
	}
	public void getMessage(String f_id)
	{
		try{
			String sql="select *from db_operator where id='"+f_id+"'";
			ResultSet set=Dao.executeQuery(sql);
			while(set.next()){
			label.setText("欢迎登录系统，你好！管理员"+set.getString("id"));
			o_id.setText(set.getString("id"));
			o_name.setText(set.getString("name"));
			o_sex.setText(set.getString("sex"));}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//Dao.close();
	}

}
