package crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class WordCounter
{
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<LinkData> links = new LinkedList<>();
    private Document htmlDocument;


    public void crawl(LinkData url, List<String> terms) throws Exception {
            Connection connection = Jsoup.connect(url.getLink()).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("**Failure** Retrieved something other than HTML");
                throw new Exception();
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            for(Element link : linksOnPage)
            {
                this.links.add(new LinkData(link.absUrl("href"), url.getDepth()));
            }
            for (String term:terms) {
                url.getTermsCount().add(this.searchForWord(term, this.htmlDocument));
            }
            url.calculateSum();
    }



    public int searchForWord(String searchWord, Document htmlDocument)
    {
        if(htmlDocument == null)
        {
            System.out.println("ERROR! Call crawl() before performing analysis on the document");
            return 0;
        }
        String bodyText = htmlDocument.body().text();
        int split = bodyText.toLowerCase().split(searchWord.toLowerCase(),-1).length-1;
        System.out.println(split);
        return split;
    }


    public List<LinkData> getLinks()
    {
        return this.links;
    }

}
