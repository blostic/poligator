package pl.agh.student;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import pl.agh.student.configuration.AppConfig;
import pl.agh.student.persistence.model.Tweet;
import pl.agh.student.service.TweetExtractor;
import pl.agh.student.service.TweetServiceI;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

public class PoligatorMain {

    private static void testDb(AbstractApplicationContext context) {
        TweetServiceI service = (TweetServiceI) context.getBean("tweetService");

        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setText("Some tweet Test");

        service.saveTweet(tweet);
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