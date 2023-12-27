<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/component/header.jspf"%>
<div
	class="container py-5 d-flex flex-column
		justify-content-center align-items-center">
	<h4>OPEN MARKET</h4>
	<div class="card mt-3" style="width: 20rem; height: 24rem;">
		<div class="card-body d-flex flex-column justify-content-between">
			<h5 class="card-title " style="word-break: keep-all;">오픈마켓에 오신
				것을 환영합니다.</h5>
			<div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><a
						href="https://kauth.kakao.com/oauth/authorize?client_id=7ab771b649c094c990041f78323bcfd1&response_type=code&redirect_uri=http://192.168.151.10:8080${contextPath }/callback/kakao">
							카카오로 로그인하기 </a></li>
					<li class="list-group-item">네이버로 로그인하기</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/view/component/footer.jspf"%>