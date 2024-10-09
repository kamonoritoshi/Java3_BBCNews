<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết bản tin - BBC News</title>
<link rel="stylesheet" type="text/css" href="css/newsDetail.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="navbar">
			<!-- Thanh menu ngang -->
			<a href="index.jsp">Trang chủ</a> | <a href="#">Văn hóa</a> | <a
				href="#">Pháp luật</a> | <a href="#">Thể thao</a> | <a href="#">...</a>
		</div>
	</div>
	<div class="news-detail">
		<div class="news-detail-image">
			<img src="image/image1.webp" alt="News Image">
		</div>
		<div class="news-detail-content">
			<h2 class="news-title">Tổng Bí thư, Chủ tịch nước Tô Lâm đặt
				vòng hoa tại Đài tưởng niệm Anh hùng dân tộc Cuba Jose Marti</h2>
			<p class="news-content">
				Cùng dự Lễ tưởng niệm có đồng chí Roberto Morales Ojeda, Ủy viên Bộ
				Chính trị, Thường trực Ban Bí thư Trung ương Đảng Cộng sản Cuba, các
				thành viên trong đoàn đại biểu cấp cao Việt Nam và các đồng chí lãnh
				đạo một số bộ ban ngành Cuba.<br> Lễ tưởng niệm diễn ra trang
				trọng với sự có mặt của Đội Danh dự thuộc các lực lượng vũ trang
				cách mạng Cuba. Sau khi thực hiện nghi lễ chào cờ hai nước, trong
				nền nhạc hùng tráng, vòng hoa tưởng niệm được các chiến sỹ trong đội
				nghi lễ đưa vào vị trí trang trọng dưới chân Tượng đài. Tiếp đó,
				Tổng Bí thư, Chủ tịch nước và Phu nhân được mời lên vị trí danh dự
				và dành một phút mặc niệm tưởng nhớ vị Anh hùng dân tộc Cuba, người
				đã gieo mầm cho tình hữu nghị giữa hai đất nước anh em ở cách xa
				nhau nửa địa cầu khi ngay từ cuối thế kỷ 19 đã có bài bút ký nổi
				tiếng "Một cuộc dạo chơi trên đất của người An Nam'' đăng trên tạp
				chí Tuổi Vàng, trong đó ca ngợi truyền thống yêu nước và chống ngoại
				xâm bất khuất của nhân dân Việt Nam.<br> Sau đó, Tổng Bí thư,
				Chủ tịch nước và Phu nhân đã vào tham quan Nhà tưởng niệm Jose Marti
				trong khuôn viên Quảng trường Cách mạng và được nghe giới thiệu về
				thân thế và sự nghiệp của vị Anh hùng lỗi lạc của dân tộc Cuba. Tại
				đây, các vị khách quý Việt Nam cũng đã được chứng kiến các hoạt động
				nghệ thuật đặc sắc của Cuba do các cháu thiếu nhi trình diễn.<br>
				Đài tưởng niệm José Marti nằm trên khu đồi ở trung tâm thành phố La
				Habana. Công trình bao gồm một tòa tháp hình ngôi sao 5 cánh. Chính
				giữa Đài tưởng niệm là bức tượng Marti cỡ lớn, được bao quanh bởi 6
				cột và các khu vườn. Tòa tháp trong Khu tưởng niệm cao 109 m, được
				thiết kế bởi một nhóm kiến trúc sư do ông Raoul Otero de Galarraga
				làm trưởng nhóm. Đây cũng là địa điểm du lịch nổi tiếng ở thủ đô La
				Habana. Khách du lịch có thể lên Đài tưởng niệm và ngắm nhìn toàn
				cảnh thành phố biển La Habana tuyệt đẹp.
			</p>
			<p class="news-meta">Ngày đăng: 28/09/2024 | Tác giả: John Doe</p>
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
	<%@ include file="footer.jsp"%>
</body>
</html>