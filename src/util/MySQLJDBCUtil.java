package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLJDBCUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/classifieds";
	private static final String USER = "root";
	private static final String PASSWORD = "mysql@12345";
	public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection stablished with mysql******");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to connect to mysqlxxxxxxxx");
        }
        return conn;
    }
}
