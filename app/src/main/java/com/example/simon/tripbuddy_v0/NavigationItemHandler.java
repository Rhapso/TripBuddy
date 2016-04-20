package com.example.simon.tripbuddy_v0;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by simon on 17/04/16.
 */
public class NavigationItemHandler implements NavigationView.OnNavigationItemSelectedListener {


    private Activity parent;

    public NavigationItemHandler(Activity parent) {
        this.parent = parent;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        View view = parent.findViewById(R.id.nav_view);
        if (id == R.id.nav_itineraire) {
            Intent intent = new Intent(parent, ItineraireActivity.class);
            parent.startActivity(intent);
        } else if (id == R.id.nav_mur) {
            Intent intent = new Intent(parent, ActivityMain.class);
            parent.startActivity(intent);
        } else if (id == R.id.nav_notes) {




        } else if (id == R.id.nav_research) {
            Intent intent = new Intent(parent, Research.class);
            parent.startActivity(intent);
        }

        if (id != R.id.nav_notes) {
            DrawerLayout drawer = (DrawerLayout) parent.findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}

