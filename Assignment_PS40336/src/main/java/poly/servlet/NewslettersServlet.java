package poly.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.NewslettersDAO;
import poly.dao.NewslettersDAOImpl;
import poly.entity.News;
import poly.entity.Newsletters;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class NewslettersServlet
 */
@WebServlet({ "/newsletters/index", "/newsletters/edit/*", "/newsletters/create", "/newsletters/createFromIndex", "/newsletters/update",
		"/newsletters/delete", "/newsletters/reset" })
public class NewslettersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewslettersDAO newslettersDAO;

	@Override
	public void init() {
		newslettersDAO = new NewslettersDAOImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		// Tạo đối tượng Newsletters từ request parameters
		Newsletters form = new Newsletters();
		try {
			BeanUtils.populate(form, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// Xử lý các hành động dựa trên đường dẫn URL
		if (path.contains("index")) {
			listNewsletters(req, resp);
		} else if (path.contains("edit")) {
			String idStr = req.getPathInfo();
			if (idStr != null && idStr.length() > 1) {
				String email = idStr.substring(1);
				Newsletters nws = newslettersDAO.findByEmail(email);
				req.setAttribute("item", nws);
			}
			listNewsletters(req, resp);
		} else if (path.contains("create")) {
			createNewsletter(req, resp, form);
		} else if (path.contains("update")) {
			updateNewsletter(req, resp, form);
		} else if (path.contains("delete")) {
			deleteNewsletter(req, resp, form);
		} else if (path.contains("reset")) {
			resetForm(req, resp);
		} else {
			listNewsletters(req, resp);
		}
	}
	
	private void listNewsletters(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Newsletters> list = newslettersDAO.findAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/newsletters_management.jsp").forward(req, resp);
	}
	
	private void createNewsletter(HttpServletRequest req, HttpServletResponse resp, Newsletters form)
			throws ServletException, IOException {
		newslettersDAO.create(form);
		listNewsletters(req, resp);
	}
	
	private void updateNewsletter(HttpServletRequest req, HttpServletResponse resp, Newsletters form)
			throws ServletException, IOException {
		newslettersDAO.update(form);
		listNewsletters(req, resp);
	}
	
	private void deleteNewsletter(HttpServletRequest req, HttpServletResponse resp, Newsletters form)
			throws ServletException, IOException {
		newslettersDAO.deleteByEmail(form.getEmail());
		listNewsletters(req, resp);
	}
	
	private void resetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("item", new Newsletters());
		listNewsletters(req, resp);
	}
}
