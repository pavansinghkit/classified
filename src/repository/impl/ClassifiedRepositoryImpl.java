package repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import repository.ClassifiedRepository;
import until.enumeration.CategoryType;
import until.enumeration.ClassifiedStatus;
import util.Classified;
import util.MySQLJDBCUtil;

public class ClassifiedRepositoryImpl implements ClassifiedRepository {
	
	@Override
	public List<Classified> getClassifiedList() {
		List<Classified> classifiedList = new ArrayList<>();
		Connection connection = MySQLJDBCUtil.getConnection();
		try (Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM classified_info")) {
			while (rs.next()) {
				Integer classifiedId = rs.getInt("classified_id");
				// System.out.println(classifiedId);
				String title = rs.getString("title");
				Double price = rs.getDouble("price");
				String description = rs.getString("description");
				CategoryType category = CategoryType.valueOf(rs.getString("category"));
				Date createdAt = rs.getDate("created_at");
				String createdBy = rs.getString("created_by");
				ClassifiedStatus status = ClassifiedStatus.valueOf(rs.getString("status"));

				classifiedList.add(new Classified(classifiedId, title, price, description, category, createdAt,
						createdBy, status));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return classifiedList;
	}
	//create
	private static final String INSERT_SQL = "INSERT INTO classified_info(title, price, description, category, created_at, created_by, status) values(?, ?, ?, ?, ?, ?, ?) ";

	@Override
	public Classified createClassified(Classified classified) {
		Connection connection = MySQLJDBCUtil.getConnection();
		if (classified != null) {
			try (PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
				//ps.setInt(1, classified.getClassifiedId());
				ps.setString(1, classified.getTitle());
				ps.setDouble(2, classified.getPrice());
				ps.setString(3, classified.getDescription());
				ps.setString(4, classified.getCategory().toString());
				ps.setDate(5, new java.sql.Date(classified.getCreatedAt().getTime()));
				ps.setString(6, classified.getCreatedBy());
				ps.setString(7, classified.getStatus().toString());
				int numRowsAffected = ps.executeUpdate();
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						classified.setId(rs.getInt(1));
					}
				} catch (SQLException s) {
					s.printStackTrace();
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return classified;
	}
	
}
