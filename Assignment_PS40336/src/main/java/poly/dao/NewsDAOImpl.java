package poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.entity.News;
import poly.utils.Jdbc;

public class NewsDAOImpl implements NewsDAO {

	@Override
	public List<News> findAll() {
		String sql = "SELECT * FROM News";
		try {
			List<News> entities = new ArrayList<>();
			Object[] values = {};
			ResultSet rs = Jdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = new News();
				entity.setId(rs.getInt("id"));
				entity.setTitle(rs.getString("title"));
				entity.setContent(rs.getString("content"));
				entity.setImage(rs.getBytes("image"));
				entity.setPostedDate(rs.getDate("posted_date"));
				entity.setAuthor(rs.getString("author"));
				entity.setViewCount(rs.getInt("view_count"));
				entity.setCategoryId(rs.getInt("category_id"));
				entity.setHome(rs.getBoolean("home"));
				entities.add(entity);
			}
			return entities;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public News findById(int id) {
		String sql = "SELECT * FROM News WHERE id=?";
		try {
			Object[] values = { id };
			ResultSet rs = Jdbc.executeQuery(sql, values);
			if (rs.next()) {
				News entity = new News();
				entity.setId(rs.getInt("id"));
				entity.setTitle(rs.getString("title"));
				entity.setContent(rs.getString("content"));
				entity.setImage(rs.getBytes("image"));
				entity.setPostedDate(rs.getDate("posted_date"));
				entity.setAuthor(rs.getString("author"));
				entity.setViewCount(rs.getInt("view_count"));
				entity.setCategoryId(rs.getInt("category_id"));
				entity.setHome(rs.getBoolean("home"));
				return entity;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Not found");
	}

	@Override
	public void create(News entity) {
		String sql = "INSERT INTO News (id, title, content, image, posted_date, author, view_count, category_id, home) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Object[] values = { entity.getId(), entity.getTitle(), entity.getContent(), entity.getImage(),
					entity.getPostedDate(), entity.getAuthor(), entity.getViewCount(), entity.getCategoryId(),
					entity.isHome() };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(News entity) {
		String sql = "UPDATE News SET title=?, content=?, image=?, posted_date=?, author=?, view_count=?, category_id=?, home=? WHERE id=?";
		try {
			Object[] values = { entity.getTitle(), entity.getContent(), entity.getImage(), entity.getPostedDate(),
					entity.getAuthor(), entity.getViewCount(), entity.getCategoryId(), entity.isHome(),
					entity.getId() };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteById(int id) {
		String sql = "DELETE FROM News WHERE id=?";
		try {
			Object[] values = { id };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
