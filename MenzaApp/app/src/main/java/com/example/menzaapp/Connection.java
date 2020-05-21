package com.example.menzaapp;

import android.app.Activity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.os.AsyncTask;
import android.renderscript.Sampler;

import java.io.IOException;

public class Connection extends Activity {

    public static class myTask extends AsyncTask<Void, Void, Elements> {

        protected Elements doInBackground(Void... params) {
            Elements content = null;
            try {
                Document savska = Jsoup.connect("http://www.sczg.unizg.hr/prehrana/restorani/savska/").get();
                content = savska.select("div[class=newsItem subpage]");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content;
        }

        @Override
        protected void onPostExecute(Elements result) {

        }
    }

    private static Elements myMethod(Elements myValue) {
        return myValue;
    }
}
