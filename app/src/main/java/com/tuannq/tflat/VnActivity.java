package com.tuannq.tflat;

import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

public class VnActivity extends AppCompatActivity {

    EditText etWord;
    ImageView ivSearch;
    TextView tvTranslate;
    Translator VnTrans;
    MaterialToolbar topAppBar;
    RadioGroup rgInput, rgOutput;
    RadioButton rb_InputVn, rb_InputEn, rb_InputFr, rb_OutputVn, rb_OutputEn, rb_OutputFr;
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

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    Translator trans = new Translator(tvTranslate, inputLang, outputLang);
                    trans.execute(text);
                } else {
                    tvTranslate.setText("- Ngôn ngữ đầu vào và đầu ra giống nhau");
                }
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
//    private static String translate( String text) throws IOException {
//        // INSERT YOU URL HERE
//        String urlStr = "https://script.google.com/macros/s/AKfycbwWT9BNzQRMANmu-hgLYwxYB0fOWKd-1L76A0O5laLhiCcz5bfV2QJCovtOHve5SlVx/exec" +
//                "?q=" + URLEncoder.encode(text, "UTF-8") +
//                "&target=" + "vi" +
//                "&source=" + "en";
//        URL url = new URL(urlStr);
//        StringBuilder response = new StringBuilder();
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestProperty("User-Agent", "Mozilla/5.0");
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//        return response.toString();
//    }

}