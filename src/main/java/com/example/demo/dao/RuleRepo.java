package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.models.RuleModel;

public interface RuleRepo extends MongoRepository<RuleModel, String>{

}
