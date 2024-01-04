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


public class PicksAdkView {
	
	private String ownerAccountId;
	private int targetProductId;
	private String title;
	private List<String> URL;
	private String nickName;
	
	

}
