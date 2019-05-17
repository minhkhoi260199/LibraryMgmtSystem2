package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import entities.Customer;

public class CustomerModel {

	public List<Customer> findAll(){
		List<Customer> customers = new ArrayList<Customer>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from customer");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setUser_id(resultSet.getInt("user_id"));
				customer.setName(resultSet.getString("name"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPhone(resultSet.getString("phone"));
				customers.add(customer);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			customers = null;
		}
		return customers;
	}
	public Customer find(int user_id) {
		Customer customer = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from customer where user_id = ?");
			preparedStatement.setInt(1, user_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				customer = new Customer();
				customer.setUser_id(resultSet.getInt("user_id"));
				customer.setName(resultSet.getString("name"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPhone(resultSet.getString("phone"));
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			customer = null;
		}
		return customer;
	}
	public boolean create(Customer customer) {
		boolean result  =true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("insert into customer(name, address, phone) values(?,?,?)");	
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getPhone());
			result = preparedStatement.executeUpdate()>0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = false;
		}
		return result;
	}	
	public boolean delete(int user_id) {
		boolean result  =true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("delete from customer where user_id = ?");	
			preparedStatement.setInt(1, user_id);
			result = preparedStatement.executeUpdate()>0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = false;
		}
		return result;
	}
	public boolean update(Customer customer) {
		boolean result  =true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("update customer set name=?, address = ? , phone= ? where user_id = ?");	
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getPhone());
			preparedStatement.setInt(4, customer.getUser_id());
			result = preparedStatement.executeUpdate()>0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = false;
		}
		return result;
	}
	public List<Customer> searchByName(String kw){
		List<Customer> customers = new ArrayList<Customer>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from customer where name like ?");
			preparedStatement.setString(1, "%" + kw + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setUser_id(resultSet.getInt("user_id"));
				customer.setName(resultSet.getString("name"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPhone(resultSet.getString("phone"));
				customers.add(customer);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			customers = null;
		}
		return customers;
		
	}
	public List<Customer> searchByPhone(String kw){
		List<Customer> customers = new ArrayList<Customer>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from customer where phone like ?");
			preparedStatement.setString(1, "%" + kw + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setUser_id(resultSet.getInt("user_id"));
				customer.setName(resultSet.getString("name"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPhone(resultSet.getString("phone"));
				customers.add(customer);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			customers = null;
		}
		return customers;
		
	}
	public List<Customer> searchByAddress(String kw){
		List<Customer> customers = new ArrayList<Customer>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from customer where address like ?");
			preparedStatement.setString(1, "%" + kw + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setUser_id(resultSet.getInt("user_id"));
				customer.setName(resultSet.getString("name"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPhone(resultSet.getString("phone"));
				customers.add(customer);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			customers = null;
		}
		return customers;
		
	}
}
