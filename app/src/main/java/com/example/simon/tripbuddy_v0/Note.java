package com.example.simon.tripbuddy_v0;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import 	android.graphics.Bitmap;
import android.widget.VideoView;

import java.util.ArrayList;

/**
 * Created by Pierre on 20/04/2016.
 */
public class Note extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_VIDEO_CAPTURE = 1;

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
        findViewById(R.id.addPic).setOnClickListener(new ImageButtonListener());
        findViewById(R.id.addVideo).setOnClickListener(new CameraButtonListener());


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
            textInput.setSingleLine(false);
            textInput.setOnLongClickListener(new NoteDataOnLongClickListener());
            LinearLayout l = (LinearLayout) ((View) v.getParent().getParent()).findViewById(R.id.layoutnote);
            l.addView(textInput, l.getChildCount()-1, lp);
        }
    }

    public class ImageButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }

    public class CameraButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            LinearLayout l = (LinearLayout) findViewById(R.id.layoutnote);
            ImageView view = new ImageView(l.getContext());
            view.setImageBitmap(imageBitmap);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setOnLongClickListener(new NoteDataOnLongClickListener());

            l.addView(l, l.getChildCount()-1, lp);
        }else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            LinearLayout l = (LinearLayout) findViewById(R.id.layoutnote);
            VideoView view = new VideoView(l.getContext());
            view.setVideoURI(videoUri);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setOnLongClickListener(new NoteDataOnLongClickListener());
            l.addView(l, l.getChildCount()-1, lp);
        }
    }

    public class NoteDataOnLongClickListener implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }
}
