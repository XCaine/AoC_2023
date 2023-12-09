package org.mine.day2;

import org.mine.BaseTask;

public class Task4 extends BaseTask {

    @Override
    protected void solve() {
        String line = _reader.nextLine();
        while (line != null) {
            GameRecord game = GameRecord.parseLine(line);
            game.print();

            int countRed = 0;
            int countGreen = 0;
            int countBlue = 0;
            for(GameRecord.Attempt attempt : game.attempts) {
                if(attempt.red > countRed) countRed = attempt.red;
                if(attempt.green > countGreen) countGreen = attempt.green;
                if(attempt.blue > countBlue) countBlue = attempt.blue;
            }
            _LOGGER.debug(STR."red: \{countRed}; green: \{countGreen}; blue: \{countBlue}");
            _result += (long) countRed * countBlue * countGreen;
            line = _reader.nextLine();
        }
    }
}
