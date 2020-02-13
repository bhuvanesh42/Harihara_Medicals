package com.example.harihara_medicals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Productapi {
    String JSONURL="https://hariharamedicals.000webhostapp.com/json-hariharan/";


      @GET("doctor.php")
        Call<String> getDoctors();
     @GET("product.php")
        Call<String> getMedicen();
     @FormUrlEncoded
    @POST("appointment.php")
    Call<Void> getAppointment(
            @Field("dname") String dname,
            @Field("fee") String fee,
            @Field("experience") String experience,
            @Field("spcl") String spcl,
            @Field("time") String time,
            @Field("date") String date);
    @FormUrlEncoded
    @POST("reminder.php")
    Call<Void> getReminder(
            @Field("desc") String desc,
            @Field("date") String date,
            @Field("title") String title,
            @Field("loc") String loc,
            @Field("time") String time);


}
