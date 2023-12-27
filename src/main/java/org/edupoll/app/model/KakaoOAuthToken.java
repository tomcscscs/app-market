package org.edupoll.app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class KakaoOAuthToken {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
}
