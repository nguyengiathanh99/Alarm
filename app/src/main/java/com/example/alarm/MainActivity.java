package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnHen,btnHuy;
    TextView txtTimer;
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;
        btnHen = (Button)findViewById(R.id.btnHen);
        btnHuy = (Button)findViewById(R.id.btnHuy);
        txtTimer = (TextView)findViewById(R.id.txtTimer);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
        btnHen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());

                int gio = timePicker.getCurrentHour();
                int phut = timePicker.getCurrentMinute();

                String string_gio = String.valueOf(gio);
                String string_phut = String.valueOf(phut);
                if(gio > 12) {
                    string_gio = String.valueOf(gio - 12);
                }
                if (phut < 10 ) {
                    string_phut = "0" + String.valueOf(phut);
                }
                intent.putExtra("extra","on");
                pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT
                );
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                txtTimer.setText("Giờ bạn đặt là: " + string_gio + ":" + string_phut);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTimer.setText("Dừng lại");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("extra","off");
                sendBroadcast(intent);
            }
        });
    }
}