package restaurant_project;

public class Category {

	//field
	private int category_id;
	private String cname;
	
	//奄沙 持失切
	public Category() {}

	//持失切
	public Category(int category_id, String cname) {
		super();
		this.category_id = category_id;
		this.cname = cname;
	}

	//get & set
	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public String getName() {
		return cname;
	}


	public void setName(String cname) {
		this.cname = cname;
	}

	//toString
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", cname=" + cname + "]";
	}
	
	
	
	
	
	
}
