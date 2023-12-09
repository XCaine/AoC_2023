package org.mine.day3;

import org.mine.BaseTask;

import java.util.ArrayList;

public class Task5 extends BaseTask {

    @Override
    protected void solve() {
        String[] lines = _reader.wholeFile();

        ArrayList<Integer> engineParts = new ArrayList<>();

        for(int i = 0; i < lines.length; i++) {
            char[] chars = lines[i].toCharArray();

            StringBuilder currentNumber = new StringBuilder();
            boolean foundEnginePath = false;

            for (int j = 0; j < chars.length; j++) {
                char currentChar = chars[j];

                if(Character.isDigit(currentChar)) {
                    currentNumber.append(Character.getNumericValue(currentChar));
                    if(j != 0) {
                        char prevChar = chars[j-1];
                        if(!Character.isDigit(prevChar) && prevChar != '.') {
                            foundEnginePath = true;
                        }
                    }
                    if(j != chars.length - 1) {
                        char prevChar = chars[j+1];
                        if(!Character.isDigit(prevChar) && prevChar != '.') {
                            foundEnginePath = true;
                        }
                    }
                    if(i != 0) {
                        char charAtPrevLine = (lines[i-1]).charAt(j);
                        if(!Character.isDigit(charAtPrevLine) && charAtPrevLine != '.') {
                            foundEnginePath = true;
                        }
                    }
                    if(i != lines.length - 1) {
                        char charAtNextLine = (lines[i+1]).charAt(j);
                        if(!Character.isDigit(charAtNextLine) && charAtNextLine != '.') {
                            foundEnginePath = true;
                        }
                    }
                    if(i != 0 && j != 0) {
                        var thatChar = (lines[i-1]).charAt(j-1);
                        if(!Character.isDigit(thatChar) && thatChar != '.') {
                            foundEnginePath = true;
                        }
                    }
                    if(i != 0 && j != chars.length - 1) {
                        var thatChar = (lines[i-1]).charAt(j+1);
                        if(!Character.isDigit(thatChar) && thatChar != '.') {
                            foundEnginePath = true;
                        }
                    }
                    if(i != lines.length - 1 && j != 0) {
                        var thatChar = (lines[i+1]).charAt(j-1);
                        if(!Character.isDigit(thatChar) && thatChar != '.') {
                            foundEnginePath = true;
                        }
                    }
                    if(i != lines.length - 1 && j != chars.length - 1) {
                        var thatChar = (lines[i+1]).charAt(j+1);
                        if(!Character.isDigit(thatChar) && thatChar != '.') {
                            foundEnginePath = true;
                        }
                    }


                    if(j != chars.length - 1) {
                        char nextChar = chars[j+1];
                        if(!Character.isDigit(nextChar)) {
                            if(foundEnginePath) {
                                engineParts.add(Integer.parseInt(currentNumber.toString()));
                            }
                            //clear StringBuilder
                            currentNumber.setLength(0);
                            foundEnginePath = false;
                        }
                    } else {
                        if(foundEnginePath) {
                            engineParts.add(Integer.parseInt(currentNumber.toString()));
                        }
                    }
                }
            }
        }
        _result = engineParts.stream().mapToLong(Integer::longValue).sum();
    }
}
