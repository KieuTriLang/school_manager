<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${!type.equals('delete') }">
	<a href="home">Return home</a>
</c:if>
<div class="card w-50 bg-secondary mx-auto text-light">
	<div class="card-body px-3">
		<h2 class="card-title text-center">${subject.NameSubject}</h2>

		<c:if test="${!type.equals('delete') }">
			<div class="d-flex justify-content-center align-item-center mt-3">
				<a href="subject?type=edit&id=${subject.Id }"
					class="btn btn-primary mr-3">Edit</a> <a
					href="subject?type=delete&id=${subject.Id }" class="btn btn-danger">Delete</a>
			</div>
		</c:if>


	</div>
</div>