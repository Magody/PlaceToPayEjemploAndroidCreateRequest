package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

import com.magody.androidejemploplacetopay.model.place_to_pay.PlaceToPayAutenticacion;

import java.util.Arrays;

public class RedirectRequest {

    private PlaceToPayAutenticacion auth;

    private String locale;
    private Person payer;
    private Person buyer;
    private PaymentRequest payment;
    private SubscriptionRequest subscription;
    private NameValuePair [] fields;
    private String paymentMethod;
    private String expiration; // 2016-09-15T13:49:01-05:00
    private String returnUrl;
    private String cancelUrl;
    private String ipAddress;
    private String userAgent;
    private boolean skipResult;
    private boolean noBuyerFill;


    public RedirectRequest(String locale, Person payer, Person buyer, PaymentRequest payment,
                           SubscriptionRequest subscription, NameValuePair[] fields,
                           String paymentMethod, String expiration, String returnUrl,
                           String cancelUrl, String ipAddress, String userAgent,
                           boolean skipResult, boolean noBuyerFill) {
        this.locale = locale;
        this.payer = payer;
        this.buyer = buyer;
        this.payment = payment;
        this.subscription = subscription;
        this.fields = fields;
        this.paymentMethod = paymentMethod;
        this.expiration = expiration;
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.skipResult = skipResult;
        this.noBuyerFill = noBuyerFill;
    }

    public RedirectRequest(PlaceToPayAutenticacion auth, String locale, Person payer,
                           Person buyer, PaymentRequest payment, SubscriptionRequest subscription,
                           NameValuePair[] fields, String paymentMethod, String expiration, String returnUrl,
                           String cancelUrl, String ipAddress, String userAgent, boolean skipResult, boolean noBuyerFill) {
        this.auth = auth;
        this.locale = locale;
        this.payer = payer;
        this.buyer = buyer;
        this.payment = payment;
        this.subscription = subscription;
        this.fields = fields;
        this.paymentMethod = paymentMethod;
        this.expiration = expiration;
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.skipResult = skipResult;
        this.noBuyerFill = noBuyerFill;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Person getPayer() {
        return payer;
    }

    public void setPayer(Person payer) {
        this.payer = payer;
    }

    public Person getBuyer() {
        return buyer;
    }

    public void setBuyer(Person buyer) {
        this.buyer = buyer;
    }

    public PaymentRequest getPayment() {
        return payment;
    }

    public void setPayment(PaymentRequest payment) {
        this.payment = payment;
    }

    public SubscriptionRequest getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionRequest subscription) {
        this.subscription = subscription;
    }

    public NameValuePair[] getFields() {
        return fields;
    }

    public void setFields(NameValuePair[] fields) {
        this.fields = fields;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public boolean isSkipResult() {
        return skipResult;
    }

    public void setSkipResult(boolean skipResult) {
        this.skipResult = skipResult;
    }

    public boolean isNoBuyerFill() {
        return noBuyerFill;
    }

    public void setNoBuyerFill(boolean noBuyerFill) {
        this.noBuyerFill = noBuyerFill;
    }

    public PlaceToPayAutenticacion getAuth() {
        return auth;
    }

    @Override
    public String toString() {
        return "RedirectRequest{" +
                "auth=" + auth +
                ", locale='" + locale + '\'' +
                ", payer=" + payer +
                ", buyer=" + buyer +
                ", payment=" + payment +
                ", subscription=" + subscription +
                ", fields=" + Arrays.toString(fields) +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", expiration=" + expiration +
                ", returnUrl='" + returnUrl + '\'' +
                ", cancelUrl='" + cancelUrl + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", skipResult=" + skipResult +
                ", noBuyerFill=" + noBuyerFill +
                '}';
    }

    public void setAuth(PlaceToPayAutenticacion auth) {
        this.auth = auth;
    }

}
