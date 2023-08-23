package com.example.fallingfruits;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Level extends AppCompatActivity {
    public static final String TAG = "lifecycle";
    SharedPreferences sp;

    private Button buttonStart;
    private Button buttonBackAfter;
    private TextView score;
    private TextView TVnume;
    private EditText editul;
    private String numele;
    public static int nivelul;
    public static float inclinare;
    public static int scorul=0;
    GameView chestie;
    public static int fundal;
    private float[] acc = new float[3];
    private float[] mags = new float[3];
    private float[] values = new float[3];
    SensorManager sManager;
    public static Context contextul;

    private SensorEventListener mySensorEventListener = new SensorEventListener()
    {
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
        }

        public void onSensorChanged(SensorEvent event)
        {
            switch (event.sensor.getType())
            {
                case Sensor.TYPE_MAGNETIC_FIELD:
                    mags = event.values.clone();
                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    acc = event.values.clone();
                    break;
            }

            if (mags != null && acc != null)
            {
                float[] gravity = new float[9];
                float[] magnetic = new float[9];
                SensorManager.getRotationMatrix(gravity, magnetic, acc, mags);
                float[] outGravity = new float[9];
                SensorManager.remapCoordinateSystem(gravity,
                        SensorManager.AXIS_X,
                        SensorManager.AXIS_Z,
                        outGravity);
                SensorManager.getOrientation(outGravity, values);

                float azimuth = values[0] * 57.2957795f;
                float pitch = values[1] * 57.2957795f;
                float roll = values[2] * 57.2957795f;
                inclinare=roll;
                //textViewToDisplayRotation.setText("X = " + azimuth + "\nY = " + pitch + "\nZ = " + roll);
                mags = null;
                acc = null;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.level);
        setTitle("Level");
        Log.d(TAG,"onCreate Level");
        buttonStart = findViewById(R.id.ButtonStart);
        buttonStart.setVisibility(View.VISIBLE);
        buttonBackAfter = findViewById(R.id.ButtonBackAfter);
        buttonBackAfter.setVisibility(View.INVISIBLE);
        score = findViewById(R.id.TVend);
        score.setVisibility(View.INVISIBLE);
        TVnume = findViewById(R.id.TVnume);
        TVnume.setVisibility(View.VISIBLE);
        editul = findViewById(R.id.Editul);
        editul.setVisibility(View.VISIBLE);
        sp = getApplicationContext().getSharedPreferences("Stocare",Context.MODE_PRIVATE);
        nivelul=sp.getInt("nivel", 0);
        //mImageView = findViewById(R.id.myimageview);
        fundal=sp.getInt("skinCurent", 0);
        FrameLayout ll = (FrameLayout) findViewById(R.id.LLnivel);
        switch (fundal) {
            case 0:
                ll.setBackgroundResource(R.drawable.backgroundplain);
                break;
            case 1:
                ll.setBackgroundResource(R.drawable.background1);
                break;
            case 2:
                ll.setBackgroundResource(R.drawable.background2);
                break;
            case 3:
                ll.setBackgroundResource(R.drawable.background3);
                break;
            case 4:
                ll.setBackgroundResource(R.drawable.background4);
                break;
            case 5:
                ll.setBackgroundResource(R.drawable.background5);
                break;
            case 6:
                ll.setBackgroundResource(R.drawable.background6);
                break;
        }
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart Level");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume Level");

        sManager.registerListener(mySensorEventListener,
                sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(mySensorEventListener,
                sManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause Level");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop Level");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart Level");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Level");
    }

    public void startLevel(View view){
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        buttonStart = findViewById(R.id.ButtonStart);
        buttonStart.setVisibility(View.INVISIBLE);
        buttonBackAfter = findViewById(R.id.ButtonBackAfter);
        score = findViewById(R.id.TVend);
        TVnume.setVisibility(View.INVISIBLE);
        editul.setVisibility(View.INVISIBLE);
        numele= editul.getText().toString();
        sp = getApplicationContext().getSharedPreferences("Stocare",Context.MODE_PRIVATE);
        scorul=0;

        contextul=getApplicationContext();
        Context context=getApplicationContext();
            new CountDownTimer(70000, 7000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    chestie=new GameView(context);
                    setContentView(chestie);
                }
                @Override
                public void onFinish() {
                    //Log.d(TAG, "Cycle1");
                    setContentView(R.layout.level);
                    buttonStart = findViewById(R.id.ButtonStart);
                    buttonStart.setVisibility(View.INVISIBLE);
                    buttonBackAfter = findViewById(R.id.ButtonBackAfter);
                    score = findViewById(R.id.TVend);
                    TVnume = findViewById(R.id.TVnume);
                    TVnume.setVisibility(View.INVISIBLE);
                    editul = findViewById(R.id.Editul);
                    editul.setVisibility(View.INVISIBLE);
                    scorul=scorul*nivelul*10;
                    score.setText("Scorul:"+scorul+" by "+numele);
                    buttonBackAfter.setVisibility(View.VISIBLE);
                    score.setVisibility(View.VISIBLE);
                    int[] highscores = new int [] {0, 0, 0, 0, 0, 0};
                    String[] nume = {"", "", "", "", "", ""};
                    highscores[0]=sp.getInt("sc1", 0);
                    highscores[1]=sp.getInt("sc2", 0);
                    highscores[2]=sp.getInt("sc3", 0);
                    highscores[3]=sp.getInt("sc4", 0);
                    highscores[4]=sp.getInt("sc5", 0);
                    nume[0]=sp.getString("nm1", "Default");
                    nume[1]=sp.getString("nm2", "Default");
                    nume[2]=sp.getString("nm3", "Default");
                    nume[3]=sp.getString("nm4", "Default");
                    nume[4]=sp.getString("nm5", "Default");
                    highscores[5]=scorul;
                    nume[5]=numele;
                    for (int a=0;a<5;a++)
                        for (int b=a+1;b<6;b++)
                            if (highscores[a]<highscores[b])
                            {
                                int c=highscores[a];
                                highscores[a]=highscores[b];
                                highscores[b]=c;
                                String d=nume[a];
                                nume[a]=nume[b];
                                nume[b]=d;
                            }
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("sc1", highscores[0]);
                    editor.putInt("sc2", highscores[1]);
                    editor.putInt("sc3", highscores[2]);
                    editor.putInt("sc4", highscores[3]);
                    editor.putInt("sc5", highscores[4]);
                    editor.putString("nm1", nume[0]);
                    editor.putString("nm2", nume[1]);
                    editor.putString("nm3", nume[2]);
                    editor.putString("nm4", nume[3]);
                    editor.putString("nm5", nume[4]);
                    editor.commit();
                    scorul=0;
                    sp = getApplicationContext().getSharedPreferences("Stocare",Context.MODE_PRIVATE);
                    FrameLayout ll = (FrameLayout) findViewById(R.id.LLnivel);
                    switch (fundal) {
                        case 0:
                            ll.setBackgroundResource(R.drawable.backgroundplain);
                            break;
                        case 1:
                            ll.setBackgroundResource(R.drawable.background1);
                            break;
                        case 2:
                            ll.setBackgroundResource(R.drawable.background2);
                            break;
                        case 3:
                            ll.setBackgroundResource(R.drawable.background3);
                            break;
                        case 4:
                            ll.setBackgroundResource(R.drawable.background4);
                            break;
                        case 5:
                            ll.setBackgroundResource(R.drawable.background5);
                            break;
                        case 6:
                            ll.setBackgroundResource(R.drawable.background6);
                            break;
                    }
                }
            }.start();

    }

    public void goBackAfter(View view){
        buttonStart.setVisibility(View.VISIBLE);
        buttonBackAfter.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        startActivity(new Intent(this, LevelSelect.class));
    }


}
