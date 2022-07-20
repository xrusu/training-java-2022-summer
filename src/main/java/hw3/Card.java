package hw3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public final class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    @Override
    public int compareTo(Card o) {
        if (rank == o.rank && suit == o.suit) {
            throw new MalformedDeck("Found two identical cards");
        }
        if (rank == o.rank) {
            return suit.compareTo(o.suit);
        } else {
            return o.rank.compareTo(rank);
        }
    }
}