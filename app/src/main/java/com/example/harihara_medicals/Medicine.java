package com.example.harihara_medicals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Medicine extends AppCompatActivity {
    public RecyclerView recyclerView;
    public MedicienAdapter medicienAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        recyclerView = findViewById(R.id.recy);
        fetchJSON();
        Log.d("res","Medicien");





    }

    private void fetchJSON() {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Productapi.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Productapi api=retrofit.create(Productapi.class);
        Call<String> call=api.getMedicen();
        Log.i("call","done");
        call.enqueue(new Callback<String>() {
            @Override

            public void onResponse(Call<String> call, Response<String> response) {

                Log.i("Responsestring", response.body().toString());
                Log.d("over","suc");
                if (response.isSuccessful()){
                    if (response.body()!=null){
                        String jsonresponse=response.body().toString();
                        writeRecycler(jsonresponse);
                    }
                    else {
                        Log.i("onemptyresponce","empty response");
                    }
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void writeRecycler(String response) {
        try {
            JSONObject obj=new JSONObject(response);
            ArrayList<Medicien_list> medicienlistArrayList=new ArrayList<>();
            JSONArray dataarray=obj.getJSONArray("product");
                for(int i=0;i<dataarray.length();i++){
                    Medicien_list medicien=new Medicien_list();
                    JSONObject dataojb=dataarray.getJSONObject(i);
                    medicien.setMedicien_name(dataojb.getString("productname"));
                    medicien.setMedicien_price(dataojb.getString("price"));
                    medicienlistArrayList.add(medicien);
                    Log.d("done",""+response.toString());
                }
                medicienAdapter=new MedicienAdapter(this,medicienlistArrayList);
                recyclerView.setAdapter(medicienAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

