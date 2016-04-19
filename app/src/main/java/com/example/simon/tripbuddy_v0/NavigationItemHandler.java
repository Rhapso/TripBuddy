package com.example.simon.tripbuddy_v0;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
        } else if (id == R.id.nav_gallery) {
            Snackbar.make(view, "Nav Gallery", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(parent, DummyContent.class);
            parent.startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) parent.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
