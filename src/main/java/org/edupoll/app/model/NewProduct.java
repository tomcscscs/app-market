package org.edupoll.app.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString


public class NewProduct {
	private MultipartFile[] images; 
	private String title;
	private String type;
	private Integer price;
	private String description;
	
	
	
	
	
	
	
	
	

}
