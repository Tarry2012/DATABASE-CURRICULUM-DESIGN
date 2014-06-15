package SQL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Selectemp{
	//定义窗口信息
	JFrame jf = new JFrame("员工工资查询");
	//定义员工号文本框
	JTextField text = new JTextField(20);
	
	public Selectemp(){
		//设置容器
		Container cp = jf.getContentPane();
		//设置窗口大小
		jf.setBounds(100, 100, 500, 500);
		//设置窗口不可放大
		jf.setResizable(false);
		//设置窗口的关闭按钮的动作是关闭该界面
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//设置显示窗口信息
		jf.setVisible(true);
		//设置显示窗口信息的位置
		jf.setLocation(360, 180);
		//设置容器的布局为空
		cp.setLayout(null);
		
		//设置图片信息
		JLabel imageLabel = new JLabel();
		imageLabel.setPreferredSize(new Dimension(1366,768));
		ImageIcon image = new ImageIcon("/home/tqy/图片/信息2.jpg");
		imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
		imageLabel.setIcon(image);
		
		//设置字体
		Font fontTop = new Font("宋体", Font.BOLD,32);
		//设置标题标签
		JLabel labelTop = new JLabel("员 工 工 资 信 息 查 询");
		//设置标题标签的位置
		labelTop.setBounds(80, 0,350, 130);
		//设置标题标签的字体
		labelTop.setFont(fontTop);
		//设置标题标签的字体颜色
		labelTop.setForeground(Color.orange);
		//添加标题标签
		cp.add(labelTop);
		
		//设置字体
		Font fontCode = new Font("宋体",Font.ITALIC,25);
		//设置标签
		JLabel labelCode = new JLabel("请输入员工编号:");
		//设置标签位置
		labelCode.setBounds(160, 130, 220, 80);
		//设置标签字体
		labelCode.setFont(fontCode);
		//添加标签
		cp.add(labelCode);
		//设置输入文本框位置
		text.setBounds(147, 250, 200, 25);
		//添加输入文本框
		cp.add(text);
		
		//设置按钮字体
		Font fontButton = new Font("宋体", Font.BOLD,20);
		//设置关闭按钮
		JButton buClose = new JButton("关闭");
		//设置按钮位置
		buClose.setBounds(10, 380, 100, 30);
		//设置按钮字体
		buClose.setFont(fontButton);
		//添加按钮
		cp.add(buClose);
		
		//设置确定按钮
		JButton buYes = new JButton("确定");
		//设置按钮的位置
		buYes.setBounds(380, 380, 100, 30);
		//设置按钮的字体
		buYes.setFont(fontButton);
		//添加按钮
		cp.add(buYes);
		
		//添加图片
		cp.add(imageLabel);
		
		
		jf.repaint();
		jf.validate();
		
		//设置确定按钮的监听器
		buYes.addActionListener(new YesEvent());
		//设置取消按钮的监听器
		buClose.addActionListener(new CloseEvent());
		
	}

	//确定按钮的监听器
	class YesEvent extends JFrame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//判断输入内容是否是空
			if (text.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "输入内容为空");
			}else {
				//如果不是空，则连接数据库处理
				String [] result = new String[6];
				result = MysqlClient.EmpSelectInfo(text.getText());
				//如果返回的值为空，则员工信息不存在
				if(result[0] == null){
					JOptionPane.showMessageDialog(null, "不存在该员工编号！");
					new Selectemp();
					jf.setVisible(false);
					//如果不为空，则显示信息
				}else {
					jf.setVisible(false);
					new SelectEmpInfo(result[0], result[1], result[2], result[3], result[4], result[5]);
				}
			}
		}
	}
	
	class CloseEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jf.setVisible(false);
			
		}
	}
	
//	public static void main(String [] args){
//		new Selectemp();
//	}
	
}