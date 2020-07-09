package crawler;

import crawler.LinkStorage;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LinkStorageTest
{
    private LinkStorage linkStorage;
    private static final String link = "https://en.wikipedia.org/wiki/South_Africa";
    private List<String> terms;

    @Before
    public void setup() {
        linkStorage = new LinkStorage();
        terms = new ArrayList<>();
        terms.add("South");
        terms.add(" Africa");
    }

    @Test
    public void testSearch() {
        linkStorage.search(link, terms);
        assertFalse(linkStorage.getPagesVisited().isEmpty());
    }

    @Test
    public void testFindCurrentUrl(){
        assertTrue(linkStorage.findResultInformation(link, terms).size() == 10);
    }

}