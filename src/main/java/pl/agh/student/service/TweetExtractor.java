package pl.agh.student.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.agh.student.configuration.TwitterConfiguration;
import twitter4j.*;

@ComponentScan({"pl.agh.student.configuration"})
@EnableTransactionManagement
@Service("tweetExtractor")
public class TweetExtractor {

    private final static Logger LOGGER = LoggerFactory.getLogger(TweetExtractor.class);

    @Autowired
    private TwitterConfiguration configuration;

    public Status getTweetById(String tweetId) throws NumberFormatException, TwitterException {
        final Twitter twitter = new TwitterFactory(configuration.getConfiguration()).getInstance();
        return twitter.showStatus(Long.parseLong(tweetId));
    }

    public ResponseList<Status> getTweetByIds(long[] ids) throws NumberFormatException, TwitterException {
        final Twitter twitter = new TwitterFactory(configuration.getConfiguration()).getInstance();
        ResponseList<Status> tweets = twitter.lookup(ids);
        LOGGER.info(tweets.size() + " tweets downloaded");
        return tweets;
    }

    //Max number of tweets per page in paging is 200
    // MAX_ID - 4th parameter - highest tweet id in 600 tweets collection
    // SINCE_ID - 3th paramater - lowest tweet id in 600 tweets collection
    public ResponseList<Status> getTweetByAccountId(String userName, long sinceId, long maxId) throws NumberFormatException, TwitterException {
        final Twitter twitter = new TwitterFactory(configuration.getConfiguration()).getInstance();
        int currentSize = 0;
        ResponseList<Status> userTimeline = twitter.getUserTimeline(userName, new Paging(1, 200, sinceId - 1, maxId));
        int it = 1;
        while (userTimeline.size() - currentSize > 0) {
            currentSize = userTimeline.size();
            userTimeline.addAll(twitter.getUserTimeline(userName, new Paging(it++, 200, sinceId - 1, maxId)));
        }
        LOGGER.info("" + userTimeline.size() + " tweets downloaded using " + it + " request.");
        return userTimeline;
    }

}
