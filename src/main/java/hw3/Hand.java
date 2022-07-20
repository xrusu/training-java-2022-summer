package hw3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
@RequiredArgsConstructor
public class Hand {
    private final List<Card> cardList = new ArrayList<>();
    // random and unique
    private final UUID playerID = UUID.randomUUID();

    public boolean hasNoCards() {
        return cardList.isEmpty();
    }

    public void addCard(Card c) {
        cardList.add(c);
    }

    public void deleteCard(Rank rank, Suit suit) {
        cardList.removeIf(card -> card.getRank() == rank && card.getSuit() == suit);
    }

    public void sort() {
        Collections.sort(cardList);
    }

    public Card getLastCard() {
        return cardList.get(cardList.size() - 1);
    }

    public void display() {
        for (Card card : cardList) {
            System.out.println(card.toString());
        }
    }
}
