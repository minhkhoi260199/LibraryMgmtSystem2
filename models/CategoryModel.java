package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Category;

public class CategoryModel {
	public Category find(int id) {
		Category category = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from categories where category_id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				category = new Category();
				category.setCategory_id(resultSet.getInt("category_id"));
				category.setType(resultSet.getString("type"));
			}
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
			category = null;
		}
		return category;
	}
	public Category findByType(String type) {
		Category category = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from categories where type = ?");
			preparedStatement.setString(1, type);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				category = new Category();
				category.setType(resultSet.getString("type"));
			}
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
			category = null;
		}
		return category;
	}
	public List<Category> findAll() {
		List<Category> categories = new ArrayList<Category>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from categories");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Category category = new Category();
				category.setCategory_id(resultSet.getInt("category_id"));
				category.setType(resultSet.getString("type"));
				categories.add(category);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			categories = null;
		}
		return categories;
	}	
	public boolean checkCategory(String type) {
		boolean check = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select type from categories");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String ktcategory = resultSet.getString(type);
				if(ktcategory == type) {
					check = false;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			check = false;
		}
		return check;
	}
	public boolean create(Category category) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("insert into categories(type) value(?)");
			preparedStatement.setString(1, category.getType());
			result= preparedStatement.executeUpdate() >0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			result = false;
		}
		return result;
	}
	public boolean delete(int category_id) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("delete from categories where category_id = ?");
			preparedStatement.setInt(1, category_id);
			result= preparedStatement.executeUpdate() >0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			result = false;
		}
		return result;
	}	
	public boolean update(Category category) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("update categories set type = ? where category_id= ?");
			preparedStatement.setString(1, category.getType());
			preparedStatement.setInt(2, category.getCategory_id());
			result = preparedStatement.executeUpdate()>0;
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			result = false;
		}
		return result;
	}
	
	
}
