package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class TranslateActivity extends AppCompatActivity {

    TextView tvWord,tvMeaning;
    String url,word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        tvWord = findViewById(R.id.tvWord);
        tvMeaning = findViewById(R.id.tvMeaning);
        word = getIntent().getStringExtra("word");
        tvWord.setText(word);
        url = dictionaryEntries(word);
        DictionaryRequest dr = new DictionaryRequest(tvMeaning);
        dr.execute(url);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Toast.makeText(MainActivity.this,"selected",Toa)
        switch (item.getItemId()){
            case R.id.menuSetting:
                Toast.makeText(TranslateActivity.this,"Setting selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuSearch:
                Toast.makeText(TranslateActivity.this,"Search selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuExit:
                TranslateActivity.this.finish();
                break;
            case android.R.id.home:
                // todo: goto back activity from here
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }
    private String dictionaryEntries(String word) {
        final String language = "en-gb";

//        final String fields = "defintions";
//        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id ;//+ "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

}