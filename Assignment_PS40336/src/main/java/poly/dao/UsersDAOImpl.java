package poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.entity.Users;
import poly.utils.Jdbc;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public List<Users> findAll() {
		String sql = "SELECT * FROM Users";
		try {
			List<Users> entities = new ArrayList<>();
			Object[] values = {};
			ResultSet rs = Jdbc.executeQuery(sql, values);
			while (rs.next()) {
				Users entity = new Users();
				entity.setId(rs.getString("id"));
				entity.setPassword(rs.getString("password"));
				entity.setFullname(rs.getString("fullname"));
				entity.setBirthday(rs.getDate("birthday"));
				entity.setGender(rs.getBoolean("gender"));
				entity.setPhoneNumber(rs.getString("phone_number"));
				entity.setEmail(rs.getString("email"));
				entity.setRole(rs.getString("role"));
				entity.setStatus(rs.getString("status"));
				entities.add(entity);
			}
			return entities;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Users findById(String id) {
		String sql = "SELECT * FROM Users WHERE id=?";
		try {
			Object[] values = { id };
			ResultSet rs = Jdbc.executeQuery(sql, values);
			if (rs.next()) {
				Users entity = new Users();
				entity.setId(rs.getString("id"));
				entity.setPassword(rs.getString("password"));
				entity.setFullname(rs.getString("fullname"));
				entity.setBirthday(rs.getDate("birthday"));
				entity.setGender(rs.getBoolean("gender"));
				entity.setPhoneNumber(rs.getString("phone_number"));
				entity.setEmail(rs.getString("email"));
				entity.setRole(rs.getString("role"));
				entity.setStatus(rs.getString("status"));
				return entity;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Not found");
	}

	@Override
	public void create(Users entity) {
		String sql = "INSERT INTO Users (id, password, fullname, birthday, gender, phone_number, email, role, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Object[] values = { entity.getId(), entity.getPassword(), entity.getFullname(), entity.getBirthday(),
					entity.isGender(), entity.getPhoneNumber(), entity.getEmail(), entity.getRole(),
					entity.getStatus() };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Users entity) {
		String sql = "UPDATE Users SET password=?, fullname=?, birthday=?, gender=?, phone_number=?, email=?, role=?, status=? WHERE id=?";
		try {
			Object[] values = { entity.getPassword(), entity.getFullname(), entity.getBirthday(), entity.isGender(),
					entity.getPhoneNumber(), entity.getEmail(), entity.getRole(), entity.getStatus(), entity.getId() };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteById(String id) {
		String sql = "DELETE FROM Users WHERE id=?";
		try {
			Object[] values = { id };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
