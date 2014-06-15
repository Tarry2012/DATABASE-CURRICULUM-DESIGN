package SQL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SelectEmpInfo{
	//定义窗口
	JFrame jf = new JFrame("查询员工工资");
	public  SelectEmpInfo(String  numEmp, String nameEmp, String sumWage, String postWage, String rewardWage, String ageWage){
		//设置窗口
		jf.setLocation(100, 50);
		jf.setSize(800, 600);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLayout(null);
		//添加容器
		Container cp = jf.getContentPane();
		
		//设置图片
		JLabel imageLabel = new JLabel();
		imageLabel.setPreferredSize(new Dimension(1366,768));
		ImageIcon image = new ImageIcon("/home/tqy/图片/信息.jpg");
		imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
		imageLabel.setIcon(image);
		
		//设置字体
		Font fontTop = new Font("宋体",Font.BOLD,40);
		//设置标签
		JLabel seleTop = new JLabel("查 询 员 工 结 果");
		seleTop.setFont(fontTop);
		seleTop.setBounds(220, 40, 400, 50);
		seleTop.setForeground(Color.black);
		//添加标签
		cp.add(seleTop);
		
		//设置字体
		Font font = new Font("宋体",Font.BOLD,20);
		
		//设置标签
		JLabel empNum = new JLabel("员工编号:");
		empNum.setFont(font);
		empNum.setBounds(100, 120, 100, 60);
		//添加标签
		cp.add(empNum);
		
		//设置标签
		JLabel showNum = new JLabel();
		showNum.setText(numEmp);
		showNum.setFont(font);
		showNum.setBounds(220, 120, 150, 60);
		//添加标签
		cp.add(showNum);
		
		//设置标签
		JLabel empName = new JLabel("员工姓名:");
		empName.setFont(font);
		empName.setBounds(400, 120, 100, 60);
		//添加标签
		cp.add(empName);
		
		//设置标签
		JLabel showName = new JLabel();
		showName.setText(nameEmp);
		showName.setFont(font);
		showName.setBounds(520, 120, 150, 60);
		//添加标签
		cp.add(showName);
		
		//设置标签
		JLabel sumSalary = new JLabel("员工总工资:");
		sumSalary.setFont(font);
		sumSalary.setBounds(240, 200, 200, 60);
		sumSalary.setForeground(Color.red);
		//添加标签
		cp.add(sumSalary);
		
		//设置标签
		JLabel showSum = new JLabel();
		showSum.setText(sumWage);
		showSum.setFont(font);
		showSum.setBounds(380, 200, 300, 60);
		showSum.setForeground(Color.red);
		//添加标签
		cp.add(showSum);
		
		//设置标签
		JLabel postSalary = new JLabel("职位工资:");
		postSalary.setFont(font);
		postSalary.setBounds(400, 300, 100, 60);
		//添加标签
		cp.add(postSalary);
		
		//设置标签
		JLabel showPost = new JLabel();
		showPost.setText(postWage);
		showPost.setFont(font);
		showPost.setBounds(520, 300, 150, 60);
		//添加标签
		cp.add(showPost);
		
		//设置标签
		JLabel empReward = new JLabel("奖惩工资:");
		empReward.setFont(font);
		empReward.setBounds(100, 300, 100, 60);
		//添加标签
		cp.add(empReward);
		
		//设置标签
		JLabel showReward = new JLabel();
		showReward.setText(rewardWage);
		showReward.setFont(font);
		showReward.setBounds(220, 300, 150, 60);
		//添加标签
		cp.add(showReward);
		
		//设置标签
		JLabel ageSalary = new JLabel("工龄工资:");
		ageSalary.setFont(font);
		ageSalary.setBounds(300, 400, 100, 60);
		//添加标签
		cp.add(ageSalary);
		
		//设置标签
		JLabel showAge = new JLabel();
		showAge.setText(ageWage);
		showAge.setFont(font);
		showAge.setBounds(470, 400, 150, 60);
		//添加标签
		cp.add(showAge);
		
		//设置字体
		Font fontButton = new Font("宋体",Font.BOLD,23);
		//设置按钮
		JButton button = new JButton("确定");
		button.setBounds(660, 450, 100, 100);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFont(fontButton);
		button.setForeground(Color.black);
		//添加按钮
		cp.add(button);

		//添加图片
		cp.add(imageLabel);
		//添加按钮监听器
		button.addActionListener(new ButtonEvent());
		
		jf.repaint();
		jf.validate();
	}
	
	//设置按钮监听器
	class ButtonEvent extends JFrame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//当当前界面隐藏
			jf.setVisible(false);
		}
	}
	
	public static void main(String [] args){
		new SelectEmpInfo("04123132","张三","12345678", "2000","100","200");
	}
	
}