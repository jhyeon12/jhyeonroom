package kiosk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc_ConPrivider {

	public static Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String pW = "tiger";
		
		return DriverManager.getConnection(jdbcURL, user, pW);
		
	}
}