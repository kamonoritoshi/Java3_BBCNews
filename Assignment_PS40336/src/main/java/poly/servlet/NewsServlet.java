package poly.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import poly.dao.CategoriesDAO;
import poly.dao.CategoriesDAOImpl;
import poly.dao.NewsDAO;
import poly.dao.NewsDAOImpl;
import poly.dao.UsersDAO;
import poly.dao.UsersDAOImpl;
import poly.entity.Categories;
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
 * Servlet implementation class NewsServlet
 */
@WebServlet({ "/news/index", "/news/edit/*", "/news/create", "/news/update", "/news/delete", "/news/reset" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDAO usersDAO;
	private CategoriesDAO categoriesDAO;
	private NewsDAO newsDAO;

	@Override
	public void init() {
		usersDAO = new UsersDAOImpl();
		categoriesDAO = new CategoriesDAOImpl();
		newsDAO = new NewsDAOImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		// Lấy danh sách tác giả để đổ vào select box
		List<Users> users = usersDAO.findAll();
		req.setAttribute("users", users);

		// Lấy danh sách loại tin để đổ vào select box
		List<Categories> categories = categoriesDAO.findAll();
		req.setAttribute("categories", categories);

		registerDateConverter();

		// Tạo đối tượng News từ request parameters
		News form = new News();
		try {
			BeanUtils.populate(form, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// Xử lý các hành động dựa trên đường dẫn URL
		if (path.contains("index")) {
			listNews(req, resp);
		} else if (path.contains("edit")) {
			String idStr = req.getPathInfo();
			if (idStr != null && idStr.length() > 1) {
				int id = Integer.parseInt(idStr.substring(1));
				News nws = newsDAO.findById(id);
				req.setAttribute("item", nws);
			}
			listNews(req, resp);
		} else if (path.contains("create")) {
			createNews(req, resp, form);
		} else if (path.contains("update")) {
			updateNews(req, resp, form);
		} else if (path.contains("delete")) {
			deleteNews(req, resp, form);
		} else if (path.contains("reset")) {
			resetForm(req, resp);
		} else {
			listNews(req, resp);
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

	private void listNews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<News> list = newsDAO.findAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/news_management.jsp").forward(req, resp);
	}

	private void createNews(HttpServletRequest req, HttpServletResponse resp, News form)
			throws ServletException, IOException {
		// Xử lý upload hình ảnh
		byte[] imageData = getFileData(req);
		form.setImage(imageData);
		if (form.getCategoryId() == 0) {
			req.setAttribute("error", "Please select a category.");
			listNews(req, resp);
			return;
		}
		if (form.getAuthor() == null) {
			req.setAttribute("error", "Please select an author.");
			listNews(req, resp);
			return;
		}
		newsDAO.create(form);
		listNews(req, resp);
	}

	private void updateNews(HttpServletRequest req, HttpServletResponse resp, News form)
			throws ServletException, IOException {
		// Xử lý upload hình ảnh nếu có
		byte[] imageData = getFileData(req);
		if (imageData != null && !(imageData.length == 0)) {
			form.setImage(imageData);
		} else {
			// Nếu không upload hình ảnh mới, giữ nguyên hình ảnh cũ
			News existing = newsDAO.findById(form.getId());
			form.setImage(existing.getImage());
		}
		if (form.getCategoryId() == 0) {
			News exist = newsDAO.findById(form.getId());
			if (exist != null) {
				form.setCategoryId(exist.getCategoryId());
			}
		}
		if (form.getAuthor() == null) {
			News exist = newsDAO.findById(form.getId());
			if (exist != null) {
				form.setAuthor(exist.getAuthor());
			}
		}

		newsDAO.update(form);
		listNews(req, resp);
	}
	
	private void deleteNews(HttpServletRequest req, HttpServletResponse resp, News form)
			throws ServletException, IOException {
		newsDAO.deleteById(form.getId());
		listNews(req, resp);
	}
	
	private void resetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("item", new News());
		listNews(req, resp);
	}
	
	/**
	 * Xử lý upload hình ảnh từ form
	 * 
	 * @param req
	 * @return file ảnh
	 * @throws IOException
	 * @throws ServletException
	 */
	private byte[] getFileData(HttpServletRequest req) throws IOException, ServletException {
		Part filePart = req.getPart("image");
	    if (filePart != null && filePart.getSize() > 0) {
	        byte[] fileData = new byte[(int) filePart.getSize()];
	        filePart.getInputStream().read(fileData);
	        return fileData;
	    }
	    return null;
	}
}
