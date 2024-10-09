package poly.dao;

import java.util.List;

import poly.entity.News;

public interface NewsDAO {
	// Truy vấn tất cả 
		List<News> findAll();
		// Truy vấn theo mã
		News findById(int id);
		// Thêm mới
		void create(News entity);
		// Cập nhật
		void update(News entity);
		// Xóa theo mã
		void deleteById(int id);
}
