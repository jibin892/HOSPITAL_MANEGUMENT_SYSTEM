package com.msg91.sendotp.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Admin_home extends AppCompatActivity {
Button phycologist_btn,terapy_btn,event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        phycologist_btn=findViewById(R.id.phycologist_btn);
        terapy_btn=findViewById(R.id.terapy_btn);
event=findViewById(R.id.event);

//
//        event.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent jj= new Intent(getApplicationContext(),Event_admin.class);
//                startActivity(jj );
//            }
//        });
//
//
//
//terapy_btn.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        Intent j= new Intent(getApplicationContext(),Class_time_Admin.class);
//        startActivity(j);
//    }
//});
//
//        phycologist_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i= new Intent(getApplicationContext(), Teachers_detailes_Admin.class);
//                startActivity(i);
//            }
//        });
    }

}

