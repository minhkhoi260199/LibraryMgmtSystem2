package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.CheckOut;
import entities.Detail;

public class DetailModel {
	public boolean create(Detail detail) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("insert into detail(checkout_id, user_id, callnumber, payment) values(?,?,?,?)");
			preparedStatement.setInt(1, detail.getCheckout_id());
			preparedStatement.setInt(2, detail.getUser_id());
			preparedStatement.setString(3, detail.getCallnumber());
			preparedStatement.setInt(4, detail.getPayment());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			result = false;
		}
		return result;
	}
	public long countDB(int checkout_id) {
		long count = 0;
		try {
			String que = "Select count(*) from detail where checkout_id = "+checkout_id+"";
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
	public List<Detail> loadData(long page, int checkout_id){
		List<Detail> details = new ArrayList<Detail>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("Select * from detail where checkout_id = "+checkout_id+" and status = 0 limit "+(page*20-20)+",20");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Detail detail = new Detail();
				detail.setDetail_id(resultSet.getInt("detail_id"));
				detail.setCheckout_id(resultSet.getInt("checkout_id"));
				detail.setUser_id(resultSet.getInt("user_id"));
				detail.setCallnumber(resultSet.getString("callnumber"));
				detail.setPayment(resultSet.getInt("payment"));
				detail.setStatus(resultSet.getInt("status"));
				details.add(detail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			details = null;
		}
		return details;
	}
	public boolean updateStt(int status_id, int detail_id) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("update detail set status = ? where detail_id = ?");	
			preparedStatement.setInt(1, status_id);
			preparedStatement.setInt(2, detail_id);
			result = preparedStatement.executeUpdate()>0;

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			result = false;
		}
		return result;
	}
	public Detail searchByUserCallnumber(int user_id, String callnumber) {
		Detail detail = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("Select * from detail where user_id = ? and callnumber = ? and status = 0");
			preparedStatement.setInt(1, user_id);
			preparedStatement.setString(2, callnumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				detail = new Detail();
				detail.setDetail_id(resultSet.getInt("detail_id"));
				detail.setCheckout_id(resultSet.getInt("checkout_id"));
				detail.setUser_id(resultSet.getInt("user_id"));
				detail.setCallnumber(resultSet.getString("callnumber"));
				detail.setPayment(resultSet.getInt("payment"));
				detail.setStatus(resultSet.getInt("status"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			detail = null;
		}
		return detail;
	}
	public Detail searchByDetailId(int detail_id) {
		Detail detail = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from detail where detail_id = ? and status = 0");
			preparedStatement.setInt(1, detail_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				detail = new Detail();
				detail.setDetail_id(resultSet.getInt("detail_id"));
				detail.setCheckout_id(resultSet.getInt("checkout_id"));
				detail.setUser_id(resultSet.getInt("user_id"));
				detail.setCallnumber(resultSet.getString("callnumber"));
				detail.setPayment(resultSet.getInt("payment"));
				detail.setStatus(resultSet.getInt("status"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			detail = null;
		}
		return detail;
	}
}
