package com.magody.androidejemploplacetopay.model.place_to_pay;

import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.Address;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.Amount;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.PaymentRequest;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.Person;
import com.magody.androidejemploplacetopay.model.place_to_pay.business_class.RedirectRequest;


public class PlaceToPay {

    public static RedirectRequest createJSONRequest(String document, String documentType, String name,
                                                    String surname, String email, String street, String city,
                                                    String country, String reference, String description,
                                                    String currency, float total, String fecha_expiracion,
                                                    String url_regreso, String userAgent, String ipAddress) {


        //estructura completa creada apartir de la documentación oficial

        return new RedirectRequest(new PlaceToPayAutenticacion(),
                ParametrosPlaceToPay.ISO639_1_ESPANIOL+"_"+ ParametrosPlaceToPay.ISO3166_1_ECUADOR, //locale
                null, // payer
                new Person( //buyer
                        documentType,
                        document,
                        name,
                        surname,
                        null,
                        email,
                        new Address(
                                street,
                                city,
                                null,
                                null,
                                country, null),
                        "0978656566"
                ),
                new PaymentRequest(
                        reference,
                        description,
                        new Amount(
                                currency,
                                total,
                                null,
                                null

                        ),
                        false,
                        null,
                        null,
                        null,
                        null,
                        false
                ),
                null,
                null,
                null,
                fecha_expiracion,//new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(fecha_expiracion),
                url_regreso,
                null,
                ipAddress,
                userAgent,
                false,
                false
        );

    }


}
