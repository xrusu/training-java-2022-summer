package hw3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
@Getter
@RequiredArgsConstructor
public class Deck {

    private final List<Card> cardList = new ArrayList<>();

    public void init() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                addCard(new Card(rank, suit));
            }
        }
    }

    public boolean isEmpty() {
        return cardList.isEmpty();
    }

    public Card getCard() {
        Card card = cardList.get(cardList.size() - 1);
        cardList.remove(cardList.size() - 1);
        return card;
    }

    public void removeCard(Card c) {
        cardList.removeIf(card -> card.getRank() == c.getRank() && card.getSuit() == c.getSuit());
    }

    public void addCard(Card c) {
        cardList.add(c);
    }

    public void shuffle() {
        Collections.shuffle(cardList);
    }

    public void sort() {
        Collections.sort(cardList);
    }
}
