package pl.agh.student;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import pl.agh.student.configuration.AppConfig;
import pl.agh.student.persistence.model.Tweet;
import pl.agh.student.service.TweetServiceI;

import java.util.List;

public class PoligatorMain {

    public static void main(String args[]) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        TweetServiceI service = (TweetServiceI) context.getBean("tweetService");

        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setText("Some tweet Test");

        service.saveTweet(tweet);

        List<Tweet> tweetList = service.findAllTweets();
        for (Tweet tmpTweet : tweetList) {
            System.out.println(tmpTweet.getText());
        }

        context.close();
    }
}