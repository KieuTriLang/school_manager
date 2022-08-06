<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="home?type=student">Danh sách sinh viên</a>
<c:if test="${type.equals('add') || type.equals('edit') }">
	<h3 class="text-center my-4">${type.equals('edit')? 'Cập nhật':'Thêm'} sinh viên</h3>
<form action="student?type=${type}&id=${student.Id}" method="post" class="w-50 mx-auto">
	<div class="form-group row mb-3">
		<label for="firstName" class="col-4 form-label">Họ</label> <input
			type="text" class="col-8 form-control" name="firstName"
			id="firstName" value="${ !oldData.containsKey('dFirstName') ? student.FirstName : oldData.dFirstName }">
			<small class="text-danger offset-4 col-8 p-0">${validateList.vFirstName}</small>
	</div>
	<div class="form-group row mb-3">
		<label for="lastName" class="col-4 form-label">Tên</label> <input
			type="text" class="col-8 form-control" name="lastName" id="lastName"
			value="${ !oldData.containsKey('dLastName') ? student.LastName : oldData.dLastName}" >
			<small class="text-danger offset-4 col-8 p-0">${validateList.vLastName}</small>
	</div>
	<div class="form-group row mb-3">
		<label for="gender" class="form-label col-4">Giới tính</label>
		<div class="col-8 row">
			<div class="col-6">
				<input type="radio" name="gender" id="male" value="male"
					${(oldData.dGender eq "male" || student.Gender eq "Male")? "checked" : "" }> <label
					for="male">Nam</label>

			</div>
			<div class="col-6">
				<input type="radio" name="gender" id="female" value="female"
					${(oldData.dGender eq "female" || student.Gender eq "Female")? "checked" : "" }> <label
					for="female">Nữ</label>
			</div>
		</div>
	<small class="text-danger offset-4 col-8 p-0">${validateList.vGender}</small>
	</div>
	<div class="form-group row mb-3">
		<fmt:parseDate value="${student.Dob}" pattern="yyyy-MM-dd HH:mm:ss"
			var="myDate" />
		<fmt:formatDate value="${myDate}" var="Dob" pattern="yyyy-MM-dd" />
		<label for="dob" class="form-label col-4">Ngày sinh</label> <input
			type="date" class="form-control col-8" name="dob" id="dob"
			value="${!oldData.containsKey('dDob') ? Dob : oldData.dDob}">
			<small class="text-danger offset-4 col-8 p-0">${validateList.vDob}</small>
	</div>
	<div class="form-group row mb-3">
		<label for="phone" class="form-label col-4">Số điện thoại</label> <input
			type="text" class="form-control col-8" name="phone" id="phone"
			value="${!oldData.containsKey('dPhone') ? student.Phone : oldData.dPhone}">
			<small class="text-danger offset-4 col-8 p-0">${validateList.vPhone}</small>
	</div>
	<div class="form-group row mb-3">
		<label for="address" class="form-label col-4">Địa chỉ</label> <input
			type="text" class="form-control col-8" name="address" id="address"
			value="${!oldData.containsKey('dAddress') ? student.Address : oldData.dAddress}">
			<small class="text-danger offset-4 col-8 p-0">${validateList.vAddress}</small>
	</div>
	<div class="form-group row mb-3">
		<label for="classId" class="form-label col-4">Lớp</label> <select
			class="custom-select col-8" id="classId" name="classId">
			<c:forEach var="item" items="${classes}">
				<option value="${item.Id}"
					${(item.Id == student.ClassId || item.Id == oldData.dClassId) ? "selected" : "" }>${item.NameClass}</option>
			</c:forEach>
		</select>
		<small class="text-danger offset-4 col-8 p-0">${validateList.vClassId}</small>
	</div>
	<div class="d-flex justify-content-center">
		<input type="submit" class="btn btn-primary mt-4 mb-5 w-25" value="${type.equals('edit')? 'Lưu':'Thêm'}">
	</div>
</form>
</c:if>

<c:if test="${type.equals('delete') }">
<form action="student?type=${type}&id=${student.Id}" method="post" class="w-50 mx-auto">
	<div class="form-group text-center">
		<h3>Bạn có đồng ý xóa sinh viên?</h3>
	</div>
	<div class="d-flex justify-content-center">
		<a href="home?type=student" class="btn btn-transparent mt-4 mb-5 mr-4 w-25">Hủy</a>
		<button type="submit" class="btn btn-danger mt-4 mb-5 w-25">Xóa</button>
	</div>
</form>
<jsp:include page="Detail.jsp"/>
</c:if>
