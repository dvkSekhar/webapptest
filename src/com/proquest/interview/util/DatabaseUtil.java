package com.proquest.interview.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is just a utility class, you should not have to change anything here
 * @author rconklin
 */
public class DatabaseUtil {
	private static final String INSERT_CYN = "INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Dave Williams','(231) 502-1236', '285 Huron St, Port Austin, MI')";
	private static final String INSERT_JOHN = "INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Chris Johnson','(321) 231-7876', '452 Freeman Drive, Algonac, MI')";
	private static final String CREATE_TABLE = "CREATE TABLE PHONEBOOK (NAME varchar(255), PHONENUMBER varchar(255), ADDRESS varchar(255))";
	public static void initDB() {
		Connection cn = null;
		try {
			cn = getConnection();
			Statement stmt = cn.createStatement();
			insertDB(stmt, CREATE_TABLE);
			insertDB(stmt, INSERT_JOHN);
			insertDB(stmt, INSERT_CYN);
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			try {
				cn.commit();
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param stmt
	 * @throws SQLException
	 */
	public static void insertDB(Statement stmt, String sql)  {
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			if(e.getMessage().indexOf("object name already exists:") <-1)
				e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		return DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "");
	}
}
