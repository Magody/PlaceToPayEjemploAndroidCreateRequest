package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

class NameValuePair {

    private String keyword;
    private String balue;
    private String displayOn;

    public NameValuePair(String keyword, String balue, String displayOn) {
        this.keyword = keyword;
        this.balue = balue;
        this.displayOn = displayOn;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBalue() {
        return balue;
    }

    public void setBalue(String balue) {
        this.balue = balue;
    }

    public String getDisplayOn() {
        return displayOn;
    }

    public void setDisplayOn(String displayOn) {
        this.displayOn = displayOn;
    }

    @Override
    public String toString() {
        return "NameValuePair{" +
                "keyword='" + keyword + '\'' +
                ", balue='" + balue + '\'' +
                ", displayOn='" + displayOn + '\'' +
                '}';
    }
}
