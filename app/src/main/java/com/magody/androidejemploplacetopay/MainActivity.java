package com.magody.androidejemploplacetopay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.magody.androidejemploplacetopay.model.NetworkUtils;
import com.magody.androidejemploplacetopay.model.place_to_pay.ParametrosPlaceToPay;
import com.magody.androidejemploplacetopay.model.place_to_pay.PlaceToPay;
import com.magody.androidejemploplacetopay.model.conexion.ApiPlaceToPayClient;
import com.magody.androidejemploplacetopay.model.conexion.ApiPlaceToPayInterface;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.RedirectRequest;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.RedirectResponse;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private WebView webView;

    private String url_pago;

    private RedirectResponse redirectResponse;

    private TextView textViewPeticion, textViewRespuesta;

    private boolean esta_en_web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        esta_en_web_view = false;

        ///////////////////////////////////////////
        ParametrosPlaceToPay.LOGIN = "login"; //Agregar el usuario otorgado por PlaceToPay
        ParametrosPlaceToPay.SECRET_KEY = "secret_key"; //Agregar el 'secret key' otorgado por PlaceToPay
        ///////////////////////////////////////////


        url_pago = null;

        webView = findViewById(R.id.webViewPlaceToPay);
        Button buttonEnviar = findViewById(R.id.buttonMainEnviar);
        Button buttonIrUrl = findViewById(R.id.buttonMainIrAlUrl);
        textViewPeticion = findViewById(R.id.textViewMainPeticion);
        textViewRespuesta = findViewById(R.id.textViewMainRespuesta);

        webView.setWebViewClient(new WebViewClient());
        webView.setVisibility(View.GONE);


        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //al pulsar el botón
                String user_agent=new WebView(MainActivity.this).getSettings().getUserAgentString();
                String ipAdress;

                //todo: tomar la ip pública de una API, lo de abajo solo toma la local
                ipAdress = NetworkUtils.getIPAddress(true);

                //todo: definir un cancelUrl para cuando el usuario aborte la operacion
                RedirectRequest requestP2PJSON = PlaceToPay.createJSONRequest("1720254224",
                        ParametrosPlaceToPay.DOCUMENTO_ECUADOR_CI,"Danny", "Díaz",
                        "meliodasfistery@gmail.com","Arenal y los cipreses", "Quito",
                        ParametrosPlaceToPay.ISO3166_1_ECUADOR,"123456",
                        "Pago de prueba", ParametrosPlaceToPay.MONEDA_ISO4217_DOLAR_ESTADOS_UNIDOS,
                        1, "2040-12-31T13:36:29-05:00",
                        "https://zilverdelivs.com/index.html", user_agent, ipAdress
                );


                Gson gson = new Gson();
                String json = gson.toJson(requestP2PJSON);
                Log.i(TAG,"JSON a enviar: \n" + json);

                textViewPeticion.setText("PETICION:\n" + json);


                createRequestPlaceToPay(requestP2PJSON);

            }
        });

        buttonIrUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(url_pago != null && redirectResponse != null){
                    webView.setVisibility(View.VISIBLE);
                    webView.loadUrl(redirectResponse.getProcessUrl()); //requiere webView actualizado para leer el javascript
                    esta_en_web_view = true;
                }else{
                    mostrarToast("Aun no se ha realizado una solicitud a P2P. No hay url");
                }
            }
        });




    }



    private void createRequestPlaceToPay(RedirectRequest redirectRequest){


        ApiPlaceToPayInterface apiPlaceToPayInterface = ApiPlaceToPayClient.getApiClient().create(ApiPlaceToPayInterface.class);
        Call<RedirectResponse> call = apiPlaceToPayInterface.createRequestP2P(redirectRequest);
        call.enqueue(new Callback<RedirectResponse>() {
            @Override
            public void onResponse(@NonNull Call<RedirectResponse> call, @NonNull Response<RedirectResponse> response) {



                if(response.body() != null)
                {

                    Status status = response.body().status;

                    mostrarToast(status.toString());

                    if(response.isSuccessful()){

                        Log.d(TAG, "RESPUESTA COMPLETA:\n\n"+response.body().toString());
                        textViewRespuesta.setText("RESPUESTA:\n" +response.body().toString());



                        redirectResponse = response.body();
                        //String stat = redirectResponse.status.toString();
                        //mostrarToast(stat);
                        //mostrarToast(redirectResponse.getProcessUrl() + "\n" + "Con id: " + redirectResponse.getRequestId());

                        url_pago = redirectResponse.getProcessUrl();
                        //startActivity(new Intent( Intent.ACTION_VIEW,  Uri.parse(redirectResponse.getProcessUrl())));


                    }
                }else{
                    //todo: seccionar por código el problema
                    textViewRespuesta.setText("La respuesta del servidor fue recibida como nula. Puede deberse a una autenticación fallida o el sitio está inactivo");

                    Log.e(TAG, "La respuesta del servidor fue recibida como nula. Ver la documentación");
                }


            }

            @Override
            public void onFailure(@NonNull Call<RedirectResponse> call,@NonNull Throwable t) {
                mostrarToast(t.toString());
                Log.e(TAG, t.toString());
            }
        });


        /*ApiPlaceToPayInterface apiPlaceToPayInterface = ApiPlaceToPayClient.getApiClient().create(ApiPlaceToPayInterface.class);
        Call call = apiPlaceToPayInterface.createRawRequestP2P(redirectRequest);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {

                if(response.body() != null)
                    Log.i("TAG", "response 33: "+ response.body().toString()); // new Gson().toJson(response.body())



            }

            @Override
            public void onFailure(@NonNull Call call,@NonNull Throwable t) {
                mostrarToast(t.toString());
                Log.e(TAG, t.toString());
            }
        });*/
    }


    @Override
    public void onBackPressed() {
        if (esta_en_web_view) {
            webView.setVisibility(View.GONE);
            esta_en_web_view = false;
        } else {
            super.onBackPressed();
        }
    }


    public void mostrarToast(String mensaje){

        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }




}
