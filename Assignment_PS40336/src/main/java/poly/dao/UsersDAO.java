package poly.dao;

import java.util.List;

import poly.entity.Users;

public interface UsersDAO {
	// Truy vấn tất cả 
		List<Users> findAll();
		// Truy vấn theo mã
		Users findById(String id);
		// Thêm mới
		void create(Users entity);
		// Cập nhật
		void update(Users entity);
		// Xóa theo mã
		void deleteById(String id);
}
