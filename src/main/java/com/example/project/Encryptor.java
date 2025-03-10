package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        if (messageLen == 0) {
            return 1;
        }
        if (messageLen % rows == 0) {
            return messageLen/rows;
        }
        return (messageLen/rows) + 1;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        int messageSub = 0;
        String[][] encryption = new String[rows][determineColumns(message.length(), rows)];
        for (int i = 0; i < encryption.length; i++) {
            for (int j = 0; j < encryption[0].length; j++) {
                if (messageSub >= message.length()) {
                    encryption[i][j] = "=";
                } else {
                encryption[i][j] = message.substring(messageSub, messageSub + 1);
                messageSub++;
                }
            }
        }
        return encryption;
    }

    public static String encryptMessage(String message, int rows) {
        String[][] encryption = generateEncryptArray(message, rows);
        String encryptionString = "";
        for (int i = encryption[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < encryption.length; j++) {
                encryptionString += encryption[j][i];
            }
        }
        return encryptionString;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String decryption = "";
        int encryptedSub = 0;
        String[][] encryptedDecryp = new String[rows][determineColumns(encryptedMessage.length(), rows)];
        if (rows * determineColumns(encryptedMessage.length(), rows) < encryptedMessage.length()) {
            return "";
        }
        for (int i = determineColumns(encryptedMessage.length(), rows) - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                if (encryptedSub < encryptedMessage.length()) {
                    encryptedDecryp[j][i] = encryptedMessage.substring(encryptedSub, encryptedSub + 1);
                    encryptedSub++;
                }
            }
        }

        for (int i = 0; i < encryptedDecryp.length; i++) {
            for (int j = 0; j < encryptedDecryp[0].length; j++) {
                if (encryptedDecryp[i][j] != null && !encryptedDecryp[i][j].equals("=")) {
                    decryption += encryptedDecryp[i][j];
                }
            }
        }
        return decryption;
    }
}