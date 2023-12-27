package org.edupoll.app.controller;

import org.edupoll.app.model.KakaoUserInfo;
import org.edupoll.app.model.KakaoUserInfo;
import org.edupoll.app.model.KakaoOAuthToken;
import org.edupoll.app.service.KakaoAPIService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignController {

	private final KakaoAPIService kakaoAPIService;
	
	@GetMapping("/signin")
	public String showSign() {
		return "signin";
	}

	@GetMapping("/callback/kakao")
	public String acceptCode(@RequestParam String code) {
		// System.out.println("code = " + code);

		KakaoOAuthToken oAuthToken = kakaoAPIService.getOAuthToken(code);
		// System.out.println(oAuthToken.getAccess_token());
		
		KakaoUserInfo kakaoUserInfo =
				kakaoAPIService.getUserInfo(oAuthToken.getAccess_token());
		
		System.out.println(kakaoUserInfo);
		
		
		return null;
	}
}






