package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class VnActivity extends AppCompatActivity {

    EditText etWord;
    ImageView ivSearch;
    TextView tvTranslate;
    Translator VnTrans;
    MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NetworkOnMainThreadException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vn);
        etWord = findViewById(R.id.etWord_EN_VN);
        ivSearch = findViewById(R.id.ivSearch_VI);
        tvTranslate = findViewById(R.id.tvTranslate_VI);
        topAppBar = findViewById(R.id.topAppBarVN);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "" + etWord.getText().toString();
                Translator trans = new Translator(tvTranslate);
                trans.execute(text);
            }
        });

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VnActivity.this,TabActivity.class);
                startActivity(intent);
                VnActivity.this.finish();
            }
        });

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.favorite:
                        Toast.makeText(VnActivity.this,"Favorite",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.search:
                        Toast.makeText(VnActivity.this,"Search",Toast.LENGTH_SHORT).show();
                        break;


                }
                return false;
            }
        });
}
    private static String translate( String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbwWT9BNzQRMANmu-hgLYwxYB0fOWKd-1L76A0O5laLhiCcz5bfV2QJCovtOHve5SlVx/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + "vi" +
                "&source=" + "en";
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}