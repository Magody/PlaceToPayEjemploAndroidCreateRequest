package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Status {

    public String status; //Estado proporcionado, podría ser uno de esos: [OK, FAILED,    APPROVED, APPROVED_PARTIAL, PARTIAL_EXPIRED,
                          //REJECTED, PENDING, PENDING_VALIDATION, REFUNDED,  MANUAL]
    public String reason; //Código de motivo proporcionado


    public String message; // Descripción del código de razón

    public String dateTime; // Fecha y hora de este estado


    public String toRawString(){

        return status + "#SEP_ATR_SPC" +
                reason + "#SEP_ATR_SPC" +
                message + "#SEP_ATR_SPC" +
                dateTime;

    }

    public static Status reconstruirDesdeRaw(String raw){
        String[] atributos = raw.split("#SEP_ATR_SPC");
        return new Status(atributos[0], atributos[1], atributos[2], atributos[3]);

    }



    public Status(String status, String reason, String message, String dateTime) {
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", message='" + message + '\'' +
                ", date=" + dateTime +
                '}';
    }
}
