package ui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entities.Employee;
import helper.BCrypt;
import models.EmployeeModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class JInternalFrameEmployee extends JInternalFrame {
	private JTextField jtextFieldSearch;
	private JTable jTable;
	private JTextField jtextFieldName;
	private JTextField jtextFieldAddress;
	private JTextField jtextFieldPhone;
	private JTextField jtextFieldDepartment;
	private JButton jbtnUpdate;
	private JButton jbtnDelete;
	private JButton jbtnAdd;
	private JTextField jtextFieldUsername;
	private JPasswordField jpasswordField;
	private JComboBox jcomboBoxSearchType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JInternalFrameEmployee frame = new JInternalFrameEmployee();
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
	public JInternalFrameEmployee() {
		setTitle("Employee Info");
		setClosable(true);
		setBounds(100, 100, 644, 610);
		getContentPane().setLayout(null);
		
		jtextFieldSearch = new JTextField();
		jtextFieldSearch.setBounds(6, 6, 351, 33);
		getContentPane().add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);
		
		JButton jbtnSearch = new JButton("Search");
		jbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSearch_actionPerformed(e);
			}
		});
		jbtnSearch.setBounds(369, 6, 117, 33);
		getContentPane().add(jbtnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 51, 620, 192);
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
		panel.setBorder(new TitledBorder(null, "Employee Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 255, 620, 314);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Name: ");
		lblNewLabel.setBounds(18, 25, 131, 29);
		panel.add(lblNewLabel);
		
		jtextFieldName = new JTextField();
		jtextFieldName.setBounds(150, 25, 408, 29);
		panel.add(jtextFieldName);
		jtextFieldName.setColumns(10);
		
		JLabel lblEmployeeAddress = new JLabel("Employee Address: ");
		lblEmployeeAddress.setBounds(18, 146, 131, 29);
		panel.add(lblEmployeeAddress);
		
		jtextFieldAddress = new JTextField();
		jtextFieldAddress.setColumns(10);
		jtextFieldAddress.setBounds(150, 146, 408, 29);
		panel.add(jtextFieldAddress);
		
		JLabel lblEmployeePhone = new JLabel("Employee Phone: ");
		lblEmployeePhone.setBounds(18, 187, 131, 29);
		panel.add(lblEmployeePhone);
		
		jtextFieldPhone = new JTextField();
		jtextFieldPhone.setColumns(10);
		jtextFieldPhone.setBounds(150, 187, 408, 29);
		panel.add(jtextFieldPhone);
		
		JLabel lblEmployeeDeparment = new JLabel("Employee Department: ");
		lblEmployeeDeparment.setBounds(18, 227, 131, 29);
		panel.add(lblEmployeeDeparment);
		
		jtextFieldDepartment = new JTextField();
		jtextFieldDepartment.setColumns(10);
		jtextFieldDepartment.setBounds(150, 227, 408, 29);
		panel.add(jtextFieldDepartment);
		
		jbtnAdd = new JButton("Add");
		jbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnAdd_actionPerformed(e);
			}
		});
		jbtnAdd.setBounds(150, 267, 90, 28);
		panel.add(jbtnAdd);
		
		jbtnDelete = new JButton("Delete");
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnDelete_actionPerformed( e);
			}
		});
		jbtnDelete.setBounds(252, 267, 90, 28);
		panel.add(jbtnDelete);
		
		jbtnUpdate = new JButton("Update");
		jbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnUpdate_actionPerformed(e);
			}
		});
		jbtnUpdate.setBounds(354, 267, 90, 28);
		panel.add(jbtnUpdate);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(18, 65, 131, 29);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(18, 106, 131, 29);
		panel.add(lblPassword);
		
		jtextFieldUsername = new JTextField();
		jtextFieldUsername.setColumns(10);
		jtextFieldUsername.setBounds(150, 65, 408, 29);
		panel.add(jtextFieldUsername);
		
		jpasswordField = new JPasswordField();
		jpasswordField.setBounds(150, 108, 408, 29);
		panel.add(jpasswordField);
		
		jcomboBoxSearchType = new JComboBox();
		jcomboBoxSearchType.setBounds(498, 6, 128, 33);
		getContentPane().add(jcomboBoxSearchType);
		
		loadJInternalFrame();
	}
		
	//Search on table
	private void jbtnSearch_actionPerformed(ActionEvent e) {
		String kw= jtextFieldSearch.getText();
		EmployeeModel employeeModel = new EmployeeModel();
		String type = (String) jcomboBoxSearchType.getSelectedItem().toString().toLowerCase();
		List<Employee> employees = employeeModel.search(type,kw);
		if(employees.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Change right type and enter keyword you want to find");
		}else {
			fillDatatoJTable(employeeModel.search(type,kw));
		}
	}
	
	//Delete employee
	private void jbtnDelete_actionPerformed(ActionEvent e) {
		try {
			int result = JOptionPane.showConfirmDialog(null, "Are U sure??", "Comfirm", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				int selectRow = jTable.getSelectedRow();
				int employee_id = Integer.parseInt(jTable.getValueAt(selectRow, 0).toString());
				EmployeeModel employeeModel = new EmployeeModel();
				if (employeeModel.delete(employee_id)) {
					JOptionPane.showMessageDialog(null, "Done");
					fillDatatoJTable(employeeModel.findAll());
				}else {
					JOptionPane.showMessageDialog(null, "Failed");
				}
			}
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}
	
	//Update employee
	private void jbtnUpdate_actionPerformed(ActionEvent e) {
		try {
			int selectRow = jTable.getSelectedRow();
			int employee_id = Integer.parseInt(jTable.getValueAt(selectRow, 0).toString());
			EmployeeModel employeeModel = new EmployeeModel();
			Employee employee = employeeModel.find(employee_id);
			if(!jtextFieldUsername.getText().isEmpty()) {
				if(jtextFieldUsername.getText().length()<6) {
					JOptionPane.showMessageDialog(null, "Username must more than 6 characters ");
					
				}else {
					if(jtextFieldPhone.getText().length()< 10){
						JOptionPane.showMessageDialog(null, "Phone number must bigger 10 number");
					}else {
						employee.setUsername(jtextFieldUsername.getText());	
						String password = String.valueOf(jpasswordField.getPassword());
						employee.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
						employee.setName(jtextFieldName.getText());
						employee.setAddress(jtextFieldAddress.getText());
						employee.setPhone(jtextFieldPhone.getText());
						employee.setDepartment(jtextFieldDepartment.getText());
						if(employeeModel.update(employee)) {
							JOptionPane.showMessageDialog(null, "Done");
							fillDatatoJTable(employeeModel.findAll());
						}else {
							JOptionPane.showMessageDialog(null, "Failed");
						}
					}
					
				}
			}
			
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}
	
	//create employee
	private void jbtnAdd_actionPerformed(ActionEvent e) {
		try {
			EmployeeModel employeeModel = new EmployeeModel();
			Employee employee = new Employee();
			String password = String.valueOf(jpasswordField.getPassword());
			if(jtextFieldName.getText().isEmpty() || jtextFieldAddress.getText().isEmpty() ||
				jtextFieldPhone.getText().isEmpty() || jtextFieldDepartment.getText().isEmpty() ||
				jtextFieldUsername.getText().isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Pls enter full information of employee");
			}else {
				if(jtextFieldUsername.getText().length()<6) {
					JOptionPane.showMessageDialog(null, "Username must more than 6 characters ");
				}else {
					employee.setUsername(jtextFieldUsername.getText());	
				}
				employee.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
				employee.setName(jtextFieldName.getText());
				employee.setAddress(jtextFieldAddress.getText());
				if(jtextFieldPhone.getText().length()< 10){
					JOptionPane.showMessageDialog(null, "Phone number must bigger 10 number");
				}else {
					employee.setPhone(jtextFieldPhone.getText());
				}
				
				employee.setDepartment(jtextFieldDepartment.getText());
			}
			
			if (employeeModel.create(employee)) {
				JOptionPane.showMessageDialog(null, "Successfull");
				fillDatatoJTable(employeeModel.findAll());
			}else {
				JOptionPane.showMessageDialog(null, "Failed to add new employee");
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Please try again!");
		}
		
	}
	
	//fillDatatoJTable
	private void fillDatatoJTable(List<Employee> employees) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Employee_id");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Username");
		defaultTableModel.addColumn("Address");
		defaultTableModel.addColumn("Phone");
		defaultTableModel.addColumn("Department");
		for(Employee employee : employees) {
			defaultTableModel.addRow(new Object[] {
					employee.getEmployee_id(),
					employee.getName(),
					employee.getUsername(),
					employee.getAddress(),
					employee.getPhone(),
					employee.getDepartment()
			});
		}
		jTable.setModel(defaultTableModel);
		jTable.getTableHeader().setReorderingAllowed(false);
	}
	
	//MouseClick
	private void jTable_mouseClicked(MouseEvent e) {
		int selectedRow = jTable.getSelectedRow();
		int employee_id =Integer.parseInt(jTable.getValueAt(selectedRow, 0).toString()) ;
		EmployeeModel employeeModel = new EmployeeModel();
		Employee employee = employeeModel.find(employee_id);
		jtextFieldUsername.setText(employee.getUsername());
		jtextFieldName.setText(employee.getName());
		jtextFieldAddress.setText(employee.getAddress());
		jtextFieldPhone.setText(employee.getPhone());
		jtextFieldDepartment.setText(employee.getDepartment());
		
		jbtnAdd.setEnabled(true);
		jbtnDelete.setEnabled(true);
		jbtnUpdate.setEnabled(true);
	}
	
	//Load JInternalFrame
	private void loadJInternalFrame() {
		EmployeeModel employeeModel = new EmployeeModel();
		fillDatatoJTable(employeeModel.findAll());
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		defaultComboBoxModel.addElement("Name");
		defaultComboBoxModel.addElement("Phone");
		defaultComboBoxModel.addElement("Address");
		defaultComboBoxModel.addElement("Department");
		defaultComboBoxModel.addElement("Username");
		jcomboBoxSearchType.setModel(defaultComboBoxModel);
		jbtnDelete.setEnabled(false);
		jbtnUpdate.setEnabled(false);
	}
}
