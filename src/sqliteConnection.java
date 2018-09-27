/*
 * Michael Odio
 *
 * 9/27/2018
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class sqliteConnection {
	Connection conn = null;

	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Chel\\Desktop\\BankUsers.sqlite");
			return conn;

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
	}


}
