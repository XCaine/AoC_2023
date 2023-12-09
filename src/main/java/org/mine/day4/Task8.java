package org.mine.day4;

import org.mine.BaseTask;

import java.util.*;

public class Task8 extends BaseTask {

    static class CardWrapper {
        int id;
        int count;
        int commonNumbers;

        public CardWrapper(Card card) {
            this.id = card.id;
            ArrayList<Integer> intersectingNumbers = card.leftNumbers;
            intersectingNumbers.retainAll(card.rightNumbers);
            this.commonNumbers = intersectingNumbers.size();
            this.count = 1;
        }
    }

    @Override
    protected void solve() {
        String line = _reader.nextLine();

        LinkedList<CardWrapper> cards = new LinkedList<>();

        while(line != null) {
            Card card = Card.fromString(line);
            CardWrapper cr = new CardWrapper(card);
            cards.add(cr);
            line = _reader.nextLine();
        }

        int i = 0;
        int limit = cards.size() - 1;
        while (i != limit) {
            CardWrapper currentCard = cards.get(i);
            for (int j = 0; j < currentCard.count; j++) {
                for (int k = 1; k <= currentCard.commonNumbers; k++) {
                    cards.get(i + k).count++;
                }
            }
            i++;
        }

        _result = cards.stream().mapToLong(cardWrapper -> cardWrapper.count).sum();
    }

}
