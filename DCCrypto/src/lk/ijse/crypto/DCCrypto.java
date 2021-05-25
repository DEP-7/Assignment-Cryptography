package lk.ijse.crypto;

public class DCCrypto {

    public static String encrypt(String plainText, String key) {
        String cipherText = "";

        /* Combine the text and the key
         * Eg:
         *   plainText = "Chandimal"
         *   key = "123"
         *   mergedText = "Chandimal123"
         * */
        String mergedText = plainText + key;

        /* reverse the mergedText
         * Eg:
         *  mergedText = "Chandimal123"
         *  reversedText = "321lamidnahC" */
        String reversedText = reverseString(mergedText);

        /* Shuffle the nearest two characters
         * Eg:
         *  reversedText = "321lamidnahC"
         *  shuffledText = "23l1madianCh" */
        String shuffledText = shuffleString(reversedText);

        /* Please find the Algorithm explanation inside secretAlgorithm() method */
        cipherText = secretAlgorithm(shuffledText, key, 1);

        return cipherText;
    }

    public static String decrypt(String cipherText, String key) {
        /* Check whether the lengths of cipher text greater than length of the key */
        if (cipherText.length() <= key.length()) {
            return cipherText;
        }

        String shuffledText = secretAlgorithm(cipherText, key, -1);

        String reversedText = shuffleString(shuffledText);

        String plainText = reverseString(reversedText);

        return plainText.substring(0, plainText.length() - key.length());
    }

    private static String secretAlgorithm(String text, String key, int status) {
        String outputString = "";

        /* Generate a uniqueKeyValue by unicode value of each character of the key multiplying by it's position and add all together */
        int uniqueKeyValue = getUniqueKeyValue(key);

        for (int i = 0; i < text.length(); i++) {
            /* Get the unicode value of current character */
            int code = text.charAt(i);

            /* Then add previously calculated uniqueKeyValue with above unicode value
             * variable "status" used to access this method from both encrypt() and decrypt() methods */
            code += uniqueKeyValue * status; // Algorithm 1

            /* Neglect the calculation for single length key
             * If we done this calculation for single length key, then it results to get hidden text easily
             * Eg:
             *   Let say, original text = "Chandimal"
             *   key = "1"
             *   we can see the output(cipherText) as this ->  "l1madianCh" (Shuffled text of original text)
             * It is risky. So neglect this calculation for single length key
             * */
            if (key.length() > 1) {
                /* Reason for use another algorithm:
                 *       - There is a very small probability we can get same uniqueKeyValue from two different keys
                 *           Eg: uniqueKeyValue is same for "AB" and "a2"
                 *
                 * In this stage it subtract the unicode of char value in key
                 * This use rotation method to get this value until end of the loop
                 *   Eg:
                 *       Let say key = "123" and this loop execute 10 times,
                 *       it get the key value like this -> 1, 2, 3, 1, 2, 3, 1, 2, 3, 1
                 * */
                code += status * (-1) * key.charAt(i % key.length()); // Algorithm 2
            }
            char newChar = (char) code;
            outputString += newChar;
        }
        return outputString;
    }

    private static int getUniqueKeyValue(String plainText) {
        int keyValue = 0;
        for (int i = 0; i < plainText.length(); i++) {
            keyValue += plainText.charAt(i) * (i + 1);
        }
        return keyValue;
    }

    private static String reverseString(String text) {
        String reversedText = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reversedText += text.charAt(i);
        }
        return reversedText;
    }

    private static String shuffleString(String text) {
        String shuffledText = "";
        for (int i = 0; i < text.length(); i += 2) {
            if (i != text.length() - 1) {
                shuffledText += text.charAt(i + 1);
            }
            shuffledText += text.charAt(i);
        }
        return shuffledText;
    }
}
