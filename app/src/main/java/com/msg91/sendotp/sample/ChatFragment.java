package com.msg91.sendotp.sample;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;


public class ChatFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner hospitalname, district,department;
    Button th_btn;
    private ArrayList<Doctor> goodModelArrayList;
    TextView dname, dphno, dw;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> nam = new ArrayList<String>();
    String a, b, c, d, e, f, g;
    SharedPreferences sh, logout;
    Button upload,dd;
    EditText doctername;

    final int RequestPermissionCode = 1;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        hospitalname = root.findViewById(R.id.hname1);
        district = root.findViewById(R.id.dis1);
        department = root.findViewById(R.id.department);
        doctername = root.findViewById(R.id.doctername);
        upload = root.findViewById(R.id.upbt111);
        dd = root.findViewById(R.id.d1);
        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/value_in_spiner.php",
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
                                    doctor.setType(json_obj.getString("type"));
                                    goodModelArrayList.add(doctor);
                                }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            for (int i = 0; i < goodModelArrayList.size(); i++) {
                                names.add(goodModelArrayList.get(i).getName().toString());
                                name.add(goodModelArrayList.get(i).getType());
                            }

                            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, names);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            hospitalname.setAdapter(spinnerArrayAdapter);
                            ArrayAdapter spinnerArrayAdapte = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, name);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            district.setAdapter(spinnerArrayAdapte);
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
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);




        }

        {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/department_spiner.php",
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

                            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, nam);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            department.setAdapter(spinnerArrayAdapter);
//
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
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);



        }

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (doctername.getText().toString().isEmpty()){
                    doctername.setError("Empty Field");
                }

                else

                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/department_and_doc.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                    Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
                                    if(response.equals("Registration Successful"))
                                    {

                                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("Registration Success")
                                                .setContentText("Login to Dashboard!")
                                                .setConfirmText("Yes,Login")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        sDialog
                                                                .setTitleText("Logining...!")

                                                                .setConfirmText("OK")


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




                            params.put("hname",hospitalname.getSelectedItem().toString().toLowerCase());
                            params.put("dis",district.getSelectedItem().toString().toLowerCase());
                            params.put("dep", department.getSelectedItem().toString().toLowerCase());
                            params.put("dname", doctername.getText().toString());

// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                            return params;
                        }

                    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);
                }





            }
        });

        dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent j = new Intent(getActivity(), ADDdepartment.class);

                startActivity(j);



            }
        });
        return root;
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

