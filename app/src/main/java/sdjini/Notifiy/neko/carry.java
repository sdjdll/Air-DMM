package sdjini.Notifiy.neko;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class carry {
    private static Context privateContext;
    public carry(Context context) {
        privateContext = context;
    }
    public static void save(String configFile, String key, String value){
        SharedPreferences preferences = privateContext.getSharedPreferences(configFile,privateContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static void save(String configFile, String key, int value){
        SharedPreferences preferences = privateContext.getSharedPreferences(configFile,privateContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,value);
        editor.apply();
    }
    public static void save(String configFile, String key, boolean value){
        SharedPreferences preferences = privateContext.getSharedPreferences(configFile,privateContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public static void save(String configFile, String key, float value){
        SharedPreferences preferences = privateContext.getSharedPreferences(configFile,privateContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key,value);
        editor.apply();
    }
    public static String useString(String configFile, String key){
        SharedPreferences preferences = privateContext.getSharedPreferences(configFile,privateContext.MODE_PRIVATE);
        String value = preferences.getString(key,null);
        if (value == null) Log.e("carry",configFile+"/"+key+" is null");
        return (value != null)?value:"null";
    }
    public static int useInt(String configFile, String key){
        SharedPreferences preferences = privateContext.getSharedPreferences(configFile,privateContext.MODE_PRIVATE);
        int value = preferences.getInt(key, -1);
        if (value == -1) Log.e("carry",configFile+"/"+key+" is null");
        return (value != -1)?value:0;
    }
    public static float useFloat(String configFile, String key){
        SharedPreferences preferences = privateContext.getSharedPreferences(configFile,privateContext.MODE_PRIVATE);
        float value = preferences.getFloat(key, -1.0F);
        if (value == -1.0F) Log.e("carry",configFile+"/"+key+" is null");
        return (value != -1.0F)?value:-1.0F;
    }
    public static boolean useBoolean(String configFile, String key){
        SharedPreferences preferences = privateContext.getSharedPreferences(configFile,privateContext.MODE_PRIVATE);
        boolean value = preferences.getBoolean(key, false);
        return value;
    }
}
