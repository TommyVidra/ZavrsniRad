package com.example.menzaapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
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
import androidx.navigation.Navigation;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CanteenView_SC extends AppCompatActivity{

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    TextView lunch;
    TextView menu;
    TextView menuContent;
    TextView veg;
    TextView vegContent;
    TextView choice;
    TextView choiceContent;
    TextView side;
    TextView sideContent;

    TextView dinner;
    TextView menuDinner;
    TextView menuContentDinner;
    TextView vegDinner;
    TextView vegContentDinner;
    TextView choiceDinner;
    TextView choiceContentDinner;
    TextView sideDinner;
    TextView sideContentDinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canteen);
        toolbar = (Toolbar) findViewById(R.id.toolbar_sc);
        setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.drawer_icon);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = mDrawer.findViewById(R.id.nvView_sc);
        Menu menu = nvDrawer.getMenu();

        initText();
        listeners();

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

        menuContent.setText(c.lunchMenu);
        vegContent.setText(c.lunchVegMenu);
        choiceContent.setText(c.lunchChoice);
        sideContent.setText(c.lunchSide);
        menuContentDinner.setText(c.dinerMenu);
        vegContentDinner.setText(c.dinerVegMenu);
        choiceContentDinner.setText(c.dinerChoice);
        sideContentDinner.setText(c.dinerSide);

        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.savska){
                    mDrawer.closeDrawers();
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
                if(menuContent.getVisibility() == View.VISIBLE || vegContent.getVisibility() == View.VISIBLE || sideContent.getVisibility() == View.VISIBLE || choiceContent.getVisibility() == View.VISIBLE)
                {   initVisabilityGone(menuContent, vegContent, sideContent, choiceContent);
                    marginsMenus(menu, veg, side, choice);
                }

                else if(menu.getVisibility() == View.VISIBLE || veg.getVisibility() == View.VISIBLE || side.getVisibility() == View.VISIBLE || choice.getVisibility() == View.VISIBLE)
                    initVisabilityGone(menu, veg, side, choice);

                else
                {
                    marginsMenus(menu, veg, choice, side);
                    initMarginsMenu(menu, veg, choice, side);
                }
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setMargins(menu, 0, 0, 0, 0);
            ViewGroup.LayoutParams params = menu.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            menu.setLayoutParams(params);
            menuContent.setVisibility(View.VISIBLE);
            }
        });
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(veg, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = veg.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                veg.setLayoutParams(params);
                vegContent.setVisibility(View.VISIBLE);
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

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuContentDinner.getVisibility() == View.VISIBLE || vegContentDinner.getVisibility() == View.VISIBLE || sideContentDinner.getVisibility() == View.VISIBLE || choiceContentDinner.getVisibility() == View.VISIBLE)
                {
                    initVisabilityGone(menuContentDinner, vegContentDinner, sideContentDinner, choiceContentDinner);
                    marginsMenus(menuDinner, vegDinner, sideDinner, choiceDinner);
                }
                else if(menuDinner.getVisibility() == View.VISIBLE || vegDinner.getVisibility() == View.VISIBLE || sideDinner.getVisibility() == View.VISIBLE || choiceDinner.getVisibility() == View.VISIBLE)
                    initVisabilityGone(menuDinner, vegDinner, sideDinner, choiceDinner);

                else {
                    marginsMenus(menuDinner, vegDinner, choiceDinner, sideDinner);
                    initMarginsMenu(menuDinner, vegDinner, choiceDinner, sideDinner);
                }
            }
        });
        menuDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(menuDinner, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = menuDinner.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                menuDinner.setLayoutParams(params);
                menuContentDinner.setVisibility(View.VISIBLE);
            }
        });
        vegDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(vegDinner, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = vegDinner.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                vegDinner.setLayoutParams(params);
                vegContentDinner.setVisibility(View.VISIBLE);
            }
        });
        choiceDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(choiceDinner, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = choiceDinner.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                choiceDinner.setLayoutParams(params);
                choiceContentDinner.setVisibility(View.VISIBLE);
            }
        });
        sideDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(sideDinner, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = sideDinner.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                sideDinner.setLayoutParams(params);
                sideContentDinner.setVisibility(View.VISIBLE);
            }
        });
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

    private void marginsMenus(View m, View v, View s, View c)
    {
        setMargins(m, 0, 0, 0, 4);
        setMargins(v, 0, 0, 0, 4);
        setMargins(c, 0, 0, 0, 4);
        setMargins(s, 0, 0, 0, 4);
    }

    private void initText()
    {
        dinner = this.findViewById(R.id.diner);
        lunch = this.findViewById(R.id.lunch);

        LinearLayout menuLayout = findViewById(R.id.menu);
        LinearLayout vegLayout = findViewById(R.id.veg);
        LinearLayout sideLayout = findViewById(R.id.side);
        LinearLayout choiceLayout = findViewById(R.id.choice);

        menuContent = menuLayout.findViewById(R.id.content);
        vegContent = vegLayout.findViewById(R.id.content);
        choiceContent = choiceLayout.findViewById(R.id.content);
        sideContent = sideLayout.findViewById(R.id.content);

        menu = menuLayout.findViewById(R.id.heading);
        veg = vegLayout.findViewById(R.id.heading);
        choice = choiceLayout.findViewById(R.id.heading);
        side = sideLayout.findViewById(R.id.heading);
        menu.setText("MENU"); veg.setText("VEGETARIJANSKI MENU"); choice.setText("IZBOR JELA"); side.setText("PRILOZI");

        menuContentDinner = this.findViewById(R.id.menuContent2);
        vegContentDinner = this.findViewById(R.id.vegMenuContent2);
        choiceContentDinner = this.findViewById(R.id.choiceContent2);
        sideContentDinner = this.findViewById(R.id.sideContent2);
        menuDinner = this.findViewById(R.id.menu2);
        vegDinner = this.findViewById(R.id.vegMenu2);
        choiceDinner = this.findViewById(R.id.choice2);
        sideDinner = this.findViewById(R.id.side2);

        initVisabilityGone(menu, choice, side, veg, menuContent, vegContent, choiceContent, sideContent);
        initVisabilityGone(menuDinner, choiceDinner, sideDinner, vegDinner, menuContentDinner, vegContentDinner, choiceContentDinner, sideContentDinner);
    }

    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
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
