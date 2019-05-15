package entities;

import java.util.Date;

public class CheckOut {
	private int checkout_id;
	private Date borrow_date;
	private int employee_id;
	public CheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckOut(int checkout_id, Date borrow_date, int employee_id) {
		super();
		this.checkout_id = checkout_id;
		this.borrow_date = borrow_date;
		this.employee_id = employee_id;
	}
	public int getCheckout_id() {
		return checkout_id;
	}
	public void setCheckout_id(int checkout_id) {
		this.checkout_id = checkout_id;
	}
	public Date getBorrow_date() {
		return borrow_date;
	}
	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
	
}
