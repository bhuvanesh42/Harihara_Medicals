package com.example.harihara_medicals;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicienAdapter extends RecyclerView.Adapter<MedicienAdapter.ViewHolder> {
    private LayoutInflater inflater;
    public ArrayList<Medicien_list> medicienlistArrayList;

    public MedicienAdapter( Context mctx,ArrayList<Medicien_list> medicienlistArrayList){
        inflater=LayoutInflater.from(mctx);
        this.medicienlistArrayList=medicienlistArrayList;
    }



    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.medicine_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
       // Medicien_list medicienlist = medicien.get(position);
       // Glide.with(mctx).load(medicienlist.getMedicien_img()).into(holder.medicien_img);
        //holder.medicien_addcards.setText(medicienlistArrayList.get(position).getMedicien_addto_cart());
        holder.medicien_prices.setText("Rs:"+medicienlistArrayList.get(position).getMedicien_price());
        holder.medicien_names.setText(medicienlistArrayList.get(position).getMedicien_name());
        //holder.medicien_count.setText(medicienlistArrayList.get(position).getMedicien_count());
        final  int[] present_count={1};
        final  int[] count_item={0};
         holder.medicien_counts.setText(String.valueOf(present_count[0]));
        holder.medicien_adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String presentValStr=holder.medicien_counts.getText().toString();
                    present_count[0]=Integer.parseInt(presentValStr);
                    present_count[0]++;
                    holder.medicien_counts.setText(String.valueOf(present_count[0]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.medicien_subs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String presentValStr=holder.medicien_counts.getText().toString();
                    present_count[0]=Integer.parseInt(presentValStr);
                    if (presentValStr.equalsIgnoreCase(String.valueOf(Integer.parseInt("1")))){
                        Toast.makeText(v.getContext(), "can't less the 1", Toast.LENGTH_SHORT).show();
                    }else {
                        present_count[0]--;
                    }
                    holder.medicien_counts.setText(String.valueOf(present_count[0]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.medicien_addcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String tablet_name=holder.medicien_names.getText().toString();
                String tablet_price=holder.medicien_prices.getText().toString();
                String tablet_count=holder.medicien_counts.getText().toString();*/
                /*Intent intent=new Intent(v.getContext(),Cartpage.class);
                intent.putExtra("tablet_name",tablet_name);
                intent.putExtra("tablet_price",tablet_price);
                intent.putExtra("tablet_count",tablet_count);
                v.getContext().startActivity(intent);*/
                Log.e("cart","yes");


                count_item[0]++;
                holder.cart_count.setText(String.valueOf(count_item[0]));
                holder.cart_count.setVisibility(View.VISIBLE);
                sendpost(holder.medicien_names.getText().toString(),holder.medicien_counts.getText().toString(),holder.medicien_prices.getText().toString());
            }
        });



    }

    private void sendpost(String pname, String pcount, String price) {
        //Log.e("data_inserted","cart added");

        Call<String> call=ApiUtils.getProductapi().getCart(pname,pcount,price);
       // Log.d("data_failed","nope");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("data_inserted","cart added");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("failed",t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicienlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView medicien_names,medicien_prices,medicien_counts,medicien_adds,medicien_subs,cart_count;
        Button medicien_addcards;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicien_names=(TextView) itemView.findViewById(R.id.medicine_name);
            medicien_adds=itemView.findViewById(R.id.medicine_add);
            medicien_subs=itemView.findViewById(R.id.medicine_sub);
            medicien_addcards=itemView.findViewById(R.id.medicine_add_cart);
            medicien_counts=(EditText) itemView.findViewById(R.id.medicine_count);
            medicien_prices=(TextView) itemView.findViewById(R.id.medicine_price);
            cart_count=itemView.findViewById(R.id.textOne);




        }
    }
}
