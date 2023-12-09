package org.mine.day4;

import java.util.ArrayList;

public class Card implements Comparable<Card> {

    public static Card fromString(String line) {
        Card card = new Card();
        String[] gameComponents = line.split(":");
        card.id = Integer.parseInt((gameComponents[0]).substring(4).trim());
        String[] numbers = (gameComponents[1]).split("\\|");
        String winningNumbers = (numbers[0]).trim();

        for (String winningNumberString : winningNumbers.split("\\s+")) {
            card.leftNumbers.add(Integer.parseInt(winningNumberString));
        }
        card.leftNumbers.sort(Integer::compareTo);


        String chosenNumbers = (numbers[1]).trim();
        for (String chosenNumberString : chosenNumbers.split("\\s+")) {
            card.rightNumbers.add(Integer.parseInt(chosenNumberString));
        }
        card.rightNumbers.sort(Integer::compareTo);
        return card;
    }

    int id;
    ArrayList<Integer> leftNumbers = new ArrayList<>();
    ArrayList<Integer> rightNumbers = new ArrayList<>();

    private Card() {

    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.id, o.id);
    }
}
