package pl.agh.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.student.persistence.model.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service("dataMiner")
public class TwitterDataMiner {

    private final static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(TweeterExtractor.class);

    @Autowired
    private TweeterExtractor twitterExtractor;

    private Queue<UserApplyCoordinates> usersMap = new PriorityQueue<>();
    private Map<String, UserApplyCoordinates> userCache = new HashMap<>();

    public List<String> getInitialAccount() {
        final List<String> result = new ArrayList<>();
        final InputStream resourceAsStream = TwitterDataMiner.class.getClassLoader().getResourceAsStream("salt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            LOGGER.error("Cannot read salt file", e);
        }
        return result;
    }

    public void extractData() {
        UserApplyCoordinates userCoordinates;
        while( (userCoordinates = usersMap.poll())!= null) {
            if (userCoordinates.applly()){
                extractDataFromUser(userCoordinates);
            }
        };
    }

    private void extractDataFromUser(UserApplyCoordinates userCoordinates) {
        Set<Tweet> tweets = twitterExtractor.getAvailableTweetsByAccountUsername(userCoordinates.getUsername());
//        Set<User>
    }

    public static void main(String... args) {
        TwitterDataMiner twitterDataMiner = new TwitterDataMiner();
        twitterDataMiner.getInitialAccount().stream().forEach(System.err::println);
    }

}
