package sdjini.Notifiy.cat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import sdjini.Notifiy.dog.BARKED;

public class catchPermission {
    public static void OverlayMeow(Activity activity){
        activity.startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.getPackageName())), 2);
    }
    public static void NotifiyMeow(Activity activity){
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            activity.startActivity(intent);
        } catch (Exception e) {
            Log.e("dog sniff", "" + e);
            BARKED.error("dog sniff", e);
        }
    }
    public static void NotificationMeow(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},1);
        }
    }
}
