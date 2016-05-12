package pl.agh.student;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.agh.student.persistence.model.Tweet;
import pl.agh.student.persistence.model.User;
import pl.agh.student.service.TweeterExtractor;
import pl.agh.student.service.TweetService;
import pl.agh.student.service.UserService;
import twitter4j.HashtagEntity;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class Poligator {

    @Autowired
    private TweeterExtractor extractor;

    @Autowired
    private TweetService twitterService;

    @Autowired
    private UserService userService;

    public void testDb() {
        Tweet tweet = new Tweet();
        tweet.setId(new Random().nextLong());
        tweet.setText("Some tweet Test");
        tweet.setCreateDate(new LocalDate());
        tweet.setHashtags(Arrays.asList("Hash1", "Hash2", "Hash3"));
        User user = new User();
        user.setRawData("sme test");
        user.setName("TEST");
        user.setScreenName("Test");
        user.setId(new Random().nextInt());
        userService.saveUser(user);

        tweet.setUser(user);
        tweet.setRawData("sme test");
        tweet.setInReplyToUser(user);
        tweet.setUserMentions(Collections.singletonList(user));
        twitterService.saveTweet(tweet);

//        Tweet tweet = twitterService.getById(1L);
//
//        List<String> hashtags = tweet.getHashtags();
//        System.err.println(tweet.getUser().getName());
//        System.err.println(tweet.getInReplyToUser().getName());
//        System.err.println(hashtags);
//        hashtags.forEach(System.err::println);
    }

    public void testTwitter() throws TwitterException {
        Status tweetById = extractor.getTweetById("711210298301136896");
        System.err.println(tweetById.getText());

        List<Status> statusList = extractor.getTweetByIds(new long[]{711210298301136896L, 710550160380731393L});
        statusList.forEach(status -> System.err.println(status.getText()));
        statusList.forEach(status -> System.out.println(status.getUser()));
    }
    
    public void pullData(List<String> accounts) throws NumberFormatException, TwitterException{
    	
    	accounts.forEach(account -> {
    		ResponseList<Status> statuses = null;
			try {
				statuses = extractor.getTweetByAccountId(account);
				User user = new User();
				if(statuses.size() > 0) {
					user = insertUser(statuses.get(0));
				}
				for (Status status : statuses) {
					addToDatabase(status, user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
    	});
    	
    }

    //TODO
	private void addToDatabase(Status status, User user) {
		Tweet tweet = new Tweet();
		tweet.setId(status.getId());
		tweet.setUser(user);
		
		List<String> hashtags = new LinkedList<String>();
		HashtagEntity[] hts = status.getHashtagEntities();
		for(int i = 0; i < hts.length; i++)
		{
		    hashtags.add(hts[i].getText());
		}
		tweet.setHashtags(hashtags);
		tweet.setText(status.getText());
		tweet.setRawData(status.getSource());
		tweet.setCreateDate(LocalDate.fromDateFields(status.getCreatedAt()));
		
		try {
			twitterService.saveTweet(tweet);
		} catch (Exception e) {
			System.err.println("Error while adding tweet");
		}
	}

	//TODO
	private User insertUser(Status status) {
		twitter4j.User twitterUser = status.getUser();
		User user = new User();
		user.setId(twitterUser.getId());
		user.setName(twitterUser.getName());
		user.setRawData(twitterUser.getDescription());
		user.setScreenName(twitterUser.getScreenName());
		user.setDescription(twitterUser.getDescription());
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			System.err.println("Error while adding user");
		}
		
		return user;
	}

}
