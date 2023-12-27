package org.edupoll.app.service;

import java.net.URI;

import org.edupoll.app.model.KakaoUserInfo;
import org.edupoll.app.model.KakaoOAuthToken;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Service
public class KakaoAPIService {

	public KakaoOAuthToken getOAuthToken(String code) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", "7ab771b649c094c990041f78323bcfd1");
		body.add("redirect_uri", "http://192.168.151.10:8080/market/callback/kakao");
		body.add("code", code);

		var request = new RequestEntity<>(body, headers, HttpMethod.POST,
				URI.create("https://kauth.kakao.com/oauth/token"));

		RestTemplate template = new RestTemplate();

		var response = template.exchange(request, String.class);

		Gson gson = new Gson();
		
		return gson.fromJson(response.getBody(), KakaoOAuthToken.class);
	}

	public KakaoUserInfo getUserInfo(String accessToken) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", "Bearer "+accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		
		var request = new RequestEntity<>(headers, HttpMethod.GET,
				URI.create("https://kapi.kakao.com/v2/user/me"));

		RestTemplate template = new RestTemplate();

		var response = template.exchange(request, String.class);
		Gson gson = new Gson();
		return gson.fromJson(response.getBody(), KakaoUserInfo.class);
	}
}
