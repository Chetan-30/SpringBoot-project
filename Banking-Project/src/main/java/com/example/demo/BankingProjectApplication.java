package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.*;


@SpringBootApplication
public class BankingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingProjectApplication.class, args);
	}
	
	@Bean
	public Deposit getDeposit() {
		return new com.example.demo.model.Deposit();
	}
	
	@Bean
	public Withdrawing getWithdrawing() {
		return new com.example.demo.model.Withdrawing();
	}
	
	@Bean
	public Transfer getTransfer() {
		return new com.example.demo.model.Transfer();
	}
		


}
