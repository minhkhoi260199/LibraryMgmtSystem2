package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			preparedStatement.setInt(1, id);
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
					.prepareStatement("insert into checkout(borrow_date, employee_id) values(?,?)");
			preparedStatement.setDate(1, new java.sql.Date(checkOut.getBorrow_date().getTime()));
			preparedStatement.setInt(2, checkOut.getEmployee_id());
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
			preparedStatement.setDate(1, new java.sql.Date(checkOut.getBorrow_date().getTime()));
			preparedStatement.setInt(2, checkOut.getEmployee_id());
			preparedStatement.setInt(3, checkOut.getCheckout_id());
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
	public int createGetId(CheckOut checkOut) {
		// for insert a new candidate
		ResultSet rs = null;
		int id = 0;
		
		String sql = "insert into checkout(borrow_date, employee_id) values(?,?)";
		
		try (Connection conn = ConnectDB.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
		
		// set parameters for statement
		pstmt.setDate(1, new java.sql.Date(checkOut.getBorrow_date().getTime()));
		pstmt.setInt(2, checkOut.getEmployee_id());
		
		int rowAffected = pstmt.executeUpdate();
			if(rowAffected == 1)
			{
			// get candidate id
				rs = pstmt.getGeneratedKeys();
				if(rs.next())
				id = rs.getInt(1);
			
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		try {
			if(rs != null)  rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return id;
	}
	public long countDB() {
		long count = 0;
		try {
			String que = "Select count(*) from checkout";
			Connection cn = ConnectDB.getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(que);
			while(rs.next()) {
				count = rs.getLong(1);
			}
			rs.close();
			st.close();
			cn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			count = 0;
		}
		return count;
	}
	public List<CheckOut> loadData(long page){
		List<CheckOut> checkOuts = new ArrayList<CheckOut>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("Select * from checkout limit "+(page*20-20)+",20 ");
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
}
