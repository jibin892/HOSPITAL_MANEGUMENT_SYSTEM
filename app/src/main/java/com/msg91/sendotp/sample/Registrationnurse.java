package com.msg91.sendotp.sample;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class Registrationnurse extends AppCompatActivity {
    private DatePicker datePicker;

    private TextView dateView;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    Intent intent ;
    EditText name, email, dob,address;
    TextView nametxt, emailtxt, dobtxt,addresstxt, login_title;
    TextView logo;
    String ph;
    LinearLayout already_have_account_layout;
    CardView register_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationnurse);

        intent=getIntent();
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        dob = findViewById(R.id.dob);
       dob.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               datePickerDialog = new DatePickerDialog(Registrationnurse.this,
                       new DatePickerDialog.OnDateSetListener() {
                           @Override
                           public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                               dob.setText(day + "/" + (month + 1) + "/" + year);
                           }
                       }, year, month, dayOfMonth);
              // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
               datePickerDialog.show();
           }
       });


        name = findViewById(R.id.name);
        nametxt = findViewById(R.id.nametxt);
        email = findViewById(R.id.email);
        emailtxt = findViewById(R.id.emailtxt);

        dobtxt = findViewById(R.id.dobtxt);
        address = findViewById(R.id.address);
        addresstxt= findViewById(R.id.addresstxt);
//        logo = findViewById(R.id.logo);

        already_have_account_layout = findViewById(R.id.already_have_account_text);
        register_card = findViewById(R.id.register_card);

    }
//    String emailPattern = "@gmail.com";

    public void registerButton(View view) {
        if (name.getText().toString().isEmpty()){

          name.setError("Empty Field");
        }
        else if ( email.getText().toString().isEmpty()){

            email.setError("Empty Field");
        }
        else if (dob.getText().toString().isEmpty()){


            dob.setError("Empty Field");


        }
        else if (address.getText().toString().isEmpty()){
            address.setError("Empty Field");

        }
//        else if (email.getText().toString().trim().matches("@gmail.com")){
//            Toast.makeText(this,"Enter Valid Email",Toast.LENGTH_SHORT).show();
//
//
//        }
        else
            {
                Intent i=new Intent(getApplicationContext(), regtwonurse.class);
                i.putExtra("name",name.getText().toString());
                i.putExtra("email",email.getText().toString());
                i.putExtra("dob",dob.getText().toString());
                i.putExtra("address",address.getText().toString());

            startActivity(i);
            }

    }


}
//




