<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/component/header.jspf"%>
<div class="container mt-3">
	<h4>내 정보</h4>
	<div class="row gx-3">
		<div class="col-md-4 p-2">
			<h6>
				<i class="bi bi-person-bounding-box"></i> 프로필 이미지
			</h6>
			<div>
				<img src="${sessionScope.logonAccount.profileImageUrl }" width="200"
					height="200" class="rounded-circle" />
			</div>
			<h6 class="mt-2">
				<i class="bi bi-person-vcard"></i> 닉네임
			</h6>
			<div>
				<input type="text" class="form-control"
					value="${sessionScope.logonAccount.nickname }" />
			</div>
			<div>
				<button type="submit" class="form-control btn btn-dark mt-2">변경</button>
			</div>
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



