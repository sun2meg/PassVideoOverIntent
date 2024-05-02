package com.megacoreapps.sun2meg.myapplication;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Initialize variables
    Button btSelect;
    private ActivityResultLauncher<String> videoPickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign variable
        btSelect = findViewById(R.id.bt_select);


// Initialize video picker launcher
    videoPickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        // Check condition
                        if (uri != null) {
                            // When result code is okay
                            // Initialize intent
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            // Set data URI for the intent
                            intent.setData(uri);
                            // Start activity
                            startActivity(intent);
                        }
                    }
                });


        btSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check condition
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // When permission is not granted
                    // Request permission
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                            , 1);
                } else {
                    // When permission is granted
                    // Create method
                    selectVideo();
                }
            }
        });
    }

    private void selectVideo() {
        // Start activity for result with the video picker launcher
        videoPickerLauncher.launch("video/*");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Check condition
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {
            // When permission is granted
            // Call method
            selectVideo();
        } else {
            // When permission is denied
            // Display toast
            Toast.makeText(getApplicationContext()
                    , "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }
}
