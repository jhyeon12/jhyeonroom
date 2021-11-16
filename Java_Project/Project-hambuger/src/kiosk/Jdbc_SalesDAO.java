package kiosk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Jdbc_SalesDAO {
    //////////////////////////////////////////////////////SingleTon
	private Jdbc_SalesDAO() {}
	// 클래스 내부에서 인스턴스 생성
	private static Jdbc_SalesDAO dao = new Jdbc_SalesDAO();
	// 내부에서 만들어진 인스턴스를 특정 메소드를 이용해서 받을 수 있도록 함
	public static Jdbc_SalesDAO getInstance() {
		return dao;
	}
	///////////////////////////////////////////////////////////////

	public List<VO_Sales> recoHamburgerForAll(Connection conn) {
		String sql = SQLQuery.SHOW_RECOMMENDED;

		ResultSet rs = null;
		Statement stm = null;

		List<VO_Sales> salesList = new LinkedList<VO_Sales>();

		try {
			conn = Jdbc_ConPrivider.getConnection();
			stm = conn.createStatement();

			rs = stm.executeQuery(sql);

			while (rs.next()) {

				VO_Sales sales = new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), 1);

				salesList.add(sales);
			}

		} catch (SQLException e) {

		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(stm);

		}

		return salesList;
	}

	// 고령자에 안성맞춤인 햄버거 메뉴 출력
	public List<VO_Sales> showRecoForTheOld(Connection conn) {
		String sql = SQLQuery.SHOW_BURGER_FOR_THE_OLD;

		ResultSet rs = null;
		Statement stm = null;

		List<VO_Sales> salesList = new LinkedList<VO_Sales>();

		try {

			stm = conn.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {

				VO_Sales sales = new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), 1);

				salesList.add(sales);
			}

		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
			e.printStackTrace();

		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(stm);

		}

		return salesList;
	}

	// 2. outOfStock() : Connector , VO_Sales 객체를 매개변수로 받아
	// 매개변수 VO_Sales 상품에 들어가는 재료가 있는지 확인하고 충분하지 않다면 true반환
	public boolean outOfStock(Connection conn, VO_Sales sales) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// sql : select
		// 1. 해당 ? (상품이름)에 대해 재고에서 해당 상품에 들어가는 재고를 뺀 결과를 보내줌(Java 의 ResultSet에 저장용)
		String sql = SQLQuery.OUT_OF_STOCK;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sales.getProductName());

			rs = pstmt.executeQuery();

			// 매개변수 VO_Sales 상품에 들어가는 재료가 있는지 확인하고 충분하지 않다면 true반환
			boolean check = false;
			while (rs.next()) {
				

				if (rs.getInt(1) < 0) {
					check = true;
					break;
				}

			}
			return check;

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("일시적인 오류입니다.");
		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(pstmt);

		}

		return false;
	}

	// 3. recieveOrder() : VO_Sales 객체를 매개변수로 받아 해당 상품의 재료가 재고에 충분한지 확인 후
	// 충분하다면 재고에서 필요 재료수만큼 재료 차감 (outOfStock 반환값이 true 일시 실행)
	public boolean recieveOrder(Connection conn, VO_Sales sales) {

		PreparedStatement pstmt = null;
		boolean result = false;

		// sql : select
		// 2. 해당 ?(상품이름) 필요재료를 재고에서 차감
		String sql = SQLQuery.RECIEVE_ORDER;

		if (outOfStock(conn, sales)) {
			System.err.println("재고가 없어 주문할 수 없습니다.");
			result = true;

		} else {

			try {
				conn = Jdbc_ConPrivider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sales.getQuantity());
				pstmt.setString(2, sales.getProductName());
				pstmt.executeQuery();

			} catch (SQLException e) {
				System.err.println("일시적인 오류입니다.");
				e.printStackTrace();
			} finally {
				Jdbc_Closer.close(pstmt);
				Jdbc_Closer.close(conn);
			}

		}
		return result;
	}

	// 상품명을 매개변수로 받아 그 상품이름을 가진 상품 하나를 반환
	public VO_Sales sendSalesByName(Connection conn, String s_name) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		VO_Sales sales = null;

		String sql = SQLQuery.SELECT_SALES_BY_NAME;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_name);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				sales = new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("일시적인 오류입니다.");
		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(pstmt);

		}

		return sales;
	}

	public List<VO_Sales> sendSalesByType(Connection conn, String type) {

		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<VO_Sales> list = new LinkedList<VO_Sales>();
		StringTokenizer tk = new StringTokenizer(type, "/");

		String sql = SQLQuery.SELECT_SALES_BY_TYPE;

		try {
			pstm = conn.prepareStatement(sql);
			if (type.contains("/")) {
				while (tk.hasMoreTokens()) {
					pstm.setString(1, tk.nextToken());
					rs = pstm.executeQuery();
					while (rs.next()) {
						list.add(new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
								rs.getString(5), 1));
					}
				}
			} else {
				pstm.setString(1, type);
				rs = pstm.executeQuery();
				while (rs.next()) {
					list.add(new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
							1));
				}
			}

		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(pstm);
		}

		return list;

	}

	// 사이드 메뉴를 list로 만들어 반환
	public List<VO_Sales> sendSideMenu(Connection conn) {
		String sql = SQLQuery.SHOW_SIDE;

		ResultSet rs = null;
		Statement stm = null;
		List<VO_Sales> sideList = new LinkedList<VO_Sales>();
		try {

			stm = conn.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {

				VO_Sales sales = new VO_Sales(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), 1);
				sideList.add(sales);
			}

		} catch (SQLException e) {

		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(stm);
		}

		return sideList;

	}
	


}