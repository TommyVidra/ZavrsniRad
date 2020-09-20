package com.example.menzaapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class OdeonCanteenView extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    TextView lunch;
    TextView menu1;
    TextView menu1Content;
    TextView menu2;
    TextView menu2Content;
    TextView menu3;
    TextView menu3Content;
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
    TextView choice;
    TextView choiceContent;
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

        Crawler c = new Crawler();
        try {
            c.odeon();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        menu1Content.setText(c.menusL.get(0));
        menu2Content.setText(c.menusL.get(1));
        menu3Content.setText(c.menusL.get(2));
        menu4Content.setText(c.menusL.get(3));
        choiceContent.setText(c.menusL.get(4));
//        menu6Content.setText(c.menusL.get(5));
//        menu7Content.setText(c.menusL.get(6));
//        menu8Content.setText(c.menusL.get(7));
//        choiceContent.setText(c.menusL.get(8));


        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case(R.id.arh):
                        mDrawer.closeDrawers();
                        return false;

                    case(R.id.fer):
                        mDrawer.closeDrawers();
                        return false;

                    case(R.id.alu):
                        mDrawer.closeDrawers();
                        return false;

                    case(R.id.filozofski):
                        mDrawer.closeDrawers();
                        return false;

                    case(R.id.fsb):
                        mDrawer.closeDrawers();
                        return false;

                    case(R.id.ttf):
                        mDrawer.closeDrawers();
                        return false;

                    case(R.id.sava):
                        mDrawer.closeDrawers();
                        return false;

                    case (R.id.savska):
                        startActivity(new Intent(OdeonCanteenView.this, CanteenView_SC.class), ActivityOptions.makeSceneTransitionAnimation(OdeonCanteenView.this).toBundle());
                        finish();
                        return false;

                    case(R.id.cvjetno):
                        startActivity(new Intent(OdeonCanteenView.this, cvjetnoView.class), ActivityOptions.makeSceneTransitionAnimation(OdeonCanteenView.this).toBundle());
                        finish();
                        return false;

                    case(R.id.ekonomija):
                        startActivity(new Intent(OdeonCanteenView.this, ekonomijaView.class), ActivityOptions.makeSceneTransitionAnimation(OdeonCanteenView.this).toBundle());
                        finish();
                        return false;
                    case(R.id.lascina):
                        startActivity(new Intent(OdeonCanteenView.this, lascinaView.class), ActivityOptions.makeSceneTransitionAnimation(OdeonCanteenView.this).toBundle());
                        finish();
                        return false;
                    case(R.id.medicina):
                        startActivity(new Intent(OdeonCanteenView.this, medicinaView.class), ActivityOptions.makeSceneTransitionAnimation(OdeonCanteenView.this).toBundle());
                        finish();
                        return false;

                    case(R.id.nsk):
                        startActivity(new Intent(OdeonCanteenView.this, nskView.class), ActivityOptions.makeSceneTransitionAnimation(OdeonCanteenView.this).toBundle());
                        finish();
                        return false;

                    case(R.id.sumarstvo):
                        startActivity(new Intent(OdeonCanteenView.this, sumarstvoView.class), ActivityOptions.makeSceneTransitionAnimation(OdeonCanteenView.this).toBundle());
                        finish();
                        return false;
                    case(R.id.tvz):
                        startActivity(new Intent(OdeonCanteenView.this, tvzView.class), ActivityOptions.makeSceneTransitionAnimation(OdeonCanteenView.this).toBundle());
                        finish();
                        return false;
                    case(R.id.veterina):
                        startActivity(new Intent(OdeonCanteenView.this, veterinaView.class), ActivityOptions.makeSceneTransitionAnimation(OdeonCanteenView.this).toBundle());
                        finish();
                        return false;

                    case (R.id.borongaj):
                        startActivity(new Intent(OdeonCanteenView.this, borongajView.class), ActivityOptions.makeSceneTransitionAnimation(OdeonCanteenView.this).toBundle());
                        finish();
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
                if(menu1Content.getVisibility() == View.VISIBLE || menu2Content.getVisibility() == View.VISIBLE || menu3Content.getVisibility() == View.VISIBLE || choiceContent.getVisibility() == View.VISIBLE || menu4Content.getVisibility() == View.VISIBLE || menu5Content.getVisibility() == View.VISIBLE || menu6Content.getVisibility() == View.VISIBLE || menu7Content.getVisibility() == View.VISIBLE || menu8Content.getVisibility() == View.VISIBLE)
                {   initVisabilityGone(menu1Content, menu2Content, menu3Content, menu4Content, menu5Content, menu6Content, choiceContent, menu7Content, menu8Content);
                    marginsMenus(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, choice);
                }

                else if(menu1.getVisibility() == View.VISIBLE || menu2.getVisibility() == View.VISIBLE || menu3.getVisibility() == View.VISIBLE || choice.getVisibility() == View.VISIBLE || menu4.getVisibility() == View.VISIBLE || menu5.getVisibility() == View.VISIBLE || menu6.getVisibility() == View.VISIBLE || menu7.getVisibility() == View.VISIBLE || menu8.getVisibility() == View.VISIBLE)
                    initVisabilityGone(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, choice);

                else
                {
                    marginsMenus(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, choice);
                    initMarginsMenu(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, choice);
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
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(menu2, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = menu2.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                menu2.setLayoutParams(params);
                menu2Content.setVisibility(View.VISIBLE);
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
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(menu3, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = menu3.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                menu3.setLayoutParams(params);
                menu3Content.setVisibility(View.VISIBLE);
            }
        });
        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(menu4, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = menu4.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                menu4.setLayoutParams(params);
                menu4Content.setVisibility(View.VISIBLE);
            }
        });
        menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(menu5, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = menu5.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                menu5.setLayoutParams(params);
                menu5Content.setVisibility(View.VISIBLE);
            }
        });
        menu6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(menu6, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = menu6.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                menu6.setLayoutParams(params);
                menu6Content.setVisibility(View.VISIBLE);
            }
        });
        menu7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(menu7, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = menu7.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                menu7.setLayoutParams(params);
                menu7Content.setVisibility(View.VISIBLE);
            }
        });
        menu8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMargins(menu8, 0, 0, 0, 0);
                ViewGroup.LayoutParams params = menu8.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                menu8.setLayoutParams(params);
                menu8Content.setVisibility(View.VISIBLE);
            }
        });
    }
    private void initCards()
    {
        lunch = this.findViewById(R.id.lunch_odeon);

        LinearLayout menu1Layout = findViewById(R.id.menu1);
        LinearLayout menu2Layout = findViewById(R.id.menu2);
        LinearLayout menu3Layout = findViewById(R.id.menu3);
        LinearLayout menu4Layout = findViewById(R.id.menu4);
        LinearLayout menu5Layout = findViewById(R.id.menu5);
        LinearLayout menu6Layout = findViewById(R.id.menu6);
        LinearLayout menu7Layout = findViewById(R.id.menu7);
        LinearLayout menu8Layout = findViewById(R.id.menu8);
        LinearLayout choiceLayout = findViewById(R.id.choice);

        menu1Content = menu1Layout.findViewById(R.id.content);
        menu2Content = menu2Layout.findViewById(R.id.content);
        menu3Content = menu3Layout.findViewById(R.id.content);
        menu4Content = menu4Layout.findViewById(R.id.content);
        menu5Content = menu5Layout.findViewById(R.id.content);
        menu6Content = menu6Layout.findViewById(R.id.content);
        menu7Content = menu7Layout.findViewById(R.id.content);
        menu8Content = menu8Layout.findViewById(R.id.content);
        choiceContent = choiceLayout.findViewById(R.id.content);

        menu1 = menu1Layout.findViewById(R.id.heading);
        menu2 = menu2Layout.findViewById(R.id.heading);
        menu3 = menu3Layout.findViewById(R.id.heading);
        menu4 = menu4Layout.findViewById(R.id.heading);
        menu5 = menu5Layout.findViewById(R.id.heading);
        menu6 = menu6Layout.findViewById(R.id.heading);
        menu7 = menu7Layout.findViewById(R.id.heading);
        menu8 = menu8Layout.findViewById(R.id.heading);
        choice = choiceLayout.findViewById(R.id.heading);

        menu1.setText("MENU 1"); menu2.setText("MENU 2"); choice.setText("JELA PO NARUDŽBI"); menu3.setText("MENU 3");
        menu4.setText("MENU 4"); menu5.setText("MENU 5"); menu6.setText("MENU 6"); menu7.setText("MENU 7"); menu8.setText("MENU 8");

        initVisabilityGone(menu1, choice, menu2, menu3, menu4, menu5, menu6, menu7, menu8, choiceContent, menu1Content, menu2Content, menu3Content, menu4Content, menu5Content, menu6Content, menu7Content, menu8Content);
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
