package com.magody.androidejemploplacetopay.model.conexion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magody.androidejemploplacetopay.model.place_to_pay.ParametrosPlaceToPay;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiPlaceToPayClient {


    private static Retrofit retrofit;

    public static Retrofit getApiClient() {

        if (retrofit == null) {
            Gson gson =  new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(ParametrosPlaceToPay.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
