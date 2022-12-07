package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="tweets")
public class TweetModel {
	
	private String tweetText;
	private String keyword;
	
	public TweetModel(String tweetText, String keyword) {
		super();
		this.tweetText = tweetText;
		this.keyword = keyword;
	}
	public String getTweetText() {
		return tweetText;
	}
	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "TweetModel [tweetText=" + tweetText + ", keyword=" + keyword + "]";
	}
	
}
