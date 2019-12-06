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


    public static RedirectResponse JSON2RedirectResponse(JSONObject jsonObject){

        try {
            Status status = Status.JSON2Status(jsonObject.getJSONObject("status"));

            return new RedirectResponse(status, jsonObject.getString("processUrl"), jsonObject.getInt("requestId"));
        } catch (JSONException e) {
            Log.e("Clase RedirectResponse", e.toString());
        }
        return null;
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
