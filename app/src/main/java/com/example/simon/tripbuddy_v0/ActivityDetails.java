package com.example.simon.tripbuddy_v0;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDetails extends AppCompatActivity {

    private int idLieux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("ASSET_ID");
            int id = Integer.parseInt(value);
            idLieux = id;

            MyRessources res = new MyRessources();

//            View view = findViewById(R.id.nav_view);
//            Snackbar.make(view, "Val: "+res.get(id).getNom(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

            TextView tv = (TextView) findViewById(R.id.textView2);
            tv.setText(res.get(id).getNom());

            ImageView iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(res.get(id).getId());

            TextView tv2 = (TextView) findViewById(R.id.textView3);
            tv2.setText(res.get(id).getInfo());

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationItemHandler(this));




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyRessources res = new MyRessources();
                DataHolder.getInstance().addEtape(res.get(idLieux));
                Snackbar.make(view, res.get(idLieux).getNom()+" ajouté à votre itineraire!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
