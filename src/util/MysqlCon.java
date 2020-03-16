package util;

import java.time.*;
import java.sql.*;
import java.io.*;

public class MysqlCon {
	
	public static void main(String[] args) {
		connect();
	}

	public static Connection connect() {
		int count = 0;
		log log = new log();
		while (count < 2) {

			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classifieds", "root",
						"mysql@12345");
				// here Classfield is database name, root is username and password is bordoloa
				count++;
				String msg = "Success";
				log.logging(count, msg);
				return con; // returning the connection instance : FOR SUCCESS

			} catch (Exception e) {
				count++;
				System.out.println("Error in connection. Reconecting... " + e);
				log.logging(count, e.toString());
			}
		}
		return null; // Returning NULL: for fail
	}
}