package com.example.fallingfruits;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class Tutorial extends AppCompatActivity {
    public static final String TAG = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.tutorial);
        setTitle("Tutorial");
        Log.d(TAG,"onCreate Tutorial");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart Tutorial");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume Tutorial");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause Tutorial");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop Tutorial");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart Tutorial");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Tutorial");
    }


    public void clickBackTutorial(View view){
        switch (view.getId()){
            case R.id.fab:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }


}