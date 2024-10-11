<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/management.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
</head>
<body>
	<c:url var="path" value="/users" />
	<!-- Form -->
	<form method="post">
		<h1>Quản Lý Người Dùng</h1>
		<label>ID:</label><br> <input type="text" name="id" value="${item.id}" required /><br><br>
		<label>Mật khẩu:</label><br> <input type="password" name="password" value="${item.password}" required /><br><br>
		<label>Họ và tên:</label><br> <input type="text" name="fullname" value="${item.fullname}" required /><br><br>
		<label>Ngày sinh:</label><br> <input type="date" name="birthday" value="${item.birthday}" class="flatpickr-input" placeholder="dd-MM-yyyy" /><br><br>
		<label>Giới tính:</label><br>
        <div class="radio-group">
            <label>
                <input type="radio" name="gender" value="male" 
                    <c:if test="${item.gender}">
                        checked
                    </c:if>
                /> Nam
            </label>
            <label>
                <input type="radio" name="gender" value="female" 
                    <c:if test="${not item.gender}">
                        checked
                    </c:if>
                /> Nữ
            </label>
        </div>
        <br><br>
        <label>Số điện thoại:</label><br> <input type="tel" name="phoneNumber" value="${item.phoneNumber}" pattern="[0-9]{10}" required /><br><br>
        <label>Email:</label><br> <input type="email" name="email" value="${item.email}" pattern="^[a-zA-Z0-9._%+-]+@(gmail\.com|yahoo\.com|outlook\.com)$" required /><br><br>
        <label>Chức vụ:</label><br>
        <div class="radio-group">
            <label>
                <input type="radio" name="role" value="admin" 
                    <c:if test="${item.role  == 'admin'}">
                        checked
                    </c:if>
                /> Quản trị viên
            </label>
            <label>
                <input type="radio" name="role" value="reporter" 
                    <c:if test="${item.role == 'reporter'}">
                        checked
                    </c:if>
                /> Phóng viên
            </label>
        </div>
        <br><br>
        <label>Trạng thái:</label><br>
        <div class="radio-group">
            <label>
                <input type="radio" name="status" value="Active" 
                    <c:if test="${item.status == 'Active'}">
                        checked
                    </c:if>
                /> Hoạt động
            </label>
            <label>
                <input type="radio" name="status" value="Inactive" 
                    <c:if test="${item.status == 'Inactive'}">
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
        <button type="reset">Mới</button>
	</form>
	<hr>
	
	<!-- Table -->
	<table>
		<thead>
			<tr>
				<th>STT</th>
				<th>ID</th>
				<th>Mật khẩu</th>
				<th>Họ và tên</th>
				<th>Ngày sinh</th>
				<th>Giới tính</th>
				<th>Số điện thoại</th>
				<th>Email</th>
				<th>Chức vụ</th>
				<th>Trạng thái</th>
				<th>Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${list}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${e.id}</td>
					<td>${e.password}</td>
					<td>${e.fullname}</td>
					<td><fmt:formatDate value="${e.birthday}" pattern="dd-MM-yyyy" /></td>
					<td>${e.gender ? 'Nam' : 'Nữ'}</td>
					<td>${e.phoneNumber}</td>
					<td>${e.email}</td>
					<td>
						<c:choose>
						    <c:when test="${e.role == 'admin'}">
						        Quản trị viên
						    </c:when>
						    <c:when test="${e.role == 'reporter'}">
						        Phóng viên
						    </c:when>
						    <c:otherwise>
						        Không xác định
						    </c:otherwise>
						</c:choose>
					</td>
					<td>${e.status == 'Active' ? 'Hoạt động' : 'Không hoạt động'}</td>
					<td>
                        <a href="${path}/edit/${e.id}">Chỉnh sửa</a>
                    </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<script>
        flatpickr(".flatpickr-input", {
            dateFormat: "d-m-Y", // Định dạng dd-MM-yyyy
            altInput: true,
            altFormat: "d-m-Y",
            allowInput: true
        });
    </script>
</body>
</html>