package com.example.fallingfruits;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Random;

public class GameView extends SurfaceView {

    private Bitmap imagine;
    private Bitmap imagineFundal;
    private Bitmap cos;
    private Bitmap o;
    private Bitmap moneda;
    private SurfaceHolder holder;
    private GameThread gameThread;
    public float x,y;
    public int cx,cy;
    public static boolean valid;
    public static boolean coin;
    public int c1,c2;
    public int[] o1={0,0,0,0,0,0};
    public int[] o2={0,0,0,0,0,0};

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    public GameView(Context context) {
        super(context);
        gameThread= new GameThread(this);
        imagine= BitmapFactory.decodeResource(getResources(),R.drawable.apple);
        cos=BitmapFactory.decodeResource(getResources(),R.drawable.basket);
        o=BitmapFactory.decodeResource(getResources(),R.drawable.obstacle);
        moneda=BitmapFactory.decodeResource(getResources(),R.drawable.coin);
        holder= getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                for (int j=0;j<6;j++){
                    o1[j]=0;
                    o2[j]=0;
                }
                gameThread.setRunning(true);
                gameThread.start();
                x=0;
                y=(getScreenWidth()-150)/2;
                cx=getScreenHeight()-250;
                Random random=new Random();
                cy=random.nextInt(getScreenWidth()-400);
                c1=random.nextInt(getScreenWidth()-100);
                c2=random.nextInt(getScreenHeight()-350);
                valid=true;
                coin=true;
                for (int j=0;j<Level.nivelul;j++){
                    o1[j]=random.nextInt(getScreenWidth()-100);
                    o2[j]=random.nextInt(getScreenHeight()-350);
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                boolean retry=true;
                gameThread.setRunning(false);
                if (x>cx)
                    if ((y>=cy)&&(y<=cy+400))
                        if (valid)
                            Level.scorul=Level.scorul+1;
                while (retry){
                    try {
                        gameThread.join();
                        retry=false;
                    }catch (InterruptedException e){

                    }
                }

            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas){
        //super.onDraw(canvas);
        imagine=getResizedBitmap(imagine,150, 150);
        cos=getResizedBitmap(cos,400,200);
        o=getResizedBitmap(o,80,80);
        moneda=getResizedBitmap(moneda,80,80);
        x=x+getScreenHeight()/60;
        if ((y+Level.inclinare>0)&&(y+Level.inclinare+150<getScreenWidth()))
            y=y+Level.inclinare;
        switch (Level.fundal) {
            case 0:
                imagineFundal= BitmapFactory.decodeResource(getResources(),R.drawable.backgroundplain);
                break;
            case 1:
                imagineFundal= BitmapFactory.decodeResource(getResources(),R.drawable.background1);
                break;
            case 2:
                imagineFundal= BitmapFactory.decodeResource(getResources(),R.drawable.background2);
                break;
            case 3:
                imagineFundal= BitmapFactory.decodeResource(getResources(),R.drawable.background3);
                break;
            case 4:
                imagineFundal= BitmapFactory.decodeResource(getResources(),R.drawable.background4);
                break;
            case 5:
                imagineFundal= BitmapFactory.decodeResource(getResources(),R.drawable.background5);
                break;
            case 6:
                imagineFundal= BitmapFactory.decodeResource(getResources(),R.drawable.background6);
                break;
        }
        canvas.drawBitmap(imagineFundal, 0, 0, null);
        canvas.drawBitmap(cos, cy, cx, null);

        if (x<cx)
            canvas.drawBitmap(imagine, y, x, null);
        if ((x>cx)&&((y<cy)||(y>cy+400)))
            canvas.drawBitmap(imagine, y, x, null);

        if (coin)
            canvas.drawBitmap(moneda, c1, c2, null);

        for (int j=0;j<Level.nivelul;j++){
            if (o1[j]!=0)
                if (o2[j]!=0)
                    canvas.drawBitmap(o, o1[j], o2[j], null);
        }

        if (valid)
            for (int j=0;j<Level.nivelul;j++){
                if((o1[j]>y)&&(o1[j]<y+150)&&(o2[j]>x)&&(o2[j]<x+150))
                    valid=false;
            }


        if (coin)
            for (int j=0;j<Level.nivelul;j++){
                if((c1>y)&&(c1<y+150)&&(c2>x)&&(c2<x+150)){
                    coin=false;
                    SharedPreferences sp=Level.contextul.getSharedPreferences("Stocare",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    int curent=sp.getInt("monezi", 0);
                    editor.putInt("monezi", curent+1);
                    editor.commit();
                }
            }
    }


}
