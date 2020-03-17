package repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import repository.ClassifiedRepository;
import until.enumeration.ClassifiedStatus;
import util.Classified;
import util.MySQLJDBCUtil;

public class ClassifiedRepositoryImpl implements ClassifiedRepository {

	@Override
	public List<Classified> getClassifiedList() {
		List<Classified> classifiedList= new ArrayList<>();
		Connection connection = MySQLJDBCUtil.getConnection();
		try (Statement st = connection.createStatement();
	             ResultSet rs = st.executeQuery("SELECT * FROM classified_info")) {
			while (rs.next()) {
                Integer classifiedId = rs.getInt("classified_id");
                //System.out.println(classifiedId);
                String title = rs.getString("title");
                Double price = rs.getDouble("price");
                String description = rs.getString("description");
                String category = rs.getString("category");
                Date createdAt = rs.getDate("created_at");
                String createdBy = rs.getString("created_by");
                ClassifiedStatus status = ClassifiedStatus.valueOf(rs.getString("status"));
               
                classifiedList.add(new Classified(classifiedId, title, price, description, category, createdAt, createdBy, status));
            }
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}  catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return classifiedList;
	}
	
}
