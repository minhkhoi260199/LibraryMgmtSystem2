package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;

import entities.Author;
import entities.Book;
import entities.BookItem;
import entities.Category;
import models.AuthorModel;
import models.BookItemModel;
import models.BookModel;
import models.CategoryModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class JinternalFrameCreateBook extends JInternalFrame {
	private JTextField jtextFieldISBN;
	private JTextField jtextFieldBooktitle;
	private JTextField jtextFieldQuantity;
	private JComboBox jcomboBoxAuthor;
	private JComboBox jcomboBoxCategory;
	private JButton jbtnCreateBook;
	private JTextField jtextFieldPrice;

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
					JinternalFrameCreateBook frame = new JinternalFrameCreateBook();
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
	public JinternalFrameCreateBook() {
		setClosable(true);
		setTitle("Create book");
		setBounds(100, 100, 357, 319);
		getContentPane().setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(24, 22, 67, 16);
		getContentPane().add(lblIsbn);
		
		JLabel lblBookTitle = new JLabel("Book title");
		lblBookTitle.setBounds(24, 56, 67, 16);
		getContentPane().add(lblBookTitle);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(24, 94, 67, 16);
		getContentPane().add(lblQuantity);
		
		jtextFieldISBN = new JTextField();
		jtextFieldISBN.setBounds(98, 16, 227, 28);
		getContentPane().add(jtextFieldISBN);
		jtextFieldISBN.setColumns(10);
		
		jtextFieldBooktitle = new JTextField();
		jtextFieldBooktitle.setColumns(10);
		jtextFieldBooktitle.setBounds(98, 50, 227, 28);
		getContentPane().add(jtextFieldBooktitle);
		
		jtextFieldQuantity = new JTextField();
		jtextFieldQuantity.setColumns(10);
		jtextFieldQuantity.setBounds(98, 88, 127, 28);
		getContentPane().add(jtextFieldQuantity);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(24, 132, 67, 16);
		getContentPane().add(lblAuthor);
		
		jcomboBoxAuthor = new JComboBox();
		jcomboBoxAuthor.setBounds(98, 127, 227, 26);
		getContentPane().add(jcomboBoxAuthor);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(24, 171, 67, 16);
		getContentPane().add(lblCategory);
		
		jcomboBoxCategory = new JComboBox();
		jcomboBoxCategory.setBounds(98, 166, 227, 26);
		getContentPane().add(jcomboBoxCategory);
		
		jbtnCreateBook = new JButton("Create");
		jbtnCreateBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnCreateBook_actionPerformed(arg0);
			}
		});
		jbtnCreateBook.setBounds(99, 242, 90, 28);
		getContentPane().add(jbtnCreateBook);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(24, 208, 67, 16);
		getContentPane().add(lblPrice);
		
		jtextFieldPrice = new JTextField();
		jtextFieldPrice.setColumns(10);
		jtextFieldPrice.setBounds(98, 202, 127, 28);
		getContentPane().add(jtextFieldPrice);
		
		loadInternalJFrame();

	}
	
	public void loadInternalJFrame() {
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
	}
	
	private void jbtnCreateBook_actionPerformed(ActionEvent arg0) {
		try {
				//Get input data
				if(Integer.parseInt(jtextFieldQuantity.getText().toString()) <0 ) {
					JOptionPane.showMessageDialog(null, "The number of quantity must be positive");
				}else {
					if(Integer.parseInt(jtextFieldPrice.getText().toString()) <1000) {
						JOptionPane.showMessageDialog(null, "The price must bigger 1000");
					}else {
						Book book = new Book();
						book.setIsbn(jtextFieldISBN.getText());
						BookModel bookModel = new BookModel();
						Book bookCheck = bookModel.find(book.getIsbn());
						if(bookCheck == null) {
							book.setTitle(jtextFieldBooktitle.getText());
							book.setQuantity(Integer.parseInt(jtextFieldQuantity.getText().toString()));
								//Get author
								int selectedAuthorIndex = jcomboBoxAuthor.getSelectedIndex();
								AuthorModel authorModel = new AuthorModel();
								Author author = authorModel.findAll().get(selectedAuthorIndex);
								//Get category
								int selectedCategoryIndex = jcomboBoxCategory.getSelectedIndex();
								CategoryModel categoryModel = new CategoryModel();
								Category category = categoryModel.findAll().get(selectedCategoryIndex);
							//
							book.setCategory_id(category.getCategory_id());
							book.setAuthor_id(author.getAuthor_id());
							book.setPrice(Integer.parseInt(jtextFieldPrice.getText().toString()));
							
							//Create new book
							if(bookModel.create(book)) {
								JOptionPane.showMessageDialog(null, "Book successfully Created.");
							////Create bookItem by quantity -----------------------------------------
								BookItemModel bookItemModel = new BookItemModel();
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
									JOptionPane.showMessageDialog(null, book.getQuantity()+" bookItem created");
//									JInternalFrameBook internalFrameBook = new JInternalFrameBook();
//									internalFrameBook.autoFillDataToTable(bookModel.findAll());
									this.setVisible(false);
								} else {
									JOptionPane.showMessageDialog(null, "Created Failed.");
								}
							}else {
								JOptionPane.showMessageDialog(null, "Created Failed.");
							}
						}else {
							JOptionPane.showMessageDialog(null, "The ISBN is existed.");
						}
					
					}
					
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
				System.err.println(e2.getMessage());
				JOptionPane.showMessageDialog(null, "Please don't leave the fill blank!!");
			}

	}
}
