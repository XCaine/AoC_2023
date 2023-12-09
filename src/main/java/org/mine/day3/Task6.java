package org.mine.day3;

import org.mine.BaseTask;

import java.util.ArrayList;
import java.util.List;

public class Task6 extends BaseTask {

    static class Gear {
        int lineNumber;
        int charNumber;
        ArrayList<Integer> borderingNumbers = new ArrayList<>();
    }

    @Override
    protected void solve() {
        String[] lines = _reader.wholeFile();
        ArrayList<Gear> gears = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            char[] chars = line.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char currentChar = chars[j];
                if (currentChar != '*') {
                    continue;
                }

                Gear gear = new Gear();
                gear.lineNumber = i;
                gear.charNumber = j;
                //not first line
                if (i != 0) {
                    String previousLine = lines[i - 1];
                    addBorderingNumbersToGear(gear, previousLine);
                }

                //not last line
                if (i != lines.length - 1) {
                    String nextLine = lines[i + 1];
                    addBorderingNumbersToGear(gear, nextLine);
                }
                //not first character
                if (j != 0 && Character.isDigit(chars[j - 1])) {
                    int number = findNumber(line, j - 1);
                    gear.borderingNumbers.add(number);
                }
                //not last character
                if (j != chars.length - 1 && Character.isDigit(chars[j + 1])) {
                    int number = findNumber(line, j + 1);
                    gear.borderingNumbers.add(number);
                }

                gears.add(gear);
            }
        }

        List<Gear> matchingGears = gears.stream().filter(gear -> gear.borderingNumbers.size() == 2).toList();
        _result = matchingGears
                .stream()
                .map(gear -> (gear.borderingNumbers.getFirst() * gear.borderingNumbers.getLast()))
                .mapToLong(value -> value)
                .sum();
    }

    private void addBorderingNumbersToGear(Gear gear, String line) {
        int currentIndex = gear.charNumber;
        char[] upper = getRelevantLineFragment(line, currentIndex).toCharArray();
        if (Character.isDigit(upper[0]) && Character.isDigit(upper[1]) && Character.isDigit(upper[2])) {
            gear.borderingNumbers.add(findNumber(line, currentIndex - 1));
        } else if (!Character.isDigit(upper[1])) {
            if (Character.isDigit(upper[0]) && Character.isDigit(upper[2])) {
                int firstNumber = findNumber(line, currentIndex - 1);
                int secondNumber = findNumber(line, currentIndex + 1);

                gear.borderingNumbers.add(firstNumber);
                gear.borderingNumbers.add(secondNumber);
            } else if (Character.isDigit(upper[0])) {
                gear.borderingNumbers.add(findNumber(line, currentIndex - 1));
            } else if (Character.isDigit(upper[2])) {
                gear.borderingNumbers.add(findNumber(line, currentIndex + 1));
            }
        } else {
            //upper character IS a digit and there are 3 chars; we'll always find the number based on this digit
            gear.borderingNumbers.add(findNumber(line, currentIndex));
        }
    }

    private int findNumber(String lineString, int position) {
        char[] line = lineString.toCharArray();
        int currentPosition = position;
        if (!Character.isDigit(line[currentPosition])) {
            throw new IllegalArgumentException("Position should point to a number in the line");
        }

        while (currentPosition != 0 && Character.isDigit(line[currentPosition - 1])) {
            currentPosition -= 1;
        }

        StringBuilder numberSb = new StringBuilder();
        numberSb.append(line[currentPosition]);
        while (currentPosition != line.length - 1 && Character.isDigit(line[currentPosition + 1])) {
            currentPosition += 1;
            numberSb.append(line[currentPosition]);
        }

        return Integer.parseInt(numberSb.toString());
    }

    private String getRelevantLineFragment(String line, int currentIndex) {
        StringBuilder prevLineSb = new StringBuilder();
        if (currentIndex != 0) {
            prevLineSb.append(line.charAt(currentIndex - 1));
        } else {
            prevLineSb.append(".");
        }
        prevLineSb.append(line.charAt(currentIndex));
        if (currentIndex != line.length() - 1) {
            prevLineSb.append(line.charAt(currentIndex + 1));
        } else {
            prevLineSb.append(".");
        }
        return prevLineSb.toString();
    }
}
