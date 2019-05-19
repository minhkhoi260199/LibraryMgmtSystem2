package ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import entities.Author;
import entities.Book;
import entities.BookItem;
import entities.Category;
import models.AuthorModel;
import models.BookItemModel;
import models.CategoryModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class JinternalFrameListBookItem extends JInternalFrame {
	private JTable jtable;
	private JTextField jtextFieldSearch;
	private JButton jbuttonAll;
	private JComboBox jcomboBoxFillter;
	private JButton jbtnNewButtonSearch;

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
					JinternalFrameListBookItem frame = new JinternalFrameListBookItem();
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
	public JinternalFrameListBookItem() {
		setTitle("Book Item List");
		setClosable(true);
		setBounds(100, 100, 450, 332);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 38, 426, 215);
		getContentPane().add(scrollPane);

		jtable = new JTable();
		scrollPane.setViewportView(jtable);

		jtextFieldSearch = new JTextField();
		jtextFieldSearch.setBounds(60, 6, 156, 28);
		getContentPane().add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(16, 12, 55, 16);
		getContentPane().add(lblSearch);

		jbtnNewButtonSearch = new JButton("Search");
		jbtnNewButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnNewButtonSearch_actionPerformed(e);
			}
		});
		jbtnNewButtonSearch.setBounds(228, 6, 70, 28);
		getContentPane().add(jbtnNewButtonSearch);

		jbuttonAll = new JButton("Show all");
		jbuttonAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonAll_actionPerformed(e);
			}
		});
		jbuttonAll.setBounds(329, 265, 78, 28);
		getContentPane().add(jbuttonAll);

		JLabel lblBy = new JLabel("by:");
		lblBy.setBounds(299, 12, 22, 16);
		getContentPane().add(lblBy);

		jcomboBoxFillter = new JComboBox();
		jcomboBoxFillter.setBounds(317, 7, 104, 26);
		getContentPane().add(jcomboBoxFillter);

		loadFrame();
	}

	public void loadFrame() {
		BookItemModel bookItemModel = new BookItemModel();
		autoFillDataToTable(bookItemModel.findAll());
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
		comboBoxModel.addElement("Callnumber");
		comboBoxModel.addElement("ISBN");
		jcomboBoxFillter.setModel(comboBoxModel);
		jbuttonAll.setEnabled(false);
	}

	public void autoFillDataToTable(List<BookItem> bookItems) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Callnumber");
		defaultTableModel.addColumn("ISBN");
		defaultTableModel.addColumn("Status");

		for (BookItem bookItem : bookItems) {
			defaultTableModel
					.addRow(new Object[] { bookItem.getCallnumber(), bookItem.getIsbn(), bookItem.getStatus() });
		}
		jtable.setModel(defaultTableModel);
		jtable.getTableHeader().setReorderingAllowed(false);
		jbuttonAll.setEnabled(true);
	}

	private void jbuttonAll_actionPerformed(ActionEvent e) {
		loadFrame();
	}

	private void jbtnNewButtonSearch_actionPerformed(ActionEvent e) {
		String kw = jtextFieldSearch.getText().toLowerCase().trim();
		String type = (String) jcomboBoxFillter.getSelectedItem().toString().toLowerCase(); // noi chuoi
		BookItemModel bookItemModel = new BookItemModel();
		List<BookItem> bookItems = bookItemModel.Search(type, kw);
		if (bookItems.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Keyword you entered is not in the table or wrong type ");
		} else {
			if (kw.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Pls enter keyword you want to find");
			} else {
				autoFillDataToTable(bookItemModel.Search(type, kw));
			}
		}
	}
}
