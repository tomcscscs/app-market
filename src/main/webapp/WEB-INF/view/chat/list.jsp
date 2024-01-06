<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/component/header.jspf"%>
<div class="container mt-3">
	<h4><i class="bi bi-chat-dots"></i> 진행중인 상품 문의들</h4>
	<p>
		현재 문의가 진행중인 상품들입니다.
	</p>
	<div class="list-group py-2">
		<c:forEach items="${chatRooms }" var="one">
			<a href="${contextPath }/chat/room/${one.id}"
				class="list-group-item list-group-item-action">
				${one.id } 
			</a>
		</c:forEach>
	</div>
</div>
<%@ include file="/WEB-INF/view/component/footer.jspf"%>