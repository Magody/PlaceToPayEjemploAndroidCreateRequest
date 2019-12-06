package com.magody.androidejemploplacetopay.model.conexion;

import com.magody.androidejemploplacetopay.model.place_to_pay.ParametrosPlaceToPay;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.RedirectRequest;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.RedirectResponse;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.Status;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiPlaceToPayInterface {

    @Headers({"Accept: application/json"})
    @POST(ParametrosPlaceToPay.END_POINT)
    Call<RedirectResponse> createRequestP2P(@Body RedirectRequest payload);

}
