package org.mine.day2;

import org.mine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameRecord {

    private static final char _RED_FIRST_LETTER = 'r';
    private static final char _GREEN_FIRST_LETTER = 'g';
    private static final char _BLUE_FIRST_LETTER = 'b';

    public static GameRecord parseLine(String line) {
        String[] gameComponents = line.split(":");

        int gameID = Integer.parseInt(
                //get rid of "Game " string
                gameComponents[0].substring(5)
        );

        GameRecord gameRecord = new GameRecord(gameID);

        String allAttempts = gameComponents[1];
        String[] attempts = allAttempts.split(";");

        for (String attemptString : attempts) {
            GameRecord.Attempt attempt = new GameRecord.Attempt();
            String[] results = attemptString.split(",");
            for (String result : results) {
                switch (StringUtils.getFirstLetter(result)) {
                    case _RED_FIRST_LETTER:
                        attempt.red = StringUtils.getFirstNumberFromString(result);
                        break;
                    case _GREEN_FIRST_LETTER:
                        attempt.green = StringUtils.getFirstNumberFromString(result);
                        break;
                    case _BLUE_FIRST_LETTER:
                        attempt.blue = StringUtils.getFirstNumberFromString(result);
                        break;
                }

            }
            gameRecord.attempts.add(attempt);
        }
        return gameRecord;
    }

    private static final Logger _logger = LoggerFactory.getLogger(GameRecord.class);

    int id;
    List<Attempt> attempts;

    GameRecord(int id) {
        this.id = id;
        this.attempts = new ArrayList<>();
    }

    public void print(boolean result) {
        String attemptsString = attempts
                .stream()
                .map(attempt -> STR."{\{attempt.red} \{attempt.green} \{attempt.blue}}")
                .collect(Collectors.joining(","));
        _logger.debug(STR."ID: \{id}; Attempts: \{attemptsString}; Result: \{result}");
    }

    public void print() {
        String attemptsString = attempts
                .stream()
                .map(attempt -> STR."{\{attempt.red} \{attempt.green} \{attempt.blue}}")
                .collect(Collectors.joining(","));
        _logger.debug(STR."ID: \{id}; Attempts: \{attemptsString};");
    }

    static class Attempt {
        int blue = 0;
        int green = 0;
        int red = 0;
    }
}
