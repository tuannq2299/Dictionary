package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class TranslateActivity extends AppCompatActivity {

    TextView tvWord,tvMeaning;
    String url,word;
    MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        tvWord = findViewById(R.id.tvWord);
        tvMeaning = findViewById(R.id.tvMeaning);
        word = getIntent().getStringExtra("word");
        tvWord.setText(word);
        url = dictionaryEntries(word);
        topAppBar = findViewById(R.id.topAppBar);
        DictionaryRequest dr = new DictionaryRequest(tvMeaning);
        dr.execute(url);
 //       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        topAppBar.setTitle(tvWord.getText().toString());
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TranslateActivity.this,TabActivity.class);
                startActivity(intent);
                TranslateActivity.this.finish();
            }
        });

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.favorite:
                        boolean mstate = !item.isChecked();
                        if(mstate){
                            item.setChecked(mstate);
                            item.setIcon(R.drawable.favorite);
                        }
                        else{
                            item.setChecked(mstate);
                            item.setIcon(R.drawable.no_favorite);
                        }
                        break;
                    case R.id.search:
                        Toast.makeText(TranslateActivity.this,"Search",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.more:
                        break;
                    case R.id.menuSetting:
                        Toast.makeText(TranslateActivity.this,"Setting",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        // Toast.makeText(MainActivity.this,"selected",Toa)
//        switch (item.getItemId()){
//            case R.id.menuSetting:
//                Toast.makeText(TranslateActivity.this,"Setting selected",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menuSearch:
//                Toast.makeText(TranslateActivity.this,"Search selected",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menuExit:
//                TranslateActivity.this.finish();
//                break;
//            case android.R.id.home:
//                // todo: goto back activity from here
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
    private String dictionaryEntries(String word) {
        final String language = "en-gb";

//        final String fields = "defintions";
//        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id ;//+ "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

}