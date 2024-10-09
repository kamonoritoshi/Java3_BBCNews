package poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import poly.entity.Categories;
import poly.utils.Jdbc;

public class CategoriesDAOImpl implements CategoriesDAO {

	@Override
	public List<Categories> findAll() {
		String sql = "SELECT * FROM Categories";
		try {
			List<Categories> entities = new ArrayList<>();
			Object[] values = {};
			ResultSet rs = Jdbc.executeQuery(sql, values);
			while (rs.next()) {
				Categories entity = new Categories();
				entity.setId(rs.getInt("id"));
				entity.setName(rs.getString("name"));
				entity.setDescription(rs.getString("description"));
				entities.add(entity);
			}
			return entities;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Categories findById(int id) {
		String sql = "SELECT * FROM Categories WHERE id=?";
		try {
			Object[] values = { id };
			ResultSet rs = Jdbc.executeQuery(sql, values);
			if (rs.next()) {
				Categories entity = new Categories();
				entity.setId(rs.getInt("id"));
				entity.setName(rs.getString("name"));
				entity.setDescription(rs.getString("description"));
				return entity;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Not found");
	}

	@Override
	public void create(Categories entity) {
		String sql = "INSERT INTO Categories (id, name, description) VALUES (?, ?, ?)";
		try {
			Object[] values = { entity.getId(), entity.getName(), entity.getDescription() };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Categories entity) {
		String sql = "UPDATE Categories SET name=?, description=? WHERE id=?";
		try {
			Object[] values = { entity.getName(), entity.getDescription(), entity.getId() };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteById(int id) {
		String sql = "DELETE FROM Categories WHERE id=?";
		try {
			Object[] values = { id };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
