package com.example.harihara_medicals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cartpage extends AppCompatActivity {
    TextView cart_tab_name,cart_tab_price,cart_tab_count,cart_detailed_price,cart_total_price;
    public  RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    public  MedicienAdapter medicienAdapter;
    ArrayList<Medicien_list> medicienlistArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);
        buldrecyclerview();

        cart_tab_name=findViewById(R.id.cart_name);
        cart_tab_price=findViewById(R.id.cart_price);
        cart_tab_count=findViewById(R.id.cart_count);
        cart_detailed_price=findViewById(R.id.cart_detail_price);
        cart_total_price=findViewById(R.id.cart_total_price);
        Intent intent=getIntent();
        String tablet_name=intent.getStringExtra("tablet_name");
        String tablet_price=intent.getStringExtra("tablet_price");
        String tablet_count=intent.getStringExtra("tablet_count");
        cart_tab_name.setText(tablet_name);
        cart_tab_price.setText(tablet_price);
        cart_tab_count.setText("Count:"+tablet_count);
        cart_detailed_price.setText(tablet_price);
        cart_total_price.setText(tablet_price);

    }

    private void buldrecyclerview() {
        RecyclerView recyclerView=findViewById(R.id.cart_recyclerview);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        //medicienAdapter= new MedicienAdapter(medicienlistArrayList);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(medicienAdapter);


    }
}
