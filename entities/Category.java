package entities;

public class Category {
	private int category_id;
	private String type;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int category_id, String type) {
		super();
		this.category_id = category_id;
		this.type = type;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
