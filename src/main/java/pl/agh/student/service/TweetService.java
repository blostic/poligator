package pl.agh.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.student.persistence.dao.TweetDao;
import pl.agh.student.persistence.model.Tweet;

import java.util.List;

@Service("tweetService")
@Transactional
public class TweetService implements TweetServiceI {

    @Autowired
    private TweetDao dao;

    public void saveTweet(Tweet tweet) {
        dao.saveTweet(tweet);
    }

    public List<Tweet> findAllTweets() {
        return dao.findAllTweets();
    }

    public void updateTweet(Tweet tweet) {
        dao.updateTweet(tweet);
    }
}
