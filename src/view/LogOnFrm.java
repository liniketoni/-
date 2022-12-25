package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import dao.User_dao;
import model.User;
import util.UserTools;
import java.awt.Color;
import javax.swing.JRadioButton;

public class LogOnFrm extends JFrame {

	private JPanel contentPane;
	private JTextField user_idTxt;
	private JPasswordField passwordTxt;
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
					LogOnFrm frame = new LogOnFrm();
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
	public LogOnFrm() {
		setResizable(false);
		setTitle("超级管理员登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("用 户 管 理 系 统");
		lblNewLabel.setForeground(new Color(204, 0, 0));
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 19));
		
		JLabel lblNewLabel_1 = new JLabel("账号：");
		
		user_idTxt = new JTextField();
		user_idTxt.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("密码：");
		
		passwordTxt = new JPasswordField();
		
		JButton LoginButton = new JButton("登  录");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getUserInfo(e);
			}
		});
		
		JButton ResettingButton = new JButton("重  置");
		ResettingButton.setForeground(new Color(255, 0, 0));
		ResettingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("普通");
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("超级");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(224)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(183)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordTxt)
								.addComponent(user_idTxt, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))))
					.addContainerGap(186, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(113)
					.addComponent(LoginButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
					.addComponent(ResettingButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(73))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(201)
					.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addGap(101)
					.addComponent(rdbtnNewRadioButton_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(160, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addComponent(lblNewLabel)
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(user_idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(rdbtnNewRadioButton_1))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(LoginButton)
						.addComponent(ResettingButton))
					.addGap(21))
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
	}

	/**
	 * 获取用户信息进行登录
	 * @param e
	 */
	public void getUserInfo(ActionEvent evt){
		// TODO Auto-generated method stub
		String user_id = this.user_idTxt.getText();
		String password =new String(this.passwordTxt.getPassword());
		user = new User(user_id,password,true);
		System.out.print(user+"\n");
		ut = new UserTools();
		ud = new User_dao();
		try {
			ArrayList<User>UserList = ut.loadBook();
			ud.setUserlist(UserList);
			if(ud.Login(user)) {
				dispose();
				new MainFrm().setVisible(true);
			}else {
				JOptionPane.showConfirmDialog(null, "登录失败！！！");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 重置事件处理
	 * @param e
	 */
	public void resetValueActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.user_idTxt.setText("");
		this.passwordTxt.setText("");
		
		
	}
}
