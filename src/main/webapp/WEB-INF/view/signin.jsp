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
			<div class="card-text text-center">
				<p
					style="border-radius: 12px; background-color: #FEE500; cursor: pointer;"
					onclick="location.href='${kakaoLoginLink}'">
					<img src="${contextPath }/resource/icon/kakao_login.png"
						alt="카카오로 로그인하기" />

				</p>
				<p
					style="border-radius: 12px; background-color: #03c75a; cursor: pointer"
					onclick="location.href='#'">
					<img src="${contextPath }/resource/icon/naver_login.png"
						alt="카카오로 로그인하기" />

				</p>

			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/view/component/footer.jspf"%>