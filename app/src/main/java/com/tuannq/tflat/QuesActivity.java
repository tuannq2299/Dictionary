package com.tuannq.tflat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import Model.Question;

public class QuesActivity extends AppCompatActivity {
    MaterialToolbar topAppBar;
    ArrayList<Question> questionArrayList;
    int index = 0;
    int rs = 0;

    TextView tvQuestion;
    Button btnA;
    Button btnB;
    Button btnC;
    Button btnD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);
        topAppBar = findViewById(R.id.quesBack);
        topAppBar.setTitle("Present");
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuesActivity.this, PresentActivity.class);
                startActivity(i);
            }
        });
        init();
        String keyword = getIntent().getStringExtra("keyword"); //thtd
        //CRUD
        CRUD crud = new CRUD(QuesActivity.this);
        questionArrayList=crud.getAllQuestionByType(keyword);

        if(index==0){
            renderQuestion(questionArrayList.get(index));
        }

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnA.getText().equals(questionArrayList.get(index).getRs())){
                    rs+=1;
                }
                Log.d("1", btnA.getText()+"");
                index+=1;
                if(index<questionArrayList.size()){
                    renderQuestion(questionArrayList.get(index));
                }
                else {
                    AlertDialog.Builder alertDialog= new AlertDialog.Builder(QuesActivity.this);
                    alertDialog.setTitle("Xac nhan thoat");
                    alertDialog.setMessage("Bạn trả lời đúng: "+rs+"/"+questionArrayList.size()+"câu.");
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(QuesActivity.this,PresentActivity.class);
                            startActivity(intent);
                        }
                    });
                    alertDialog.show();
                }
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnB.getText().equals(questionArrayList.get(index).getRs())){
                    rs+=1;
                }
                Log.d("1", btnB.getText()+"");
                index+=1;
                if(index<questionArrayList.size()){
                    btnB.setText("1");
                    renderQuestion(questionArrayList.get(index));
                }
                else {
                    AlertDialog.Builder alertDialog= new AlertDialog.Builder(QuesActivity.this);
                    alertDialog.setTitle("Xac nhan thoat");
                    alertDialog.setMessage("Bạn trả lời đúng: "+rs+"/"+questionArrayList.size()+"câu.");
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(QuesActivity.this,PresentActivity.class);
                            startActivity(intent);

                        }
                    });
                    alertDialog.show();
                }
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnC.getText().equals(questionArrayList.get(index).getRs())){
                    rs+=1;
                }
                Log.d("1", btnC.getText()+"");

                index+=1;
                if(index<questionArrayList.size()){
                    btnC.setText("1");
                    renderQuestion(questionArrayList.get(index));
                }
                else {
                    AlertDialog.Builder alertDialog= new AlertDialog.Builder(QuesActivity.this);
                    alertDialog.setTitle("Xac nhan thoat");
                    alertDialog.setMessage("Bạn trả lời đúng: "+rs+"/"+questionArrayList.size()+"câu.");
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(QuesActivity.this,PresentActivity.class);
                            startActivity(intent);

                        }
                    });

                    alertDialog.show();
                }
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnD.getText().equals(questionArrayList.get(index).getRs())){
                    rs+=1;
                }
                Log.d("1", btnD.getText()+"");
                index+=1;
                if(index<questionArrayList.size()){
                    btnD.setText("1");
                    renderQuestion(questionArrayList.get(index));
                }
                else {
                    AlertDialog.Builder alertDialog= new AlertDialog.Builder(QuesActivity.this);
                    alertDialog.setTitle("Xac nhan thoat");
                    alertDialog.setMessage("Bạn trả lời đúng: "+rs+"/"+questionArrayList.size()+"câu.");
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(QuesActivity.this,PresentActivity.class);
                            startActivity(intent);

                        }
                    });
                    alertDialog.show();
                }
            }
        });
    }


    void init(){
        tvQuestion = findViewById(R.id.tvQuestion);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
    }


    void renderQuestion(Question q){
        tvQuestion.setText(q.getQuestion());
        btnA.setText(q.getA());
        btnB.setText(q.getB());
        btnC.setText(q.getC());
        btnD.setText(q.getD());
    }
}