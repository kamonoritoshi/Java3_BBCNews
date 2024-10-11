<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="poly.entity.News" %>
<%@ page import="poly.dao.NewsDAO" %>
<%@ page import="poly.dao.NewsDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> - BBC News</title>
<link rel="stylesheet" type="text/css" href="css/newsDetail.css">
</head>
<body>
	<%
		// Lấy ID từ tham số URL
	    String idParam = request.getParameter("id");
	    int newsId = Integer.parseInt(idParam); // Chuyển đổi sang kiểu int
	    
	 	// Lấy thông tin bản tin từ cơ sở dữ liệu
        NewsDAO newsDAO = new NewsDAOImpl();
        News nws = newsDAO.findById(newsId);
        
        if (nws != null) {
	%> 

	<div class="news-detail">
		<div class="news-detail-image">
			<img src="<%= request.getContextPath() + "/image?id=" + nws.getId() %>" alt="News Image">
		</div>
		<div class="news-detail-content">
			<h2 class="news-title"><%= nws.getTitle() %></h2>
			<p class="news-content">
				<%= nws.getContent() %>
			</p>
			<p class="news-meta"><%= nws.getPostedDate() %> | Tác giả: <%= nws.getAuthorName() %></p>
		</div>
	</div>

	<!-- Related News Section -->
	<div class="related-news">
		<h2>Tin cùng loại</h2>
		<ul>
			<li><a href="#">Tin cùng loại 1</a></li>
			<li><a href="#">Tin cùng loại 2</a></li>
			<li><a href="#">Tin cùng loại 3</a></li>
		</ul>
	</div>
	<% } else { %>
	<div class="news-detail">
		<h2>Bản tin không tồn tại</h2>
	</div>
	<% } %>
</body>
</html>