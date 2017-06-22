
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Pars {
    static Set<String> erste;
    static Set<String> zweite;
    static Set<String> dritte;
    static String host = "https://lenta.ru";
    static Iterator<String> itr;
    static Iterator<String> itr2;

    public static void main(String[] args) throws IOException {
        erste = new HashSet<String>();
        zweite = new HashSet<String>();
        dritte = new HashSet<String>();
        searchSite(erste, "https://lenta.ru/", "https://lenta.ru");

        for (int i = 0; i < 2; i++) {
            dritte.addAll(erste);
            itr = dritte.iterator();
            while (itr.hasNext()) {
                try {
                    searchSite(zweite, "https://lenta.ru/", itr.next().toString());
                } catch (Exception e) {
                }
            }
            zweite.removeAll(erste);
            dritte.removeAll(dritte);
            dritte.addAll(zweite);
            erste.addAll(zweite);
        }

        System.out.println("Размер Set = " + erste.size());
        Iterator<String> itr2 = erste.iterator();
        while (itr2.hasNext()) {
            System.out.println("!!   " + itr2.next().toString());
        }
    }

    public static Set<String> searchSite(Set<String> set, String host, String site) {
        //hashSet = new HashSet<String>();
        Document doc = null;
        try {
            doc = Jsoup.connect(site).get();
        } catch (IOException e) {
        }
        for (int i = 0; i < doc.select("a").size(); i++) {
            Element link = doc.select("a").get(i);
            String linkHref = link.attr("href");
            if (linkHref.startsWith("/"))
                set.add(host + linkHref);
        }
        return set;
    }

}
