package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="view_balance")
public class View_balance {

	@Id
	private int account_number;
	private String account_type;
	private String name;
	private float balance;
	
}
