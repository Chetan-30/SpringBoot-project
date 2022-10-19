package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="deposit-list")
public class Deposit {
	@Id
	private int account_number;
	private float amount_deposited;
}
