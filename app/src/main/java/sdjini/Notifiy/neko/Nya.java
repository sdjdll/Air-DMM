package sdjini.Notifiy.neko;

import sdjini.Notifiy.MainActivity;
import sdjini.Notifiy.dog.*;

import android.app.Notification;
import android.content.Context;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import sdjini.Notifiy.R;

public class Nya {

    private static Context PrivateContext;
    private static BARKED DogBarked = new BARKED(MainActivity.PublicActivity);
    public Nya(Context context){
        PrivateContext = context;
    }

    public static void Nyano(String msg) {
//        DogBarked.print("Neko","Nya");
        Toast.makeText(PrivateContext,msg,Toast.LENGTH_SHORT).show();
    }

    public static void MIYA(String msg){
        DogBarked.print("Neko","MIYA");
        Toast.makeText(PrivateContext,msg,Toast.LENGTH_LONG).show();
    }

    public static Notification Notification(Context context,String title, String msg) {
        DogBarked.print("NekoNotification", "Notification: Notification Building");
        Notification notification = new NotificationCompat.Builder(context, "notification_listener")
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setOngoing(true)
                .build();
        notification.flags |= Notification.FLAG_NO_CLEAR;
        return notification;
    }
}
