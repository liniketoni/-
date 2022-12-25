package view;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.User_dao;
import model.User;
import util.UserTools;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class User_select extends JInternalFrame {
	private JTable userTypeTable;
	private JTextField user_idText;
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
					User_select frame = new User_select();
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
	public User_select() {
		setTitle("用戶信息查詢");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 676, 454);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("账 号： ");
		
		user_idText = new JTextField();
		user_idText.setColumns(10);
		
		JButton btnNewButton = new JButton("查 询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectaction(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(123)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(user_idText, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(98, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)))
					.addGap(91))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(user_idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(48)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addGap(54))
		);
		
		userTypeTable = new JTable();
		userTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8D26\u53F7", "\u5BC6\u7801", "\u7BA1\u7406\u5458"
			}
		));
		userTypeTable.getColumnModel().getColumn(0).setResizable(false);
		userTypeTable.getColumnModel().getColumn(1).setResizable(false);
		userTypeTable.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(userTypeTable);
		getContentPane().setLayout(groupLayout);
		this.fillTable("");
	}
	public void selectaction(ActionEvent evt) {
		// TODO Auto-generated method stub
		fillTable(this.user_idText.getText());
		
	}

	private void fillTable(String user_id) {
		System.out.print(user_id+"\n");
	  DefaultTableModel dtm = (DefaultTableModel)userTypeTable.getModel();
	  dtm.setRowCount(0);
		ut = new UserTools();
		ud = new User_dao();
		ArrayList<User> UserList;
		try {
			UserList = ut.loadBook();
			System.out.print(UserList.toString()+"\n");
			ud.setUserlist(UserList);
			if(!user_id.equals("")) {
				System.out.print(1+"\n");
				UserList=ud.User_Search(user_id);
				System.out.print(2+"\n");
			}
			for(User user:UserList) {
				Vector v = new Vector();
				v.add(user.getUser_id());
				v.add(user.getPass_word());
				v.add(user.isAdmin());
				dtm.addRow(v);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
