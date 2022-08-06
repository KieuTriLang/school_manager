<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="home?type=subject">Danh sách môn học</a>
<c:if test="${type.equals('add') || type.equals('edit') }">
	<h3 class="text-center my-4">${type.equals('edit')? 'Cập nhật':'Thêm'} môn học</h3>
	<form action="subject?type=${type}&id=${subject.Id}" method="post"
		class="w-50 mx-auto">
		<div class="form-group row mb-3 align-items-center">
			<label for="nameSubject" class="col-4 form-label">Tên môn học</label> <input
				type="text" class="col-8 form-control" name="nameSubject"
				id="nameSubject" value="${subject.NameSubject }">
			<small class="text-danger offset-4 col-8 p-0">${validateList.vName}</small>
		</div>
		<div class="d-flex justify-content-center">
			<input type="submit" class="btn btn-primary mt-4 mb-5 w-25"
				value="${type.equals('edit')? 'Lưu':'Thêm'}">
		</div>
	</form>
</c:if>

<c:if test="${type.equals('delete') }">
	<form action="subject?type=${type}&id=${subject.Id}" method="post"
		class="w-50 mx-auto">
		<div class="form-group text-center">
			<h3>Bạn có chắc muốn xóa môn học?</h3>
		</div>
		<div class="d-flex justify-content-center">
			<a href="home" class="btn btn-transparent mt-4 mb-5 mr-4 w-25">Hủy</a>
			<button type="submit" class="btn btn-danger mt-4 mb-5 w-25">Xóa</button>
		</div>
	</form>
	<jsp:include page="Detail.jsp"/>
</c:if>
