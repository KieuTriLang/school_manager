<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="home?type=result">Danh sách kết quả thi</a>
<c:if test="${type.equals('add') || type.equals('edit') }">
	<h3 class="text-center my-4">${type.equals('edit')? 'Cập nhật':'Thêm'}
		kết quả thi</h3>
	<form
		action="result?type=${type eq 'edit' ? 'edit': 'add' }&classId=${classId}"
		method="post"
		class="d-flex flex-column align-items-center w-75 mx-auto">
		<select class="custom-select w-50" name="subjectId">
			<option value="0" selected>Chọn môn học</option>
			<c:forEach var="item" items="${subjects }">
				<option value="${item.Id }"
					${(item.Id == oldData.dSubjectId || item.Id == results.get(0).SubjectId) ? "selected" : "" }>${item.NameSubject}</option>
			</c:forEach>
		</select> <small class="text-danger p-0">${validateList.vSubjectId}</small>
		<div
			class="row text-center bg-secondary text-light my-3 form-group w-100 p-2">
			<div class="col-6 py-2">Tên sinh viên</div>
			<input type="text" name="nameTest" class="col-6 py-2"
				placeholder="Tên bài thi"
				value="${!oldData.containsKey('dNameTest') ? results.get(0).NameTest : oldData.dNameTest}" /> <small
				class="text-danger offset-6 mt-2">${validateList.vNameTest}</small>
		</div>


		<div class="row justify-content-center text-center w-100">
			<div class="row col-6">
				<c:forEach var="item" items="${type eq 'edit' ? results : students}">
					<input type="hidden" name="id" value="${item.Id }" />
					<input type="hidden" name="studentId" value="${type eq 'edit' ? item.StudentId : item.Id }" />
					<div class="col-12 align-middle border-bottom my-2">${item.FirstName}
						${item.LastName }</div>
				</c:forEach>

			</div>

			<div class="row col-6">
				<c:forEach var="item" items="${oldMark.size() > 0 ? oldMark : (type eq 'edit' ? results : students)}">
					<input type="text" name="mark" class="col-12 border-bottom my-2 form-control"
						placeholder="Điểm thi" value="${oldMark.size() > 0 ? item : item.Mark }"/>
				</c:forEach>
			</div>
		</div>
		<small class="text-danger p-0">${validateList.vMark}</small> <input
			type="submit" class="btn btn-primary mt-4 mb-5"
			value="${type.equals('edit')? 'Lưu':'Thêm'}">
	</form>
</c:if>

<c:if test="${type.equals('delete') }">
	<form action="${deleteUrl }"
		method="post" class="w-50 mx-auto text-center">
		<div class="form-group">
			<h3 class="">Bạn có đồng ý xóa kết quả bài thi?</h3>
		</div>
		<c:forEach var="item" items="${results }">
			<input type="hidden" name="id" value="${item.Id }"/>
		</c:forEach>
		<div class="d-flex justify-content-center">
			<a href="home" class="btn btn-transparent mt-4 mb-5 mr-4 w-25">Hủy</a>
			<button type="submit" class="btn btn-danger mt-4 mb-5 w-25">Xóa</button>
		</div>
		<jsp:include page="Detail.jsp"/>
	</form>
</c:if>
