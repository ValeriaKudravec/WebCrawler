package crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class WordCounterTest {

    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private static final String link = "https://en.wikipedia.org/wiki/South_Africa";
    private WordCounter wordCounter = new WordCounter();

    @Test
    public void testSearchForWord(){
        try {
            Connection connection = Jsoup.connect(link).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            System.out.println(htmlDocument == null);
            Assert.assertTrue(wordCounter.searchForWord("South", htmlDocument) == 551 );
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
