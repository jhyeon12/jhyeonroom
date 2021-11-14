package kiosk;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc_Closer {

	// SQL 과 통신했던 모든 객체들을 닫는 역할을 담당하는 JDBCUtil 클래스
	

		public static void close(Connection conn) {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		public static void close(Statement stm) {
			if(stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		public static void close(ResultSet rs) {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
}
