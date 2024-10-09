package poly.dao;

import java.util.List;

import poly.entity.Categories;

public interface CategoriesDAO {
	// Truy vấn tất cả 
		List<Categories> findAll();
		// Truy vấn theo mã
		Categories findById(int id);
		// Thêm mới
		void create(Categories entity);
		// Cập nhật
		void update(Categories entity);
		// Xóa theo mã
		void deleteById(int id);
}
