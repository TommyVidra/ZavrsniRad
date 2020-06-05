package com.example.menzaapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.ExecutionException;

public class medicinaView  extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    TextView lunch;
    TextView menu1;
    TextView menu1Content;
    TextView side;
    TextView sideContent;
    TextView choice;
    TextView choiceContent;

    //ovdje su ovi parametri kako bi ih mogli staviti nevidljivima
    TextView menu2;
    TextView menu2Content;
    TextView menu4;
    TextView menu4Content;
    TextView menu5;
    TextView menu5Content;
    TextView menu6;
    TextView menu6Content;
    TextView menu7;
    TextView menu7Content;
    TextView menu8;
    TextView menu8Content;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunch_canteen);

        toolbar = (Toolbar) findViewById(R.id.toolbar_sc);
        setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.drawer_icon);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = mDrawer.findViewById(R.id.nvView_sc);
        Menu menu = nvDrawer.getMenu();
        initCards();
        listeners();

        medCrawler c = new medCrawler();
        try {
            c.med();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        menu1Content.setText(c.menuS);
        sideContent.setText(c.sideS);
        choiceContent.setText(c.choiceS);


        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case(R.id.fer):
                        mDrawer.closeDrawers();
                        return false;

                    case (R.id.savska):
                        startActivity(new Intent(medicinaView.this, CanteenView_SC.class), ActivityOptions.makeSceneTransitionAnimation(medicinaView.this).toBundle());
                        finish();
                        return false;

                    case (R.id.arh):
                        startActivity(new Intent(medicinaView.this, OdeonCanteenView.class), ActivityOptions.makeSceneTransitionAnimation(medicinaView.this).toBundle());
                        finish();
                        return false;

                    case (R.id.borongaj):
                        mDrawer.closeDrawers();
                        return false;
                }
                return false;
            }
        });
    }
    private void listeners()
    {
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menu1Content.getVisibility() == View.VISIBLE || choiceContent.getVisibility() == View.VISIBLE || sideContent.getVisibility() == View.VISIBLE)
                {   initVisabilityGone(menu1Content, choiceContent, sideContent);
                    marginsMenus(menu1, choice, side);
                }

                else if(menu1.getVisibility() == View.VISIBLE || side.getVisibility() == View.VISIBLE || choice.getVisibility() == View.VISIBLE)
                    initVisabilityGone(menu1, side, choice);

                else
                {
                    marginsMenus(menu1, side, choice);
                    initMarginsMenu(menu1, side, choice);
                }
            }
        });
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(menu1, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = menu1.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                menu1.setLayoutParams(params);
                menu1Content.setVisibility(View.VISIBLE);
            }
        });
        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(choice, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = choice.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                choice.setLayoutParams(params);
                choiceContent.setVisibility(View.VISIBLE);
            }
        });
        side.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(side, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = side.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                side.setLayoutParams(params);
                sideContent.setVisibility(View.VISIBLE);
            }
        });
    }
    private void initCards()
    {
        lunch = this.findViewById(R.id.lunch_odeon);

        LinearLayout menu1Layout = findViewById(R.id.menu1);
        LinearLayout choiceLayout = findViewById(R.id.menu3);
        LinearLayout sideLayout = findViewById(R.id.choice); //ovdje je side jer je zadnji choice a side su prilozi

        LinearLayout menu2Layout = findViewById(R.id.menu2);
        LinearLayout menu4Layout = findViewById(R.id.menu4);
        LinearLayout menu5Layout = findViewById(R.id.menu5);
        LinearLayout menu6Layout = findViewById(R.id.menu6);
        LinearLayout menu7Layout = findViewById(R.id.menu7);
        LinearLayout menu8Layout = findViewById(R.id.menu8);


        menu1Content = menu1Layout.findViewById(R.id.content);
        sideContent = sideLayout.findViewById(R.id.content);
        choiceContent = choiceLayout.findViewById(R.id.content);

        menu2Content = menu2Layout.findViewById(R.id.content);
        menu4Content = menu4Layout.findViewById(R.id.content);
        menu5Content = menu5Layout.findViewById(R.id.content);
        menu6Content = menu6Layout.findViewById(R.id.content);
        menu7Content = menu7Layout.findViewById(R.id.content);
        menu8Content = menu8Layout.findViewById(R.id.content);


        menu1 = menu1Layout.findViewById(R.id.heading);
        side = sideLayout.findViewById(R.id.heading);
        choice = choiceLayout.findViewById(R.id.heading);

        menu2 = menu2Layout.findViewById(R.id.heading);
        menu4 = menu4Layout.findViewById(R.id.heading);
        menu5 = menu5Layout.findViewById(R.id.heading);
        menu6 = menu6Layout.findViewById(R.id.heading);
        menu7 = menu7Layout.findViewById(R.id.heading);
        menu8 = menu8Layout.findViewById(R.id.heading);


        menu1.setText("MENU"); choice.setText("IZBOR"); side.setText("PRILOZI");

        initVisabilityGone(menu1, choice, menu2, side, menu4, menu5, menu6, menu7, menu8, choiceContent, menu1Content, menu2Content, sideContent, menu4Content, menu5Content, menu6Content, menu7Content, menu8Content);
    }

    private void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    private void initMarginsMenu(View... views)
    {
        for(View v : views)
        {
            v.setVisibility(View.VISIBLE);
            v.getLayoutParams().height = (int) getResources().getDimension(R.dimen.extendedTextHeight);
        }
    }

    private void initVisabilityGone(View... views)
    {
        for(View v : views)
            v.setVisibility(View.GONE);
    }

    private void marginsMenus(View... views)
    {
        for(View v : views)
            setMargins(v, 0, 0, 0, 4);
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
