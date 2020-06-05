package com.example.menzaapp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class ekonomijaCrawler {

    public static LinkedList<String> lunchMenu = new LinkedList<>();
    public static LinkedList<String> vegMenu = new LinkedList<>();
    public static LinkedList<String> lunchChoiceL = new LinkedList<>();
    public static LinkedList<String> lunchSideL = new LinkedList<>();

    public static String menu = "";
    public static String veg = "";
    public static String choice = "";
    public static String side = "";

    public static void ekonomija() throws ExecutionException, InterruptedException {

        Connection.myTask task = new Connection.myTask();
        task.execute("http://www.sczg.unizg.hr/prehrana/restorani/ekonomija/");
        Document doc = task.get();

        Elements content = doc.select("div[class=newsItem subpage]");
        Elements mainContent2 = returnContent(content.get(1), "p");

        menuFood(mainContent2, "p");
        nonMenuFood(mainContent2, "p");

        for(String s : lunchMenu)
            menu += s + "\n";
        for(String s : vegMenu)
            veg += s + "\n";
        for(String s : lunchChoiceL)
            choice += s + "\n";
        for(String s : lunchSideL)
            side += s + "\n";

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
        String lunchSide = "";


        for (int i = 0; i < ele.select(patern).size(); ++i) {
            if (ele.get(i).text().toLowerCase().contains("izbor")) {
                if (lunchChoice.length() < 2) {
                    ++i;
                    while (!ele.get(i).text().toLowerCase().contains("prilog")) {
                        lunchChoice += ele.get(i).text() + "\n";
                        ++i;
                    }
                    ++i;
                    while (!ele.get(i).text().toLowerCase().contains("napomena")) {
                        lunchSide += ele.get(i).text() + "\n";
                        ++i;
                    }
                } else if (lunchChoice.length() > 2)
                    break;
            }
        }

        LinkedList<String> lunchChoices = new LinkedList<>();
        LinkedList<String> lunchSides = new LinkedList<>();

        for (String s : lunchChoice.split("\n"))
            lunchChoices.add(menuFoodFormater(s).replaceAll("\n+", "\n"));
        for (String s : lunchSide.split("\n"))
            lunchSides.add(menuFoodFormater(s).replaceAll("\n+", "\n"));

        lunchChoiceL = lunchChoices;
        lunchSideL = lunchSides;
    }

    private static String menuFoodFormater(String line) {
        String temp = "";

        if (line.length() > 3) {
            if (line.contains("/"))
                line = line.split("\\/")[0];
            else if (line.contains("("))
                line = line.split("\\(")[0];

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
        }

        return temp;
    }

    private static void menuFood(Elements ele, String patern) {
        String lunch = "";
        String veg = "";

        for (int i = 0; i < ele.select(patern).size(); ++i) {
            if (ele.get(i).text().toLowerCase().contains("menu")) {
                if (lunch.length() < 2) {
                    ++i;
                    while (!ele.get(i).text().toLowerCase().contains("vegetarijanski")) {
                        lunch += ele.get(i).text() + "\n";
                        ++i;
                    }
                    --i;
                }
                else if (veg.length() < 2) {
                    ++i;
                    while (!ele.get(i).text().toLowerCase().contains("izbor")) {
                        veg += ele.get(i).text() + "\n";
                        ++i;

                    }
                }
                else if (veg.length() > 2 && lunch.length() > 2)
                        break;
                }
            }

            LinkedList<String> lunchMenus = new LinkedList<>();
            LinkedList<String> vegMenus = new LinkedList<>();

            for (String s : lunch.split("\n"))
                lunchMenus.add(menuFoodFormater(s).replaceAll("\n+", "\n"));
            for (String s : veg.split("\n"))
                vegMenus.add(menuFoodFormater(s).replaceAll("\n+", "\n"));

            lunchMenu = lunchMenus;
            vegMenu = vegMenus;
        }
    }