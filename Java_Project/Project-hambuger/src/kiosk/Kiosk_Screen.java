package kiosk;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

// 키오스크 화면 출력만을 담당하는 클래스

public class Kiosk_Screen {

	Input_Scan scan;
	Jdbc_SalesDAO dao;
	Jdbc_OrderDAO dao_Order;
	Basket myBasket;
	VO_Orders orders;
	
	static boolean  fromMain;

	public Kiosk_Screen() {
		scan = new Input_Scan();
		dao = Jdbc_SalesDAO.getInstance();
		dao_Order = Jdbc_OrderDAO.getInstance();
		myBasket = new Basket();
		orders = new VO_Orders();
		

	}

	// methods

	// showMain();
	// 최초 화면 ( 신상품, 추천메뉴 , 광고를 출력)
	public void showMain() {
		System.out.println(SortPrinting.ROOF);
		SortPrinting.centerAlign("WECOME TO MC DONALDS");
		System.out.println(SortPrinting.ROW);
		for (int i = 0; i < 3; i++) {
			System.out.println(SortPrinting.SPACE);
		}
		SortPrinting.centerAlign("SELECT ORDER");
		System.out.println(SortPrinting.SPACE);

		SortPrinting.centerAlign("[1] General          [2] TheOld");
		for (int i = 0; i < 5; i++) {
			System.out.println(SortPrinting.SPACE);
		}
		System.out.println(SortPrinting.BOTTOM);

		int choice = scan.getChoice(2);
		switch (choice) {
		case 1:
			// step1 은 매장식사, 테이크아웃을 선택하는 메소드입니다
			orders.setAgeGroup("General");

			break;
		case 2:
			orders.setAgeGroup("TheOld");
			break;
		}

		takeoutOrEatHere();

	}

	// 매장에서 식사 / 테이크아웃
	private void takeoutOrEatHere() {

		System.out.println(SortPrinting.ROOF);
		SortPrinting.centerAlign("WECOME TO MC DONALDS");
		System.out.println(SortPrinting.ROW);
		for (int i = 0; i < 3; i++) {
			System.out.println(SortPrinting.SPACE);
		}
		SortPrinting.centerAlign("SELECT ORDER");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[1] EatHere          [2] TakeOut");
		for (int i = 0; i < 5; i++) {
			System.out.println(SortPrinting.SPACE);
		}
		System.out.println(SortPrinting.BOTTOM);

		int choice = scan.getChoice(2);
		switch (choice) {
		case 1:
			// eatHereOrNot 값 = "매장식사"
			orders.setEatHereOrNot("Y");
			break;

		case 2:
			orders.setEatHereOrNot("N");
			break;
		}
		if (orders.getAgeGroup().equalsIgnoreCase("TheOld")) {
			showRecoMenuForTheOld();
		} else {
			step2RecoMenuPrint();
		}
		// 다음 메뉴로 (= 추천메뉴선택, 버거보기, 음료보기, 사이드보기, 주문내역 출력, 주문취소,주문완료선택)
		// 추천메뉴는 1~9까지 선택하며, 버거보기는 , 음료보기는 . 사이드보기는 / 키를 입력해야 합니다.
		// 이는 Scan 클래스의 screenChoice2 가 담당합니다.
		// 이 screenChoice2 에 들어가는 매개변수는 정수 9 입니다.
		// screenChoice2 가 반환하는 정수는 "1~9", ",", ".", "/", "space키", "엔터키" 에 대한 아스키코드 값 -
		// 48 입니다.

		// 이 주석을 지우고 다음 메뉴에 해당하는 메소드를 호출하세요
	}

	// 버거 종류를 보여주는 메소드입니다.
	private void showBurgerType() {
		SortPrinting.printBanner("SELECT BURGER TYPE");
		SortPrinting.centerAlign("[1] Signature Burger");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[2] Beef Burger");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[3] Chicken/Shrimp Burger");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[4] Bulgogi/Etc Burger");
		System.out.println(SortPrinting.SPACE);
		System.out.println(SortPrinting.ROW);
		SortPrinting.leftAlign("[space] PREV");
		System.out.println(SortPrinting.BOTTOM);

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
			showSalesByType(s_Type);

		} else {

			step2RecoMenuPrint();
		}

	}

	// 추천메뉴를 상단에 보여주며, 버거, 음료, 사이드 메뉴 보기로 선택하여 분기할 수 있다.
	// 나의 장바구니 정보를 간략하게 출력하며
	// 상품을 모두 담았을시 결제 버튼을 눌러 최종 주문 확인 단계로 이동할 수 있다.
	private void step2RecoMenuPrint() {
		// 추천메뉴 출력
		Connection conn = null;
		List<VO_Sales> saleRecoBurgerList = null;
		////////////////////////////////////////////////////
		try {
			conn = Jdbc_ConPrivider.getConnection();
			saleRecoBurgerList = dao.recoHamburgerForAll(conn);
			SortPrinting.printBanner("R E C O M M E N D E D");
			SortPrinting.printInfoInRow(saleRecoBurgerList, 3);
			System.out.println(SortPrinting.SPACE);
			System.out.println(SortPrinting.ROW);
			System.out.println(SortPrinting.SPACE);

			// 주문내역 장바구니에서 가져오기
			//////////////////////////////////
			if (myBasket.isEmpty()) {
				SortPrinting.centerAlign("Basket is Empty");
			} else {
				// 주문내역 총 합계, 총 제품수
				int totalPrice = 0;
				int totalProduct = 0;

				for (int i = 0; i < myBasket.size(); i++) {

					totalPrice += myBasket.get(i).getPrice() * myBasket.get(i).getQuantity();
					totalProduct += myBasket.get(i).getQuantity();
				}
				String basketInfo = "Sum ".concat(String.valueOf(totalPrice).concat(" ┃ count ")
						.concat(String.valueOf(totalProduct).concat("ea"))); // Sum 총가격 count 총갯수 ea
				SortPrinting.leftAlign(basketInfo); // 를 왼쪽 정렬해서 출력
			}
			////////////////////////////////////

			System.out.println(SortPrinting.SPACE);
			System.out.println(SortPrinting.ROW);
			SortPrinting.centerAlign("[,] Burger  [.] Drink  [/] Side"); // 이 문자열을 중앙 정렬하여 출력
			System.out.println(SortPrinting.ROW);
			System.out.println(SortPrinting.SPACE);
			SortPrinting.centerAlign("[space] Cancel          [Enter] Pay"); // 이 문자열을 중앙 정렬하여 출력
			System.out.println(SortPrinting.BOTTOM);

			// (추천메뉴선택, 버거보기, 음료보기, 사이드보기, 주문내역 출력, 주문취소,주문완료선택)
			// 추천메뉴는 1~9까지 선택하며, 버거보기는 , 음료보기는 . 사이드보기는 / 키를 입력해야 합니다.
			// 입력은 Scan 클래스의 screenChoice2 메소드 가 담당합니다.
			// 이 screenChoice2 에 들어가는 매개변수는 추천메뉴의 사이즈 입니다.
			// screenChoice2 가 반환하는 정수는 "1~9", ",", ".", "/", "space키", "엔터키" 에 대한
			// 아스키코드 값 - 48 입니다.

		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
			e.printStackTrace();
		} finally {
			Jdbc_Closer.close(conn);
		}
		//////////////////////////////////////////////////////////////////

		// 선택
		int choice = scan.screenChoice2(saleRecoBurgerList.size()) - 1;

		// 키 입력시 코드실행
		// 추천메뉴(버거) -> 추천메뉴상세보기로 이동
		if (choice >= 0 && choice <= 9) {
			fromMain = true;
			// 추천메뉴(버거) 상세보기 메소드

			// 재고가 충분한지 확인
			// 충분하지 않다면 추천메
			try {
				conn = Jdbc_ConPrivider.getConnection();

				myBasket.add(saleRecoBurgerList.get(choice));
				setMenuChoice("Signature");
	//			showSalesBySelected((saleRecoBurgerList.get(choice).getProductName()),
		//				myBasket.get(myBasket.size() - 1).getQuantity());

			} catch (SQLException e) {
				System.err.println("일시적인 오류입니다.");
			} finally {
				Jdbc_Closer.close(conn);
			}
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
			showSalesByType("Side");
		}

		// 주문취소(space) -> 일반주문/간편주문 메소드
		else if (choice == -17) {
			myBasket.clear();
			showMain();
		}

		// 주문완료(enter) -> 주문내역확인 메소드
		else if (choice == -36) {
			if (myBasket.isEmpty()) {
				System.out.println("장바구니가 비었습니다. 제품을 추가하세요");
				step2RecoMenuPrint();
			} else {

				lastCheck(myBasket.list);
			}
		}

	}

	private void showRecoMenuForTheOld() {
		SortPrinting.printBanner("Recommended For Old");
		Connection conn = null;
		List<VO_Sales> list = new LinkedList<VO_Sales>();

		try {
			conn = Jdbc_ConPrivider.getConnection();
			list = dao.showRecoForTheOld(conn);
		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
		} finally {
			Jdbc_Closer.close(conn);
		}

		SortPrinting.printInfoInRow(list, 3);
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[space] Cancel");
		System.out.println(SortPrinting.BOTTOM);

		int choice = scan.screenChoice3(list.size()) - 1;

		if (choice != -17) {
			try {
				conn = Jdbc_ConPrivider.getConnection();

				myBasket.add(list.get(choice));
				setMenuChoice(null);

			} catch (SQLException e) {
				System.err.println("일시적인 오류입니다.");
			} finally {
				Jdbc_Closer.close(conn);
			}

		} else {
			orders.setAgeGroup(null);
			orders.setEatHereOrNot(null);
			showMain();
		}

	}

	// 단품, 세트, 라지세트 유무를 선택하는 메소드
	private void setMenuChoice(String type) {
		SortPrinting.printBanner("COMBO OR NOT ?");
		SortPrinting.centerAlign("[1] Burger Only");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[2] Combo");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[3] Large Combo");
		System.out.println(SortPrinting.SPACE);
		System.out.println(SortPrinting.ROW);
		SortPrinting.leftAlign("[space] PREV");
		System.out.println(SortPrinting.BOTTOM);

		int choice = scan.screenChoice3(3);
		if (choice != -16) {
			switch (choice) {
			case 1:
				// 추천메뉴로 돌아감
				if (orders.getAgeGroup().equalsIgnoreCase("TheOld")) {
					showDrinkForTheOld();
				} else {
					step2RecoMenuPrint();
				}

				break;
			case 2:
				// 세트 메뉴 선택창 출력
				if (orders.getAgeGroup().equalsIgnoreCase("TheOld")) {
					Connection conn = null;

					try {
						conn = Jdbc_ConPrivider.getConnection();
						myBasket.add(dao.sendSalesByName(conn, "FrenchFries(Regular)"));
						myBasket.add(dao.sendSalesByName(conn, "Coke"));

					} catch (SQLException e) {
						System.err.println("일시적인 오류입니다.");
					} finally {
						Jdbc_Closer.close(conn);
					}

					lastCheck(myBasket.list);
					// 세트메뉴 선택시 고령자일 경우 자동으로 상품을 장바구니에 넣고(감자튀김과 콜라)
					// 바로 최종주문으로 넘어간다.
				} else {

					setMenuCombination(type, choice);

				}
				// 일반 고객일 경우 세트메뉴 변경 창으로 이동
				break;
				
			case 3: 
				
				if (orders.getAgeGroup().equalsIgnoreCase("TheOld")) {
					Connection conn = null;

					try {
						conn = Jdbc_ConPrivider.getConnection();
						myBasket.add(dao.sendSalesByName(conn, "FrenchFries(Regular)"));
						myBasket.add(dao.sendSalesByName(conn, "Coke"));

					} catch (SQLException e) {
						System.err.println("일시적인 오류입니다.");
					} finally {
						Jdbc_Closer.close(conn);
					}

					lastCheck(myBasket.list);
			
				} else {

					setMenuCombination(type, choice);

				}
				
				break;	
			}
		} else {
			// 뒤로가기 누를시 선택했던 버거(myBasket.list 의 제일 마지막 인덱스) 를 삭제하고
			// ageType이 고령자일시 고령자를 위한 추천메뉴로 돌아가고
			// 일반고객일시 선택했던 해당 타입의 메뉴를 출력하는 메소드로 돌아감
			myBasket.delete(myBasket.get(myBasket.list.size() - 1));

			if (orders.getAgeGroup().equalsIgnoreCase("TheOld")) {
				showRecoMenuForTheOld();
			} else {
				if(fromMain ==true) {
				step2RecoMenuPrint();
				}else {
					showSalesByType(type);
				}
			}

		}
	}

	// 세트메뉴 선택시 호출되는 메소드, 매개변수로 choice를 받아 1일시 일반세트, 2일시 라지세트메뉴
	// 선택을 할 수 있도록 한다.
	private void setMenuCombination(String type, int choice) {

		SortPrinting.printBanner("CHOICE COMBINATION");
		System.out.println(SortPrinting.SPACE);
		
		switch(choice) {
		case 2:
			SortPrinting.centerAlign("[1] FrenchFries(M)");
			System.out.println(SortPrinting.SPACE);
			SortPrinting.centerAlign("[2] FrenchFries(M) + CheeseStick");
			break;
		case 3:
			SortPrinting.centerAlign("[1] FrenchFries(L)");
			System.out.println(SortPrinting.SPACE);
			SortPrinting.centerAlign("[2] FrenchFries(L) + CheeseStick");
			break;
		}
		
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[3] CheeseStick");
		System.out.println(SortPrinting.SPACE);
		System.out.println(SortPrinting.ROW);
		SortPrinting.leftAlign("[space] PREV");
		System.out.println(SortPrinting.BOTTOM);

		int comboChoice = scan.screenChoice3(3);

		Connection conn = null;

		try {
			conn = Jdbc_ConPrivider.getConnection();
			if (comboChoice != -16) {
				switch (comboChoice) {
				case 1:
					
					switch(choice) {
					case 2:
						myBasket.add(dao.sendSalesByName(conn, "FrenchFries(Regular)"));
						break;
					case 3:
						myBasket.add(dao.sendSalesByName(conn, "FrenchFries(Large)"));
						break;
						
					}
				
					break;
					
				case 2:
					
					switch(choice) {
					case 2:
						myBasket.add(dao.sendSalesByName(conn, "FrenchFries(Regular)"));
						myBasket.add(dao.sendSalesByName(conn, "CheeseStick"));
						break;
					case 3:
						myBasket.add(dao.sendSalesByName(conn, "FrenchFries(Large)"));
						myBasket.add(dao.sendSalesByName(conn, "CheeseStick"));
						break;
					}
					break;
					
				case 3:
					myBasket.add(dao.sendSalesByName(conn, "CheeseStick"));
					break;
				}
				// 추가시킨 후에 다시 전체 메뉴 호출한다.

				step2RecoMenuPrint();
			} else {
				// 스페이스를 눌렀으므로 단품/ 세트 고르는 메소드로 돌아간다.
				setMenuChoice(type);
			}

		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다");
		} finally {
			Jdbc_Closer.close(conn);
		}

	}

	// 음료 카테고리 선택창
	private void drinkChoice() {
		SortPrinting.printBanner("D R I N K");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[1] Coffee");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[2] ADE");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[3] Beverage");
		System.out.println(SortPrinting.SPACE);
		System.out.println(SortPrinting.ROW);
		SortPrinting.leftAlign("[space] PREV ");
		System.out.println(SortPrinting.BOTTOM);

		int choice = scan.screenChoice3(3);
		if (choice != -16) {
			// 해당 선택에 따라 커피, 에이드, 음료를 출력해주는 메소드 호출
			String drinkType = null;
			switch (choice) {
			case 1:
				drinkType = "Coffee";
				break;
			case 2:
				drinkType = "Ade";
				break;
			case 3:
				drinkType = "Beverage";
				break;
			}
			showSalesByType(drinkType);

		} else {
			// 이전 버튼을 눌렀으므로 별 테이블로 이동

			step2RecoMenuPrint();
		}
	}
	
	// 고령자들을 위한 음료메뉴 출력
	private void showDrinkForTheOld() {
		SortPrinting.printBanner("D R I N K");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[1] Coffee");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[2] Coke");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[3] OrangeJuice");
		System.out.println(SortPrinting.SPACE);
		System.out.println(SortPrinting.ROW);
		SortPrinting.leftAlign("[space] PREV ");
		System.out.println(SortPrinting.BOTTOM);

		int choice = scan.screenChoice3(3);
		Connection conn = null;
		try {
			conn = Jdbc_ConPrivider.getConnection();

			switch (choice) {
			case 1:
				myBasket.add(dao.sendSalesByName(conn, "Americano"));
			case 2:
				myBasket.add(dao.sendSalesByName(conn, "Coke"));
			case 3:
				myBasket.add(dao.sendSalesByName(conn, "OrangeJuice"));
			}
		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
		} finally {
			Jdbc_Closer.close(conn);
		}

		// 선택한 타입에 따라 default 음료를 담고 바로 최종 주문 확인으로
		lastCheck(myBasket.list);
	}

	// 타입에 따라 해당 타입 상품 출력
	private void showSalesByType(String type) {

		SortPrinting.printBanner(type);

		Connection conn = null;
		List<VO_Sales> list = new LinkedList<VO_Sales>();
		/////////////////////////////////////

		try {
			conn = Jdbc_ConPrivider.getConnection();

			list = dao.sendSalesByType(conn, type);

		} catch (SQLException e) {
			
			System.err.println("일시적인 오류입니다.");
		} finally {
			Jdbc_Closer.close(conn);

		}

		SortPrinting.printInfoInRow(list, 3);
		System.out.println(SortPrinting.ROW);
		SortPrinting.leftAlign("[space] PREV");
		System.out.println(SortPrinting.BOTTOM);

		int nextChoice = scan.screenChoice3(list.size()) - 1;
		if (nextChoice != -17) {

			myBasket.add(list.get(nextChoice));
			if (!(type.contains("Ade")||type.contains("Coffee")||type.contains("Beverage")||type.contains("Side") ) ) {
				setMenuChoice(type);
			} else {
				showSalesBySelected(list.get(nextChoice).getProductName(),
						myBasket.get(myBasket.list.size() - 1).getQuantity(), list.get(nextChoice).getType());
			}

		} else {
               if((type.contains("Ade")||type.contains("Coffee")||type.contains("Beverage"))){
            	   drinkChoice();
               }else if (type.contains("Side")) {
            	   step2RecoMenuPrint();
               }else {
            	   showBurgerType();
               }
			//step2RecoMenuPrint();

		}

	}

	// 사이드 메뉴 출력
//	private void sideShow() {
//		Connection conn = null;
//		List<VO_Sales> sideList = null;
//
//		try {
//			conn = Jdbc_ConPrivider.getConnection();
//			sideList = dao.sendSideMenu(conn);
//		} catch (SQLException e) {
//			System.err.println("일시적인 오류입니다.");
//		} finally {
//			Jdbc_Closer.close(conn);
//		}
//
//		SortPrinting.printBanner("S I D E");
//		SortPrinting.printInfoInRow(sideList, 3);
//		System.out.println(SortPrinting.ROW);
//		SortPrinting.leftAlign("[space] PREV ");
//		System.out.println(SortPrinting.BOTTOM);
//
//		int choice = scan.screenChoice3(sideList.size()) - 1;
//		if (choice != -17) {
//			// 추가 후 상품 - + 로 이동
//
//			myBasket.add(sideList.get(choice));
//
//			showSalesBySelected(sideList.get(choice).getProductName(),
//					myBasket.get(myBasket.size() - 1).getQuantity(), sideList.get(choice).getType() );
//
//		} else {
//
//			step2RecoMenuPrint();
//		}
//
//	}

	// 선택한 상품의 정보 출력
	private void showSalesBySelected(String s_name, int SaleQty, String type) {

		Connection conn = null;
		VO_Sales sales = null;

		try {
			conn = Jdbc_ConPrivider.getConnection();
			sales = dao.sendSalesByName(conn, s_name);
		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
		} finally {
			Jdbc_Closer.close(conn);
		}

		SortPrinting.printBanner(s_name);
		SortPrinting.centerAlign(sales.getProductName());
		SortPrinting.centerAlign(String.valueOf(sales.getPrice()).concat(" won"));
		SortPrinting.centerAlign(String.valueOf(sales.getCalorie()).concat("(kcal)"));
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("-  [".concat(String.valueOf(SaleQty).concat("]  +")));
		System.out.println(SortPrinting.ROW);
		SortPrinting.centerAlign("[space] Cancel          [Enter] Add This");
		System.out.println(SortPrinting.BOTTOM);

		int choice = scan.screenChoice4();

		switch (choice) {
		case -5:
			// 상품 추가
			SaleQty++;
			showSalesBySelected(s_name, SaleQty, type);
			break;
		case -3:

			if (SaleQty - 1 == 0) {
				System.err.println("1개 미만으로 주문하실 수 없습니다.");
			} else {
				SaleQty--;
			}

			showSalesBySelected(s_name, SaleQty, type);
			break;
		case -16:
			// 이전 메뉴
			// 담은 음료를 장바구니에서 뺌
			myBasket.delete(myBasket.get(myBasket.size()-1));
			// 뺴고 이전 메뉴로
			showSalesByType(type);

		case -35:
			// 다음 메뉴 (별)
			// 최종 howMany를 내 장바구니 상품의 주문 갯수에 저장

			myBasket.get(myBasket.size() - 1).setQuantity(SaleQty);
			step2RecoMenuPrint();

		}

	}

	// 주문 최종 확인
	private void lastCheck(List<VO_Sales> myBasketList) {
		int totalPrice = 0;

		int countAll = 0;
		for (VO_Sales mySale : myBasketList) {
			totalPrice += mySale.getPrice() * mySale.getQuantity();
			countAll += mySale.getQuantity();
		}

		String myOrderInfo = "SUM "
				.concat(String.valueOf(totalPrice).concat("    ").concat(String.valueOf(countAll).concat(" ea")));

		SortPrinting.printBanner("Please Check your Order");
		System.out.println(SortPrinting.ROW);
		SortPrinting.printMyBasketInRow(myBasket.list, 3);
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign(myOrderInfo);
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[-]    ".concat(String.valueOf(countAll).concat("    [+]")));
		System.out.println(SortPrinting.ROW);
		SortPrinting.centerAlign("[space] Cancel          [Enter] Pay");
		System.out.println(SortPrinting.BOTTOM);

		int choice = scan.screenChoice4();

		switch (choice) {
		case -5:

			for (VO_Sales sales : myBasketList) {
				sales.setQuantity(sales.getQuantity() + 1);
			}
			lastCheck(myBasketList);

		case -3:
			if (countAll - 1 == 0) {
				System.err.println("1개 미만으로 주문하실 수 없습니다.");
			} else {

				for (VO_Sales sales : myBasketList) {
					sales.setQuantity(sales.getQuantity() - 1);
				}
			}
			lastCheck(myBasketList);
		case -16:
			// 주문 내역의 모든 정보 제거

			System.out.println(SortPrinting.ROOF);
			System.out.println(SortPrinting.ROW);
			SortPrinting.centerAlign("Orders cannot be back");
			SortPrinting.centerAlign("Are you sure?");
			System.out.println(SortPrinting.ROW);
			SortPrinting.centerAlign("[space] NO              [Enter] YES");
			System.out.println(SortPrinting.BOTTOM);

			int lastchoice = scan.lastCheck();
			switch (lastchoice) {
			case -16:
				lastCheck(myBasketList);
			case -35:

				myBasket.clear();
				orders.setAgeGroup(null);
				orders.setEatHereOrNot(null);
				orders.setTableService(null);
				showMain();
			}

		case -35:
			// 최종 주문 확인시 장바구니에 들어간 모든 상품에 재료들 중 하나라도
			// 재고가 부족한 상품이 있으면 메인 화면으로 돌아감
			Connection conn = null;
			try {
				conn = Jdbc_ConPrivider.getConnection();
				boolean outOfStock = false;
				for (VO_Sales sale : myBasketList) {
					if (dao.outOfStock(conn, sale)) {
						outOfStock = true;
						break;
					}
				}
				if (outOfStock) {
					System.out.println("재고가 충분하지 않아 구매하실 수 없습니다. 메인화면으로돌아갑니다.");
					showMain();
				} else {
					for (VO_Sales sale : myBasketList) {
						// outOfStock 가 false가 나왔을 경우이므로
						// 장바구니에 있는 모든 제품에 대해 충분한 재고가 있는 경우이다.
						dao.recieveOrder(conn, sale);
					}

					tableService();

				}
			} catch (SQLException e) {
				System.err.println("일시적인 오류입니다.");
			} finally {
				Jdbc_Closer.close(conn);
			}

		}

	}

	// 테이블 서비스 유무 확인
	private void tableService() {
		SortPrinting.printBanner("Select Table Service");
		System.out.println(SortPrinting.ROW);
		SortPrinting.centerAlign("[1] Table Service");
		System.out.println(SortPrinting.SPACE);
		SortPrinting.centerAlign("[2] Self Service");
		System.out.println(SortPrinting.ROW);
		System.out.println(SortPrinting.BOTTOM);

		int choice = scan.getChoice(2);

		if (choice == 1) {
			orders.setTableService("Y");
		} else {
			orders.setTableService("N");
		}

		paying(0);
		// 주문을 접수받아 재고에서 이미 빠졌으므로 더 이상 뒤로가기를 사용할 수 없음

	}

	// 결제
	private void paying(int wrongCard) {
		System.out.println(SortPrinting.ROOF);
		SortPrinting.centerAlign("WECOME TO MC DONALDS");
		System.out.println(SortPrinting.ROW);
		for (int i = 0; i < 3; i++) {
			System.out.println(SortPrinting.SPACE);
		}
		SortPrinting.centerAlign("P A Y I N G");
		System.out.println(SortPrinting.SPACE);

		SortPrinting.centerAlign("Please Insert IC Card");
		for (int i = 0; i < 5; i++) {
			System.out.println(SortPrinting.SPACE);
		}
		System.out.println(SortPrinting.BOTTOM);

		// insert 를 누르면 결제를 완료합니다.

		while (true) {

			if ((scan.getInfo().equalsIgnoreCase("insert"))) {
				System.out.println(SortPrinting.ROOF);
				SortPrinting.centerAlign("Order Completed");
				System.out.println(SortPrinting.BOTTOM);

				Connection conn = null;

				try {
					conn = Jdbc_ConPrivider.getConnection();
					dao_Order.addOrderData(conn, myBasket.list, orders);
					myBasket.list.clear();

				} catch (SQLException e) {
					System.err.println("일시적인 오류입니다.");
					e.printStackTrace();
				} finally {
					Jdbc_Closer.close(conn);

				}

				// myBasket.list 에 있는 모든 상품 번호들의 갯수만큼 주문 객체 생성
				// 생성해서 HamOrder 테이블에 추가

				orders.setAgeGroup(null);
				orders.setEatHereOrNot(null);
				orders.setTableService(null);

				showMain();
			} else {
				if (wrongCard == 2) {
					System.out.println(SortPrinting.ROOF);
					SortPrinting.centerAlign("Check YOUR CARD INFO");
					System.out.println(SortPrinting.BOTTOM);
					break;
				}
				wrongCard++;
				System.out.println(SortPrinting.ROOF);
				System.out.println("Please Re - Insert Card");
				System.out.println(SortPrinting.BOTTOM);
			}
		}
		fromMain = false;
		showMain();
	}

	

}