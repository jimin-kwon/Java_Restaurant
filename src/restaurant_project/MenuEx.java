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
