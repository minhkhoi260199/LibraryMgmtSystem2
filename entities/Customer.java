package entities;

public class Customer {

	private int user_id;
	private String name;
	private String address;
	private String phone;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Customer(int user_id, String name, String address, String phone) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	public Customer() {
		super();
	}
	
}