package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

import java.util.Arrays;

class SubscriptionRequest {

    private String reference;
    private String description;
    private NameValuePair[] fields;

    public SubscriptionRequest(String reference, String description, NameValuePair[] fields) {
        this.reference = reference;
        this.description = description;
        this.fields = fields;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NameValuePair[] getFields() {
        return fields;
    }

    public void setFields(NameValuePair[] fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "SubscriptionRequest{" +
                "reference='" + reference + '\'' +
                ", description='" + description + '\'' +
                ", fields=" + Arrays.toString(fields) +
                '}';
    }
}
