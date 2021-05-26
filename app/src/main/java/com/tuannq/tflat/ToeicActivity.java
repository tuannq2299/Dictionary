package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class ToeicActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toeic);
        topAppBar = findViewById(R.id.ToeicBack);
        topAppBar.setTitle("Toeic");
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ToeicActivity.this,TabActivity.class);
                startActivity(intent);
                ToeicActivity.this.finish();
            }
        });
    }
}