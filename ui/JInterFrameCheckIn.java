package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import entities.Book;
import entities.BookItem;
import entities.CheckOut;
import entities.Detail;
import models.BookItemModel;
import models.BookModel;
import models.CheckOutModel;
import models.DetailModel;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JInterFrameCheckIn extends JInternalFrame {
	private JTable jtableCheckOutList;
	private int limitBorrowDay = 5;
	private int feePerDay = 1000;
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
					JInterFrameCheckIn frame = new JInterFrameCheckIn();
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
	public JInterFrameCheckIn() {
		setClosable(true);
		setTitle("CheckIn ");
		setBounds(100, 100, 1235, 579);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 102, 561, 349);
		getContentPane().add(scrollPane);
		
		jtableCheckOutList = new JTable();
		jtableCheckOutList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(jtableCheckOutList);
		
		jbuttonToStart = new JButton("<<");
		jbuttonToStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonToStart_actionPerformed(arg0);
			}
		});
		jbuttonToStart.setBounds(6, 463, 90, 40);
		getContentPane().add(jbuttonToStart);
		
		jbuttonPrevious = new JButton("<");
		jbuttonPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonPrevious_actionPerformed(arg0);
			}
		});
		jbuttonPrevious.setBounds(107, 463, 53, 40);
		getContentPane().add(jbuttonPrevious);
		
		jbuttonLast = new JButton(">>");
		jbuttonLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonLast_actionPerformed(e);
			}
		});
		jbuttonLast.setBounds(318, 463, 90, 40);
		getContentPane().add(jbuttonLast);
		
		jbuttonNext = new JButton(">");
		jbuttonNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonNext_actionPerformed(e);
			}
		});
		jbuttonNext.setBounds(253, 463, 53, 40);
		getContentPane().add(jbuttonNext);
		
		jlabelPage = new JLabel("jlabelPage");
		jlabelPage.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelPage.setFont(new Font("SansSerif", Font.PLAIN, 14));
		jlabelPage.setBounds(167, 463, 81, 40);
		getContentPane().add(jlabelPage);
		
		jlabelPageTotal = new JLabel("jlabelPageTotal");
		jlabelPageTotal.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelPageTotal.setFont(new Font("SansSerif", Font.PLAIN, 14));
		jlabelPageTotal.setBounds(420, 463, 147, 40);
		getContentPane().add(jlabelPageTotal);
		
		JLabel lblCallnumber = new JLabel("Callnumber :");
		lblCallnumber.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblCallnumber.setBounds(644, 55, 90, 35);
		getContentPane().add(lblCallnumber);
		
		jtextFieldCallnumber = new JTextField();
		jtextFieldCallnumber.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldCallnumber.setBounds(746, 53, 199, 39);
		getContentPane().add(jtextFieldCallnumber);
		jtextFieldCallnumber.setColumns(10);
		
		JLabel lblUserId = new JLabel("User ID :");
		lblUserId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblUserId.setBounds(653, 6, 81, 35);
		getContentPane().add(lblUserId);
		
		jtextFieldUserId = new JTextField();
		jtextFieldUserId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldUserId.setColumns(10);
		jtextFieldUserId.setBounds(746, 6, 199, 35);
		getContentPane().add(jtextFieldUserId);
		
		jbtnSubmit = new JButton("Submit");
		jbtnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnSubmit_actionPerformed(arg0);
			}
		});
		jbtnSubmit.setBounds(966, 56, 133, 35);
		getContentPane().add(jbtnSubmit);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(661, 102, 556, 349);
		getContentPane().add(scrollPane_1);
		
		jtableCheckIn = new JTable();
		scrollPane_1.setViewportView(jtableCheckIn);
		
		jbtnCheckIn = new JButton("Check In");
		jbtnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnCheckIn_actionPerformed(arg0);
			}
		});
		jbtnCheckIn.setBounds(560, 496, 154, 40);
		getContentPane().add(jbtnCheckIn);
		
		jtextFieldCheckOutId = new JTextField();
		jtextFieldCheckOutId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldCheckOutId.setColumns(10);
		jtextFieldCheckOutId.setBounds(133, 41, 199, 35);
		getContentPane().add(jtextFieldCheckOutId);
		
		JLabel jlabelCheckOutId = new JLabel("Checkout ID :");
		jlabelCheckOutId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelCheckOutId.setBounds(31, 41, 90, 35);
		getContentPane().add(jlabelCheckOutId);
		
		jbuttonSelect = new JButton(">>");
		jbuttonSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonSelect_actionPerformed(e);
			}
		});
		jbuttonSelect.setBounds(596, 235, 53, 40);
		getContentPane().add(jbuttonSelect);
		
		jbtnDelete = new JButton("Remove from list");
		jbtnDelete.setFont(new Font("SansSerif", Font.PLAIN, 14));
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnDelete_actionPerformed(arg0);
			}
		});
		jbtnDelete.setBounds(1023, 454, 180, 35);
		getContentPane().add(jbtnDelete);
		
		jbtnFind = new JButton("Find");
		jbtnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnFind_actionPerformed(arg0);
			}
		});
		jbtnFind.setBounds(344, 41, 133, 35);
		getContentPane().add(jbtnFind);
		loadJInternalFrame();
	}
	long page = 1;
	long pageNum = 1;
	private JLabel jlabelPage;
	private JLabel jlabelPageTotal;
	private JTextField jtextFieldCallnumber;
	private JTextField jtextFieldUserId;
	private JTable jtableCheckIn;
	private JTextField jtextFieldCheckOutId;
	private JButton jbtnSubmit;
	int checkout_id = 0;
	private JButton jbuttonNext;
	private JButton jbuttonLast;
	private JButton jbuttonPrevious;
	private JButton jbuttonToStart;
	private List<Detail> detailCheckOuts = new ArrayList<Detail>();
	private JButton jbuttonSelect;
	private JButton jbtnCheckIn;
	private JButton jbtnDelete;
	private JButton jbtnFind;
	private void loadJInternalFrame() {
		jbuttonNext.setEnabled(false);
		jbuttonLast.setEnabled(false);
		jbuttonPrevious.setEnabled(false);
		jbuttonToStart.setEnabled(false);
		jlabelPage.setVisible(false);
		jlabelPageTotal.setVisible(false);
	}
	//auto load data to checkin table function
	private void autofilltableCheckIn(List<Detail> details) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Detail ID");
		defaultTableModel.addColumn("CheckOut ID");
		defaultTableModel.addColumn("User ID");
		defaultTableModel.addColumn("Callnumber");
		defaultTableModel.addColumn("Book Title");
		defaultTableModel.addColumn("Payment");
		for(Detail detail : details) {
			if(detail != null) {
				BookItemModel bookItemModel = new BookItemModel();
				BookItem bookItem = bookItemModel.findCallnumberBorrowed(detail.getCallnumber());
				BookModel bookModel = new BookModel();
				Book book = bookModel.find(bookItem.getIsbn());
				defaultTableModel.addRow(new Object[] {
						detail.getDetail_id(),
						detail.getCheckout_id(),
						detail.getUser_id(),
						detail.getCallnumber(),
						book.getTitle(),
						detail.getPayment(),
				});
			}
		}
		jtableCheckIn.setModel(defaultTableModel);
		jtableCheckIn.getTableHeader().setReorderingAllowed(false);
	}
	//going back function
	private void jbuttonPrevious_actionPerformed(ActionEvent arg0) {
		if(page > 1) {
			page--;
			DetailModel detailModel = new DetailModel();
			jlabelPage.setText("" + page);
			jlabelPageTotal.setText(page + "/" + pageNum);
			autofilltableCheckout(detailModel.loadData(page, checkout_id));
		}
	}
	//going next page function
	private void jbuttonNext_actionPerformed(ActionEvent e) {
		if(page < pageNum) {
			page++;
			DetailModel detailModel = new DetailModel();
			jlabelPage.setText("" + page);
			jlabelPageTotal.setText(page + "/" + pageNum);
			autofilltableCheckout(detailModel.loadData(page, checkout_id));
		}
	}
	//going back to page 1 function
	private void jbuttonToStart_actionPerformed(ActionEvent arg0) {
		page = 1;
		DetailModel detailModel = new DetailModel();
		jlabelPage.setText("" + page);
		jlabelPageTotal.setText(page + "/" + pageNum);
		autofilltableCheckout(detailModel.loadData(page, checkout_id));
	}
	//going all the way to the last page function
	private void jbuttonLast_actionPerformed(ActionEvent e) {
		page = pageNum;
		DetailModel detailModel = new DetailModel();
		jlabelPage.setText("" + page);
		jlabelPageTotal.setText(page + "/" + pageNum);
		autofilltableCheckout(detailModel.loadData(page, checkout_id));
	}
	//auto load data to checkout table function
	private void autofilltableCheckout(List<Detail> details) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Detail ID");
		defaultTableModel.addColumn("CheckOut ID");
		defaultTableModel.addColumn("User ID");
		defaultTableModel.addColumn("Callnumber");
		defaultTableModel.addColumn("Payment");
		for(Detail detail : details) {
			defaultTableModel.addRow(new Object[] {
					detail.getDetail_id(),
					detail.getCheckout_id(),
					detail.getUser_id(),
					detail.getCallnumber(),
					detail.getPayment(),
			});
		}
		jtableCheckOutList.setModel(defaultTableModel);
		jtableCheckOutList.getTableHeader().setReorderingAllowed(false);
	}
	//picking the select book and adding it to checkin table
	private void jbuttonSelect_actionPerformed(ActionEvent e) {
		int selectedRow = jtableCheckOutList.getSelectedRow();
		if(selectedRow != -1) {
			int detail_id = Integer.parseInt((jtableCheckOutList.getValueAt(selectedRow, 0)).toString());
			DetailModel detailModel = new DetailModel();
			Detail detail = detailModel.searchByDetailId(detail_id);
			if(detail != null) {
				boolean result = detailModel.updateStt(1, detail_id);
				if(result) {				
					detailCheckOuts.add(detail);
					autofilltableCheckout(detailModel.loadData(page, checkout_id));
					autofilltableCheckIn(detailCheckOuts);
				}else {
					JOptionPane.showMessageDialog(null, "Can't change the status of this book.");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Something wrong is happening.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please select the book you want to check in.");
		}
	}
	//event click the checkin button to checkin book.
	public void jbtnCheckIn_actionPerformed(ActionEvent arg0) {
		//get employee_id.
		JFrameMain jFrameMain = (JFrameMain) this.getTopLevelAncestor();
		int employee_id = jFrameMain.getEmployee_id();
		//get current date.
		Date date = new Date();
		boolean result = false;
		int sumFee = 0;
		int sumPayment = 0;
		int sumOutOfDate = 0;
		int sumBook = 0;
		for(Detail detail : detailCheckOuts) {
			sumBook ++;
			CheckOutModel checkOutModel = new CheckOutModel();
			CheckOut checkOut = checkOutModel.find(detail.getCheckout_id());
			BookItemModel bookItemModel = new BookItemModel();
			bookItemModel.updateStatusToOn(detail.getCallnumber());
			sumPayment = sumPayment + detail.getPayment();
			Date borrow_date = checkOut.getBorrow_date();
			if(date.compareTo(borrow_date)>0) {
				long d1 = borrow_date.getTime();
				long d2 = date.getTime();
				//calculate the number of days from borrow date to return date.
				long diff = (d2 - d1)/ (24 * 60 * 60 * 1000);
				int keepingBookDay = (int) diff;
				int out_of_date = keepingBookDay - limitBorrowDay;
				if(out_of_date < 0) {
					out_of_date = 0;
				};
				sumOutOfDate = sumOutOfDate + out_of_date;
				//calculate fee
				int fee = feePerDay * out_of_date;
				sumFee = sumFee + fee;
				//create new detail Object to add information in order to update.
				Detail detail2 = new Detail();
				detail2.setDetail_id(detail.getDetail_id());
				detail2.setEmployee_id(employee_id);
				detail2.setReturn_date(date);
				detail2.setOut_of_date(out_of_date);
				detail2.setFee(fee);
				DetailModel detailModel = new DetailModel();
				result = detailModel.changeCheckIn(detail2);
				if(result == false) {
					JOptionPane.showMessageDialog(null,"Something wrong has been happened. Ask your admin to check the system.");
					break;
				}
			}
		}
		if(result) {
			int payBack = 0;
			int extra = 0;
			String str = "";
			if(sumPayment >= sumFee) {
				payBack = sumPayment - sumFee;
				str = "Total of returned books: "+sumBook+" books \nOut of date: " + sumOutOfDate + " days \nFee: " 
								+ sumFee + "\nPayment: " + sumPayment + "\nPayback: " +payBack;
			}else {
				extra = sumFee - sumPayment;
				str = "Total of returned books: "+sumBook+" books \nOut of date: " + sumOutOfDate + " days \nFee: " 
						+ sumFee + "\nPayment: " + sumPayment + "\nExtra: " + extra;
			}
//			String str = "Total of returned books: "+sumBook+" books \nOut of date: " + sumOutOfDate + " days \nFee: " + sumFee + "\nPayment: " + sumPayment;
			JOptionPane.showMessageDialog(null, str);
			jtextFieldCallnumber.setText("");
			jtextFieldCheckOutId.setText("");
			jtextFieldUserId.setText("");
			detailCheckOuts.clear();
			autofilltableCheckIn(detailCheckOuts);
		}
	}
	//event submit information to search callnumber and userid to get detail id/ search by checkout_id to get 
	//list of borrowed book in detail table
	private void jbtnSubmit_actionPerformed(ActionEvent arg0) {
		
		if(jtextFieldCallnumber.getText().isEmpty() && jtextFieldUserId.getText().isEmpty()) {
			
			if(!jtextFieldCheckOutId.getText().isEmpty()) {
				try {
					checkout_id = Integer.parseInt(jtextFieldCheckOutId.getText().toString());
					DetailModel detailModel = new DetailModel();
					long count = detailModel.countDB(checkout_id);
					if(count != 0) {
						if(count % 20 == 0) {
							pageNum = count / 20;
						}else {
							pageNum = (count / 20) + 1;
						}
						jlabelPage.setText("1");
						jlabelPageTotal.setText("1/" + pageNum);
						jbuttonNext.setEnabled(true);
						jbuttonLast.setEnabled(true);
						jbuttonPrevious.setEnabled(true);
						jbuttonToStart.setEnabled(true);
						jlabelPage.setVisible(true);
						jlabelPageTotal.setVisible(true);
						autofilltableCheckout(detailModel.loadData(1,checkout_id));
					}else {
						JOptionPane.showMessageDialog(null, "The checkout id is invalid.");
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
					JOptionPane.showMessageDialog(null, "The checkout id contains only number.");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Please enter checkout id.");
			}
		}else {
			if(jtextFieldCallnumber.getText().isEmpty() || jtextFieldUserId.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter the callnumber/user id.");
			}else {
				 try {
					int user_id = Integer.parseInt(jtextFieldUserId.getText().toString());
					String callnumber = jtextFieldCallnumber.getText();
					DetailModel detailModel = new DetailModel();
					Detail detail = detailModel.searchByUserCallnumber(user_id, callnumber);
					if(detail != null) {
						detailModel.updateStt(1, detail.getDetail_id());
						detailCheckOuts.add(detail);
						autofilltableCheckIn(detailCheckOuts);
					}else {
						JOptionPane.showMessageDialog(null, "The user id or callnumber is invalid.");
					}
				 } catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "The user id contains only number.");
				 }
			}
		}
	}
	//Find by checkoutID
	private void jbtnFind_actionPerformed(ActionEvent arg0) {
		if(!jtextFieldCheckOutId.getText().isEmpty()) {
			try {
				checkout_id = Integer.parseInt(jtextFieldCheckOutId.getText().toString());
				DetailModel detailModel = new DetailModel();
				long count = detailModel.countDB(checkout_id);
				System.out.println(count);
				if(count != 0) {
					if(count % 20 == 0) {
						pageNum = count / 20;
					}else {
						pageNum = (count / 20) + 1;
					}
					jlabelPage.setText("1");
					jlabelPageTotal.setText("1/" + pageNum);
					jbuttonNext.setEnabled(true);
					jbuttonLast.setEnabled(true);
					jbuttonPrevious.setEnabled(true);
					jbuttonToStart.setEnabled(true);
					jlabelPage.setVisible(true);
					jlabelPageTotal.setVisible(true);
					autofilltableCheckout(detailModel.loadData(1,checkout_id));
				}else {
					JOptionPane.showMessageDialog(null, "The checkout id is invalid.");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "The checkout id contains only number.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please enter checkout id.");
		}
	}
	//function delete picked detail
	private void jbtnDelete_actionPerformed(ActionEvent arg0) {
		if(jtableCheckIn.getSelectedRow() != -1) {
			int selectedRow = jtableCheckIn.getSelectedRow();
			int selected_detail_id = Integer.parseInt(jtableCheckIn.getValueAt(selectedRow, 0).toString());
			int selected_checkout_id = Integer.parseInt(jtableCheckIn.getValueAt(selectedRow, 1).toString());
			DetailModel detailModel = new DetailModel();
			boolean result = detailModel.updateStt(0, selected_detail_id);
			long count = detailModel.countDB(checkout_id);
			System.out.println(count);
			if(count != 0) {
				if(count % 20 == 0) {
					pageNum = count / 20;
				}else {
					pageNum = (count / 20) + 1;
				}
				page = 1;
				System.out.println(pageNum);
				jlabelPage.setText("1");
				jlabelPageTotal.setText("1/" + pageNum);
				jbuttonNext.setEnabled(true);
				jbuttonLast.setEnabled(true);
				jbuttonPrevious.setEnabled(true);
				jbuttonToStart.setEnabled(true);
				jlabelPage.setVisible(true);
				jlabelPageTotal.setVisible(true);
				detailCheckOuts.remove(selectedRow);
				autofilltableCheckIn(detailCheckOuts);
				autofilltableCheckout(detailModel.loadData(1,checkout_id));
			}else {
				JOptionPane.showMessageDialog(null, "The checkout id is invalid.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please select a book you want to remove.");
		}
	}
}
