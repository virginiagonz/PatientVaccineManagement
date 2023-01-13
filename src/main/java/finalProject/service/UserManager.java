package finalProject.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import finalProject.model.Users;

public class UserManager {

	private String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu10";

	private String username = "cs3220stu10";

	private String password = "OyuNpDygg5Kp";

	private Connection connection;

	public UserManager() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Users> getUserEntries() {

		List<Users> userEntries = new ArrayList<Users>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");

			while (rs.next()) {
				Users userEntry = new Users();
				userEntry.setUserId(rs.getInt("id"));
				userEntry.setFirstName(rs.getString("first_name"));
				userEntry.setLastName(rs.getString("last_name"));
				userEntry.setPhoneNum(rs.getString("phone"));
				userEntries.add(userEntry);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userEntries;
	}

	public Users getUserEntry(int userId) {
		Users entry = new Users();
		try {
			String sql = "select * from users where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				entry.setUserId(rs.getInt("id"));
				entry.setFirstName(rs.getString("first_name"));
				entry.setLastName(rs.getString("last_name"));

			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entry;
	}

	public int addUser(String firstName, String lastName, String phoneNum) {
		int id = 0;
		try {
			String sql = "insert into users ( first_name, last_name, phone) values (?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, phoneNum);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next())
				id = rs.getInt(1);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void updateUser(int userId, String firstName, String lastName) {
		try {
			String sql = "update users set first_name = ?, last_name = ? where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setInt(3, userId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEntry(int userId) {
		try {
			String sql = "delete from users where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
