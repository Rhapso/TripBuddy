package com.example.simon.tripbuddy_v0;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.Random;

public class ItineraireActivity extends AppCompatActivity {

    String[] wordlist = new String[] { "a", "b", "c" };
    private SwipeLayout swipeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itineraire);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createList();


//        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationItemHandler(this));
    }


    public void createList() {
        DataHolder dh = DataHolder.getInstance();
        ListView list = (ListView) findViewById(R.id.layoutineraire);
        list.removeViews(0, list.getChildCount());
        list.setAdapter(new MyAdapter(this, dh.getItineraire(), this));
    }

    private class MyAdapter extends ArrayAdapter<Lieux> {

        private ItineraireActivity parent;

        public MyAdapter(Context context, ArrayList<Lieux> itineraire, ItineraireActivity parent) {
            super(context, -1, -1, itineraire);
            this.parent = parent;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout listLayout = new LinearLayout(ItineraireActivity.this);
            listLayout.setLayoutParams(new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT));

            swipeLayout = new SwipeLayout(ItineraireActivity.this);
            LinearLayout ll = new LinearLayout(ItineraireActivity.this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            ImageButton iw = new ImageButton(ItineraireActivity.this);
            iw.setImageResource(R.drawable.ic_menu_delete);
            ll.addView(iw, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            iw.setOnClickListener(new OnDeleteHandler(this.parent, position));

            ImageButton iw2 = new ImageButton(ItineraireActivity.this);
            iw2.setImageResource(R.drawable.ic_keyboard_arrow_up);
            ll.addView(iw2, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            iw2.setOnClickListener(new OnUpHandler(this.parent, position));

            ImageButton iw3 = new ImageButton(ItineraireActivity.this);
            iw3.setImageResource(R.drawable.ic_expand_more);
            ll.addView(iw3, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            iw3.setOnClickListener(new OnDownHandler(this.parent, position));


            swipeLayout.addView(ll, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

            LinearLayout ll2 = new LinearLayout(ItineraireActivity.this);

            TextView listText = new TextView(ItineraireActivity.this);
            listText.setPadding(20,20, 20, 20);
            listText.setTextSize(30);
            //listLayout.addView(listText);

            listText.setText(super.getItem(position).getNom());


            ll2.addView(listText, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            swipeLayout.addView(ll2, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

            listLayout.addView(swipeLayout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            return listLayout;
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
        getMenuInflater().inflate(R.menu.itineraire, menu);
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

}

class OnDeleteHandler implements View.OnClickListener {

    private ItineraireActivity parent;
    private int position;

    public OnDeleteHandler(ItineraireActivity parent, int position) {
        this.position = position;
        this.parent = parent;
    }

    @Override
    public void onClick(View v) {
        DataHolder dh = DataHolder.getInstance();
        dh.getItineraire().remove(position);
        parent.createList();
        Toast.makeText(v.getContext(), "Delete Item "+position, Toast.LENGTH_SHORT).show();
    }
}

class OnUpHandler implements View.OnClickListener {

    private ItineraireActivity parent;
    private int position;

    public OnUpHandler(ItineraireActivity parent, int position) {
        this.position = position;
        this.parent = parent;
    }

    @Override
    public void onClick(View v) {
        DataHolder dh = DataHolder.getInstance();

        if(position>0) {
            Lieux l1 = dh.getItineraire().get(position);
            Lieux l2 = dh.getItineraire().get(position-1);
            dh.getItineraire().set(position-1, l1);
            dh.getItineraire().set(position, l2);
            parent.createList();
        }

    }
}


class OnDownHandler implements View.OnClickListener {

    private ItineraireActivity parent;
    private int position;

    public OnDownHandler(ItineraireActivity parent, int position) {
        this.position = position;
        this.parent = parent;
    }

    @Override
    public void onClick(View v) {
        DataHolder dh = DataHolder.getInstance();

        if(position<dh.getItineraire().size()-1) {
            Lieux l1 = dh.getItineraire().get(position);
            Lieux l2 = dh.getItineraire().get(position+1);
            dh.getItineraire().set(position+1, l1);
            dh.getItineraire().set(position, l2);
            parent.createList();
        }

    }
}