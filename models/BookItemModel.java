package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.BookItem;

public class BookItemModel {
	public BookItem find(String isbn) {
		BookItem bookItem = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from bookitem where isbn = ? and status = 0");
			preparedStatement.setString(1, isbn);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				bookItem = new BookItem();
				bookItem.setIsbn(resultSet.getString("isbn"));
				bookItem.setCallnumber(resultSet.getString("callnumber"));
				bookItem.setStatus(resultSet.getInt("status"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			bookItem = null;
		}
		return bookItem;
	}
	public BookItem findCallnumber(String callnumber) {
		BookItem bookItem = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from bookitem where callnumber = ? and status = 0");
			preparedStatement.setString(1, callnumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				bookItem = new BookItem();
				bookItem.setIsbn(resultSet.getString("isbn"));
				bookItem.setCallnumber(resultSet.getString("callnumber"));
				bookItem.setStatus(resultSet.getInt("status"));
			}else {
				bookItem = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			bookItem = null;
		}
		return bookItem;
	}
	public boolean updateStatus(String callnumber) {
		boolean result  =true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("update bookitem set status=? where callnumber = ?");	
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, callnumber);
			result = preparedStatement.executeUpdate()>0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = false;
		}
		return true;
	}
}
