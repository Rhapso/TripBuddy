package com.example.simon.tripbuddy_v0;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        data = new ArrayList<String>();

//        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("ASSET_ID");
            int id = Integer.parseInt(value);


        }

        findViewById(R.id.addText).setOnClickListener(new TextButtonListener());



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

    public class TextButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            EditText textInput = new EditText(v.getContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            //textInput.setLayoutParams(lp);
            textInput.setHint("Type your text");

            LinearLayout l = (LinearLayout) ((View) v.getParent().getParent()).findViewById(R.id.layoutnote);
            l.addView(textInput, lp);
        }
    }
}
