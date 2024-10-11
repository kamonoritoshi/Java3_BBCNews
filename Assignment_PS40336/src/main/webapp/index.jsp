<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ - BBC News</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="navbar">
			<!-- Thanh menu ngang -->
			<a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a> | 
			<a href="${pageContext.request.contextPath}/news/category?name=Politics" target="contentFrame">Chính trị</a> | 
			<a href="${pageContext.request.contextPath}/news/category?name=Economy" target="contentFrame">Kinh tế</a> | 
			<a href="${pageContext.request.contextPath}/news/category?name=Sports" target="contentFrame">Thể thao</a> | 
			<a href="${pageContext.request.contextPath}/login.jsp">Đăng nhập</a>
		</div>

		<div class="content">
			<iframe src="${pageContext.request.contextPath}/home" width="1200" height="570" frameborder="0" name="contentFrame"></iframe>
		</div>

		<div class="sidebar">
			<!-- Sidebar bên phải -->
			<div class="sidebar-item highlight-news">
				<a href="${pageContext.request.contextPath}/news/highlight" target="contentFrame">5 bản tin được xem nhiều</a>
			</div>
			<div class="sidebar-item latest-news">
				<a href="${pageContext.request.contextPath}/news/latest" target="contentFrame">5 bản tin mới nhất</a>
			</div>
			<div class="sidebar-item viewed-news">
				<h3>5 tin bạn đã xem</h3>
			</div>
			<div class="sidebar-item newsletter">
				<h3>Newsletter</h3>
				<form action="${pageContext.request.contextPath}/newsletters/create" method="post">
			        <input type="email" name="email" placeholder="Nhập email..." required>
			        <button type="submit">Đăng ký</button>
			    </form>
			    <c:if test="${not empty message}">
			        <div class="alert alert-success">${message}</div>
			    </c:if>
			    <c:if test="${not empty error}">
			        <div class="alert alert-danger">${error}</div>
			    </c:if>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>