package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivSearch;
    EditText etSearch;
    TextView tvBanner;
    Button btnHistory,btnYW,btnVA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivSearch=findViewById(R.id.ivSearch);
        etSearch=findViewById(R.id.etSearch);
        tvBanner=findViewById(R.id.tvBanner);
        btnHistory=findViewById(R.id.btnHistory);
        btnYW=findViewById(R.id.btnYW);
        btnVA=findViewById(R.id.btnVA);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestApiButton(v);
            }
        });
        btnVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestTranslateVi(v);
            }
        });
    }
    public void requestApiButton(View v){
        if(etSearch.getText().toString().length()>0) {

            String word = etSearch.getText().toString().trim();
            Intent intent = new Intent(MainActivity.this, TranslateActivity.class);
            intent.putExtra("word", word);
            startActivity(intent);

        }
    }
    public void requestTranslateVi(View v){
            Intent intent = new Intent(MainActivity.this, VnActivity.class);
            startActivity(intent);
    }

}