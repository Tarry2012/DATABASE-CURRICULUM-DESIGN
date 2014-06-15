package SQL;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class WageLogin extends JFrame{
	public WageLogin(){
		super();
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(10);
		getContentPane().setLayout(borderLayout);
		setTitle("工资管理系统登陆");
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension screenSize = tool.getScreenSize();
		setSize(285,194);
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(mainPanel);
		final JLabel imageLabel = new JLabel();
		Container CreatecdIcon;
		//ImageIcon loginIcon = CreatecdIcon.add("login.png");
		
		
	}
	
}