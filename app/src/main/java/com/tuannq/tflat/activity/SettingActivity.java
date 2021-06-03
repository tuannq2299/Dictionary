package com.tuannq.tflat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.appbar.MaterialToolbar;
import com.tuannq.tflat.control.AlarmReceiver;
import com.tuannq.tflat.R;

public class SettingActivity extends AppCompatActivity {
    Switch switchNoti;
    MaterialToolbar toolbar;
    PendingIntent alarmIntent;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        switchNoti=findViewById(R.id.switchNoti);
        sp = this.getSharedPreferences("status",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        toolbar=findViewById(R.id.topSetting);
        toolbar.setTitle("Setting");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(SettingActivity.this, MainActivity.class);
//                startActivity(intent);
                SettingActivity.this.finish();
            }
        });
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0,intent, 0 );
        switchNoti.setChecked(sp.getBoolean("switchNoti",false));
        switchNoti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                if(isChecked){
                    long interval = 86400000;
                    manager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+interval,interval,alarmIntent);
                    editor.putBoolean("switchNoti",true).apply();
                }
                else{
                    if(manager!=null){
                        manager.cancel(alarmIntent);
                        editor.putBoolean("switchNoti",false).apply();
                    }

                }
            }
        });
    }
}