<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/component/header.jspf"%>
<div class="container mt-3">
	<div class="py-5 text-center">
		<div class="row py-lg-5">
			<div class="col-lg-6 col-md-8 mx-auto">
				<h1 class="fw-light" style="font-weight: bold;"">반갑습니다.</h1>
				<p class="lead text-body-secondary">Something short and leading
					about the collection below—its contents, the creator, etc. Make it
					short and sweet, but not too short so folks don’t simply skip over
					it entirely.</p>
			</div>
		</div>
	</div>
	<div class="album py-3 bg-body-tertiary">
		<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
			<c:forEach items="${products }" var="one">
				<div class="col" onclick="location.href='${contextPath}/product/${one.id }'" 
					style="cursor: pointer">
					<div class="card shadow-sm">
						<div class="card-header " style="height: 196px; overflow: hidden">
							<img src="${contextPath }${one.images[0].url}" class="rounded-2"
								style="width: 100%; height: 100%" class="object-fit-cover" />
						</div>
						<div class="card-body">
							<p class="card-text text-truncate">${one.title }</p>
							<div class="d-flex justify-content-between align-items-center">
								<div>
									<c:choose>
										<c:when test="${one.type eq 'sell' }">
											<span class="badge  bg-dark">판매</span>
											<small style="font-weight: bold"><fmt:formatNumber
													pattern="#,###" value="${one.price }" />원</small>
										</c:when>
										<c:otherwise>
											<span class="badge bg-warning">나눔</span>
										</c:otherwise>
									</c:choose>
								</div>
								<small class="text-body-secondary">${one.account.nickname }</small>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
</div>
<%@ include file="/WEB-INF/view/component/footer.jspf"%>