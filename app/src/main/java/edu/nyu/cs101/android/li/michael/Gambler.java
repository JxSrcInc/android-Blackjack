package edu.nyu.cs101.android.li.michael;

import java.util.Random;

public class Gambler {
    protected final String HIT = "hit";
    protected final String STAND = "stand";
    private final int MAX_CARDS = 10;

    // a string that keeps track of which cards we've dealt to the gambler
    String cards = ""; // starts off blank

    // sentinel variable that keep track of whether to ask each player if they want
    // a new card
    boolean askToHit = true;

    // two sentinel variables that keep track of whether either player has gone
    // "bust".
    boolean bust = false;

    // two integers to keep a running total of the points
    int total = 0;

    // create a Random "object" to use to generate random numbers
    // the default "seed" used by this built-in Random object is the current time in
    // milliseconds.
    // two separate pseudo-random number generators based on the same seed will
    // produce the same pseudo-random numbers... beware!
    Random randomGenerator = new Random();

    public Gambler() {
        init();
    }
    private void init() {
        // deal first "hand" to player
        int card1 = getNextCard();
        int card2 = getNextCard();
        cards = card1 + ", " + card2; // store these cards as a string we use to tell the user what they have been
        // dealt so far

        // calculate totals
        total = card1 + card2; // add these two cards to the total
    }

    public void reset() {
        cards = "";
        askToHit = true;
        bust = false;
        init();
    }

    public int getNextCard() {
        return randomGenerator.nextInt(MAX_CARDS) + 1; // generate a pseudo-random number between 1-10
    }

    protected boolean proc(String response) {
        switch (response.toLowerCase()) {
            case HIT:
                int newCard = getNextCard();
                total += newCard;
                cards += ", " + newCard;
                if (total > 21) {
                    bust = true;
                }
                return true;
            case STAND:
                askToHit = false;
                return true;
            default:
                // should not happen
                throw new RuntimeException("Invalid response");
        } // switch
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public boolean isAskToHit() {
        return askToHit;
    }

    public void setAskToHit(boolean askToHit) {
        this.askToHit = askToHit;
    }

    public boolean isBust() {
        return bust;
    }

    public void setBust(boolean bust) {
        this.bust = bust;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

