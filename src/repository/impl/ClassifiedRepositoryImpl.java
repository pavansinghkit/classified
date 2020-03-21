package repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import repository.ClassifiedRepository;
import until.enumeration.CategoryType;
import until.enumeration.ClassifiedStatus;
import util.Classified;
import util.MySQLJDBCUtil;

public class ClassifiedRepositoryImpl implements ClassifiedRepository {

	// ***************************print for admin****************************//

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
				Date modifiedAt = rs.getDate("modified_at");
				String modifiedBy = rs.getString("modified_by");
				ClassifiedStatus status = ClassifiedStatus.valueOf(rs.getString("status"));
				classifiedList.add(new Classified(classifiedId, title, price, description, category, createdAt,
						createdBy, status, modifiedAt, modifiedBy));
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

	// ***************************print only for current
	// USER****************************//

	@Override
	public List<Classified> getClassifiedListByUserName(String userName) {
		List<Classified> classifiedListByUserName = new ArrayList<>();
		Connection connection = MySQLJDBCUtil.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM classified_info WHERE created_by=? AND status =?");// added AND
																										// status =?
			ps.setString(1, userName);
			ps.setString(2, ClassifiedStatus.ACTIVE.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer classifiedId = rs.getInt("classified_id");
				// System.out.println(classifiedId);
				String title = rs.getString("title");
				Double price = rs.getDouble("price");
				String description = rs.getString("description");
				CategoryType category = CategoryType.valueOf(rs.getString("category"));
				Date createdAt = rs.getDate("created_at");
				String createdBy = rs.getString("created_by");
				Date modifiedAt = rs.getDate("modified_at");
				String modifiedBy = rs.getString("modified_by");
				ClassifiedStatus status = ClassifiedStatus.valueOf(rs.getString("status"));
				classifiedListByUserName.add(new Classified(classifiedId, title, price, description, category,
						createdAt, createdBy, status, modifiedAt, modifiedBy));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return classifiedListByUserName;
	}
	// ************************ create *****************************//

	private static final String INSERT_SQL = "INSERT INTO classified_info(title, price, description, category, created_at, created_by, status) values(?, ?, ?, ?, ?, ?, ?) ";

	@Override
	public Classified createClassified(Classified classified) {
		Connection connection = MySQLJDBCUtil.getConnection();
		if (classified != null) {
			try (PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
				// ps.setInt(1, classified.getClassifiedId());
				ps.setString(1, classified.getTitle());
				ps.setDouble(2, classified.getPrice());
				ps.setString(3, classified.getDescription());
				ps.setString(4, classified.getCategory().toString());
				ps.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				ps.setString(6, classified.getCreatedBy());
				ps.setString(7, classified.getStatus().toString());
//				ps.setDate(8, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
//				ps.setString(9, classified.getModifiedBy());

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

	// ***************************update******************************//
	private static String sqlUpdate = ("update classified_info set title=?,price=?,description=?,category=?, modified_at =?, modified_by= ? where classified_id=?");

	@Override
	public Classified updateClassified(Classified classified) {
		Connection connection = MySQLJDBCUtil.getConnection();
		if (classified != null) {
			try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
				// data update
				pstmt.setString(1, classified.getTitle());
				pstmt.setDouble(2, classified.getPrice());
				pstmt.setString(3, classified.getDescription());
				pstmt.setString(4, classified.getCategory().toString());
				pstmt.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				pstmt.setString(6, classified.getModifiedBy());
				pstmt.setInt(7, classified.getClassifiedId());
				int rowAffected = pstmt.executeUpdate();
				if (rowAffected < 1) {
					System.out.println("Invalid Classified Selected! ");
				} else {
					System.out.println(
							"Your classified has been updated successfully, but you can see only if this is approved by Admin");
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return classified;
	}
	// change status after payment

	private static final String SQL_STATUS_UPDATE = "update classified_info set status=? where classified_id=?";

	@Override
	public void updateStatus(Integer classifiedId, String status) {
		System.out.println("Your payment is in process please do not hit back button/ refresh your page...");
		Connection connection = MySQLJDBCUtil.getConnection();
		if (classifiedId != null) {
			try (PreparedStatement pstmt = connection.prepareStatement(SQL_STATUS_UPDATE)) {
				// status update
				pstmt.setString(1, status);
				pstmt.setInt(2, classifiedId);
				int paymentStatus = pstmt.executeUpdate();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
