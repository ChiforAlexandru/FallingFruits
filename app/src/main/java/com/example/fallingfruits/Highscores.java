package com.example.fallingfruits;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.res.AssetManager;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.TextView;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Scanner;

public class Highscores extends AppCompatActivity {
    public static final String TAG = "lifecycle";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.highscores);
        setTitle("Highscores");
        Log.d(TAG,"onCreate Highscores");
        sp = getApplicationContext().getSharedPreferences("Stocare", Context.MODE_PRIVATE);
        ((android.widget.TextView) findViewById(R.id.Text1)).setText("1:  "+sp.getInt("sc1", 0)+" by "+sp.getString("nm1", "Default"));
        ((android.widget.TextView) findViewById(R.id.Text2)).setText("2:  "+sp.getInt("sc2", 0)+" by "+sp.getString("nm2", "Default"));
        ((android.widget.TextView) findViewById(R.id.Text3)).setText("3:  "+sp.getInt("sc3", 0)+" by "+sp.getString("nm3", "Default"));
        ((android.widget.TextView) findViewById(R.id.Text4)).setText("4:  "+sp.getInt("sc4", 0)+" by "+sp.getString("nm4", "Default"));
        ((android.widget.TextView) findViewById(R.id.Text5)).setText("5:  "+sp.getInt("sc5", 0)+" by "+sp.getString("nm5", "Default"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart Highscores");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume Highscores");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause Highscores");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop Highscores");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart Highscores");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Highscores");
    }


    public void clickBackHighscores(View view){
        switch (view.getId()){
            case R.id.fab:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }


}