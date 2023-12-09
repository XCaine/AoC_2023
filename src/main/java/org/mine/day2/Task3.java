package org.mine.day2;

import org.mine.BaseTask;


public class Task3 extends BaseTask {

    @Override
    protected void solve() {
        int maxRedCubes = 12;
        int maxGreenCubes = 13;
        int maxBlueCubes = 14;

        String line = _reader.nextLine();
        while (line != null) {
            GameRecord game = GameRecord.parseLine(line);
            boolean gameIsValid = (game.attempts.stream().allMatch(attempt -> attempt.green <= maxGreenCubes && attempt.red <= maxRedCubes && attempt.blue <= maxBlueCubes));
            game.print(gameIsValid);
            if (gameIsValid) {
                _result += game.id;
            }
            line = _reader.nextLine();
        }
    }
}








