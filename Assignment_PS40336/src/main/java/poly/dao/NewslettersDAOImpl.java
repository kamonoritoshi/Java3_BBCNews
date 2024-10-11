package poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.entity.Newsletters;
import poly.utils.Jdbc;

public class NewslettersDAOImpl implements NewslettersDAO{

	@Override
	public List<Newsletters> findAll() {
		String sql = "SELECT * FROM Newsletters";
		try {
			List<Newsletters> entities = new ArrayList<>();
			Object[] values = {};
			ResultSet rs = Jdbc.executeQuery(sql, values);
			while (rs.next()) {
				Newsletters entity = new Newsletters();
				entity.setEmail(rs.getString("email"));
				entity.setStatus(rs.getBoolean("status"));
				entities.add(entity);
			}
			return entities;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Newsletters findByEmail(String email) {
		String sql = "SELECT * FROM Newsletters WHERE email=?";
		try {
			Object[] values = { email };
			ResultSet rs = Jdbc.executeQuery(sql, values);
			if (rs.next()) {
				Newsletters entity = new Newsletters();
				entity.setEmail(rs.getString("email"));
				entity.setStatus(rs.getBoolean("status"));
				return entity;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Not found");
	}

	@Override
	public void create(Newsletters entity) {
		String sql = "INSERT INTO Newsletters (email, status) VALUES (?, ?)";
		try {
			Object[] values = { entity.getEmail(), entity.isStatus() };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Newsletters entity) {
		String sql = "UPDATE Newsletters SET status=? WHERE email=?";
		try {
			Object[] values = { entity.isStatus(), entity.getEmail() };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteByEmail(String email) {
		String sql = "DELETE FROM Newsletters WHERE email=?";
		try {
			Object[] values = { email };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
