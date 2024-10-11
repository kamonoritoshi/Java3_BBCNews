package poly.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.dao.UsersDAO;
import poly.dao.UsersDAOImpl;
import poly.entity.Users;

import java.io.IOException;
import java.util.Base64;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private UsersDAO userDAO;

	@Override
	public void init() throws ServletException {
		userDAO = new UsersDAOImpl();
	}

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					String encoded = cookie.getValue();
					byte[] bytes = Base64.getDecoder().decode(encoded);
					String[] userInfo = new String(bytes).split(",");
					request.setAttribute("username", userInfo[0]);
					request.setAttribute("password", userInfo[1]);
					request.setAttribute("remember", userInfo[2]);
				}
			}
		}
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        
        // Ghi log giá trị nhận được
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Remember: " + remember);
        
        // Xác thực người dùng từ cơ sở dữ liệu thông qua UserDAO
        Users user = userDAO.findById(username);
        
        if (user != null && username.equals(user.getId()) && password.equals(user.getPassword()) && "active".equalsIgnoreCase(user.getStatus())) {
        	HttpSession session = request.getSession();
            session.setAttribute("username", user.getFullname());
            session.setAttribute("role", user.getRole());
            
            if (remember != null) {
				byte[] bytes = (username + "," + password + "," + remember).getBytes();
				String userInfo = Base64.getEncoder().encodeToString(bytes);
				Cookie cookie = new Cookie("user", userInfo);
				cookie.setMaxAge(7 * 24 * 60 * 60);
				cookie.setPath("/");
				response.addCookie(cookie);
				System.out.println("Cookie set: " + userInfo); // Ghi log Cookie
			} else {
				Cookie cookie = new Cookie("user", "");
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
				System.out.println("Cookie cleared"); // Ghi log xóa Cookie
			}
            
         // Chuyển hướng đến dashboard hoặc trang chính
            response.sendRedirect("dashboard.jsp");
        } else {
        	// Đăng nhập thất bại
            request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng, hoặc tài khoản không hoạt động.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
	}

}
