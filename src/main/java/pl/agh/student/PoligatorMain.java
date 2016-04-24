package pl.agh.student;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import pl.agh.student.configuration.AppConfig;
import pl.agh.student.persistence.model.Tweet;
import pl.agh.student.persistence.model.User;
import pl.agh.student.service.TweetExtractor;
import pl.agh.student.service.TweetServiceI;
import pl.agh.student.service.UserService;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.Arrays;
import java.util.List;

public class PoligatorMain {

    private static void testDb(AbstractApplicationContext context) {
        TweetServiceI twitterService = (TweetServiceI) context.getBean("tweetService");
        UserService userService =  (UserService) context.getBean("userService");

//        Tweet tweet = new Tweet();
//        tweet.setId(1L);
//        tweet.setText("Some tweet Test");
//        tweet.setCreateDate(new LocalDate());
//        tweet.setHashtags(Arrays.asList("Hash1", "Hash2", "Hash3"));
//        User user = new User();
//        user.setName("TEST");
//        user.setScreenName("Test");
//        user.setId(123);
//        userService.saveUser(user);
//
//        tweet.setUser(user);
//        twitterService.saveTweet(tweet);

        Tweet tweet = twitterService.getById(1L);

        List<String> hashtags = tweet.getHashtags();
        System.err.println(hashtags);
        hashtags.forEach(System.err::println);
    }

    private static void testTwitter(AbstractApplicationContext context) throws TwitterException {
        TweetExtractor extractor = (TweetExtractor) context.getBean("tweetExtractor");

        Status tweetById = extractor.getTweetById("711210298301136896");
        System.err.println(tweetById.getText());

        List<Status> statusList = extractor.getTweetByIds(new long[]{711210298301136896L, 710550160380731393L});
        statusList.forEach(status -> System.err.println(status.getText()));
    }

    public static void main(String args[]) throws TwitterException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        testDb(context);
        testTwitter(context);

        context.close();
    }

}