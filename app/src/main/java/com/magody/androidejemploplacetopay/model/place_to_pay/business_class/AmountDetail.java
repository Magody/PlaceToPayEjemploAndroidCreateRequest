package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

class AmountDetail {

    private String kind;
    private float amount; //AmountType: representación decimal del valor

    public AmountDetail(String kind, float amount) {
        this.kind = kind;
        this.amount = amount;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AmountDetail{" +
                "kind='" + kind + '\'' +
                ", amount=" + amount +
                '}';
    }
}
