import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeMap;

public class Crawler {

    public static void main (String[] args) throws IOException {

        //Map containing line work times
        TreeMap<String, String> LineMenu = new TreeMap<>();

        System.out.println(odeonCrawler.getMenus());

        //To Do: napraviti metodu za connect
//        Document odeon = Jsoup.connect("http://odeon.hr/dnevni-meni-studentska-menza/").get(); //savksa
//        Elements menus = odeon.select("div[class=entry-content clearfix]");
//
//        Element date = menus.select("h3").first();
//        Elements food = menus.select("h3");
//        food.remove(0);
//
//        Elements divs = food.select("div");
//        Elements menu1 = divs.first().select("p");
//
//        String menu1Content = "";
//        for (String s : menu1.text().split("\\s+"))
//        {
//            if(Character.isUpperCase(s.charAt(0)) && menu1Content.length() > 2)
//                menu1Content += "\n" + s;
//            else if (Character.isUpperCase(s.charAt(0)))
//                menu1Content += s;
//            else
//                menu1Content += " " + s;
//        }

//        File myObj = new File("savska_sc_jsoup.html");
//        myObj.createNewFile();
//
//        for(Element e : savska.getAllElements())
//        {
//            try {
//                Files.write(Paths.get(myObj.getPath()), e.toString().getBytes(), StandardOpenOption.APPEND);
//            }catch (IOException ex) {
//                //exception handling left as an exercise for the reader
//            }
//        }


//        Elements content = savska.select("div[class=newsItem subpage]");
//
//
//        //first column -- canteen name -- Savska restoran
//        String canteenSC = content.select("p[style]").first().text();
//
//
//        //first column Canteen work info
//        Element mainContent1 = content.get(0);
//        //second column Canteen food menu
//        Elements mainContent2 = returnContent(content.get(1), "p");
//        //date of the menu, and the canteen
//        String date = wordFormater(mainContent2.select("p").get(1).text());
//        //line of canteen in SC
//        String line = wordFormater(mainContent2.select("p").get(3).text());
//        //non menu food
//        LinkedList<String> nonMenufood = nonMenuFood(mainContent2, "p");
//        //menus for lunch, diner
//        LinkedList<String> menuFood = menuFood(mainContent2, "p");
//        System.out.println(line);
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
