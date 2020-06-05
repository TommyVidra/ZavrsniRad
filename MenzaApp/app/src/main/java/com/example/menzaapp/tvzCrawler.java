package com.example.menzaapp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class tvzCrawler{

    public static LinkedList<String> lunchMenu = new LinkedList<>();
    public static LinkedList<String> lunchChoiceL = new LinkedList<>();

    public static String menuS = "";
    public static String choiceS = "";

    public static void tvz() throws ExecutionException, InterruptedException {

        Connection.myTask task = new Connection.myTask();
        task.execute("http://www.sczg.unizg.hr/prehrana/restorani/restoran-tvz/");
        Document doc = task.get();

        Elements content = doc.select("div[class=newsItem subpage]");
        Elements mainContent2 = returnContent(content.get(1), "p");

        menuFood(mainContent2, "p");
        nonMenuFood(mainContent2, "p");

        for(String s : lunchChoiceL)
            choiceS += s + "\n";
        for(String s : lunchMenu)
            menuS += s + "\n";
    }

    private static Elements returnContent(Element element, String parm) {

        Elements list = new Elements();

        //Goes through elements and stores elements that are not blank to a new list
        for (Element e : element.select(parm)) {
            if (!e.hasText() && e.isBlock())
                continue;
            else
                list.add(e);
        }

        return list;
    }

    private static void nonMenuFood(Elements ele, String patern) {
        String lunchChoice = "";


        for (int i = 0; i < ele.select(patern).size(); ++i) {
            if (ele.get(i).text().toLowerCase().contains("izbor")) {
                if (lunchChoice.length() < 2) {
                    ++i;
                    while (!ele.get(i).text().toLowerCase().contains("napomena")) {
                        lunchChoice += ele.get(i).text() + "\n";
                        ++i;
                    }
                } else
                    break;
            }
        }

        LinkedList<String> lunchChoices = new LinkedList<>();

        for (String s : lunchChoice.split("\n"))
            lunchChoices.add(menuFoodFormater(s).replaceAll("\n+", "\n"));

        lunchChoiceL = lunchChoices;
    }

    private static String commaSeparator(String line)
    {
        String temp = "";

        for (String l : line.split(","))
        {
            if(l.length() > 3)
            {
                if(l.contains("(")) {
                    l = l.split("\\(")[0];
                    temp += l + " ,";
                }
            }
        }

        return temp;
    }

    private static String firstCapital(String line)
    {
        String temp = "";

        String[] tempArr = line.split(" ");
        boolean badString = false;
        for (int i = 0; i < tempArr.length; ++i) {

            if (tempArr[i].length() > 0) {
                if (i == 0)
                    temp += tempArr[i].substring(0, 1).toUpperCase() + tempArr[i].toLowerCase().substring(1);

                else if (badString && i == 1)
                    temp += tempArr[i].substring(0, 1).toUpperCase() + tempArr[i].toLowerCase().substring(1);

                else
                    temp += " " + tempArr[i].toLowerCase();
            } else
                badString = true;
        }

        return temp;
    }

    private static String menuFoodFormater(String line) {
        String temp = "";
        boolean multi = false;

        if (line.length() > 3) {

            if (line.contains("/"))
                line = line.split("\\/")[0];

            else if (line.contains(","))
            {
                line = commaSeparator(line);
                multi = true;
            }

            else if (line.contains("("))
                line = line.split("\\(")[0];


            if (multi)
            {
                for(int i = 0; i < line.split(",").length; ++i)
                {
                    if(i + 1 == line.split(",").length)
                         temp += firstCapital(line.split(",")[i]);
                    else
                        temp += firstCapital(line.split(",")[i]) + ", ";
                }
            }
            else
                temp += firstCapital(line);
        }

        return temp;
    }

    private static void menuFood(Elements ele, String patern) {
        String lunch = "";

        for (int i = 0; i < ele.select(patern).size(); ++i) {
            if (ele.get(i).text().toLowerCase().contains("menu")) {
                if (lunch.length() < 2) {
                    ++i;
                    while (!ele.get(i).text().toLowerCase().contains("izbor")) {
                        lunch += ele.get(i).text() + "\n";
                        ++i;
                    }
                }
                else
                    break;
            }
        }

        LinkedList<String> lunchMenus = new LinkedList<>();

        for (String s : lunch.split("\n"))
            lunchMenus.add(menuFoodFormater(s).replaceAll("\n+", "\n"));

        lunchMenu = lunchMenus;
    }
}