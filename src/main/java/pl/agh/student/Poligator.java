package pl.agh.student;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.agh.student.persistence.model.Tweet;
import pl.agh.student.persistence.model.User;
import pl.agh.student.service.TweeterExtractor;
import pl.agh.student.service.TweetService;
import pl.agh.student.service.UserService;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.Arrays;
import java.util.Collections;
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
    }

}
