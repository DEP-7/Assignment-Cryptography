package lk.ijse.crypto;

public class DCCrypto {
    public static String encrypt(String plainText, String key) {
        plainText += key;
        String reversedText = "";
        for (int i = plainText.length() - 1; i >= 0; i--) {
            reversedText += plainText.charAt(i);
        }

        String softShuffledText = "";
        for (int i = 0; i < reversedText.length(); i += 2) {
            if (i != reversedText.length() - 1) {
                softShuffledText += reversedText.charAt(i + 1);
            }
            softShuffledText += reversedText.charAt(i);
        }

        int uniqueKeyValue = getUniqueKeyValue(key);
        String cipherText = "";
        for (int i = 0; i < softShuffledText.length(); i++) {
            int code = softShuffledText.charAt(i);
            code += uniqueKeyValue;
            char newChar = (char) code;
            cipherText += newChar;
        }

        return cipherText;
    }

    public static String decrypt(String cipherText, String key) {
        if (cipherText.length() <= key.length()) {
            return cipherText;
        }

        int uniqueKeyValue = getUniqueKeyValue(key);
        String softShuffledText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            int code = cipherText.charAt(i);
            code -= uniqueKeyValue;
            char oldChar = (char) code;
            softShuffledText += oldChar;
        }

        String reversedText = "";
        for (int i = 0; i < softShuffledText.length(); i += 2) {
            if (i != softShuffledText.length() - 1) {
                reversedText += softShuffledText.charAt(i + 1);
            }
            reversedText += softShuffledText.charAt(i);
        }

        String plainText = "";
        for (int i = reversedText.length() - 1; i >= 0; i--) {
            plainText += reversedText.charAt(i);
        }
        return plainText.substring(0, plainText.length() - key.length());
    }

    private static int getUniqueKeyValue(String plainText) {
        int keyValue = 0;
        for (int i = 0; i < plainText.length(); i++) {
            keyValue += plainText.charAt(i) * (i + 1);
        }
        return keyValue;
    }
}
