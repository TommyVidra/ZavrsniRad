package com.example.menzaapp;

import android.app.Activity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.os.AsyncTask;
import android.renderscript.Sampler;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Connection extends Activity {

    public static class myTask extends AsyncTask<String, Void, Document> {

        protected Document doInBackground(String... params) {
            String url = params[0];
            //Elements content = null;
            Document temp = null;
            try {
                temp = Jsoup.connect(url).get();
                System.out.println("TU SMO \n \n \n \n #################" );
                //Document savska = Jsoup.connect("http://192.168.0.19:8080").get();

                //content = savska.select("div[class=newsItem subpage]");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return temp;
        }

        @Override
        protected void onPostExecute(Document result) {

        }
    }

    private static Elements myMethod(Elements myValue) {
        return myValue;
    }
}
