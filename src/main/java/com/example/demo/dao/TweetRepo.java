package com.example.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.models.TweetModel;

public interface TweetRepo extends MongoRepository<TweetModel, String>{
	public List<TweetModel> findByKeyword(String Keyword);
}
