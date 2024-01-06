<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/component/header.jspf"%>
<div class="container my-3 ">
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
				<img
					src="${fn:startsWith(product.account.profileImageUrl, 'http') ? '' : contextPath }${product.account.profileImageUrl }"
					width="42" height="42" class="rounded-circle" />
				<h5>${product.account.nickname }</h5>
				<small>${product.account.address }</small>
			</div>
			<!-- 상품 타이블 및  설명 -->
			<div>
				<h4>${product.title }</h4>
				<p style="height: 100px;">${product.description }</p>
			</div>
			<!-- 거래희망장소 -->
			<div>
				<small>거래희망장소</small>
			</div>
			<div style="height: 200px; overflow: hidden" class="my-1 rounded-4">

				<div id="staticMap" style="width: 100%; height: 100%"></div>
			</div>
			<div class="d-flex gap-2 text-secondary" style="font-size: small;">
				<span id="pickedCnt">관심 ${totalPick }</span> <span>조회 ${product.viewCnt }</span>
			</div>
			<!-- 가격 및 찜하기 버튼-->
			<div class="d-flex my-2 align-items-center">
				<div class="p-2 border-end">
					<c:choose>
						<c:when test="${picked }">
							<i class="bi bi-heart-fill" id="pick" style="cursor: pointer;"></i>
						</c:when>
						<c:otherwise>
							<i class="bi bi-heart" id="pick" style="cursor: pointer;"></i>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="flex-grow-1 p-2">
					<c:choose>
						<c:when test="${product.type eq 'sell' }">
							<fmt:formatNumber pattern="#,###" value="${product.price }" />원	
						</c:when>
						<c:otherwise>
							나눔<i class="bi bi-emoji-laughing"></i>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="p-2">
					<button class="btn btn-sm btn-dark" type="button"
						onclick="location.href='${contextPath}/chat/link?productId=${product.id }'"
					>문의하기</button>
				</div>
			</div>
			 <!-- 
			<form action="${contextPath }/product/pick" method="post"  
				id="pickform" style="display: none">
				<input type="hidden" name="_method" value="" id="pickform_method" />
				<input type="hidden" name="targetProductId" value="${product.id }" />
			</form>
			 -->
		</div>
	</div>
</div>
<c:choose>
	<c:when test="${empty sessionScope.logonAccount }">
		<script>
			document.querySelector("#pick").onclick=function(evt) {
				if(window.confirm("로그인이 필요한 작업입니다.")) {
					location.href="${contextPath}/signin";	
				}
			}
		</script>
	</c:when>
	<c:otherwise>
		<script>
			document.querySelector("#pick").onclick=function(evt) {
				const xhr = new XMLHttpRequest();
				if(this.className=='bi bi-heart-fill') {
					xhr.open("delete", 
							"${contextPath}/product/pickAjax?targetProductId=${product.id}", true);
					xhr.send();
				}else {
					xhr.open("post", "${contextPath}/product/pickAjax", true);
					xhr.setRequestHeader("content-type" , "application/x-www-form-urlencoded");
					xhr.send("targetProductId=${product.id}");
				}
				// get : send()
			
				xhr.onreadystatechange= function() {
					if(xhr.readyState==4 ) {
						// window.alert(xhr.responseText);
						var response = JSON.parse(xhr.responseText);
						if(response.result == 'success') {
							if(document.querySelector("#pick").className=='bi bi-heart-fill') {
								document.querySelector("#pick").className='bi bi-heart';
							}else {
								document.querySelector("#pick").className='bi bi-heart-fill';
							}
							
							document.querySelector("#pickedCnt").innerHTML = "관심 " + response.updatePick;
						}
					}
				}
			}
		</script>
	</c:otherwise>
</c:choose>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b2ac15002f8cc5e0eae8cf1b4f792c45&libraries=services"></script>
<script>
	var staticMapContainer = document.getElementById('staticMap'); // 이미지 지도를 표시할 div
	var staticMapOption = {
		center : new kakao.maps.LatLng(${product.account.latitude}, ${product.account.longitude }), // 이미지 지도의 중심좌표
		level : 3, // 이미지 지도의 확대 레벨
		marker : {
			position : new kakao.maps.LatLng(${product.account.latitude}, ${product.account.longitude }),
			text : '여기서 거래하기를 희망해요!' // text 옵션을 설정하면 마커 위에 텍스트를 함께 표시할 수 있습니다
		}
	// 이미지 지도에 표시할 마커
	};

	// 이미지 지도를 생성합니다
	var staticMap = new kakao.maps.StaticMap(staticMapContainer,
			staticMapOption);
</script>

<%@ include file="/WEB-INF/view/component/footer.jspf"%>
