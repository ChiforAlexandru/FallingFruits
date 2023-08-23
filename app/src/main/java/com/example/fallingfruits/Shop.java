package com.example.fallingfruits;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shop extends AppCompatActivity {
    public static final String TAG = "lifecycle";
    SharedPreferences sp;

    public void refreshShopButtons(){
        sp = getApplicationContext().getSharedPreferences("Stocare", Context.MODE_PRIVATE);
        if (sp.getInt("skin1", 0)==1)
            ((android.widget.Button) findViewById(R.id.shop1)).setText("Use");
        if (sp.getInt("skin2", 0)==1)
            ((android.widget.Button) findViewById(R.id.shop2)).setText("Use");
        if (sp.getInt("skin3", 0)==1)
            ((android.widget.Button) findViewById(R.id.shop3)).setText("Use");
        if (sp.getInt("skin4", 0)==1)
            ((android.widget.Button) findViewById(R.id.shop4)).setText("Use");
        if (sp.getInt("skin5", 0)==1)
            ((android.widget.Button) findViewById(R.id.shop5)).setText("Use");
        if (sp.getInt("skin6", 0)==1)
            ((android.widget.Button) findViewById(R.id.shop6)).setText("Use");
        if (sp.getInt("skin1", 0)==0)
            ((android.widget.Button) findViewById(R.id.shop1)).setText("Buy");
        if (sp.getInt("skin2", 0)==0)
            ((android.widget.Button) findViewById(R.id.shop2)).setText("Buy");
        if (sp.getInt("skin3", 0)==0)
            ((android.widget.Button) findViewById(R.id.shop3)).setText("Buy");
        if (sp.getInt("skin4", 0)==0)
            ((android.widget.Button) findViewById(R.id.shop4)).setText("Buy");
        if (sp.getInt("skin5", 0)==0)
            ((android.widget.Button) findViewById(R.id.shop5)).setText("Buy");
        if (sp.getInt("skin6", 0)==0)
            ((android.widget.Button) findViewById(R.id.shop6)).setText("Buy");
        switch (sp.getInt("skinCurent", 0)) {
            case 0:
                ((android.widget.Button) findViewById(R.id.defaultul)).setText("Current(Default)");
                break;
            case 1:
                ((android.widget.Button) findViewById(R.id.shop1)).setText("Current Skin");
                break;
            case 2:
                ((android.widget.Button) findViewById(R.id.shop2)).setText("Current Skin");
                break;
            case 3:
                ((android.widget.Button) findViewById(R.id.shop3)).setText("Current Skin");
                break;
            case 4:
                ((android.widget.Button) findViewById(R.id.shop4)).setText("Current Skin");
                break;
            case 5:
                ((android.widget.Button) findViewById(R.id.shop5)).setText("Current Skin");
                break;
            case 6:
                ((android.widget.Button) findViewById(R.id.shop6)).setText("Current Skin");
                break;
        }
        if (sp.getInt("skinCurent", 0)==0)
            ((android.widget.Button) findViewById(R.id.defaultul)).setText("Current(Default)");
        else ((android.widget.Button) findViewById(R.id.defaultul)).setText("Default Skin");
        ((android.widget.TextView) findViewById(R.id.monezi)).setText("Coins: "+sp.getInt("monezi", 0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.shop);
        setTitle("Shop");
        Log.d(TAG,"onCreate Shop");
        refreshShopButtons();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart Shop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume Shop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause Shop");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop Shop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart Shop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Shop");
    }


    public void clickBackShop(View view){
        switch (view.getId()){
            case R.id.fab:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    public void clickShop(View view){
        int valoare;
        sp = getApplicationContext().getSharedPreferences("Stocare", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Context context = getApplicationContext();
        CharSequence text = "Not enough coins (10 are required)";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        switch (view.getId()){
            case R.id.shop1:
                if ((sp.getInt("skin1", 0)==0)&&(sp.getInt("monezi", 0)<10))
                    toast.show();
                if ((sp.getInt("skin1", 0)==0)&&(sp.getInt("monezi", 0)>=10)){
                    ((android.widget.Button) findViewById(R.id.shop1)).setText("Use");
                    editor.putInt("skin1", 1);
                    valoare=sp.getInt("monezi", 0)-10;
                    editor.putInt("monezi", valoare);
                    editor.commit();
                    ((android.widget.TextView) findViewById(R.id.monezi)).setText("Coins: "+valoare);
                }
                if (sp.getInt("skin1", 0)==1){
                    editor.putInt("skinCurent", 1);
                    editor.commit();
                    refreshShopButtons();
                }
                break;
            case R.id.shop2:
                if ((sp.getInt("skin2", 0)==0)&&(sp.getInt("monezi", 0)<10))
                    toast.show();
                if ((sp.getInt("skin2", 0)==0)&&(sp.getInt("monezi", 0)>=10)){
                    ((android.widget.Button) findViewById(R.id.shop2)).setText("Use");
                    editor.putInt("skin2", 1);
                    valoare=sp.getInt("monezi", 0)-10;
                    editor.putInt("monezi", valoare);
                    editor.commit();
                    ((android.widget.TextView) findViewById(R.id.monezi)).setText("Coins: "+valoare);
                }
                if (sp.getInt("skin2", 0)==1){
                    editor.putInt("skinCurent", 2);
                    editor.commit();
                    refreshShopButtons();
                }
                break;
            case R.id.shop3:
                if ((sp.getInt("skin3", 0)==0)&&(sp.getInt("monezi", 0)<10))
                    toast.show();
                if ((sp.getInt("skin3", 0)==0)&&(sp.getInt("monezi", 0)>=10)){
                    ((android.widget.Button) findViewById(R.id.shop3)).setText("Use");
                    editor.putInt("skin3", 1);
                    valoare=sp.getInt("monezi", 0)-10;
                    editor.putInt("monezi", valoare);
                    editor.commit();
                    ((android.widget.TextView) findViewById(R.id.monezi)).setText("Coins: "+valoare);
                }
                if (sp.getInt("skin3", 0)==1){
                    editor.putInt("skinCurent", 3);
                    editor.commit();
                    refreshShopButtons();
                }
                break;
            case R.id.shop4:
                if ((sp.getInt("skin4", 0)==0)&&(sp.getInt("monezi", 0)<10))
                    toast.show();
                if ((sp.getInt("skin4", 0)==0)&&(sp.getInt("monezi", 0)>=10)){
                    ((android.widget.Button) findViewById(R.id.shop4)).setText("Use");
                    editor.putInt("skin4", 1);
                    valoare=sp.getInt("monezi", 0)-10;
                    editor.putInt("monezi", valoare);
                    editor.commit();
                    ((android.widget.TextView) findViewById(R.id.monezi)).setText("Coins: "+valoare);
                }
                if (sp.getInt("skin4", 0)==1){
                    editor.putInt("skinCurent", 4);
                    editor.commit();
                    refreshShopButtons();
                }
                break;
            case R.id.shop5:
                if ((sp.getInt("skin5", 0)==0)&&(sp.getInt("monezi", 0)<10))
                    toast.show();
                if ((sp.getInt("skin5", 0)==0)&&(sp.getInt("monezi", 0)>=10)){
                    ((android.widget.Button) findViewById(R.id.shop5)).setText("Use");
                    editor.putInt("skin5", 1);
                    valoare=sp.getInt("monezi", 0)-10;
                    editor.putInt("monezi", valoare);
                    editor.commit();
                    ((android.widget.TextView) findViewById(R.id.monezi)).setText("Coins: "+valoare);
                }
                if (sp.getInt("skin5", 0)==1){
                    editor.putInt("skinCurent", 5);
                    editor.commit();
                    refreshShopButtons();
                }
                break;
            case R.id.shop6:
                if ((sp.getInt("skin6", 0)==0)&&(sp.getInt("monezi", 0)<10))
                    toast.show();
                if ((sp.getInt("skin6", 0)==0)&&(sp.getInt("monezi", 0)>=10)){
                    ((android.widget.Button) findViewById(R.id.shop6)).setText("Use");
                    editor.putInt("skin6", 1);
                    valoare=sp.getInt("monezi", 0)-10;
                    editor.putInt("monezi", valoare);
                    editor.commit();
                    ((android.widget.TextView) findViewById(R.id.monezi)).setText("Coins: "+valoare);
                }
                if (sp.getInt("skin6", 0)==1){
                    editor.putInt("skinCurent", 6);
                    editor.commit();
                    refreshShopButtons();
                }
                break;
            case R.id.defaultul:
                editor.putInt("skinCurent", 0);
                editor.commit();
                refreshShopButtons();
                break;
        }
    }



}

