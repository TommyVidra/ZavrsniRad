import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;

public class odeonCrawler {

    public static LinkedList<String> getMenus() throws IOException {

        LinkedList<String> menusList = new LinkedList<>();

        Document odeon = Jsoup.connect("http://odeon.hr/dnevni-meni-studentska-menza/").get(); //savksa
        Elements menus = odeon.select("div[class=entry-content clearfix]");

        Element date = menus.select("h3").first();
        Elements food = menus.select("h3");
        food.remove(0);

        Elements divs = food.select("div");
        Elements menu1 = divs.first().select("p");

        for(Element e : divs.select("p"))
            if(e.text().length() > 2)
                menusList.add(getMenu(e));

        return menusList;
    }

    public static String getMenu(Element element)
    {
        String temp = "";

        for (String s : element.text().split("\\s+"))
        {
            if(s.length() > 1)
            {
                if(Character.isUpperCase(s.charAt(0)) && temp.length() > 2)
                    temp += "\n" + s;
                else if (Character.isUpperCase(s.charAt(0)))
                    temp += s;
                else
                    temp += " " + s;
            }
        }
        //System.out.println(temp);
        return temp;
    }
}
