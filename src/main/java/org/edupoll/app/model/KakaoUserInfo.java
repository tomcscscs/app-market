package org.edupoll.app.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class KakaoUserInfo {
	
	private long id;
	
	@SerializedName("properties")
	private KakaoProfile profile;
}
