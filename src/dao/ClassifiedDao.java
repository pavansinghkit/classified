package dao;

import java.sql.Connection;
import java.sql.Statement;

import util.Classified;
import util.MySQLJDBCUtil;

public class ClassifiedDao {
	
	public Classified saveClassified(Classified classified) {
		try {
			Connection con = MySQLJDBCUtil.getConnection();
			Statement stmt = con.createStatement();
			
		} catch (Exception e) {
		}
		return null;
	}
	
	

}
