package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                Intent i = new Intent(QuesActivity.this,PresentActivity.class);
                startActivity(i);
            }
        });
        init();



        String keyword = getIntent().getStringExtra("keyword"); //thtd
        //CRUD
        CRUD crud = new CRUD(QuesActivity.this);
        //Get Question để test sau này sửa sau
        //Làm xong insert 3-4 câu hỏi để test nghiệp vụ xem đúng logic không
        questionArrayList = crud.getAllQuestion();

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

                if(index<questionArrayList.size()){
                    btnA.setText("1");
                    renderQuestion(questionArrayList.get(index));
                }
                else {
//                    Tao intern goi ve man PresentActivity
//                    Hien thi so cau tra loi dung
                                    //PresentActivity.this
//                    Toast.makeText(QuesActivity.this, "Bạn trả lời đúng: "+rs+"/"+
//                            questionArrayList.size()+"câu.", Toast.LENGTH_LONG);
                }
            }
        });

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