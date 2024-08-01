package com.example.novedeepassignment2;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import androidx.core.app.NotificationCompat;

public class NotificationUtils {

    @SuppressLint("NotificationPermission")
    public static void notifyAppointment(Context context, String appointmentId, String appointmentName, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(context, "appointments")
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setContentTitle(appointmentName)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        notificationManager.notify(Integer.parseInt(appointmentId), notification);
    }
}

