package com.example.menzaapp;

import android.app.ActivityOptions;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class test extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.drawer_icon); // mjenja ikonu ladice

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = mDrawer.findViewById(R.id.nvView);
        Menu menu = nvDrawer.getMenu();

        initCards();
        final CardView sc_canteen = findViewById(R.id.sc_savska);
        sc_canteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(sc_canteen);
            }
        });

        nvDrawer = findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.savska){
                    itemClick();
                }
                return false;
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

//    public void animateIntentItem(MenuItem item) {
//        // Ordinary Intent for launching a new activity
//        Intent intent = new Intent(this, CanteenView_SC.class);
//        // Get the transition name from the string
//        String transitionName = getString(R.string.transition_string);
//        // Define the view that the animation will start from
//        MenuItem viewStart = item;
//        ActivityOptionsCompat options =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
//                        viewStart,   // Starting view
//                        transitionName    // The String
//                );
//        //Start the Intent
//        ActivityCompat.startActivity(this, intent, options.toBundle());
//    }

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

    public void itemClick()
    {
        startActivity(new Intent(this, CanteenView_SC.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
