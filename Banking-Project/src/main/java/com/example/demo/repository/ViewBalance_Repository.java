package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.View_balance;

@Repository
public interface ViewBalance_Repository extends MongoRepository<View_balance,Integer> {

}
