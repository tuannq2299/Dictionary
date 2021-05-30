package com.tuannq.tflat;

import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.MaterialToolbar;

import Model.TranslateParagraphHistory;

public class VnActivity extends AppCompatActivity {

    EditText etWord;
    ImageView ivSearch;
    TextView tvTranslate;
    Translator VnTrans;
    MaterialToolbar topAppBar;
    RadioGroup rgInput, rgOutput;
    RadioButton rb_InputVn, rb_InputEn, rb_InputFr, rb_OutputVn, rb_OutputEn, rb_OutputFr;
    Button btnParagraphHistory;
    FragmentManager fragmentManager;
    Fragment_ListParagraphHistory fragment_listParagraphHistory;
    CRUD database;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NetworkOnMainThreadException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vn);
        etWord = findViewById(R.id.etWord_EN_VN);
        ivSearch = findViewById(R.id.ivSearch_VI);
        tvTranslate = findViewById(R.id.tvTranslate_VI);
        topAppBar = findViewById(R.id.topAppBarVN);
        rgInput = findViewById(R.id.rgInput);
        rgOutput = findViewById(R.id.rgOutput);
        rb_InputVn = findViewById(R.id.rb_InputVn);
        rb_InputEn = findViewById(R.id.rb_InputEn);
        rb_InputFr = findViewById(R.id.rb_inputFr);
        rb_OutputVn = findViewById(R.id.rb_OutputVn);
        rb_OutputEn = findViewById(R.id.rb_OutputEn);
        rb_OutputFr = findViewById(R.id.rb_OutputFr);
        btnParagraphHistory = findViewById(R.id.btnParagraphHistory);
        fragmentManager = this.getSupportFragmentManager();
        fragment_listParagraphHistory = (Fragment_ListParagraphHistory) fragmentManager.findFragmentById(R.id.fragment_list);
        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentById(R.id.fragment_list)).show(fragmentManager.findFragmentById(R.id.fragment_history_paragraph)).commit();
        database = new CRUD(VnActivity.this);

        String word = getIntent().getStringExtra("word");
        String mean = getIntent().getStringExtra("mean");

        if(!word.equals("")&&!!mean.equals("")){
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
                int checkedRadioInputId = rgInput.getCheckedRadioButtonId();
                int checkedRadioOutputId = rgOutput.getCheckedRadioButtonId();
                if (checkedRadioInputId == R.id.rb_InputVn){
                    inputLang = "vi";
                } else if (checkedRadioInputId == R.id.rb_InputEn){
                    inputLang = "en";
                } else if (checkedRadioInputId == R.id.rb_inputFr){
                    inputLang = "fr";
                }

                // check for output language
                if (checkedRadioOutputId == R.id.rb_OutputVn){
                    outputLang = "vi";
                } else if (checkedRadioOutputId == R.id.rb_OutputEn){
                    outputLang = "en";
                } else if (checkedRadioOutputId == R.id.rb_OutputFr){
                    outputLang = "fr";
                }
                // Run translator
                if(!inputLang.equals(outputLang)) {
                    TranslateParagraphHistory history = new TranslateParagraphHistory(text, "", inputLang, outputLang);
                    Translator trans = new Translator(tvTranslate, history, database);
                    trans.execute(text);
                    history.setOutputParagraph(tvTranslate.getText().toString());
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
                Intent intent=new Intent(VnActivity.this,TabActivity.class);
                startActivity(intent);
                VnActivity.this.finish();
            }
        });

        // Menu item
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
}