<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${!type.equals('delete') }">
	<a href="home?type=class">Danh sách lớp học</a>

</c:if>
<div class="card w-50 bg-secondary mx-auto text-light">
	<div class="card-body px-3">
		<h2 class="card-title text-center">${classRoom.NameClass}</h2>

		<div class="d-flex flex-column align-item-center mt-3">
			<p class="text-center h4 mb-3">Sinh viên</p>
			<table class="table table-striped table-hover m-0">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Tên sinh viên</th>
						<th scope="col">Giới tính</th>
						<th scope="col">Ngày sinh</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${students}">
						<tr class="">
							<td class="align-middle"><a href="student?id=${item.Id}"
								class="text-light">${item.FirstName } ${item.LastName}</a></td>
							<td class="align-middle">${item.Gender eq "Male" ? "Nam":"Nữ" }</td>
							<fmt:parseDate value="${item.Dob}" pattern="yyyy-MM-dd HH:mm:ss"
								var="myDate" />
							<fmt:formatDate value="${myDate}" var="Dob" pattern="yyyy-MM-dd" />
							<td class="align-middle">${Dob}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="d-flex flex-column align-item-center mt-3">
			<p class="text-center h4 mb-3">Môn học</p>
			<table class="table table-striped table-hover m-0">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Tên môn học</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${subjects}">
						<tr class="">
							<td class="align-middle">${item.NameSubject }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${!type.equals('delete') }">
			<div class="d-flex justify-content-center align-item-center mt-3">
				<a href="class?type=edit&id=${classRoom.Id }"
					class="btn btn-primary mr-3">Sửa</a> <a
					href="class?type=delete&id=${classRoom.Id }" class="btn btn-danger">Xóa</a>
			</div>
		</c:if>


	</div>
</div>