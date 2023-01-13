package finalProject.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import finalProject.model.Contacts;

public class ContactManager {
	
	private String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu10";

	private String username = "cs3220stu10";

	private String password = "OyuNpDygg5Kp";

	private Connection connection;

	public ContactManager() {
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

	public List<Contacts> getContactEntries() {

		List<Contacts> contactEntries = new ArrayList<Contacts>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from contacts");

			while (rs.next()) {
				
				Contacts contactEntry = new Contacts();
				contactEntry.setContactId(rs.getInt("contactId"));
				contactEntry.setNameKey(rs.getString("nameKey"));
				contactEntry.setNameValue(rs.getString("nameValue"));
				
				contactEntries.add(contactEntry);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contactEntries;
	}

	public Contacts getContactEntry(int id) {
		Contacts entry = new Contacts();
		try {
			String sql = "select * from contacts where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				entry.setContactId(rs.getInt("contact_id"));
				entry.setNameKey(rs.getString("name_key"));
				entry.setNameValue(rs.getString("name_value"));

			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entry;
	}
	
	public int addContact(String nameKey, String nameValue, int userId) {
		int id = 0;
		try {
			String sql = "insert into contacts ( name_key, name_value, userId) values (?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, nameKey);
			pstmt.setString(2, nameValue);
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

	public void updateContact(int contactId, int nameKey, int nameValue) {
		try {
			String sql = "update contacts set name_key = ?, name_value = ? where contact_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, nameKey);
			pstmt.setInt(2, nameValue);
			pstmt.setInt(3, contactId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteEntry(int contactId) {
		try {
			String sql = "delete from contacts where contact_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, contactId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
