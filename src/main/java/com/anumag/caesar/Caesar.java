package com.anumag.caesar;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.lang.Character.isWhitespace;

public class Caesar {
    private final String alphabet = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";

    public String encrypt(String text, int key) {
        text = text.toLowerCase(Locale.ROOT);
        StringBuilder encryptedText = new StringBuilder();
        int[] positions = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                boolean isWhitespace = isWhitespace(text.charAt(i));
                if (isWhitespace) {
                    positions[i] = 100;
                } else if (text.charAt(i) == alphabet.charAt(j)) {
                    int pos;
                    pos = j + key;
                    while (pos >= 33) {
                        pos -= 33;
                    }
                    positions[i] = pos;

                }
            }
        }

        for (int position : positions) {
            if (position == 100) {
                encryptedText.append(" ");
            } else {
                for (int j = 0; j < alphabet.length(); j++) {
                    if (position == j) {
                        encryptedText.append(alphabet.charAt(j));
                    }
                }
            }
        }
        return encryptedText.toString();
    }

    public String decrypt(String text, int key) {
        text = text.toLowerCase(Locale.ROOT);
        StringBuilder decryptedText = new StringBuilder();
        int[] positions = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                boolean isWhitespace = isWhitespace(text.charAt(i));
                if (isWhitespace) {
                    positions[i] = 100;
                } else if (text.charAt(i) == alphabet.charAt(j)) {
                    int pos;
                    pos = j - key;
                    while (pos < 0) {
                        pos += 33;
                    }
                    positions[i] = pos;
                }
            }
        }

        for (int position : positions) {
            if (position == 100) {
                decryptedText.append(" ");
            } else {
                for (int j = 0; j < alphabet.length(); j++) {
                    if (position == j) {
                        decryptedText.append(alphabet.charAt(j));
                    }
                }
            }
        }
        return decryptedText.toString();
    }

    public String bruteForce(String text) {
        text = text.toLowerCase(Locale.ROOT);
        StringBuilder decryptedText = new StringBuilder();

        int[] positions = new int[text.length()];


        for (int k = 0; k < 33; k++) {
            decryptedText.append(k + ". ");
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < alphabet.length(); j++) {
                    boolean isWhitespace = isWhitespace(text.charAt(i));
                    if (isWhitespace) {
                        positions[i] = 100;
                    } else if (text.charAt(i) == alphabet.charAt(j)) {
                        int pos;
                        pos = j - k;
                        if (pos < 0) {
                            pos += 33;
                        }
                        positions[i] = pos;
                    }
                }
            }

            for (int position : positions) {
                if (position == 100) {
                    decryptedText.append(" ");
                } else {
                    for (int j = 0; j < alphabet.length(); j++) {
                        if (position == j) {
                            decryptedText.append(alphabet.charAt(j));
                        }
                    }
                }
            }
            decryptedText.append("\n\n");
        }
        return decryptedText.toString();
    }

    public String frequencyAnalysis(String text) {
        StringBuilder decryptedText = new StringBuilder();

        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        String tempText = text.replaceAll("\\s+", "");
        char[] strArray = tempText.toCharArray();
        for (char c : strArray) {
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {
                charCountMap.put(c, 1);
            }
        }

        for (Map.Entry entry : charCountMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        Map.Entry<Character, Integer> maxEntry = null;

        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        System.out.println(maxEntry);

        String keyValue = maxEntry.getKey().toString();
        System.out.println(keyValue);
        int key1 = 0;
        int key2 = 0;

        for (int i = 0; i < alphabet.length(); i++) {
            if (alphabet.charAt(i) == keyValue.charAt(0)) {
                key1 = i;
                key2 = i - 18;
                while (key2 < 0) {
                    key2 += 33;
                }
            }
        }

        System.out.println(key1);
        System.out.println(key2);

        decryptedText.append(decrypt(text, key1) + "\n");
        decryptedText.append(decrypt(text, key2));

        return decryptedText.toString();
    }
}
