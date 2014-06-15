package SQL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.*;

public class ModifyEmp{
	JFrame jf = new JFrame("修改员工信息");
	JLabel showNum = new JLabel();
	JLabel showDepName = new JLabel();
	JTextField showTime = new JTextField(20);
	JTextField showPost = new JTextField(20);
	JTextField showName = new JTextField(20);
	JTextField showSex = new JTextField(20);
	JTextField showDepNum = new JTextField(20);
	JTextField showAge = new JTextField(20);
	JTextField showTel = new JTextField(20);
	
	
	public ModifyEmp(Vector<Vector<String>> data,Vector<String> columnNames) {
		
		jf.setLayout(new BorderLayout(30,5));
		final JTable table = new JTable(data,columnNames);

		jf.pack();
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLocation(400, 200);
		jf.setSize(800, 500);
		jf.setVisible(true);
	
		Font fontlabel = new Font("宋体", Font.BOLD, 15);
		
		JPanel JpUP = new JPanel(new GridLayout(0, 1));
		JpUP.add(new JScrollPane(table));

		JPanel JpDown = new JPanel(new GridLayout(0, 6));
		
		Font font = new Font("宋体",Font.BOLD,20);
		
		JLabel labelNum = new JLabel("员工号:");
		labelNum.setFont(fontlabel);
		JpDown.add(labelNum);
		
		JpDown.add(showNum);
	
		JLabel labelName = new JLabel("姓名:");
		labelName.setFont(fontlabel);
		JpDown.add(labelName);
		JpDown.add(showName);
		
		
		JLabel labelAge = new JLabel("年龄:");
		labelAge.setFont(fontlabel);
		JpDown.add(labelAge);
		JpDown.add(showAge);
		
		
		JLabel labelTime = new JLabel("入职时间:");
		labelTime.setFont(fontlabel);
		JpDown.add(labelTime);
		JpDown.add(showTime);
		
		
		JLabel labelSex = new JLabel("性别:");
		labelSex.setFont(fontlabel);
		JpDown.add(labelSex);
		JpDown.add(showSex);
		
		JLabel labelTel = new JLabel("电话:");
		labelTel.setFont(fontlabel);
		JpDown.add(labelTel);
		JpDown.add(showTel);
		
		JLabel labelPost = new JLabel("职位:");
		labelPost.setFont(fontlabel);
		JpDown.add(labelPost);
		JpDown.add(showPost);
		
		JLabel labelDepNum = new JLabel("部门号:");
		labelDepNum.setFont(fontlabel);
		JpDown.add(labelDepNum);
		JpDown.add(showDepNum);
		
		JLabel labelDepName = new JLabel("部门名称:");
		labelDepName.setFont(fontlabel);
		JpDown.add(labelDepName);
		
		JpDown.add(showDepName);
		
		JButton Button4 = new JButton();
		Button4.setContentAreaFilled(false);
		Button4.setBorderPainted(false);
		JpDown.add(Button4);
		
		
		JButton Button1 = new JButton();
		JpDown.add(Button1);
		Button1.setContentAreaFilled(false);
		Button1.setBorderPainted(false);
		
		JButton Button2 = new JButton();
		JpDown.add(Button2);
		Button2.setContentAreaFilled(false);
		Button2.setBorderPainted(false);
		JButton Button3 = new JButton();
		Button3.setContentAreaFilled(false);
		Button3.setBorderPainted(false);
		JpDown.add(Button3);
		
		JButton noButton = new JButton("取 消");
		noButton.setFont(font);
		noButton.setBorderPainted(false);
		noButton.setContentAreaFilled(false);
		noButton.setForeground(Color.red);
		JpDown.add(noButton);
		
		JButton yesButton = new JButton("修 改");
		yesButton.setFont(font);
		yesButton.setBorderPainted(false);
		yesButton.setContentAreaFilled(false);
		yesButton.setForeground(Color.red);
		JpDown.add(yesButton);
		
		JpUP.add(JpDown);
		jf.add(JpUP);
		
		
		jf.repaint();
		jf.validate();
		
		noButton.addActionListener(new NoButtonEvent());
		yesButton.addActionListener(new YesButtonEvent());
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				int row = table.getSelectedRow();
			
				Object valueNum = table.getValueAt(row, 0);
				showNum.setText(valueNum.toString());
				Object valueName = table.getValueAt(row, 1);
				showName.setText(valueName.toString());
				Object valueAge = table.getValueAt(row, 2);
				showAge.setText(valueAge.toString());
				Object valueTime = table.getValueAt(row, 3);
				showTime.setText(valueTime.toString().substring(0, 4));
				Object valueSex = table.getValueAt(row, 4);
				showSex.setText(valueSex.toString());
				Object valueTel = table.getValueAt(row, 5);
				showTel.setText(valueTel.toString());
				Object valuePost = table.getValueAt(row, 6);
				showPost.setText(valuePost.toString());
				Object valueDepnum = table.getValueAt(row, 7);
				showDepNum.setText(valueDepnum.toString());
				Object valueDepName = table.getValueAt(row, 8);
				showDepName.setText(valueDepName.toString());
			  
			}
		});
		
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
			int rs = MysqlClient.EmpModifyInfo(showNum.getText(), showName.getText(), showAge.getText(), showTime.getText(), showSex.getText(),
							showTel.getText(), showPost.getText(), showDepNum.getText());
			if (rs == -1 || rs == 0){
				JOptionPane.showMessageDialog(null, "修改有误");
			}else{
				JOptionPane.showMessageDialog(null, "修改成功");
				Vector<Vector<String>> data = new Vector<>();
				Vector<String> columnNames = new Vector<>();
	 			data = MysqlClient.GetAllEmp(columnNames);
	 			new ModifyEmp(data,columnNames);
				jf.setVisible(false);
			}
		}
		
	}
	
	public static void main(String[] args){
		//new ModifyEmp().init("0412312","质管部");
	}
	
}