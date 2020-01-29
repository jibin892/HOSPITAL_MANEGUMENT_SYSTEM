package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Resetpassnurse extends AppCompatActivity {
EditText npass,cnpass;
Button reset;
SharedPreferences sp;
    String ph;
    Intent i;
    CheckBox pchek,cpchek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassnurse);
        npass=findViewById(R.id.npass1);
        cnpass=findViewById(R.id.cnpass1);
        pchek=findViewById(R.id.checkBox2);
        cpchek=findViewById(R.id.checkBox3);
        reset=findViewById(R.id.reset_btn1);
      i=getIntent();







        pchek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    npass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pchek.setText("Hide");
                }
                else
                {

                    npass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pchek.setText("Show");
                }
            }
        });



        cpchek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    cnpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    cpchek.setText("Hide");
                }
                else
                {

                    cnpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    cpchek.setText("Show");
                }
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if(npass.getText().toString().isEmpty()){

                    npass.setError("enter a new Password");
                }
                else if(cnpass.getText().toString().isEmpty()){

                    cnpass.setError("Confirm a new Password");
                }

                else if (npass.getText().toString().length()<=6){

                    npass.setError("Password Must Contain 6 Digits");
                }

                else if (cnpass.getText().toString().length()<=6){

                    cnpass.setError("Password Must Contain 6 Digits");
                }
                else if(!(npass.getText().toString().equals(cnpass.getText().toString()))){

                    Toast.makeText(Resetpassnurse.this, "Password Noy Match ", Toast.LENGTH_LONG).show();

                }
                else if ((npass.getText().toString().equals(cnpass.getText().toString()))){


                            new SweetAlertDialog(Resetpassnurse.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Are you sure?")
                                    .setContentText("Update will happen soon!")
                                    .setConfirmText("Update!")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Stock_manegument_system/reset_pass_seller.php",
                                                    new Response.Listener<String>() {
                                                        @Override
                                                        public void onResponse(String response) {
//If we are getting success from server
                                                            Toast.makeText(Resetpassnurse.this, response, Toast.LENGTH_LONG).show();
                            if(response.equals("Successful"))
                               {
                         startActivity(new Intent(Resetpassnurse.this,Signinnurse.class));
                                   }
                                                            try {
                                                                JSONArray jsonArray = new JSONArray(response);
                                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                                    JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");


                                                                }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
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
//Adding parameters to request
                                                    params.put("pass",cnpass.getText().toString());
                                                    params.put("phone",i.getStringExtra("num"));
// params.put("nort",new_st.getSelectedItem().toString().toLowerCase());

//returning parameter
                                                    return params;
                                                }

                                            };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                                            RequestQueue requestQueue = Volley.newRequestQueue(Resetpassnurse.this);
                                            requestQueue.add(stringRequest);
                                        }
                                    })
                                    .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                        }
                                    })
                                    .show();


                        }


                    };




        });

    }
}
