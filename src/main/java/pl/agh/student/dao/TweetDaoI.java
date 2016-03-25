package pl.agh.student.dao;

import pl.agh.student.model.Tweet;

import java.util.List;

public interface TweetDaoI {

    void saveTweet(Tweet tweet);

    List<Tweet> findAllTweets();

    Tweet findById(Long id);

    void updateTweet(Tweet tweet);

}
