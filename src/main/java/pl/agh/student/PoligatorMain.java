package pl.agh.student;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import pl.agh.student.configuration.AppConfig;
import twitter4j.TwitterException;

public class PoligatorMain {

    public static void main(String args[]) throws TwitterException, FileNotFoundException, IOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Poligator poligator = context.getBean(Poligator.class);
        
        List<String> accounts = new LinkedList<String>();
        
        String line;
        try (
            InputStream fis = new FileInputStream("accounts.txt");
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
        ) {
            while ((line = br.readLine()) != null) {
                accounts.add(line);
            }
        }

//        poligator.testDb();
//        poligator.testTwitter();
        poligator.pullData(accounts);

        context.close();
    }

}