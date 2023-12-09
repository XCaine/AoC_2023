package org.mine.day7;

import org.apache.commons.lang3.tuple.MutablePair;
import org.mine.BaseTask;

import java.util.*;

public class Task14 extends BaseTask {

    private static final String _PRECEDENCE = "J23456789TQKA";

    private static class GameHand implements Comparable<GameHand> {
        char[] cards;
        int bid;
        int type;

        public GameHand(String cards, int bid) {
            this.cards = cards.toCharArray();
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

            for (char card : cards) {
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

            Optional<MutablePair<Character, Integer>> nonJokerCards = cardStatistics.stream()
                    .filter(cs -> cs.getLeft() != 'J')
                    .max(Comparator.comparing(MutablePair::getRight));
            int maxNonJokerCards = nonJokerCards.isPresent() ? nonJokerCards.get().getRight() : 0;

            Optional<MutablePair<Character, Integer>> jokers = cardStatistics.stream().filter(cs -> cs.getLeft() == 'J').findAny();
            int jokersCount = jokers.isPresent() ? jokers.get().getRight() : 0;
            int type = 0;
            if (maxNonJokerCards + jokersCount == 5) {
                type = 6;
            } else if (maxNonJokerCards + jokersCount == 4) {
                type = 5;
            } else if (maxNonJokerCards + jokersCount == 3) {
                //either 3 + 2 + 0J or 2 + 2 + 1J or 2 + 1 + 2J
                if (jokersCount == 2 && cardStatistics.stream().filter(cs -> cs.getLeft() != 'J').anyMatch(cs -> cs.getRight() == 2)) {
                    type = 4;
                } else if (jokersCount == 1 && cardStatistics.stream().filter(cs -> cs.getRight() == 2).count() == 2) {
                    type = 4;
                } else if (jokersCount == 0 && cardStatistics.stream().anyMatch(cs -> cs.getRight() == 2)) {
                    type = 4;
                } else {
                    type = 3;
                }
            } else if (maxNonJokerCards + jokersCount == 2) {
                int pairCount = (int) cardStatistics.stream().filter(cs -> cs.getRight() == 2).count();
                //either 2 + 2 + 0J / 2 + 1 + 1J / 1 + 1J / 2 + 0J
                if (pairCount == 2) {
                    type = 2;
                } else if(pairCount == 1 && jokersCount == 1){
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
                for (int i = 0; i < this.cards.length; i++) {
                    int thisCardPrecedence = _PRECEDENCE.indexOf(this.cards[i]);
                    int otherCardPrecedence = _PRECEDENCE.indexOf(o.cards[i]);
                    if (thisCardPrecedence != otherCardPrecedence) {
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
