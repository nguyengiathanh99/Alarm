package com.example.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Receiver", "Hello");
        String chuoi_string = intent.getExtras().getString("extra");
        Log.e("Key",chuoi_string);
        Intent myIntent = new Intent(context,Music.class);
        myIntent.putExtra("extra",chuoi_string);
        context.startService(myIntent);
    }
}
