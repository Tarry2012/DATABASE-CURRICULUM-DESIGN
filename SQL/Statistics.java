package SQL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Statistics{
	//定义窗口
	JFrame jf = new JFrame("工资统计信息");
	public Statistics(String num, String avg,String hight,String low, String hDep, String lDep){
		System.out.println("aaaaabbb:" + lDep);
		//设置容器
		Container cp = jf.getContentPane();
		//设置窗口
		jf.setBounds(100, 100, 650, 600);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.setLocation(300, 100);
		//设置界面布局
		cp.setLayout(null);
		
		//设置图片
		JLabel imageLabel = new JLabel();
		imageLabel.setPreferredSize(new Dimension(1366,768));
		ImageIcon image = new ImageIcon("/home/tqy/图片/信息.jpg");
		imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
		imageLabel.setIcon(image);
		
		//设置字体
		Font fontop = new Font("宋体",Font.BOLD, 35);
		//设置标题标签
		JLabel top = new JLabel("工 资 统 计 信 息");
		top.setBounds(180, 0, 300, 130);
		top.setFont(fontop);
		top.setForeground(Color.black);
		//添加标题标签
		cp.add(top);
		
		//设置字体
		Font font = new Font("宋体",Font.BOLD,19);
		//设置标签
		JLabel numLabel = new JLabel("员工总人数:");
		numLabel.setFont(font);
		numLabel.setBounds(100, 125, 120, 35);
		//添加标签
		cp.add(numLabel);
		
		//设置标签
		JLabel showNum = new JLabel();
		showNum.setText(num);
		showNum.setFont(font);
		showNum.setBounds(220, 125, 150, 35);
		//添加标签
		cp.add(showNum);
		
		//设置标签
		JLabel avgMoney = new JLabel("员工平均工资:");
		avgMoney.setFont(font);
		avgMoney.setBounds(300, 125, 150, 35);
		//添加标签
		cp.add(avgMoney);
		
		//设置标签
		JLabel showAvg = new JLabel();
		showAvg.setText(avg);
		showAvg.setFont(font);
		showAvg.setBounds(435, 125, 150, 35);
		//添加标签
		cp.add(showAvg);
		
		//设置标签
		JLabel hightLabel = new JLabel("员工最高工资:");
		hightLabel.setFont(font);
		hightLabel.setBounds(100, 200, 150, 35);
		//添加标签
		cp.add(hightLabel);
		
		//设置标签
		JLabel showHight = new JLabel();
		showHight.setText(hight);
		showHight.setFont(font);
		showHight.setBounds(233, 200, 150, 35);
		//添加标签
		cp.add(showHight);
		
		//设置标签
		JLabel lowLabel = new JLabel("员工最低工资:");
		lowLabel.setFont(font);
		lowLabel.setBounds(340, 200, 150, 35);
		//添加标签
		cp.add(lowLabel);
		
		//设置标签
		JLabel showLow = new JLabel();
		showLow.setText(low);
		showLow.setFont(font);
		showLow.setBounds(480, 200, 150, 35);
		//添加标签
		cp.add(showLow);
		
		//设置标签
		JLabel hightDep = new JLabel("最高工资部门:");
		hightDep.setFont(font);
		hightDep.setBounds(100, 280, 150, 35);
		//添加标签
		cp.add(hightDep);
		
		//设置标签
		JLabel showDephight = new JLabel();
		showDephight.setText(hDep);
		showDephight.setFont(font);
		showDephight.setBounds(250, 280, 150, 35);
		//添加标签
		cp.add(showDephight);
		
		//设置标签
		JLabel lowDep = new JLabel("最低工资部门:");
		lowDep.setFont(font);
		lowDep.setBounds(200, 360, 150, 35);
		//添加标签
		cp.add(lowDep);
		
		//设置标签
		JLabel showLowDep = new JLabel();
		showLowDep.setText(lDep);
		showLowDep.setFont(font);
		showLowDep.setBounds(350, 360, 150, 35);
		//添加标签
		cp.add(showLowDep);
		
		//设置字体
		Font buttonFont = new Font("宋体", Font.BOLD, 30);
		//设置按钮
		JButton button = new JButton("确定");
		button.setBounds(500, 500, 100, 35);
		button.setFont(buttonFont);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setVisible(true);
		//添加按钮
		cp.add(button);
		
		//添加按钮监听器
		button.addActionListener(new ButtonEvent());
		//添加图片
		cp.add(imageLabel);
		
		jf.repaint();
		jf.validate();
	}
	
	//按钮监听器
	class ButtonEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//当前界面消失
			jf.setVisible(false);
		}
		
	}
	public static void main(String [] args){
		new Statistics("123","1233","3254","fwer","dsfw","dfwef");
	}
}