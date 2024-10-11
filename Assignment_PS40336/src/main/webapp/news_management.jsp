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
	<c:url var="path" value="/news" />
	<!-- Form -->
	<form method="post" enctype="multipart/form-data">
		<h1>Quản Lý Bản Tin</h1>
		<label>ID:</label><br> <input type="text" name="id" value="${item.id}" required /><br><br>
		<label>Tiêu đề:</label><br> <input type="text" name="title" value="${item.title}" required/><br><br>
		<label>Nội dung:</label><br>
		<textarea name="content" rows="10">${item.content}</textarea><br><br>
		<label>Hình ảnh:</label><br>
		<div class="file-input-wrapper">
			<span class="file-input-label">Chọn hình ảnh</span>
			<input type="file" name="image" accept="image/*" />
		</div>
		<c:if test="${not empty item.image}">
            <br><br>
            <img src="${pageContext.request.contextPath}/image?id=${item.id}" alt="News Image" class="form-image"/>
        </c:if>
        <br><br>
        <label>Ngày đăng:</label><br> <input type="date" name="postedDate" value="${item.postedDate}" class="flatpickr-input" placeholder="dd-MM-yyyy" /><br><br>
        <label>Tác giả:</label><br>
        <select name="author" required>
        	<option value="">-- Chọn tác giả --</option>
        	<c:forEach var="user" items="${users}">
        		<option value="${user.id}"
        			<c:if test="${user.id == item.author}">
        				selected
        			</c:if>
        		>${user.fullname}</option>
        	</c:forEach>
        </select>
        <br><br>
        <label>Lượt xem:</label><br> <input type="number" name="viewCount" step="1" value="${item.viewCount}" readonly />
        <label>Loại tin:</label>
        <select name="categoryId" required>
        	<option value="">-- Chọn loại tin --</option>
        	<c:forEach var="ctgr" items="${categories}">
        		<option value="${ctgr.id}"
        			<c:if test="${ctgr.id == item.categoryId}">
        				selected
        			</c:if>
        		>${ctgr.name}</option>
        	</c:forEach>
        </select>
        <br><br>
        <label>Lên trang chủ:</label><br>
        <div class="radio-group">
            <label>
                <input type="radio" name="home" value="yes" 
                    <c:if test="${item.home}">
                        checked
                    </c:if>
                /> Có
            </label>
            <label>
                <input type="radio" name="home" value="no" 
                    <c:if test="${not item.home}">
                        checked
                    </c:if>
                /> Không
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
				<th>Tiêu đề</th>
				<th>Nội dung</th>
				<th>Hình ảnh</th>
				<th>Ngày đăng</th>
				<th>Tác giả</th>
				<th>Lượt xem</th>
				<th>Loại tin</th>
				<th>Lên trang chủ</th>
				<th>Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${list}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${e.id}</td>
					<td>${e.title}</td>
					<td>${e.content}</td>
					<td>
						<c:choose>
                            <c:when test="${not empty e.image}">
                                <img src="${pageContext.request.contextPath}/image?id=${e.id}" alt="News Image" class="table-image"/>
                            </c:when>
                            <c:otherwise>
                                No Image
                            </c:otherwise>
                        </c:choose>
					</td>
					<td><fmt:formatDate value="${e.postedDate}" pattern="dd-MM-yyyy" /></td>
					<td>${e.authorName}</td>
					<td>${e.viewCount}</td>
					<td>${e.categoryName}</td>
					<td>${e.home ? 'Có' : 'Không'}</td>
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