package hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class Hw3 {
    private static final int NUMBER_OF_GAMES = 50000;
    // no need for volatile here due to each thread having its own id
    // not very good if we have a high number of games but for this case it's ok
    private static int[] numberOfMovesRecord = new int[NUMBER_OF_GAMES];

    // used to track when all games are finished so that
    // all other computations may be done (max min mean)
    // AtomicInteger would be overkill tho
    private static AtomicInteger gamesFinishedAtomic = new AtomicInteger(1);


    public static void main(String[] args) {
        // README: UNCOMMENT ONE BY ONE EACH FUNCTION BELOW
        // TO SEE DIFFERENT WAYS OF RUNNING THE GAME

        // runWithThreadPool();
        // runWithSingleThreadForEachGame();
        // runWithoutThreads();
    }

    public static void runWithThreadPool() {
        // TODO RUN NUMBER_OF_GAMES GAMES, BY CREATING A THREAD POOL, WITH A DYNAMIC NUMBER OF THREADS
    }

    public static void runWithSingleThreadForEachGame() {
        // TODO RUN NUMBER_OF_GAMES GAMES, WITH A SINGLE THREAD CORRESPONDING TO EACH GAME
    }

    public static void runWithoutThreads() {
        // TODO RUN NUMBER_OF_GAMES GAMES, WITHOUT THREADS
    }

    public static void gameInstance(int threadId) {
        // TODO create the game instance but make it a thread
        // ~ copy paste code from gameInstanceST but wrap it in a thread,
        // or use gameInstanceST() and pass the threadId inside the created thread
    }

    public static void computeResults() {
        int mean = 0;
        for (int i = 0; i < NUMBER_OF_GAMES; i++) {
            mean += numberOfMovesRecord[i];
            System.out.println("Number of moves: " + numberOfMovesRecord[i]);
        }
        Arrays.sort(numberOfMovesRecord);
        System.out.println("Min: " + numberOfMovesRecord[0]);
        System.out.println("Max: " + numberOfMovesRecord[NUMBER_OF_GAMES - 1]);
        System.out.println("Mean: " + mean / NUMBER_OF_GAMES);
    }

    // used for the thread pool
    public static void gameInstanceST(int threadId) {
        int numberOfMoves = 0;
        // game initialization stuff
        Deck deck = new Deck();

        //didn't want to put the code from init in the constructor
        //don't really have a good reason for this other than didn't want to
        //tamper with the lombok autogenerated constructor
        deck.init();

        //shuffle the deck
        deck.shuffle();

        //dynamic number of players, the game won't break if more players are added
        List<Hand> players = new ArrayList<>();
        players.add(new Hand());
        players.add(new Hand());
        players.add(new Hand());
        players.add(new Hand());

        //deal an ~ equal amount of cards to each player
        while (!deck.isEmpty()) {
            for (Hand hand : players) {
                hand.addCard(deck.getCard());
            }
        }

        while (players.size() - 1 >= 1) {
            numberOfMoves++;

            // could have used a normal List<Card> but
            // I'm going keep a player ID and its card in a Pair
            //
            // could have find out the player without the player id like this:
            // each card can only exist once, hence, we can find out
            // with player showed which card
            // this is at the cost of execution speed, to save memory <- not smart, memory is cheap time not so much
            // hence I chose to use a Pair
            List<Pair> cardsOnTable = new ArrayList<>();

            for (Hand hand : players) {
                cardsOnTable.add(new Pair(hand.getPlayerID(), hand.getLastCard()));
            }

            Collections.sort(cardsOnTable);
            Pair biggestCard = cardsOnTable.get(cardsOnTable.size() - 1);

            for (Hand hand : players) {
                if (hand.getPlayerID().equals(biggestCard.getLeft())) {
                    // add all the cards to the winner
                    for (Pair pair : cardsOnTable) {
                        hand.addCard(pair.getRight());

                        //remove the card from the losers hands
                        for (Hand anotherHand : players) {
                            if (anotherHand.getPlayerID().equals(pair.getLeft())) {
                                anotherHand.deleteCard(pair.getRight().getRank(), pair.getRight().getSuit());
                            }
                        }
                    }
                }
            }

            players.removeIf(Hand::hasNoCards);
        }

        numberOfMovesRecord[threadId] = numberOfMoves;
        gamesFinishedAtomic.incrementAndGet();

        // check if all games have been 'played' and if so,
        // calculate max min and mean
        if (gamesFinishedAtomic.get() == NUMBER_OF_GAMES) {
            computeResults();
        }
    }
}
