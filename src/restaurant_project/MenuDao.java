package restaurant_project;

import java.util.List;

public interface MenuDao {

//menu
	public List<Menu> selectAll();
	public int insertMenu(Menu me);
	public int updateMenu(Menu me);
	public int deleteMenu(int menu_id);
	public List<Menu> selectByName(String name);
	
	
	
//Category
	public List<Category> selectAllCategory();
	public int insertCategory(Category cg);
	
	
	
	




}
