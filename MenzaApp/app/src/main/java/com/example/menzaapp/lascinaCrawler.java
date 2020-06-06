package com.example.menzaapp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class lascinaCrawler {

    public static LinkedList<String> dinnerMenu = new LinkedList<>();
    public static LinkedList<String> lunchMenu = new LinkedList<>();
    public static LinkedList<String> dinnerChoiceL = new LinkedList<>();
    public static LinkedList<String> lunchChoiceL = new LinkedList<>();
    public static LinkedList<String> dinnerSideL = new LinkedList<>();
    public static LinkedList<String> lunchSideL = new LinkedList<>();

    public static String dinnerMenuS = "";
    public static String dinnerChoiceS = "";
    public static String lunchMenuS = "";
    public static String dinnerSideS = "";
    public static String lunchSideS = "";
    public static String lunchChoiceS = "";

    public static void lascina() throws IOException, ExecutionException, InterruptedException {

        dinnerMenuS = ""; dinnerChoiceS = ""; lunchMenuS = ""; dinnerSideS = ""; lunchSideS = ""; lunchChoiceS = "";

        Connection.myTask task = new Connection.myTask();
        task.execute("http://www.sczg.unizg.hr/prehrana/restorani/sd-lascina/");
        Document doc = task.get();

        Elements content = doc.select("div[class=newsItem subpage]");
        Elements mainContent2 = returnContent(content.get(1), "p");

        menuFood(mainContent2, "p");
        nonMenuFood(mainContent2, "p");

        for(String s : dinnerChoiceL)
            dinnerChoiceS += s + "\n";
        for(String s : lunchChoiceL)
            lunchChoiceS += s + "\n";

        for(String s : dinnerMenu)
            dinnerMenuS += s + "\n";
        for(String s : lunchMenu)
            lunchMenuS += s + "\n";

        for(String s : dinnerSideL)
            dinnerSideS += s + "\n";
        for(String s : lunchSideL)
            lunchSideS += s + "\n";

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

    private static void nonMenuFood(Elements ele, String patern)
    {
        String dinnerChoice = "";
        String lunchChoice = "";
        String dinnerSide = "";
        String lunchSide = "";


        for(int i = 0; i < ele.select(patern).size(); ++i)
        {
            if(ele.get(i).text().toLowerCase().contains("izbor"))
            {
                if(lunchChoice.length() < 2)
                {
                    ++i;
                    while(!ele.get(i).text().toLowerCase().contains("prilog")){
                        lunchChoice += ele.get(i).text() + "\n";
                        ++i;
                    }
                    ++i;
                    while(!ele.get(i).text().toLowerCase().contains("menu"))
                    {
                        lunchSide += ele.get(i).text() + "\n";
                        ++i;
                    }
                }
                else if(dinnerChoice.length() < 2) //nadodati jos ako je neki dan ili nesto?
                {
                    ++i;
                    while(!ele.get(i).text().toLowerCase().contains("prilog")){
                        dinnerChoice += ele.get(i).text() + "\n";
                        ++i;
                    }
                    ++i;
                    while(!(ele.get(i).text().length() < 2))
                    {
                        dinnerSide += ele.get(i).text() + "\n";
                        ++i;
                    }
                }
                else if(dinnerChoice.length() > 2 && lunchChoice.length() > 2)
                    break;
            }
        }

        LinkedList<String> dinnerChoices = new LinkedList<>();
        LinkedList<String> lunchChoices = new LinkedList<>();
        LinkedList<String> dinnerSides = new LinkedList<>();
        LinkedList<String> lunchSides = new LinkedList<>();

        for(String s : lunchChoice.split("\n"))
            lunchChoices.add(menuFoodFormater(s).replaceAll("\n+", "\n"));
        for(String s : dinnerChoice.split("\n"))
            dinnerChoices.add(menuFoodFormater(s).replaceAll("\n+", "\n"));
        for(String s : lunchSide.split("\n"))
            lunchSides.add(menuFoodFormater(s).replaceAll("\n+", "\n"));
        for(String s : dinnerSide.split("\n"))
            dinnerSides.add(menuFoodFormater(s).replaceAll("\n+", "\n"));

        lunchChoiceL = lunchChoices;
        dinnerChoiceL = dinnerChoices;
        lunchSideL = lunchSides;
        dinnerSideL = dinnerSides;
    }

    private static String menuFoodFormater(String line)
    {
        String temp = "";

        if(line.length() > 3)
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

    private static void menuFood(Elements ele, String patern)
    {
        String dinner = "";
        String lunch = "";

        for(int i = 0; i < ele.select(patern).size(); ++i)
        {
            if(ele.get(i).text().toLowerCase().contains("menu"))
            {
                if(lunch.length() < 2)
                {
                    ++i;
                    while(!ele.get(i).text().toLowerCase().contains("izbor")){
                        lunch += ele.get(i).text() + "\n";
                        ++i;
                    }
                }
                else if(dinner.length() < 2)
                {
                    ++i;
                    while(!ele.get(i).text().toLowerCase().contains("izbor")){
                        dinner += ele.get(i).text() + "\n";
                        ++i;
                    }
                }
                else if(dinner.length() > 2 && lunch.length() > 2)
                    break;
            }
        }

        LinkedList<String> dinnerMenus = new LinkedList<>();
        LinkedList<String> lunchMenus = new LinkedList<>();

        for(String s : lunch.split("\n"))
            lunchMenus.add(menuFoodFormater(s).replaceAll("\n+", "\n"));
        for(String s : dinner.split("\n"))
            dinnerMenus.add(menuFoodFormater(s).replaceAll("\n+", "\n"));

        lunchMenu = lunchMenus;
        dinnerMenu = dinnerMenus;
    }
}
