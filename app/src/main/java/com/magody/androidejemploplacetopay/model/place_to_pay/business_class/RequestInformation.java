package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

import android.view.SurfaceControl;

public class RequestInformation {

    public Status status;
    public RedirectRequest request;
    public Transaction[] payment;
    public SubscriptionResponse subscrition;



    @Override
    public String toString() {
        return "RequestInformation{" +
                "status=" + status +
                ", request=" + request +
                '}';
    }
}
