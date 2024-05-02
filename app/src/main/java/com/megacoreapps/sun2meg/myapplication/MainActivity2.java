package com.megacoreapps.sun2meg.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity2 extends AppCompatActivity {

    // Initialize variables
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Assign variables
        videoView = findViewById(R.id.video_view);

        // Initialize video URI
        Uri videoUri = getIntent().getData();

        // Check if URI is not null
        if (videoUri != null) {
            // Set video URI to the VideoView
            videoView.setVideoURI(videoUri);
            // Start playing the video
            videoView.start();
        } else {
            // Handle the case where URI is null
            Toast.makeText(this, "Invalid video URI", Toast.LENGTH_SHORT).show();
        }
    }
}
