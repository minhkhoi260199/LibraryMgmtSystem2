package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Book;
import entities.BookItem;
import entities.CheckOut;
import entities.Customer;
import entities.Detail;
import models.BookItemModel;
import models.BookModel;
import models.CheckOutModel;
import models.CustomerModel;
import models.DetailModel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

public class JInternalFrameCheckOut extends JInternalFrame {
	private JTable jtableList;
	private JTextField jtextFieldISBN;
	private JButton jbuttonSearch;
	private JTable jtableBorrowList;

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
					JInternalFrameCheckOut frame = new JInternalFrameCheckOut();
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
	public JInternalFrameCheckOut() {
		setClosable(true);
		setTitle("Check Out Book");
		getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 15));
		setBounds(100, 100, 1076, 612);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 97, 446, 370);
		getContentPane().add(scrollPane);
		
		jtableList = new JTable();
		jtableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtableList.setFont(new Font("SansSerif", Font.PLAIN, 15));
		scrollPane.setViewportView(jtableList);
		
		JLabel jlabelISBN = new JLabel("Find book:");
		jlabelISBN.setFont(new Font("SansSerif", Font.PLAIN, 16));
		jlabelISBN.setBounds(20, 62, 79, 24);
		getContentPane().add(jlabelISBN);
		
		jtextFieldISBN = new JTextField();
		jtextFieldISBN.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldISBN.setColumns(10);
		jtextFieldISBN.setBounds(100, 59, 176, 28);
		getContentPane().add(jtextFieldISBN);
		
		jbuttonSearch = new JButton("Search");
		jbuttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonSearch_actionPerformed(e);
			}
		});
		jbuttonSearch.setFont(new Font("SansSerif", Font.PLAIN, 13));
		jbuttonSearch.setBounds(279, 59, 73, 28);
		getContentPane().add(jbuttonSearch);
		
		jbuttonAll = new JButton("Show All");
		jbuttonAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonAll_actionPerformed(arg0);
			}
		});
		jbuttonAll.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbuttonAll.setBounds(30, 478, 115, 28);
		getContentPane().add(jbuttonAll);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(575, 97, 448, 370);
		getContentPane().add(scrollPane_1);
		
		jtableBorrowList = new JTable();
		jtableBorrowList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jtableBorrowList_mouseClicked(arg0);
			}
		});
		scrollPane_1.setViewportView(jtableBorrowList);
		
		JLabel jlabelCallnumber = new JLabel("Callnumber");
		jlabelCallnumber.setFont(new Font("SansSerif", Font.PLAIN, 16));
		jlabelCallnumber.setBounds(616, 14, 88, 37);
		getContentPane().add(jlabelCallnumber);
		
		jtextFieldCallnumber = new JTextField();
		jtextFieldCallnumber.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldCallnumber.setColumns(10);
		jtextFieldCallnumber.setBounds(705, 14, 166, 37);
		getContentPane().add(jtextFieldCallnumber);
		
		JButton jbuttonAddCallnumber = new JButton("Add");
		jbuttonAddCallnumber.setBackground(new Color(152, 251, 152));
		jbuttonAddCallnumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonAddCallnumber_actionPerformed(arg0);
			}
		});
		jbuttonAddCallnumber.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbuttonAddCallnumber.setBounds(888, 14, 90, 37);
		getContentPane().add(jbuttonAddCallnumber);
		
		jbuttonDelete = new JButton("Remove from list");
		jbuttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonDelete_actionPerformed(arg0);
			}
		});
		jbuttonDelete.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbuttonDelete.setBounds(870, 477, 153, 30);
		getContentPane().add(jbuttonDelete);
		
		jtextFieldUserId = new JTextField();
		jtextFieldUserId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldUserId.setColumns(10);
		jtextFieldUserId.setBounds(82, 8, 63, 37);
		getContentPane().add(jtextFieldUserId);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblUserId.setBounds(20, 11, 58, 28);
		getContentPane().add(lblUserId);
		
		JButton jbuttonCheckOut = new JButton("Check Out");
		jbuttonCheckOut.setBackground(new Color(255, 0, 0));
		jbuttonCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonCheckOut_actionPerformed(arg0);
			}
		});
		jbuttonCheckOut.setFont(new Font("SansSerif", Font.BOLD, 17));
		jbuttonCheckOut.setBounds(456, 518, 141, 43);
		getContentPane().add(jbuttonCheckOut);
		
		JButton jbuttonTo = new JButton("Add to ->");
		jbuttonTo.setBackground(new Color(124, 252, 0));
		jbuttonTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonTo_actionPerformed(e);
			}
		});
		jbuttonTo.setBounds(488, 258, 79, 29);
		getContentPane().add(jbuttonTo);
		
		JLabel lblBorrowList = new JLabel("Borrow List :");
		lblBorrowList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBorrowList.setForeground(Color.RED);
		lblBorrowList.setBounds(575, 62, 126, 28);
		getContentPane().add(lblBorrowList);
		
		jcomboBoxFillter = new JComboBox();
		jcomboBoxFillter.setBounds(384, 59, 94, 28);
		getContentPane().add(jcomboBoxFillter);
		
		JLabel lblBy = new JLabel("by :");
		lblBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBy.setBounds(359, 61, 30, 24);
		getContentPane().add(lblBy);
		
		loadInternalJFrame();
	}
	//Global variable
	List<BookItem> bookItems1 = new ArrayList<BookItem>();
	private JTextField jtextFieldCallnumber;
	private JTextField jtextFieldUserId;
	private JButton jbuttonAll;
	private JButton jbuttonDelete;
	private JComboBox jcomboBoxFillter;
	//display data in the InternalJFrame
	private void loadInternalJFrame() {
		BookModel bookModel = new BookModel();
		fillDataToTable(bookModel.findAll());
		jbuttonDelete.setEnabled(false);
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
		comboBoxModel.addElement("Title");
		comboBoxModel.addElement("ISBN");
		jcomboBoxFillter.setModel(comboBoxModel);
	}
	//load data to List table function
	private void fillDataToTable(List<Book> books) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("ISBN");
		defaultTableModel.addColumn("Title");
		for(Book book : books) {
			defaultTableModel.addRow(new Object[] {
					book.getIsbn(),
					book.getTitle(),
			});
		}
		jtableList.setModel(defaultTableModel);
		jtableList.getTableHeader().setReorderingAllowed(false);
	}
	//load data to Borrow List table
	private void fillDataToTable1(List<BookItem> bookItems) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Callnumber");
		defaultTableModel.addColumn("ISBN");
		defaultTableModel.addColumn("Title");
		for(BookItem bookItem : bookItems) {
			BookModel bookModel = new BookModel();
			Book book = bookModel.find(bookItem.getIsbn());
			defaultTableModel.addRow(new Object[] {
					bookItem.getCallnumber(),
					bookItem.getIsbn(),
					book.getTitle()
			});
		}
		jtableBorrowList.setModel(defaultTableModel);
		jtableBorrowList.getTableHeader().setReorderingAllowed(false);
	}
	//event click to add data to BorrowList table
	private void jbuttonTo_actionPerformed(ActionEvent e) {
		int selectedRow = jtableList.getSelectedRow();
		if(selectedRow != -1) {
			String isbn = (jtableList.getValueAt(selectedRow, 0)).toString();
			BookItemModel bookItemModel = new BookItemModel();
			BookItem bookItem = bookItemModel.find(isbn);
			if(bookItem != null) {
				if(bookItemModel.updateStatusToOff(bookItem.getCallnumber())) {
					bookItems1.add(bookItem);
				}
			}else {
				JOptionPane.showMessageDialog(null, "This book is not available right now.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please select your book.");
		}
		fillDataToTable1(bookItems1);
	}
	//event click of AddCallnumber button to add data to BorrowList table
	private void jbuttonAddCallnumber_actionPerformed(ActionEvent arg0) {
		String callnumber = jtextFieldCallnumber.getText();
		if(!callnumber.isEmpty()) {
			BookItemModel bookItemModel = new BookItemModel();
			BookItem bookItem = bookItemModel.findCallnumber(callnumber);
			if(bookItem != null) {
				if(bookItemModel.updateStatusToOff(bookItem.getCallnumber())) {
					bookItems1.add(bookItem);
				}
			}else {
				JOptionPane.showMessageDialog(null, "The book was borrowed. Please enter a different callnumber.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please enter your callnumber.");
		}
		fillDataToTable1(bookItems1);
	}
	//event click of BorrowList table to add data to jtextFieldBorrowBook
	private void jtableBorrowList_mouseClicked(MouseEvent arg0) {
		jbuttonDelete.setEnabled(true);
		
	}
	//event click to delete book in BorrowList table
	private void jbuttonDelete_actionPerformed(ActionEvent arg0) {
		BookItemModel bookItemModel = new BookItemModel();
		int selectedRow = jtableBorrowList.getSelectedRow();
		if(selectedRow != -1) {
			BookItem bookItem = bookItems1.get(selectedRow);
			if(bookItemModel.updateStatusToOn(bookItem.getCallnumber())) {
				bookItems1.remove(selectedRow);
				fillDataToTable1(bookItems1);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please select your book.");
		}
	}
	//event click to create a checkout 
	private void jbuttonCheckOut_actionPerformed(ActionEvent arg0) {
		//check if the list of borrowed book is empty or not
		if(bookItems1.size() > 0) {
			//check if the user id field has value or not
			if(!jtextFieldUserId.getText().isEmpty()) {
				try {
					int user_id = Integer.parseInt(jtextFieldUserId.getText().toString());
					CustomerModel customerModel = new CustomerModel();
					Customer customer = customerModel.find(user_id);
					//check if the user id is valid or not
					if(customer != null) {
						JFrameMain jFrameMain = (JFrameMain) this.getTopLevelAncestor();
						int employee_id = jFrameMain.getEmployee_id();
						CheckOut checkOut = new CheckOut();
						Date date = new Date();
						CheckOutModel checkOutModel = new CheckOutModel();
						checkOut.setBorrow_date(date);
						checkOut.setEmployee_id(employee_id);
						int checkout_id = checkOutModel.createGetId(checkOut);
						//check the checkout_id
						if(checkout_id != 0) {
							for(BookItem bookItem : bookItems1) {
								Detail detail = new Detail();
								detail.setCallnumber(bookItem.getCallnumber());
								detail.setCheckout_id(checkout_id);
								BookModel bookModel = new BookModel();
								Book book = bookModel.find(bookItem.getIsbn());
								detail.setPayment(book.getPrice()/10);
								detail.setUser_id(user_id);
								DetailModel detailModel = new DetailModel();
								detailModel.create(detail);
							}
							bookItems1.clear();
							jtextFieldCallnumber.setText("");
							jtextFieldISBN.setText("");
							jtextFieldUserId.setText("");
							fillDataToTable1(bookItems1);
							JOptionPane.showMessageDialog(null, "Creating checkout successfully.");
						//show message if the check out id is 0
						}else {
							JOptionPane.showMessageDialog(null, "Creating checkout failed.");
						}
					//if the user id is invalid => show message
					}else {
						JOptionPane.showMessageDialog(null, "The user id is invalid.");
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "The user id only contains number.");
				}
			//if the field is empty => show message
			}else {
				JOptionPane.showMessageDialog(null, "Please enter user id.");
			}
		//if the borrowed book is empty => show message
		}else {
			JOptionPane.showMessageDialog(null, "Please select your book you want to borrow please.");
		}
	}
	//event get all of book
	private void jbuttonAll_actionPerformed(ActionEvent arg0) {
		BookModel bookModel = new BookModel();
		fillDataToTable(bookModel.findAll());
	}
	//event search ISBN
	private void jbuttonSearch_actionPerformed(ActionEvent e) {
		String kw = jtextFieldISBN.getText().toString();
		String type = (String) jcomboBoxFillter.getSelectedItem().toString().toLowerCase(); // noi chuoi
		if(!kw.isEmpty()) {
			List<Book> books = new ArrayList<Book>();
			BookModel bookModel = new BookModel();
			books = bookModel.search(type, kw);
			if(books != null) {
				fillDataToTable(books);
			}else {
				JOptionPane.showMessageDialog(null, "No result!");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please enter the isbn.");
		}
	}
}
