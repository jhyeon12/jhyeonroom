package kiosk;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc_Closer {

	// SQL 과 통신했던 모든 객체들을 닫는 역할을 담당하는 JDBCUtil 클래스
		// 닫는 모든 메소드들은 오버로딩하며(닫는 객체만 다를뿐 닫는 공통의 역할을 하기 때문에)
		// 자주 쓰이는 메소드일 거라 예상되므로 사용할때마다 인스턴스의 선언을 해서
		// 사용하여 일회성으로 사용하기 보다는 static으로 선언하여
		// 버려지는 인스턴스를 최소화한다.

		public static void close(Connection conn) {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
	//  이거 안만들어도 되는 이유
	//  PreparedStatement 가 Statement를 상속받고 있고,
	//  Statement 객체내의 메소드에 이미 close() 라는 메소드가 있기 때문에
	//  close(Statement stm) 메소드 하나만으로도 PreparedStatement 까지 닫아줄 수 있기 때문이다.
		
		
//		public static void close(PreparedStatement pstm) {
//			if (pstm != null) {
//				try {
//					pstm.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		
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
