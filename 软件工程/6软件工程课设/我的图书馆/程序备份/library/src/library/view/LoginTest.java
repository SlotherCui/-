package library.view;

import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
public class LoginTest extends JDialog implements ActionListener{
 private JButton ok,cancel;
 private JTextField user,pwd;
 private JLabel bg;//��Label��������ʾ����
 
 public LoginTest(){
  this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
  this.setSize(311,258);
  this.setLocationRelativeTo(null);
  this.getContentPane().setLayout(null);
  this.setResizable(false);
  
  //ͼƬ������,����Ҫһ����ʱ��,���Ե��²Ż���±���
  this.setTitle("��һ�¾Ϳ���������");
  
  //��ʼ������
  bg = new JLabel("image/login.gif");
  bg.setHorizontalAlignment(JLabel.LEFT);
  bg.setVerticalAlignment(JLabel.TOP);
  bg.setLayout(null);
  //
  this.setContentPane(bg);
  
  user = new JTextField();
  user.setBounds(100,105,188,22);
  this.getContentPane().add(user);
  
  pwd = new JPasswordField();
  pwd.setLocation(user.getX(),user.getY()+30);
  pwd.setSize(user.getSize());
  this.getContentPane().add(pwd);
  
  ok = new JButton("Submit");
  ok.setLocation(118,pwd.getY()+38);
  ok.setSize(78,23);
  ok.addActionListener(this);
  this.getContentPane().add(ok);
  
  cancel = new JButton("Cancel");
  cancel.setLocation(ok.getX()+ok.getWidth()+10,ok.getY());
  cancel.setSize(ok.getSize());
  cancel.addActionListener(this);
  this.getContentPane().add(cancel);
  
  //���ر���ͼƬ
//  new Thread(){
//   public void run(){
//    try{
//     URL url = new URL("http://hiphotos.baidu.com/ttbet/pic/item/8c3fcc2dcdc54628359bf717.jpg");
//     Icon icon = new ImageIcon(url);
//     bg.setIcon(icon);
//    }catch(Exception e){}
//   }
//  }.start();
 }
 public static void main(String[] args) {
  new LoginTest().setVisible(true);
 }
 public void actionPerformed(ActionEvent e) {
  if(e.getSource().equals(ok)){
   JOptionPane.showMessageDialog(this,"����:"+user.getText().trim()+"  ����������:"+pwd.getText().trim());
  }
  else if(e.getSource().equals(cancel)){
   //....
   //....
   this.dispose();
  }
 }
} 
