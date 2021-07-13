package restaurant_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {

	// 인스턴스 생성
	private static MenuDaoImpl instance;

	private MenuDaoImpl() {
	}

	public static MenuDaoImpl getInstance() {
		if (instance == null) {
			instance = new MenuDaoImpl();
		}
		return instance;
	}

	// db연결
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// connection
	private boolean connect() {
		boolean result = false;

		String url = "jdbc:mysql://localhost:3306/restaurant?" + "characterEncoding=utf-8&serverTimezone=UTC"
				+ "&useSSL=false&allowPublicKeyRetrieval=true";
		String user = "root";
		String password = "admin";
		try {
			conn = DriverManager.getConnection(url, user, password);
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// DBMS 연결 객체 닫아줌
	private void close() {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//1. 메뉴 조회
	@Override
	public List<Menu> selectAll() {
		List<Menu> list = new ArrayList<Menu>();
		if (connect()) {
			String sql = "select * from menu";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Menu me = new Menu();
					me.setMenu_id(rs.getInt("menu_id"));
					me.setName(rs.getString("mname"));
					me.setDescription(rs.getString("description"));
					me.setPrice(rs.getInt("price"));
					me.setCategory_id(rs.getInt("category_id"));

					list.add(me);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		close();
		return list;
	}

//2. 메뉴 추가
	@Override
	public int insertMenu(Menu me) {
		int result = 0;
		if (connect()) {
			String sql = "insert into menu(menu_id, mname, description, price, category_id)" + "values(?, ?, ?, ?, ?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, me.getMenu_id());
				pstmt.setString(2, me.getName());
				pstmt.setString(3, me.getDescription());
				pstmt.setInt(4, me.getPrice());
				pstmt.setInt(5, me.getCategory_id());
				result = pstmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

//3. 카탈로그 조회
	@Override
	public List<Category> selectAllCategory() {
		List<Category> listCategory = new ArrayList<Category>();
		if (connect()) {
			String sql = "select * from category";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Category cg = new Category();
					cg.setCategory_id(rs.getInt("category_id"));
					cg.setName(rs.getString("cname"));

					listCategory.add(cg);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		close();
		return listCategory;
	}

//4.카탈로그 추가
	@Override
	public int insertCategory(Category cg) {
		int Category = 0;
		int result = 0;
		if (connect()) {
			String sql = "insert into category(category_id, cname)" + "values(?, ?)";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cg.getCategory_id());
				pstmt.setString(2, cg.getName());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return result;
	}


	
//5.메뉴 수정 
	@Override
	public int updateMenu(Menu me) {
		int result = 0;
		if(connect()) {
			String sql = "update menu set mname = ?, description = ?, price = ? where menu_id = ? and category_id = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, me.getName());
				pstmt.setString(2, me.getDescription());
				pstmt.setInt(3, me.getPrice());
				pstmt.setInt(4, me.getMenu_id());
				pstmt.setInt(5, me.getCategory_id());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		return result;
	}
//6.메뉴 삭제	
	@Override
	public int deleteMenu (int menu_id) {
		int result = 0;
		if(connect()) {
			String sql = "delete from menu where Menu_id = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, menu_id);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}

	
//7.이름으로 메뉴조회
	@Override
	public List<Menu> selectByName(String mname) {
		List<Menu> list = new ArrayList<Menu>();
		
		if (connect()) {
			String sql = "select * from menu where name like ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + mname + "%");
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Menu me = new Menu();
					me.setMenu_id(rs.getInt("menu_id"));
					me.setName(rs.getString("mname"));
					me.setDescription(rs.getString("description"));
					me.setPrice(rs.getInt("price"));
					me.setCategory_id(rs.getInt("category_id"));

					list.add(me);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		close();
		return list;
	}

	


	

}




