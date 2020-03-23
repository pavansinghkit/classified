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

	// *******************print only for currentUSER****************************//

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
	// ************************ create for user*****************************//

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

	// ************************ create for Admin*****************************//
	
	private static final String INSERT_SQL_ADMIN = "INSERT INTO classified_info(title, price, description, category, created_at, created_by) values(?, ?, ?, ?, ?, ?) ";

	@Override
	public Classified createClassifiedAdmin(Classified classified) {
		Connection connection = MySQLJDBCUtil.getConnection();
		if (classified != null) {
			try (PreparedStatement pst = connection.prepareStatement(INSERT_SQL_ADMIN, Statement.RETURN_GENERATED_KEYS)) {
				pst.setString(1, classified.getTitle());
				pst.setDouble(2, classified.getPrice());
				pst.setString(3, classified.getDescription());
				pst.setString(4, classified.getCategory().toString());
				pst.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				pst.setString(6, classified.getCreatedBy());
				//pst.setString(7, classified.getStatus().ACTIVE.toString());

				int numRowsAffected = pst.executeUpdate();
				try (ResultSet rs = pst.getGeneratedKeys()) {
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
	
	// ***************************update all fields******************************//
	
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
	
	// ***************************update title******************************//

	private static String sqlUpdateTitle = ("update classified_info set title=?, modified_at =?, modified_by= ? where classified_id=?");

	@Override
	public Classified updateClassifiedTitle(Classified classified) {
		Connection connection = MySQLJDBCUtil.getConnection();
		if (classified != null) {
			try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdateTitle)) {
				// data update
				pstmt.setString(1, classified.getTitle());
				pstmt.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				pstmt.setString(3, classified.getModifiedBy());
				pstmt.setInt(4, classified.getClassifiedId());
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
	// ***************************update Category******************************//

	private static String sqlUpdateCategry = ("update classified_info set category=?, modified_at =?, modified_by= ? where classified_id=?");

	@Override
	public Classified updateClassifiedCategory(Classified classified) {
		Connection connection = MySQLJDBCUtil.getConnection();
		if (classified != null) {
			try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdateCategry)) {
				// data update
				pstmt.setString(1, classified.getCategory().toString());
				pstmt.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				pstmt.setString(3, classified.getModifiedBy());
				pstmt.setInt(4, classified.getClassifiedId());
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
	// ***************************update Price******************************//
		private static String sqlUpdatePrice = ("update classified_info set price=?, modified_at =?, modified_by= ? where classified_id=?");

		@Override
		public Classified updateClassifiedPrice(Classified classified) {
			Connection connection = MySQLJDBCUtil.getConnection();
			if (classified != null) {
				try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdatePrice)) {
					// data update
					pstmt.setDouble(1, classified.getPrice());
					pstmt.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
					pstmt.setString(3, classified.getModifiedBy());
					pstmt.setInt(4, classified.getClassifiedId());
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
		// ***************************update all discription******************************//
		private static String sqlUpdatedescription = ("update classified_info set description=?, modified_at =?, modified_by= ? where classified_id=?");

		@Override
		public Classified updateClassifiedDescription(Classified classified) {
			Connection connection = MySQLJDBCUtil.getConnection();
			if (classified != null) {
				try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdatedescription)) {
					// data update
					pstmt.setString(1, classified.getDescription());
					pstmt.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
					pstmt.setString(3, classified.getModifiedBy());
					pstmt.setInt(4, classified.getClassifiedId());
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
