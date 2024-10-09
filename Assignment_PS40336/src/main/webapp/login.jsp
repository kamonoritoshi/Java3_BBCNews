<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Đăng nhập - Công cụ quản trị tin tức</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<div class="login-container">
		<h2>Đăng Nhập</h2>
		<form action="login" method="post">
			<div class="form-group">
				<label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required
                    value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>">
			</div>
			<div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" required 
                	value="<%= request.getAttribute("password") != null ? request.getAttribute("password") : "" %>">
            </div>
            <div class="form-group">
                <input type="checkbox" id="remember" name="remember" 
                    <%= request.getAttribute("remember") != null ? "checked" : "" %>>
                <label for="remember">Remember Me</label>
            </div>
            <div class="form-group">
                <button type="submit">Đăng nhập</button>
            </div>
            <div class="error-message">
                <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
            </div>
		</form>
	</div>
</body>
</html>