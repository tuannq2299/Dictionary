package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import Model.Present;

public class PresentActivity extends AppCompatActivity {

    ListView lvPresent;
    ArrayList<Present> arrPresent;
    PresentAdapter adapter;
    MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);
        topAppBar=findViewById(R.id.presentBack);
        topAppBar.setTitle("Directory");
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PresentActivity.this,TabActivity.class);
                startActivity(intent);
                PresentActivity.this.finish();
            }
        });
        lvPresent=findViewById(R.id.listPresent);
        lvPresent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(PresentActivity.this,QuesActivity.class);
                //sửa cái này đi
                //mỗi 1 item thì str phải khác
                // ông xử lý cái này sau nhá
                //String str = "thtd";//o day getindex ra //arrPresent.get(position).getIndex();
                String str=arrPresent.get(position).getIndex();
                in.putExtra("keyword", str);
                startActivity(in);

            }
        });
        Anhxa();
        adapter = new PresentAdapter(this, R.layout.present_one_line,arrPresent);
        lvPresent.setAdapter(adapter);

    }
    private void Anhxa(){
        lvPresent = (ListView) findViewById(R.id.listPresent);
        arrPresent = new ArrayList<>();
        //thtd hoặc lấy theo số 1,2 ở đầu
        //Khơi tạo chèm them index vào
        //ok chua vay nua la xong
        //ok de t thu
        arrPresent.add(new Present("1 ","1. Present simple","    (Thì hiện tại đơn)"));
        arrPresent.add(new Present("2 ","2. Present continuous tense","    ( Thì hiện tại tiếp diễn )"));
        arrPresent.add(new Present("3 ","3. Present perfect tense","    ( Thì hiện hoàn thành )"));
        arrPresent.add(new Present("4 ","4. Present perfect continuous tense","    ( Thì hiện tại hoàn thành tiếp diễn )"));
        arrPresent.add(new Present("5 ","5. Past simple tense","    ( Thì quá khứ đơn )"));
        arrPresent.add(new Present("6 ","6. Past continuous tense","    ( Thì quá khứ tiếp diễn )"));
        arrPresent.add(new Present("7 ","7. Past perfect tense","    ( Thì quá khứ hoàn thành )"));
        arrPresent.add(new Present("8 ","8. Past perfect continuous tense","    ( Thì quá khứ hoàn thành tiếp diễn )"));
        arrPresent.add(new Present("9 ","9. Simple future tense","    ( Thì tương lai đơn )"));
        arrPresent.add(new Present("10 ","10. Future continuous tense","    ( Thì tương lai tiếp diễn )"));
        arrPresent.add(new Present("11 ","11. Future perfect tense","    ( Thì tương lai hoàn thành )"));
        arrPresent.add(new Present("12 ","12. Future perfect continuous tense","    ( Thì tương lai hoàn thành tiếp diễn )"));
    }
}