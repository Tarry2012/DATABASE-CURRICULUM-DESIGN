package SQL;
import java.awt.*;
import java.awt.event.*;
import java.rmi.server.UID;

import javax.swing.*;

class Login{
	//用户名文本框
	JTextField User = new JTextField(20);
	//密码框
	JPasswordField Passwd = new JPasswordField(20);
	//界面框
	JFrame frame = new JFrame("登陆");
	//操作员等级
	Operate operate = new Operate();

	//登陆界面
 public void LoginFirst(){
	 //设置登陆界面的位置
  frame.setLocation(470, 220);
  //设置登陆界面不可放大
  frame.setResizable(false);
  //用户单击窗口的关闭按钮时程序执行的关闭的操作
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //设置窗口可见
  frame.setVisible(true);
  //设置窗口大小
  frame.setSize(350, 150);
  //设置窗口布局
  frame.setLayout(null);
 
  //设置窗口容器
  Container cp = frame.getContentPane();
  
  //设置图片
  JLabel imageLabel = new JLabel();
  imageLabel.setPreferredSize(new Dimension(1366,768));
  ImageIcon image = new ImageIcon("/home/tqy/图片/卡通.jpg");
  imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
  imageLabel.setIcon(image);
  
  //设置用户名标签
  JLabel labelUser = new JLabel("用户名:");
  //设置用户名标签位置
  labelUser.setBounds(40, 20, 100, 30);
  //添加用户名标签
  cp.add(labelUser);
  
  //设置密码标签
  JLabel labelPass = new JLabel("密码:");
  //设置密码标签位置
  labelPass.setBounds(40, 50, 100, 30);
  //添加密码标签
  cp.add(labelPass);
  
  //设置用户文本框标签位置
  User.setBounds(140, 25, 100, 25);
  //添加用户文本框
  cp.add(User);
  //设置密码框位置
  Passwd.setBounds(140, 55, 100, 25);
  //添加密码款
  cp.add(Passwd);
  
  //设置确定按钮
  JButton button = new JButton("确定");
  //设置确定按钮位置
  button.setBounds(250, 90, 60, 30);
  //添加确定按钮
  cp.add(button);
  
  //设置取消按钮
  JButton button2 = new JButton("取消");
  //设置取消按钮标签
  button2.setBounds(40, 90, 60, 30);
  //添加取消按钮
  cp.add(button2);
  //添加图片
  cp.add(imageLabel);
  
  //设置确定按钮监听器
  button.addActionListener(new ButtonEvent());
  //设置取消按钮监听器
  button2.addActionListener(new ExitEvent());
  //刷新界面，直到界面组件全部显现出来
  frame.repaint();
  frame.validate();
 }
 
 //取消按钮的监听器
 class ExitEvent extends JFrame implements ActionListener{
	 //退出程序
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}
	 
 }

 //确定按钮的监听器
 class ButtonEvent extends JFrame implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		//登陆的用户信息
		String name = User.getText();
		String [] result;
		//登陆的密码信息
		char [] passWd = Passwd.getPassword();
		String password = String.valueOf(passWd);
		//判断框中内容是否为空
		if (User.getText().trim().equals("") || password == "" ){
			JOptionPane.showMessageDialog(null,"内容不能为空");
		//如果不为空
		}else{
			//连接数据库，验证登陆信息
			result = MysqlClient.check(name, password);
			operate.setName(result[0]);
			operate.setpassword(result[1]);
			operate.setadmin(result[2]);
			//如果返回信息部位空，则该用户存在，登陆成功
			if (operate.getName() != null){
		//		System.out.println("login.java222:" + operate.getName() + operate.getadmin() + operate.getpassword());
				JOptionPane.showMessageDialog(null,"登陆成功!");
				//获取登陆用户的等级，如果是“3”，即为一般用户
				if (operate.getadmin().equals("3")){
					new MainFaceUser();
					frame.setVisible(false);
				//如果是“2”，则是管理员用户
				}else if(operate.getadmin().equals("2")){
					new MainFaceAdmin();
					frame.setVisible(false);
				//如果是“3”，则是root用户
				}else{
					new MainFaceRoot();
					frame.setVisible(false);
				}
				//如果返回内容为空，则该用户不存在或者密码错误
			}else {
				JOptionPane.showMessageDialog(null,"用户名或密码错误!");
			}
		}
	}
 }
 
 public static void main(String [] args){
	 new Login().LoginFirst();
 }
}


