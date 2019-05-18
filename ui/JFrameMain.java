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
	private JMenu mnBook;
	private JMenuItem mntmCreateBook;
	private JSeparator separator_1;
	private JMenuItem mntmBookItemList;
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
		
		mnBook = new JMenu("Book");
		mnManage.add(mnBook);
		
		mntmBook = new JMenuItem("Book List");
		mnBook.add(mntmBook);
		
		mntmCreateBook = new JMenuItem("Create Book");
		mntmCreateBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCreateBook_actionPerformed(e);
			}
		});
		mnBook.add(mntmCreateBook);
		
		separator_1 = new JSeparator();
		mnBook.add(separator_1);
		
		mntmBookItemList = new JMenuItem("Book Item List");
		mntmBookItemList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmBookItemList_actionPerformed(e);
			}
		});
		mnBook.add(mntmBookItemList);
		mntmBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmBook_actionPerformed(e);
			}
		});
		
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
		
		JMenu mnReceiptList = new JMenu("Receipt List");
		menuBar.add(mnReceiptList);
		
		JMenuItem mntmCheckoutList = new JMenuItem("Checkout List");
		mntmCheckoutList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmCheckoutList_actionPerformed(arg0);
			}
		});
		mnReceiptList.add(mntmCheckoutList);
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
	
	public void mntmCreateBook_actionPerformed(ActionEvent e) { 
		JinternalFrameCreateBook jinternalFrameCreateBook = new JinternalFrameCreateBook();
		jDesktopPaneMain.add(jinternalFrameCreateBook);
		jinternalFrameCreateBook.setVisible(true);
	}
	
	public void mntmBookItemList_actionPerformed(ActionEvent e) {
		JinternalFrameListBookItem jinternalFrameListBookItem = new JinternalFrameListBookItem();
		jDesktopPaneMain.add(jinternalFrameListBookItem);
		jinternalFrameListBookItem.setVisible(true);
	}
	
	public void mntmCheckoutList_actionPerformed(ActionEvent arg0) {
		JInternalFrameCheckList jInternalFrameCheckList = new JInternalFrameCheckList();
		jDesktopPaneMain.add(jInternalFrameCheckList);
		jInternalFrameCheckList.setVisible(true);
	}
}
