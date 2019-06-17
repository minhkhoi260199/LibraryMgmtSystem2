package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Book;
import entities.BookItem;
import entities.CheckOut;
import entities.CheckOutJDetailJBook;
import entities.Detail;
import models.BookItemModel;
import models.BookModel;
import models.CheckOutJDetailJBookModel;
import models.CheckOutModel;
import models.DetailModel;

import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;

public class JInternalFrameDetailList extends JInternalFrame {
	private JTable jtableDetail;
	private JButton jbuttonNext;
	private JButton jbuttonBack;
	private JButton jbuttonFirst;
	private JButton jbuttonLast;
	private JLabel jlabelPage;

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
					JInternalFrameDetailList frame = new JInternalFrameDetailList();
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
	public JInternalFrameDetailList() {
		setTitle("Detail List");
		setClosable(true);
		setBounds(100, 100, 1379, 545);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 75, 1355, 362);
		getContentPane().add(scrollPane);
		
		jtableDetail = new JTable();
		jtableDetail.setFont(new Font("SansSerif", Font.PLAIN, 15));
		scrollPane.setViewportView(jtableDetail);
		
		jbuttonBack = new JButton("Back");
		jbuttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonBack_actionPerformed(arg0);
			}
		});
		jbuttonBack.setBounds(555, 456, 81, 41);
		getContentPane().add(jbuttonBack);
		
		jbuttonNext = new JButton("Next");
		jbuttonNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonNext_actionPerformed(arg0);
			}
		});
		jbuttonNext.setBounds(735, 456, 81, 41);
		getContentPane().add(jbuttonNext);
		
		jlabelPage = new JLabel("New label");
		jlabelPage.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelPage.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelPage.setBounds(657, 456, 55, 41);
		getContentPane().add(jlabelPage);
		
		jbuttonLast = new JButton("Last");
		jbuttonLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonLast_actionPerformed(arg0);
			}
		});
		jbuttonLast.setBounds(844, 456, 81, 41);
		getContentPane().add(jbuttonLast);
		
		jbuttonFirst = new JButton("First");
		jbuttonFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonFirst_actionPerformed(arg0);
			}
		});
		jbuttonFirst.setBounds(446, 456, 81, 41);
		getContentPane().add(jbuttonFirst);
		
		jtextFieldSearch = new JTextField();
		jtextFieldSearch.setBounds(342, 24, 185, 39);
		getContentPane().add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);
		
		jbtnSearch = new JButton("Search");
		jbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnSearch_actionPerformed(arg0);
			}
		});
		jbtnSearch.setFont(new Font("SansSerif", Font.PLAIN, 14));
		jbtnSearch.setBounds(546, 24, 90, 39);
		getContentPane().add(jbtnSearch);
		
		jcomboBox = new JComboBox();
		jcomboBox.setBounds(691, 24, 140, 39);
		getContentPane().add(jcomboBox);
		
		JLabel lblBy = new JLabel("by:");
		lblBy.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBy.setBounds(657, 28, 55, 27);
		getContentPane().add(lblBy);
		
		jbtnShowAll = new JButton("Show All");
		jbtnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnShowAll_actionPerformed(e);
			}
		});
		jbtnShowAll.setBackground(Color.GREEN);
		jbtnShowAll.setFont(new Font("SansSerif", Font.PLAIN, 14));
		jbtnShowAll.setBounds(992, 24, 90, 39);
		getContentPane().add(jbtnShowAll);
		loadJInternalFrame();
	}
	long page = 1;
	long pageNum = 1;
	private JTextField jtextFieldSearch;
	private JButton jbtnSearch;
	private JComboBox jcomboBox;
	private int condition = 0;
	private String type = "";
	private JButton jbtnShowAll;
//	private CheckOutJDetailJBook detailJBook = new CheckOutJDetailJBook();
	private void loadJInternalFrame() {
		jbuttonBack.setEnabled(false);
		jbuttonFirst.setEnabled(false);
		jbuttonLast.setEnabled(false);
		jbuttonNext.setEnabled(false);
		jlabelPage.setVisible(false);
		DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<String>();
		boxModel.addElement("Borrowing Employee");
		boxModel.addElement("Returning Employee");
		boxModel.addElement("Customer");
		jcomboBox.setModel(boxModel);
		CheckOutJDetailJBookModel detailModel = new CheckOutJDetailJBookModel();
		long count = detailModel.countDBforDetailList(condition,type);
		if(count != 0) {
			if(count % 20 == 0) {
				pageNum = count / 20;
			}else {
				pageNum = (count / 20) + 1;
			}
			jlabelPage.setText("1/"+pageNum);
			jlabelPage.setVisible(true);
			jbuttonBack.setEnabled(true);
			jbuttonFirst.setEnabled(true);
			jbuttonLast.setEnabled(true);
			jbuttonNext.setEnabled(true);
			autoloadtabledate(detailModel.loadDataDetail(1,condition,type));
		}
	}
	private void autoloadtabledate(List<CheckOutJDetailJBook> details) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Detail ID");
		defaultTableModel.addColumn("CheckOut ID");
		defaultTableModel.addColumn("User ID");
		defaultTableModel.addColumn("Callnumber");
		defaultTableModel.addColumn("Title");
		defaultTableModel.addColumn("Payment");
		defaultTableModel.addColumn("Out of Date");
		defaultTableModel.addColumn("Fee");
		defaultTableModel.addColumn("Borrow Date");
		defaultTableModel.addColumn("Borrow Employee ID");
		defaultTableModel.addColumn("Return Date");
		defaultTableModel.addColumn("Return Employee ID");
		defaultTableModel.addColumn("Status");
		for(CheckOutJDetailJBook detail : details) {
			String a = Integer.toString(detail.getReturn_employee_id());
			if(detail.getReturn_employee_id() == 0) {
				a = "";
			}
			String b = "";
			if(detail.getStatus() == 0) {
				b="Not Returned";
			}else {
				b="Returned";
			}
			defaultTableModel.addRow(new Object[] {
					detail.getDetail_id(),
					detail.getCheckout_id(),
					detail.getUser_id(),
					detail.getCallnumber(),
					detail.getTitle(),
					detail.getPayment(),
					detail.getOut_of_date(),
					detail.getFee(),
					detail.getBorrow_date(),
					detail.getBorrow_employee_id(),
					detail.getReturn_date(),
//					detail.getEmployee_id(),
					a,
					b,
			});
		}
		jtableDetail.setModel(defaultTableModel);
		jtableDetail.getTableHeader().setReorderingAllowed(false);
	}
	private void jbuttonBack_actionPerformed(ActionEvent arg0) {
		if(page > 1) {
			page --;
			CheckOutJDetailJBookModel detailModel = new CheckOutJDetailJBookModel();
			jlabelPage.setText(page + "/" + pageNum);
			autoloadtabledate(detailModel.loadDataDetail(page,condition,type));
		}
	}
	private void jbuttonNext_actionPerformed(ActionEvent arg0) {
		if(page < pageNum) {
			page ++;
			CheckOutJDetailJBookModel detailModel = new CheckOutJDetailJBookModel();
			jlabelPage.setText(page +"/"+ pageNum);
			autoloadtabledate(detailModel.loadDataDetail(page,condition,type));
		}
	}
	private void jbuttonFirst_actionPerformed(ActionEvent arg0) {
		page = 1;
		CheckOutJDetailJBookModel detailModel = new CheckOutJDetailJBookModel();
		jlabelPage.setText(page +"/"+ pageNum);
		autoloadtabledate(detailModel.loadDataDetail(page,condition,type));
	}
	private void jbuttonLast_actionPerformed(ActionEvent arg0) {
		page = pageNum;
		CheckOutJDetailJBookModel detailModel = new CheckOutJDetailJBookModel();
		jlabelPage.setText(page +"/"+ pageNum);
		autoloadtabledate(detailModel.loadDataDetail(page,condition,type));
	}
	private void jbtnSearch_actionPerformed(ActionEvent arg0) {
		type = jcomboBox.getSelectedItem().toString().toLowerCase();
		try{
			condition = Integer.parseInt(jtextFieldSearch.getText().toString());
			CheckOutJDetailJBookModel detailModel = new CheckOutJDetailJBookModel();
			long count = detailModel.countDBforDetailList(condition,type);
			if(count != 0) {
				if(count % 20 == 0) {
					pageNum = count / 20;
				}else {
					pageNum = (count / 20) + 1;
				}
				jlabelPage.setText("1/"+pageNum);
				autoloadtabledate(detailModel.loadDataDetail(1,condition,type));
			}else {
				autoloadtabledate(detailModel.loadDataDetail(1,condition,type));
			}
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Please enter the id of " + type);
			type="";
		}
	}
	private void jbtnShowAll_actionPerformed(ActionEvent e) {
		condition = 0;
		type = "";
		CheckOutJDetailJBookModel detailModel = new CheckOutJDetailJBookModel();
		long count = detailModel.countDBforDetailList(condition,type);
		if(count != 0) {
			if(count % 20 == 0) {
				pageNum = count / 20;
			}else {
				pageNum = (count / 20) + 1;
			}
			jlabelPage.setText("1/"+pageNum);
			autoloadtabledate(detailModel.loadDataDetail(1,condition,type));
		}
	}
}
