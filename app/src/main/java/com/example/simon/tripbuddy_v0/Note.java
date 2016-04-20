package com.example.simon.tripbuddy_v0;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pierre on 20/04/2016.
 */
public class Note extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_VIDEO_CAPTURE = 1;

    private ArrayList<String> data;
    private ArrayList<String> photos = new ArrayList<String>();
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
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(photoFile));
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    }
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
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            File img = new File(photos.get(photos.size()));

            LinearLayout l = (LinearLayout) findViewById(R.id.layoutnote);
            ImageView view = new ImageView(l.getContext());
            view.setImageURI(Uri.fromFile(img));
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setOnLongClickListener(new NoteDataOnLongClickListener());

            l.addView(view, l.getChildCount()-1, lp);
        }else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            LinearLayout l = (LinearLayout) findViewById(R.id.layoutnote);
            VideoView view = new VideoView(l.getContext());
            view.setVideoURI(videoUri);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setOnLongClickListener(new NoteDataOnLongClickListener());
            l.addView(view, l.getChildCount()-1, lp);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        photos.add("file:" + image.getAbsolutePath());
        return image;
    }

    public class NoteDataOnLongClickListener implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }
}
