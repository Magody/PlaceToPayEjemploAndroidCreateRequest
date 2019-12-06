package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

public class Item {

    private String sku;
    private String name;
    private String category;
    private String qty;
    private float prive;
    private float tax;

    public Item(String sku, String name, String category, String qty, float prive, float tax) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.qty = qty;
        this.prive = prive;
        this.tax = tax;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public float getPrive() {
        return prive;
    }

    public void setPrive(float prive) {
        this.prive = prive;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Item{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", qty='" + qty + '\'' +
                ", prive=" + prive +
                ", tax=" + tax +
                '}';
    }
}
