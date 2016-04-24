package pl.agh.student;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import pl.agh.student.configuration.AppConfig;
import twitter4j.TwitterException;

public class PoligatorMain {

    public static void main(String args[]) throws TwitterException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Poligator poligator = context.getBean(Poligator.class);

        poligator.testDb();
        poligator.testTwitter();

        context.close();
    }

}