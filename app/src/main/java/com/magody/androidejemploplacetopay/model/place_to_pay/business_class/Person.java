package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

public class Person {

    private String documentType; //parámetros
    private String document;
    private String name;
    private String surname;
    private String company;
    private String email;
    private Address address;
    private String mobile; //maximo 30 caracteres

    public Person(String documentType, String document, String name, String surname, String company, String email, Address address, String mobile) {
        this.documentType = documentType;
        this.document = document;
        this.name = name;
        this.surname = surname;
        this.company = company;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Person{" +
                "documentType='" + documentType + '\'' +
                ", document='" + document + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
