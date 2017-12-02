package blackjack;

import java.util.Arrays;
import java.util.Random;

final class Deck {
    private int[] deck;
    private Random r;
    private int[] getDeck(int size) {
        this.r = new Random();
        int i=0;
        int[] d = new int[52*size];
        while (i<52*size) {
            boolean inDeck = false;
            int x = r.nextInt(52*size);
            for(int j=0; j<i; j++) {
                if (d[j] == x) {inDeck = true; break;}
            }
            if (!inDeck) {d[i] = x; i++;}
        }
        return d;
    }
    public Deck() {
        this.deck = getDeck(1);
    }
    public Deck(int n) {
        this.deck = getDeck(n);
    }
    public int[] giveCard(int count){
        int remSize = this.deck.length - count;
        if (remSize >= 0) {
            int[] d = Arrays.copyOfRange(this.deck, 0, count);
            this.deck = Arrays.copyOfRange(this.deck, count, this.deck.length);
            return d;
        }
        else {
            int[] d = {};
            return d;
        }
    }
    public boolean isEmpty() {
        return (this.deck.length == 0);
    }
    public int[] getDeck() {
        return this.deck;
    }
}