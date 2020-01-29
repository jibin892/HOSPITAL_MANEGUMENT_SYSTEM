
package com.msg91.sendotp.sample;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.msg91.sendotp.sample.ui.gallery.NurseFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Request extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private ArrayList<Doctor> goodModelArrayList;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> nam = new ArrayList<String>();

    TextView  pname2, pname3,rid;
    ImageView pname4;
    Intent i;
    private Bitmap bitmap;
    private Uri filePath;
    Button bt, cart;
    SharedPreferences sh;
Spinner  deparetment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        deparetment = findViewById(R.id.pname);
        bt = findViewById(R.id.h_btn11);
     //   cart = findViewById(R.id.cart);
        pname2 = findViewById(R.id.pname2);
        pname3 = findViewById(R.id.pname3);
        pname4 = findViewById(R.id.pname4);
       // rid= findViewById(R.id.rid11);


        i = getIntent();
       // Toast.makeText(getApplicationContext(),i.getStringExtra("image" ), Toast.LENGTH_LONG).show();
       // pname.setText(i.getStringExtra("a"));
        pname2.setText(i.getStringExtra("name1"));
        pname3.setText(i.getStringExtra("name2"));

        Picasso.get().load(i.getStringExtra("image")).into(pname4);



//        i = getIntent();
//        pname.setText(i.getStringExtra("name"));
//        pname2.setText(i.getStringExtra("name1"));
//        pname3.setText(i.getStringExtra("name2"));
//        Picasso.get().load(i.getStringExtra("image")).into(pname4);
//        rid.setText(i.getStringExtra("a"));
        sh = Objects.requireNonNull(getApplicationContext()).getSharedPreferences("data", MODE_PRIVATE);


        {

            StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/Appoiment_spiner.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//If we are getting success from server
                         //   Toast.makeText(Request.this,response, Toast.LENGTH_LONG).show();
                            if(response.equals("[]"))
                            {

                                new SweetAlertDialog(Request.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("No Depatment Availabel")
                                        .setContentText("back to Dashboard!")
                                        .setConfirmText("Yes,Login")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog
                                                        .setTitleText("Logining...!")

                                                        .setConfirmText("OK")

                                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                            @Override
                                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                Intent in=new Intent(Request.this, MainActivity2.class);
                                                                startActivity(in);
                                                            }
                                                        })
                                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            }
                                        })
                                        .show();




//
                            }

                            goodModelArrayList = new ArrayList<>();


                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");

                                    Doctor doctor = new Doctor();
                                    doctor.setName(json_obj.getString("dep"));

                                    goodModelArrayList.add(doctor);
                                }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            for (int i = 0; i < goodModelArrayList.size(); i++) {
                                nam.add(goodModelArrayList.get(i).getName().toString());

                            }

                            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, nam);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            deparetment.setAdapter(spinnerArrayAdapter);

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
                    params.put("a", i.getStringExtra("name1"));
                    params.put("b", i.getStringExtra("name2"));

// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                    return params;
                }

            };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);




        }


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(getApplicationContext(), Doctersview.class);
                j.putExtra("hname", pname2.getText().toString());
                j.putExtra("hplace", pname3.getText().toString());
                j.putExtra("dep", deparetment.getSelectedItem().toString());
                startActivity(j);
            }


        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {





//Toast.makeText(Therapy.this,doctor.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

        // Toast.makeText(Therapy.this,doctor.getItemAtPosition(i).toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}




































