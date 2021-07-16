#branch master에 파일이 있습니다 :)

# Java_Restaurant
- java 간단한 식당 주문 프로그램을 만들었습니다.
- 7가지의 기능을 구현 해보았습니다. 
****************************************************************************************************************
 1. 메뉴조회   2.메뉴 추가   3.카테고리 조회   4.카테고리 추가   5.메뉴 수정   6.메뉴삭제   7.이름으로 메뉴 조회 
****************************************************************************************************************


#Menu_ex
package restaurant_project;

import java.util.List;
import java.util.Scanner;
import restaurant_project.Menu;

public class MenuEx {

//	1. 메뉴 조회 - selectAllMenu()
//	2. 메뉴 추가 - insertMenu()
//	3. 카테고리 조회 - selectAllCategory
//	4. 카테고리 추가 - insertCategory
//	5. 메뉴 수정 - updateMenu()
//	6. 메뉴 삭제 - deleteMenu()
//	7. 이름으로 메뉴 조회 - selectMenuByName()
	
	private static MenuDao dao = MenuDaoImpl.getInstance();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {
			System.out.println(
					"---------------------------------------------------------------------------------------------------");
			System.out.println(
					"1. 메뉴 조회  | 2. 메뉴 추가  | 3. 카테고리 조회  | 4. 카테고리 추가  | 5. 메뉴 수정  |  6. 메뉴 삭제  | 7. 이름으로 메뉴 조회");
			System.out.println(
					"----------------------------------------------------------------------------------------------------");

			int select = Integer.parseInt(sc.nextLine());

			switch (select) {

////// 1.메뉴 조회			
			case 1:
				List<Menu> list = dao.selectAll();
				for (Menu m : list) {
					System.out.println(m);
				}
				break;

	
//// 2.메뉴 등록				
			case 2:
				System.out.println("등록할 메뉴 id 등록 하세요 ");
				int menu_id = Integer.parseInt(sc.nextLine());
				System.out.println("등록할 메뉴 이름을 등록하세요");
				String mname = sc.nextLine();
				System.out.println("등록할 메뉴 설명 등록하세요");
				String description = sc.nextLine();
				System.out.println("등록할 메뉴 가격 등록 하세요 ");
				int price = Integer.parseInt(sc.nextLine());
				System.out.println("등록할 메뉴 카테로그 번호 등록하세요 ");
				int category_id = Integer.parseInt(sc.nextLine());
	
				Menu me = new Menu(menu_id, mname, description, price, category_id);
				int result = dao.insertMenu(me);
				if (result > 0) {
					System.out.println("등록 완료");
					listAllMenu();
				} else {
					System.out.println("등록 실패");
				}
				break;
												
//// 3.카탈로그 조회			
			case 3:
				List<Category> listCategory = dao.selectAllCategory();
				for (Category c : listCategory) {
					System.out.println(c);
				}
				
				break;

////// 4.카탈로그 추가			 
			case 4:
				System.out.println("카테고리 번호 입력 하세요.");
				category_id = Integer.parseInt(sc.nextLine());
				System.out.println("등록 할 카테고리 입력하세요.");
				String cname = sc.nextLine();

				Category cg = new Category(category_id, cname);
				result = dao.insertCategory(cg);
				if (result > 0) {
					System.out.println("카탈로그 등록");
				} else {
					System.out.println("카탈로그 실패");
				}
				System.out.println(result);

				break;

////// 5.메뉴 수정	
			case 5:
				System.out.println("수정할 메뉴 id 등록 하세요 ");
				menu_id = Integer.parseInt(sc.nextLine());
				System.out.println("수정할 메뉴 이름을 등록하세요");
				mname = sc.nextLine();
				System.out.println("수정할 메뉴 설명 등록하세요");
				description = sc.nextLine();
				System.out.println("수정할 메뉴 가격 등록 하세요 ");
				price = Integer.parseInt(sc.nextLine());
				System.out.println("수정할 메뉴 카테로그 id 등록하세요 ");
				category_id = Integer.parseInt(sc.nextLine());

				Menu modify_me = new Menu(menu_id, mname, description, price, category_id);

				result = dao.updateMenu(modify_me);
				if (result > 0) {
					System.out.println("수정 완료");
					listAllMenu();
				} else {
					System.out.println("수정 실패");
				}
				break;

////// 6.메뉴 삭제			 
			case 6:
				System.out.println("메뉴 id를 입력하세요 >>>");
				menu_id = Integer.parseInt(sc.nextLine());

				result = dao.deleteMenu(menu_id);

				if (result > 0) {
					System.out.println("삭제 성공");
					listAllMenu();
				} else {
					System.out.println("삭제 실패");
				}
				break;

////// 7.이름으로 메뉴조회			
			case 7:
				System.out.println("조회할 메뉴명을 입력하세요 >>>");
				@SuppressWarnings("unused") String Menu_name = sc.nextLine();
				List<Menu> list1 = dao.selectAll();
				for (Menu m : list1) {
					System.out.println(m);
				}

			}
		}

	}

	private static void listAllMenu() {
		// TODO Auto-generated method stub

	}

}

	
	
####MenuDaoImPl
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





