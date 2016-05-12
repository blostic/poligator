package pl.agh.student;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.agh.student.persistence.model.Tweet;
import pl.agh.student.persistence.model.User;
import pl.agh.student.service.TweetExtractor;
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
    private TweetExtractor extractor;

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
        user.setName("TEST");
        user.setScreenName("Test");
        user.setId(new Random().nextInt());
        userService.saveUser(user);

        tweet.setUser(user);
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
	    		statuses.forEach(status -> addToDatabase(status));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	});
    	
    }

    //TODO
	private void addToDatabase(Status status) {
		Tweet tweet = new Tweet();
		User user = getUser(status);

		
		List<String> hashtags = new LinkedList<String>();
		HashtagEntity[] hts = status.getHashtagEntities();
		for(int i = 0; i < hts.length; i++)
		{
		    hashtags.add(hts[i].getText());
		}
		tweet.setHashtags(hashtags);
		tweet.setText(status.getText());
//		twitterService.saveTweet(tweet);
	}

	//TODO
	private User getUser(Status status) {
		User user = new User();
		user.setName(status.getUser().getName());
//		userService.saveUser(user);
		
		return user;
	}

}
