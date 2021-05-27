package com.tuannq.tflat;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import Model.Word;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        CRUD crud = new CRUD(context);
        ArrayList<Word>listWords = crud.getFavoriteWords();
        Random rd= new Random();
        Word w=listWords.get(rd.nextInt(listWords.size()));
        NotificationHelper helper = new NotificationHelper(context);
        Notification.Builder builder = helper.getNotiChannel(w.getWord(),"YOUR FAVORITE WORD");
        helper.getManager().notify(1,builder.build());
    }
}
