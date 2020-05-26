import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class cassandraCrawler {

    public static LinkedList<String> getMenus() throws IOException {
        LinkedList<String> menusList = new LinkedList<>();

        File cassandra = new File("cassandra.html");
        Document odeon = Jsoup.parse(cassandra, "UTF-8"); //savksa
        Elements menus = odeon.select("div[class=entry-content]");
        Elements content = menus.select("p").select("b");
        content.remove(0);

        for(Element e : content)
            if(e.text().length() > 6)
                menusList.add(odeonCrawler.getMenu(e));

        return menusList;
    }
}
