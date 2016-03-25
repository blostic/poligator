package pl.agh.student.persistence.dao;

import pl.agh.student.persistence.model.Tweet;

import java.util.List;

public interface TweetDaoI {

    void saveTweet(Tweet tweet);

    List<Tweet> findAllTweets();

    Tweet findById(Long id);

    void updateTweet(Tweet tweet);

}
