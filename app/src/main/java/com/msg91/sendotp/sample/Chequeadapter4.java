package com.msg91.sendotp.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter4 extends RecyclerView.Adapter<Chequeadapter4.ProductViewHolder> {
    Intent i;


    private Context mCtx;
    private List<Cheque4> productList;

    public Chequeadapter4(Context mCtx, List<Cheque4> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c4, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final   Cheque4 cheque;   cheque = productList.get(position);

        holder.name.setText(cheque.getPrize1());
        holder.dname.setText(cheque.getPrize2());
        holder.dep.setText(cheque.getPrize3());
        holder.hos.setText(cheque.getPrize4());
        holder.time.setText(cheque.getPrize5());

        sh= mCtx.getSharedPreferences("data",MODE_PRIVATE);


    }




    @Override
    public int getItemCount() {
        return productList.size();
    }
    SharedPreferences sh;
    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView name,dname,dep,time,hos;
        ImageView image;
        Button sms,call;


        public ProductViewHolder(View itemView) {
            super(itemView);

           name = itemView.findViewById(R.id.name131);
            dname= itemView.findViewById(R.id.dname131);
            dep= itemView.findViewById(R.id.dep131);
           hos= itemView.findViewById(R.id.hos131);
          time = itemView.findViewById(R.id.time131);


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