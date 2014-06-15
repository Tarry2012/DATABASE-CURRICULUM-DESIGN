package SQL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.*;

import SQL.ModifyEmp.NoButtonEvent;
import SQL.ModifyEmp.YesButtonEvent;

public class ChangeReward{
	JFrame jf = new JFrame("修改员工信息");
//	JTable table;
//	Object columnTitle={"员工编号","员工姓名","员工年龄","入职时间","性别","电话","职位","部门号","部门名称"};
	
	JLabel showNum = new JLabel();
	JLabel showName = new JLabel();
	JTextField showFestival = new JTextField(15);
	JTextField showUsually = new JTextField(15);
	JTextField showReward = new JTextField(15);
	JTextField showVacate = new JTextField(15);
	JTextField showComelate = new JTextField(15);
	JTextField showDesert = new JTextField(15);
	
	
	public ChangeReward(Vector<Vector<String>> data,Vector<String> columnNames) {
		
		jf.setLayout(new BorderLayout(30,5));
		final JTable table = new JTable(data,columnNames);
	
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLocation(400, 200);
		jf.setSize(800, 500);
		jf.setVisible(true);
		
		JPanel JpUP = new JPanel(new GridLayout(0, 1));
		JpUP.add(new JScrollPane(table));

		
		JPanel JpDown = new JPanel(new GridLayout(0, 4));
		Font font = new Font("宋体",Font.BOLD,20);
		JLabel labelNum = new JLabel("员工号:");
		labelNum.setFont(font);
		JpDown.add(labelNum);
		
	
		JpDown.add(showNum);
		
		
		JLabel labelName = new JLabel("员工姓名:");
		labelName.setFont(font);
		JpDown.add(labelName);
		JpDown.add(showName);
		
		
		JLabel labelFestival = new JLabel("节日加班次数:");
		labelFestival.setFont(font);
		JpDown.add(labelFestival);
		JpDown.add(showFestival);
		
		
		JLabel labelUsually = new JLabel("普通加班次数:");
		labelUsually.setFont(font);
		JpDown.add(labelUsually);
		JpDown.add(showUsually);
		
		JLabel labelReward = new JLabel("奖金金额:");
		labelReward.setFont(font);
		JpDown.add(labelReward);
		JpDown.add(showReward);
		
		JLabel labelVacate = new JLabel("请假次数:");
	    labelVacate.setFont(font);
		JpDown.add(labelVacate);
		JpDown.add(showVacate);
		
		JLabel labelComelate = new JLabel("迟到次数:");
		labelComelate.setFont(font);
		JpDown.add(labelComelate);
		JpDown.add(showComelate);
		
		JLabel labelDesert = new JLabel("旷班次数:");
		labelDesert.setFont(font);
		JpDown.add(labelDesert);
		JpDown.add(showDesert);
		
		JButton noButton = new JButton("取消");
		noButton.setFont(font);
		noButton.setBorderPainted(false);
		noButton.setContentAreaFilled(false);
		noButton.setForeground(Color.red);
		JpDown.add(noButton);
		
		JButton Button = new JButton();
		Button.setBorderPainted(false);
		Button.setContentAreaFilled(false);
		JpDown.add(Button);
		
		JButton Button2 = new JButton();
		Button2.setBorderPainted(false);
		Button2.setContentAreaFilled(false);
		JpDown.add(Button2);
		
		JButton yesButton = new JButton("修改");
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
				Object valueFestival = table.getValueAt(row, 2);
				showFestival.setText(valueFestival.toString());
				Object valueUsually = table.getValueAt(row, 3);
				showUsually.setText(valueUsually.toString());
				Object valueReward = table.getValueAt(row, 4);
				showReward.setText(valueReward.toString());
				Object valueVacate = table.getValueAt(row, 5);
				showVacate.setText(valueVacate.toString());
				Object valueComelate = table.getValueAt(row, 6);
				showComelate.setText(valueComelate.toString());
				Object valueDesert = table.getValueAt(row, 7);
				showDesert.setText(valueDesert.toString());
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
			int rs = MysqlClient.ModifyReward(showNum.getText(), showName.getText(), showFestival.getText(), showUsually.getText(), 
					showReward.getText(),showVacate.getText(),showComelate.getText(),showDesert.getText());
			if (rs == -1 || rs == 0){
				JOptionPane.showMessageDialog(null, "修改有误");
			}else{
				JOptionPane.showMessageDialog(null, "修改成功");
				Vector<Vector<String>> data = new Vector<>();
				Vector<String> columnNames = new Vector<>();
	 			data = MysqlClient.GetAllReward(columnNames);
	 			new ChangeReward(data,columnNames);
				jf.setVisible(false);
			}
		}
		
	}
	public static void main(String [] args){
		
	}
}