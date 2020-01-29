package com.msg91.sendotp.sample;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Appoiment_conform extends AppCompatActivity {
EditText time;
Button t;
Intent i;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoiment_conform);
        time=findViewById(R.id.tt);
        t=findViewById(R.id.timebtn);
i=getIntent();

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(Appoiment_conform.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                time.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();


            }
        });

t.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
if(time.getText().toString().isEmpty()){
    time.setError("ENTER TIME");
}
else {
    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/appoiment_user_view.php",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
//If we are getting success from server
                    Toast.makeText(Appoiment_conform.this, response, Toast.LENGTH_LONG).show();
                    if (response.contains("Registration Successful")) {

                        new SweetAlertDialog(Appoiment_conform.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(" SUDMITED")
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
                                                        Intent in = new Intent(Appoiment_conform.this, MainActivityhome.class);
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


            params.put("name",i.getStringExtra("name"));
            params.put("phone", i.getStringExtra("ph"));
            params.put("dname", i.getStringExtra("dep"));
            params.put("hos", i.getStringExtra("dname"));
            params.put("dep", i.getStringExtra("hos"));
            params.put("time", time.getText().toString());
            params.put("date", i.getStringExtra("date"));

//returning parameter
            return params;
        }

    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
    RequestQueue requestQueue = Volley.newRequestQueue(Appoiment_conform.this);
    requestQueue.add(stringRequest);


}


    }
});


    }
}
