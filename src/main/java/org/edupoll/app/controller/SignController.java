package org.edupoll.app.controller;

import javax.servlet.ServletContext;

import org.edupoll.app.model.KakaoOAuthToken;
import org.edupoll.app.model.KakaoUserInfo;
import org.edupoll.app.service.KakaoAPIService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignController {

	private final ServletContext application;
	private final KakaoAPIService kakaoAPIService;

	@GetMapping("/signin")
	public String showSign(Model model) {

		String kakaoLoginLink = "https://kauth.kakao.com/oauth/authorize?";
		kakaoLoginLink += "client_id=${client_id}&response_type=code";
		kakaoLoginLink += "&redirect_uri=${redirect_uri}";

		String clientId = "6547789a3b90cbd78c3073922c994989";
		String redirectURI = "http://192.168.4.22:8080${contextPath}/callback/kakao"
							.replace("${contextPath}", application.getContextPath());
		
		kakaoLoginLink = kakaoLoginLink.replace("${client_id}", clientId);
		kakaoLoginLink = kakaoLoginLink.replace("${redirect_uri}", redirectURI);
		
		
		model.addAttribute("kakaoLoginLink", kakaoLoginLink);
		return "signin";
	}

	@GetMapping("/callback/kakao")
	public String acceptCode(@RequestParam String code) {
		// System.out.println("code = " + code);

		KakaoOAuthToken oAuthToken = kakaoAPIService.getOAuthToken(code);
		// System.out.println(oAuthToken.getAccess_token());

		KakaoUserInfo kakaoUserInfo = kakaoAPIService.getUserInfo(oAuthToken.getAccess_token());

		System.out.println(kakaoUserInfo);

		return null;
	}
}
