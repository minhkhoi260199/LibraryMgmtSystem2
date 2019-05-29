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
import entities.BookItem;
import entities.Category;
import models.AuthorModel;
import models.BookItemModel;
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
import javax.swing.JDesktopPane;

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
	private JButton jbuttonOpenBookItem;
	private JComboBox jcomboBoxFillter;
	private JLabel lblBy;

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
		setBounds(100, 100, 818, 480);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 56, 766, 196);
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
		jpanel.setBounds(31, 263, 745, 177);
		getContentPane().add(jpanel);
		jpanel.setLayout(null);
		
		JLabel jlabelName = new JLabel("Name");
		jlabelName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelName.setBounds(51, 21, 59, 35);
		jpanel.add(jlabelName);
		
		JLabel jlabelAuthor = new JLabel("Author");
		jlabelAuthor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelAuthor.setBounds(51, 72, 72, 35);
		jpanel.add(jlabelAuthor);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblCategory.setBounds(398, 72, 72, 35);
		jpanel.add(lblCategory);
		
		JLabel jlabelQuantity = new JLabel("Quantity");
		jlabelQuantity.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jlabelQuantity.setBounds(398, 21, 72, 35);
		jpanel.add(jlabelQuantity);
		
		jtextFieldName = new JTextField();
		jtextFieldName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldName.setBounds(121, 21, 223, 28);
		jpanel.add(jtextFieldName);
		jtextFieldName.setColumns(10);
		
		jtextFieldQuantity = new JTextField();
		jtextFieldQuantity.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldQuantity.setColumns(10);
		jtextFieldQuantity.setBounds(475, 21, 218, 28);
		jpanel.add(jtextFieldQuantity);
		
		jbtnUpdate = new JButton("Update");
		jbtnUpdate.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnUpdate_actionPerformed(e);
			}
		});
		jbtnUpdate.setBounds(367, 134, 90, 28);
		jpanel.add(jbtnUpdate);
		
		jbtnDelete = new JButton("Delete");
		jbtnDelete.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnDelete_actionPerformed(arg0);
			}
		});
		jbtnDelete.setBounds(493, 134, 90, 28);
		jpanel.add(jbtnDelete);
		
		jbtnCreate = new JButton("Create");
		jbtnCreate.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbtnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnCreate_actionPerformed(arg0);
			}
		});
		jbtnCreate.setBounds(625, 134, 90, 28);
		jpanel.add(jbtnCreate);
		
		jcomboBoxAuthor = new JComboBox();
		jcomboBoxAuthor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jcomboBoxAuthor.setBounds(120, 72, 224, 28);
		jpanel.add(jcomboBoxAuthor);
		
		jcomboBoxCategory = new JComboBox();
		jcomboBoxCategory.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jcomboBoxCategory.setBounds(474, 72, 218, 28);
		jpanel.add(jcomboBoxCategory);
		
		jbuttonOpenBookItem = new JButton("BookItem List");
		jbuttonOpenBookItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbuttonOpenBookItem_actionPerformed(e);
			}
		});
		jbuttonOpenBookItem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jbuttonOpenBookItem.setEnabled(false);
		jbuttonOpenBookItem.setBounds(68, 134, 145, 28);
		jpanel.add(jbuttonOpenBookItem);
		
		jtextFieldSearch = new JTextField();
		jtextFieldSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		jtextFieldSearch.setBounds(159, 11, 237, 33);
		getContentPane().add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSearch_actionPerformed(arg0);
			}
		});
		btnSearch.setBounds(406, 12, 100, 35);
		getContentPane().add(btnSearch);
		
		lblSearchBookTitle = new JLabel("Search book title");
		lblSearchBookTitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSearchBookTitle.setBounds(21, 11, 142, 33);
		getContentPane().add(lblSearchBookTitle);
		
		jbtnAll = new JButton("All");
		jbtnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnAll_actionPerformed(e);
			}
		});
		jbtnAll.setBounds(687, 12, 100, 35);
		getContentPane().add(jbtnAll);
		
		jcomboBoxFillter = new JComboBox();
		jcomboBoxFillter.setBounds(553, 11, 100, 34);
		getContentPane().add(jcomboBoxFillter);
		
		lblBy = new JLabel("by:");
		lblBy.setBounds(516, 21, 27, 14);
		getContentPane().add(lblBy);
		loadInternalJFrame();
	}
	private void loadInternalJFrame() {
		//Fill book table
		BookModel bookModel = new BookModel();
		autoFillDataToTable(bookModel.findAll());
		//Fill author combobox
		AuthorModel authorModel = new AuthorModel();
		List<Author> authors = authorModel.findAll();
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for(Author author : authors) {
			defaultComboBoxModel.addElement(author.getName());
		}
		//fill category combobox
		CategoryModel categoryModel = new CategoryModel();
		DefaultComboBoxModel<String> defaultComboBoxModel1 = new DefaultComboBoxModel<String>();
		for(Category category : categoryModel.findAll()) {
			defaultComboBoxModel1.addElement(category.getType());
		}
		jcomboBoxAuthor.setModel(defaultComboBoxModel);
		jcomboBoxCategory.setModel(defaultComboBoxModel1);
		jbtnUpdate.setEnabled(false);
		jbtnDelete.setEnabled(false);
		jbuttonOpenBookItem.setEnabled(false);
		//Seach combobox
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
		comboBoxModel.addElement("Title");
		comboBoxModel.addElement("ISBN");
		comboBoxModel.addElement("Category");
		comboBoxModel.addElement("Author");
		jcomboBoxFillter.setModel(comboBoxModel);
	}
	public void autoFillDataToTable(List<Book> books) {
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
					book.getTitle(),
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
		jtextFieldName.setText(book.getTitle().toString());
		jtextFieldQuantity.setText(String.valueOf(book.getQuantity()));
		jbuttonOpenBookItem.setEnabled(true);
		jbtnUpdate.setEnabled(true);
		jbtnDelete.setEnabled(true);
		jbtnCreate.setEnabled(false);
	}
	private void btnSearch_actionPerformed(ActionEvent arg0) {
		String keyword = jtextFieldSearch.getText();
		String type = (String) jcomboBoxFillter.getSelectedItem().toString().toLowerCase(); // noi chuoi
		BookModel bookModel = new BookModel();
		List<Book> books = bookModel.search(type, keyword);
		jtextFieldName.setText("");
		jtextFieldQuantity.setText("");
		jtextFieldSearch.setText("");
		jcomboBoxCategory.setSelectedIndex(0);
		jcomboBoxAuthor.setSelectedIndex(0);
		autoFillDataToTable(books);
	}
	private void jbtnAll_actionPerformed(ActionEvent e) {
		BookModel bookModel = new BookModel();
		autoFillDataToTable(bookModel.findAll());
		jtextFieldName.setText("");
		jtextFieldQuantity.setText("");
		jtextFieldSearch.setText("");
		jcomboBoxCategory.setSelectedIndex(0);
		jcomboBoxAuthor.setSelectedIndex(0);
		jbuttonOpenBookItem.setEnabled(false);
		jbtnUpdate.setEnabled(false);
		jbtnDelete.setEnabled(false);
		jbtnCreate.setEnabled(true);
	}
	private void jbtnUpdate_actionPerformed(ActionEvent e) {
		try {
			int selectedRow = jtableBook.getSelectedRow();
			String isbn = jtableBook.getValueAt(selectedRow, 0).toString();
			BookModel bookModel = new BookModel();
			Book book = bookModel.find(isbn);
			book.setTitle(jtextFieldName.getText().toString());
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
				JOptionPane.showMessageDialog(null, "Book Successfully Updated.");
				//Update bookitem
				BookItemModel bookItemModel = new BookItemModel();
				if(bookItemModel.deleteBookitemByISBN(isbn)) {
					int flag = 0;
					int totalQuantity = 0;
					for(Book b : bookModel.findAll()) {
						totalQuantity += b.getQuantity();
					}
					for(int i=1; i<=book.getQuantity(); i++) {
						BookItem bookItem = new BookItem();
						String callnumber = book.getTitle().charAt(0)+""+book.getTitle().charAt(1)+"-"+author.getName().charAt(0)+author.getName().charAt(1)+"-"+(totalQuantity+i);
						bookItem.setCallnumber(callnumber);
						bookItem.setIsbn(book.getIsbn());
						bookItem.setStatus(0);
						if(bookItemModel.createBookItem(bookItem)) {
							flag = 1;
						} else {
							flag = 0;
						}
					}
					if(flag == 1) {
						JOptionPane.showMessageDialog(null, book.getQuantity()+" bookItem updated");
						jtextFieldName.setText("");
						jtextFieldQuantity.setText("");
						jtextFieldSearch.setText("");
						jcomboBoxCategory.setSelectedIndex(0);
						jcomboBoxAuthor.setSelectedIndex(0);
					} else {
						JOptionPane.showMessageDialog(null, "Created Failed.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Update bookItem Failed.");
				}
				autoFillDataToTable(bookModel.findAll());
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
		try {
			BookModel bookModel = new BookModel();
			int result = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete this book?","Confirm",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				int selectedRow = jtableBook.getSelectedRow();
				String isbn = jtableBook.getValueAt(selectedRow, 0).toString();
				BookItemModel bookItemModel = new BookItemModel();
				if(bookItemModel.deleteBookitemByISBN(isbn)) {
					Book book = bookModel.find(isbn);
					JOptionPane.showMessageDialog(null, "Deleted "+book.getQuantity()+" bookItems");
					if(bookModel.delete(isbn)) {
					JOptionPane.showMessageDialog(null, "Successfully Deleted.");
					autoFillDataToTable(bookModel.findAll());
					}else {
					JOptionPane.showMessageDialog(null, "Deleted Failed.");
					}
				} else {
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
		JinternalFrameCreateBook jinternalFrameCreateBook = new JinternalFrameCreateBook();
		JDesktopPane desktopPane = (JDesktopPane) getParent(); // lay desktop Pane tu parent
		desktopPane.add(jinternalFrameCreateBook);
		jinternalFrameCreateBook.setVisible(true);
	}
	private void jbuttonOpenBookItem_actionPerformed(ActionEvent e) {
		JinternalFrameListBookItem jinternalFrameListBookItem = new JinternalFrameListBookItem();
		JDesktopPane desktopPane = (JDesktopPane) getParent(); // lay desktop Pane tu parent
		desktopPane.add(jinternalFrameListBookItem);
		jinternalFrameListBookItem.setVisible(true);
		//Load thong tin selected book
		BookItemModel bookItemModel = new BookItemModel();
		int selectedRow = jtableBook.getSelectedRow();
		jinternalFrameListBookItem.autoFillDataToTable(bookItemModel.findAllbyISBN((jtableBook.getValueAt(selectedRow, 0)).toString()));
	}
}
