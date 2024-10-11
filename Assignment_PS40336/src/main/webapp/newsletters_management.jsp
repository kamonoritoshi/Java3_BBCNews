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
	<c:url var="path" value="/newsletters" />
	<!-- Form -->
	<form method="post">
		<h1>Quản Lý Đăng Ký Nhận Bản Tin</h1>
		<label>Email:</label><br> <input type="email" name="email" value="${item.email}" pattern="^[a-zA-Z0-9._%+-]+@(gmail\.com|yahoo\.com|outlook\.com)$" required /><br><br>
		<label>Trạng thái:</label><br>
        <div class="radio-group">
            <label>
                <input type="radio" name="status" value="Active" 
                    <c:if test="${item.status}">
                        checked
                    </c:if>
                /> Hoạt động
            </label>
            <label>
                <input type="radio" name="status" value="Inactive" 
                    <c:if test="${not item.status}">
                        checked
                    </c:if>
                /> Không hoạt động
            </label>
        </div>
        <br><br>
        
        <hr>
        <button type="submit" formaction="${path}/create">Thêm</button>
        <button type="submit" formaction="${path}/update">Sửa</button>
        <button type="submit" formaction="${path}/delete">Xoá</button>
        <button type="reset">Xoá</button>
	</form>
	<hr>
	
	<!-- Table -->
	<table>
		<thead>
			<tr>
				<th>STT</th>
				<th>Email</th>
				<th>Trạng thái</th>
				<th>Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${list}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${e.email}</td>
					<td>${e.status ? 'Hoạt động' : 'Không hoạt động'}</td>
					<td>
						<a href="${path}/edit/${e.email}">Chỉnh sửa</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>