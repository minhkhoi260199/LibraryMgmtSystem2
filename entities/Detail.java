package entities;

import java.util.Date;

public class Detail {
	private int detail_id;
	private int checkout_id;
	private int user_id;
	private String callnumber;
	private int out_of_date;
	private int payment;
	private int fee;
	private Date return_date;
	private int employee_id;
	private int status;
	
	
	
	public Detail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Detail(int detail_id, int checkout_id, int user_id, String callnumber, int out_of_date, int payment, int fee,
			Date return_date, int employee_id, int status) {
		super();
		this.detail_id = detail_id;
		this.checkout_id = checkout_id;
		this.user_id = user_id;
		this.callnumber = callnumber;
		this.out_of_date = out_of_date;
		this.payment = payment;
		this.fee = fee;
		this.return_date = return_date;
		this.employee_id = employee_id;
		this.status = status;
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
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
}
