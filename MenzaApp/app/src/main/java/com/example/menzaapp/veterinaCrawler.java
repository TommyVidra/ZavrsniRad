package com.example.menzaapp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class veterinaCrawler {

    public static LinkedList<String> lunchMenu = new LinkedList<>();

    public static String choiceS = "";
    public static String sideS = "";
    public static String menuS = "";


    public static void veterina() throws ExecutionException, InterruptedException {

        Connection.myTask task = new Connection.myTask();
        task.execute("http://www.sczg.unizg.hr/prehrana/restorani/veterina/");
        Document doc = task.get();

        Elements content = doc.select("div[class=newsItem subpage]");
        Elements mainContent2 = returnContent(content.get(1), "p");

        menuFood(mainContent2, "p");
        LinkedList<String> nonMenus = nonMenuFood(mainContent2, "p");
        choiceS = nonMenus.get(0); sideS = nonMenus.get(1);

        for(String s : lunchMenu)
            menuS += s + "\n";

    }

    private static String wordFormater(String s)
    {
        String temp = "";
        String[] tempArr = s.split("\\s+");
        boolean badString = false;

        for (int i = 0; i < tempArr.length; ++i) {

            if(tempArr[i].length() > 0)
            {
                if(tempArr[i].equals("\\s+"))
                    continue;

                if(i == 0)
                    temp += tempArr[i].substring(0, 1).toUpperCase() + tempArr[i].toLowerCase().substring(1);

                else if(badString && i == 1)
                    temp += tempArr[i].substring(0, 1).toUpperCase() + tempArr[i].toLowerCase().substring(1);

                else if(tempArr[i].matches("\\d+.") || tempArr[i].matches("[a-z]."))
                    temp += tempArr[i];

                else
                    temp +=" " + tempArr[i].toLowerCase();
            }
            else
                badString = true;
        }

        return temp;
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

    private static LinkedList<String> nonMenuFoodFormater(String food)
    {
        LinkedList<String> tempList = new LinkedList<>();
        int max = 0;
        String temp = "";

        for(String s : food.split("\n"))
        {
            if(max > Integer.parseInt(s.substring(0, 1)))
            {
                tempList.add(temp);
                temp = s + "\n";
                max = Integer.parseInt(s.substring(0, 1));
            }
            else
            {
                temp += s + "\n";
                max = Integer.parseInt(s.substring(0, 1));
            }
        }
        tempList.add(temp);
        //System.out.println(tempList);
        return tempList;
    }

    private static LinkedList<String> nonMenuFood(Elements ele, String patern)
    {
        String temp = "";

        //za provjeru ako pocnje s brojem za priloge i izbor
        for (Element e : ele.select(patern)){
            if(e.text().length() > 2){
                if(Character.isDigit(e.text().charAt(0)) && (e.text().split("\\.").length < 3))
                {
                    //System.out.println(e);
                    if(e.text().contains("/"))
                        temp += e.text().substring(0, 2) + " " + wordFormater(e.text().split("\\/")[0].substring(2)) + "\n";
                    else if(e.text().contains("("))
                        temp += e.text().substring(0, 2) + " " + wordFormater(e.text().split("\\(")[0].substring(2)) + "\n";
                    else
                        temp += e.text().substring(0, 2) + " " + wordFormater(e.text().substring(2)) + "\n";

                }
            }
        }

        LinkedList<String> tempList = nonMenuFoodFormater(temp);

        return tempList;
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