package hambuger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect_provider {

public static Connection getConnection() throws SQLException {
		
		//String jdbcUrl = "jdbc:oracle:thin:@[주소]:[포트]:[데이터베이스]";
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String pw = "tiger";
		
		return DriverManager.getConnection(jdbcUrl, user, pw);
	}
}
