package org.edupoll.app.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateInfo {
	private String nickname;
	private MultipartFile profileImage;
	
}
