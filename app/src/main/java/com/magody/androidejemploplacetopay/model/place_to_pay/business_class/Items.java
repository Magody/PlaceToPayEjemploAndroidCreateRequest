package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;


import java.util.Arrays;

class Items {

    private Item[] item;

    public Items(Item[] item) {
        this.item = item;
    }

    public Item[] getItem() {
        return item;
    }

    public void setItem(Item[] item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Items{" +
                "item=" + Arrays.toString(item) +
                '}';
    }
}
