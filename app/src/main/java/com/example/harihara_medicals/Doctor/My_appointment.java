package com.example.harihara_medicals.Doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.harihara_medicals.Adapters.My_appoinment_list_adaptor;
import com.example.harihara_medicals.Model.My_appoinment_list;
import com.example.harihara_medicals.R;
import com.example.harihara_medicals.Retrofit.Productapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class My_appointment extends AppCompatActivity {
    private My_appoinment_list_adaptor my_appoinment_list_adaptor;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_appointment);
        recyclerView = findViewById(R.id.my_appointment_recy);
        getResponse();

    }

    private void getResponse() {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(Productapi.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Productapi api =retrofit.create(Productapi.class);

        Call<String> call = api.getAppoinments();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String jsonresponse = response.body().toString();
                        writedata(jsonresponse);


                    } else {
                        Log.i("onemptyresponce", "empty response");
                    }
                }
            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void writedata(String response) {
        try {
            JSONObject obj=new JSONObject(response);
            ArrayList<My_appoinment_list> my_appoinment_listArrayList=new ArrayList<>();
            JSONArray dataarray =obj.getJSONArray("appointment");
            for (int i=0;i<dataarray.length();i++){
                My_appoinment_list my_appoinment_list=new My_appoinment_list();
                JSONObject dataobj= dataarray.getJSONObject(i);
                my_appoinment_list.setDr_name(dataobj.getString("doctor_name"));
                my_appoinment_list.setDr_spl(dataobj.getString("specialist"));
                my_appoinment_list.setDr_fees(dataobj.getString("fees"));
                my_appoinment_list.setDr_ex(dataobj.getString("experience"));
                my_appoinment_list.setDr_time(dataobj.getString("time"));
                my_appoinment_list.setDr_date(dataobj.getString("date"));
                my_appoinment_listArrayList.add(my_appoinment_list);
            }


            my_appoinment_list_adaptor = new My_appoinment_list_adaptor(this,my_appoinment_listArrayList);
            recyclerView.setAdapter(my_appoinment_list_adaptor);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
