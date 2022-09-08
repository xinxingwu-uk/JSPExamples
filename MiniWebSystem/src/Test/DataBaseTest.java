package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.JdbcUtils;

public class DataBaseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils = new JdbcUtils();
		Connection con=jdbcUtils.getConnection();
		Statement stat;
		try {
			stat = con.createStatement();
		String sql1 = "create table if not exists userinfo (user varchar(32),passwd varchar(32),PRIMARY KEY(user))";
		stat.executeUpdate(sql1);
		String sql2 = "insert into userinfo values(?,?);";
		PreparedStatement pstmt = con.prepareStatement(sql2);
		pstmt.setString(1, "xxx");
		pstmt.setString(2, "123456");
		pstmt.executeUpdate();
		pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
