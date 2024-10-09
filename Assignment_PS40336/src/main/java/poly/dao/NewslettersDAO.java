package poly.dao;

import java.util.List;
import poly.entity.Newsletters;

public interface NewslettersDAO {
	// Truy vấn tất cả
	List<Newsletters> findAll();
	// Truy vấn theo mã
	Newsletters findByEmail(String email);
	// Thêm mới
	void create(Newsletters entity);
	// Cập nhật
	void update(Newsletters entity);
	// Xóa theo mã
	void deleteByEmail(String email);
}
