import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class Pars {
    static HashSet<String> hashSet;
    public static void main(String[] args) throws IOException {
        searchSite("https://lenta.ru");
        System.out.println("Размер hashSet = " + searchSite("https://lenta.ru").size());

// Вывести в консоль записи
        Iterator<String> itr = searchSite("https://lenta.ru").iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next().toString());
        }

    }

    private static HashSet<String> searchSite(String site) throws IOException {
        hashSet = new HashSet<String>();
        Document doc = Jsoup.connect(site).get();
        for (int i = 0; i <doc.select("a").size() ; i++) {
            Element link = doc.select("a").get(i);
            String linkHref = link.attr("href");
            if(linkHref.startsWith("/"))
                hashSet.add("https://lenta.ru/"+linkHref);
        }
        return hashSet;
    }

}
