package crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LinkData {

    private String link;
    private int depth;
    private int allCount;
    private List<Integer> termsCount;

    public LinkData(String link){
        this.link = link;
        this.depth = 0;
        this.allCount = 0;
        this.termsCount = new ArrayList<>();
    }

    public LinkData(String link, int depth){
        this.link = link;
        this.depth = depth+1;
        this.allCount = 0;
        this.termsCount = new ArrayList<>();
    }


    public void calculateSum(){
       int  sum = 0;
        for (Integer countTerm:termsCount) {
            sum+=countTerm;
        }
        this.allCount = sum;
    }

    public void addCount(){
        this.depth++;
    }

    public void setTermsCount(List<Integer> termsCount1) {
        this.termsCount.addAll(termsCount1);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public List<Integer> getTermsCount() {
        return termsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkData)) return false;
        LinkData linkData = (LinkData) o;
        return link.equals(linkData.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\"").append(link).append("\",\"")
                .append(depth).append("\",\"").append(allCount).append("\",\"");
        termsCount.forEach(item->stringBuilder.append("\"").append(item).append("\","));
        return stringBuilder.toString();
    }
}
