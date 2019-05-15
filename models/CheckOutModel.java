package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.CheckOut;

public class CheckOutModel {
	public List<CheckOut> findAll(){
		List<CheckOut> checkOuts = new ArrayList<CheckOut>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from checkout");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				CheckOut checkOut = new CheckOut();
				checkOut.setCheckout_id(resultSet.getInt("checkout_id"));
				checkOut.setBorrow_date(resultSet.getDate("borrow_date"));
				checkOut.setEmployee_id(resultSet.getInt("employee_id"));
				checkOuts.add(checkOut);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			checkOuts = null;
		}
		return checkOuts;
	}
	public CheckOut find(int id) {
		CheckOut checkOut = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from checkout where checkout_id = ?");
			preparedStatement.setInt(0, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				checkOut = new CheckOut();
				checkOut.setCheckout_id(resultSet.getInt("checkout_id"));
				checkOut.setBorrow_date(resultSet.getDate("borrow_date"));
				checkOut.setEmployee_id(resultSet.getInt("employee_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			checkOut = null;
		}
		return checkOut;
	}
	public boolean create(CheckOut checkOut) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("insert into checkout(borrow_date, employee_id) value(?,?)");
			preparedStatement.setDate(0, new java.sql.Date(checkOut.getBorrow_date().getTime()));
			preparedStatement.setInt(1, checkOut.getEmployee_id());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			result = false;
		}
		return result;
	}
	public boolean update(CheckOut checkOut) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("update checkout set borrow_date = ?, employee_id = ? where checkout_id = ?");
			preparedStatement.setDate(0, new java.sql.Date(checkOut.getBorrow_date().getTime()));
			preparedStatement.setInt(1, checkOut.getEmployee_id());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
			System.err.println(e.getMessage());
		}
		return result;
	}
	public boolean delete(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("delete from checkout where checkout_id = ?");
			preparedStatement.setInt(0, id);
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
			System.err.println(e.getMessage());
		}
		return result;
	}
}
