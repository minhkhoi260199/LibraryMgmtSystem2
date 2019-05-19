package models;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import entities.BookItem;

public class BookItemModel {
	
	public boolean deleteBookitemByISBN(String isbn) {
		boolean result = false;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("delete from bookitem where isbn = ?");
			preparedStatement.setString(1, isbn);
			result = preparedStatement.executeUpdate() >0 ;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return result;
	}
	public List<BookItem> findAll(){
		List<BookItem> bookItems = new ArrayList<BookItem>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from bookitem");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				BookItem bookItem = new BookItem();
				bookItem.setIsbn(resultSet.getString("isbn"));
				bookItem.setCallnumber(resultSet.getString("callnumber"));
				bookItem.setStatus(resultSet.getInt("status"));
				bookItems.add(bookItem);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return bookItems;
	}
	public boolean createBookItem(BookItem bookItem) {
		boolean result = false;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("Insert into bookitem(callnumber,isbn,status) value(?,?,?)");
			preparedStatement.setString(1, bookItem.getCallnumber());
			preparedStatement.setString(2, bookItem.getIsbn());
			preparedStatement.setInt(3, bookItem.getStatus());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			result = false;
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return result;
	}
	public List<BookItem> findAllbyISBN(String isbn){
		List<BookItem> bookItems = new ArrayList<BookItem>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from bookitem where isbn = ?");
			preparedStatement.setString(1, isbn);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				BookItem bookItem = new BookItem();
				bookItem.setIsbn(resultSet.getString("isbn"));
				bookItem.setCallnumber(resultSet.getString("callnumber"));
				bookItem.setStatus(resultSet.getInt("status"));
				bookItems.add(bookItem);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return bookItems;
	}
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
	public List<BookItem> Search(String type, String kw){
		List<BookItem> bookItems = new ArrayList<BookItem>();
		try {
				PreparedStatement preparedStatement = ConnectDB.getConnection()
						.prepareStatement("select * from bookitem where "+type+" like ?");
				preparedStatement.setString(1, "%" + kw + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				BookItem bookItem = new BookItem();
				bookItem.setIsbn(resultSet.getString("isbn"));
				bookItem.setCallnumber(resultSet.getString("callnumber"));
				bookItem.setStatus(resultSet.getInt("status"));
				bookItems.add(bookItem);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return bookItems;
	}
	public boolean updateStatusToOff(String callnumber) {
		boolean result  = true;
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
	public boolean updateStatusToOn(String callnumber) {
		boolean result  = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("update bookitem set status=? where callnumber = ?");	
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, callnumber);
			result = preparedStatement.executeUpdate()>0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = false;
		}
		return true;
	}
}
