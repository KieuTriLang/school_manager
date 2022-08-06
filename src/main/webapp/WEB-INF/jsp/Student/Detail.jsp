<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${!type.equals('delete') }">
	<a href="home?type=student">Danh sách sinh viên</a>
</c:if>
<div class="card w-50 bg-secondary mx-auto text-light">
	<div class="card-body px-3">
		<h2 class="card-title text-center">${student.FirstName }
			${student.LastName }</h2>
		<div class="d-flex justify-content-between align-item-center mt-3">
			<p>Ngày sinh</p>
			<fmt:parseDate value="${student.Dob}" pattern="yyyy-MM-dd HH:mm:ss"
				var="myDate" />
			<fmt:formatDate value="${myDate}" var="Dob" pattern="yyyy-MM-dd" />
			<p>${Dob}</p>
		</div>
		<div class="d-flex justify-content-between align-item-center mt-3">
			<p>Giới tính</p>
			<p>${student.Gender eq "Male" ? "Nam":"Nữ"}</p>
		</div>
		<div class="d-flex justify-content-between align-item-center mt-3">
			<p>Địa chỉ</p>
			<p>${student.Address}</p>
		</div>
		<div class="d-flex justify-content-between align-item-center mt-3">
			<p>Số điện thoại</p>
			<p>${student.Phone}</p>
		</div>
		<div class="d-flex justify-content-between align-item-center mt-3">
			<p>Lớp</p>
			<a href="class?id=${student.ClassId}" class="text-light">${student.NameClass}</a>
		</div>
		<div class="d-flex flex-column align-item-center mt-3">
			<p class="text-center h4 mb-3">Kết quả thi</p>
			<table class="table table-striped table-hover m-0">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Môn học</th>
						<th scope="col">Tên bài thi</th>
						<th scope="col">Điểm</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${results}">
						<tr class="">
							<th scope="col" class="align-middle">${item.NameSubject}</th>
							<td class="align-middle"><a href="result?id=${item.Id}"
								class="text-light">${item.NameTest }</a></td>
							<td class="align-middle">${item.Mark }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${!type.equals('delete') }">
			<div class="d-flex justify-content-center align-item-center mt-3">
				<a href="student?type=edit&id=${student.Id }"
					class="btn btn-primary mr-3">Sửa</a> <a
					href="student?type=delete&id=${student.Id }" class="btn btn-danger">Xóa</a>
			</div>
		</c:if>


	</div>
</div>