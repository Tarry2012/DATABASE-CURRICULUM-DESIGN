package SQL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SelectDepInfo{
	//定义窗口
	JFrame jf = new JFrame("查询部门信息");
	public SelectDepInfo(String depNumInfo, String depNameInfo, String depSumInfo, 
			String depBaseInfo, String depAgeInfo){
		//设置窗口
		jf.setLocation(350, 150);
		jf.setSize(500, 400);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLayout(null);
		//添加容器
		Container cp = jf.getContentPane();
		
		//设置字体
		Font fontTop = new Font("宋体", Font.BOLD, 35);
		//设置标签
		JLabel labelTop = new JLabel("查询部门信息结果");
		labelTop.setBounds(100, 0, 350, 80);
		labelTop.setFont(fontTop);
		labelTop.setForeground(Color.black);
		//添加标签
		cp.add(labelTop);
		
		//设置图片
		JLabel imageLabel = new JLabel();
		imageLabel.setPreferredSize(new Dimension(500,400));
		ImageIcon image = new ImageIcon("/home/tqy/图片/信息.jpg");
		imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
		imageLabel.setIcon(image);
		
		//设置字体
		Font font = new Font("宋体", Font.BOLD, 17);
		
		//设置标签
		JLabel depNum = new JLabel("部门编号:");
		depNum.setFont(font);
		depNum.setBounds(60,130, 100, 60);
		//添加标签
		cp.add(depNum);
		
		//设置标签
		JLabel showNum = new JLabel();
		showNum.setText(depNumInfo);
		showNum.setFont(font);
		showNum.setBounds(145, 131, 150, 60);
		//添加标签
		cp.add(showNum);
		
		//设置标签
		JLabel depName = new JLabel("部门名称:");
		depName.setFont(font);
		depName.setBounds(260, 130, 100, 60);
		//添加标签
		cp.add(depName);
		
		//设置标签
		JLabel showName = new JLabel();
		showName.setText(depNameInfo);
		showName.setFont(font);
		showName.setBounds(343, 131, 150, 60);
		//添加标签
		cp.add(showName);
		
		//设置标签
		JLabel depSum = new JLabel("总人数:");
		depSum.setFont(font);
		depSum.setBounds(60,200, 100, 60);
		//添加标签
		cp.add(depSum);

		//
		JLabel showSum = new JLabel();
		showSum.setText(depSumInfo);
		showSum.setFont(font);
		showSum.setBounds(140, 200, 150, 60);
		cp.add(showSum);
		
		JLabel depBase = new JLabel("部门位置:");
		depBase.setFont(font);
		depBase.setBounds(260, 200, 100, 60);
		cp.add(depBase);
		
		JLabel showBase = new JLabel();
		showBase.setText(depBaseInfo);
		showBase.setFont(font);
		showBase.setBounds(343, 200, 150, 60);
		cp.add(showBase);
		
		JLabel depAvg = new JLabel("平均工资:");
		depAvg.setFont(font);
		depAvg.setBounds(140, 260, 100, 60);
		depAvg.setForeground(Color.red);
		cp.add(depAvg);
		
		JLabel showAvg = new JLabel();
		showAvg.setText(depAgeInfo);
		showAvg.setFont(font);
		showAvg.setBounds(230, 260, 150, 60);
		showAvg.setForeground(Color.red);
		cp.add(showAvg);
		
		Font fontButton = new Font("宋体",Font.BOLD,20);
		JButton button = new JButton("确定");
		button.setBounds(400, 280, 100, 100);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFont(fontButton);
		cp.add(button);

		cp.add(imageLabel);
		button.addActionListener(new ButtonEvent());
		jf.repaint();
		jf.validate();
		
	}
	
	class ButtonEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jf.setVisible(false);
		}
		
	}
	public static void main(String [] args){
		new SelectDepInfo("04123132","天天", "1000", "1234.5", "123.4");
	}
}