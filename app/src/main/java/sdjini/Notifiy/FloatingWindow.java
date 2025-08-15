package sdjini.Notifiy;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import sdjini.Notifiy.dog.BARKED;
import sdjini.Notifiy.dog.sniff;
import sdjini.Notifiy.cat.*;

public class FloatingWindow extends Service {

    public WindowManager windowManager;
    @SuppressLint("StaticFieldLeak")
    public static View floatingView;
    private BARKED DogBarked;
    private sniff DogSniff;

    @SuppressLint("InflateParams")
    @Override
    public void onCreate(){
        super.onCreate();
        try{
            DogBarked = new BARKED(this);
            DogSniff = new sniff(MainActivity.PublicActivity);

            Log.d("FloatingWindow", "onCreate: onCreate Start");
            DogBarked.print("FloatingWindow", "onCreate: onCreate Start");

            Log.d("FloatingWindow", "onCreate: Create New FloatyWindow Start");
            DogBarked.print("FloatingWindow", "onCreate: Create New FloatyWindow Start");

            Log.d("FloatingWindow", "onCreate: FloatyWindow Init Start");
            DogBarked.print("FloatingWindow", "onCreate: FloatyWindow Init Start");
            windowManagerCreate:
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            floatingView = layoutInflater.inflate(R.layout.floating_layout, null);
            Log.d("FloatingWindow", "onCreate: FloatyWindow Init End");
            DogBarked.print("FloatingWindow", "onCreate: FloatyWindow Init End");

            Log.d("FloatingWindow", "onCreate: FloatyWindow Setting Start");
            DogBarked.print("FloatingWindow", "onCreate: FloatyWindow Setting Start");
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT,
                    android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ? LayoutParams.TYPE_APPLICATION_OVERLAY : LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    PixelFormat.TRANSLUCENT);
            layoutParams.gravity = Gravity.TOP | Gravity.START;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            float screenHeight = displayMetrics.heightPixels * MainActivity.positionHeight / 100F;
            layoutParams.y = (int) screenHeight;
            Log.d("FloatingWindow", "onCreate: FloatyWindow Setting End");
            DogBarked.print("FloatingWindow", "onCreate: FloatyWindow Setting End");

            Log.d("FloatingWindow", "onCreate: Create New FloatyWindow End");
            DogBarked.print("FloatingWindow", "onCreate: Create New FloatyWindow End");

            if (!sniff.OverlayPermission(this))
                catchPermission.OverlayMeow(MainActivity.PublicActivity);
            try {
                windowManager.addView(floatingView, layoutParams);
            } catch (NullPointerException e) {
                windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                windowManager.addView(floatingView, layoutParams);
            }
            LinearLayout background = floatingView.findViewById(R.id.background);
            background.setAlpha(MainActivity.alpha / 100.0F);
            Log.d("FloatingWindow", "onCreate: onCreate End");
            DogBarked.print("FloatingWindow", "onCreate: onCreate End");
        } catch (RuntimeException e){
            DogBarked.error("FloatingWindow",e);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("FloatingWindow","onDestroy: onDestroy Start");
        DogBarked.print("FloatingWindow","onDestroy: onDestroy Start");
        if (floatingView != null && floatingView.getWindowToken() != null) {
            windowManager.removeView(floatingView);
        }
        floatingView = null;
        Log.d("FloatingWindow","onDestroy: onDestroy End");
        DogBarked.print("FloatingWindow","onDestroy: onDestroy End");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("FloatingWindow", "onBind: onBind Start");
        DogBarked.print("FloatingWindow", "onBind: onBind Start");
        Log.d("FloatingWindow","onBind: onBind End");
        DogBarked.print("FloatingWindow","onBind: onBind End");
        return null;
    }
}