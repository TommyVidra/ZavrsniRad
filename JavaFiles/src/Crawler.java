import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Crawler {

    public static void main (String[] args) throws IOException {

        //Map containing line work times
        TreeMap<String, String> LineMenu = new TreeMap<>();

        //To Do: napraviti metodu za connect
        Document savska = Jsoup.connect("http://www.sczg.unizg.hr/prehrana/restorani/savska/").get();
        Elements content = savska.select("div[class=newsItem subpage]");


        //first column -- canteen name -- Savska restoran
        String canteenSC = content.select("p[style]").first().text();


        //first column Canteen work info
        Element mainContent1 = content.get(0);
        //second column Canteen food menu
        Elements mainContent2 = returnContent(content.get(1), "p");
        //date of the menu, and the canteen
        String datePlace = "";

        //returnes keys for our map
        LinkedList<String> canteenLines = returntElements(mainContent1, new LinkedList<String>(
                List.of("lini", "Expr")), "p");

        //retunes values for our keys
        LinkedList<String> canteenLinesTime = returntElements(mainContent1, new LinkedList<String>(
                List.of("ruča", "veče", "PONE", "SUBO")), "ul");

        //returns default work time for canteen
        LinkedList<String> defaultWorkTime = returntElements(mainContent1, new LinkedList<String>(
                List.of("Petk", "Subo")), "p" );

        String temp = "\n";
        for(String s : defaultWorkTime)
        {
            temp += s + "\n";
        }

        //Canteen work time has ben initialized to LineMenu
        for(String s : canteenLines)
        {
            if(s.contains("lijevo"))
                LineMenu.put(s.toUpperCase(), canteenLinesTime.get(canteenLines.indexOf(s)).toUpperCase() + temp.toUpperCase());
            else
                LineMenu.put(s.toUpperCase(), canteenLinesTime.get(canteenLines.indexOf(s)).toUpperCase());
        }


        //Vege menu and regular menu
        LinkedList<String> Menus = menuHandler(mainContent2);

        LinkedList<String> Food = foodHandler(mainContent2);
        for(String s : Food)
        {
            System.out.println("#" + s);
        }
    }

    private static Elements returnContent(Element element, String parm)
    {

        Elements list = new Elements();

        //Goes through elements and stores elements that are not blank to a new list
        for(Element e : element.select(parm)){
            if(!e.hasText() && e.isBlock())
                continue;
            else
                list.add(e);
        }

        return list;
    }

    private static LinkedList<String> returntElements(Element e, List<String> parm, String s)
    {

        LinkedList<String> list = new LinkedList<>();

        for(Element ei : e.select(s))
        {
            //adds all of elements that we are intrested in -- they need to start with a 5 sequence string
            if(parm.contains(ei.text().substring(0, 4)))
                list.add(ei.text());
        }
        return list;
    }

    private static LinkedList<String> menuHandler(Elements e)
    {
        LinkedList<String> menu = new LinkedList<>();

        for(int i = 0; i < e.size(); ++i)
        {
            if(e.get(i).text().contains("MENU"))
            {
                menu.add(e.get(i+1).text().toUpperCase());
            }
        }
        return menu;
    }

    private static LinkedList<String> foodHandler(Elements e)
    {
        LinkedList<String> food = new LinkedList<>();

        for(int i = 0; i < e.size(); ++i)
        {
            if(e.get(i).text().contains(":"))
            {
                ++i;
                int j = 1;
                while(j < 3 && i < e.size())
                {
                    food.add(e.get(i).text());
                    ++i;
                    if(!e.get(i -1 ).hasText())
                        ++j;
                }
            }
        }
        return food;
    }
}
