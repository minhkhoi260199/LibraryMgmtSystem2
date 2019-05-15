package ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import entities.Author;
import entities.Book;
import entities.Category;
import models.AuthorModel;
import models.BookModel;
import models.CategoryModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class JInternalFrameBook extends JInternalFrame {
	private JTable jtableBook;
	private JScrollPane scrollPane;
	private JPanel jpanel;
	private JTextField jtextFieldName;
	private JTextField jtextFieldQuantity;
	private JButton jbtnUpdate;
	private JButton jbtnDelete;
	private JButton jbtnCreate;
	private JTextField jtextFieldSearch;
	private JButton btnSearch;
	private JLabel lblSearchBookTitle;
	private JButton jbtnAll;
	private JComboBox jcomboBoxAuthor;
	private JComboBox jcomboBoxCategory;
	private JTextField jtextFieldISBN;
	private JLabel jlabelISBN;
	private JButton jbtnSave;
	private JButton jbtnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JInternalFrameBook frame = new JInternalFrameBook();
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
	public JInternalFrameBook() {
		setClosable(true);
		setTitle("Book");
		setBounds(100, 100, 969, 540);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 56, 921, 196);
		getContentPane().add(scrollPane);
		
		jtableBook = new JTable();
		jtableBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jtableBook_mouseClicked(arg0);
			}
		});
		jtableBook.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtableBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(jtableBook);
		
		jpanel = new JPanel();
		jpanel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jpanel.setBorder(new TitledBorder(null, "Book Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		jpanel.setBounds(21, 263, 921, 241);
		getContentPane().add(jpanel);
		jpanel.setLayout(null);
		
		JLabel jlabelName = new JLabel("Name");
		jlabelName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelName.setBounds(70, 72, 72, 35);
		jpanel.add(jlabelName);
		
		JLabel jlabelAuthor = new JLabel("Author");
		jlabelAuthor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelAuthor.setBounds(70, 119, 72, 35);
		jpanel.add(jlabelAuthor);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblCategory.setBounds(490, 119, 72, 35);
		jpanel.add(lblCategory);
		
		JLabel jlabelQuantity = new JLabel("Quantity");
		jlabelQuantity.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelQuantity.setBounds(490, 72, 72, 35);
		jpanel.add(jlabelQuantity);
		
		jtextFieldName = new JTextField();
		jtextFieldName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldName.setBounds(137, 76, 278, 35);
		jpanel.add(jtextFieldName);
		jtextFieldName.setColumns(10);
		
		jtextFieldQuantity = new JTextField();
		jtextFieldQuantity.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldQuantity.setColumns(10);
		jtextFieldQuantity.setBounds(574, 76, 278, 35);
		jpanel.add(jtextFieldQuantity);
		
		jbtnUpdate = new JButton("Update");
		jbtnUpdate.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnUpdate_actionPerformed(e);
			}
		});
		jbtnUpdate.setBounds(70, 180, 90, 28);
		jpanel.add(jbtnUpdate);
		
		jbtnDelete = new JButton("Delete");
		jbtnDelete.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnDelete_actionPerformed(arg0);
			}
		});
		jbtnDelete.setBounds(196, 180, 90, 28);
		jpanel.add(jbtnDelete);
		
		jbtnCreate = new JButton("Create");
		jbtnCreate.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbtnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnCreate_actionPerformed(arg0);
			}
		});
		jbtnCreate.setBounds(762, 180, 90, 28);
		jpanel.add(jbtnCreate);
		
		jcomboBoxAuthor = new JComboBox();
		jcomboBoxAuthor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jcomboBoxAuthor.setBounds(137, 124, 278, 35);
		jpanel.add(jcomboBoxAuthor);
		
		jcomboBoxCategory = new JComboBox();
		jcomboBoxCategory.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jcomboBoxCategory.setBounds(574, 124, 278, 35);
		jpanel.add(jcomboBoxCategory);
		
		jlabelISBN = new JLabel("ISBN");
		jlabelISBN.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelISBN.setBounds(70, 30, 72, 35);
		jpanel.add(jlabelISBN);
		
		jtextFieldISBN = new JTextField();
		jtextFieldISBN.setBounds(137, 34, 278, 35);
		jpanel.add(jtextFieldISBN);
		jtextFieldISBN.setColumns(10);
		
		jbtnSave = new JButton("Save");
		jbtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSave_actionPerformed(e);
			}
		});
		jbtnSave.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbtnSave.setBounds(648, 180, 90, 28);
		jpanel.add(jbtnSave);
		
		jbtnBack = new JButton("Back");
		jbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnBack_actionPerformed(e);
			}
		});
		jbtnBack.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbtnBack.setBounds(532, 181, 90, 28);
		jpanel.add(jbtnBack);
		
		jtextFieldSearch = new JTextField();
		jtextFieldSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldSearch.setBounds(159, 6, 260, 38);
		getContentPane().add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSearch_actionPerformed(arg0);
			}
		});
		btnSearch.setBounds(431, 9, 100, 35);
		getContentPane().add(btnSearch);
		
		lblSearchBookTitle = new JLabel("Search book title");
		lblSearchBookTitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSearchBookTitle.setBounds(21, 6, 142, 38);
		getContentPane().add(lblSearchBookTitle);
		
		jbtnAll = new JButton("All");
		jbtnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnAll_actionPerformed(e);
			}
		});
		jbtnAll.setBounds(657, 9, 100, 35);
		getContentPane().add(jbtnAll);
		loadInternalJFrame();
	}
	private void loadInternalJFrame() {
		BookModel bookModel = new BookModel();
		autoFillDateToTable(bookModel.findAll());
		AuthorModel authorModel = new AuthorModel();
		List<Author> authors = authorModel.findAll();
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for(Author author : authors) {
			defaultComboBoxModel.addElement(author.getName());
		}
		CategoryModel categoryModel = new CategoryModel();
		DefaultComboBoxModel<String> defaultComboBoxModel1 = new DefaultComboBoxModel<String>();
		for(Category category : categoryModel.findAll()) {
			defaultComboBoxModel1.addElement(category.getType());
		}
		jcomboBoxAuthor.setModel(defaultComboBoxModel);
		jcomboBoxCategory.setModel(defaultComboBoxModel1);
		jlabelISBN.setVisible(false);
		jtextFieldISBN.setVisible(false);
		jbtnSave.setVisible(false);
		jbtnBack.setVisible(false);
		jbtnUpdate.setEnabled(false);
		jbtnDelete.setEnabled(false);
	}
	private void autoFillDateToTable(List<Book> books) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("ISBN");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Author");
		defaultTableModel.addColumn("Category");
		defaultTableModel.addColumn("Quantity");
		
		for(Book book : books) {
			AuthorModel authorModel = new AuthorModel();
			Author author = authorModel.find(book.getAuthor_id());
			CategoryModel categoryModel = new CategoryModel();
			Category category = categoryModel.find(book.getCategory_id());
			defaultTableModel.addRow(new Object[] {
					book.getIsbn(),
					book.getName(),
					author.getName(),
					category.getType(),
					book.getQuantity(),
			});
		}
		jtableBook.setModel(defaultTableModel);
		jtableBook.getTableHeader().setReorderingAllowed(false);
	}
	private void jtableBook_mouseClicked(MouseEvent arg0) {
		int selectedRow = jtableBook.getSelectedRow();
		String isbn = (jtableBook.getValueAt(selectedRow, 0)).toString();
		BookModel bookModel = new BookModel();
		Book book = bookModel.find(isbn);
		AuthorModel authorModel = new AuthorModel();
		List<Author> authors = authorModel.findAll();
		int au_id = 0;
//		int cate_id = book.getCategory_id() - 1;
		int i = 0;
		for(Author author : authors) {
			if(author.getAuthor_id() == book.getAuthor_id()) {
				au_id = i;
				break;
			}
			i++;
		}
		CategoryModel categoryModel = new CategoryModel();
		List<Category> categories = categoryModel.findAll();
		int j = 0;
		int cate_id = 0;
		for(Category category : categories) {
			if(category.getCategory_id() == book.getCategory_id()) {
				cate_id = j;
				break;
			}
			j++;
		}
//		int au_id = book.getAuthor_id() - 1;
		jcomboBoxCategory.setSelectedIndex(cate_id);
		jcomboBoxAuthor.setSelectedIndex(au_id);
		jtextFieldName.setText(book.getName().toString());
		jtextFieldQuantity.setText(String.valueOf(book.getQuantity()));
		jbtnUpdate.setEnabled(true);
		jbtnDelete.setEnabled(true);
	}
	private void btnSearch_actionPerformed(ActionEvent arg0) {
		String keyword = jtextFieldSearch.getText();
		BookModel bookModel = new BookModel();
		List<Book> books = bookModel.search(keyword);
		jtextFieldName.setText("");
		jtextFieldName.setText("");
		jtextFieldQuantity.setText("");
		jtextFieldSearch.setText("");
		jcomboBoxCategory.setSelectedIndex(0);
		jcomboBoxAuthor.setSelectedIndex(0);
		jtextFieldISBN.setText("");
		autoFillDateToTable(books);
	}
	private void jbtnAll_actionPerformed(ActionEvent e) {
		BookModel bookModel = new BookModel();
		List<Book> books = bookModel.findAll();
		jtextFieldName.setText("");
		jtextFieldName.setText("");
		jtextFieldQuantity.setText("");
		jtextFieldSearch.setText("");
		jcomboBoxCategory.setSelectedIndex(0);
		jcomboBoxAuthor.setSelectedIndex(0);
		jtextFieldISBN.setText("");
		autoFillDateToTable(books);
	}
	private void jbtnUpdate_actionPerformed(ActionEvent e) {
		try {
			int selectedRow = jtableBook.getSelectedRow();
			String isbn = jtableBook.getValueAt(selectedRow, 0).toString();
			BookModel bookModel = new BookModel();
			Book book = bookModel.find(isbn);
			book.setName(jtextFieldName.getText().toString());
			book.setQuantity(Integer.parseInt(jtextFieldQuantity.getText().toString()));
			//get the index of the author selected in the combo box
			int selectedIndex = jcomboBoxAuthor.getSelectedIndex();
			//get author information that is selected in jcomboBoxAuthor
			AuthorModel authorModel = new AuthorModel();
			Author author = authorModel.findAll().get(selectedIndex);
			//set new author_id into book
			book.setAuthor_id(author.getAuthor_id());
			//get the index of the category selected in the combo box
			int selectedIndex1 = jcomboBoxCategory.getSelectedIndex();
			//get category information that is selected in jcomboBoxCategory
			CategoryModel categoryModel = new CategoryModel();
			Category category = categoryModel.findAll().get(selectedIndex1);
			//set new category_id into book
			book.setCategory_id(category.getCategory_id());
			if(bookModel.update(book)) {
				JOptionPane.showMessageDialog(null, "Successfully Updated.");
				autoFillDateToTable(bookModel.findAll());
			}else {
				JOptionPane.showMessageDialog(null, "Updated Failed.");
			}
		} catch (Exception e2) {
			// TODO: handle exception
			System.err.println(e2.getMessage());
		}
	}
	private void jbtnDelete_actionPerformed(ActionEvent arg0) {
		jtextFieldName.setText("");
		jtextFieldQuantity.setText("");
		jtextFieldSearch.setText("");
		jcomboBoxCategory.setSelectedIndex(0);
		jcomboBoxAuthor.setSelectedIndex(0);
		jtextFieldISBN.setText("");
		try {
			BookModel bookModel = new BookModel();
			int result = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete this book?","Confirm",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				int selectedRow = jtableBook.getSelectedRow();
				String isbn = jtableBook.getValueAt(selectedRow, 0).toString();
				if(bookModel.delete(isbn)) {
					JOptionPane.showMessageDialog(null, "Successfully Deleted.");
					autoFillDateToTable(bookModel.findAll());
				}else {
					JOptionPane.showMessageDialog(null, "Deleted Failed.");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Canceled.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error!!!");
		}
	}
	private void jbtnCreate_actionPerformed(ActionEvent arg0) {
		jlabelISBN.setVisible(true);
		jtextFieldISBN.setVisible(true);
		jbtnSave.setVisible(true);
		jbtnBack.setVisible(true);
		jbtnCreate.setVisible(false);
		jtextFieldName.setText("");
		jtextFieldQuantity.setText("");
		jtextFieldSearch.setText("");
		jcomboBoxCategory.setSelectedIndex(0);
		jcomboBoxAuthor.setSelectedIndex(0);
		jtextFieldISBN.setText("");
		
	}
	private void jbtnSave_actionPerformed(ActionEvent e) {
		jlabelISBN.setVisible(false);
		jtextFieldISBN.setVisible(false);
		jbtnSave.setVisible(false);
		jbtnCreate.setVisible(true);
		jbtnBack.setVisible(false);
		try {
			Book book = new Book();
			book.setName(jtextFieldName.getText());
			book.setIsbn(jtextFieldISBN.getText());
			book.setQuantity(Integer.parseInt(jtextFieldQuantity.getText().toString()));
			int selectedIndex = jcomboBoxAuthor.getSelectedIndex();
			AuthorModel authorModel = new AuthorModel();
			Author author = authorModel.findAll().get(selectedIndex);
			CategoryModel categoryModel = new CategoryModel();
			Category category = categoryModel.findAll().get(selectedIndex);
			book.setCategory_id(category.getCategory_id());
			book.setAuthor_id(author.getAuthor_id());
			BookModel bookModel = new BookModel();
			jtextFieldName.setText("");
			jtextFieldQuantity.setText("");
			jtextFieldSearch.setText("");
			jcomboBoxCategory.setSelectedIndex(0);
			jcomboBoxAuthor.setSelectedIndex(0);
			jtextFieldISBN.setText("");
			if(bookModel.create(book)) {
				JOptionPane.showMessageDialog(null, "Successfully Created.");
				autoFillDateToTable(bookModel.findAll());
			}else {
				JOptionPane.showMessageDialog(null, "Created Failed.");
			}
		} catch (Exception e2) {
			// TODO: handle exception
			System.err.println(e2.getMessage());
			JOptionPane.showMessageDialog(null, "Error!!!");
		}
	}
	private void jbtnBack_actionPerformed(ActionEvent e) {
		jtextFieldName.setText("");
		jtextFieldQuantity.setText("");
		jtextFieldSearch.setText("");
		jcomboBoxCategory.setSelectedIndex(0);
		jcomboBoxAuthor.setSelectedIndex(0);
		jtextFieldISBN.setText("");
		jtextFieldISBN.setVisible(false);
		jbtnSave.setVisible(false);
		jtextFieldISBN.setVisible(false);
		jbtnBack.setVisible(false);
		jbtnCreate.setVisible(true);
	}
}
