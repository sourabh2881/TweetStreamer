package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.RuleRepo;
import com.example.demo.dao.TweetRepo;
import com.example.demo.models.RuleModel;
import com.example.demo.models.TweetModel;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;



@Controller
public class HomeController {
	
	@Autowired
	TweetRepo repo;
	
	@Autowired
	RuleRepo rulerepo;
	
	@RequestMapping("/add")
	@ResponseBody
	public String addKeyword(@RequestParam String keyword) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("1R77R9qAXgims7WGMOFSzoWyv")
				.setOAuthConsumerSecret("iZ8dktLxIUFGw9IiGOTverNxfSPEfDQxTTvj75V6izC2dw5fHu")
				.setOAuthAccessToken("1430034068519866368-OHBl5eMjzCsjvBlR4jlPE1JPE0FQMT")
				.setOAuthAccessTokenSecret("NjXYEMA6KnRsJPBt5Os7xgYg6p0b6FocitEyxXMxqUAl4");

		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

		StatusListener statusListener = new StatusListener() {
			private int count = 0;
			private long originalTweetId = 0;

			@Override
			public void onStatus(Status status) {
				TweetModel tweet = new TweetModel(status.getText()+ " - " + status.getUser().getName() , keyword);
				repo.save(tweet);
				System.out.println(status.getText() + " - " +status.getUser().getName());
				
			} // end of the onStatus()

			@Override
			public void onException(Exception ex) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
				
			}
		}; // end of the listener

		FilterQuery fq = new FilterQuery();

		String keywords[] = { keyword };
		RuleModel rule = new RuleModel(keyword);
		rulerepo.save(rule);
		
		fq.track(keywords);

		twitterStream.addListener(statusListener);
		twitterStream.filter(fq);
		return "stream on!";
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public String removeKeyword(@RequestParam String keyword) {
		rulerepo.deleteById(keyword);
		return "Removed!";
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public List<TweetModel> findTweet(@RequestParam String keyword) {
		List<TweetModel> tweets = repo.findByKeyword(keyword);
		return tweets;
	}
	
	@RequestMapping("/tweet")
	@ResponseBody
	public String createTweet(String tweet) throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("7RjVuFkYP4Evj0XpKp3X7x3M5")
		.setOAuthConsumerSecret("zdhePPee28tPML2z0MqjrFrE85FdYD51aPh1v93dOjShgLGuHW")
		.setOAuthAccessToken("1453399197625958410-jH3cJdUTqcCI6DbYZKNicUQFl6steJ")
		.setOAuthAccessTokenSecret("9A94AGL74nl4kl5NhdgTwHgkIxnuQaxCnCk0tI6cUFW4e");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
	    Status status = twitter.updateStatus("Using twitter API");
	    return status.getText();
	}
}
