package Test.DataGenerate;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbClass {
	public Connection getConn(String url) throws Exception {
		// Connection conn;
		// Class.forName("com.mysql.jdbc.Driver");
		// conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/content",
		// "root", "1");
		// return conn;
		Connection con = null;
		Class.forName("org.sqlite.JDBC");
		con = DriverManager
				.getConnection(url);
		return con;

	}
}
