<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Công cụ quản trị tin tức - BBC News</title>
	<link rel="stylesheet" type="text/css" href="css/dashboard.css">
</head>
<body>
	<div class="header">
        CÔNG CỤ QUẢN TRỊ TIN TỨC
    </div>

    <div class="navbar">
        <a href="#">Trang chủ</a> |
        <a href="#">Tin tức</a> 
        <c:if test="${sessionScope.role == 'admin'}">
            | <a href="#">Loại tin</a> |
            <a href="#">Người dùng</a> |
            <a href="#">Newsletter</a>
        </c:if>
        | <a href="logout">Đăng xuất</a>
    </div>

    <div class="content">
        Chào mừng <c:out value="${sessionScope.username}" /> đến với Dashboard.
    </div>

    <div class="footer">
        Welcome <c:out value="${sessionScope.username}" default="Họ và tên"/>
    </div>
</body>
</html>