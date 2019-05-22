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
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entities.CheckOut;
import entities.Detail;
import models.CheckOutModel;
import models.DetailModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class JInterFrameCheckIn extends JInternalFrame {
	private JTable jtableCheckOutList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setTitle("CheckIn ");
		setBounds(100, 100, 1249, 689);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 188, 561, 349);
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
		jbuttonToStart.setBounds(23, 574, 90, 40);
		getContentPane().add(jbuttonToStart);
		
		jbuttonPrevious = new JButton("<");
		jbuttonPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonPrevious_actionPerformed(arg0);
			}
		});
		jbuttonPrevious.setBounds(124, 574, 53, 40);
		getContentPane().add(jbuttonPrevious);
		
		jbuttonLast = new JButton(">>");
		jbuttonLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonLast_actionPerformed(e);
			}
		});
		jbuttonLast.setBounds(335, 574, 90, 40);
		getContentPane().add(jbuttonLast);
		
		jbuttonNext = new JButton(">");
		jbuttonNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonNext_actionPerformed(e);
			}
		});
		jbuttonNext.setBounds(270, 574, 53, 40);
		getContentPane().add(jbuttonNext);
		
		jlabelPage = new JLabel("jlabelPage");
		jlabelPage.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelPage.setFont(new Font("SansSerif", Font.PLAIN, 14));
		jlabelPage.setBounds(184, 574, 81, 40);
		getContentPane().add(jlabelPage);
		
		jlabelPageTotal = new JLabel("jlabelPageTotal");
		jlabelPageTotal.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelPageTotal.setFont(new Font("SansSerif", Font.PLAIN, 14));
		jlabelPageTotal.setBounds(437, 574, 81, 40);
		getContentPane().add(jlabelPageTotal);
		
		JLabel lblCallnumber = new JLabel("Callnumber");
		lblCallnumber.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblCallnumber.setBounds(23, 32, 90, 35);
		getContentPane().add(lblCallnumber);
		
		jtextFieldCallnumber = new JTextField();
		jtextFieldCallnumber.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldCallnumber.setBounds(143, 25, 199, 35);
		getContentPane().add(jtextFieldCallnumber);
		jtextFieldCallnumber.setColumns(10);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblUserId.setBounds(23, 81, 90, 35);
		getContentPane().add(lblUserId);
		
		jtextFieldUserId = new JTextField();
		jtextFieldUserId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldUserId.setColumns(10);
		jtextFieldUserId.setBounds(143, 74, 199, 35);
		getContentPane().add(jtextFieldUserId);
		
		jbtnSubmit = new JButton("Submit");
		jbtnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnSubmit_actionPerformed(arg0);
			}
		});
		jbtnSubmit.setBounds(394, 25, 90, 35);
		getContentPane().add(jbtnSubmit);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(653, 188, 561, 349);
		getContentPane().add(scrollPane_1);
		
		jtableCheckIn = new JTable();
		scrollPane_1.setViewportView(jtableCheckIn);
		
		JButton jbtnCheckIn = new JButton("Check In");
		jbtnCheckIn.setBounds(1060, 574, 154, 40);
		getContentPane().add(jbtnCheckIn);
		
		jtextFieldCheckOutId = new JTextField();
		jtextFieldCheckOutId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldCheckOutId.setColumns(10);
		jtextFieldCheckOutId.setBounds(143, 121, 199, 35);
		getContentPane().add(jtextFieldCheckOutId);
		
		JLabel jlabelCheckOutId = new JLabel("Checkout ID");
		jlabelCheckOutId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelCheckOutId.setBounds(23, 128, 90, 35);
		getContentPane().add(jlabelCheckOutId);
		
		jbuttonSelect = new JButton(">>");
		jbuttonSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonSelect_actionPerformed(e);
			}
		});
		jbuttonSelect.setBounds(588, 338, 53, 40);
		getContentPane().add(jbuttonSelect);
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
	
	private void loadJInternalFrame() {
//		DetailModel detailModel = new DetailModel();
//		long count = detailModel.countDB();
//		if(count % 20 == 0) {
//			pageNum = count / 20;
//		}else {
//			pageNum = (count / 20) + 1;
//		}
//		jlabelPage.setText("1");
//		jlabelPageTotal.setText("1/" + pageNum);
//		autofilltableCheckout(detailModel.loadData(1));
		jbuttonNext.setEnabled(false);
		jbuttonLast.setEnabled(false);
		jbuttonPrevious.setEnabled(false);
		jbuttonToStart.setEnabled(false);
		jlabelPage.setVisible(false);
		jlabelPageTotal.setVisible(false);
	}
	private void autofilltableCheckIn(List<Detail> details) {
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
		jtableCheckIn.setModel(defaultTableModel);
		jtableCheckIn.getTableHeader().setReorderingAllowed(false);
	}
	private void jbuttonPrevious_actionPerformed(ActionEvent arg0) {
		if(page > 1) {
			page--;
			DetailModel detailModel = new DetailModel();
			jlabelPage.setText("" + page);
			jlabelPageTotal.setText(page + "/" + pageNum);
			autofilltableCheckout(detailModel.loadData(page, checkout_id));
		}
	}
	private void jbuttonNext_actionPerformed(ActionEvent e) {
		if(page < pageNum) {
			page++;
			DetailModel detailModel = new DetailModel();
			jlabelPage.setText("" + page);
			jlabelPageTotal.setText(page + "/" + pageNum);
			autofilltableCheckout(detailModel.loadData(page, checkout_id));
		}
	}
	private void jbuttonToStart_actionPerformed(ActionEvent arg0) {
		page = 1;
		DetailModel detailModel = new DetailModel();
		jlabelPage.setText("" + page);
		jlabelPageTotal.setText(page + "/" + pageNum);
		autofilltableCheckout(detailModel.loadData(page, checkout_id));
	}
	private void jbuttonLast_actionPerformed(ActionEvent e) {
		page = pageNum;
		DetailModel detailModel = new DetailModel();
		jlabelPage.setText("" + page);
		jlabelPageTotal.setText(page + "/" + pageNum);
		autofilltableCheckout(detailModel.loadData(page, checkout_id));
	}
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
}
