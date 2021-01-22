package com.logic;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import java.util.LinkedList;

/**
 * BroadCastReciever 를 응용한 네트워크 상태를 알 수 있는 메니저
 *
 */
public class NetworkManager extends BroadcastReceiver implements Runnable {

    private final Context context;
    private volatile boolean mRegistered;
    private ConnectivityManager.NetworkCallback mCallback;
    private Thread mEventNotifier;
    private int mConnectedNetworks = 0;
    private LinkedList<Boolean> mEvents = new LinkedList<>();

    public NetworkManager( Context context ){
        this.context = context;

    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    @SuppressWarnings("deprecation")
    private void registerLegacyReceiver()
    {
        /* deprecated since API level 28 */
        context.registerReceiver(this, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    @Override
    public void run() {

    }
}
