package com.magody.androidejemploplacetopay.model.place_to_pay;

public class ParametrosPlaceToPay {

    public static String LOGIN = null;
    public static String SECRET_KEY = null; // CREDENCIAL SECRETA


    public static final String BASE_URL = "https://test.placetopay.ec/";
    public static final String END_POINT = "redirection/api/session";

    public static final String ISO639_1_ESPANIOL = "es";
    public static final String ISO3166_1_ECUADOR = "EC";




    ///Tipos de documento
    //Internacional
    public static final String DOCUMENTO_INTERNACIONAL_PASAPORTE = "PPN"; // pasaporte
    public static final String DOCUMENTO_INTERNACIONAL_TAX = "TAX"; // TAX
    public static final String DOCUMENTO_INTERNACIONAL_LIC = "LIC"; // Labeler Identification Code

    //Colombia
    public static final String DOCUMENTO_COLOMBIA_CC = "CC"; // cédula de ciudadanía
    public static final String DOCUMENTO_COLOMBIA_CE = "CE"; // cédula extranjera
    public static final String DOCUMENTO_COLOMBIA_TI = "TI"; // tarjeta de identidad
    public static final String DOCUMENTO_COLOMBIA_RC = "RC"; // registro civil
    public static final String DOCUMENTO_COLOMBIA_NIT = "NIT"; // Número de Identificación Tributaria
    public static final String DOCUMENTO_COLOMBIA_RUT = "RUT"; // Registro único tributario

    //Ecuador: solo se encuentran habilitados CI, RUC, PPN
    public static final String DOCUMENTO_ECUADOR_CI = "CI"; // Cédula de Identidad
    public static final String DOCUMENTO_ECUADOR_RUC = "RUC"; // Registro Único de Contribuyentes

    //Panamá
    public static final String DOCUMENTO_PANAMA_CIP = "CIP"; // Cédula de identidad personal

    //Brazil
    public static final String DOCUMENTO_BRAZIL_CPF = "CPF"; // Cadastro de Pessoas Físicas

    //USA
    public static final String DOCUMENTO_USA_SSN = "SSN"; // Social security number



    ///Tipo de moneda ISO 4217
    public static final String MONEDA_ISO4217_DOLAR_ESTADOS_UNIDOS = "USD";  //United States dollar
    public static final String MONEDA_ISO4217_PESO_COLOMBIANO = "COP";  //Colombian Peso




}
