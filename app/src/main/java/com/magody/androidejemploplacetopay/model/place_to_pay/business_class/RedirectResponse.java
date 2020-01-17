package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class RedirectResponse {

    public Status status;  //Estado de esta solicitud
    public String processUrl;  //para redireccionar el cliente para completar el proceso
    public int requestId;  //Referencia única de esta sesión




    public String toRawString(){
        return status.toRawString() + "#SEP_ATR#" +
                processUrl + "#SEP_ATR#" +
                requestId;
    }

    public static RedirectResponse reconstruirDesdeRaw(String raw){

        String[] atributos = raw.split("#SEP_ATR#");

        Status statu = Status.reconstruirDesdeRaw(atributos[0]);
        String processUrl = atributos[1];
        int requestId = Integer.parseInt(atributos[2]);

        return new RedirectResponse(statu, processUrl, requestId);


    }


    public RedirectResponse(Status status, String processUrl, int requestId) {
        this.status = status;
        this.processUrl = processUrl;
        this.requestId = requestId;
    }


    public String getProcessUrl() {
        return processUrl;
    }

    public void setProcessUrl(String processUrl) {
        this.processUrl = processUrl;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "RedirectResponse{" +
                "status=" + status +
                ", processUrl='" + processUrl + '\'' +
                ", requestId=" + requestId +
                '}';
    }
}
