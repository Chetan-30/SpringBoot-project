package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Deposit;

@Repository
public interface Deposit_Repository extends MongoRepository<Deposit, Integer>{
	
}
