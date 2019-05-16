package com.example.thelast.tampilan;

import android.app.Application;
import android.os.StrictMode;

import com.example.thelast.utils.DBHelper;
import com.onesignal.OneSignal;

import java.io.IOException;

import supriyanto.tampilan.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("proximanova-regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        OneSignal.startInit(getApplicationContext()).init();

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}