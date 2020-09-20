package com.example.menzaapp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

public class cvjetnoCrawler {

    public static LinkedList<String> menus = new LinkedList<>();
    public static String menu ="";
    public static String veg ="";
    public static String nonMenus = "";
    //NE RADI - Kod treba ispraviti
    public static void cvjetno() throws ExecutionException, InterruptedException { //Rucak samo?

        menu =""; veg =""; nonMenus = "";

        Connection.myTask task = new Connection.myTask();
        task.execute("http://www.sczg.unizg.hr/prehrana/restorani/sd-cvjetno-naselje/");
        Document doc = task.get();

        Elements content = doc.select("div[class=place]");
        Elements menuContent = content.select("h6");
        System.out.println(menuContent);

        menus.add(afterFormat(menuFoodSplit(menuContent.first().text().split(":")[1]).replaceAll("\n+", "\n")));
        System.out.println(menus);
        nonMenus += afterFormat(menuFoodSplit(menuContent.get(1).text().split(":")[1]).replaceAll("\n+", "\n"));
        System.out.println(nonMenus);
        menus.add(afterFormat(menuFoodSplit(menuContent.get(2).text().split(":")[1]).replaceAll("\n+", "\n")));
        System.out.println(menus);

        menu = menus.get(0);
        veg = menus.get(1);
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