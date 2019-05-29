package entities;

public class Book {
	private String isbn;
	private String title;
	private int author_id;
	private int category_id;
	private int quantity;
	private int price;
	
	public Book() {
	}
	public Book(String isbn, String title, int author_id, int category_id, int quantity, int price) {
		this.isbn = isbn;
		this.title = title;
		this.author_id = author_id;
		this.category_id = category_id;
		this.quantity = quantity;
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
