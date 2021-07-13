package restaurant_project;

public class Menu {

	//field
	private int menu_id;
	private String mname;
	private String description;
	private int price;
	private int category_id;
	
	//奄沙持失切
	 public Menu () {}

	 
	//持失切
	public Menu(int menu_id, String mname, String description, int price, int category_id) {
		super();
		this.menu_id = menu_id;
		this.mname = mname;
		this.description = description;
		this.price = price;
		this.category_id = category_id;
	}


	//get, set
	public int getMenu_id() {
		return menu_id;
	}



	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}



	public String getName() {
		return mname;
	}



	public void setName(String mname) {
		this.mname = mname;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getCategory_id() {
		return category_id;
	}



	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}



	
	//toString
	@Override
	public String toString() {
		return "Menu [menu_id=" + menu_id + ", mname=" + mname + ", description=" + description + ", price=" + price
				+ ", category_id=" + category_id + "]";
	}
	
	
	
	
	 
	 
}
