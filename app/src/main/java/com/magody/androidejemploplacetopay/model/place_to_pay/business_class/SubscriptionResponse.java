package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

public class SubscriptionResponse {
    public Status status;
    public String type;
    public NameValuePair[] instrument;
    /*
Acorde con el tipo de suscripción los valore retornados
puede cambiar y serán devueltos en la estructura de
NameValuePair.
token : [token, subtoken, franchise, franchiseName,
lastDigits, validUntil]
account : [bankCode, bankName, accountType,
accountNumber]

     */
}
