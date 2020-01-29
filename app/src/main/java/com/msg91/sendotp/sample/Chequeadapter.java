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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter extends RecyclerView.Adapter<Chequeadapter.ProductViewHolder> {
    Intent i;


    private Context mCtx;
    private List<Cheque> productList;

    public Chequeadapter(Context mCtx, List<Cheque> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Cheque  cheque = productList.get(position);
        holder.la.setText(cheque.getUser3());
        holder.lo.setText(cheque.getUser4());
        holder.ph.setText(cheque.getUser2());
        holder.name.setText(cheque.getImage());
        holder.detalis.setText(cheque.getStatus());
        Picasso.get().load(cheque.getUser1()).into(holder.image);





        holder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+cheque.getUser3()+","+cheque.getUser4()));
                mCtx.startActivity(intent);







            }
        });



        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+cheque.getUser2()));
                mCtx.startActivity(intent);





            }
        });

        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(mCtx,Request.class);
                i.putExtra("image", cheque.getUser1());
                i.putExtra("name1",cheque.getImage());
                i.putExtra("name2",cheque.getStatus());
                i.putExtra("a",cheque.getUser2());
                mCtx.startActivity(i);




            }
        });
        //  SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Creating editor to store values to shared preferences

        // mCtx.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }




    @Override
    public int getItemCount() {
        return productList.size();
    }
    SharedPreferences sh;
    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView name,price,detalis,a,ph,la,lo;
        ImageView image;
        Button buy,del,map;


        public ProductViewHolder(View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.cname);
            ph = itemView.findViewById(R.id.vph11);
            detalis = itemView.findViewById(R.id.cdetails);
            image = itemView.findViewById(R.id.cimage);
            buy = itemView.findViewById(R.id.cbuy);
            map = itemView.findViewById(R.id.map);
            del = itemView.findViewById(R.id.del);
            la= itemView.findViewById(R.id.lan11);
            lo = itemView.findViewById(R.id.lon11);
            sh=mCtx.getSharedPreferences("data",MODE_PRIVATE);

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
    public void filterList(ArrayList<Cheque> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

}