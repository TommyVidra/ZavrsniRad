import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeMap;

public class cvjetnoCrawler {

    public static LinkedList<String> menus = new LinkedList<>();
    public static String nonMenus = "";

    public static void cvjetno() throws IOException { //Rucak samo?

        TreeMap<String, String> LineMenu = new TreeMap<>();

        Document savska = Jsoup.connect("http://www.sczg.unizg.hr/prehrana/restorani/sd-cvjetno-naselje/").get(); //savksa

        Elements content = savska.select("div[class=place]");

        Elements menuContent = content.select("h6");

        menus.add(afterFormat(menuFoodSplit(menuContent.first().text().split(":")[1]).replaceAll("\n+", "\n")));
        nonMenus += afterFormat(menuFoodSplit(menuContent.get(1).text().split(":")[1]).replaceAll("\n+", "\n"));
        menus.add(afterFormat(menuFoodSplit(menuContent.get(2).text().split(":")[1]).replaceAll("\n+", "\n")));
//
        System.out.println(menus);
        System.out.println(nonMenus);
    }

    private static String afterFormat(String lines)
    {
        String temp = "";

        for(String s : lines.split("\n"))
        {
            if(!s.contains(")"))
                temp += s + "\n";
        }

        return temp;
    }

    private static String menuFoodFormater(String line)
    {
        String temp = "";

        if(line.length() > 3 && !line.contains("*"))
        {
            if(line.contains("/"))
                line = line.split("\\/")[0];
            else if(line.contains("("))
                line = line.split("\\(")[0];

            String[] tempArr = line.split(" ");
            boolean badString = false;
            for(int i = 0; i < tempArr.length; ++i)
            {

                if(tempArr[i].length() > 0)
                {
                    if(i == 0)
                        temp += tempArr[i].substring(0, 1).toUpperCase() + tempArr[i].toLowerCase().substring(1);

                    else if(badString && i == 1)
                        temp += tempArr[i].substring(0, 1).toUpperCase() + tempArr[i].toLowerCase().substring(1);

                    else
                        temp += " " + tempArr[i].toLowerCase();
                }
                else
                    badString = true;

            }
        }

        return temp;
    }

    private static String menuFoodSplit(String line)
    {
        String temp = "";

        for(String s : line.split(","))
        {
            temp += menuFoodFormater(s) + "\n";
        }

        return temp;
    }

}