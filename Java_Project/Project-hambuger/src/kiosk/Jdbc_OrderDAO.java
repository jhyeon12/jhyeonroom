package kiosk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
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
			}
			return ticketNumber;
		}
		
		// 주문 테이블에 데이터 추가
       public void addOrderData(Connection conn, List<VO_Sales> list, VO_Orders orders) {
    	   PreparedStatement  pst = null;
    	   ResultSet rs = null;
    	   String sql = SQLQuery.INSERT_ORDER_INFO; 
    	   
    	    int ticket = createTicket(conn);
    	    
			Calendar cal = Calendar.getInstance();
			String year =String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH)+1);
			String date = String.valueOf(cal.get(Calendar.DATE));
			orders.setPurchaseDate(year.concat("/").concat(month).concat("/").concat(date));
			
			try {
				
				
				pst = conn.prepareStatement(sql);
				for(VO_Sales sales : list) {
					
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
			}finally {
				Jdbc_Closer.close(rs);
				Jdbc_Closer.close(pst);
			}
    	   
    	   
    	   
    	   
       }
}
