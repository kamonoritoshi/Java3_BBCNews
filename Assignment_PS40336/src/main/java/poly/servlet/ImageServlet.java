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
import java.io.OutputStream;

import java.io.IOException;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NewsDAO newsDAO; 
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() throws ServletException {
		newsDAO = new NewsDAOImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
        if (id != 0) {
            News news = newsDAO.findById(id);
            if (news != null && news.getImage() != null) {
                resp.setContentType("image/jpeg");
                resp.setHeader("Cache-Control", "public, max-age=86400");
                OutputStream out = resp.getOutputStream();
                out.write(news.getImage());
                out.close();
            } else {
                System.out.println("Không tìm thấy ảnh"); // Trả về lỗi 404 nếu không tìm thấy ảnh
            }
        } else {
        	System.out.println("Thiếu tham số id"); // 400 nếu thiếu tham số id
        }
	}
    
	
}
