<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/view/component/header.jspf"%>
<div class="container mt-3">
	<div class="row gx-3">
		<!-- 상품 이미지 -->
		<div class="col-lg-6">
			<div id="carouselExampleIndicators" class="carousel slide">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<c:forEach var="image" items="${product.images }"
						varStatus="status">
						<div
							class="carousel-item ${status.first ? 'active' :'' } rounded-4"
							style="height: 400px; overflow: hidden">
							<img src="${contextPath }${image.url}"
								class="d-block object-fit-cover"
								style="height: 100%; width: 100%">
						</div>
					</c:forEach>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>
		<!-- 상품 상세설명 -->
		<div class="col-lg-6">
			<!-- 판매자 정보 -->
			<div class="my-2 d-flex align-items-center">
				<img src="${fn:startsWith(product.account.profileImageUrl, 'http') ? '' : contextPath }${product.account.profileImageUrl }"
									width="42" height="42" class="rounded-circle" />
				<h5>${product.account.nickname }</h5><small>${product.account.address }</small>
			</div>
			<!-- 상품 타이블 및  설명 -->
			<div>
				<h4>${product.title }</h4>
				<p>${product.description }</p>
			</div>	
			<!-- 거래희망장소 -->
			
			<!-- 가격 및 찜하기 버튼-->
			
		
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/view/component/footer.jspf"%>
