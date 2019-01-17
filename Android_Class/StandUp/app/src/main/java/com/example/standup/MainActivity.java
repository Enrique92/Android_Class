package com.example.standup;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 0;
    private static final String CHANNEL_ID = "notification_channel";
    private ToggleButton alarmToggle;
    private NotificationManager mManager;
    private AlarmManager alarmManager;
    private PendingIntent notifyPendingIntent;

    public void createNotificationChannel() {
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "Stand up Notification", NotificationManager.IMPORTANCE_HIGH
            );
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(Color.RED);
            channel.setDescription("Notifications every 15 min, stand up!!");
            mManager.createNotificationChannel(channel);
        }
    }

    public void deliverNotification(Context context) {
        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, CHANNEL_ID
        ).setSmallIcon(R.drawable.ic_standup)
                .setContentTitle("Stand up Alert!")
                .setContentText("You should stand up!")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        mManager.notify(NOTIFICATION_ID, builder.build());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a Intent for the Pending Intent and then update the flag.
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        notifyPendingIntent = PendingIntent.getBroadcast(
                this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmToggle = findViewById(R.id.toggleButton);
        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String message;

                if (isChecked) {
                    //deliverNotification(MainActivity.this);

                    long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES / 3000;
                    // The current elapsed time in milliseconds
                    long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;
                    // The alarm type
                    alarmManager.setInexactRepeating(
                            AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval, notifyPendingIntent
                    );
                    message = "Stand up, alarm ON!";
                } else {
                    if (alarmManager != null) {
                        alarmManager.cancel(notifyPendingIntent);
                    }
                    mManager.cancelAll();
                    message = "Stand up, alarm OFF";
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        createNotificationChannel();
    }

    public void nextAlarm(View view) {
        String alarmInfo = "There is not alarm!";
        if (alarmManager != null) {
            long nextAlarmTime = alarmManager.getNextAlarmClock().getTriggerTime();
            String date = DateFormat.getDateTimeInstance().format(new Date(nextAlarmTime));
            Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();
        }
        Toast.makeText(MainActivity.this, alarmInfo, Toast.LENGTH_LONG).show();
    }
}
