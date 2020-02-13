package com.example.harihara_medicals.ui.productfragment2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.harihara_medicals.Medicine;
import com.example.harihara_medicals.R;
import com.example.harihara_medicals.ui.Calender.CalenderFragment;
import com.example.harihara_medicals.ui.cart.CartFragment;
import com.example.harihara_medicals.ui.medicien.MedicienFragment;

public class ProductFragment2 extends Fragment {

private Button btn_health,btn_cosmetics,btn_medicine,btn_surgical,btn_veternary,btn_ayrvedic;
private EditText product_search;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        final  View view=inflater.inflate(R.layout.product_fragment2_fragment, null);
        btn_medicine=view.findViewById(R.id.product_btn_Medicine);
        btn_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Fragment newFragment = new MedicienFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.nav_host_fragment, newFragment);

                transaction.commit();*/
               startActivity( new Intent(getActivity(),Medicine.class));

            }
        });
       product_search=view.findViewById(R.id.product_search);
        product_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Medicine.class));
           }
        });
        return view;


    }

}
