<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<body>
	<header class="site-header">
		<div class="logo-container">
			<img alt="BBC News Logo" src="image/BBC_News-Logo.wine.svg"
				class="logo">
		</div>
		<div class="header-info">
			<h1>Báo Điện tử BBC News</h1>
			<div class="sub-info">
				<c:set var="now" value="<%=new java.util.Date()%>" />
				<span> <fmt:formatDate value="${now}" pattern="EEEE, dd/MM/yyyy" /></span>
			</div>
		</div>
	</header>
</body>
<hr>