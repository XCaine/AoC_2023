package org.mine.day1;

import org.mine.BaseTask;

import java.util.Map;

public class Task2 extends BaseTask {

    private final Map<String, Integer> _numberMap = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
    );

    @Override
    protected void solve() {
        String line = _reader.nextLine();

        while (line != null) {
            _LOGGER.debug(STR."Line: \{line}");

            int firstNumber = -1;
            int lastNumber = -1;
            boolean firstNumberAssigned = false;

            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char currentDigit = chars[i];
                if (Character.isDigit(currentDigit)) {
                    if (!firstNumberAssigned) {
                        firstNumber = Character.getNumericValue(currentDigit);
                        firstNumberAssigned = true;
                    }
                    lastNumber = Character.getNumericValue(currentDigit);
                } else {
                    //not a digit
                    for (String numberString : _numberMap.keySet()) {
                        if (matches(numberString.toCharArray(), chars, i)) {
                            if (!firstNumberAssigned) {
                                firstNumber = _numberMap.get(numberString);
                                firstNumberAssigned = true;
                            }
                            lastNumber = _numberMap.get(numberString);
                        }
                    }
                }
            }
            _result += Integer.parseInt(STR."\{firstNumber}\{lastNumber}");
            _LOGGER.debug(STR."First number: \{firstNumber}; Last number: \{lastNumber}");
            line = _reader.nextLine();
        }
    }

    boolean matches(char[] pattern, char[] outerString, int startingPositionInOuterString) {
        if (pattern.length > outerString.length - startingPositionInOuterString) {
            return false;
        }
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] != outerString[i + startingPositionInOuterString]) {
                return false;
            }
        }
        return true;
    }

}
