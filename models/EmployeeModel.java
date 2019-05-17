package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import entities.Employee;
import helper.BCrypt;
import ui.JInternalFrameEmployee;

public class EmployeeModel {

	public Employee find(int employee_id) {
		Employee employee = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from employee where employee_id = ?");
			preparedStatement.setInt(1, employee_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				employee = new Employee();
				employee.setEmployee_id(resultSet.getInt("employee_id"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setName(resultSet.getString("name"));
				employee.setAddress(resultSet.getString("address"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setDepartment(resultSet.getString("department"));
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			employee = null;
		}
		return employee;
	}
	
	// show on table
	public List<Employee> findAll(){
		List<Employee> employees = new ArrayList<Employee>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from employee");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Employee employee = new Employee();
				employee = new Employee();
				employee.setEmployee_id(resultSet.getInt("employee_id"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setName(resultSet.getString("name"));
				employee.setAddress(resultSet.getString("address"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setDepartment(resultSet.getString("department"));
				employees.add(employee);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			employees = null;
		}
		return employees;
	}
	
	//Create account employee
	public boolean create(Employee employee) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("insert into employee(username, password, name, address, phone, department) values(?,?,?,?,?,?)");
			preparedStatement.setString(1, employee.getUsername());
			preparedStatement.setString(2, employee.getPassword());
			preparedStatement.setString(3, employee.getName());
			preparedStatement.setString(4, employee.getAddress());
			preparedStatement.setString(5, employee.getPhone());
			preparedStatement.setString(6, employee.getDepartment());
			result = preparedStatement.executeUpdate()>0;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			result = false;
		}
		return result;
	}
	
	//Delete account employee
	public boolean delete(int employee_id) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("delete from employee where employee_id = ?");
			preparedStatement.setInt(1, employee_id);
			result = preparedStatement.executeUpdate()>0;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			result = false;
		}
		return result;
	}
	
	//Update employee info
	public boolean update(Employee employee) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("update employee set username=?, password=?, name=?, address=?, phone=?, department=? where employee_id = ?");
			preparedStatement.setString(1, employee.getUsername());
			preparedStatement.setString(2, employee.getPassword());
			preparedStatement.setString(3, employee.getName());
			preparedStatement.setString(4, employee.getAddress());
			preparedStatement.setString(5, employee.getPhone());
			preparedStatement.setString(6, employee.getDepartment());
			preparedStatement.setInt(7, employee.getEmployee_id());
			result = preparedStatement.executeUpdate()>0;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			result = false;
		}
		return result;
	}
	
	//Search by name employee info
	public List<Employee> searchByName(String kw){
		List<Employee> employees = new ArrayList<Employee>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from employee where name like ?");
			preparedStatement.setString(1, "%" + kw + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Employee employee = new Employee();
				employee = new Employee();
				employee.setEmployee_id(resultSet.getInt("employee_id"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setName(resultSet.getString("name"));
				employee.setAddress(resultSet.getString("address"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setDepartment(resultSet.getString("department"));
				employees.add(employee);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			employees = null;
		}
		return employees;
	}
	
	//Search by phone employee info
		public List<Employee> searchByPhone(String kw){
			List<Employee> employees = new ArrayList<Employee>();
			try {
				PreparedStatement preparedStatement = ConnectDB.getConnection()
						.prepareStatement("select * from employee where phone like ?");
				preparedStatement.setString(1, "%" + kw + "%");
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					Employee employee = new Employee();
					employee = new Employee();
					employee.setEmployee_id(resultSet.getInt("employee_id"));
					employee.setUsername(resultSet.getString("username"));
					employee.setPassword(resultSet.getString("password"));
					employee.setName(resultSet.getString("name"));
					employee.setAddress(resultSet.getString("address"));
					employee.setPhone(resultSet.getString("phone"));
					employee.setDepartment(resultSet.getString("department"));
					employees.add(employee);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				employees = null;
			}
			return employees;
		}
		
		//Search by address employee info
		public List<Employee> searchByAddress(String kw){
			List<Employee> employees = new ArrayList<Employee>();
			try {
				
				PreparedStatement preparedStatement = ConnectDB.getConnection()
						.prepareStatement("select * from employee where address like ?");
				preparedStatement.setString(1, "%" + kw + "%");
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					Employee employee = new Employee();
					employee = new Employee();
					employee.setEmployee_id(resultSet.getInt("employee_id"));
					employee.setUsername(resultSet.getString("username"));
					employee.setPassword(resultSet.getString("password"));
					employee.setName(resultSet.getString("name"));
					employee.setAddress(resultSet.getString("address"));
					employee.setPhone(resultSet.getString("phone"));
					employee.setDepartment(resultSet.getString("department"));
					employees.add(employee);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				employees = null;
			}
			return employees;
		}
		public List<Employee> searchByDepartment(String kw){
			List<Employee> employees = new ArrayList<Employee>();
			try {
				
				PreparedStatement preparedStatement = ConnectDB.getConnection()
						.prepareStatement("select * from employee where department like ?");
				preparedStatement.setString(1, "%" + kw + "%");
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					Employee employee = new Employee();
					employee = new Employee();
					employee.setEmployee_id(resultSet.getInt("employee_id"));
					employee.setUsername(resultSet.getString("username"));
					employee.setPassword(resultSet.getString("password"));
					employee.setName(resultSet.getString("name"));
					employee.setAddress(resultSet.getString("address"));
					employee.setPhone(resultSet.getString("phone"));
					employee.setDepartment(resultSet.getString("department"));
					employees.add(employee);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				employees = null;
			}
			return employees;
		}
		public List<Employee> searchByUserName(String kw){
			List<Employee> employees = new ArrayList<Employee>();
			try {
				
				PreparedStatement preparedStatement = ConnectDB.getConnection()
						.prepareStatement("select * from employee where username like ?");
				preparedStatement.setString(1, "%" + kw + "%");
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					Employee employee = new Employee();
					employee = new Employee();
					employee.setEmployee_id(resultSet.getInt("employee_id"));
					employee.setUsername(resultSet.getString("username"));
					employee.setPassword(resultSet.getString("password"));
					employee.setName(resultSet.getString("name"));
					employee.setAddress(resultSet.getString("address"));
					employee.setPhone(resultSet.getString("phone"));
					employee.setDepartment(resultSet.getString("department"));
					employees.add(employee);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				employees = null;
			}
			return employees;
		}
	// login
	public Employee find(String username){
		Employee employee = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from employee where username = ?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				employee = new Employee();
				employee.setEmployee_id(resultSet.getInt("employee_id"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setName(resultSet.getString("name"));
				employee.setAddress(resultSet.getString("address"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setDepartment(resultSet.getString("department"));
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			employee = null;
		}
		return employee;
	}
	
	public Employee login(String username, String password) {
		Employee employee = this.find(username);
		if(employee != null) {
			if(BCrypt.checkpw(password, employee.getPassword())) {
				return employee;
			}
		}
		return null;
	}
	
}
