package pl.agh.student.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
@org.springframework.context.annotation.Configuration
public class TwitterConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwitterConfiguration.class);

    private static List<Configuration> twitterConfigurations = new ArrayList<>();
    private static volatile int currentConfigPosition = 0;

    static {
        InputStream resourceAsStream = TwitterConfiguration.class.getClassLoader().getResourceAsStream("auth");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] authData = line.split(",");
                Configuration configuration = new ConfigurationBuilder()
                        .setOAuthConsumerKey(authData[0])
                        .setOAuthConsumerSecret(authData[1])
                        .setOAuthAccessToken(authData[2])
                        .setOAuthAccessTokenSecret(authData[3])
                        .build();
                twitterConfigurations.add(configuration);
            }
        } catch (IOException e) {
            LOGGER.error("Cannot get twitter account configuration");
        }
    }

    public synchronized Configuration getConfiguration() {
        return twitterConfigurations.get(currentConfigPosition++ % twitterConfigurations.size());
    }
}
