package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.User_dao;
import model.User;
import util.UserTools;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class User_delete extends JInternalFrame {
	private JTextField user_idText;
	private JTextField passwordText;
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
					User_delete frame = new User_delete();
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
	public User_delete() {
		getContentPane().setForeground(UIManager.getColor("Button.background"));
		setClosable(true);
		setIconifiable(true);
		setTitle("删除用户");
		setBounds(100, 100, 560, 438);
		
		JLabel lblNewLabel = new JLabel("账  号：");
		
		user_idText = new JTextField();
		user_idText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密   码");
		
		passwordText = new JTextField();
		passwordText.setColumns(10);
		
		JButton confirm_Button = new JButton("确  认");
		confirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 actionUserDelete(e);
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
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(76)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(confirm_Button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordText)
								.addComponent(user_idText, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
							.addContainerGap(224, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Reseting_Button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(106))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(96)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(user_idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirm_Button)
						.addComponent(Reseting_Button))
					.addGap(62))
		);
		getContentPane().setLayout(groupLayout);

	}

	protected void actionUserDelete(ActionEvent e) {
			String user_id = this.user_idText.getText();
			String password = this.passwordText.getText();
			user = new User(user_id,password,false);
			System.out.print(user+"\n");
			ut = new UserTools();
			ud = new User_dao();
			ArrayList<User> UserList;
			try {
				UserList = ut.loadBook();
				ud.setUserlist(UserList);
				if(ud.delete(user)) {
					JOptionPane.showConfirmDialog(null, "删除成功！！！");
					this.ResetingAction();
				}else{
					JOptionPane.showConfirmDialog(null, "用户不存在！！！");
					this.ResetingAction();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	public void ResetingAction() {
		// TODO Auto-generated method stub
		this.user_idText.setText("");
		this.passwordText.setText("");
	}
}
