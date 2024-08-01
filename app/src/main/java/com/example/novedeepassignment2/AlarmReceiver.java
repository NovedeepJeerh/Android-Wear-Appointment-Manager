package com.example.novedeepassignment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("appointments", Context.MODE_PRIVATE);
        Set<String> appointmentsSet = sharedPreferences.getStringSet("appointments", null);

        if (appointmentsSet != null) {
            long currentTime = System.currentTimeMillis();
            for (String appointment : appointmentsSet) {
                String[] parts = appointment.split(",");
                if (parts.length >= 3) {
                    String dateTimeString = parts[2];
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    try {
                        Date appointmentDate = format.parse(dateTimeString);
                        if (appointmentDate != null && Math.abs(appointmentDate.getTime() - currentTime) < 60 * 1000) {
                            // Trigger the alarm (vibration, notification, etc.)
                            // For example, vibrate the watch
                            // Vibrate for 500 milliseconds
                            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            if (vibrator != null && vibrator.hasVibrator()) {
                                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
