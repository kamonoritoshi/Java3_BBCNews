package poly.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.NewsDAO;
import poly.dao.NewsDAOImpl;
import poly.entity.News;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class NewsCategoryServlet
 */
@WebServlet("/news/category")
public class NewsCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;
	
	@Override
	public void init() throws ServletException {
		newsDAO = new NewsDAOImpl();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String categoryName = req.getParameter("name");
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            // Truy vấn danh sách bản tin theo thể loại
            List<News> newsList = newsDAO.findByCategory(categoryName);
            req.setAttribute("newsList", newsList);
        } else {
            req.setAttribute("error", "Không xác định được thể loại.");
        }
        // Chuyển tiếp đến JSP để hiển thị
        req.getRequestDispatcher("/trangchu.jsp").forward(req, resp);
	}

}
