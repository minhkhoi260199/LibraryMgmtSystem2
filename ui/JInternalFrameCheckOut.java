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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JInternalFrameCheckOut extends JInternalFrame {
	private JTable jtableList;
	private JTextField jtextFieldISBN;
	private JButton jbuttonSearch;
	private JTable jtableBorrowList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		scrollPane.setBounds(32, 119, 448, 359);
		getContentPane().add(scrollPane);
		
		jtableList = new JTable();
		jtableList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jtableList_mouseClicked(arg0);
			}
		});
		jtableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtableList.setFont(new Font("SansSerif", Font.PLAIN, 15));
		scrollPane.setViewportView(jtableList);
		
		JLabel jlabelISBN = new JLabel("ISBN");
		jlabelISBN.setFont(new Font("SansSerif", Font.PLAIN, 16));
		jlabelISBN.setBounds(69, 20, 58, 28);
		getContentPane().add(jlabelISBN);
		
		jtextFieldISBN = new JTextField();
		jtextFieldISBN.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldISBN.setColumns(10);
		jtextFieldISBN.setBounds(120, 14, 243, 37);
		getContentPane().add(jtextFieldISBN);
		
		jbuttonSearch = new JButton("Search");
		jbuttonSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbuttonSearch.setBounds(375, 14, 90, 37);
		getContentPane().add(jbuttonSearch);
		
		JButton jbuttonAll = new JButton("All");
		jbuttonAll.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbuttonAll.setBounds(477, 14, 90, 37);
		getContentPane().add(jbuttonAll);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(577, 121, 448, 359);
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
		jlabelCallnumber.setBounds(579, 20, 88, 28);
		getContentPane().add(jlabelCallnumber);
		
		jtextFieldCallnumber = new JTextField();
		jtextFieldCallnumber.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldCallnumber.setColumns(10);
		jtextFieldCallnumber.setBounds(667, 11, 243, 37);
		getContentPane().add(jtextFieldCallnumber);
		
		JButton jbuttonAddCallnumber = new JButton("Add");
		jbuttonAddCallnumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonAddCallnumber_actionPerformed(arg0);
			}
		});
		jbuttonAddCallnumber.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbuttonAddCallnumber.setBounds(925, 11, 90, 37);
		getContentPane().add(jbuttonAddCallnumber);
		
		jtextFieldBorrowBook = new JTextField();
		jtextFieldBorrowBook.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldBorrowBook.setColumns(10);
		jtextFieldBorrowBook.setBounds(667, 63, 243, 37);
		getContentPane().add(jtextFieldBorrowBook);
		
		JButton jbuttonDelete = new JButton("Delete");
		jbuttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonDelete_actionPerformed(arg0);
			}
		});
		jbuttonDelete.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbuttonDelete.setBounds(925, 63, 90, 37);
		getContentPane().add(jbuttonDelete);
		
		JLabel lblBorrower = new JLabel("Borrower");
		lblBorrower.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBorrower.setBounds(202, 20, 74, 28);
		getContentPane().add(lblBorrower);
		
		jtextFieldUserId = new JTextField();
		jtextFieldUserId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldUserId.setColumns(10);
		jtextFieldUserId.setBounds(277, 63, 243, 37);
		getContentPane().add(jtextFieldUserId);
		
		JLabel lblUserId = new JLabel("User Id");
		lblUserId.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblUserId.setBounds(204, 69, 58, 28);
		getContentPane().add(lblUserId);
		
		JButton jbuttonCheckOut = new JButton("Check Out");
		jbuttonCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonCheckOut_actionPerformed(arg0);
			}
		});
		jbuttonCheckOut.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbuttonCheckOut.setBounds(884, 506, 141, 37);
		getContentPane().add(jbuttonCheckOut);
		
		JButton jbuttonTo = new JButton("To");
		jbuttonTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonTo_actionPerformed(e);
			}
		});
		jbuttonTo.setBounds(496, 257, 58, 29);
		getContentPane().add(jbuttonTo);
		
		loadInternalJFrame();
	}
	//Global variable
	List<BookItem> bookItems1 = new ArrayList<BookItem>();
	private JTextField jtextFieldCallnumber;
	private JTextField jtextFieldBorrowBook;
	private JTextField jtextFieldUserId;
	//display data in the InternalJFrame
	private void loadInternalJFrame() {
		BookModel bookModel = new BookModel();
		fillDataToTable(bookModel.findAll());
	}
	//load data to List table function
	private void fillDataToTable(List<Book> books) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("ISBN");
		defaultTableModel.addColumn("Name");
		for(Book book : books) {
			defaultTableModel.addRow(new Object[] {
					book.getIsbn(),
					book.getName(),
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
		for(BookItem bookItem : bookItems) {
			defaultTableModel.addRow(new Object[] {
					bookItem.getCallnumber(),
					bookItem.getIsbn(),
			});
		}
		jtableBorrowList.setModel(defaultTableModel);
		jtableBorrowList.getTableHeader().setReorderingAllowed(false);
	}
	//event click of List table to add data to BorrowList table
	private void jtableList_mouseClicked(MouseEvent arg0) {
		int selectedRow = jtableList.getSelectedRow();
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
	}
	private void jbuttonTo_actionPerformed(ActionEvent e) {
		fillDataToTable1(bookItems1);
	}
	//event click of BorrowList table to add data to jtextFieldBorrowBook
	private void jtableBorrowList_mouseClicked(MouseEvent arg0) {
		int selectedRow = jtableBorrowList.getSelectedRow();
		String isbn = (jtableBorrowList.getValueAt(selectedRow, 1).toString());
		BookModel bookModel = new BookModel();
		Book book = bookModel.find(isbn);
		jtextFieldBorrowBook.setText(book.getName());
	}
	//event click of AddCallnumber button to add data to BorrowList table
	private void jbuttonAddCallnumber_actionPerformed(ActionEvent arg0) {
		String callnumber = jtextFieldCallnumber.getText();
		BookItemModel bookItemModel = new BookItemModel();
		BookItem bookItem = bookItemModel.findCallnumber(callnumber);
		if(bookItem != null) {
			if(bookItemModel.updateStatusToOff(bookItem.getCallnumber())) {
				bookItems1.add(bookItem);
			}
		}else {
			JOptionPane.showMessageDialog(null, "The book was borrowed. Please enter a different callnumber.");
		}
		fillDataToTable1(bookItems1);
	}
	//event delete book from borrow list
	//event click to delete book in BorrowList table
	private void jbuttonDelete_actionPerformed(ActionEvent arg0) {
		BookItemModel bookItemModel = new BookItemModel();
		int selectedRow = jtableBorrowList.getSelectedRow();
		BookItem bookItem = bookItems1.get(selectedRow);
		if(bookItemModel.updateStatusToOn(bookItem.getCallnumber())) {
			bookItems1.remove(selectedRow);
			fillDataToTable1(bookItems1);
		}
	}
	//event click to create a checkout 
	private void jbuttonCheckOut_actionPerformed(ActionEvent arg0) {
		//check if the list of borrowed book is empty or not
		if(bookItems1.size() > 0) {
			//check if the user id field has value or not
			if(!jtextFieldUserId.getText().isEmpty()) {
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
							detail.setPayment(book.getPrice());
							detail.setUser_id(user_id);
							DetailModel detailModel = new DetailModel();
							detailModel.create(detail);
						}
						JOptionPane.showMessageDialog(null, "Creating checkout successfully.");
					//show message if the check out id is 0
					}else {
						JOptionPane.showMessageDialog(null, "Creating checkout failed.");
					}
				//if the user id is invalid => show message
				}else {
					JOptionPane.showMessageDialog(null, "The user id is invalid.");
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
}
