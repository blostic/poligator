package pl.agh.student.persistence.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TWEET")
public class Tweet {

    @Id
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "TWEET_TEXT", nullable = false)
    private String text;

    @Column(name = "CREATE_DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "RETWEET_COUNT")
    private int retweetCount;

    @Column(name = "RETWEETED")
    private boolean retweeted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IN_REPLY_TO_USER_ID")
    private User inReplyToUser;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "HASHTAGS", joinColumns = @JoinColumn(name = "TWEET_ID"))
    private List<String> hashtags;

    @ElementCollection(fetch = FetchType.EAGER)
    @ManyToMany
    @JoinTable(name = "USER_MENTIONS",
            joinColumns = @JoinColumn(name = "TWEET_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
    private List<User> userMentions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }

    public User getInReplyToUser() {
        return inReplyToUser;
    }

    public void setInReplyToUser(User inReplyToUser) {
        this.inReplyToUser = inReplyToUser;
    }

    public List<String> getHashtags() {
        if (hashtags == null) {
            this.hashtags = new ArrayList<>();
        }
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<User> getUserMentions() {
        if (userMentions == null) {
            userMentions = new ArrayList<>();
        }
        return userMentions;
    }

    public void setUserMentions(List<User> userMentions) {
        this.userMentions = userMentions;
    }

}
