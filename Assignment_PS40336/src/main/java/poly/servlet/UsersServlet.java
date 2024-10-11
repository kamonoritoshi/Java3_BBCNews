package poly.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.UsersDAO;
import poly.dao.UsersDAOImpl;
import poly.entity.News;
import poly.entity.Users;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet({ "/users/index", "/users/edit/*", "/users/create", "/users/update", "/users/delete", "/users/reset" })
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDAO usersDAO;

	@Override
	public void init() {
		usersDAO = new UsersDAOImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		registerDateConverter();

		// Tạo đối tượng Users từ request parameters
		Users form = new Users();
		try {
			BeanUtils.populate(form, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// Xử lý các hành động dựa trên đường dẫn URL
		if (path.contains("index")) {
			listUsers(req, resp);
		} else if (path.contains("edit")) {
			String idStr = req.getPathInfo();
			if (idStr != null && idStr.length() > 1) {
				String id = idStr.substring(1);
				Users user = usersDAO.findById(id);
				req.setAttribute("item", user);
			}
			listUsers(req, resp);
		} else if (path.contains("create")) {
			createUser(req, resp, form);
		} else if (path.contains("update")) {
			updateUser(req, resp, form);
		} else if (path.contains("delete")) {
			deleteUser(req, resp, form);
		} else if (path.contains("reset")) {
			resetForm(req, resp);
		} else {
			listUsers(req, resp);
		}
	}
	
	private void registerDateConverter() {
		ConvertUtils.register(new Converter() {
			private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			@Override
			public Object convert(Class type, Object value) {
				if (value == null) {
					return null;
				}
				if (value instanceof Date) {
					return value;
				}
				if (value instanceof String) {
					String str = (String) value;
					if (str.trim().isEmpty()) {
						return null;
					}
					try {
						return sdf.parse(str);
					} catch (ParseException e) {
						throw new RuntimeException("Invalid date format. Please use dd-MM-yyyy.");
					}
				}
				throw new RuntimeException("Unsupported conversion type");
			}
		}, java.util.Date.class);
	}
	
	private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Users> list = usersDAO.findAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/users_management.jsp").forward(req, resp);
	}
	
	private void createUser(HttpServletRequest req, HttpServletResponse resp, Users form)
			throws ServletException, IOException {
		usersDAO.create(form);
		listUsers(req, resp);
	}
	
	private void updateUser(HttpServletRequest req, HttpServletResponse resp, Users form)
			throws ServletException, IOException {
		usersDAO.update(form);
		listUsers(req, resp);
	}
	
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp, Users form)
			throws ServletException, IOException {
		usersDAO.deleteById(form.getId());
		listUsers(req, resp);
	}
	
	private void resetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("item", new Users());
		listUsers(req, resp);
	}
}
