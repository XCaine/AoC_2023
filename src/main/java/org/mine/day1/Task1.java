package org.mine.day1;

import org.mine.BaseTask;

public class Task1 extends BaseTask {

    @Override
    protected void solve() {

        String line = _reader.nextLine();
        while (line != null) {
            _LOGGER.debug(STR."Line: \{line}");
            boolean firstNumberAssigned = false;
            char firstNumber = 0;
            char lastNumber = 0;
            for(char c : line.toCharArray()) {
                if(!Character.isDigit(c)) {
                    continue;
                }
                if(!firstNumberAssigned) {
                    firstNumber = c;
                    firstNumberAssigned = true;
                }
                lastNumber = c;
            }
            _result += Integer.parseInt(STR."\{firstNumber}\{lastNumber}");
            _LOGGER.debug(STR."Sum: \{_result}; First number: \{firstNumber}; Second number: \{lastNumber}");
            line = _reader.nextLine();
        }
    }

}
