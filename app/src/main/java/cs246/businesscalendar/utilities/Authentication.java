package cs246.businesscalendar.utilities;

import java.security.MessageDigest;

public class Authentication {
    public static String calculateHash(String input) throws Exception {
        // Concatenate the Date and Description
        String combinedString = input;

        // Get an instance of the SHA-256 algorithm
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Create Byte Array
        byte[] byteArray = digest.digest(combinedString.getBytes("UTF-8"));

        // Create a new hex string
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            String hex = Integer.toHexString(0xff & byteArray[i]);
            if (hex.length() == 1) { hexString.append('0'); }
            hexString.append(hex);
        }

        return hexString.toString();
    }}
