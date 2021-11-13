package kiosk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Jdbc_SalesDAO {

	private Jdbc_SalesDAO() {
	}

	// 클래스 내부에서 인스턴스 생성
	private static Jdbc_SalesDAO dao = new Jdbc_SalesDAO();

	// 내부에서 만들어진 인스턴스를 특정 메소드를 이용해서 받을 수 있도록 함
	static public Jdbc_SalesDAO getInstance() {
		return dao;
	}

	public List<VO_Sales> recoHamburgerForAll() {
		String sql = SQLQuery.showRecommended;

		Connection conn = null;
		ResultSet rs = null;
		Statement stm = null;

		List<VO_Sales> salesList = new LinkedList<VO_Sales>();

		try {
			conn = Jdbc_ConPrivider.getConnection();
			stm = conn.createStatement();

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				// debug
				System.out.println("상품을 찾았습니다!");

				VO_Sales sales = new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), 0);
//				System.out.println(sales.toString());
				salesList.add(sales);
			}

		} catch (SQLException e) {

		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(stm);
			Jdbc_Closer.close(conn);
		}

		return salesList;
	}

	// 2. outOfStock() : Connector , VO_Sales 객체를 매개변수로 받아
	// 매개변수 VO_Sales 상품에 들어가는 재료가 있는지 확인하고 충분하지 않다면 true반환
	public boolean outOfStock(VO_Sales sales) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// sql : select
		// 1. 해당 ? (상품이름)에 대해 재고에서 해당 상품에 들어가는 재고를 뺀 결과를 보내줌(Java 의 ResultSet에 저장용)
		String sql = "select ingre_stock-ingre_need from (select * from VO_Sales s1 natural join ingredient where s1.s_name = ?)";

		try {
			conn = Jdbc_ConPrivider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sales.getProductName());

			rs = pstmt.executeQuery();

			// 매개변수 VO_Sales 상품에 들어가는 재료가 있는지 확인하고 충분하지 않다면 true반환
			while (rs.next()) {
				boolean check = false;

				if (rs.getInt(1) < 0) {
					check = true;
					break;
				}

				return check;
			}

		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(pstmt);
			Jdbc_Closer.close(conn);

		}

		return false;
	}

	// 3. recieveOrder() : VO_Sales 객체를 매개변수로 받아 해당 상품의 재료가 재고에 충분한지 확인 후
	// 충분하다면 재고에서 필요 재료수만큼 재료 차감 (outOfStock 반환값이 true 일시 실행)
	public void recieveOrder(VO_Sales sales) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		// sql : select
		// 2. 해당 ?(상품이름) 필요재료를 재고에서 차감
		String sql = "update ingredient i1 set ingre_stock = ingre_stock - ingre_need*? where i1.s_id = (select s_id from VO_Sales s1 where s1.s_name = ?)";

		if (outOfStock(sales)) {
			System.err.println("재고가 없어 주문할 수 없습니다.");

		} else {

			try {
				conn = Jdbc_ConPrivider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sales.getQuantity());
				pstmt.setString(2, sales.getProductName());
				pstmt.executeQuery();

			} catch (SQLException e) {
				System.err.println("일시적인 오류입니다.");
			} finally {
				Jdbc_Closer.close(pstmt);
				Jdbc_Closer.close(conn);
			}

		}
	}

	/// 장바구니를 매개변수로 받아서 그 장바구니에 세트메뉴를 추가
	public void addSetMenu(List<VO_Sales> myBasket) {
		String sql = "select * from sales where (s_name = 'FrenchFries' or s_name = 'Coke')";
		Connection conn = null;
		ResultSet rs = null;
		Statement stm = null;

		try {
			conn = Jdbc_ConPrivider.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				VO_Sales sales = new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), 0);
				myBasket.add(sales);

			}

		} catch (SQLException e) {

		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(stm);
			Jdbc_Closer.close(conn);

		}

	}

	// 장바구니를 매개변수로 받아서 그 장바구니에 라지 세트메뉴를 추가
	public void addLargeSetMenu(List<VO_Sales> myBasket) {
		String sql = "select * from sales where s_name = 'LargefrenchFries' or s_name = 'LargeCoke'";
		Connection conn = null;
		ResultSet rs = null;
		Statement stm = null;

		try {
			conn = Jdbc_ConPrivider.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				VO_Sales sales = new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), 0);
				myBasket.add(sales);

			}

		} catch (SQLException e) {

		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(stm);
			Jdbc_Closer.close(conn);

		}

	}

	// 해당 타입을 가진 버거를 모아 List로 반환

	public List<VO_Sales> sendBurgerByType(String type) {
		String sql = null;
		if (type.contains("Chicken") || type.contains("Shrimp")) {
			sql = "select * from sales where s_type = 'Chicken' or s_type = 'Shrimp' ";
		} else if (type.contains("Bulgogi") || type.contains("Etc")) {
			sql = "select * from sales where s_type = 'Bulgogi' or s_type = 'Etc' ";
		} else if (type.equals("Signature")) {
			sql = "select * from sales where s_type = 'Signature' ";
		} else if (type.equals("Beef")) {
			sql = "select * from sales where s_type = 'Beef' ";
		}

		Connection conn = null;
		ResultSet rs = null;
		Statement stm = null;
		List<VO_Sales> burgersByType = new LinkedList<VO_Sales>();
		try {
			conn = Jdbc_ConPrivider.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				VO_Sales sales = new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), 0);
				burgersByType.add(sales);

			}

		} catch (SQLException e) {

		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(stm);
			Jdbc_Closer.close(conn);

		}
		return burgersByType;
	}

	// 상품명을 매개변수로 받아 그 상품이름을 가진 상품 하나를 반환
	public VO_Sales sendSalesByName(String s_name) {
		System.out.println(s_name);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		VO_Sales sales = null;

		String sql = "select * from sales where s_name = ? ";

		try {
			conn = Jdbc_ConPrivider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("찾았습니다!");
				sales = new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("일시적인 오류입니다.");
		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(pstmt);
			Jdbc_Closer.close(conn);
		}

		return sales;
	}

	// 정수를 매개변수로 받아 1이면 커피, 2이면 에이드 3이면 음료목록을 보내주는 메소드
	public List<VO_Sales> sendDrinkListByChoice(Connection conn, int choice) {
		
		Statement stm = null;
		ResultSet rs = null;
		List<VO_Sales> list = new LinkedList<VO_Sales>();
		String sql = null;

		if (choice == 1) {
			sql = "select * from sales where s_type = 'Coffee' ";
		} else if (choice == 2) {
			sql = "select * from sales where s_type = 'Ade' ";
		} else if (choice == 3) {
			sql = "select*from sales where s_type = 'Beverage' ";
		}

		try {
			
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				list.add(new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), 1));
			}

		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(stm);
			
		}

		return list;

	}

	// 사이드 메뉴를 list로 만들어 반환
	public List<VO_Sales> sendSideMenu() {
		String sql = "select * from sales where s_type = 'Side' ";
		Connection conn = null;
		ResultSet rs = null;
		Statement stm = null;
		List<VO_Sales> sideList = new LinkedList<VO_Sales>();
		try {
			conn = Jdbc_ConPrivider.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {

				VO_Sales sales = new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), 0);
				sideList.add(sales);
			}

		} catch (SQLException e) {

		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(stm);
			Jdbc_Closer.close(conn);
		}

		return sideList;

	}

	// 영수증에 들어갈 주문번호를 시퀀스로 생성해서 반환

	public int createTicket() {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;

		String sql = SQLQuery.CreateTicket;
		int ticketNumber = 0;

		try {
			conn = Jdbc_ConPrivider.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				ticketNumber = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
		} finally {

			Jdbc_Closer.close(stm);
			Jdbc_Closer.close(conn);
		}
		return ticketNumber;
	}
}
