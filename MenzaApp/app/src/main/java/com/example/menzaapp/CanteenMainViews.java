package com.example.menzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

public class CanteenMainViews extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent intent = getIntent();
        setContentView(R.layout.canteen_main);
        //String value = intent.getStringExtra("key"); //if it's a string you stored.

//        View cardView = findViewById(R.id.cardView);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CanteenMainViews.this, CanteenView_SC.class));
//            }
//        });
    }
        public void animateIntent(View view) {

            // Ordinary Intent for launching a new activity
            Intent intent = new Intent(this, CanteenView_SC.class);

            // Get the transition name from the string
            String transitionName = getString(R.string.transition_string);

            // Define the view that the animation will start from
            View viewStart = findViewById(R.id.cardView);

            ActivityOptionsCompat options =

                    ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                            viewStart,   // Starting view
                            transitionName    // The String
                    );
            //Start the Intent
            ActivityCompat.startActivity(this, intent, options.toBundle());

        }

    public void animateIntentFER(View view) {

//        Intent intent = new Intent(CanteenMainViews.this, test_view.class);
//        startActivity(intent);

    }
    }

