package com.msg91.sendotp.sample;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Appoiment extends AppCompatActivity {
    SharedPreferences sh,logout;
    Button ap;
    EditText date;
Intent ii;
    private DatePicker datePicker;

    private TextView dateView;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar calendar;
    TextView dname,dph,did,dob,daddress,dgender,demail,dob2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoiment);
        dname=findViewById(R.id.rdname11);
        did=findViewById(R.id.docyou3);
        dph=findViewById(R.id.dw);
        date=findViewById(R.id.adate);
        demail=findViewById(R.id.demail);
        dob=findViewById(R.id.dob);
        dob2=findViewById(R.id.dob2);
         ap=findViewById(R.id.ap);
        daddress=findViewById(R.id.daddress);
        dgender=findViewById(R.id.dgender);
        ii=getIntent();

        sh=getSharedPreferences("data",MODE_PRIVATE);
        String Item=sh.getString("phone",null);


        String nn=  sh.getString("name",null);
        String ee=    sh.getString("email",null);
        String add=     sh.getString("address",null);
        String ex=    sh.getString("gender",null);

        dob.setText(ii.getStringExtra("name"),null);
        dname.setText(ii.getStringExtra("dep"),null);
        dob2.setText(ii.getStringExtra("hos"),null);
        dph.setText(Item);
        did.setText(nn);
        demail.setText(ee);
        daddress.setText(add);
        dgender.setText(ex);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(Appoiment.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        ap.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/appiiment_confirm.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
                        Toast.makeText(Appoiment.this,response,Toast.LENGTH_LONG).show();
                        if(response.contains("Registration Successful"))
                        {

                            new SweetAlertDialog(Appoiment.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("YOUR APPLICATION SUDMITED")
                                    .setContentText("Back to Dashboard!")
                                    .setConfirmText("Yes")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog
                                                    .setTitleText("Thank You...!")

                                                    .setConfirmText("OK")

                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                            Intent in=new Intent(Appoiment.this, MainActivity2.class);
                                                            startActivity(in);
                                                        }
                                                    })
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        }
                                    })
                                    .show();




//
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                params.put("name",did.getText().toString());
                params.put("phone",dph.getText().toString());
                params.put("email",demail.getText().toString());
                params.put("add",daddress.getText().toString());
                params.put("g",dgender.getText().toString());
                params.put("dname",dob.getText().toString());
                params.put("dep",dname.getText().toString());
                params.put("hos",dob2.getText().toString());
                params.put("date",date.getText().toString());
//returning parameter
                return params;
            }

        };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(Appoiment.this);
        requestQueue.add(stringRequest);



    }
});



    }
}
