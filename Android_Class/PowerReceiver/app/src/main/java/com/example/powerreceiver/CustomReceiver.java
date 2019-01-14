package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            String message = "Unknown intent action";
            switch (intentAction) {
                case Intent.ACTION_HEADSET_PLUG:
                    message = "Headset plugging!";
                    break;
                case Intent.ACTION_POWER_CONNECTED:
                    message = "Power connected!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    message = "Power disconnected!";
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    double numberRandom = intent.getDoubleExtra("rnd", 0);
                    double valueSqrt = intent.getDoubleExtra("sqrt", 0);
                    message = "Custom Broadcast receiver!\n";
                    message = message + "Square of the random number " + numberRandom + " is: " + valueSqrt;
                    break;
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
