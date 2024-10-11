package poly.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.CategoriesDAO;
import poly.dao.CategoriesDAOImpl;
import poly.entity.Categories;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class CategoriesServlet
 */
@WebServlet({ "/categories/index", "/categories/edit/*", "/categories/create", "/categories/update",
		"/categories/delete", "/categories/reset" })
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoriesDAO categoriesDAO;

	@Override
	public void init() {
		categoriesDAO = new CategoriesDAOImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		// Tạo đối tượng Categories từ request parameters
		Categories form = new Categories();
		try {
			BeanUtils.populate(form, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// Xử lý các hành động dựa trên đường dẫn URL
		if (path.contains("index")) {
			listCategories(req, resp);
		} else if (path.contains("edit")) {
			String idStr = req.getPathInfo();
			if (idStr != null && idStr.length() > 1) {
				int id = Integer.parseInt(idStr.substring(1));
				Categories ctgr = categoriesDAO.findById(id);
				req.setAttribute("item", ctgr);
			}
			listCategories(req, resp);
		} else if (path.contains("create")) {
			createCategory(req, resp, form);
		} else if (path.contains("update")) {
			updateCategory(req, resp, form);
		} else if (path.contains("delete")) {
			deleteCategory(req, resp, form);
		} else if (path.contains("reset")) {
			resetForm(req, resp);
		} else {
			listCategories(req, resp);
		}
	}
	
	private void listCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Categories> list = categoriesDAO.findAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/categories_management.jsp").forward(req, resp);
	}
	
	private void createCategory(HttpServletRequest req, HttpServletResponse resp, Categories form)
			throws ServletException, IOException {
		categoriesDAO.create(form);
		listCategories(req, resp);
	}
	
	private void updateCategory(HttpServletRequest req, HttpServletResponse resp, Categories form)
			throws ServletException, IOException {
		categoriesDAO.update(form);
		listCategories(req, resp);
	}
	
	private void deleteCategory(HttpServletRequest req, HttpServletResponse resp, Categories form)
			throws ServletException, IOException {
		categoriesDAO.deleteById(form.getId());
		listCategories(req, resp);
	}
	
	private void resetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("item", new Categories());
		listCategories(req, resp);
	}
}
