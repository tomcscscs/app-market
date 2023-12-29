package org.edupoll.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Account {
	private String id;
	private String nickname;
	private String platform;
	private String accessToken;
	private String profileImageUrl;
	private String address;
	private Double latitude;
	private Double longitude;

}
