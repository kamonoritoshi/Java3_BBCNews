package poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.entity.News;
import poly.utils.Jdbc;

public class NewsDAOImpl implements NewsDAO {

	@Override
	public List<News> findAll() {
		String sql = "SELECT u.fullname AS authorName, n.*, c.name AS categoryName FROM Users u RIGHT JOIN News n ON u.id = n.author LEFT JOIN Categories c ON n.category_id = c.id;";
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
				entity.setAuthorName(rs.getString("authorName"));
				entity.setViewCount(rs.getInt("view_count"));
				entity.setCategoryId(rs.getInt("category_id"));
				entity.setCategoryName(rs.getString("categoryName"));
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
		String sql = "SELECT u.fullname AS authorName, n.*, c.name AS categoryName FROM Users u RIGHT JOIN News n ON u.id = n.author LEFT JOIN Categories c ON n.category_id = c.id WHERE n.id = ?;";
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
				entity.setAuthorName(rs.getString("authorName"));
				entity.setViewCount(rs.getInt("view_count"));
				entity.setCategoryId(rs.getInt("category_id"));
				entity.setCategoryName(rs.getString("categoryName"));
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

	@Override
	public List<News> findByHome() {
		String sql = "SELECT u.fullname AS authorName, n.*, c.name AS categoryName FROM Users u RIGHT JOIN News n ON u.id = n.author LEFT JOIN Categories c ON n.category_id = c.id WHERE n.home = 1 ORDER BY n.id;";
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
				entity.setAuthorName(rs.getString("authorName"));
				entity.setViewCount(rs.getInt("view_count"));
				entity.setCategoryId(rs.getInt("category_id"));
				entity.setCategoryName(rs.getString("categoryName"));
				entity.setHome(rs.getBoolean("home"));
				entities.add(entity);
			}
			return entities;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<News> findByCategory(String categoryName) {
		List<News> entities = new ArrayList<>();
		String sql = "SELECT u.fullname AS authorName, n.*, c.name AS categoryName FROM Users u RIGHT JOIN News n ON u.id = n.author LEFT JOIN Categories c ON n.category_id = c.id WHERE c.name = ? ORDER BY n.id;";
		try {
			Object[] values = { categoryName };
			ResultSet rs = Jdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = new News();
				entity.setId(rs.getInt("id"));
				entity.setTitle(rs.getString("title"));
				entity.setContent(rs.getString("content"));
				entity.setImage(rs.getBytes("image"));
				entity.setPostedDate(rs.getDate("posted_date"));
				entity.setAuthor(rs.getString("author"));
				entity.setAuthorName(rs.getString("authorName"));
				entity.setViewCount(rs.getInt("view_count"));
				entity.setCategoryId(rs.getInt("category_id"));
				entity.setCategoryName(rs.getString("categoryName"));
				entity.setHome(rs.getBoolean("home"));
				entities.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	@Override
	public List<News> findTopViewed() {
		List<News> entities = new ArrayList<>();
        String sql = "SELECT TOP 5 u.fullname AS authorName, n.*, c.name AS categoryName FROM Users u RIGHT JOIN News n ON u.id = n.author LEFT JOIN Categories c ON n.category_id = c.id ORDER BY view_count DESC;";
        try {
        	Object[] values = { };
			ResultSet rs = Jdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = new News();
				entity.setId(rs.getInt("id"));
				entity.setTitle(rs.getString("title"));
				entity.setContent(rs.getString("content"));
				entity.setImage(rs.getBytes("image"));
				entity.setPostedDate(rs.getDate("posted_date"));
				entity.setAuthor(rs.getString("author"));
				entity.setAuthorName(rs.getString("authorName"));
				entity.setViewCount(rs.getInt("view_count"));
				entity.setCategoryId(rs.getInt("category_id"));
				entity.setCategoryName(rs.getString("categoryName"));
				entity.setHome(rs.getBoolean("home"));
				entities.add(entity);
			}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return entities;
	}

	@Override
	public List<News> findLatest() {
		List<News> entities = new ArrayList<>();
        String sql = "SELECT TOP 5 u.fullname AS authorName, n.*, c.name AS categoryName FROM Users u RIGHT JOIN News n ON u.id = n.author LEFT JOIN Categories c ON n.category_id = c.id ORDER BY posted_date DESC;";
        try {
        	Object[] values = { };
			ResultSet rs = Jdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = new News();
				entity.setId(rs.getInt("id"));
				entity.setTitle(rs.getString("title"));
				entity.setContent(rs.getString("content"));
				entity.setImage(rs.getBytes("image"));
				entity.setPostedDate(rs.getDate("posted_date"));
				entity.setAuthor(rs.getString("author"));
				entity.setAuthorName(rs.getString("authorName"));
				entity.setViewCount(rs.getInt("view_count"));
				entity.setCategoryId(rs.getInt("category_id"));
				entity.setCategoryName(rs.getString("categoryName"));
				entity.setHome(rs.getBoolean("home"));
				entities.add(entity);
			}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return entities;
	}
}
