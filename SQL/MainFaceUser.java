package SQL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.*;

public class MainFaceUser{
	Operate operate = new Operate();
	
	public MainFaceUser(){
	JFrame jf = new JFrame("工资管理系统");
	Toolkit tool = Toolkit.getDefaultToolkit();
	Dimension screenSize = tool.getScreenSize();
	Container CP2 = jf.getContentPane();
	CP2.setBounds(0,0,screenSize.height/2,screenSize.width/5);
	CP2.setLayout(null);
	
	jf.setSize(1000,700);
	jf.setResizable(false);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jf.setVisible(true);
	jf.setLocation(150, 0);
	
	Font fontTop = new Font("宋体",Font.BOLD,50);
	JLabel labelTop = new JLabel("工  资  管  理  系  统");  
	labelTop.setForeground(Color.orange);
	labelTop.setFont(fontTop);
	labelTop.setBounds(270, 0, 500, 150);
	CP2.add(labelTop);
	
	JLabel imageLabel = new JLabel();
	imageLabel.setPreferredSize(new Dimension(1366,768));
	ImageIcon image = new ImageIcon("/home/tqy/图片/卡通.jpg");
	imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
	imageLabel.setIcon(image);
	
	
	Font fontsm = new Font("宋体",Font.BOLD,20);

	JButton exit = new JButton("退出");
	exit.setBounds(900, 0, 100, 60);
	exit.setForeground(Color.black);
	exit.setContentAreaFilled(false);
	exit.setBorderPainted(false);
	exit.setFont(fontsm);
	exit.setForeground(Color.GRAY);
	CP2.add(exit);
	
	Font font = new Font("宋体",Font.BOLD,35);
	JButton selection = new JButton("查询工资信息");
	selection.setBounds(380, 190, 300, 100);
	selection.setForeground(Color.black);
	selection.setContentAreaFilled(false);
	selection.setBorderPainted(false);
	selection.setFont(font);
	selection.setForeground(Color.DARK_GRAY);
	CP2.add(selection);
	
	JButton modify = new JButton("更新工资信息");
	modify.setBounds(380, 290, 300, 100);
	modify.setForeground(Color.black);
	modify.setContentAreaFilled(false);
	modify.setBorderPainted(false);
	modify.setFont(font);
	modify.setForeground(Color.DARK_GRAY);
	CP2.add(modify);
	
	JButton protect = new JButton("用户管理");
	protect.setBounds(380, 390, 300, 100);
	protect.setForeground(Color.black);
	protect.setContentAreaFilled(false);
	protect.setBorderPainted(false);
	protect.setFont(font);
	protect.setForeground(Color.DARK_GRAY);
	CP2.add(protect);
	
	//添加菜单栏
	MenuBar mv = new MenuBar();
	Menu select = new Menu("查询信息");
	Menu update = new Menu("更新信息");
	Menu statis = new Menu("工资统计信息");
	Menu system = new Menu("用户管理");
	MenuItem employee = new MenuItem("员工信息");
	MenuItem deptment = new MenuItem("部门信息");
	MenuItem exitItem = new MenuItem("删除用户");
	MenuItem empinfo = new MenuItem("修改员工信息");
	MenuItem depinfo = new MenuItem("删除员工信息");
	MenuItem posinfo = new MenuItem("增加员工信息");
	MenuItem rewardinfo = new MenuItem("修改奖惩信息");
	MenuItem statemp = new MenuItem("工资统计信息");
	MenuItem entre = new MenuItem("增加用户");
	
	select.add(employee);
	select.add(deptment);
	update.add(empinfo);
	update.add(depinfo);
	update.add(posinfo);
	update.add(rewardinfo);
	statis.add(statemp);
	system.add(entre);
	system.add(exitItem);
	
	Font fontmv = new Font("宋体",Font.BOLD,15);
	mv.add(select);
	mv.add(update);
	mv.add(statis);
	mv.add(system);
	mv.setFont(fontmv);
	jf.setMenuBar(mv);
	
	CP2.add(imageLabel);
//	jf.repaint();
//	jf.validate();
	
	exit.addActionListener(new ExitEvent());
	selection.addActionListener(new SelectionEvent());
	employee.addActionListener(new SelectionEvent());
	entre.addActionListener(new NoLimitEvent());
	protect.addActionListener(new NoLimitEvent());
	exitItem.addActionListener(new NoLimitEvent());
	deptment.addActionListener(new DeptInfoEvent());
	statemp.addActionListener(new StatisEvent());
	depinfo.addActionListener(new NoLimitEvent());
	posinfo.addActionListener(new NoLimitEvent());
	empinfo.addActionListener(new NoLimitEvent());
	rewardinfo.addActionListener(new NoLimitEvent());
	modify.addActionListener(new NoLimitEvent());
	
	}
	
	
	class NoLimitEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(null, "没有权限");
		}
		
	}
	
	class StatisEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String[] result = new String[6];
			result = MysqlClient.StatisticsInfo();
			new Statistics(result[0], result[1], result[2], result[3], result[4], result[5]);
		}
		
	}
	
	class DeptInfoEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new SelectDep();
		}
	}
	
	
	class ExitEvent extends JFrame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);;
		}
		
	}
	
	class SelectionEvent extends JFrame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new Selectemp();
			
		}
		
	}
	
	public static void main(String [] args){
		new MainFaceUser();
 	}
}