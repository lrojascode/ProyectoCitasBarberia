package com.feignclient.app.model;

import lombok.Data;

@Data
public class Empleado {
	private Long id;
	private String first_name;
	private String last_name;
	private String profession;
	private String pictureBase64;
	private byte[] picture;
	private String working_days;
	private Boolean active;
}