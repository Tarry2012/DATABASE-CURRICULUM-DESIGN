package SQL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SelectDep{
	//设置窗口
	JFrame jf = new JFrame("部门信息查询");
	//设置文本信息
	JTextField text = new JTextField(20);
	
	public SelectDep(){
			//设置容器
			Container cp = jf.getContentPane();
			//设置窗口信息
			jf.setBounds(100, 100, 500, 500);
			jf.setResizable(false);
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jf.setVisible(true);
			jf.setLayout(null);
			jf.setLocation(350,100);
			cp.setBounds(0,0,500,500);
			cp.setLayout(null);
			
			//设置图片
			JLabel imageLabel = new JLabel();
			imageLabel.setPreferredSize(new Dimension(1366,768));
			ImageIcon image = new ImageIcon("/home/tqy/图片/信息2.jpg");
			imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
			imageLabel.setIcon(image);
			
			//设置字体
			Font fontTop = new Font("宋体", Font.BOLD,36);
			//设置标题标签
			JLabel labelTop = new JLabel("部 门 信 息 查 询");
			labelTop.setBounds(90, 0,300, 100);
			labelTop.setFont(fontTop);
			labelTop.setForeground(Color.orange);
			cp.add(labelTop);
			
			//设置字体
			Font fontCode = new Font("宋体",Font.ITALIC,25);
			//设置标签
			JLabel labelCode = new JLabel("请输入部门编号:");
			labelCode.setBounds(160, 130, 200, 80);
			labelCode.setFont(fontCode);
			//加入标签
			cp.add(labelCode);
			//设置文本框
			text.setBounds(155, 250, 200, 25);
			//添加文本框
			cp.add(text);
			
			//设置字体
			Font fontButton = new Font("宋体",Font.BOLD ,20);
			//设置关闭按钮
			JButton buClose = new JButton("关闭");
			buClose.setBounds(20, 380, 100, 35);
			buClose.setFont(fontButton);
			//添加关闭按钮
			cp.add(buClose);
			//设置确定按钮
			JButton buYes = new JButton("确定");
			buYes.setBounds(380, 380, 100, 30);
			buYes.setFont(fontButton);
			//添加确定按钮
			cp.add(buYes);
			//添加图片
			cp.add(imageLabel);
			
			jf.repaint();
			jf.validate();
			
			//添加关闭按钮监听器
			buClose.addActionListener(new CloseEvent());
			//添加确定按钮监听器
			buYes.addActionListener(new YesEvent());
	}	
	
	//关闭按钮监听器
	class CloseEvent extends JFrame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//使该界面隐藏
			jf.setVisible(false);
		}
	}
	
	//确定按钮监听器
	class YesEvent extends JFrame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String [] result = new String[5];
			//判断文本框中内容是否为空
			if (text.getText().trim().equals("")){
				//如果是空
				JOptionPane.showMessageDialog(null, "输入内容为空");
			}else{
				//如果不是空，连接数据库
				result = MysqlClient.DepSelectInfo(text.getText());
				if (result[0] == null){
					//如果返回空，则提示错误
					JOptionPane.showMessageDialog(null, "输入信息有误");
					new SelectDep();
					jf.setVisible(false);
				}else {
					//如果返回值不是空，则调用显示信息界面
					new SelectDepInfo(result[0], result[1], result[3], result[2], result[4]);
					jf.setVisible(false);
				}
			//	System.out.println("SelectDep:" + result[0]);
			}
		}
		
	}
	
	public static void main(String [] args){
		new SelectDep();
	}
}