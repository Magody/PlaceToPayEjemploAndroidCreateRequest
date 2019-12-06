package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

import java.util.Arrays;

public class PaymentRequest {

    private String reference;// minimo 1 máximo 32
    private String descripcion;

    private Amount amount;
    private boolean allowPartial;
    private Person shipping;
    private Items items;
    private NameValuePair[] fields;
    private Recurring recurring;
    private boolean subscribe;


    public PaymentRequest(String reference, String descripcion, Amount amount, boolean allowPartial, Person shipping, Items items, NameValuePair[] fields, Recurring recurring, boolean subscribe) {
        this.reference = reference;
        this.descripcion = descripcion;
        this.amount = amount;
        this.allowPartial = allowPartial;
        this.shipping = shipping;
        this.items = items;
        this.fields = fields;
        this.recurring = recurring;
        this.subscribe = subscribe;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public boolean isAllowPartial() {
        return allowPartial;
    }

    public void setAllowPartial(boolean allowPartial) {
        this.allowPartial = allowPartial;
    }

    public Person getShipping() {
        return shipping;
    }

    public void setShipping(Person shipping) {
        this.shipping = shipping;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public NameValuePair[] getFields() {
        return fields;
    }

    public void setFields(NameValuePair[] fields) {
        this.fields = fields;
    }

    public Recurring getRecurring() {
        return recurring;
    }

    public void setRecurring(Recurring recurring) {
        this.recurring = recurring;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "reference='" + reference + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", amount=" + amount +
                ", allowPartial=" + allowPartial +
                ", shipping=" + shipping +
                ", items=" + items +
                ", fields=" + Arrays.toString(fields) +
                ", recurring=" + recurring +
                ", subscribe=" + subscribe +
                '}';
    }
}
