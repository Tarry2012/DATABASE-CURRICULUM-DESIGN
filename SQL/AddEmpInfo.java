package SQL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.LabelPeer;

import javax.swing.*;

public class AddEmpInfo{
	JFrame jf = new JFrame("增加员工信息");
	JTextField inputNum = new JTextField(20);
	JTextField inputName = new JTextField(20);
	JTextField inputAge = new JTextField(20);
	JTextField inputTime = new JTextField(20);
	JTextField inputSex = new JTextField(20);
	JTextField inputTel = new JTextField(20);
	JTextField inputPost = new JTextField(20);
	JTextField inputDep = new JTextField(20);
	
	public AddEmpInfo(){
		jf.setSize(600, 550);
		jf.setLocation(400, 150);
		Container cp = jf.getContentPane();
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLayout(null);
		cp.setSize(600,550);
		
		JLabel imageLabel = new JLabel();
		imageLabel.setPreferredSize(new Dimension(1366,768));
		ImageIcon image = new ImageIcon("/home/tqy/图片/信息3.jpg");
		imageLabel.setSize(image.getIconWidth(),image.getIconHeight());
		imageLabel.setIcon(image);
		
		Font fontTop = new Font("宋体",Font.BOLD,35);
		JLabel labelTop = new JLabel("增 加 员 工 信 息");
		labelTop.setFont(fontTop);
		labelTop.setBounds(130, 0, 300, 100);
		labelTop.setForeground(Color.black);
		cp.add(labelTop);
		
		Font font = new Font("宋体",Font.BOLD,20);
		
		JLabel labelNum = new JLabel("员工号:");
		labelNum.setFont(font);
		labelNum.setBounds(40, 100,100, 70);
		labelNum.setForeground(Color.black);
		cp.add(labelNum);
		
		
		inputNum.setBounds(120, 115, 120, 35);
		cp.add(inputNum);
		
		JLabel labelName = new JLabel("姓名:");
		labelName.setFont(font);
		labelName.setBounds(320, 100,100, 60);
		labelName.setForeground(Color.black);
		cp.add(labelName);
		
		
		inputName.setBounds(390, 115, 120, 35);
		cp.add(inputName);
		
		
		JLabel labelAge = new JLabel("年龄:");
		labelAge.setFont(font);
		labelAge.setBounds(40, 200,100, 60);
		labelAge.setForeground(Color.black);
		cp.add(labelAge);
		
		
		inputAge.setBounds(120, 215, 120, 35);
		cp.add(inputAge);
		
		JLabel labelTime = new JLabel("入职时间:");
		labelTime.setFont(font);
		labelTime.setBounds(280, 200,100, 60);
		labelTime.setForeground(Color.black);
		cp.add(labelTime);
		
		
		inputTime.setBounds(391, 215, 120, 35);
		cp.add(inputTime);
		
		JLabel message = new JLabel("年份");
		message.setBounds(520, 224, 40, 20);
		cp.add(message);
		
		
		JLabel labelSex = new JLabel("性别:");
		labelSex.setFont(font);
		labelSex.setBounds(40, 300,100, 60);
		labelSex.setForeground(Color.black);
		cp.add(labelSex);
		
		
		inputSex.setBounds(120, 315, 120, 35);
		cp.add(inputSex);
		
		JLabel labelTel = new JLabel("电话:");
		labelTel.setFont(font);
		labelTel.setBounds(320, 300,100, 60);
		labelTel.setForeground(Color.black);
		cp.add(labelTel);
		
		
		inputTel.setBounds(390, 315, 120, 35);
		cp.add(inputTel);
		
		JLabel labelPost = new JLabel("职位:");
		labelPost.setFont(font);
		labelPost.setBounds(40, 390,100, 60);
		labelPost.setForeground(Color.black);
		cp.add(labelPost);
		
		
		inputPost.setBounds(120, 405, 120, 35);
		cp.add(inputPost);
		
		JLabel labelDep = new JLabel("部门号:");
		labelDep.setFont(font);
		labelDep.setBounds(300, 390,100, 60);
		labelDep.setForeground(Color.black);
		cp.add(labelDep);
		
		
		inputDep.setBounds(390, 405, 120, 35);
		cp.add(inputDep);
		
		Font fontButton = new Font("宋体", Font.BOLD, 20);
		JButton yesButton = new JButton("确定");
		yesButton.setBorderPainted(false);
		yesButton.setContentAreaFilled(false);
		yesButton.setFont(fontButton);
		yesButton.setBounds(460,470, 100, 35);
		yesButton.setForeground(Color.black);
		cp.add(yesButton);
		
		JButton noButton = new JButton("取消");
		noButton.setBorderPainted(false);
		noButton.setContentAreaFilled(false);
		noButton.setFont(fontButton);
		noButton.setBounds(30,470, 100, 35);
		noButton.setForeground(Color.black);
		cp.add(noButton);
		
		noButton.addActionListener(new NoButtonEvent());
		yesButton.addActionListener(new YesButtonEvent());
		
		cp.add(imageLabel);
		jf.repaint();
		jf.validate();
	}
	
	
	class NoButtonEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jf.setVisible(false);
		}
	}
	
	class YesButtonEvent extends JFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (inputNum.getText().trim().equals("") || inputName.getText().trim().equals("") || inputSex.getText().trim().equals("")
					|| inputTel.getText().trim().equals("") || inputPost.getText().trim().equals("") || inputDep.getText().trim().equals("")
					|| inputAge.getText().trim().equals("") || inputTime.getText().trim().equals("") || inputSex.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "输入内容有空");
			}else{
				String num = inputNum.getText();
				String name = inputName.getText();
				String age = inputAge.getText();
				String time = inputTime.getText();
				String sex = inputSex.getText();
				String tel = inputTel.getText();
				String post = inputPost.getText();
				String dep = inputDep.getText();
				
				int rs = MysqlClient.InsertEmp(num, name, age, time, sex, tel, post, dep);
				if (rs != 1){
					JOptionPane.showMessageDialog(null, "输入内容有误！");
				}else{
					JOptionPane.showMessageDialog(null, "输入成功!");
				}
			}
		}
		
	}
	public static void main(String [] args){
		new AddEmpInfo();
	}
}