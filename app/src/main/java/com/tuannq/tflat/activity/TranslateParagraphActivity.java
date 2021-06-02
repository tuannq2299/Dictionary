package com.tuannq.tflat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.MaterialToolbar;

import com.tuannq.tflat.control.CRUD;
import com.tuannq.tflat.Model.TranslateParagraphHistory;
import com.tuannq.tflat.R;
import com.tuannq.tflat.control.Translator;
import com.tuannq.tflat.fragment.Fragment_ListParagraphHistory;

public class TranslateParagraphActivity extends AppCompatActivity {

    AutoCompleteTextView etWord;
    ImageView ivSearch;
    TextView tvTranslate;
    Translator VnTrans;
    MaterialToolbar topAppBar;
    Button btnParagraphHistory;
    FragmentManager fragmentManager;
    Fragment_ListParagraphHistory fragment_listParagraphHistory;
    CRUD database;
    Spinner spInputlang, spOutputLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NetworkOnMainThreadException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_paragraph);
        etWord = findViewById(R.id.etWord_EN_VN);
        ivSearch = findViewById(R.id.ivSearch_VI);
        tvTranslate = findViewById(R.id.tvTranslate_VI);
        topAppBar = findViewById(R.id.topAppBarVN);
        spInputlang = findViewById(R.id.spInputLang);
        spOutputLang = findViewById(R.id.spOutputLang);

        String lang[] = {"Vietnamese", "English", "French"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lang);
        spInputlang.setAdapter(adapter);
        spOutputLang.setAdapter(adapter);

        btnParagraphHistory = findViewById(R.id.btnParagraphHistory);
        fragmentManager = this.getSupportFragmentManager();
        fragment_listParagraphHistory = (Fragment_ListParagraphHistory) fragmentManager.findFragmentById(R.id.fragment_list);
        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentById(R.id.fragment_list)).show(fragmentManager.findFragmentById(R.id.fragment_history_paragraph)).commit();
        database = new CRUD(TranslateParagraphActivity.this);

        String word = getIntent().getStringExtra("word");
        String mean = getIntent().getStringExtra("mean");
        Log.d("xxxx", mean+word);
        if(!word.equals("")&&!mean.equals("")){
            tvTranslate.setText(mean);
            etWord.setText(word);
        }

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mặc định là en và vi
                String text = "" + etWord.getText().toString();
                String inputLang = "en";
                String outputLang = "vi";

                // check for input language

                String inputLangTemp  = (String) spInputlang.getSelectedItem();
                if (inputLangTemp=="Vietnamese"){
                    inputLang = "vi";
                } else if (inputLangTemp=="English"){
                    inputLang = "en";
                } else if (inputLangTemp=="French"){
                    inputLang = "fr";
                }

                // check for output language
                String outputLangTemp  = (String) spOutputLang.getSelectedItem();
                if (outputLangTemp=="Vietnamese"){
                    outputLang = "vi";
                } else if (outputLangTemp=="English"){
                    outputLang = "en";
                } else if (outputLangTemp=="French"){
                    outputLang = "fr";
                }
                // Run translator
                if(!inputLang.equals(outputLang)) {
                    TranslateParagraphHistory history = new TranslateParagraphHistory(text, "", inputLang, outputLang);
                    Translator trans = new Translator(tvTranslate, history, database);
                    trans.execute(text);
                    fragment_listParagraphHistory.list.add(0, history);
                } else {
                    tvTranslate.setText("- Ngôn ngữ đầu vào và đầu ra giống nhau");
                }
            }
        });
        // hiển thị fragment và update list khi click button
        btnParagraphHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_listParagraphHistory.notifyDataChanged();
                fragmentManager.beginTransaction().show(fragmentManager.findFragmentById(R.id.fragment_list)).hide(fragmentManager.findFragmentById(R.id.fragment_history_paragraph)).addToBackStack(null).commit();
            }
        });

        // navigation
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentManager.findFragmentById(R.id.fragment_list).isVisible()){
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentById(R.id.fragment_list)).show(fragmentManager.findFragmentById(R.id.fragment_history_paragraph)).commit();
                } else {
                    Intent intent = new Intent(TranslateParagraphActivity.this, MainActivity.class);
                    startActivity(intent);
                    TranslateParagraphActivity.this.finish();
                }
            }
        });

        // Menu item
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.favorite:
                        Toast.makeText(TranslateParagraphActivity.this,"Favorite",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.search:
                        Toast.makeText(TranslateParagraphActivity.this,"Search",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
}
}