package com.example.menzaapp;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

public class Crawler {

    public static String lunchMenu = "";
    public static String lunchVegMenu = "";

    public static String dinerMenu = "";
    public static String dinerVegMenu = "";

    public static String lunchSide  = "";
    public static String lunchChoice = "";

    public static String dinerSide = "";
    public static String dinerChoice = "";

    public static LinkedList<String> menusL;

    public static void ScLijevo() throws IOException, ExecutionException, InterruptedException {

            //Map containing line work times
            TreeMap<String, String> LineMenu = new TreeMap<>();

            Connection.myTask task = new Connection.myTask();
            task.execute("http://www.sczg.unizg.hr/prehrana/restorani/savska/");
            Document contentD = task.get();
            Elements content = contentD.select("div[class=newsItem subpage]");

            //String canteenSC = content.select("p[style]").first().text();


            //first column Canteen work info
            //Element mainContent1 = content.get(0);
            //second column Canteen food menu
            Elements mainContent2 = returnContent(content.get(1), "p");
            //System.out.println(content.get(1));
            //date of the menu, and the canteen
            String date = wordFormater(mainContent2.select("p").get(1).text());
            //line of canteen in SC
            String line = wordFormater(mainContent2.select("p").get(3).text());
            //non menu food
            LinkedList<String> nonMenufood = nonMenuFood(mainContent2, "p");
            //menus for lunch, diner
            LinkedList<String> menuFood = menuFood(mainContent2, "p");
            String meni = "";
            String nonMeni = "";

            int counter = 0;
            for(String s : menuFood)
            {
                if(counter == 0)
                    lunchMenu = s;
                else if(counter == 1)
                    lunchVegMenu = s;
                else if(counter == 2)
                    dinerMenu = s;
                else
                    dinerVegMenu = s;
                ++counter;
            }
            counter = 0;
            for(String s: nonMenufood)
            {
                if(counter == 0)
                    lunchChoice = s;
                else if(counter == 1)
                    lunchSide = s;
                else if(counter == 2)
                    dinerChoice = s;
                else
                    dinerSide = s;
                ++counter;
            }
            System.out.println(nonMenufood.toString());

        }

    public static void odeon() throws ExecutionException, InterruptedException {

        LinkedList<String> menusList = new LinkedList<>();

        Connection.myTask task = new Connection.myTask();
        task.execute("http://odeon.hr/dnevni-meni-studentska-menza/");
        Document odeon = task.get();
        Elements menus = odeon.select("div[class=entry-content clearfix]");

        Element date = menus.select("h3").first();
        Elements food = menus.select("h3");
        food.remove(0);

        Elements divs = food.select("div");
        Elements menu1 = divs.first().select("p");

        for(Element e : divs.select("p"))
            if(e.text().length() > 2)
                menusList.add(getMenu(e));

        menusL = menusList;
    }

    public static void cassandra() throws ExecutionException, InterruptedException {
        LinkedList<String> menusList = new LinkedList<>();

        Connection.myTask task = new Connection.myTask();
        task.execute("http://www.cassandra.hr/studentski-menu/");

        Document cassandra = task.get();
        System.out.println(cassandra);
        Elements menus = cassandra.select("div[class=entry-content]");
        Elements content = menus.select("p").select("b");
        content.remove(0);

        for(Element e : content)
            if(e.text().length() > 6)
                menusList.add(getMenu(e));

        menusL = menusList;
    }

    private static String getMenu(Element element)
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
                    temp += " " + tempArr[i];

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
                if(Character.isDigit(e.text().charAt(0)))
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

    private static String menuFoodSplit(String line)
    {
        String temp = "";

        for(String s : line.split(","))
        {
            temp += menuFoodFormater(s) + "\n";
        }

        return temp;
    }

    private static LinkedList<String> menuFood(Elements ele, String patern)
    {
        String temp = "";

        for(int i = 0; i < ele.select(patern).size(); ++i)
        {
            if(ele.get(i).text().toLowerCase().contains("menu"))
            {
                temp += ele.get(i + 1).text() + "\n";
            }
        }

        LinkedList<String> menus = new LinkedList<>();
        int i = 0;
        for(String s : temp.split("\n"))
        {
            menus.add(menuFoodSplit(s).replaceAll("\n+", "\n"));
        }

        return menus;
    }
}
