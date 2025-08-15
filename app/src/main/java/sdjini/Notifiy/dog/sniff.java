package sdjini.Notifiy.dog;

import sdjini.Notifiy.MainActivity;
import sdjini.Notifiy.cat.*;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import sdjini.Notifiy.NotificationListener;

public class sniff {

    private BARKED DogBarked = new BARKED(MainActivity.PublicActivity);
    public sniff(Activity activity){
        DogBarked.print("DogSniff","Init");
        if (!OverlayPermission(activity.getApplicationContext())) catchPermission.OverlayMeow(activity);
        if (!NotifiyPermission(activity.getApplicationContext())) catchPermission.NotifiyMeow(activity);
        if (!foregroundServicePermission(activity.getApplicationContext()) && notificationPermission(activity.getApplicationContext()))
            catchPermission.NotificationMeow(activity);
    }

    public static boolean OverlayPermission(Context context){
        return Settings.canDrawOverlays(context);
    }
    public static boolean NotifiyPermission(Context context){
        ComponentName cn = new ComponentName(context, NotificationListener.class);
        String flat = Settings.Secure.getString(context.getContentResolver(),
                "enabled_notification_listeners");
        return flat != null && flat.contains(cn.flattenToString());

    }
    public static boolean foregroundServicePermission(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.FOREGROUND_SERVICE) == PackageManager.PERMISSION_GRANTED;
    }
    public static boolean notificationPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13及以上需要POST_NOTIFICATIONS权限
            return ContextCompat.checkSelfPermission(context,
                    Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        } else {
            // Android 13以下默认有通知权限
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            return notificationManager.areNotificationsEnabled();
        }
    }


}
