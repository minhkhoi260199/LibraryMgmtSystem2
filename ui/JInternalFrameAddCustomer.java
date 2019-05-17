package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entities.Category;
import entities.Customer;
import models.CategoryModel;
import models.CustomerModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class JInternalFrameAddCustomer extends JInternalFrame {
	private JTable jTable;
	private JTextField jtextFieldName;
	private JTextField jtextFieldAddress;
	private JTextField jtextFieldPhone;
	private JPanel panel;
	private JButton jbtnDelete;
	private JButton jbtnAdd;
	private JButton jbtnUpdate;
	private JTextField jtextFieldSearch;
	private JButton jbtnSearch;
	private JComboBox jcomboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JInternalFrameAddCustomer frame = new JInternalFrameAddCustomer();
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
	public JInternalFrameAddCustomer() {
		setTitle("Customer");
		setClosable(true);
		setBounds(100, 100, 579, 507);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 57, 557, 185);
		getContentPane().add(scrollPane);
		
		jTable = new JTable();
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jTable_mouseClicked(e);
			}
		});
		scrollPane.setViewportView(jTable);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 270, 557, 188);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(19, 29, 71, 26);
		panel.add(lblName);
		
		jtextFieldName = new JTextField();
		jtextFieldName.setBounds(102, 29, 424, 26);
		panel.add(jtextFieldName);
		jtextFieldName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(19, 67, 71, 26);
		panel.add(lblAddress);
		
		jtextFieldAddress = new JTextField();
		jtextFieldAddress.setColumns(10);
		jtextFieldAddress.setBounds(102, 67, 424, 26);
		panel.add(jtextFieldAddress);
		
		JLabel lblPhone = new JLabel("Phone: ");
		lblPhone.setBounds(19, 105, 71, 26);
		panel.add(lblPhone);
		
		jtextFieldPhone = new JTextField();
		jtextFieldPhone.setColumns(10);
		jtextFieldPhone.setBounds(102, 105, 424, 26);
		panel.add(jtextFieldPhone);
		
		jbtnAdd = new JButton("Add");
		jbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnAdd_actionPerformed(arg0);
			}
		});
		jbtnAdd.setBounds(102, 143, 90, 28);
		panel.add(jbtnAdd);
		
		jbtnDelete = new JButton("Delete");
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnDelete_actionPerformed(e) ;
			}
		});
		jbtnDelete.setBounds(204, 143, 90, 28);
		panel.add(jbtnDelete);
		
		jbtnUpdate = new JButton("Update");
		jbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnUpdate_actionPerformed(e);
			}
		});
		jbtnUpdate.setBounds(306, 143, 90, 28);
		panel.add(jbtnUpdate);
		
		jtextFieldSearch = new JTextField();
		jtextFieldSearch.setBounds(6, 6, 315, 34);
		getContentPane().add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);
		
		jbtnSearch = new JButton("Search");
		jbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSearch_actionPerformed(e);
			}
		});
		jbtnSearch.setBounds(333, 6, 90, 34);
		getContentPane().add(jbtnSearch);
		
		jcomboBox = new JComboBox();
		jcomboBox.setBounds(435, 6, 126, 34);
		getContentPane().add(jcomboBox);
		
		loadJInternalFrame();
		
	}
	//Load JInternalFrame
	private void loadJInternalFrame() {
		CustomerModel customerModel = new CustomerModel();
		fillDatatoJTable(customerModel.findAll());
		List<Customer> customers = customerModel.findAll();
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		defaultComboBoxModel.addElement("Name");
		defaultComboBoxModel.addElement("Phone");
		defaultComboBoxModel.addElement("Address");
		jcomboBox.setModel(defaultComboBoxModel);
		jbtnDelete.setEnabled(false);
		jbtnUpdate.setEnabled(false);
	}
	//MouseClick
	private void jTable_mouseClicked(MouseEvent e) {
		int selectedRow = jTable.getSelectedRow();
		int id =Integer.parseInt(jTable.getValueAt(selectedRow, 0).toString()) ;
		CustomerModel customerModel = new CustomerModel() ;
		Customer customer =customerModel.find(id);
		jtextFieldName.setText(customer.getName());
		jtextFieldAddress.setText(customer.getAddress());
		jtextFieldPhone.setText(customer.getPhone());
		jbtnAdd.setEnabled(true);
		jbtnDelete.setEnabled(true);
		jbtnUpdate.setEnabled(true);
	}
	//Search on table
	private void jbtnSearch_actionPerformed(ActionEvent e) {
		String kw= jtextFieldSearch.getText();
		CustomerModel customerModel = new CustomerModel();
		int type = jcomboBox.getSelectedIndex();
		if(type == 0 ) {
			List<Customer> customers = customerModel.searchByName(kw);
			if(customers.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter name of customer you want to find");
			}else {
				fillDatatoJTable(customerModel.searchByName(kw));
			}
		}else if(type == 1) {
			List<Customer> customers = customerModel.searchByPhone(kw);
			if(customers.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter phone of customer you want to find");
			}else {
				fillDatatoJTable(customerModel.searchByPhone(kw));
			}
		}else {
			List<Customer> customers = customerModel.searchByAddress(kw);
			if(customers.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter address of customer you want to find");
			}else {
				fillDatatoJTable(customerModel.searchByAddress(kw));
			}
		}
		
		
	}
	//Delete customer
	private void jbtnDelete_actionPerformed(ActionEvent e) {
		try {
			int result = JOptionPane.showConfirmDialog(null, "Are U sure??", "Comfirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				int selectedRow = jTable.getSelectedRow();
				int user_id =Integer.parseInt(jTable.getValueAt(selectedRow, 0).toString()) ;
				CustomerModel customerModel = new CustomerModel();
				if (customerModel.delete(user_id)) {
					JOptionPane.showMessageDialog(null, "Done");
					fillDatatoJTable(customerModel.findAll());
				}else {
					JOptionPane.showMessageDialog(null, "Failed");
				}
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}
	//Update customer
	private void jbtnUpdate_actionPerformed(ActionEvent e) {
		try {
			int selectedRow = jTable.getSelectedRow();
			int user_id =Integer.parseInt(jTable.getValueAt(selectedRow, 0).toString()) ;
			CustomerModel customerModel = new CustomerModel() ;
			Customer customer =customerModel.find(user_id);
			customer.setName(jtextFieldName.getText());
			customer.setAddress(jtextFieldAddress.getText());
			if(jtextFieldPhone.getText().length()< 10){
				JOptionPane.showMessageDialog(null, "Phone number must bigger 10 number");
			}else {
				customer.setPhone(jtextFieldPhone.getText());
				if(customerModel.update(customer)) {
					JOptionPane.showMessageDialog(null, "Done");
					fillDatatoJTable(customerModel.findAll());
				}
				else {
					JOptionPane.showMessageDialog(null, "Failed");
				}
			}
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Failed");
		}
		
	}
	//Add customer
	private void jbtnAdd_actionPerformed(ActionEvent arg0) {
		try {
			CustomerModel customerModel= new CustomerModel();
			Customer customer = new Customer();
			if(jtextFieldName.getText().isEmpty() || jtextFieldAddress.getText().isEmpty()||
					jtextFieldPhone.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Pls enter full customer information");
			}else {
				customer.setName(jtextFieldName.getText());
				customer.setAddress(jtextFieldAddress.getText());
				if(jtextFieldPhone.getText().length()< 10){
					JOptionPane.showMessageDialog(null, "Phone number must bigger 10 number");
				}else {
					customer.setPhone(jtextFieldPhone.getText());
					if(customerModel.create(customer)) {
						JOptionPane.showMessageDialog(null, "Successfull");
						fillDatatoJTable(customerModel.findAll());
					}
					else {
						JOptionPane.showMessageDialog(null, "Failed");
					}
				}
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}
	//fillDatatoJTable
	private void fillDatatoJTable(List<Customer> customers) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("User_id");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Address");
		defaultTableModel.addColumn("Phone");
		for(Customer customer : customers) {
			defaultTableModel.addRow(new Object[] {
					customer.getUser_id(),
					customer.getName(),
					customer.getAddress(),
					customer.getPhone()
			});
		}
		jTable.setModel(defaultTableModel);
		jTable.getTableHeader().setReorderingAllowed(false);
		
	}
}
