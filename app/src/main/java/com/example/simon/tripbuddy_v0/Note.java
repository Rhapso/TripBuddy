package com.example.simon.tripbuddy_v0;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pierre on 20/04/2016.
 */
public class Note extends AppCompatActivity {

    private ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = new ArrayList<String>(data);

//        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("ASSET_ID");
            int id = Integer.parseInt(value);


        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationItemHandler(this));

    }

    public void addText(String s){
        data.add("text#"+s);
    }

    public void addImage(String s){
        data.add("imag#"+s);
    }

    public void addVideo(String s){
        data.add("vide#"+s);
    }

    public void addPosition(String s){
        data.add("posi#"+s);
    }
}
