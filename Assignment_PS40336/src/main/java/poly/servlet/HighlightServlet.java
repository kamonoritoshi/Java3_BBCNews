package poly.servlet;

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
 * Servlet implementation class HighlightServlet
 */
@WebServlet("/news/highlight")
public class HighlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;
	
	@Override
    public void init() throws ServletException {
        newsDAO = new NewsDAOImpl();
    }
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<News> topNews = newsDAO.findTopViewed();
        req.setAttribute("newsList", topNews);
        req.getRequestDispatcher("/trangchu.jsp").forward(req, resp);
	}
}
