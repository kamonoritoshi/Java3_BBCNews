<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
	<!-- Phần nội dung chính -->
	<c:if test="${not empty newsList}">
		<c:forEach var="news" items="${newsList}">
			<div class="news-item">
				<div class="news-image">
					<img alt="News Image" src="${pageContext.request.contextPath}/image?id=${news.id}">
				</div>
				<div class="news-info">
					<a href="${pageContext.request.contextPath}/newsDetail.jsp?id=${news.id}" class="news-title">${news.title}</a>
                    <p class="news-meta"><fmt:formatDate value="${news.postedDate}" pattern="dd-MM-yyyy" /> | ${news.authorName}</p>
				</div>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${empty newsList}">
		<p>Không có bản tin nào để hiển thị</p>
	</c:if>
</body>
</html>