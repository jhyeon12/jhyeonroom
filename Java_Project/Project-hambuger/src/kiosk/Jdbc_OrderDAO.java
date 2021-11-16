package kiosk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Jdbc_OrderDAO {

	private Jdbc_OrderDAO() {
	}

	private static Jdbc_OrderDAO dao = new Jdbc_OrderDAO();

	static public Jdbc_OrderDAO getInstance() {
		return dao;
	}

	// 영수증에 들어갈 주문번호를 시퀀스로 생성해서 반환

	public int createTicket(Connection conn) {

		Statement stm = null;
		ResultSet rs = null;

		String sql = SQLQuery.CREATE_TICKET;
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
		}
		return ticketNumber;
	}

	// 주문 테이블에 데이터 추가
	public void addOrderData(Connection conn, List<VO_Sales> list, VO_Orders orders) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = SQLQuery.INSERT_ORDER_INFO;

		int ticket = createTicket(conn);

		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		String date = String.valueOf(cal.get(Calendar.DATE));
		String hour = String.valueOf(cal.get(Calendar.HOUR));
		String min = String.valueOf(cal.get(Calendar.MINUTE));
		String sec = String.valueOf(cal.get(Calendar.SECOND));

		String orderDate = year.concat("/").concat(month).concat("/").concat(date).concat("/".concat(hour)).concat("/".concat(min)).concat("/".concat(sec));
		
		
		orders.setPurchaseDate(orderDate);

		try {

			pst = conn.prepareStatement(sql);
			for (VO_Sales sales : list) {

				pst.setInt(1, ticket);
				pst.setString(2, orders.getAgeGroup());
				pst.setString(3, orders.getPurchaseDate());
				pst.setString(4, orders.getEatHereOrNot());
				pst.setString(5, orders.getTableService());
				pst.setInt(6, sales.getProductId());

				rs = pst.executeQuery();

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(pst);
		}
	}

	// 일반 또는 노인 고객이 주문한 상품을 출력, 중복되는 상품이름은 출력하지 않는다.
	public List<String> sendSalesByGeneral(Connection conn, String ageType) {
		String sql = "select s_name from sales natural join Hamorder where Hamorder.ageType = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> saleList = new LinkedList<>();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, ageType);
			rs = pst.executeQuery();

			while (rs.next()) {
				boolean chk = false;
				for (String arg : saleList) {
					if (arg.equals(rs.getString(1))) {
						chk = true;
					}
				}
				if (!chk) {
					saleList.add(rs.getString(1));
				}
			}
		} catch (SQLException e) {
			System.err.println("일시적인 오류입니다.");
			e.printStackTrace();
		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(pst);
		}
		return saleList;
	}

	// 오늘 주문한 모든 상품의 합계를 HamOwner 테이블에 추가
	public void settlement(Connection conn) {
		Statement posDate = null;
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		String date = String.valueOf(cal.get(Calendar.DATE));
		String hour = String.valueOf(cal.get(Calendar.HOUR));
		String min = String.valueOf(cal.get(Calendar.MINUTE));
		String sec = String.valueOf(cal.get(Calendar.SECOND));

		String lastUpdated = year.concat("/").concat(month).concat("/").concat(date).concat("/".concat(hour)).concat("/".concat(min)).concat("/".concat(sec));

		String sql = "update HamOwner set ho_profit = ho_profit + ?, ho_date = ?";
		String sql2 = "select sum(s_price) from sales natural join hamorder where saleDate > ?";
		String posDatesql = "select ho_date from hamowner";

		try {
			
			String thisPosDate = null;
			posDate = conn.createStatement();
			pst = conn.prepareStatement(sql);
			pst2 = conn.prepareStatement(sql2);
			
			rs = posDate.executeQuery(posDatesql);
			while(rs.next()) {
				thisPosDate = rs.getString(1);
			}
			
			pst2.setString(1, thisPosDate);
			rs2 = pst2.executeQuery();
			
			while(rs2.next()){
				pst.setInt(1, rs2.getInt(1));
				pst.setString(2, lastUpdated);
				pst.executeQuery();
			}
			
			//System.out.println(a+"행");
			
			

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			Jdbc_Closer.close(rs2);
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(posDate);
			Jdbc_Closer.close(pst);
			Jdbc_Closer.close(pst2);

		}
	}

	public int todayProfit(Connection conn) {

		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		String date = String.valueOf(cal.get(Calendar.DATE));
		String orderDate = year.concat("/").concat(month).concat("/").concat(date);

		PreparedStatement pst = null;
		ResultSet rs = null;
		int profit = 0;
		String sql2 = "select sum(s_price) from sales natural join hamorder where saleDate > ?";

		try {

			pst = conn.prepareStatement(sql2);
			pst.setString(1, orderDate);
			rs = pst.executeQuery();

			while (rs.next()) {
				profit = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			Jdbc_Closer.close(rs);
			Jdbc_Closer.close(pst);
		}

		return profit;

	}

}