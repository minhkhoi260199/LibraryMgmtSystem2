package entities;

public class BookItem {
	private String callnumber;
	private String isbn;
	private int status;
	public BookItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookItem(String callnumber, String isbn, int status) {
		super();
		this.callnumber = callnumber;
		this.isbn = isbn;
		this.status = status;
	}
	public String getCallnumber() {
		return callnumber;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
