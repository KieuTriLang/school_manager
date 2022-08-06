<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="form-control mb-3">${results.get(0).NameSubject }</div>
<div class="row text-center text-middle bg-dark text-light">
	<div class="col-6 py-2">Tên sinh viên</div>
	<div class="col-6 py-2">${results.get(0).NameTest }</div>
</div>
<c:forEach var="item" items="${results}">
	<div class="row text-center text-middle border-bottom">
		<div class="col-6 py-2">${item.FirstName} ${item.LastName }</div>
		<div class="col-6 py-2">${item.Mark }</div>
	</div>

</c:forEach>
