package SQL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DeleteUser{
	//定义文本框
	JTextField textUser = new JTextField(20);
	//定义窗口
	JFrame jf = new JFrame("删除用户界面");
	public DeleteUser(){
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
		imageLabel.setPreferredSize(new Dimension(500,268));
		ImageIcon image = new ImageIcon("/home/tqy/图片/信息3.jpg");
		imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
		imageLabel.setIcon(image);
		
		//设置字体
		Font fontTop = new Font("宋体",Font.BOLD,35);
		//设置标签
		JLabel labelTop = new JLabel("删 除 用 户");
		labelTop.setBounds(150, 0, 200, 100);
		labelTop.setFont(fontTop);
		labelTop.setForeground(Color.black);
		//添加标签
		cp.add(labelTop);
		
		//设置字体
		Font fontUser = new Font("宋体", Font.BOLD,25);
		//设置标签
		JLabel labelUser = new JLabel("用户名:");
		labelUser.setFont(fontUser);
		labelUser.setBounds(120, 170, 100, 50);
		labelUser.setForeground(Color.black);
		//添加标签
		cp.add(labelUser);
	
		//设置文本框
		textUser.setBounds(250, 180, 170, 35);
		//添加文本框
		cp.add(textUser);
		
		//设置按钮
		JButton button = new JButton("确定");
		button.setFont(fontUser);
		button.setBounds(350, 300, 100, 45);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setForeground(Color.black);
		//添加按钮
		cp.add(button);
		
		//设置按钮
		JButton noButton = new JButton("取消");
		noButton.setFont(fontUser);
		noButton.setBounds(40, 300, 100, 45);
		noButton.setContentAreaFilled(false);
		noButton.setBorderPainted(false);
		noButton.setForeground(Color.black);
		//添加按钮
		cp.add(noButton);
		//添加图片
		cp.add(imageLabel);
		
		//添加确定按钮监听器
		button.addActionListener(new ButtonEvent());
		//添加取消按钮监听器
		noButton.addActionListener(new NoButtonEvent());
		
		jf.repaint();
		jf.validate();
		
	}
	
	//设置取消按钮监听器
	class NoButtonEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//是当前界面消失
			jf.setVisible(false);
		}
		
	}
	
	//设置确定按钮监听器
	class ButtonEvent extends JFrame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//判断文本框内容是否为空
			if (textUser.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "输入内容为空");
			}else{//如果不为空，连接数据库处理
					String name = textUser.getText();
					System.out.println("DeleteUser: " + name);
					if (MysqlClient.DeleterUser(name) != 1){
						JOptionPane.showMessageDialog(null, "用户名输入有误");
						new DeleteUser();
						jf.setVisible(false);
					}else{
						//如果删除成功，提示
						JOptionPane.showMessageDialog(null, "删除成功");
						jf.setVisible(false);
					}
			}
		}
	}
	public static  void main(String [] args){
		new DeleteUser();
	}
}