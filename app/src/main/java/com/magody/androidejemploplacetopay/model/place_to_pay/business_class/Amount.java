package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

import java.util.Arrays;

public class Amount {

    private String currency; //de parametros
    private float total; //de parametros
    private TaxDetail [] taxes; //de parametros
    private AmountDetail[] details; //de parametros

    public Amount(String currency, float total, TaxDetail[] taxes, AmountDetail[] details) {
        this.currency = currency;
        this.total = total;
        this.taxes = taxes;
        this.details = details;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public TaxDetail[] getTaxes() {
        return taxes;
    }

    public void setTaxes(TaxDetail[] taxes) {
        this.taxes = taxes;
    }

    public AmountDetail[] getDetails() {
        return details;
    }

    public void setDetails(AmountDetail[] details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "currency='" + currency + '\'' +
                ", total=" + total +
                ", taxes=" + Arrays.toString(taxes) +
                ", details=" + Arrays.toString(details) +
                '}';
    }
}
