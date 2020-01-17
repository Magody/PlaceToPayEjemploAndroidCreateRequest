package com.magody.androidejemploplacetopay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class RedireccionActivity extends AppCompatActivity {

    public static final String TAG = "RedirectionActivity";
    
    private WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redireccion);

        webView = findViewById(R.id.webViewPlaceToPay);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript

        Bundle bundle = getIntent().getExtras();

        if(bundle == null){
            cancelarOperacion();

        }else{
            String url = bundle.getString("url", null);
            if(url == null){
                cancelarOperacion();
            }else{
                mostrarToast("Entrando a: "+url);
                Log.d(TAG, "Entrando a: "+url);
                webView.loadUrl(url); //requiere webView actualizado para leer el javascript

            }
        }


    }

    private void cancelarOperacion(){
        mostrarToast("No se envio ninguna url");
        setResult(RESULT_CANCELED);
        onBackPressed();
    }


    public void mostrarToast(String mensaje){

        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

}
