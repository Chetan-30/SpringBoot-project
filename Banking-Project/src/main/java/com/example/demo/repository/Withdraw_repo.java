package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Withdrawing;

@Repository
public interface Withdraw_repo extends MongoRepository<Withdrawing, Integer>{

}
