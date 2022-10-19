package com.example.demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.View_balance;


@Repository
public interface Viewbalance_repo extends ReactiveMongoRepository<View_balance, Integer>{

}
