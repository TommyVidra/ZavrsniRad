package com.example.menzaapp;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CanteenView_SC extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent intent = getIntent();
        setContentView(R.layout.canteen);
        TextView txt = this.findViewById(R.id.menuContent);
        TextView txt1 = this.findViewById(R.id.vegMenuContent);
        TextView txt2 = this.findViewById(R.id.choiceContent);
        TextView txt3 = this.findViewById(R.id.sideContent);
        TextView txt4 = this.findViewById(R.id.menuContent2);
        TextView txt5 = this.findViewById(R.id.vegMenuContent2);
        TextView txt6 = this.findViewById(R.id.choiceContent2);
        TextView txt7 = this.findViewById(R.id.sideContent2);

        if(configs.local)
        {
            System.out.println("TU SMO \n \n###############################");

            LocalCanteens_SC canteen = new LocalCanteens_SC();
            try {
                LocalCanteens_SC.ScLijevo();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            txt.setText(canteen.lunchMenu);
            txt1.setText(canteen.lunchVegMenu);
            txt2.setText(canteen.lunchChoice);
            txt3.setText(canteen.lunchSide);
            txt4.setText(canteen.dinerMenu);
            txt5.setText(canteen.dinerVegMenu);
            txt6.setText(canteen.dinerChoice);
            txt7.setText(canteen.dinerSide);

        }
        else
        {
            Crawler c = new Crawler();
            try {
                c.ScLijevo();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            txt.setText(c.lunchMenu);
            txt1.setText(c.lunchVegMenu);
            txt2.setText(c.lunchChoice);
            txt3.setText(c.lunchSide);
            txt4.setText(c.dinerMenu);
            txt5.setText(c.dinerVegMenu);
            txt6.setText(c.dinerChoice);
            txt7.setText(c.dinerSide);
        }

        //String value = intent.getStringExtra("key"); //if it's a string you stored.
//        EditText txt = this.findViewById(R.id.lunch);
//        EditText txt1 = this.findViewById(R.id.menu);
//        try {
//            String fill = Crawler.ScLijevo();
//            txt.setText(fill);
//
//        } catch (IOException | ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }

    }


}
