package kiosk;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main_StoreOwner {
	// 점주의 포스기를 간단하게 구현
	// 아직 진행중

	
	
	
	public static void main(String[] args) {
		
		
		Input_Scan scan = new Input_Scan();
		
		while(true) {
			System.out.println(SortPrinting.ROOF);
			System.out.println(SortPrinting.SPACE);
			SortPrinting.centerAlign("Store Owner Management");
			System.out.println(SortPrinting.SPACE);
			System.out.println(SortPrinting.ROW);
			System.out.println(SortPrinting.SPACE);
			SortPrinting.centerAlign("[1] Settlement");	
			System.out.println(SortPrinting.SPACE);
			SortPrinting.centerAlign("[2] Today profit");	
			System.out.println(SortPrinting.SPACE);
			SortPrinting.centerAlign("[3] View Order By General");	
			System.out.println(SortPrinting.SPACE);
			SortPrinting.centerAlign("[4] View Order By Older");	
			System.out.println(SortPrinting.SPACE);
			SortPrinting.centerAlign("[5] EXIT");	
			System.out.println(SortPrinting.SPACE);
			System.out.println(SortPrinting.BOTTOM);
			
			int choice = scan.getChoice(5);
			switch(choice) {
			case 1:
				settlement();
				break;
			case 2:
				todayProfit();
				break;
			case 3:
				orderByAgeType("General");
				break;
			case 4:
				orderByAgeType("TheOld");
				break;
			case 5:
				System.out.println("POS 기를 종료합니다.");
				System.exit(0);
			}	
		}
	}

	private static void settlement() {
		// 정산
		Connection conn = null;
		try {
			
			conn = Jdbc_ConPrivider.getConnection();
			Jdbc_OrderDAO dao_Order =Jdbc_OrderDAO.getInstance();
			
			dao_Order.settlement(conn);
			
			
			
			System.out.println("정산이 완료되었습니다.");
		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다");
			e.printStackTrace();
		}finally {
			Jdbc_Closer.close(conn);
		}		
	}
	
	// 오늘 판매한 금액 출력
	private static void todayProfit() {
		Connection conn = null;
		Jdbc_OrderDAO dao_Order =Jdbc_OrderDAO.getInstance();
		
		try {
			conn = Jdbc_ConPrivider.getConnection();
			System.out.println("오늘 판매한 상품의 합계");
			System.out.println(dao_Order.todayProfit(conn));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			Jdbc_Closer.close(conn);
		}
		
	}
	
	private static void orderByAgeType(String ageType) {
		// 고객 나이 타입에 따라 주문한 상품들 출력
		Connection conn = null;
		List<String> saleList = null;
		Jdbc_OrderDAO dao_Order =Jdbc_OrderDAO.getInstance();
		
		try {
			conn = Jdbc_ConPrivider.getConnection();
			saleList = dao_Order.sendSalesByGeneral(conn, ageType);
		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다");
			e.printStackTrace();
		}finally {
			Jdbc_Closer.close(conn);
		}
		
		for(String sales : saleList) {
			System.out.println("\t".concat(sales));
		}
	}
	

	
	

}