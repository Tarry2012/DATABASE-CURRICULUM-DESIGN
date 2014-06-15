package SQL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddUser{
	//设置窗口
	JFrame jf = new JFrame();
	//设置用户名文本框
	JTextField textName = new JTextField(20);
	//设置密码框
	JPasswordField pass = new JPasswordField(10);
	//设置密码框
	JPasswordField secPasswd = new JPasswordField(10);
	//设置等级文本框
	JTextField textAdmin = new JTextField(20);
	
	public AddUser(){
		//设置窗口
		jf.setSize(500, 400);
		jf.setLocation(500, 250);
		//定义容器
		Container cp = jf.getContentPane();
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLayout(null);

		//设置图片
		JLabel imageLabel = new JLabel();
		imageLabel.setPreferredSize(new Dimension(1366,768));
		ImageIcon image = new ImageIcon("/home/tqy/图片/信息3.jpg");
		imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
		imageLabel.setIcon(image);
		
		//设置字体
		Font fontTop = new Font("宋体",Font.BOLD,30);
		//设置标签
		JLabel labelTop = new JLabel("增 加 用 户");
		labelTop.setBounds(170, 0, 200, 100);
		labelTop.setFont(fontTop);
		labelTop.setForeground(Color.black);
		//添加标签
		cp.add(labelTop);
		
		//设置字体
		Font font = new Font("宋体",Font.BOLD,20);
		
		//设置标签
		JLabel labelName = new JLabel("用户名:");
		labelName.setBounds(150, 100, 100, 35);
		labelName.setFont(font);
		//添加标签
		cp.add(labelName);
		
		//设置文本框
		textName.setBounds(254, 105, 120, 25);
		//添加文本框
		cp.add(textName);
		
		//设置密码标签
		JLabel labelPass = new JLabel("密码:");
		labelPass.setBounds(170, 165, 100, 35);
		labelPass.setFont(font);
		//添加密码标签
		cp.add(labelPass);
		
		//设置密码框
		pass.setBounds(254, 170, 120, 25);
		//添加密码框
		cp.add(pass);
		
		//设置密码文本框2
		JLabel labelpasswd = new JLabel("再次输入:");
		labelpasswd.setFont(font);
		labelpasswd.setBounds(125, 230, 120, 35);
		//添加密码文本框2
		cp.add(labelpasswd);
		
		//设置密码框
		secPasswd.setBounds(254, 240, 120, 25);
		//添加密码框
		cp.add(secPasswd);
		
		//设置标签
		JLabel labelAdmin = new JLabel("等级:");
		labelAdmin.setFont(font);
		labelAdmin.setBounds(165, 290, 100, 35);
		//添加标签
		cp.add(labelAdmin);
		
		//设置文本框位置
		textAdmin.setBounds(254, 300, 120, 25);
		//添加文本框
		cp.add(textAdmin);
		
		//设置提示标签
		JLabel labelExplain = new JLabel("(1,2,3)");
		labelExplain.setBounds(280, 320, 100, 25);
		//添加提示标签
		cp.add(labelExplain);
		
		//设置字体
		Font fontButton = new Font("宋体",Font.PLAIN,17);
		//设置按钮
		JButton button = new JButton("确定");
		button.setFont(fontButton);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(390, 320, 100, 35);
		//添加按钮
		cp.add(button);
		
		//设置按钮
		JButton button2 = new JButton("取消");
		button2.setFont(fontButton);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.setBounds(20, 320, 100, 35);
		//添加按钮
		cp.add(button2);
		
		//添加确定按钮监听器
		button.addActionListener(new TextNameEvent());
		//添加取消按钮监听器
		button2.addActionListener(new ExitEvent());
		//添加图片
		cp.add(imageLabel);
		
		jf.repaint();
		jf.validate();
	}
	
	//设置取消按钮监听器
	class ExitEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//使当前界面消失
			jf.setVisible(false);
		}
		
	}
	
	//设置确定按钮监听器
	class TextNameEvent extends JFrame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String passwd1 = String.valueOf(pass.getPassword());
			String passwd2 = String.valueOf(secPasswd.getPassword());
			//判断框中内容是否为空
			if (textAdmin.getText().trim().equals("") || textAdmin.getText().trim().equals("") || passwd1.equals("") || passwd1.equals("")){
				JOptionPane.showMessageDialog(null, "输入内容为空!");
			}else if( !passwd1.equals(passwd2) ){
				//判断两个文本框中的密码是否相同
				JOptionPane.showMessageDialog(null, "两次输入密码不正确!");
				textAdmin.setText("");
				textName.setText("");
			}else {
				//连接数据库处理
				String admin = textAdmin.getText();
				String name = textName.getText();
				//如果返回不是1,则输入有误
				if (MysqlClient.InsertUser(name, passwd1, admin) != 1){
					JOptionPane.showMessageDialog(null, "用户名已存在或等级选项(1,2,3)");
				//	jf.setVisible(false);
				}else{
					//否则添加成功
					JOptionPane.showMessageDialog(null, "添加成功");
					//new AddUser();
					jf.setVisible(false);
				}
			}
			
		}
	}
	public static void main(String [] args){
		new AddUser();
	}
}