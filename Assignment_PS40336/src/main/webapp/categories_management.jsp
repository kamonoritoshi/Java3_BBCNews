<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/management.css">
</head>
<body>
	<c:url var="path" value="/categories" />
	<!-- Form -->
	<form method="post">
		<h1>Quản Lý Loại Tin</h1>
		<label>Id:</label><br> <input type="text" name="id" value="${item.id}"><br>
		<label>Tên loại:</label><br> <input type="text" name="name" value="${item.name}"><br>
		<label>Mô tả:</label><br>
		<textarea name="description" rows="3">${item.description}</textarea>
		<hr>
		<button formaction="${path}/create">Thêm</button>
		<button formaction="${path}/update">Sửa</button>
		<button formaction="${path}/delete">Xoá</button>
		<button formaction="${path}/reset">Mới</button>
	</form>
	<hr>
	<!-- Table -->
	<table border="1" style="width: 100%">
		<thead>
			<tr>
				<th>STT</th>
				<th>Id</th>
				<th>Tên loại</th>
				<th>Mô tả</th>
				<th>Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="d" items="${list}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${d.id}</td>
					<td>${d.name}</td>
					<td>${d.description}</td>
					<td><a href="${path}/edit/${d.id}">Chỉnh sửa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>