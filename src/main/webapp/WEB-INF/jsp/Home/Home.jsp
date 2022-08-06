<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${type eq null }">
	<div class="d-flex justify-content-around">
		<a href="home?type=student" class="btn btn-primary">Sinh viên</a> <a
			href="home?type=class" class="btn btn-primary">Lớp học</a> <a
			href="home?type=subject" class="btn btn-primary">Môn học</a> <a
			href="home?type=result" class="btn btn-primary">Kết quả thi</a>
	</div>
</c:if>
<c:if test="${type.equals('student') }">

	<div class="container">
		<a href="home" class="">Trang chủ</a>
		<div class="d-flex justify-content-between mt-2 align-items-center">
			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Sắp xếp ${sort.equals("name") ? "(Tên)":"" }
					${sort.equals("dob") ? "(Ngày sinh)":"" }</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="home?type=student">Tất cả</a> <a
						class="dropdown-item" href="${sortUrl}&sort=name">Tên</a> <a
						class="dropdown-item" href="${sortUrl}&sort=dob">Ngày sinh</a>
					<!-- 					<a class="dropdown-item" href="#">Điểm trung bình</a> -->
				</div>
			</div>
			<nav class="navbar navbar-light bg-light">
				<form action="${searchUrl }" method="get" class="form-inline">
					<input type="hidden" name="type" value="student"> <input
						class="form-control mr-sm-2" type="search" name="search"
						placeholder="tên sinh viên . . ." aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm</button>
				</form>
			</nav>
			<a href="student?type=add" class="btn btn-info px-2 py-1">Thêm
				sinh viên</a>
		</div>
		<div
			class="row align-items-center text-center bg-dark text-light p-2  mb-2 rounded-top">
			<div class="col-3">Họ tên</div>
			<div class="col-2">Giới tính</div>
			<div class="col-2">Ngày sinh</div>
			<div class="col-2">Lớp</div>
			<div class="col-3"></div>
		</div>
		<c:forEach var="student" items="${students}">
			<div class="row align-items-center text-center border-bottom p-2">
				<div class="col-3 align-middle">${student.FirstName }
					${student.LastName }</div>
				<div class="col-2 align-middle">${student.Gender.equals('Male') ? 'Nam' : 'Nữ' }</div>
				<fmt:parseDate value="${student.Dob}" pattern="yyyy-MM-dd HH:mm:ss"
					var="myDate" />
				<fmt:formatDate value="${myDate}" var="Dob" pattern="yyyy-MM-dd" />
				<div class="col-2 align-middle">${Dob}</div>
				<div class="col-2 align-middle">${student.NameClass }</div>
				<div class="col-3 text-center align-middle">
					<a href="student?id=${student.Id}"
						class="btn btn-info text-light px-2 py-1"><small>Chi
							tiết</small></a> <a href="student?type=edit&id=${student.Id}"
						class="btn btn-primary text-light px-2 py-1"><small>Sửa</small></a>
					<a href="student?type=delete&id=${student.Id}"
						class="btn btn-danger text-light px-2 py-1"><small>Xóa</small></a>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>
<c:if test="${type.equals('class') }">

	<div class="container">
		<a href="home" class="">Trang chủ</a>
		<div class="d-flex justify-content-between mt-2 align-items-center">
			<nav class="navbar navbar-light bg-light">
				<form action="${searchUrl }" method="get" class="form-inline">
					<input type="hidden" name="type" value="class"> <input
						class="form-control mr-sm-2" type="search" name="search"
						placeholder="Tên lớp" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm</button>
				</form>
			</nav>
			<a href="class?type=add" class="btn btn-info">Thêm lớp</a>
		</div>
		<div
			class="row align-items-center text-center bg-dark text-light p-2  mb-2 rounded-top">
			<div class="col-4">Tên lớp</div>
			<div class="col-2">Số sinh viên</div>
			<div class="col-2">Số môn học</div>
			<div class="col-4"></div>
		</div>
		<c:forEach var="item" items="${classes}">
			<div class="row align-items-center text-center border-bottom p-2">
				<div class="align-middle col-4">${item.NameClass}</div>
				<div class="align-middle col-2">${item.NumberSt }</div>
				<div class="align-middle col-2">${item.NumberSj }</div>
				<div class="align-middle col-4">
					<a href="class?id=${item.Id}"
						class="btn btn-info text-light px-2 py-1"><small>Chi
							tiết</small></a> <a href="class?type=edit&id=${item.Id}"
						class="btn btn-primary text-light px-2 py-1"><small>Sửa</small></a>
					<a href="class?type=delete&id=${item.Id}"
						class="btn btn-danger text-light px-2 py-1"><small>Xóa</small></a>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>
<c:if test="${type.equals('subject') }">

	<div class="container">
		<a href="home" class="">Trang chủ</a>
		<div class="d-flex justify-content-between mt-2 align-items-center">
			<nav class="navbar navbar-light bg-light">
				<form action="${searchUrl}" class="form-inline">
					<input type="hidden" name="type" value="subject"> <input
						class="form-control mr-sm-2" type="search" name="search"
						placeholder="Tên môn học" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm</button>
				</form>
			</nav>
			<a href="subject?type=add" class="btn btn-info">Thêm môn học</a>
		</div>
		<div
			class="row align-items-center text-center bg-dark text-light p-2  mb-2 rounded-top">
			<div class="col-8">Tên môn học</div>
			<div class="col-4"></div>
		</div>
		<c:forEach var="item" items="${subjects}">
			<div class="row align-items-center text-center border-bottom p-2">
				<div class="align-middle col-8">${item.NameSubject }</div>
				<div class="align-middle col-4">
					<a href="subject?type=edit&id=${item.Id}"
						class="btn btn-primary text-light px-2 py-1"><small>Sửa</small></a>
					<a href="subject?type=delete&id=${item.Id}"
						class="btn btn-danger text-light px-2 py-1"><small>Xóa</small></a>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>
<c:if test="${type.equals('result') }">

	<div class="container">
		<a href="home" class="">Trang chủ</a>
		<div class="d-flex justify-content-between my-2 align-items-center">
			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Lớp: ${ !className.equals('') ? className : ''}</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="max-height: 280px;overflow-y: auto;">
					<a class="dropdown-item" href="${classUrl}">Tất cả</a>
					<c:forEach var="item" items="${classes }">
						<a class="dropdown-item"
							href="${classUrl}&className=${item.NameClass}">${item.NameClass} (${item.NumberTest} bài thi)</a>
					</c:forEach>
				</div>
			</div>
			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Môn: ${ !subjectName.equals('') ? subjectName : 'Môn học'}</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="max-height: 280px;overflow-y: auto;">
					<a class="dropdown-item" href="${subjectUrl}">Tất cả</a>
					<c:forEach var="item" items="${subjects}">
						<a class="dropdown-item"
							href="${subjectUrl}&subjectName=${item.NameSubject}">${item.NameSubject }</a>
					</c:forEach>
				</div>
			</div>
			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Sắp xếp ${sortName.equals("incMark") ? "(Điểm tăng)":"" }
					${sortName.equals("desMark") ? "(Điểm giảm)":"" }</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="${sortUrl}&sort=incMark">Điểm
						(tăng)</a> <a class="dropdown-item" href="${sortUrl}&sort=desMark">Điểm
						(giảm)</a>

				</div>

			</div>
			<form action="${searchUrl}" method="get" class="form-inline">
				<input type="hidden" name="type" value="result"> <input
					class="form-control mr-sm-2" type="search" name="search"
					placeholder="Tên bài thi" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm</button>
			</form>
			<div class="dropdown">
				<button class="btn btn-info dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Thêm kết quả thi</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<c:forEach var="item" items="${classes }">
						<a class="dropdown-item"
							href="result?type=add&classId=${item.Id }">${item.NameClass }</a>
					</c:forEach>
				</div>

			</div>
			<c:if test="${nameTests.size() > 0}">
				<div class="dropdown">
					<button class="btn btn-warning dropdown-toggle" type="button"
						id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">Sửa kết quả thi</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<c:forEach var="item" items="${nameTests }">
							<a class="dropdown-item"
								href="result?type=edit&classId=${item.ClassId}&subjectId=${item.SubjectId}&nameTest=${item.NameTest}">${item.NameTest }</a>
						</c:forEach>
					</div>

				</div>
			</c:if>
			<c:if test="${nameTests.size() > 0}">
				<div class="dropdown">
					<button class="btn btn-danger dropdown-toggle" type="button"
						id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">Xóa bài thi</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<c:forEach var="item" items="${nameTests }">
							<a class="dropdown-item"
								href="result?type=delete&classId=${item.ClassId}&subjectId=${item.SubjectId}&nameTest=${item.NameTest}">${item.NameTest }</a>
						</c:forEach>
					</div>

				</div>
			</c:if>
		</div>

		<div
			class="row align-items-center text-center bg-dark text-light p-2  mb-2 rounded-top">
			<div class="col-4">Tên sinh viên</div>
			<div class="col-2">Lớp</div>
			<div class="col-2">Môn học</div>
			<div class="col-2">Tên bài thi</div>
			<div class="col-2">Điểm</div>
			<div class="col-2"></div>
		</div>
		<c:forEach var="item" items="${results}">
			<div class="row align-items-center text-center border-bottom p-2">
				<div class="align-middle col-4">${item.FirstName}
					${item.LastName }</div>
				<div class="align-middle col-2">${item.NameClass }</div>
				<div class="align-middle col-2">${item.NameSubject }</div>
				<div class="align-middle col-2">${item.NameTest }</div>
				<div class="align-middle col-2">${item.Mark }</div>
			</div>
		</c:forEach>
	</div>
</c:if>
<c:if test="${totalPage > 1 }">
	<nav aria-label="...">
		<ul class="pagination justify-content-center mt-3">
			<li class="page-item ${curPage == 1 ? 'disabled':'' }"><a
				class="page-link"
				href="${pageUrl}&page=${curPage > 1 ? curPage - 1 : curPage}"
				tabindex="-1"><span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span></a></li>
			<c:forEach varStatus="i" begin="1" end="${totalPage }" step="1">
				<li class="page-item ${i.index == curPage ? 'active': '' }"><a
					class="page-link" href="${pageUrl}&page=${i.index}">${i.index}</a></li>
			</c:forEach>
			<li class="page-item ${curPage == totalPage ? 'disabled':'' }"><a
				class="page-link"
				href="${pageUrl}&page=${curPage < totalPage ? curPage + 1 : curPage}"><span
					aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span></a></li>
		</ul>
	</nav>
</c:if>
