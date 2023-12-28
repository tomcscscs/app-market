package org.edupoll.app.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateLocation {
	private String address;
	private Double latitude;
	private Double longitude;
}
