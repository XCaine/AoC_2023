package org.mine.day7;

import org.apache.commons.lang3.tuple.MutablePair;
import org.mine.BaseTask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Task13 extends BaseTask {

    private static final String _PRECEDENCE = "23456789TJQKA";

    private static class GameHand implements Comparable<GameHand> {
        char[] hand;
        int bid;
        int type;

        public GameHand(String hand, int bid) {
            this.hand = hand.toCharArray();
            this.bid = bid;
            this.type = calculateType();
        }

        /**
         * Five of a kind   -> 6
         * Four of a kind   -> 5
         * Full house       -> 4
         * Three of a kind  -> 3
         * Two pair         -> 2
         * One pair         -> 1
         * High card        -> 0
         */
        private int calculateType() {
            ArrayList<MutablePair<Character, Integer>> cardStatistics = new ArrayList<>();

            for (char card : hand) {
                Optional<MutablePair<Character, Integer>> foundCard = cardStatistics
                        .stream()
                        .filter(pair -> pair.getLeft() == card)
                        .findAny();

                if (foundCard.isEmpty()) {
                    cardStatistics.add(new MutablePair<>(card, 1));
                } else {
                    foundCard.get().setRight(foundCard.get().getRight() + 1);
                }
            }

            Integer maxSameCards = cardStatistics.stream().max(Comparator.comparing(MutablePair::getRight)).get().getRight();
            int type = 0;
            if (maxSameCards == 5) {
                type = 6;
            } else if (maxSameCards == 4) {
                type = 5;
            } else if (maxSameCards == 3) {
                if (cardStatistics.stream().anyMatch(cs -> cs.getRight() == 2)) {
                    type = 4;
                } else {
                    type = 3;
                }
            } else if (maxSameCards == 2) {
                if (cardStatistics.stream().filter(cs -> cs.getRight() == 2).count() == 2) {
                    type = 2;
                } else {
                    type = 1;
                }
            }
            return type;
        }

        @Override
        public int compareTo(GameHand o) {
            if (this.type != o.type) {
                return this.type > o.type ? 1 : -1;
            } else {
                for (int i = 0; i < this.hand.length; i++) {
                    int thisCardPrecedence = _PRECEDENCE.indexOf(this.hand[i]);
                    int otherCardPrecedence = _PRECEDENCE.indexOf(o.hand[i]);
                    if(thisCardPrecedence != otherCardPrecedence) {
                        return thisCardPrecedence > otherCardPrecedence ? 1 : -1;
                    }
                }
            }
            return 0;
        }
    }

    @Override
    protected void solve() {
        ArrayList<GameHand> hands = parseInput();
        hands.sort(GameHand::compareTo);

        for (int i = 1; i <= hands.size(); i++) {
            GameHand hand = hands.get(i - 1);
            _result += ((long) i * hand.bid);
        }
    }

    ArrayList<GameHand> parseInput() {
        String line = _reader.nextLine();
        ArrayList<GameHand> hands = new ArrayList<>();
        while (line != null) {
            String[] parts = line.split("\\s+");
            hands.add(new GameHand(parts[0], Integer.parseInt(parts[1])));
            line = _reader.nextLine();
        }
        return hands;
    }
}
