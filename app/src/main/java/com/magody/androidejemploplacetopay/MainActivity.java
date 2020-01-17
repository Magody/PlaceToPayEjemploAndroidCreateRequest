package com.magody.androidejemploplacetopay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.magody.androidejemploplacetopay.model.NetworkUtils;
import com.magody.androidejemploplacetopay.model.place_to_pay.ParametrosPlaceToPay;
import com.magody.androidejemploplacetopay.model.place_to_pay.PlaceToPay;
import com.magody.androidejemploplacetopay.model.conexion.ApiPlaceToPayClient;
import com.magody.androidejemploplacetopay.model.conexion.ApiPlaceToPayInterface;
import com.magody.androidejemploplacetopay.model.place_to_pay.PlaceToPayAutenticacion;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.RedirectRequest;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.RedirectResponse;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.RequestInformation;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.Status;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private int REQUEST_CODE_REDIRECT_URL = 1;

    private String url_pago;
    private SharedPreferences sharedPreferences;

    private RedirectResponse redirectResponse;

    private TextView textViewPeticion, textViewRespuesta, textViewInformacion;


    //CACHE
    private static final String DIRECTORIO_SEGUIMIENTO_PAGO = "dir_p2p_seg_pago", DIRECTORIO_REDIRECT_RESPONSE = "data_red_response";
    private boolean hay_seguimiento_pago, hilo_ejecutandose;


    public Runnable hiloComprobadorEstado = new Runnable() {
        @Override
        public void run() {

            while(hay_seguimiento_pago){

                try {
                    //cada 10 segundos hace la comprobación

                    if(redirectResponse != null){
                        PlaceToPayAutenticacion auth = new PlaceToPayAutenticacion();
                        getRequestInformation(auth);
                    }

                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    Log.d(TAG, "Error: "+e.toString());
                }
            }

            hilo_ejecutandose = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        ///////////////////////////////////////////
        ParametrosPlaceToPay.LOGIN = "060a56e83051d18b81fb06eae15d526c"; //Agregar el usuario otorgado por PlaceToPay
        ParametrosPlaceToPay.SECRET_KEY = "9r8LA9J4vSoPCR8R"; //Agregar el 'secret key' otorgado por PlaceToPay
        ///////////////////////////////////////////


        url_pago = null;


        Button buttonEnviar = findViewById(R.id.buttonMainEnviar);
        Button buttonIrUrl = findViewById(R.id.buttonMainIrAlUrl);
        textViewPeticion = findViewById(R.id.textViewMainPeticion);
        textViewRespuesta = findViewById(R.id.textViewMainRespuesta);
        textViewInformacion = findViewById(R.id.textViewMainInformacion);

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
                Log.i(TAG,"JSON a enviar: \n" + obtenerPrettyJSON(json));

                textViewPeticion.setText(obtenerPrettyJSON(json));


                //todo: comprobar si no hay ya una instancia de pago, un cliente solo debería tener una a la vez
                createRequestPlaceToPay(requestP2PJSON);

                //createRequestP2PMap(requestP2PJSON);

            }


        });

        buttonIrUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(redirectResponse != null){

                    Intent intent = new Intent(MainActivity.this, RedireccionActivity.class);
                    intent.putExtra("url", redirectResponse.getProcessUrl());

                    startActivityForResult(intent, REQUEST_CODE_REDIRECT_URL);
                }else{
                    mostrarToast("Aun no se ha realizado una solicitud a P2P. No hay url");
                }
            }
        });

        findViewById(R.id.buttonMainGetInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceToPayAutenticacion auth = new PlaceToPayAutenticacion();

                getRequestInformation(auth);

            }
        });


        comprobarSeguimientoPago();


    }

    private void comprobarSeguimientoPago() {
        hay_seguimiento_pago = sharedPreferences.getBoolean(DIRECTORIO_SEGUIMIENTO_PAGO, false);



        if (hay_seguimiento_pago) {



            String raw_redirect_response = sharedPreferences.getString(DIRECTORIO_REDIRECT_RESPONSE, null);

            if(raw_redirect_response != null){
                redirectResponse = RedirectResponse.reconstruirDesdeRaw(raw_redirect_response);
                if(redirectResponse != null){
                    //si no hay hilo lo crea
                    if(!hilo_ejecutandose){
                        //para no crear goteras de memoria
                        new Thread(hiloComprobadorEstado).start();
                        hilo_ejecutandose = true;
                    }
                }
            }

        }

    }


    @Override
    protected void onResume() {
        super.onResume();




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hilo_ejecutandose = false;
        hay_seguimiento_pago = false; //para no tener el hilo infinito
    }

    private void getRequestInformation(PlaceToPayAutenticacion auth){

        if(redirectResponse == null){
            mostrarToast("Aun no se ha hecho una solicitud. No hay pedido que observar");
            Log.e(TAG, "Aun no se ha hecho una solicitud. No hay pedido que observar");
            return;
        }

        final String URL = ParametrosPlaceToPay.URL_BASE_INFORMATION+redirectResponse.getRequestId();

        HashMap<String, Object> map_auth = new HashMap<>();
        map_auth.put("auth", auth);

        Gson gson = new Gson();
        String json = gson.toJson(map_auth);
        //Log.i(TAG,"JSON a enviar para GetInfo: \n" + obtenerPrettyJSON(json));



        ApiPlaceToPayInterface apiInterface = ApiPlaceToPayClient.getApiClient().create(ApiPlaceToPayInterface.class);
        Call<RequestInformation> call = apiInterface.getRequestInformation(URL, map_auth);
        call.enqueue(new Callback<RequestInformation>() {
        	@Override
        	public void onResponse(@NonNull Call<RequestInformation> call, @NonNull Response<RequestInformation> response) {



        		if(response.isSuccessful() && response.body() != null && redirectResponse != null){

                    Log.d(TAG, "URL: "+URL+"\nId:"+redirectResponse.getRequestId()+"\n\n"+"Respuesta: "+response.body());


                    final RequestInformation data = response.body();
                    Gson gson = new Gson();
                    String json = gson.toJson(data);
                    textViewInformacion.setText(obtenerPrettyJSON(json));



                    //posición de control siempre realiza un seguimiento
                    hay_seguimiento_pago = true;
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putBoolean(DIRECTORIO_SEGUIMIENTO_PAGO, true);


                    if(!hilo_ejecutandose){

                        new Thread(hiloComprobadorEstado).start();

                        hilo_ejecutandose = true;
                    }


                    if(data.status.status.equals(ParametrosPlaceToPay.P2P_ESTADO_REJECTRED))
                    {
                        mostrarToast("Rechazado");

                        //actualiza base de datos (la tabla transaccion) como rechazado y deshace todos los pedidos generados

                        hay_seguimiento_pago = false;
                        redirectResponse = null;
                        hilo_ejecutandose = false;


                        editor.putBoolean(DIRECTORIO_SEGUIMIENTO_PAGO, false);
                        editor.putString(DIRECTORIO_REDIRECT_RESPONSE, null);



                    }
                    else if(data.status.status.equals(ParametrosPlaceToPay.P2P_ESTADO_APPROVED_PARTIAL))
                    {
                        mostrarToast("Aprobado parcialmente");

                        //actualiza base de datos (la tabla transaccion) como rechazado y deshace todos los pedidos generados



                    }else if(data.status.status.equals(ParametrosPlaceToPay.P2P_ESTADO_APPROVED))
                    {
                        mostrarToast("Aprobado");

                        //actualiza base de datos (la tabla transaccion) como rechazado y deshace todos los pedidos generados
                        //comienza el pedido y tod0 lo asociado

                        hay_seguimiento_pago = false;
                        redirectResponse = null;
                        hilo_ejecutandose = false;


                        editor.putBoolean(DIRECTORIO_SEGUIMIENTO_PAGO, false);
                        editor.putString(DIRECTORIO_REDIRECT_RESPONSE, null);



                    }else if(data.status.status.equals(ParametrosPlaceToPay.P2P_ESTADO_PENDING)){
                        mostrarToast("Pendiente");

                    }else if(data.status.status.equals(ParametrosPlaceToPay.P2P_ESTADO_PENDING_VALIDATION)){

                        mostrarToast("Pendiente Validación");

                    }else if(data.status.status.equals(ParametrosPlaceToPay.P2P_ESTADO_PARTIAL_EXPIRED)){
                        //actualiza base de datos (la tabla transaccion) como rechazado y deshace todos los pedidos generados
                        mostrarToast("HA EXPIRADO Parciallmente");

                        hay_seguimiento_pago = false;
                        redirectResponse = null;
                        hilo_ejecutandose = false;


                        editor.putBoolean(DIRECTORIO_SEGUIMIENTO_PAGO, false);
                        editor.putString(DIRECTORIO_REDIRECT_RESPONSE, null);


                    }else if(data.status.status.equals(ParametrosPlaceToPay.P2P_ESTADO_REFUNDED)){
                        //actualiza base de datos (la tabla transaccion) como rechazado y deshace todos los pedidos generados
                        mostrarToast("REFUNDED");


                    }else{

                        mostrarToast(data.status.status);
                    }


                    editor.apply();




        		}
        	}

        	@Override
        	public void onFailure(@NonNull Call<RequestInformation> call,@NonNull Throwable t) {
        		mostrarToast(t.toString());
        		Log.e(TAG, t.toString());
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

                        redirectResponse = response.body();

                        Gson gson = new Gson();
                        String json = gson.toJson(redirectResponse);
                        Log.d(TAG, "RESPUESTA COMPLETA:\n\n"+obtenerPrettyJSON(json));

                        textViewRespuesta.setText(obtenerPrettyJSON(json));




                        //String stat = redirectResponse.status.toString();
                        //mostrarToast(stat);
                        //mostrarToast(redirectResponse.getProcessUrl() + "\n" + "Con id: " + redirectResponse.getRequestId());

                        url_pago = redirectResponse.getProcessUrl();


                        Log.d(TAG, "URL: "+url_pago);
                        //startActivity(new Intent( Intent.ACTION_VIEW,  Uri.parse(redirectResponse.getProcessUrl())));


                        if(redirectResponse.status.status.equals(ParametrosPlaceToPay.P2P_ESTADO_OK)){

                            hay_seguimiento_pago = true;

                            SharedPreferences.Editor editor1 = sharedPreferences.edit();

                            editor1.putString(DIRECTORIO_REDIRECT_RESPONSE, redirectResponse.toRawString());

                            editor1.apply();


                            SharedPreferences.Editor editor2 = sharedPreferences.edit();

                            editor2.putBoolean(DIRECTORIO_SEGUIMIENTO_PAGO, true);

                            editor2.apply();

                            if(!hilo_ejecutandose){
                                new Thread(hiloComprobadorEstado).start();
                                hilo_ejecutandose = true;
                            }
                        }else{
                            //error
                            //No se recibió un OK
                            cancelarOperacion();
                        }


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


    }


    public void mostrarToast(String mensaje){

        Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_LONG).show();
    }

    private String obtenerPrettyJSON(String uglyJSONString){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJSONString);
        return gson.toJson(je);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE_REDIRECT_URL){
                //tod0 correcto
                mostrarToast("Compra realizada correctamente");

            }
        }else if(resultCode == RESULT_CANCELED){
            if(requestCode == REQUEST_CODE_REDIRECT_URL){
                mostrarToast("No se ha podido realizar la compra");

            }
        }

    }

    public void cancelarOperacion(){
        mostrarToast("Operación cancelada");
    }

}
