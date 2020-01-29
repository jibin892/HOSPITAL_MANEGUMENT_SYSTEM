package com.msg91.sendotp.sample;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class regtwonurse extends AppCompatActivity implements
    AdapterView.OnItemSelectedListener {
        String[] genderr = { "Male", "Female","Other"};
    String[] danc = { "Alappuzha", "Ernakulam", "Idukki", "Kannur", "Kasaragod","Kollam","Kottayam","Kozhikode","Malappuram","Palakkad","Pathanamthitta","Thiruvananthapuram","Thrissur","Wayanad"};
    private ArrayList<Doctor> goodModelArrayList;
    TextView dname, dphno, dw;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> nam = new ArrayList<String>();
SharedPreferences sp;
    ImageView  imgview;
    TextView nametxt, emailtxt, dobtxt,addresstxt, login_title;
    TextView logo;
    EditText pass,cpass;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private Uri filePath;
    LinearLayout already_have_account_layout;
    CardView register_card;
    Intent i;
    CheckBox pchek,cpchek;
    Spinner gender1,district1,hospital1,department1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regtwonurse);
        i=getIntent();
        pchek=findViewById(R.id.checkBox5);
        cpchek=findViewById(R.id.checkBox6);
        pass= findViewById(R.id.pass11);
        cpass=findViewById(R.id.cpass11);
        nametxt = findViewById(R.id.nametxt);
        emailtxt = findViewById(R.id.emailtxt);
        gender1=findViewById(R.id.gender11);
        district1=findViewById(R.id.dis11);
        department1=findViewById(R.id.department11);
        hospital1=findViewById(R.id.hos11);
        sp=getSharedPreferences("reg",MODE_PRIVATE);
        dobtxt = findViewById(R.id.dobtxt);
        imgview = findViewById(R.id.profile11);
        addresstxt= findViewById(R.id.addresstxt);
        gender1.setOnItemSelectedListener(this);
        already_have_account_layout = findViewById(R.id.already_have_account_text);
        register_card = findViewById(R.id.register_card);
        //Creating the ArrayAdapter instance having the country list



        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aas = new ArrayAdapter(this,android.R.layout.simple_spinner_item,danc);
        aas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
       district1.setAdapter(aas);



////        Toast.makeText(regtwo.this,projectname.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
//        ArrayAdapter ap = new ArrayAdapter(this,android.R.layout.simple_spinner_item,neww);
//        ap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        newstudent.setAdapter(ap);



        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,genderr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        gender1.setAdapter(aa);
//        Toast.makeText(regtwo.this,projectname.getSelectedItem().toString(),Toast.LENGTH_LONG).show();


        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }


//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,IMAGE_PICK_CODE);

        });



        {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/docters_spiner.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
//                        Toast.makeText(Therapy.this, response, Toast.LENGTH_LONG).show();

                        goodModelArrayList = new ArrayList<>();


                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");

                                Doctor doctor = new Doctor();
                                doctor.setName(json_obj.getString("name"));
                              //  doctor.setType(json_obj.getString("type"));
                                goodModelArrayList.add(doctor);
                            }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < goodModelArrayList.size(); i++) {
                            names.add(goodModelArrayList.get(i).getName().toString());
                            //name.add(goodModelArrayList.get(i).getType());
                        }

                        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(regtwonurse.this, R.layout.support_simple_spinner_dropdown_item, names);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        hospital1.setAdapter(spinnerArrayAdapter);
//                        ArrayAdapter spinnerArrayAdapte = new ArrayAdapter(regtwonurse.this, R.layout.support_simple_spinner_dropdown_item, name);
//                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//                     department1.setAdapter(spinnerArrayAdapte);
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


// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                return params;
            }

        };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(regtwonurse.this);
        requestQueue.add(stringRequest);




    }
        {

            StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/docter_spiner_two.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//If we are getting success from server
                            //   Toast.makeText(Request.this,response, Toast.LENGTH_LONG).show();

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

                            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(regtwonurse.this, R.layout.support_simple_spinner_dropdown_item, nam);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                          department1.setAdapter(spinnerArrayAdapter);

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
//                    params.put("a", i.getStringExtra("name1"));
//                    params.put("b", i.getStringExtra("name2"));

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

        pchek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pchek.setText("Hide");
                }
                else
                {

                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pchek.setText("Show");
                }
            }
        });

        cpchek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    cpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    cpchek.setText("Hide");
                }
                else
                {

                   cpass .setTransformationMethod(PasswordTransformationMethod.getInstance());
                    cpchek.setText("Show");
                }
            }
        });


    }


    public void registerButtonf(View view) { {


        if (pass.getText().toString().isEmpty()){

            pass.setError("Empty Field");
        }
        else if (cpass.getText().toString().isEmpty()){
            cpass.setError("Empty Field");
        }
        else if (pass.getText().toString().length()<=6){

            pass.setError("Password Must Contain 6 Digits");
        }

        else if (cpass.getText().toString().length()<=6){

            cpass.setError("Password Must Contain 6 Digits");
        }

        else if (!(pass.getText().toString().equals(cpass.getText().toString()))) {

            Toast.makeText(regtwonurse.this,"Password not match",Toast.LENGTH_LONG).show();

        }

     else

        {



            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/docter_registration.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//If we are getting success from server
                            Toast.makeText(regtwonurse.this,response,Toast.LENGTH_LONG).show();
                            if(response.equals("Registration Successful"))
                            {

                                new SweetAlertDialog(regtwonurse.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Registration Success")
                                        .setContentText("Login to Dashboard!")
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
                                                                Intent in=new Intent(regtwonurse.this, Signin.class);
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
                    params.put("email",i.getStringExtra("email"));
                    params.put("phone",sp.getString("ph",null));
                    params.put("dob",i.getStringExtra("dob"));
                    params.put("address",i.getStringExtra("address"));
                    params.put("gender",gender1.getSelectedItem().toString());
                    params.put("dis",district1.getSelectedItem().toString());
                    params.put("hos",hospital1.getSelectedItem().toString());
                    params.put("dep",department1.getSelectedItem().toString());
                    params.put("pass", cpass.getText().toString());


// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                    return params;
                }

            };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(regtwonurse.this);
            requestQueue.add(stringRequest);

        }



    }

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(),dance[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(regtwonurse.this.getContentResolver(), filePath);
                imgview.setImageBitmap(bitmap);
                getStringImage(bitmap);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

}


