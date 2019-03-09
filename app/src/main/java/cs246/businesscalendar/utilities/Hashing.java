package cs246.businesscalendar.utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Hashing {
    public static String calculateHash(String input) throws Exception {
        // Get an instance of the SHA-256 algorithm
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Create Byte Array
        byte[] byteArray = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        // Create a new hex string
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            String hex = Integer.toHexString(0xff & byteArray[i]);
            if (hex.length() == 1) { hexString.append('0'); }
            hexString.append(hex);
        }

        return hexString.toString();
    }}
