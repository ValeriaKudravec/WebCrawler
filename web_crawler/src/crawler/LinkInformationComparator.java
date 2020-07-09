package crawler;

import java.util.Comparator;

public class LinkInformationComparator implements Comparator{

    @Override
    public int compare(Object o, Object t1) {
        LinkData l1 = (LinkData)o;
        LinkData l2 = (LinkData)t1;
        int res = l2.getAllCount()-l1.getAllCount();
        if(res == 0){
            return (int)l2.getLink().compareToIgnoreCase(l1.getLink());
        }
        return  l2.getAllCount()-l1.getAllCount();

    }
}
