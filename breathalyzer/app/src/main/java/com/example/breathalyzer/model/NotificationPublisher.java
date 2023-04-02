package com.example.breathalyzer.model;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.breathalyzer.R;

public class NotificationPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Water Notification");
        builder.setSmallIcon(R.drawable.water_drop);
        builder.setContentTitle("Get Hydrated!");
        builder.setContentText("Time is up. This is a friendly reminder to stay hydrated.");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Set the intent that will fire when the user taps the notification
         builder.setAutoCancel(true);



        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        //user notification
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(200, builder.build());


    }
}
