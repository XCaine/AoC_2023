package org.mine.day4;

import org.mine.BaseTask;

import java.util.ArrayList;

public class Task7 extends BaseTask {

    @Override
    protected void solve() {
        String line = _reader.nextLine();

        ArrayList<Card> cards = new ArrayList<>();
        while (line != null) {
            Card card = Card.fromString(line);
            cards.add(card);

            ArrayList<Integer> intersectingNumbers = card.leftNumbers;
            intersectingNumbers.retainAll(card.rightNumbers);
            int numberOfCorrectGuesses = intersectingNumbers.size();

            int points = 0;
            if(numberOfCorrectGuesses != 0) {
                points = (int)Math.pow(2, numberOfCorrectGuesses - 1);
            }
            _result += points;
            line = _reader.nextLine();
        }
    }

}
