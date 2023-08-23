package com.example.fallingfruits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        setTitle("MainActivity");
        Log.d(TAG,"onCreate MainActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop MainActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy MainActivity");
    }

    public void clickMain(View view){
        switch (view.getId()){
            case R.id.play:
                startActivity(new Intent(this, LevelSelect.class));
                break;
            case R.id.highscores:
                startActivity(new Intent(this, Highscores.class));
                break;
            case R.id.shop:
                startActivity(new Intent(this, Shop.class));
                break;
            case R.id.tutorial:
                startActivity(new Intent(this, Tutorial.class));
                break;
        }
    }

    public void clickExit(View view){
        this.finishAffinity();
    }

}