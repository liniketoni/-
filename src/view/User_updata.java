package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.User_dao;
import model.User;
import util.UserTools;

public class User_updata extends JInternalFrame {
	private JTextField user_idText;
	private JTextField old_passwordText;
	private JTextField new_passwordText;
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
					User_updata frame = new User_updata();
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
	public User_updata() {
		setIconifiable(true);
		setClosable(true);
		setTitle("用户信息修改");
		setBounds(100, 100, 644, 387);
		
		JLabel lblNewLabel = new JLabel("账  号：");
		
		user_idText = new JTextField();
		user_idText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("原密码：");
		
		old_passwordText = new JTextField();
		old_passwordText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("现密码：");
		
		new_passwordText = new JTextField();
		new_passwordText.setColumns(10);
		
		JButton UpDataButton = new JButton("修  改");
		UpDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpDataaction(e);
			}
		});
		
		JButton ResetingButton = new JButton("清  空");
		ResetingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetingAction();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(108)
					.addComponent(UpDataButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
					.addComponent(ResetingButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(114))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(166)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(new_passwordText))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(old_passwordText))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(user_idText, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(252, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(user_idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(old_passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(new_passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(UpDataButton)
						.addComponent(ResetingButton))
					.addGap(52))
		);
		getContentPane().setLayout(groupLayout);

	}
	public void UpDataaction(ActionEvent evt) {
		// TODO Auto-generated method stub
		String user_id = this.user_idText.getText();
		String old_password = this.old_passwordText.getText();
		String new_password = this.new_passwordText.getText();
		user = new User(user_id,old_password,false);
		ut = new UserTools();
		ud = new User_dao();
		try {
			ArrayList<User> UserList = ut.loadBook();
			ud.setUserlist(UserList);
			if(ud.updata(user, new_password)) {
				JOptionPane.showConfirmDialog(null, "修改成功！！！");
				this.ResetingAction();
			}else {
				JOptionPane.showConfirmDialog(null, "修改失败！！！");
				this.ResetingAction();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ResetingAction() {
		// TODO Auto-generated method stub
		this.user_idText.setText("");
		this.old_passwordText.setText("");
		this.new_passwordText.setText("");
	}

}
