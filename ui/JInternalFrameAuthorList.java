package ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;

import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entities.Author;
import models.AuthorModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JInternalFrameAuthorList extends JInternalFrame {
	private JTextField jtextFieldAuthorName;
	private JButton jbuttonDelete;
	private JButton jbuttonUpdate;
	private JTextField jtextFieldSearch;
	private JButton jbtnSearch;
	private JButton jbuttonCreate;
	private JTable jTable;

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
					JInternalFrameAuthorList frame = new JInternalFrameAuthorList();
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
	public JInternalFrameAuthorList() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedOut(e);
			}
		});
		setOpaque(false);
		setClosable(true);
		setTitle("Author List");
		setBounds(100, 100, 450, 351);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 59, 426, 104);
		getContentPane().add(scrollPane);
		
		jTable = new JTable();
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jTable_mouseClicked(e);
			}
		});
		scrollPane.setViewportView(jTable);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Author info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 175, 426, 139);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblAuthorName = new JLabel("Author name");
		lblAuthorName.setBounds(17, 25, 95, 36);
		panel.add(lblAuthorName);

		jtextFieldAuthorName = new JTextField();
		jtextFieldAuthorName.setBounds(113, 25, 288, 36);
		panel.add(jtextFieldAuthorName);
		jtextFieldAuthorName.setColumns(10);

		jbuttonCreate = new JButton("Create");
		jbuttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonCreate_actionPerformed(arg0);
			}
		});
		jbuttonCreate.setBounds(17, 93, 90, 28);
		panel.add(jbuttonCreate);

		jbuttonUpdate = new JButton("Update");
		jbuttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbuttonUpdate_actionPerformed(arg0);
			}
		});
		jbuttonUpdate.setBounds(159, 93, 90, 28);
		panel.add(jbuttonUpdate);

		jbuttonDelete = new JButton("Delete");
		jbuttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonDelete_actionPerformed(e);
			}
		});
		jbuttonDelete.setBounds(308, 93, 90, 28);
		panel.add(jbuttonDelete);

		jtextFieldSearch = new JTextField();
		jtextFieldSearch.setBounds(6, 19, 246, 28);
		getContentPane().add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);

		jbtnSearch = new JButton("Search");
		jbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnSearch_actionPerformed(arg0);
			}
		});
		jbtnSearch.setBounds(264, 19, 98, 28);
		getContentPane().add(jbtnSearch);

		loadJInternalFrame();
	}

	// Mouse click out
	private void mouseClickedOut(MouseEvent e) {
		loadJInternalFrame();
		jbuttonCreate.setEnabled(true);
		jbuttonDelete.setEnabled(false);
		jbuttonUpdate.setEnabled(false);
		jtextFieldAuthorName.setText("");
	}

	// Search author name
	private void jbtnSearch_actionPerformed(ActionEvent arg0) {
		String kw = jtextFieldSearch.getText();
		AuthorModel authorModel = new AuthorModel();
		List<Author> authors = authorModel.search(kw);
		if (kw.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Pls enter name of author you want to find");
		} else {
			if (authors.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Name of author you want to find is not in the table");
			} else {
				fillDataToJTable(authorModel.search(kw));
			}
		}

	}

	// Delete author name
	private void jbuttonDelete_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirm", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			int selectedRow = jTable.getSelectedRow();
			int author_id = Integer.parseInt(jTable.getValueAt(selectedRow, 0).toString());
			AuthorModel authorModel = new AuthorModel();
			if (authorModel.delete(author_id)) {
				JOptionPane.showMessageDialog(null, "Done");
				fillDataToJTable(authorModel.findAll());
				jtextFieldAuthorName.setText("");
				jbuttonCreate.setEnabled(true);
				jbuttonDelete.setEnabled(false);
				jbuttonUpdate.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "Failed");
			}
		}
	}

	// Update author name
	private void jbuttonUpdate_actionPerformed(ActionEvent arg0) {
		try {
			int selectedRow = jTable.getSelectedRow();
			int author_id = Integer.parseInt(jTable.getValueAt(selectedRow, 0).toString());
			AuthorModel authorModel = new AuthorModel();
			Author author = authorModel.find(author_id);
			author.setName(jtextFieldAuthorName.getText());
			if (authorModel.update(author)) {
				JOptionPane.showMessageDialog(null, "Done");
				fillDataToJTable(authorModel.findAll());
				jtextFieldAuthorName.setText("");
				jbuttonCreate.setEnabled(true);
				jbuttonDelete.setEnabled(false);
				jbuttonUpdate.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "Failed");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Khong thuc hien duoc!");
		}
	}

	// create author name
	private void jbuttonCreate_actionPerformed(ActionEvent arg0) {
		AuthorModel authorModel = new AuthorModel();
		Author author = new Author();
		if (jtextFieldAuthorName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Pls enter name of author");
		} else {
			author.setName(jtextFieldAuthorName.getText());
			if (authorModel.create(author)) {
				JOptionPane.showMessageDialog(null, "Successfull");
				fillDataToJTable(authorModel.findAll());
				jtextFieldAuthorName.setText("");
				jbuttonDelete.setEnabled(false);
				jbuttonUpdate.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "Failed");
			}
		}

	}

	// MouseClick to show author name
	private void jTable_mouseClicked(MouseEvent e) {
		int selectedRow = jTable.getSelectedRow();
		int author_id =Integer.parseInt(jTable.getValueAt(selectedRow, 0).toString()) ;
		AuthorModel authorModel= new AuthorModel();
		Author author = authorModel.find(author_id);
		jtextFieldAuthorName.setText(author.getName());
		jbuttonCreate.setEnabled(false);
		jbuttonDelete.setEnabled(true);
		jbuttonUpdate.setEnabled(true);
	}

	private void fillDataToJTable(List<Author> authors) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Author ID");
		defaultTableModel.addColumn("Author Name");

		for (Author author : authors) {
			defaultTableModel.addRow(new Object[] { author.getAuthor_id(), author.getName() });
			jTable.setModel(defaultTableModel);
			jTable.getTableHeader().setReorderingAllowed(false);
		}
	}

	private void loadJInternalFrame() {
		AuthorModel authorModel = new AuthorModel();
		fillDataToJTable(authorModel.findAll());
		jbuttonDelete.setEnabled(false);
		jbuttonUpdate.setEnabled(false);
	}
}
