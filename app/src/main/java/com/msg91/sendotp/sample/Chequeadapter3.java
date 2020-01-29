package com.msg91.sendotp.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter3 extends RecyclerView.Adapter<Chequeadapter3.ProductViewHolder> {
    Intent aa;


    private Context mCtx;
    private List<Cheque3> productList;

    public Chequeadapter3(Context mCtx, List<Cheque3> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c3, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Cheque3 cheque;
        cheque = productList.get(position);


        holder.name.setText(cheque.getUser());
        holder.phone.setText(cheque.getStatus());
        holder.email.setText(cheque.getPrize1());
        holder.address.setText(cheque.getPrize2());
        holder.gender.setText(cheque.getPrize3());
        holder.doctername.setText(cheque.getPrize4());
        holder.dep.setText(cheque.getImage());
        holder.hos.setText(cheque.getPrize());
        holder.date.setText(cheque.getPrize5());
        sh = mCtx.getSharedPreferences("data", MODE_PRIVATE);
        holder.appoiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent aa=new Intent(mCtx, Appoiment_conform.class);
                aa.putExtra("ph", cheque.getStatus());
                aa.putExtra("name", cheque.getPrize2());
                aa.putExtra("dname", cheque.getPrize4());
                aa.putExtra("dep", cheque.getImage());
                aa.putExtra("hos", cheque.getPrize3());
                aa.putExtra("date", cheque.getPrize5());
                mCtx.startActivity(aa);


            }
        });
        holder.delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Hospital_Manegument_system/appoiment_reject.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//If we are getting success from server
                                Toast.makeText(mCtx, response, Toast.LENGTH_LONG).show();
                                if(response.equals("reject"))
                                {

                                    new SweetAlertDialog(mCtx, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Appoiment Rejecterd")
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
                                                                    Intent in=new Intent(mCtx,MainActivityhome.class);
                                                                    mCtx.startActivity(in);
                                                                }
                                                            })
                                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                }
                                            })
                                            .show();




//
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

                        params.put("ph", cheque.getStatus());
                        params.put("hos", cheque.getPrize5());
                        params.put("dep", cheque.getPrize4());
                        params.put("dname", cheque.getPrize3());
                        params.put("date", cheque.getPrize2());
                        params.put("note", "Appoiment Rejected");
// Toast.makeText(Pay.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                        return params;
                    }

                };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(stringRequest);



            }
        });
//
//
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }
    SharedPreferences sh;
    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView name,phone,email,address,gender,doctername,dep,hos,date;
        ImageView image;
        Button sms,call,appoiment,delet;


        public ProductViewHolder(View itemView) {
            super(itemView);

             name = itemView.findViewById(R.id.tnamea);
             phone = itemView.findViewById(R.id.pha);
             email= itemView.findViewById(R.id.emaila);
             address= itemView.findViewById(R.id.addressa);
             gender = itemView.findViewById(R.id.gendera);
             doctername = itemView.findViewById(R.id.dnamea);
             dep = itemView.findViewById(R.id.depa);
             hos= itemView.findViewById(R.id.hosa);
           appoiment= itemView.findViewById(R.id.a);
           date= itemView.findViewById(R.id.dateaa);

            delet= itemView.findViewById(R.id.declaine);


        }

//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//    class ProductViewHolder extends RecyclerView.ViewHolder {
//
//

//        public ProductViewHolder(View itemView) {
//            super(itemView);
//
//

////            review=itemView.findViewById(R.id.re);
////            viewreview=itemView.findViewById(R.id.ve);
////          //  pid=itemView.findViewById(R.id.productidd);
//
//        }
//
//    }

    }

}