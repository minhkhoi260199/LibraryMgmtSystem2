package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Author;

public class AuthorModel {
	
	public List<Author> findAll(){
		List<Author> authors = new ArrayList<Author>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from author");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Author author = new Author();
				author.setAuthor_id(resultSet.getInt("author_id"));
				author.setName(resultSet.getString("name"));
				authors.add(author);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			authors = null;
		}
		return authors;		
	}
	
	public Author find(int author_id) {
		Author author = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from author where author_id = ?");
			preparedStatement.setInt(1, author_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				author = new Author();
				author.setAuthor_id(resultSet.getInt("author_id"));
				author.setName(resultSet.getString("name"));
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			author = null;
		}
		return author;
	}
	
	public boolean create(Author author) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("insert into author(name) values (?)");
			preparedStatement.setString(1, author.getName());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public boolean update(Author author) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("update author set name = ? where author_id = ?");
			preparedStatement.setString(1, author.getName());
			preparedStatement.setInt(2, author.getAuthor_id());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public boolean delete(int author_id) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("delete from author where author_id = ?");
			preparedStatement.setInt(1, author_id);
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public List<Author> search(String kw) {
		List<Author> authors = new ArrayList<Author>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from author where name like ?");
			preparedStatement.setString(1, "%" + kw + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Author author = new Author();
				author.setName(resultSet.getString("name"));
				authors.add(author);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			authors = null;
		}
		return authors;
	}

}
