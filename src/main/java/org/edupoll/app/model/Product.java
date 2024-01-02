package org.edupoll.app.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Product {
	
	private int id;
	private String title;
	private String type;
	private Integer price;
	private String description;
	private String accountId;
	private int viewCnt;
	
	private List<ProductImage> images;
	
	

}
