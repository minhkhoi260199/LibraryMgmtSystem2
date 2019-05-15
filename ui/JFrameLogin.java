package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Employee;
import models.EmployeeModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameLogin extends JFrame {

	private JPanel contentPane;
	private JTextField jtextFieldUserName;
	private JPasswordField jpasswordFieldPassWord;
	//private JButton jbtnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameLogin frame = new JFrameLogin();
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
	public JFrameLogin() {
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 11, 70, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 45, 70, 20);
		contentPane.add(lblPassword);
		
		jtextFieldUserName = new JTextField();
		jtextFieldUserName.setBounds(90, 11, 224, 29);
		contentPane.add(jtextFieldUserName);
		jtextFieldUserName.setColumns(10);
		
		jpasswordFieldPassWord = new JPasswordField();
		jpasswordFieldPassWord.setBounds(90, 45, 224, 29);
		contentPane.add(jpasswordFieldPassWord);
		
		JButton jbtnLogin = new JButton("Login");
		jbtnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnLogin_actionPerformed(arg0);
			}
		});
		jbtnLogin.setBounds(90, 86, 98, 29);
		contentPane.add(jbtnLogin);
	}

	private void jbtnLogin_actionPerformed(ActionEvent arg0) {
		EmployeeModel employeeModel = new EmployeeModel();
		String username = jtextFieldUserName.getText().trim();
		String password = String.valueOf(jpasswordFieldPassWord.getPassword()).trim();
		Employee employee = employeeModel.login(username, password);
		if(employee == null) {
			JOptionPane.showMessageDialog(null, "Username or password are incorrect!");
		}else {
			this.setVisible(false);
			JFrameMain jFrameMain = new JFrameMain();
			jFrameMain.setVisible(true);
		}
	}	
}
