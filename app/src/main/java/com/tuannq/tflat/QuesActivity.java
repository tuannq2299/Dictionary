package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import Model.Question;

public class QuesActivity extends AppCompatActivity {
    MaterialToolbar topAppBar;
    ArrayList<Question> questionArrayList;
    int index = 0;
    int rs = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);
        topAppBar = findViewById(R.id.quesBack);
        topAppBar.setTitle("Present");
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuesActivity.this,PresentActivity.class);
                startActivity(i);
            }
        });
      //  String keyword = getIntent().getStringExtra("keyword"); //thtd
        //Lay danh sach cau hoi
        //CRUD
      //  CRUD crud = new CRUD(QuesActivity.this);
      //  questionArrayList = crud.getAllQuestionByType(keyword);

        //hien thi cau hoi so 0
       // renderQuestion(questionArrayList.get(0));


        //Onclick nut A goi CheckDapAn
        //lay value nut a so sanh a.getValue().equal( questionList.get(index).getRs());
        //neu bang thi rs+1;
        // index+1
        // Kiem tra xem con cau hoi con khong index+1 < questionList.size()
        // set lai view cau hoi tiep theo
        //renderQuestion(questionArrayList.get(index));
        // else hien thi ket qua rs;

        //...               CheckDapAn
        //Nut D             CheckDapAn

        // Check Dap An
        //Kiểm tra kết quả và lưu kết quả.
        //Biến kết quả + 1
        //Tải câu hỏi tiếp theo
        //Hiển thị kết quả nếu hết
    }
   // void renderQuestion(Question q){
        // hien thi cau hoi ra view 4 dap an
//        tx.setValue(q.getQuestion());
   // }
}