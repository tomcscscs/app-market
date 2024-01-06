package org.edupoll.app.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {
	private String id;
	private int productId;
	private String sellerId;
	private String buyerId;
	private Date createdAt;

}
