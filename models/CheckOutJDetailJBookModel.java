package models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.CheckOutJDetailJBook;
import entities.Detail;
public class CheckOutJDetailJBookModel {
	public List<CheckOutJDetailJBook> findAll(){
		List<CheckOutJDetailJBook> checkOutJDetailJBook = new ArrayList<CheckOutJDetailJBook>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT detail.detail_id, detail.checkout_id, detail.user_id, detail.callnumber, detail.out_of_date, detail.payment, detail.fee, detail.return_date, detail.employee_id as return_employee_id, book.title, checkout.borrow_date, checkout.employee_id as borrow_employee_id, detail.status FROM detail LEFT JOIN bookitem ON bookitem.callnumber = detail.callnumber LEFT JOIN book ON book.isbn = bookitem.isbn LEFT JOIN checkout ON checkout.checkout_id = detail.checkout_id order by detail.status ASC, detail.user_id ASC, checkout.borrow_date DESC");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				CheckOutJDetailJBook checkOutJDetailJBook1 = new CheckOutJDetailJBook();
				checkOutJDetailJBook1.setDetail_id(resultSet.getInt("detail_id"));
				checkOutJDetailJBook1.setCheckout_id(resultSet.getInt("checkout_id"));
				checkOutJDetailJBook1.setUser_id(resultSet.getInt("user_id"));
				checkOutJDetailJBook1.setCallnumber(resultSet.getString("callnumber"));
				checkOutJDetailJBook1.setOut_of_date(resultSet.getInt("out_of_date"));
				checkOutJDetailJBook1.setPayment(resultSet.getInt("payment"));
				checkOutJDetailJBook1.setFee(resultSet.getInt("fee"));
				checkOutJDetailJBook1.setReturn_date(resultSet.getDate("return_date"));
				checkOutJDetailJBook1.setReturn_employee_id(resultSet.getInt("return_employee_id"));
				checkOutJDetailJBook1.setTitle(resultSet.getString("title"));
				checkOutJDetailJBook1.setBorrow_date(resultSet.getDate("borrow_date"));
				checkOutJDetailJBook1.setBorrow_employee_id(resultSet.getInt("borrow_employee_id"));
				checkOutJDetailJBook1.setStatus(resultSet.getInt("status"));
				checkOutJDetailJBook.add(checkOutJDetailJBook1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			checkOutJDetailJBook = null;
		}
		return checkOutJDetailJBook;
	}
	public long countDBforDetailList(int condition, String type) {
		long count = 0;
		if(condition == 0 && type == "")
		{
			try {
				String que = "Select count(*) FROM detail LEFT JOIN bookitem ON bookitem.callnumber = detail.callnumber LEFT JOIN book ON book.isbn = bookitem.isbn LEFT JOIN checkout ON checkout.checkout_id = detail.checkout_id";
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
		}else {
			if(type == "borrowing employee") {
				type = "borrowed_employee_id";
				try {
					String que = "Select count(*) FROM detail LEFT JOIN bookitem ON bookitem.callnumber = detail.callnumber LEFT JOIN book ON book.isbn = bookitem.isbn LEFT JOIN checkout ON checkout.checkout_id = detail.checkout_id having "+type+" = "+condition+"";
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
			}else if(type == "returning employee") {
				type = "returned_employee_id";
				try {
					String que = "Select count(*) FROM detail LEFT JOIN bookitem ON bookitem.callnumber = detail.callnumber LEFT JOIN book ON book.isbn = bookitem.isbn LEFT JOIN checkout ON checkout.checkout_id = detail.checkout_id having "+type+" = "+condition+"";
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
			}else {
				type = "user_id";
				try {
					String que = "Select count(*) FROM detail LEFT JOIN bookitem ON bookitem.callnumber = detail.callnumber LEFT JOIN book ON book.isbn = bookitem.isbn LEFT JOIN checkout ON checkout.checkout_id = detail.checkout_id where "+type+" = "+condition+"";
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
			}
		}
		return count;
	}
	public List<CheckOutJDetailJBook> loadDataDetail(long page, int condition, String type){
		List<CheckOutJDetailJBook> checkOutJDetailJBook = new ArrayList<CheckOutJDetailJBook>();
		if(condition == 0 && type == "") {
			try {
				PreparedStatement preparedStatement = ConnectDB.getConnection()
						.prepareStatement("Select detail.detail_id, detail.checkout_id, detail.user_id, detail.callnumber, detail.out_of_date, detail.payment, detail.fee, detail.return_date, detail.employee_id as return_employee_id, book.title, checkout.borrow_date, checkout.employee_id as borrow_employee_id, detail.status FROM detail LEFT JOIN bookitem ON bookitem.callnumber = detail.callnumber "
								+ "LEFT JOIN book ON book.isbn = bookitem.isbn LEFT JOIN checkout ON checkout.checkout_id = detail.checkout_id "
								+ "order by detail.status ASC, detail.user_id ASC, checkout.borrow_date DESC limit "+(page*20-20)+",20");
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					CheckOutJDetailJBook checkOutJDetailJBook1 = new CheckOutJDetailJBook();
					checkOutJDetailJBook1.setDetail_id(resultSet.getInt("detail_id"));
					checkOutJDetailJBook1.setCheckout_id(resultSet.getInt("checkout_id"));
					checkOutJDetailJBook1.setUser_id(resultSet.getInt("user_id"));
					checkOutJDetailJBook1.setCallnumber(resultSet.getString("callnumber"));
					checkOutJDetailJBook1.setOut_of_date(resultSet.getInt("out_of_date"));
					checkOutJDetailJBook1.setPayment(resultSet.getInt("payment"));
					checkOutJDetailJBook1.setFee(resultSet.getInt("fee"));
					checkOutJDetailJBook1.setReturn_date(resultSet.getDate("return_date"));
					checkOutJDetailJBook1.setReturn_employee_id(resultSet.getInt("return_employee_id"));
					checkOutJDetailJBook1.setTitle(resultSet.getString("title"));
					checkOutJDetailJBook1.setBorrow_date(resultSet.getDate("borrow_date"));
					checkOutJDetailJBook1.setBorrow_employee_id(resultSet.getInt("borrow_employee_id"));
					checkOutJDetailJBook1.setStatus(resultSet.getInt("status"));
					checkOutJDetailJBook.add(checkOutJDetailJBook1);
				}
				preparedStatement.close();
				resultSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(e.getMessage());
				checkOutJDetailJBook = null;
			}
		}else {
			if(type == "borrowing employee") {
				type = "borrowed_employee_id";
				try {
					PreparedStatement preparedStatement = ConnectDB.getConnection()
							.prepareStatement("Select detail.detail_id, detail.checkout_id, detail.user_id, detail.callnumber, detail.out_of_date, detail.payment, detail.fee, detail.return_date, detail.employee_id as return_employee_id, book.title, checkout.borrow_date, checkout.employee_id as borrow_employee_id, detail.status FROM detail LEFT JOIN bookitem ON bookitem.callnumber = detail.callnumber "
									+ "LEFT JOIN book ON book.isbn = bookitem.isbn LEFT JOIN checkout ON checkout.checkout_id = detail.checkout_id "
									+ "having "+type+" = "+condition+" "
									+ "order by detail.status ASC, detail.user_id ASC, checkout.borrow_date DESC limit "+(page*20-20)+",20");
					ResultSet resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) {
						CheckOutJDetailJBook checkOutJDetailJBook1 = new CheckOutJDetailJBook();
						checkOutJDetailJBook1.setDetail_id(resultSet.getInt("detail_id"));
						checkOutJDetailJBook1.setCheckout_id(resultSet.getInt("checkout_id"));
						checkOutJDetailJBook1.setUser_id(resultSet.getInt("user_id"));
						checkOutJDetailJBook1.setCallnumber(resultSet.getString("callnumber"));
						checkOutJDetailJBook1.setOut_of_date(resultSet.getInt("out_of_date"));
						checkOutJDetailJBook1.setPayment(resultSet.getInt("payment"));
						checkOutJDetailJBook1.setFee(resultSet.getInt("fee"));
						checkOutJDetailJBook1.setReturn_date(resultSet.getDate("return_date"));
						checkOutJDetailJBook1.setReturn_employee_id(resultSet.getInt("return_employee_id"));
						checkOutJDetailJBook1.setTitle(resultSet.getString("title"));
						checkOutJDetailJBook1.setBorrow_date(resultSet.getDate("borrow_date"));
						checkOutJDetailJBook1.setBorrow_employee_id(resultSet.getInt("borrow_employee_id"));
						checkOutJDetailJBook1.setStatus(resultSet.getInt("status"));
						checkOutJDetailJBook.add(checkOutJDetailJBook1);
					}
					preparedStatement.close();
					resultSet.close();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
					checkOutJDetailJBook = null;
				}
			}else if(type == "returning employee"){
				type = "returned_employee_id";
				try {
					PreparedStatement preparedStatement = ConnectDB.getConnection()
							.prepareStatement("Select detail.detail_id, detail.checkout_id, detail.user_id, detail.callnumber, detail.out_of_date, detail.payment, detail.fee, detail.return_date, detail.employee_id as return_employee_id, book.title, checkout.borrow_date, checkout.employee_id as borrow_employee_id, detail.status FROM detail LEFT JOIN bookitem ON bookitem.callnumber = detail.callnumber "
									+ "LEFT JOIN book ON book.isbn = bookitem.isbn LEFT JOIN checkout ON checkout.checkout_id = detail.checkout_id "
									+ "having "+type+" = "+condition+" "
									+ "order by detail.status ASC, detail.user_id ASC, checkout.borrow_date DESC limit "+(page*20-20)+",20");
					ResultSet resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) {
						CheckOutJDetailJBook checkOutJDetailJBook1 = new CheckOutJDetailJBook();
						checkOutJDetailJBook1.setDetail_id(resultSet.getInt("detail_id"));
						checkOutJDetailJBook1.setCheckout_id(resultSet.getInt("checkout_id"));
						checkOutJDetailJBook1.setUser_id(resultSet.getInt("user_id"));
						checkOutJDetailJBook1.setCallnumber(resultSet.getString("callnumber"));
						checkOutJDetailJBook1.setOut_of_date(resultSet.getInt("out_of_date"));
						checkOutJDetailJBook1.setPayment(resultSet.getInt("payment"));
						checkOutJDetailJBook1.setFee(resultSet.getInt("fee"));
						checkOutJDetailJBook1.setReturn_date(resultSet.getDate("return_date"));
						checkOutJDetailJBook1.setReturn_employee_id(resultSet.getInt("return_employee_id"));
						checkOutJDetailJBook1.setTitle(resultSet.getString("title"));
						checkOutJDetailJBook1.setBorrow_date(resultSet.getDate("borrow_date"));
						checkOutJDetailJBook1.setBorrow_employee_id(resultSet.getInt("borrow_employee_id"));
						checkOutJDetailJBook1.setStatus(resultSet.getInt("status"));
						checkOutJDetailJBook.add(checkOutJDetailJBook1);
					}
					preparedStatement.close();
					resultSet.close();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
					checkOutJDetailJBook = null;
				}
			}else{
				type="user_id";
				try {
					PreparedStatement preparedStatement = ConnectDB.getConnection()
							.prepareStatement("Select detail.detail_id, detail.checkout_id, detail.user_id, detail.callnumber, detail.out_of_date, detail.payment, detail.fee, detail.return_date, detail.employee_id as return_employee_id, book.title, checkout.borrow_date, checkout.employee_id as borrow_employee_id, detail.status FROM detail LEFT JOIN bookitem ON bookitem.callnumber = detail.callnumber "
									+ "LEFT JOIN book ON book.isbn = bookitem.isbn LEFT JOIN checkout ON checkout.checkout_id = detail.checkout_id "
									+ "where "+type+" = "+condition+" "
									+ "order by detail.status ASC, detail.user_id ASC, checkout.borrow_date DESC limit "+(page*20-20)+",20");
					ResultSet resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) {
						CheckOutJDetailJBook checkOutJDetailJBook1 = new CheckOutJDetailJBook();
						checkOutJDetailJBook1.setDetail_id(resultSet.getInt("detail_id"));
						checkOutJDetailJBook1.setCheckout_id(resultSet.getInt("checkout_id"));
						checkOutJDetailJBook1.setUser_id(resultSet.getInt("user_id"));
						checkOutJDetailJBook1.setCallnumber(resultSet.getString("callnumber"));
						checkOutJDetailJBook1.setOut_of_date(resultSet.getInt("out_of_date"));
						checkOutJDetailJBook1.setPayment(resultSet.getInt("payment"));
						checkOutJDetailJBook1.setFee(resultSet.getInt("fee"));
						checkOutJDetailJBook1.setReturn_date(resultSet.getDate("return_date"));
						checkOutJDetailJBook1.setReturn_employee_id(resultSet.getInt("return_employee_id"));
						checkOutJDetailJBook1.setTitle(resultSet.getString("title"));
						checkOutJDetailJBook1.setBorrow_date(resultSet.getDate("borrow_date"));
						checkOutJDetailJBook1.setBorrow_employee_id(resultSet.getInt("borrow_employee_id"));
						checkOutJDetailJBook1.setStatus(resultSet.getInt("status"));
						checkOutJDetailJBook.add(checkOutJDetailJBook1);
					}
					preparedStatement.close();
					resultSet.close();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
					checkOutJDetailJBook = null;
				}
			}
		}
		return checkOutJDetailJBook;
	}
}
