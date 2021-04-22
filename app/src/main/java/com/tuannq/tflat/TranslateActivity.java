package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
    }
    private String dictionaryEntries(String word) {
        final String language = "en-gb";
//        final String fields = "defintions";
//        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id ;//+ "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

}