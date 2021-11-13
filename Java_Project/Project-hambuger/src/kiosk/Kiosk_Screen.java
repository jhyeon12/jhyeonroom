package kiosk;

import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kiosk_Screen {

	Input_Scan scan;
	Jdbc_SalesDAO dao;
	Basket myBasket;
	String eatHereOrNot;
	int age;

	public Kiosk_Screen() {
		scan = new Input_Scan();
		dao = Jdbc_SalesDAO.getInstance();
		myBasket = new Basket();

	}

	// methods

	

	// showMain();
	// 최초 화면 ( 신상품, 추천메뉴 , 광고를 출력)
	public void showMain() {
		////////// 광고, 신상품, 추천메뉴 를 출력했습니다.
		System.out.println("\t주문하시려면 클릭하세요");
		System.out.println("1.일반주문 \t||\t2.간편주문");
		int choice = scan.getChoice(2);

		switch (choice) {
		case 1:
			// step1 은 매장식사, 테이크아웃을 선택하는 메소드입니다
			age = 1;

			break;
		case 2:
			age = 0;
			// 고령자를 위한 햄버거 메뉴 출력
			break;
		}
		takeoutOrEatHere();

	}

	public void takeoutOrEatHere() {
		System.out.println("=============================");
		System.out.println("1.매장에서 식사 \t||\t2.테이크 아웃");
		int choice = scan.getChoice(2);
		switch (choice) {
		case 1:
			// eatHereOrNot 값 = "매장식사"
			eatHereOrNot = "매장식사";
			break;

		case 2:
			eatHereOrNot = "테이크아웃";
			break;
		}
		step2RecoMenuPrint();
		// 다음 메뉴로 (= 추천메뉴선택, 버거보기, 음료보기, 사이드보기, 주문내역 출력, 주문취소,주문완료선택)
		// 추천메뉴는 1~9까지 선택하며, 버거보기는 , 음료보기는 . 사이드보기는 / 키를 입력해야 합니다.
		// 이는 Scan 클래스의 screenChoice2 가 담당합니다.
		// 이 screenChoice2 에 들어가는 매개변수는 정수 9 입니다.
		// screenChoice2 가 반환하는 정수는 "1~9", ",", ".", "/", "space키", "엔터키" 에 대한 아스키코드 값 -
		// 48 입니다.

		// 이 주석을 지우고 다음 메뉴에 해당하는 메소드를 호출하세요
	}

	// 버거 종류를 보여주는 메소드입니다.
	public void showBurgerType() {
		System.out.println("\t1. 시그니처 버거 \n\t2. 비프 버거\n\t3. 치킨/슈림프 버거\n\t4.불고기/기타 버거    space : 이전");
		int choice = scan.screenChoice3(4);
		String s_Type = null;
		if (choice != -16) {

			switch (choice) {
			case 1:
				s_Type = "Signature";
				break;
			case 2:
				s_Type = "Beef";
				break;
			case 3:
				s_Type = "Chicken/Shrimp";
				break;
			case 4:
				s_Type = "Bulgogi/Etc";
				break;
			}

			// s_Type 에 따라 해당 타입의 버거 출력하는 메소드 호출
			showBurgersByType(s_Type);

		} else {
			// 1~5 이외의 숫자는 space 뿐이므로 뒤로 가기
			step2RecoMenuPrint();
		}

	}

	// step2RecoMenuPrint() 효은 담당 메소드
	public void step2RecoMenuPrint() {
		// 추천메뉴 출력
		List<VO_Sales> saleRecoBurgerList = new LinkedList<>();
		
		saleRecoBurgerList = dao.recoHamburgerForAll();
		
		// 추천메뉴 보기
		System.out.println("===============\n**  추천메뉴  **");
		for (int i = 0; i < saleRecoBurgerList.size(); i++) {
			System.out.printf("[%d] %s ", i+1, saleRecoBurgerList.get(i).getProductName());
		}

		System.out.println("---------------");
		System.out.println("[,]: 버거 [.]: 음료, [/]: 사이드 ");
		System.out.println("---------------");
		//////////////////////////////////////////////////////////////////////////
		System.out.println("\n\n ====================================");

		// 주문내역 장바구니에서 가져오기
		if (myBasket.list.isEmpty()) {
			System.out.println("장바구니가 비어 있습니다.");

		} else {
			// 주문내역 총 합계, 총 제품수
			int totalPrice = 0;
			int totalProduct = 0;
		
			for (int i = 0; i < myBasket.list.size(); i++) {
				
				totalPrice += myBasket.list.get(i).getPrice() * myBasket.list.get(i).getQuantity();
				totalProduct += myBasket.list.get(i).getQuantity();
			}

			System.out.printf("합계: %d |제품: %d", totalPrice, totalProduct);
		}
		System.out.println("\n\n ====================================");
        //////////////////////////////////////////////////////////////////////////

		System.out.println("------------------------------------------");
		System.out.println("주문취소(space)\n주문완료(enter)");
		System.out.println("------------------------------------------");

		// (추천메뉴선택, 버거보기, 음료보기, 사이드보기, 주문내역 출력, 주문취소,주문완료선택)
		// 추천메뉴는 1~9까지 선택하며, 버거보기는 , 음료보기는 . 사이드보기는 / 키를 입력해야 합니다.
		// 이는 Scan 클래스의 screenChoice2 가 담당합니다.
		// 이 screenChoice2 에 들어가는 매개변수는 정수 9 입니다.
		// screenChoice2 가 반환하는 정수는 "1~9", ",", ".", "/", "space키", "엔터키" 에 대한
		// 아스키코드 값 - 48 입니다.

		int choice = scan.screenChoice2(9)-1;
		

		// 키 입력시 코드실행
		// 추천메뉴(버거) -> 추천메뉴상세보기로 이동
		if (choice >= 0 && choice <= 9) {
			// 추천메뉴(버거) 상세보기 메소드
			showBurgersByType(saleRecoBurgerList.get(choice).getProductName());
		}

		// 버거->버거상세보기. 음료->음료상세보기. 사이드 상세보기로 이동
		else if (choice == -5) {
			showBurgerType();
		}

		else if (choice == -3) {
			// 음료 상세보기 메소드
			drinkChoice();
		}

		else if (choice == -2) {
			// 사이드 상세보기 메소드
			sideShow();
		}

		// 주문취소(space) -> 일반주문/간편주문 메소드
		else if (choice == -17) {
			myBasket.list.clear();
			showMain();
		}

		// 주문완료(enter) -> 주문내역확인 메소드
		else if (choice == -36) {
			// 주문내역확인 메소드

			
			
			
		}

	}

	// 버거 타입을 저장한 문자열을 매개변수로 받아 해당 타입의 버거를 가져옴
	public void showBurgersByType(String type) {
		List<VO_Sales> list = dao.sendBurgerByType(type);
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%d. %s\n", i + 1, list.get(i).getProductName());
		}
		System.out.println(" space : 이전 ");
		int choice = scan.screenChoice3(list.size())-1;
		if (choice != -17) {
			myBasket.list.add(new VO_Sales(list.get(choice).getProductId(), 
					list.get(choice).getProductName(), list.get(choice).getPrice(),
					list.get(choice).getCalorie(), list.get(choice).getType(),1));
			setMenuChoice(type);
		} else {
			showBurgerType();
		}
	}

	// 단품, 세트, 라지세트 유무를 선택하는 메소드
	public void setMenuChoice(String type) {
		System.out.println("1. 단품\n\t2.세트       space : 이전");
		int choice = scan.screenChoice3(3);
		if (choice != -16) {
			switch (choice) {
			case 1:
				//  추천메뉴로 돌아감
				step2RecoMenuPrint();

				break;
			case 2:
				// 세트 메뉴 선택창 출력
				setMenuCombination(type);
				break;
			}
		} else {
			// space 를 눌렀다는 뜻이므로 이전메뉴로 돌아간다
			// 돌아갈때는 내 장바구니에 추가되어있는 선택 버거를 줄인다.
			myBasket.list.remove(myBasket.list.get(myBasket.list.size() - 1));
			showBurgersByType(type);

		}
	}
	
	// 주문내역 확인 메소드
	public void showOrderMenu() {
		System.out.println("\t주문내역\t");
		for(int i = 0; i >= myBasket.list.size(); i++) {
			System.out.printf(" %s %d %d ", i+1, myBasket.list.get(i).getProductName(), myBasket.list.get(i).getPrice(), myBasket.list.get(i).getQuantity());
		}
		
		
		
	}

	// 세트메뉴 선택시 호출되는 메소드, 매개변수로 choice를 받아 1일시 일반세트, 2일시 라지세트메뉴
	// 선택을 할 수 있도록 한다.
	public void setMenuCombination(String type) {

		System.out.println("\t1. 감자튀김M\n\t2.감자튀김M + 치즈스틱\n\t3.치즈스틱      space : 이전");
		int choice = scan.screenChoice3(3);
		

		if (choice != -16) {
			switch (choice) {
			case 1:
				// 내 바스켓에 감자튀김M 상품을 추가시킨다. DB에 상품명은 FrenchFriesM 이다.	
				myBasket.list.add(dao.sendSalesByName("FrenchFries(Regular)"));
				break;
			case 2:
				myBasket.list.add(dao.sendSalesByName("FrenchFries(Regular)"));
				myBasket.list.add(dao.sendSalesByName("CheeseStick"));
				break;
			case 3:
				myBasket.list.add(dao.sendSalesByName("CheeseStick"));
				break;
			}
			// 추가시킨 후에 다시 전체 메뉴 호출한다.
			
			
			step2RecoMenuPrint();
		} else {
			// 스페이스를 눌렀으므로 단품/ 세트 고르는 메소드로 돌아간다.
			setMenuChoice(type);

		}
	}

	// 음료 카테고리 선택창
	public void drinkChoice() {
		System.out.println("1. 커피\n\t2. 에이드\n\t3.음료      space : 이전");
		int choice = scan.screenChoice3(3);
		if (choice != -17) {
			// 해당 선택에 따라 커피, 에이드, 음료를 출력해주는 메소드 호출

		} else {
			// 이전 버튼을 눌렀으므로 별 테이블로 이동
			step2RecoMenuPrint();
		}
	}

	// int 를 매개변수로 받아 해당 선택에 따라 마실것 출력
	public void showDrinkByChoice(int choice) {
		Connection conn = null;
		try {
			conn = Jdbc_ConPrivider.getConnection();
			
			List<VO_Sales> list = dao.sendDrinkListByChoice(conn, choice);
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("[%d] %s %d %d cal", i + 1, list.get(i).getProductName(), list.get(i).getPrice(),
						list.get(i).getCalorie());
			}
			System.out.println("space : 이전");

			int nextChoice = scan.screenChoice3(list.size()) - 1;
			if (nextChoice != -17) {
				// 선택한 상품의 정보 출력
				myBasket.list.add(list.get(nextChoice));
				showSalesBySelected(list.get(nextChoice).getProductName());
			} else {
				drinkChoice();
			}
		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
		}finally {
			Jdbc_Closer.close(conn);
			
		}
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////	
	// 사이드 메뉴 출력
	public void sideShow() {
			List<VO_Sales> sideList = new LinkedList<>();
			sideList = dao.sendSideMenu();
			
			
			for(int i = 0 ; i < sideList.size(); i++) {
				System.out.printf("%d %s %d %d\n", i+1, sideList.get(i).getProductName(), sideList.get(i).getPrice(), sideList.get(i).getCalorie());
			}
			
		
			System.out.println("상품을 선택하세요");
			
			int choice = scan.screenChoice3(sideList.size())-1;
			if(choice != -17) {
			  // 추가 후 상품 - + 로 이동
			  
			  myBasket.list.add(sideList.get(choice));
			  showSalesBySelected(sideList.get(choice).getProductName());
			  
			}else {
				// 뒤로가기 >> 별 메소드
				step2RecoMenuPrint();
			}
				
			
			
			
		}

	// 선택한 상품의 정보 출력
	public void showSalesBySelected(String s_name) {

		VO_Sales sales = dao.sendSalesByName(s_name);

		System.out.printf("%s %d %d\n", sales.getProductName(), sales.getPrice(), sales.getCalorie());

		int howMany = 1;
		System.out.println("+  [" + howMany + "]" + "  -");
		// + 를 누르면 howMany 갯수 증가
		// - 를 누르면 howMany 갯수 감소
		// 미구현

		System.out.println("space : 취소 \tEnter : 확인");

		int choice = scan.screenChoice4();
		switch (choice) {
		case -5:
			// 상품 추가
			howMany++;
			showSalesBySelected(s_name);
			break;
		case -3:
			if (howMany - 1 == 0) {
				System.err.println("1개 미만으로 주문하실 수 없습니다.");
			} else {
				howMany--;
			}
			showSalesBySelected(s_name);
			break;
		case -16:
			// 이전 메뉴
			// 담은 음료를 장바구니에서 뺌
			myBasket.list.remove(myBasket.list.size() - 1);
			// 뺴고 이전 메뉴로
			drinkChoice();

		case -35:
			// 다음 메뉴 (별)
			// 최종 howMany를 내 장바구니 상품의 주문 갯수에 저장
			myBasket.list.get(myBasket.list.size()-1).setQuantity(howMany);
			step2RecoMenuPrint();

		}

	}
	
	
	// 남은 메소드  
	// 1. 주문내역 확인 (전체 + or -) , 주문취소, 주문완료
	// 2. 테이블/셀프 (카톡에서 가져오기)
	// 3. 결제(카톡에서 가져오기)
	// 4. 영수증
}
