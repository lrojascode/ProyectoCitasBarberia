package com.feignclient.app.model;

import lombok.Data;

@Data
public class Customer {

	 private Long id;
	 private Long users_id;
	 private String first_name;
	 private String last_name;
	 private String phone;
}
