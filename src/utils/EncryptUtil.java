package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class EncryptUtil {
    public static String getPasswordEncrypt(String plain_p, String pepper) {
        String ret = "";

        if(plain_p != null && !plain_p.equals("")) {
            byte[] bytes;
            String password = plain_p + pepper;
            try {
                bytes = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
                ret = DatatypeConverter.printHexBinary(bytes);
            } catch(NoSuchAlgorithmException ex) {}
        }

        return ret;
    }

    public static String getWordEncrypt(String plain) {
        String ret = "";
        String salt = "6Ab3mtmH"; // salt例
        if(plain != null && !plain.equals("")) {
            byte[] bytes;
            String password = plain + salt;
            try {
                bytes = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
                ret = DatatypeConverter.printHexBinary(bytes);
            } catch(NoSuchAlgorithmException ex) {}
        }
        return ret;
    }
}