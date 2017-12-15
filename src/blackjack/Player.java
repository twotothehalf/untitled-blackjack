package blackjack;
public class Player {
    private int[] hand;
    private int score;
    private String name;
    
    public Player(int[] card) {
        this.hand = card;
        this.score = 0;
    }
    public int getScore() {
        return this.score;
    }
    public void clearHand() {
        this.hand = new int[0];
    }
    public int[] getHand() {
        return this.hand;
    }
    
    public void setHand(int i, int hand) {
        this.hand[i] = hand;
    }
    
    public void setHand(int[] hand) {
        this.hand = hand;
    }
    
    public String[] getHandName() {
        String[] name = new String[this.hand.length];
        for (int i=0; i<this.hand.length; i++) {
            switch((this.hand[i] % 52) % 13) {
                case 0 : name[i] = "A"; break;
                case 10: name[i] = "J"; break;
                case 11: name[i] = "Q"; break;
                case 12: name[i] = "K"; break;
                default: name[i] = Integer.toString(((this.hand[i]%52)%13)+1);
            }
            switch((this.hand[i]%52)/13) {
                case 0: name[i]+="♦"; break;
                case 1: name[i]+="♣"; break;
                case 2: name[i]+="♥"; break;
                case 3: name[i]+="♠"; break;
            }
        }
        return name;
    }
    public int getHand(int n) {
        return this.hand[n];
    }
    public String getCardValue(int n) {
        String v;
        if (n%13>=1&&n%13<=9) {v = (n%13 + 1) + "";}
        else if (n%13>9) {v = "10";}
        else {v = "1 or 11";}
        return v;
    }
    public int getHandValue() {
        int value=0;
        // This loop adds up the non-aces first, since aces can be either 1 or 11
        for (int i=0; i<this.hand.length; i++) {
            if (this.hand[i]%13 >= 1 && this.hand[i]%13 <= 9) {
                value += (this.hand[i] % 13) + 1;
            }
            else if (this.hand[i] % 13 > 9) {
                value += 10;
            }
        }
        // This loop adds up only the aces, depending on the score
        for (int i=0; i<this.hand.length; i++) {
            if (this.hand[i] % 13 == 0) {
                if (value <= 10) {
                    value += 11;
                }
                else {
                    value += 1;
                }
            }
        }
        return value;        
    }
    public void addToHand(int[] cards) {
        int[] newHand = new int[this.hand.length + cards.length];
        System.arraycopy(this.hand, 0, newHand, 0, this.hand.length);
        System.arraycopy(cards, 0, newHand, this.hand.length, cards.length);
        this.hand = newHand;
    }
    public void addScore(int p) {
        this.score += p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isCardSplittable() {        
        return this.hand[0]-this.hand[1] == 0 || this.hand[0]-this.hand[1] == -13;
    }
}
