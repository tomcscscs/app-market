package org.edupoll.app.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {
	private int id;
	private String title;
	private String type;
	private int price;
	private String description;
	private String accountId;
	private int viewCnt;
	
	private Account account;

	
	private List<ProductImage> images;
	
	
}
