package sdjini.Notifiy.dog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sdjini.Notifiy.MainActivity;
import sdjini.Notifiy.R;
import sdjini.Notifiy.neko.Nya;

public class BARKED {
    private static File logDir;
    private static Context privateContext;
    private static File logFile;
    private static Nya nekoNya;

    public BARKED(Context context){
        privateContext = context;
        nekoNya = new Nya(context);
//        print("DogBarked","Init");
    }

    public static void print(String tag, String content) {
        try{
            logDir = new File(privateContext.getExternalFilesDir(null),"logs");
            if (!logDir.exists()) logDir.mkdir();
            logFile = new File(logDir, "log.txt");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedTime = sdf.format(new Date());
            if (!logFile.exists())logFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(logFile, true);
            String logContext = formattedTime + "\t" + tag + "\t" + content + "\n";
            fos.write(logContext.getBytes());
            fos.close();
        }catch (IOException | NullPointerException e){
            nekoNya.Nyano(privateContext.getString(R.string.LogWriteFiled));
            Log.e("DogBarked",""+e);
        }
    }
    public static void error(String tag,Exception error){
        logDir = new File(privateContext.getExternalFilesDir(null),"logs");
        if (!logDir.exists()) logDir.mkdir();
        logFile = new File(logDir, "log.txt");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = sdf.format(new Date());
        try{
            if (!logFile.exists())logFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(logFile, true);
            String logContext = formattedTime + "\t" + tag + "\tRUFF!RUFF!\t" + error + "\n";
            fos.write(logContext.getBytes());
            fos.close();
        }catch (IOException e){
            nekoNya.Nyano(privateContext.getString(R.string.LogWriteFiled));
            Log.e("DogBarked",""+e);
        }
    }
}
