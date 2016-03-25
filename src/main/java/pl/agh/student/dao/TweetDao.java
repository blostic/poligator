package pl.agh.student.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.agh.student.model.Tweet;

import java.util.List;

@Repository("twitterDao")
public class TweetDao extends AbstractDao implements TweetDaoI {

    public void saveTweet(Tweet tweet) {
        persist(tweet);
    }

    @SuppressWarnings("unchecked")
    public List<Tweet> findAllTweets() {
        Criteria criteria = getSession().createCriteria(Tweet.class);
        return (List<Tweet>) criteria.list();
    }

    public Tweet findById(Long id) {
        Criteria criteria = getSession().createCriteria(Tweet.class);
        criteria.add(Restrictions.eq("id", id));
        return (Tweet) criteria.uniqueResult();
    }

    public void updateTweet(Tweet tweet) {
        getSession().update(tweet);
    }
}
