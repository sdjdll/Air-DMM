package sdjini.Notifiy;

import sdjini.Notifiy.cat.*;
import sdjini.Notifiy.dog.*;
import sdjini.Notifiy.neko.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public static int positionHeight;
    public static int delayTime;
    public static int alpha;
    public static Activity PublicActivity;

    private Button BTN_saveOptions;
    private Button BTN_startFloatWindow;
    private Button BTN_restartFloatWindow;
    private Button BTN_closeFloatWindow;
    private Button BTN_notifiyConfig;
    private Button BTN_restartNotifiy;


    private EditText ET_positionHeight;
    private EditText ET_delayTime;
    private EditText ET_alpha;
    private BARKED DogBarked;
    private sniff DogSniff;
    private Nya nekoNya;
    private carry nekoCarry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PublicActivity = this;

        DogSniff = new sniff(this);
        DogBarked = new BARKED(this);
        nekoNya = new Nya(this);
        nekoCarry = new carry(this);
        try{
            setContentView(R.layout.activity_main);

            Log.d("MainActivity", "onCreate: onCreate Start");
            DogBarked.print("MainActivity", "onCreate: onCreate Start");

            Log.d("MainActivity", "onCreate: Init Start");
            DogBarked.print("MainActivity", "onCreate: Init Start");

            BTN_saveOptions = findViewById(R.id.BTN_saveOptions);
            BTN_startFloatWindow = findViewById(R.id.BTN_startFloatWindow);
            BTN_restartFloatWindow = findViewById(R.id.BTN_restartFloatWindow);
            BTN_closeFloatWindow = findViewById(R.id.BTN_closeFloatWindow);
            BTN_notifiyConfig = findViewById(R.id.BTN_notifiyConfig);
            BTN_restartNotifiy = findViewById(R.id.BTN_restartNotifiy);

            ET_positionHeight = findViewById(R.id.ET_positionHeight);
            ET_delayTime = findViewById(R.id.ET_delayTime);
            ET_alpha = findViewById(R.id.ET_alpha);


            Log.d("MainActivity", "onCreate: Init End");
            DogBarked.print("MainActivity", "onCreate: Init End");

            Log.d("MainActivity", "onCreate: Config Read Start");
            DogBarked.print("MainActivity", "onCreate: Config Read Start");
            positionHeight = nekoCarry.useInt("FloatWindowConfig","positionHeight");
            delayTime = nekoCarry.useInt("FloatWindowConfig","delayTime");
            alpha = nekoCarry.useInt("FloatWindowConfig","alpha");
            ET_positionHeight.setText(String.valueOf(positionHeight));
            ET_delayTime.setText(String.valueOf(delayTime));
            ET_alpha.setText(String.valueOf(alpha));
            Log.d("MainActivity", "onCreate: Config Read End");
            DogBarked.print("MainActivity", "onCreate: Config Read End");

            Log.d("MainActivity", "onCreate: BTN Init Start");
            DogBarked.print("MainActivity", "onCreate: BTN Init Start");
            BTN_saveOptions.setOnClickListener(v -> BtnAct_saveOptionsI());
            BTN_startFloatWindow.setOnClickListener(v -> BtnAct_startFloatWindow());
            BTN_restartFloatWindow.setOnClickListener(v -> Nya.Nyano(getString(R.string.Text1)));
            BTN_closeFloatWindow.setOnClickListener(v -> Nya.Nyano(getString(R.string.Text1)));
            BTN_notifiyConfig.setOnClickListener(v -> BtnAct_notifiyConfig());
            BTN_restartNotifiy.setOnClickListener(v -> BtnAct_restartNotifiy());
            Log.d("MainActivity", "onCreate: BTN Init End");
            DogBarked.print("MainActivity", "onCreate: BTN Init End");
//            startService(new Intent(this, NotificationListener.class));
/*
        //DEBUG
        Button StartService = findViewById(R.id.StartService);
        Button TestService = findViewById(R.id.TestService);

        StartService.setOnClickListener(v -> StartService());
        TestService.setOnClickListener(v -> TestService());
*/
            Log.d("MainActivity", "onCreate: onCreate End");
            DogBarked.print("MainActivity", "onCreate: onCreate End");
        } catch (RuntimeException e) {
            DogBarked.error("MainActivity",e);
        }
    }
/*
    //DEBUG
    private void StartService(){
        startService(new Intent(this, NotificationListener.class));
        NotificationListener.requestRebind(new ComponentName(this, NotificationListener.class));
    }
    private void TestService(){
        new NotificationListener().getActiveNotifications();
    }
*/
    private void BtnAct_restartNotifiy(){
        catchPermission.NotifiyMeow(this);
    }

    private void BtnAct_notifiyConfig(){
        Log.d("MainActivity","BtnAct_notifiyConfig: BtnAct_notifiyConfig Start");
        DogBarked.print("MainActivity","BtnAct_notifiyConfig: BtnAct_notifiyConfig Start");
        startActivity(new Intent(this, NotifiyConfig.class));
        Log.d("MainActivity","BtnAct_notifiyConfig: BtnAct_notifiyConfig End");
        DogBarked.print("MainActivity","BtnAct_notifiyConfig: BtnAct_notifiyConfig End");
    }
    private void BtnAct_saveOptionsI()  {
        Log.d("MainActivity","BtnAct_saveOptions: BtnAct_saveOptionsI Start");
        DogBarked.print("MainActivity","BtnAct_saveOptions: BtnAct_saveOptionsI Start");

        Log.d("MainActivity","BtnAct_saveOptions: Get ET Start");
        DogBarked.print("MainActivity","BtnAct_saveOptions: Get ET Start");
        try {
            positionHeight = Integer.parseInt(ET_positionHeight.getText().toString());
            delayTime = Integer.parseInt(ET_delayTime.getText().toString());
            alpha = Integer.parseInt(ET_alpha.getText().toString());
            if (alpha > 100 | alpha < 0) throw new IllegalArgumentException("alpha is error number");
        } catch (NumberFormatException e){
            Log.e("MainActivity", "BtnAct_saveOptions:" + e);
            DogBarked.error("MainActivity", e);
            nekoNya.Nyano(getString(R.string.Text5));
        } catch (NullPointerException e){
            positionHeight = 50;
            delayTime = 3;
            alpha = 50;
            Log.e("MainActivity", "BtnAct_saveOptions:" + e);
            DogBarked.error("MainActivity", e);
            nekoNya.Nyano(getString(R.string.Text4));
            nekoNya.Nyano(getString(R.string.Text2));
        } catch (IllegalArgumentException e){
            alpha = 50;
            Log.e("MainActivity", "BtnAct_saveOptions:" + e);
            DogBarked.error("MainActivity", e);
            nekoNya.Nyano(getString(R.string.Text3));
            nekoNya.Nyano(getString(R.string.Text2));
        }
        Log.d("MainActivity","BtnAct_saveOptions: Get ET End");
        DogBarked.print("MainActivity","BtnAct_saveOptions: Get ET End");

        Log.d("MainActivity","BtnAct_saveOptions: Write Config Start");
        DogBarked.print("MainActivity","BtnAct_saveOptions: Write Config Start");
        nekoCarry.save("FloatWindowConfig", "positionHeight", positionHeight);
        nekoCarry.save("FloatWindowConfig", "delayTime", delayTime);
        nekoCarry.save("FloatWindowConfig", "alpha", alpha);
        Log.d("MainActivity","BtnAct_saveOptions: Write Config End");
        DogBarked.print("MainActivity","BtnAct_saveOptions: Write Config End");

        Log.d("MainActivity","BtnAct_saveOptions: BtnAct_saveOptionsI End");
        DogBarked.print("MainActivity","BtnAct_saveOptions: BtnAct_saveOptionsI End");
    }
    private void BtnAct_saveOptionsII(){
        Log.d("MainActivity","BtnAct_saveOptions: BtnAct_saveOptionsII Start");
        DogBarked.print("MainActivity","BtnAct_saveOptions: BtnAct_saveOptionsII Start");
        BtnAct_saveOptionsI();
        if (!DogSniff.OverlayPermission(this)) {
            catchPermission.OverlayMeow(this);
            Log.d("MainActivity","BtnAct_saveOptions: BtnAct_saveOptionsII End");
            DogBarked.print("MainActivity","BtnAct_saveOptions: BtnAct_saveOptionsII End");
            return;
        }
        BtnAct_restartFloatWindow();
        Log.d("MainActivity","BtnAct_saveOptions: BtnAct_saveOptionsII End");
        DogBarked.print("MainActivity","BtnAct_saveOptions: BtnAct_saveOptionsII End");
    }
    private void BtnAct_startFloatWindow(){
        Log.d("MainActivity","BtnAct_startFloatWindow: BtnAct_startFloatWindow Start");
        DogBarked.print("MainActivity","BtnAct_startFloatWindow: BtnAct_startFloatWindow Start");
        if (!DogSniff.OverlayPermission(this)) {
            catchPermission.OverlayMeow(this);
            Log.d("MainActivity","BtnAct_startFloatWindow: BtnAct_startFloatWindow End");
            DogBarked.print("MainActivity","BtnAct_startFloatWindow: BtnAct_startFloatWindow End");
            return;
        }
        startService(new Intent(this, FloatingWindow.class));

        Log.d("MainActivity","BtnAct_startFloatWindow: BTN Reset Start");
        DogBarked.print("MainActivity","BtnAct_startFloatWindow: BTN Reset Start");
        BTN_restartFloatWindow.setOnClickListener(v -> BtnAct_restartFloatWindow());
        BTN_closeFloatWindow.setOnClickListener(v -> BtnAct_closeFloatWindow());
        BTN_startFloatWindow.setOnClickListener(v -> Nya.Nyano(getString(R.string.Text6)));
        BTN_saveOptions.setOnClickListener(v -> BtnAct_saveOptionsII());
        Log.d("MainActivity","BtnAct_startFloatWindow: BTN Reset End");
        DogBarked.print("MainActivity","BtnAct_startFloatWindow: BTN Reset End");

        Log.d("MainActivity","BtnAct_startFloatWindow: NotificationListener Rebind");
        DogBarked.print("MainActivity","BtnAct_startFloatWindow: NotificationListener Rebind");
//        NotificationListener notificationListener = new NotificationListener();
//        NotificationListenerService.requestRebind(new ComponentName(this,notificationListener.getClass()));
        Log.d("MainActivity","BtnAct_startFloatWindow: BtnAct_startFloatWindow End");
        DogBarked.print("MainActivity","BtnAct_startFloatWindow: BtnAct_startFloatWindow End");
    }

    private void BtnAct_restartFloatWindow() {
        Log.d("MainActivity","BtnAct_restartFloatWindow: BtnAct_restartFloatWindow Start");
        DogBarked.print("MainActivity","BtnAct_restartFloatWindow: BtnAct_restartFloatWindow Start");
        BtnAct_closeFloatWindow();
        BtnAct_startFloatWindow();
        Log.d("MainActivity","BtnAct_restartFloatWindow: BtnAct_restartFloatWindow End");
        DogBarked.print("MainActivity","BtnAct_restartFloatWindow: BtnAct_restartFloatWindow End");
    }
    private void BtnAct_closeFloatWindow(){
        Log.d("MainActivity","BtnAct_closeFloatWindow: BtnAct_closeFloatWindow Start");
        DogBarked.print("MainActivity","BtnAct_closeFloatWindow: BtnAct_closeFloatWindow Start");
        stopService(new Intent(this, FloatingWindow.class));

        Log.d("MainActivity","BtnAct_closeFloatWindow: BTN Reset Start");
        DogBarked.print("MainActivity","BtnAct_closeFloatWindow: BTN Reset Start");
        BTN_startFloatWindow.setOnClickListener(v -> BtnAct_startFloatWindow());
        BTN_restartFloatWindow.setOnClickListener(v -> Nya.Nyano(getString(R.string.Text1)));
        BTN_closeFloatWindow.setOnClickListener(v -> Nya.Nyano(getString(R.string.Text1)));
        BTN_saveOptions.setOnClickListener(v -> BtnAct_saveOptionsI());
        Log.d("MainActivity","BtnAct_closeFloatWindow: BTN Reset End");
        DogBarked.print("MainActivity","BtnAct_closeFloatWindow: BTN Reset End");

        Log.d("MainActivity","BtnAct_closeFloatWindow: BtnAct_closeFloatWindow End");
        DogBarked.print("MainActivity","BtnAct_closeFloatWindow: BtnAct_closeFloatWindow End");
    }
}