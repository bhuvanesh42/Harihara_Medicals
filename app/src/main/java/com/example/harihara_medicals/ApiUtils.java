package com.example.harihara_medicals;

public class ApiUtils {
    private ApiUtils(){}
    public  static final String BASE_URL="https://grubby.co.za/hariharaMedicals/harihara_medicals/API/";
    public  static Productapi getProductapi(){

        return RetrofitClient.getClient(BASE_URL).create(Productapi.class);

    }
}
