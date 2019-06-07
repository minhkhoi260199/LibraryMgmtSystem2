package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Detail;
import models.DetailModel;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

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
		setBounds(100, 100, 986, 545);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 19, 962, 418);
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
		jbuttonBack.setBounds(338, 454, 81, 41);
		getContentPane().add(jbuttonBack);
		
		jbuttonNext = new JButton("Next");
		jbuttonNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonNext_actionPerformed(arg0);
			}
		});
		jbuttonNext.setBounds(518, 454, 81, 41);
		getContentPane().add(jbuttonNext);
		
		jlabelPage = new JLabel("New label");
		jlabelPage.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelPage.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelPage.setBounds(440, 454, 55, 41);
		getContentPane().add(jlabelPage);
		
		jbuttonLast = new JButton("Last");
		jbuttonLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonLast_actionPerformed(arg0);
			}
		});
		jbuttonLast.setBounds(627, 454, 81, 41);
		getContentPane().add(jbuttonLast);
		
		jbuttonFirst = new JButton("First");
		jbuttonFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonFirst_actionPerformed(arg0);
			}
		});
		jbuttonFirst.setBounds(229, 454, 81, 41);
		getContentPane().add(jbuttonFirst);
		loadJInternalFrame();
	}
	long page = 1;
	long pageNum = 1;
	private void loadJInternalFrame() {
		jbuttonBack.setEnabled(false);
		jbuttonFirst.setEnabled(false);
		jbuttonLast.setEnabled(false);
		jbuttonNext.setEnabled(false);
		jlabelPage.setVisible(false);
		DetailModel detailModel = new DetailModel();
		long count = detailModel.countDBforDetailList();
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
			autoloadtabledate(detailModel.loadDataDetail(1));
		}
	}
	private void autoloadtabledate(List<Detail> details) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Detail ID");
		defaultTableModel.addColumn("CheckOut ID");
		defaultTableModel.addColumn("User ID");
		defaultTableModel.addColumn("Callnumber");
		defaultTableModel.addColumn("Payment");
		defaultTableModel.addColumn("Out of Date");
		defaultTableModel.addColumn("Fee");
		defaultTableModel.addColumn("Return Date");
		defaultTableModel.addColumn("Employee ID");
		for(Detail detail : details) {
			String a = Integer.toString(detail.getEmployee_id());
			if(detail.getEmployee_id() == 0) {
				a = "";
			}
			defaultTableModel.addRow(new Object[] {
					detail.getDetail_id(),
					detail.getCheckout_id(),
					detail.getUser_id(),
					detail.getCallnumber(),
					detail.getPayment(),
					detail.getOut_of_date(),
					detail.getFee(),
					detail.getReturn_date(),
//					detail.getEmployee_id(),
					a,
			});
		}
		jtableDetail.setModel(defaultTableModel);
		jtableDetail.getTableHeader().setReorderingAllowed(false);
	}
	private void jbuttonBack_actionPerformed(ActionEvent arg0) {
		if(page > 1) {
			page --;
			DetailModel detailModel = new DetailModel();
			jlabelPage.setText(page + "/" + pageNum);
			autoloadtabledate(detailModel.loadDataDetail(page));
		}
	}
	private void jbuttonNext_actionPerformed(ActionEvent arg0) {
		if(page < pageNum) {
			page ++;
			DetailModel detailModel = new DetailModel();
			jlabelPage.setText(page +"/"+ pageNum);
			autoloadtabledate(detailModel.loadDataDetail(page));
		}
	}
	private void jbuttonFirst_actionPerformed(ActionEvent arg0) {
		page = 1;
		DetailModel detailModel = new DetailModel();
		jlabelPage.setText(page +"/"+ pageNum);
		autoloadtabledate(detailModel.loadDataDetail(page));
	}
	private void jbuttonLast_actionPerformed(ActionEvent arg0) {
		page = pageNum;
		DetailModel detailModel = new DetailModel();
		jlabelPage.setText(page +"/"+ pageNum);
		autoloadtabledate(detailModel.loadDataDetail(page));
	}
}
