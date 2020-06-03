package com.example.menzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

public class CanteenMainViews_neKoristiSe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.canteen_main_nekoristise);

        initCards();
        final CardView sc_canteen = findViewById(R.id.sc_savska);

        sc_canteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(sc_canteen);
            }
        });
    }

    private void initCards()
    {
        CardView sc_canteen = findViewById(R.id.sc_savska);
        initViews(sc_canteen, configs.names[0], configs.dinner, configs.imgIds[0]);

        CardView fer = findViewById(R.id.fer_cassandra);
        initViews(fer, configs.names[1], configs.lunch, configs.imgIds[1]);

        CardView arh = findViewById(R.id.arh_odeon);
        initViews(arh, configs.names[2], configs.lunch, configs.imgIds[2]);

        CardView sava = findViewById(R.id.sava);
        initViews(sava, configs.names[3], configs.dinner, configs.imgIds[3]);
    }

    private void initViews(CardView c, String name, String content, int id)
    {
        ImageView im = c.findViewById(R.id.imageView);
        im.setImageResource(id);
        TextView nameTemp = c.findViewById(R.id.name);
        nameTemp.setText(name);
        TextView contentTemp = c.findViewById(R.id.content);
        contentTemp.setText(content);
    }
        public void animateIntent(CardView view) {

            // Ordinary Intent for launching a new activity
            Intent intent = new Intent(this, CanteenView_SC.class);

            // Get the transition name from the string
            String transitionName = getString(R.string.transition_string);

            // Define the view that the animation will start from
            View viewStart = view;

            ActivityOptionsCompat options =

                    ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                            viewStart,   // Starting view
                            transitionName    // The String
                    );
            //Start the Intent
            ActivityCompat.startActivity(this, intent, options.toBundle());

        }

    public void animateIntentFER(View view) {

//        Intent intent = new Intent(CanteenMainViews_neKoristiSe.this, test_view.class);
//        startActivity(intent);
    }
}

