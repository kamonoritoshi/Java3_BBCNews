<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ - BBC News</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="navbar">
			<!-- Thanh menu ngang -->
			<a href="index.jsp">Trang chủ</a> | <a href="#">Văn hóa</a> | <a
				href="#">Pháp luật</a> | <a href="#">Thể thao</a> | <a href="#">...</a>
		</div>

		<div class="content">
			<!-- Phần nội dung chính -->
			<div class="news-image">
				<img alt="Ảnh" src="image/image1.webp">
			</div>
			<div class="news-info">
				<a href="newsDetail.jsp?id=1" class="news-title">Tổng Bí thư,
					Chủ tịch nước Tô Lâm đặt vòng hoa tại Đài tưởng niệm Anh hùng dân
					tộc Cuba Jose Marti</a>
				<p class="news-summary">Trong khuôn khổ chuyến thăm cấp Nhà nước
					tới Cuba, chiều 26/9 (giờ địa phương), Tổng Bí thư, Chủ tịch nước
					Tô Lâm và Phu nhân cùng đoàn đại biểu cấp cao Việt Nam đã tới đặt
					vòng hoa tại Tượng đài vị Anh hùng dân tộc Cuba Jose Marti.</p>
				<p class="news-meta">27/09/2024 | TTXVN</p>
			</div>
			<div class="news-image">
				<img alt="Ảnh" src="image/image1.webp">
			</div>
			<div class="news-info">
				<a href="newsDetail.jsp?id=1" class="news-title">Tiêu đề bản tin
					2</a>
				<p class="news-summary">Trích lấy phần đầu (số ký tự phù hợp)
					của nội dung bản tin</p>
				<p class="news-meta">Ngày đăng | Tác giả</p>
			</div>
			<div class="news-image">
				<img alt="Ảnh" src="image/image1.webp">
			</div>
			<div class="news-info">
				<a href="newsDetail.jsp?id=1" class="news-title">Tiêu đề bản tin
					3</a>
				<p class="news-summary">Trích lấy phần đầu (số ký tự phù hợp)
					của nội dung bản tin</p>
				<p class="news-meta">Ngày đăng | Tác giả</p>
			</div>
		</div>

		<div class="sidebar">
			<!-- Sidebar bên phải -->
			<div class="sidebar-item highlight-news">
				<h3>5 bản tin được xem nhiều</h3>
			</div>
			<div class="sidebar-item latest-news">
				<h3>5 bản tin mới nhất</h3>
			</div>
			<div class="sidebar-item viewed-news">
				<h3>5 bản tin đã bạn đã xem</h3>
			</div>
			<div class="sidebar-item newsletter">
				<h3>Newsletter</h3>
				<input type="email" placeholder="Nhập email...">
				<button>Đăng ký</button>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>