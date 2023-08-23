package com.example.fallingfruits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LevelSelect extends AppCompatActivity {
    public static final String TAG = "lifecycle";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.levelselect);
        setTitle("LevelSelect");
        Log.d(TAG,"onCreate LevelSelect");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart LevelSelect");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume LevelSelect");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause LevelSelect");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop LevelSelect");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart LevelSelect");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy LevelSelect");
    }

    public void clickBackLevel(View view){
        switch (view.getId()){
            case R.id.fab:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    public void clickLevels(View view){
        sp = getApplicationContext().getSharedPreferences("Stocare", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        switch (view.getId()){
            case R.id.level1:
                editor.putInt("nivel", 1);
                editor.commit();
                startActivity(new Intent(this, Level.class));
                break;
            case R.id.level2:
                editor.putInt("nivel", 2);
                editor.commit();
                startActivity(new Intent(this, Level.class));
                break;
            case R.id.level3:
                editor.putInt("nivel", 3);
                editor.commit();
                startActivity(new Intent(this, Level.class));
                break;
            case R.id.level4:
                editor.putInt("nivel", 4);
                editor.commit();
                startActivity(new Intent(this, Level.class));
                break;
            case R.id.level5:
                editor.putInt("nivel", 5);
                editor.commit();
                startActivity(new Intent(this, Level.class));
                break;
            case R.id.level6:
                editor.putInt("nivel", 6);
                editor.commit();
                startActivity(new Intent(this, Level.class));
                break;
        }
    }

}
