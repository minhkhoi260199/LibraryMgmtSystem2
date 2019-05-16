package models;

import java.sql.PreparedStatement;

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
}
