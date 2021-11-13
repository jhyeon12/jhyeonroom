package kiosk;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws IOException {
		Kiosk_Screen screen = new Kiosk_Screen();
		screen.showMain();

	}
	
}

