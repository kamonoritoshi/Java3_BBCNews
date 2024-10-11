<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Danh sách bản tin - BBC News</title>
	<link rel="stylesheet" type="text/css" href="css/category.css">
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<h1>Bản tin theo loại tin</h1>
		<!-- Hiển thị danh sách các bản tin theo loại tin -->
		<div class="news-list">
			<div class="news-item">
				<div class="news-image">
					<img alt="Ảnh" src="image/image1.webp">
				</div>
				<div class="news-info">
					<a href="newsDetail.jsp?id=1" class="news-title">Tiêu đề bản tin 1</a>
					<p class="news-summary">Trích lấy phần đầu (số ký tự phù hợp) của nội dung bản tin</p>
					<p class="news-meta">Ngày đăng | Tác giả</p>
				</div>
			</div>
			<div class="news-item">
				<div class="news-image">
					<img alt="Ảnh" src="image/image1.webp">
				</div>
				<div class="news-info">
					<a href="newsDetail.jsp?id=2" class="news-title">Tiêu đề bản tin 2</a>
					<p class="news-summary">Trích lấy phần đầu (số ký tự phù hợp) của nội dung bản tin</p>
					<p class="news-meta">Ngày đăng | Tác giả</p>
				</div>
			</div>
			<div class="news-item">
				<div class="news-image">
					<img alt="Ảnh" src="image/image1.webp">
				</div>
				<div class="news-info">
					<a href="newsDetail.jsp?id=3" class="news-title">Tiêu đề bản tin 3</a>
					<p class="news-summary">Trích lấy phần đầu (số ký tự phù hợp) của nội dung bản tin</p>
					<p class="news-meta">Ngày đăng | Tác giả</p>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>