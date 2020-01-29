package com.msg91.sendotp.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ADDdepartment extends AppCompatActivity {
 EditText a;
 Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddepartment);
        a=findViewById(R.id.aa2);
        b =findViewById(R.id.aa1);



      b.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/depatment.php",
                      new Response.Listener<String>() {
                          @Override
                          public void onResponse(String response) {
//If we are getting success from server
                           //   Toast.makeText(ADDdepartment.this,response,Toast.LENGTH_LONG).show();
                              if(response.equals("success"))
                              {

                                  new SweetAlertDialog(ADDdepartment.this, SweetAlertDialog.WARNING_TYPE)
                                          .setTitleText("Adding Success")
                                          .setContentText("back to home!")
                                          .setConfirmText("Yes")
                                          .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                              @Override
                                              public void onClick(SweetAlertDialog sDialog) {
                                                  sDialog
                                                          .setTitleText("Logining...!")

                                                          .setConfirmText("OK")

                                                          .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                              @Override
                                                              public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                  Intent in=new Intent(ADDdepartment.this, MainActivityhome.class);
                                                                  startActivity(in);
                                                              }
                                                          })
                                                          .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                              }
                                          })
                                          .show();




//
                              }
                              else if (response.equals("Registration Failed")){




                                  new SweetAlertDialog(ADDdepartment.this, SweetAlertDialog.WARNING_TYPE)
                                          .setTitleText("Already Added")
                                          .setContentText("Back to home")
                                          .setConfirmText("Yes")
                                          .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                              @Override
                                              public void onClick(SweetAlertDialog sDialog) {
                                                  sDialog
                                                          .setTitleText("Logining...!")

                                                          .setConfirmText("OK")

                                                          .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                              @Override
                                                              public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                  Intent in=new Intent(ADDdepartment.this, MainActivityhome.class);
                                                                  startActivity(in);
                                                              }
                                                          })
                                                          .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                              }
                                          })
                                          .show();






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



                      params.put("na", a.getText().toString());


// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                      return params;
                  }

              };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
              RequestQueue requestQueue = Volley.newRequestQueue(ADDdepartment.this);
              requestQueue.add(stringRequest);
          }
      });



    }
}
