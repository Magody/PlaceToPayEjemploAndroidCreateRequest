package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

public class Transaction {

    public Status status;
    public int internalReference;
    public String reference;
    public String paymentMethod;
    public String paymentMethodName;
    public String issueName;
    public AmountConversion amount;
    public String receipt;
    public String franchise;
    public boolean refunded;
    public String authorization;
    public NameValuePair[] processorFields;


}
