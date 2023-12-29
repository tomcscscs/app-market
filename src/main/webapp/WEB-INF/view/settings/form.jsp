<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/view/component/header.jspf"%>
<div class="container mt-3">
	<h4>내 정보</h4>
	<div class="row gx-3">
		<div class="col-md-4 p-2">
			<form method="post" enctype="multipart/form-data"
				action="${contextPath }/settings/profile/info">
				<h6>
					<i class="bi bi-person-bounding-box"></i> 프로필 이미지
				</h6>
				<div>
					<img
						src="${fn:startsWith(sessionScope.logonAccount.profileImageUrl, '/upload') ? contextPath:'' }${sessionScope.logonAccount.profileImageUrl }"
						width="200" height="200" class="rounded-circle"
						style="cursor: pointer;"
						onclick="document.querySelector('#profileImage').click();"
						id="profileImageView" />
				</div>
				<div style="display: none">
					<input type="file" class="form-control" id="profileImage"
						accept="image/*" name="profileImage" />
				</div>
				<h6 class="mt-2">
					<i class="bi bi-person-vcard"></i> 닉네임
				</h6>
				<div>
					<input type="text" class="form-control"
						value="${sessionScope.logonAccount.nickname }" name="nickname" />
				</div>
				<div>
					<button type="submit" class="form-control btn btn-dark mt-2">변경</button>
				</div>
			</form>
		</div>
		<div class="col-md-8 p-2">
			<h6>
				<i class="bi bi-map"></i> 동네 설정
			</h6>
			<div style="height: 400px; background-color: #ddd" id="map"
				class="mb-2"></div>
			<form action="${contextPath }/settings/profile/location"
				method="post">
				<input type="hidden" name="_method" value="put" /><input
					type="hidden" name="latitude" id="latitude"
					value="${sessionScope.logonAccount.latitude }" /><input
					type="hidden" name="longitude" id="longitude"
					value="${sessionScope.logonAccount.longitude }" />
				<div>
					<input type="text" class="form-control" id="address" name="address"
						value="${sessionScope.logonAccount.address }" readonly />
				</div>
				<div>
					<button type="submit" class="form-control btn btn-dark mt-2">변경</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b2ac15002f8cc5e0eae8cf1b4f792c45&libraries=services"></script>
<script>
	// 이미지 미리보기 스크립트
	document.querySelector("#profileImage").onchange= function(e) {
		if(e.target.files[0]) {
			var fileReader = new FileReader();
			fileReader.readAsDataURL(e.target.files[0]);
			fileReader.onload = function(e) {
				console.log(e.target.result);
				document.querySelector("#profileImageView").src = e.target.result;
			}
		}
	};
	
	
	
	
	// 지도 관련 스크립트
	var lat = ${empty logonAccount.latitude ? 35.16014247399227 : logonAccount.latitude};
	var lng = ${empty logonAccount.longitude ?  126.85159147037064 : logonAccount.longitude };
	var container = document.querySelector("#map"); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(lat, lng), //지도의 중심좌표.
		level : 4
	//지도의 레벨(확대, 축소 정도)
	};
	var map = new kakao.maps.Map(container, options);
	// 마커가 표시될 위치입니다 

	var geocoder = new kakao.maps.services.Geocoder();

	var marker = new kakao.maps.Marker({
		position : map.getCenter()
	});
	marker.setMap(map);
	// 지도에 클릭 이벤트를 등록합니다
	// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다

	kakao.maps.event
			.addListener(
					map,
					'click',
					function(mouseEvent) {
						// 클릭한 위도, 경도 정보를 가져옵니다 
						var latlng = mouseEvent.latLng;
						// 마커 위치를 클릭한 위치로 옮깁니다
						marker.setPosition(latlng);
						map.panTo(latlng);

						geocoder
								.coord2RegionCode(
										latlng.getLng(),
										latlng.getLat(),
										function displayCenterInfo(result,
												status) {
											if (status === kakao.maps.services.Status.OK) {
												for (var i = 0; i < result.length; i++) {
													// 행정동의 region_type 값은 'H' 이므로
													if (result[i].region_type === 'H') {
														document
																.querySelector("#address").value = result[i].address_name;
														break;
													}
												}
											}
										});
						document.querySelector("#latitude").value = latlng
								.getLat();
						document.querySelector("#longitude").value = latlng
								.getLng();
					});
</script>
<%@ include file="/WEB-INF/view/component/footer.jspf"%>



