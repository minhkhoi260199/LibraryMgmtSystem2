package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import entities.Category;
import models.CategoryModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JinternalFrameCategory extends JInternalFrame {
	private JTextField jtextFieldCategoryName;
	private JButton jbtnAddNew;
	private JButton jbtnUpdate;
	private JButton jbtnDelete;
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
					JinternalFrameCategory frame = new JinternalFrameCategory();
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
	public JinternalFrameCategory() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedOut(arg0);
			}
		});
		setIconifiable(true);
		setClosable(true);
		setTitle("Manage Categories");
		setBounds(100, 100, 433, 308);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Category Name:");
		lblNewLabel.setBounds(17, 174, 103, 28);
		getContentPane().add(lblNewLabel);
		
		jtextFieldCategoryName = new JTextField();
		jtextFieldCategoryName.setColumns(10);
		jtextFieldCategoryName.setBounds(132, 174, 272, 28);
		getContentPane().add(jtextFieldCategoryName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 6, 387, 136);
		getContentPane().add(scrollPane);
		
		jTable = new JTable();
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jTable_mouseClicked(e);
			}
		});
		scrollPane.setViewportView(jTable);
		
		jbtnAddNew = new JButton("Add New");
		jbtnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnAddNew_actionPerformed(arg0);
			}
		});
		jbtnAddNew.setBounds(44, 231, 90, 28);
		getContentPane().add(jbtnAddNew);
		
		jbtnUpdate = new JButton("Update");
		jbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnUpdate_actionPerformed(arg0);
			}
		});
		jbtnUpdate.setBounds(171, 231, 90, 28);
		getContentPane().add(jbtnUpdate);
		
		jbtnDelete = new JButton("Delete");
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnDelete_actionPerformed(arg0);
			}
		});
		jbtnDelete.setBounds(296, 231, 90, 28);
		getContentPane().add(jbtnDelete);

		loadJInternalFrame();
		
	}
	
	private void loadJInternalFrame() {
		CategoryModel categoryModel = new CategoryModel();
		fillDatatoJTable(categoryModel.findAll());
		
		jbtnDelete.setEnabled(false);
		jbtnUpdate.setEnabled(false);
	}
	private void mouseClickedOut(MouseEvent arg0) {
		loadJInternalFrame();
		jtextFieldCategoryName.setText("");
		jbtnAddNew.setEnabled(true);
		jbtnDelete.setEnabled(false);
		jbtnUpdate.setEnabled(false);
	}
	private void jTable_mouseClicked(MouseEvent e) {
		int selectedRow = jTable.getSelectedRow();
		int id =Integer.parseInt(jTable.getValueAt(selectedRow, 0).toString()) ;
		CategoryModel categoryModel = new CategoryModel();
		Category category = categoryModel.find(id);
		jtextFieldCategoryName.setText(category.getType());
		jbtnAddNew.setEnabled(false);
		jbtnDelete.setEnabled(true);
		jbtnUpdate.setEnabled(true);
	}

	public void jbtnAddNew_actionPerformed(ActionEvent arg0) {
		try {
			CategoryModel categoryModel = new CategoryModel();
			Category category = new Category();
			String typeLong = jtextFieldCategoryName.getText().toLowerCase();
			if(categoryModel.checkCategory(typeLong)) {
				if (jtextFieldCategoryName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Pls enter name of category you want to add");
				} else {
					category.setType(jtextFieldCategoryName.getText());
					if (categoryModel.create(category)) {
						JOptionPane.showMessageDialog(null, "Done");
						fillDatatoJTable(categoryModel.findAll());
						jtextFieldCategoryName.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "Failed");
					}
				}

			}else {
				JOptionPane.showMessageDialog(null, "Category already in the table");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}
	
	public void jbtnUpdate_actionPerformed(ActionEvent arg0) {
		try {
			int selectedRow = jTable.getSelectedRow();
			int category_id =Integer.parseInt(jTable.getValueAt(selectedRow, 0).toString()) ;
			CategoryModel categoryModel =new CategoryModel();
			Category category = categoryModel.find(category_id);
			category.setType(jtextFieldCategoryName.getText());
			if (categoryModel.update(category)) {
				JOptionPane.showMessageDialog(null, "Done");
				fillDatatoJTable(categoryModel.findAll());
				jbtnAddNew.setEnabled(true);
				jbtnDelete.setEnabled(false);
				jbtnUpdate.setEnabled(false);
				jtextFieldCategoryName.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "Failed");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}

	public void jbtnDelete_actionPerformed(ActionEvent arg0) {
		try {
			int result = JOptionPane.showConfirmDialog(null, "Are U sure??", "Comfirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				int selectedRow = jTable.getSelectedRow();
				int category_id =Integer.parseInt(jTable.getValueAt(selectedRow, 0).toString()) ;
				CategoryModel categoryModel =new CategoryModel();
				if(categoryModel.delete(category_id)) {
					JOptionPane.showMessageDialog(null, "Done");
					fillDatatoJTable(categoryModel.findAll());
					jbtnAddNew.setEnabled(true);
					jbtnDelete.setEnabled(false);
					jbtnUpdate.setEnabled(false);
					jtextFieldCategoryName.setText("");
					
				}else {
					JOptionPane.showMessageDialog(null, "Failed");
				}
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed");
		}
				
	} 
	private void fillDatatoJTable(List<Category> categories) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Category_id");
		defaultTableModel.addColumn("Type");
		for(Category category : categories ) {
			defaultTableModel.addRow(new Object[] {
				category.getCategory_id(),
				category.getType()
			});
		}
		jTable.setModel(defaultTableModel);
		jTable.getTableHeader().setReorderingAllowed(false);
	}
}
