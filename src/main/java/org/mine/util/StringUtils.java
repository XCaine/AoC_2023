package org.mine.util;

public class StringUtils {

    public static int getFirstNumberFromString(String string) {
        char[] chars = string.toCharArray();
        StringBuilder sb = new StringBuilder();

        boolean hasFoundFirstDigit = false;
        for(char c : chars) {
            if(Character.isDigit(c)) {
                hasFoundFirstDigit = true;
                sb.append(c);
            } else if(hasFoundFirstDigit) {
                //not a digit and first digit has been already found
                break;
            }
        }
        return Integer.parseInt(sb.toString());
    }

    public static char getFirstLetter(String string) {
        char[] chars = string.toCharArray();
        for(char c : chars) {
            if(Character.isLetter(c)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No letters in string");
    }
}
