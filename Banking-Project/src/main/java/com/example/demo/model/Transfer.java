package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="transfer-list")
public class Transfer {
	@Id
	private int first_account;
	private int second_account;
	private float amount_transferred;
}
