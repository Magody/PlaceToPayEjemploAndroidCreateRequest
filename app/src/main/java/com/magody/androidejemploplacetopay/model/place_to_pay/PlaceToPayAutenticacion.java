package com.magody.androidejemploplacetopay.model.place_to_pay;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PlaceToPayAutenticacion {

    private String login;
    private String seed;
    private String nonce;
    private String tranKey;

    public PlaceToPayAutenticacion() {

        //this.nonce = "WmEyvut9GgvcMWrV";
        //this.seed = "2019-11-29T11:59:20-05:00";

        this.nonce = new BigInteger(130, new SecureRandom()).toString();

        this.seed = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ", Locale.getDefault())).format(new Date());

        login = ParametrosPlaceToPay.LOGIN;
        tranKey = getTranKey();

        this.nonce = getNonce();
        /*
        this.seed = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ", Locale.getDefault())).format(new Date());*/
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public void setTranKey(String tranKey) {
        this.tranKey = tranKey;
    }

    static byte[] sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        return mDigest.digest(input.getBytes());
    }

    public String getTranKey() {
        try {

            String union = nonce + seed + ParametrosPlaceToPay.SECRET_KEY;
            byte[] SHA1 = sha1(union);

            //Log.d("Autenticacion", union+"\n"+SHA1);

            return base64(SHA1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getSeed() {
        return this.seed;
    }

    public String getNonceClean() {
        return nonce;
    }


    public String getNonce() {
        return base64(nonce.getBytes());
    }


    static String base64(byte[] input) {
        return new String(Base64.encode(input, Base64.NO_WRAP));
    }


    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append(halfbyte <= 9 ? (char) ('0' + halfbyte) : (char) ('a' + halfbyte - 10));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] textBytes = text.getBytes(StandardCharsets.ISO_8859_1);
        md.update(textBytes, 0, textBytes.length);
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    @Override
    public String toString() {
        return "PlaceToPayAutenticacion{" +
                "login='" + login + '\'' +
                ", seed='" + seed + '\'' +
                ", nonce='" + nonce + '\'' +
                ", tranKey='" + tranKey + '\'' +
                '}';
    }
}
