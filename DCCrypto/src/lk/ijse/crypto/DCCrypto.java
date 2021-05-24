package lk.ijse.crypto;

public class DCCrypto {
    public static String encrypt(String plainText, String key) {
        plainText += key;
        String reversedText = "";
        for (int i = plainText.length() - 1; i >= 0; i--) {
            reversedText += plainText.charAt(i);
        }
        System.out.println(reversedText);
        String softShuffledText = "";
        for (int i = 0; i < reversedText.length(); i += 2) {
            if (i != reversedText.length() - 1) {
                softShuffledText += reversedText.charAt(i + 1);
            }
            softShuffledText += reversedText.charAt(i);
        }
        System.out.println(softShuffledText);
        int uniqueKeyValue = getUniqueKeyValue(key);
        //System.out.println(uniqueKeyValue);
        String cipherText = "";
        for (int i = 0; i < softShuffledText.length(); i++) {
            int code = softShuffledText.charAt(i);
            code += uniqueKeyValue;
            char newChar = (char) code;
            cipherText += newChar;
        }
        System.out.println(cipherText);
        return null;
    }

    public static String decrypt(String cipherText, String key) {
        return null;
    }

    private static int getUniqueKeyValue(String plainText) {
        int keyValue = 0;
        for (int i = 0; i < plainText.length(); i++) {
            keyValue += plainText.charAt(i) * (i + 1);
            //System.out.println((int) plainText.charAt(i)*(i+1));
        }
        return keyValue;
        //System.out.println(keyValue);
    }

    public static void main(String[] args) {
        DCCrypto.encrypt("abcde", "123");// for odd lengths
        System.out.println("-------------------");
        DCCrypto.encrypt("abcde", "1234");// for even lengths
        System.out.println(getUniqueKeyValue("12345"));
    }
}
