package pl.agh.student.service;

import pl.agh.student.model.Tweet;

import java.util.List;

public interface TweetServiceI {

    void saveTweet(Tweet tweet);

    List<Tweet> findAllTweets();

    void updateTweet(Tweet tweet);
}
