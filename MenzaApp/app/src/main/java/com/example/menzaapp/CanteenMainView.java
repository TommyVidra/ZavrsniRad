package com.example.menzaapp;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.google.android.material.snackbar.Snackbar;

public class CanteenMainView extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    CardView arh;
    CardView alu;
    CardView borongaj;
    CardView cvjetno;
    CardView ekonomija;
    CardView fer;
    CardView ffzg;
    CardView fsb;
    CardView lascina;
    CardView medicina;
    CardView nsk;
    CardView sava;
    CardView savskaSC;
    CardView sumarstvo;
    CardView ttf;
    CardView tvz;
    CardView veterina;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.canteen_main);
        final LinearLayout layout = new LinearLayout(this);
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
        savskaSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(savskaSC);
            }
        });
        arh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(arh);
            }
        });
        borongaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(borongaj);
            }
        });
        cvjetno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(cvjetno);
            }
        });
        ekonomija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(ekonomija);
            }
        });
        lascina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(lascina);
            }
        });
        medicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(medicina);
            }
        });
        nsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(nsk);
            }
        });
        sumarstvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(sumarstvo);
            }
        });
        tvz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(tvz);
            }
        });
        veterina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(veterina);
            }
        });

        alu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "Menza još nije nadodana", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        ttf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "Menza još nije nadodana", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        ffzg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "Menza još nije nadodana", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        fsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "Menza još nije nadodana", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        fer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIntent(fer);
            }
        });
        sava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "Menza još nije nadodana", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        nvDrawer = findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.alu_card), "Menza još nije nadodana", Snackbar.LENGTH_LONG);
                switch (id)
                {
                    case(R.id.savska):
                        startActivity(new Intent(CanteenMainView.this, CanteenView_SC.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.arh):
                        startActivity(new Intent(CanteenMainView.this, OdeonCanteenView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.borongaj):
                        startActivity(new Intent(CanteenMainView.this, borongajView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.cvjetno):
                        startActivity(new Intent(CanteenMainView.this, cvjetnoView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.ekonomija):
                        startActivity(new Intent(CanteenMainView.this, ekonomijaView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.lascina):
                        startActivity(new Intent(CanteenMainView.this, lascinaView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.medicina):
                        startActivity(new Intent(CanteenMainView.this, medicinaView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.nsk):
                        startActivity(new Intent(CanteenMainView.this, nskView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.sumarstvo):
                        startActivity(new Intent(CanteenMainView.this, sumarstvoView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.tvz):
                        startActivity(new Intent(CanteenMainView.this, tvzView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.veterina):
                        startActivity(new Intent(CanteenMainView.this, veterinaView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                    case(R.id.alu):
                        snackbar.show();
                        break;
                    case(R.id.filozofski):
                        snackbar.show();
                        break;
                    case(R.id.fsb):
                        snackbar.show();
                        break;
                    case(R.id.sava):
                        snackbar.show();
                        break;
                    case(R.id.ttf):
                        snackbar.show();
                        break;
                    case(R.id.fer):
                        startActivity(new Intent(CanteenMainView.this, CassandraCanteenView.class), ActivityOptions.makeSceneTransitionAnimation(CanteenMainView.this).toBundle());
                        break;
                }
                return false;
            }
        });

    }

    private void initCards()
    {
        arh = findViewById(R.id.arh_card);
        alu = findViewById(R.id.alu_card);
        borongaj = findViewById(R.id.borongaj_card);
        cvjetno = findViewById(R.id.cvjetno_card);
        ekonomija = findViewById(R.id.ekonomija_card);
        fer = findViewById(R.id.fer_card);
        ffzg = findViewById(R.id.ffzg_card);
        fsb = findViewById(R.id.fsb_card);
        lascina = findViewById(R.id.lascina_card);
        medicina = findViewById(R.id.medicina_card);
        nsk = findViewById(R.id.nsk_card);
        sava = findViewById(R.id.sava_card);
        savskaSC = findViewById(R.id.savska_card);
        sumarstvo = findViewById(R.id.sumarstvo_card);
        ttf = findViewById(R.id.ttf_card);
        tvz = findViewById(R.id.tvz_card);
        veterina = findViewById(R.id.veterina_card);

        initViews(arh, configs.names[0], configs.lunch, configs.imgIds[0]);
        initViews(alu, configs.names[1], configs.lunch, configs.imgIds[1]);
        initViews(borongaj, configs.names[2], configs.lunch, configs.imgIds[2]);
        initViews(cvjetno, configs.names[3], configs.dinner, configs.imgIds[3]);
        initViews(ekonomija, configs.names[4], configs.lunch, configs.imgIds[4]);
        initViews(fer, configs.names[5], configs.lunch, configs.imgIds[5]);
        initViews(ffzg, configs.names[6], configs.lunch, configs.imgIds[6]);
        initViews(fsb, configs.names[7], configs.lunch, configs.imgIds[7]);
        initViews(lascina, configs.names[8], configs.dinner, configs.imgIds[8]);
        initViews(medicina, configs.names[9], configs.lunch, configs.imgIds[9]);
        initViews(nsk, configs.names[10], configs.lunch, configs.imgIds[10]);
        initViews(sava, configs.names[11], configs.dinner, configs.imgIds[11]);
        initViews(savskaSC, configs.names[12], configs.dinner, configs.imgIds[12]);
        initViews(sumarstvo, configs.names[13], configs.lunch, configs.imgIds[13]);
        initViews(ttf, configs.names[14], configs.lunch, configs.imgIds[14]);
        initViews(tvz, configs.names[15], configs.lunch, configs.imgIds[15]);
        initViews(veterina, configs.names[16], configs.lunch, configs.imgIds[16]);
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
        Intent intent = null;
        switch(view.getId()) {
            case (R.id.arh_card):
                intent = new Intent(this, OdeonCanteenView.class);
                break;
            case (R.id.savska_card):
                intent = new Intent(this, CanteenView_SC.class);
                break;
            case (R.id.borongaj_card):
                intent = new Intent(this, borongajView.class);
                break;
            case (R.id.cvjetno_card):
                intent = new Intent(this, cvjetnoView.class);
                break;
            case (R.id.ekonomija_card):
                intent = new Intent(this, ekonomijaView.class);
                break;
            case (R.id.lascina_card):
                intent = new Intent(this, lascinaView.class);
                break;
            case (R.id.medicina_card):
                intent = new Intent(this, medicinaView.class);
                break;
            case (R.id.nsk_card):
                intent = new Intent(this, nskView.class);
                break;
            case (R.id.sumarstvo_card):
                intent = new Intent(this, sumarstvoView.class);
                break;
            case (R.id.tvz_card):
                intent = new Intent(this, tvzView.class);
                break;
            case (R.id.veterina_card):
                intent = new Intent(this, veterinaView.class);
                break;
            case (R.id.fer_card):
                intent = new Intent(this, CassandraCanteenView.class);
                break;
        }
        String transitionName = getString(R.string.transition_string);

        View viewStart = view;
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        viewStart,   // Starting view
                        transitionName    // The String
                );
        //Start the Intent
        ActivityCompat.startActivity(this, intent, options.toBundle());
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
