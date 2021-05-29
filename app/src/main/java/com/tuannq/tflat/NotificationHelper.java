package com.tuannq.tflat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;

public class NotificationHelper extends ContextWrapper {

    private static final String CHANNEL_ID = "com.tuannq.tflat.NOTI";
    private static final String CHANNEL_NAME = "NOTI Channel";
    private NotificationManager manager;
    public NotificationHelper(Context base) {
        super(base);
        createChannels();
    }

    private void createChannels() {
        NotificationChannel notiChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
        notiChannel.enableLights(true);
        notiChannel.enableVibration(true);
        notiChannel.setLightColor(Color.GREEN);
        notiChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(notiChannel);
    }

    public NotificationManager getManager() {
        if(manager==null){
            manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public Notification.Builder getNotiChannel(String title,String body){
        Intent resultIntent = new Intent(this,TranslateActivity.class);
        resultIntent.putExtra("word",title);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        return new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentText(body)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.icon)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
    }
}
