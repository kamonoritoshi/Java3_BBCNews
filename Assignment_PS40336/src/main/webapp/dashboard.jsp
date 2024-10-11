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
	<style>
		iframe {
			position: absolute;
			left: 25px;
			bottom: 70px;			
		}
	</style>
</head>
<body>
	<div class="header">
        CÔNG CỤ QUẢN TRỊ TIN TỨC
    </div>

    <div class="navbar">
        <a href="${pageContext.request.contextPath}/home" target="contentFrame">Trang chủ</a> |
        <a href="${pageContext.request.contextPath}/news/index" target="contentFrame">Tin tức</a> 
        <c:if test="${sessionScope.role == 'admin'}">
            | <a href="${pageContext.request.contextPath}/categories/index" target="contentFrame">Loại tin</a> |
            <a href="${pageContext.request.contextPath}/users/index" target="contentFrame">Người dùng</a> |
            <a href="${pageContext.request.contextPath}/newsletters/index" target="contentFrame">Newsletter</a>
        </c:if>
        | <a href="logout">Đăng xuất</a>
    </div>

    <div class="content">
        <iframe src="${pageContext.request.contextPath}/home" width="1450" height="570" frameborder="0" name="contentFrame"></iframe>
    </div>

    <div class="footer">
        Chào mừng <c:out value="${sessionScope.username}" default="Họ và tên"/> đến với Trang Quản Trị.
    </div>
</body>
</html>