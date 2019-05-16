package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class JFrameMain extends JFrame {

	private JPanel contentPane;
	private JDesktopPane jDesktopPaneMain;
	private JMenu mnChecking;
	private JMenu mnDetail;
	private JMenu mnManage;
	private JMenuItem mntmCategory;
	private JMenuItem mntmAuthor;
	private JMenuItem mntmBook;
	private JMenuItem mntmEmployee;
	private JMenuItem mntmCustomer;
	private JMenuItem mntmCheckOut;
	private JMenuItem mntmCheckIn;
	private int employee_id;

	
	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

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
					JFrameMain frame = new JFrameMain();
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
	public JFrameMain() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnChecking = new JMenu("Checking Book");
		menuBar.add(mnChecking);
		
		mntmCheckOut = new JMenuItem("Check Out");
		mntmCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCheckOut_actionPerformed(e);
			}
		});
		mnChecking.add(mntmCheckOut);
		
		mntmCheckIn = new JMenuItem("Check In");
		mnChecking.add(mntmCheckIn);
		
		mnDetail = new JMenu("Detail");
		menuBar.add(mnDetail);
		
		mnManage = new JMenu("Manage");
		menuBar.add(mnManage);
		
		mntmBook = new JMenuItem("Book");
		mntmBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmBook_actionPerformed(e);
			}
		});
		mnManage.add(mntmBook);
		
		mntmCategory = new JMenuItem("Category");
		mntmCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCategory_actionPerformed(e);
			}
		});
		mnManage.add(mntmCategory);
		
		mntmAuthor = new JMenuItem("Author");
		mntmAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmAuthor_actionPerformed(e);
			}
		});
		mnManage.add(mntmAuthor);
		
		JSeparator separator = new JSeparator();
		mnManage.add(separator);
		
		mntmEmployee = new JMenuItem("Employee");
		mntmEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmEmployee_actionPerformed(e);
			}
		});
		mnManage.add(mntmEmployee);
		
		mntmCustomer = new JMenuItem("Customer");
		mntmCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCustomer_actionPerformed(e);
			}
		});
		mnManage.add(mntmCustomer);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		jDesktopPaneMain = new JDesktopPane();
		contentPane.add(jDesktopPaneMain, BorderLayout.CENTER);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void mntmBook_actionPerformed(ActionEvent e) {
		JInternalFrameBook jInternalFrameBook = new JInternalFrameBook();
		jDesktopPaneMain.add(jInternalFrameBook);
		jInternalFrameBook.setVisible(true);
	}
	
	public void mntmCategory_actionPerformed(ActionEvent e) {
		JinternalFrameCategory jinternalFrameCategory = new JinternalFrameCategory();
		jDesktopPaneMain.add(jinternalFrameCategory);
		jinternalFrameCategory.setVisible(true);
	}
	
	public void mntmAuthor_actionPerformed(ActionEvent e) {
		JInternalFrameAuthorList jInternalFrameAuthorList = new JInternalFrameAuthorList();
		jDesktopPaneMain.add(jInternalFrameAuthorList);
		jInternalFrameAuthorList.setVisible(true);
	}
	
	public void mntmEmployee_actionPerformed(ActionEvent e) {
		JInternalFrameEmployee jInternalFrameEmployee = new JInternalFrameEmployee();
		jDesktopPaneMain.add(jInternalFrameEmployee);
		jInternalFrameEmployee.setVisible(true);
	}
	
	public void mntmCustomer_actionPerformed(ActionEvent e) {
		JInternalFrameAddCustomer jInternalFrameAddCustomer = new JInternalFrameAddCustomer();
		jDesktopPaneMain.add(jInternalFrameAddCustomer);
		jInternalFrameAddCustomer.setVisible(true);
	}
	
	public void mntmCheckOut_actionPerformed(ActionEvent e) {
		JInternalFrameCheckOut jInternalFrameCheckOut = new JInternalFrameCheckOut();
		jDesktopPaneMain.add(jInternalFrameCheckOut);
		jInternalFrameCheckOut.setVisible(true);
	}
}
