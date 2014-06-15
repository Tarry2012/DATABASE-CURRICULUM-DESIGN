package SQL;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import SQL.DeleteUser.ButtonEvent;

public class DeleteEmp{
	JTextField textUser = new JTextField(20);
	JTextField textName = new JTextField(20);
	JFrame jf = new JFrame("删除员工界面");
	public DeleteEmp(){

		jf.setSize(500, 400);
		jf.setLocation(500, 250);
		Container cp = jf.getContentPane();
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLayout(null);
		cp.setSize(500,400);
		
		JLabel imageLabel = new JLabel();
		imageLabel.setPreferredSize(new Dimension(1366,768));
		ImageIcon image = new ImageIcon("/home/tqy/图片/信息3.jpg");
		imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
		imageLabel.setIcon(image);
		
		Font fontTop = new Font("宋体",Font.BOLD,35);
		JLabel labelTop = new JLabel("删 除 员 工");
		labelTop.setBounds(150, 0, 200, 100);
		labelTop.setFont(fontTop);
		labelTop.setForeground(Color.black);
		cp.add(labelTop);
		
		Font fontUser = new Font("宋体", Font.BOLD,25);
		JLabel labelUser = new JLabel("员工号:");
		labelUser.setFont(fontUser);
		labelUser.setBounds(100, 140, 100, 50);
		labelUser.setForeground(Color.black);
		cp.add(labelUser);
		
		
		textUser.setBounds(220, 150, 200, 35);
		cp.add(textUser);
		
		JButton button = new JButton("确 定");
		button.setFont(fontUser);
		button.setBounds(320, 280, 200, 47);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setForeground(Color.black);
		cp.add(button);
		
		JButton button2 = new JButton("取 消");
		button2.setFont(fontUser);
		button2.setBounds(0, 280, 180, 47);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.setForeground(Color.black);
		cp.add(button2);
		
		cp.add(imageLabel);
		
		button.addActionListener(new ButtonEvent());
		button2.addActionListener(new ExitEvent());
		
		jf.repaint();
		jf.validate();
	}
	
	class ExitEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jf.setVisible(false);
		}
		
	}
	class ButtonEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (textUser.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "输入内容为空");
			}else{
					String name = textUser.getText();
				//	System.out.println("delete :" + name);
					if (MysqlClient.DeleteName(name) != 1){
						JOptionPane.showMessageDialog(null, "输入有误");
						new DeleteEmp();
						jf.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null, name + "号员工已被成功删除");
						jf.setVisible(false);
					}
			}
		}
	}
	public static void main(String [] args){
		new DeleteEmp();
	}
}