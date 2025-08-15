package sdjini.Notifiy;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Arrays;

import sdjini.Notifiy.dog.*;
import sdjini.Notifiy.neko.*;
import sdjini.Notifiy.cat.*;

public class NotificationListener extends NotificationListenerService {

    public String NotificationTitle;
    public String NotificationContext;
    public String NotificationPackage;

    protected static String[] keyWord;
    protected static String[] wbList;

    private BARKED DogBarked;
    private sniff DogSniff;
    private carry nekoCarry;

    @SuppressLint("ForegroundServiceType")
    public void onCreate() {
        super.onCreate();
        DogBarked = new BARKED(this);
        DogSniff = new sniff(MainActivity.PublicActivity);
        nekoCarry = new carry(this);
        try{
            Log.d("NotificationListener", "onCreate: onCreate Start");
            DogBarked.print("NotificationListener", "onCreate: onCreate Start");
            String s;
            try {
                s = nekoCarry.useString("NotifiyConfig", "keyWord");
            }catch (NullPointerException e){
                nekoCarry.save("NotifiyConfig", "keyWord", "[\"com.tencent.mobileqq\",\"com.tencent.tim\",\"com.tencent.mm\"]");
                s = "[\"com.tencent.mobileqq\",\"com.tencent.tim\",\"com.tencent.mm\"]";
            }
            try {
                JSONArray jsonArray = new JSONArray(s);
                keyWord = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++)
                    keyWord[i] = jsonArray.getString(i);
            } catch (JSONException e) {
                Log.e("NotificationListener", "Json \"KeyWorld\" Pause Error", e);
                DogBarked.error("NotificationListener", e);
            }
            try {
                s = nekoCarry.useString("NotifiyConfig", "whiteList");
                JSONArray jsonArray = new JSONArray(s);
                wbList = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++)
                    wbList[i] = jsonArray.getString(i);
            }catch (JSONException e){
                Log.e("NotificationListener", "Json \"KeyWorld\" Pause Error", e);
                DogBarked.error("NotificationListener", e);
            } catch (NullPointerException e){
                wbList = null;
            }
            Log.d("NotificationListener", "onCreate: Start Foreground Start");
            DogBarked.print("NotificationListener", "onCreate: Start Foreground Start");
            if (!DogSniff.notificationPermission(MainActivity.PublicActivity))
                catchPermission.NotificationMeow(MainActivity.PublicActivity);
            createNotificationChannel();
            startForeground(1, Nya.Notification(this, "Air DMM is Running!NAY!!!", "如果悬浮窗一直显示notificationTitle和notificationContext请重启通知服务喵~"));
            Log.d("NotificationListener", "onCreate: Start Foreground End");
            DogBarked.print("NotificationListener", "onCreate: Start Foreground End");

            requestRebind(new ComponentName(this, NotificationListener.class));
            getActiveNotifications();
            Log.d("NotificationListener", "onCreate: onCreate End");
            DogBarked.print("NotificationListener", "onCreate: onCreate End");
        } catch (RuntimeException e) {
            DogBarked.error("NotificationListener",e);
        }
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        Log.d("NotificationListener", "onNotificationPosted: onNotificationPosted Start");
        DogBarked.print("NotificationListener", "onNotificationPosted: onNotificationPosted Start");

        Bundle bundle = sbn.getNotification().extras;
        NotificationTitle = bundle.getString(Notification.EXTRA_TITLE);
        NotificationContext = bundle.getString(Notification.EXTRA_TEXT);
        NotificationPackage = sbn.getPackageName();

        Log.d("NotificationListener","NotificationPackage:"+NotificationPackage);
        Log.d("NotificationListener","whiteList:"+ Arrays.toString(wbList));
        Log.d("NotificationListener", "NotificationTitle: "+NotificationTitle);
        Log.d("NotificationListener", "keyWord:"+ Arrays.toString(keyWord));
        Log.d("NotificationListener","NotificationContext:"+NotificationContext);

        Log.d("NotificationListener", "onNotificationPosted: Post on Float Window Start");
        DogBarked.print("NotificationListener", "onNotificationPosted: Post on Float Window Start");
        try {
            Log.d("NotificationListener", "containsKeyword:" + containsKeyword(NotificationPackage, wbList, nekoCarry.useBoolean("NotifiyConfig", "blackListMode")));
            if (containsKeyword(NotificationPackage, wbList, nekoCarry.useBoolean("NotifiyConfig", "blackListMode"))){
                Log.d("NotificationListener", Arrays.toString(keyWord) + ":" + containsKeyword(NotificationTitle, keyWord, nekoCarry.useBoolean("NotifiyConfig","invertKeyWord")));
                if (!(containsKeyword(NotificationTitle, keyWord, nekoCarry.useBoolean("NotifiyConfig","invertKeyWord")|containsKeyword(NotificationContext, keyWord, nekoCarry.useBoolean("NotifiyConfig","invertKeyWord"))))){
                    TextView TV_notificationTitle = FloatingWindow.floatingView.findViewById(R.id.TV_notificationTitle);

                    TV_notificationTitle.setText(NotificationTitle);
                    if (containsKeyword(NotificationTitle, keyWord, true))
                        TV_notificationTitle.setTextColor(Color.RED);
                    else TV_notificationTitle.setTextColor(Color.BLACK);

                    TextView TV_notificationContext = FloatingWindow.floatingView.findViewById(R.id.TV_notificationContext);

                    TV_notificationContext.setText(NotificationContext);
                    if (containsKeyword(NotificationContext, keyWord, true))
                        TV_notificationContext.setTextColor(Color.RED);
                    else TV_notificationContext.setTextColor(Color.BLACK);
                }
//                else {
//                    TextView TV_notificationTitle = FloatingWindow.floatingView.findViewById(R.id.TV_notificationTitle);
//
//                    if (containsKeyword(NotificationTitle, keyWord, true))
//                        TV_notificationTitle.setText(NotificationTitle);
//                    TextView TV_notificationContext = FloatingWindow.floatingView.findViewById(R.id.TV_notificationContext);
//
//                    if (containsKeyword(NotificationContext, keyWord, true))
//                        TV_notificationContext.setText(NotificationContext);
//                }
            }
        }catch (NullPointerException e){
            Log.e("NotificationListener", "onNotificationPosted: Post on Float Window Error: " + e);
            Log.e("NotificationListener", "onNotificationPosted: Post on Float Window Error: " + e);
        } finally {
            Log.d("NotificationListener", "onNotificationPosted: Post on Float Window End");
            DogBarked.print("NotificationListener", "onNotificationPosted: Post on Float Window End");
            Log.d("NotificationListener", "onNotificationPosted: onNotificationPosted End");
            DogBarked.print("NotificationListener", "onNotificationPosted: onNotificationPosted End");
        }
    }

    private void createNotificationChannel() {
        Log.d("NotificationListener", "createNotificationChannel: createNotificationChannel Start");
        DogBarked.print("NotificationListener", "createNotificationChannel: createNotificationChannel Start");
        if (!DogSniff.NotifiyPermission(MainActivity.PublicActivity))
            catchPermission.NotifiyMeow(MainActivity.PublicActivity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "notification_listener",
                    "Foreground Service",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel); // 确保注册
        }
        requestRebind(new ComponentName(this, NotificationListener.class));
        Log.d("NotificationListener", "createNotificationChannel: createNotificationChannel End");
        DogBarked.print("NotificationListener", "createNotificationChannel: createNotificationChannel End");
    }



    public void onDestroy(){
        super.onDestroy();
        Log.d("NotificationListener", "onDestroy: onDestroy");
        DogBarked.print("NotificationListener", "onDestroy: onDestroy");
        stopSelf();
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        Log.d("NotificationListener", "onListenerConnected: onListenerDisconnected");
        DogBarked.print("NotificationListener", "onListenerConnected: onListenerDisconnected");
    }

    @Override
    public void onListenerDisconnected() {
        super.onListenerDisconnected();
        Log.d("NotificationListener", "onListenerDisconnected: onListenerDisconnected");
        DogBarked.print("NotificationListener", "onListenerDisconnected: onListenerDisconnected");
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("NotificationListener", "onBind: onBind");
        DogBarked.print("NotificationListener", "onBind: onBind");
        return super.onBind(intent);
    }
    private boolean containsKeyword(String context,String[] key,boolean mode){
        //mode true = 有关键字返回true，mode false=有关键词返回false
        if (context == null || key == null) return false;
        boolean b = Arrays.stream(key).anyMatch(context::contains);
        if (mode) return b;
        else return !b;
    }
}