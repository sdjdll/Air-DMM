package sdjini.Notifiy;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Arrays;

import sdjini.Notifiy.dog.BARKED;
import sdjini.Notifiy.neko.Nya;
import sdjini.Notifiy.neko.carry;

public class NotifiyConfig extends AppCompatActivity {

    private TextInputEditText TIE_whiteList;
    private TextInputEditText TIE_keyWord;
    private BARKED DogBarked = new BARKED(this);
    private carry nekoCarry = new carry(this);
    private Nya nekoNya = new Nya(this);
    private Switch SWT_blackListMode;
    private Switch SWT_invertKeyWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiy_config);

        Button BTN_saveNotifiyConfig = findViewById(R.id.BTN_saveNotifiyConfig);

        TIE_whiteList = findViewById(R.id.TIE_whiteList);
        TIE_keyWord = findViewById(R.id.TIE_KeyWord);

        SWT_blackListMode = findViewById(R.id.SWT_blackListMode);
        SWT_invertKeyWord = findViewById(R.id.SWT_invertKeyWord);

        TIE_whiteList.setText(nekoCarry.useString("NotifiyConfig","whiteList"));
        TIE_keyWord.setText(nekoCarry.useString("NotifiyConfig","keyWord"));

        SWT_blackListMode.setChecked(nekoCarry.useBoolean("NotifiyConfig", "blackListMode"));
        SWT_blackListMode.setOnCheckedChangeListener((compoundButton, b) -> SwtAct_blackListMode(b));
        SWT_invertKeyWord.setChecked(nekoCarry.useBoolean("NotifiyConfig", "invertKeyWord"));
        SWT_invertKeyWord.setOnCheckedChangeListener((compoundButton, b) -> SwtAct_invertKeyWord(b));

        BTN_saveNotifiyConfig.setOnClickListener(v -> BtnAct_saveNotifiyConfig());
    }

    private void SwtAct_blackListMode(boolean b){
        nekoCarry.save("NotifiyConfig", "blackListMode", b);
        if (b) {
            nekoNya.Nyano(getString(R.string.SwtAct_blackListModeTrue1));
            nekoNya.Nyano(getString(R.string.SwtAct_blackListModeTrue2));
        } else {
            nekoNya.Nyano(getString(R.string.SwtAct_blackListModeFalse1));
            nekoNya.Nyano(getString(R.string.SwtAct_blackListModeFalse2));
        }
    }
    private void SwtAct_invertKeyWord(boolean b){
        nekoCarry.save("NotifiyConfig", "invertKeyWord", b);
        if (b) {
            nekoNya.Nyano(getString(R.string.SwtAct_invertKeyWordModeTrue1));
            nekoNya.Nyano(getString(R.string.SwtAct_invertKeyWordModeTrue2));
        } else {
            nekoNya.Nyano(getString(R.string.SwtAct_invertKeyWordModeFalse1));
            nekoNya.Nyano(getString(R.string.SwtAct_invertKeyWordModeFalse2));
        }
    }
    private void BtnAct_saveNotifiyConfig(){
        String wblist = String.valueOf(TIE_whiteList.getText());
        nekoCarry.save("NotifiyConfig", "wblist", wblist);
        String keyWord = String.valueOf(TIE_keyWord.getText());
        nekoCarry.save("NotifiyConfig", "keyWord", keyWord);
        String[] list;
        try{
            JSONArray jsonArray = new JSONArray(wblist);
            list = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++)
                list[i] = jsonArray.getString(i);
        } catch (JSONException e) {
            list = null;
            Log.e("NotifiyConfig", "Json \"WhiteList\" Pause Error", e);
            DogBarked.error("NotifiyConfig", e);
        }
        NotificationListener.wbList = list;
        try{
            JSONArray jsonArray = new JSONArray(keyWord);
            list = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++)
                list[i] = jsonArray.getString(i);
        } catch (JSONException e) {
            list = null;
            Log.e("NotifiyConfig", "Json \"KeyWorld\" Pause Error", e);
            DogBarked.error("NotifiyConfig", e);
        }
        NotificationListener.keyWord = list;
        Log.d("NotifiyConfig","Update:"+"WhiteList:"+ Arrays.toString(NotificationListener.wbList)+"KeyWord:"+ Arrays.toString(NotificationListener.keyWord));
    }
}