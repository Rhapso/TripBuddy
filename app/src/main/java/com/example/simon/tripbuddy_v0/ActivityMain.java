package com.example.simon.tripbuddy_v0;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Random;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_uno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createButton();

        DataHolder dh = DataHolder.getInstance();
        dh.setData("Hey jude!");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationItemHandler(this));
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void createButton() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.unoMainLayout);
        int baseSize = 150;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int offsetX = 8;
        int width = size.x;
        int nCol = width/baseSize;

        Grille g = new Grille(20, nCol);
        g.alea();




        //View view = findViewById(R.id.nav_view);
        //Snackbar.make(view, "Col: "+nCol, Snackbar.LENGTH_LONG).setAction("Action", null).show();


        MyRessources res = new MyRessources();

        Random r = new Random();

        for(int i=0; i<20; i++){
            for(int j=0; j<nCol; ++j) {
                if(!g.grille[i][j].isOccupe) {

                    ImageButton btnTag3 = new ImageButton(this);

                    int id = r.nextInt(res.size());
                    btnTag3.setImageResource(res.get(id).getId());

                    btnTag3.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    btnTag3.setPadding(0, 0, 0, 0);
                    layout.addView(btnTag3);
                    int wB = width / nCol + 20;
                    int hB = baseSize + 30;
                    RelativeLayout.LayoutParams OBJ3 = new RelativeLayout.LayoutParams(g.grille[i][j].width * width / nCol + 20, g.grille[i][j].height * baseSize + 30);
                    OBJ3.topMargin = i * (baseSize) + 140;
                    OBJ3.leftMargin = j * (width / nCol);
                    btnTag3.setLayoutParams(OBJ3);

                    btnTag3.setTag(id);
                    btnTag3.setOnClickListener(this);

                }
            }
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_uno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ActivityMain Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.simon.tripbuddy_v0/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ActivityMain Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.simon.tripbuddy_v0/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public void onClick(View v) {
        View view = findViewById(R.id.nav_view);
        Snackbar.make(view, "Col: "+v.getTag(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        Intent intent = new Intent(ActivityMain.this, ActivityDetails.class);
        intent.putExtra("ASSET_ID", v.getTag().toString());
        startActivity(intent);
    }
}
