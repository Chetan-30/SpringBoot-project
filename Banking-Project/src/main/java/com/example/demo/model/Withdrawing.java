package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="withdraw-list")
public class Withdrawing {
	@Id
	private int account_number;
	private float amount_withdrawn;
}
