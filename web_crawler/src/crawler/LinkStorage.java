package crawler;

import java.util.*;

public class LinkStorage {
    private static final int MAX_PAGES_TO_SEARCH = 10;
    private static final int MAX_DEPTH_TO_SEARCH = 8;
    private Set<LinkData> pagesVisited;
    private List<LinkData> pagesToVisit;

    public LinkStorage(){

        this.pagesVisited = new TreeSet<>(new LinkInformationComparator());
       this.pagesToVisit = new LinkedList<LinkData>();
    }
    public void search(String link, List<String> searchWord) {
        try {
            while (this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
                LinkData currentUrl;
                LinkData url = new LinkData(link);
                WordCounter leg = new WordCounter();
               currentUrl = findCurrentUrl(url);
               if(currentUrl.getDepth() >= 8){
                   break;
               }
                leg.crawl(currentUrl, searchWord);
                this.pagesVisited.add(currentUrl);
                this.pagesToVisit.addAll(leg.getLinks());
            }
        }
        catch (Exception e){
            return;
        }

    }

    private LinkData findCurrentUrl(LinkData url) throws Exception {
        LinkData currentUrl;
        if (this.pagesToVisit.isEmpty()) {
            return url;
        } else {
           return this.nextUrl();
        }
    }
    public List<LinkData> findResultInformation(String link, List<String> searchWord){
        this.search(link, searchWord);
        List<LinkData> result = new ArrayList<>();
        int i = 0;
        for (LinkData linkData: this.pagesVisited){
            if(i < 10) {
                result.add(linkData);
            }
            else {
                break;
            }
            i++;
        }
        return result;
    }

    private LinkData nextUrl() throws Exception{
        LinkData nextUrl;
        do {
            if(!this.pagesToVisit.isEmpty()){
                nextUrl = this.pagesToVisit.remove(0);
            }
            else {
                throw new Exception();
            }
        } while (this.pagesVisited.contains(nextUrl));
        return nextUrl;
    }

    public Set<LinkData> getPagesVisited() {
        return pagesVisited;
    }
}