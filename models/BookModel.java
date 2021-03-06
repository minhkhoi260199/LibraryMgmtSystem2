package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Book;

public class BookModel {
	public List<Book> findAll() {
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from book");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Book book = new Book();
				book.setIsbn(resultSet.getString("isbn"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setQuantity(resultSet.getInt("quantity"));
				books.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			books = null;
		}
		return books;
	}
	public boolean create(Book book) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("Insert into book(isbn, title, author_id, category_id, quantity, price) value(?,?,?,?,?,?)");
			preparedStatement.setString(1, book.getIsbn());
			preparedStatement.setString(2, book.getTitle());
			preparedStatement.setInt(3, book.getAuthor_id());
			preparedStatement.setInt(4, book.getCategory_id());
			preparedStatement.setInt(5, book.getQuantity());
			preparedStatement.setInt(6, book.getPrice());
			result = preparedStatement.executeUpdate() > 0 ;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			result = false;
		}
		return result;
	}
	public boolean delete(String isbn) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("delete from book where isbn = ?");
			preparedStatement.setString(1, isbn);
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
			System.err.println(e.getMessage());
		}
		return result;
	}
	public boolean update(Book book) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("update book set title = ?, author_id = ?, category_id = ?, quantity = ? where isbn = ?");
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setInt(2, book.getAuthor_id());
			preparedStatement.setInt(3, book.getCategory_id());
			preparedStatement.setInt(4, book.getQuantity());
			preparedStatement.setString(5, book.getIsbn());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
			System.err.println(e.getMessage());
		}
		return result;
	}
	public Book find(String isbn) {
		Book book = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from book where isbn = ?");
			preparedStatement.setString(1, isbn);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				book = new Book();
				book.setIsbn(resultSet.getString("isbn"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setPrice(resultSet.getInt("price"));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			book = null;
		}
		return book;
	}
	public List<Book> search(String type, String kw){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from book where "+type+" like ?");
			preparedStatement.setString(1, "%" + kw + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Book book = new Book();
				book.setIsbn(resultSet.getString("isbn"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setQuantity(resultSet.getInt("quantity"));
				books.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
			books = null;
			System.err.println(e.getMessage());
		}
		return books;
	}
}
