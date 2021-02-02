package com.dream.kimlibrary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.dream.kimlibrary.R;

public class WifiManagerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_manager);

 //       Context.WIFI_AWARE_SERVICE
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if( manager.isWifiEnabled() ){

        } else {

        }
    }
}