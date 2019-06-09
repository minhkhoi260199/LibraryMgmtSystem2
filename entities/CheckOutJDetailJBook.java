package entities;

import java.util.Date;

public class CheckOutJDetailJBook {
	private int detail_id;
	private int checkout_id;
	private int user_id;
	private String callnumber;
	private int out_of_date;
	private int payment;
	private int fee;
	private Date borrow_date;
	private Date return_date;
	private int borrow_employee_id;
	private int return_employee_id;
	private int status;
	private String title;
	
	
	
	public CheckOutJDetailJBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckOutJDetailJBook(int detail_id, int checkout_id, int user_id, String callnumber, int out_of_date,
			int payment, int fee, Date borrow_date, Date return_date, int borrow_employee_id, int return_employee_id,
			int status, String title) {
		super();
		this.detail_id = detail_id;
		this.checkout_id = checkout_id;
		this.user_id = user_id;
		this.callnumber = callnumber;
		this.out_of_date = out_of_date;
		this.payment = payment;
		this.fee = fee;
		this.borrow_date = borrow_date;
		this.return_date = return_date;
		this.borrow_employee_id = borrow_employee_id;
		this.return_employee_id = return_employee_id;
		this.status = status;
		this.title = title;
	}
	public int getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}
	public int getCheckout_id() {
		return checkout_id;
	}
	public void setCheckout_id(int checkout_id) {
		this.checkout_id = checkout_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getCallnumber() {
		return callnumber;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public int getOut_of_date() {
		return out_of_date;
	}
	public void setOut_of_date(int out_of_date) {
		this.out_of_date = out_of_date;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public Date getBorrow_date() {
		return borrow_date;
	}
	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public int getBorrow_employee_id() {
		return borrow_employee_id;
	}
	public void setBorrow_employee_id(int borrow_employee_id) {
		this.borrow_employee_id = borrow_employee_id;
	}
	public int getReturn_employee_id() {
		return return_employee_id;
	}
	public void setReturn_employee_id(int return_employee_id) {
		this.return_employee_id = return_employee_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
