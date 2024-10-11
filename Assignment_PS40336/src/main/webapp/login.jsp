<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập - Công cụ quản trị tin tức</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="login-container">
		<div class="navbar">
			<!-- Thanh menu ngang -->
			<a href="index.jsp">Trang chủ</a> | <a href="#">Văn hóa</a> | <a
				href="#">Pháp luật</a> | <a href="#">Thể thao</a> | <a
				href="login.jsp">Đăng Nhập</a>
		</div>
		<h2>Đăng Nhập</h2>
		<form action="login" method="post">
			<div class="form-group">
				<label for="username">Tên đăng nhập:</label> <input type="text"
					id="username" name="username" required
					value="<%=request.getAttribute("username") != null ? request.getAttribute("username") : ""%>">
			</div>
			<div class="form-group">
				<label for="password">Mật khẩu:</label> <input type="password"
					id="password" name="password" required
					value="<%=request.getAttribute("password") != null ? request.getAttribute("password") : ""%>">
			</div>
			<div class="form-group">
				<input type="checkbox" id="remember" name="remember"
					<%=request.getAttribute("remember") != null ? "checked" : ""%>>
				<label for="remember">Remember Me</label>
			</div>
			<div class="form-group">
				<button type="submit">Đăng nhập</button>
			</div>
			<div class="error-message">
				<%=request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : ""%>
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>