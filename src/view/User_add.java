package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.User_dao;
import model.User;
import util.UserTools;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class User_add extends JInternalFrame {
	private JTextField user_idText;
	private JPasswordField passwordText_1;
	private JPasswordField passwordText;
	public User user;
	public User_dao ud;
	public UserTools ut;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_add frame = new User_add();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public User_add() {
		setIconifiable(true);
		setClosable(true);
		setTitle("添加用户");
		setBounds(100, 100, 584, 457);
		
		JLabel lblNewLabel = new JLabel("账  号：");
		
		JLabel lblNewLabel_1 = new JLabel("密  码：");
		
		JLabel lblNewLabel_2 = new JLabel("密  码： ");
		
		user_idText = new JTextField();
		user_idText.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("普通用户");
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("管理员用户");
		
		passwordText_1 = new JPasswordField();
		
		passwordText = new JPasswordField();
		
		JButton confirm_Button = new JButton("确  定");
		confirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionUserAdd(e);
			}
		});
		
		JButton Reseting_Button = new JButton("清  空");
		Reseting_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetingAction();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(158)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(user_idText, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
							.addComponent(passwordText_1))
						.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(202, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(107, Short.MAX_VALUE)
					.addComponent(rdbtnNewRadioButton_1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(105)
					.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(106))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(76)
					.addComponent(confirm_Button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
					.addComponent(Reseting_Button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(69))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(99)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(user_idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordText_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(rdbtnNewRadioButton_1))
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirm_Button)
						.addComponent(Reseting_Button))
					.addGap(40))
		);
		getContentPane().setLayout(groupLayout);

		
	}

	public void ResetingAction() {
		// TODO Auto-generated method stub
		this.user_idText.setText("");
		this.passwordText.setText("");
	}

	public void actionUserAdd(ActionEvent evt) {
		// TODO Auto-generated method stub
		String user_id = this.user_idText.getText();
		String password = new String(this.passwordText.getPassword());
		user = new User(user_id,password,false);
		System.out.print(user+"\n");
		ut = new UserTools();
		ud = new User_dao();
		try {
			ArrayList<User>UserList = ut.loadBook();
			ud.setUserlist(UserList);
			if(ud.add(user)) {
				JOptionPane.showConfirmDialog(null, "添加成功！！！");
				this.ResetingAction();
			}else {
				JOptionPane.showConfirmDialog(null, "添加失败！！！");
				this.ResetingAction();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
