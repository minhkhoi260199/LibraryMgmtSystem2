package entities;

public class BookAuthor {
	private int author_id;
	private String isbn;
	public BookAuthor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookAuthor(int author_id, String isbn) {
		super();
		this.author_id = author_id;
		this.isbn = isbn;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
}
