package blackjack;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Tester {
    static Deck d = new Deck();
    static Scanner s = new Scanner(System.in);
    static Betting bet = new Betting();
    static Random r = new Random();
    static Player[] player = new Player[5];
    static int currentPlayerHand = 1;
    static String[] currentStatus = new String[4];
        
    // Maximum possible hand size in blackjack: 11 cards
    public static void main(String[] args) {
        //Dealer setup
        player[0] = new Player(d.giveCard(2));
        System.out.print("Dealer name: ");
        player[0].setName(s.nextLine());
        System.out.print("Dealer money: ");
        bet.setDealerMoney(s.nextDouble());
        s.nextLine();

        //Player setup
        player[1] = new Player(d.giveCard(2));
        System.out.print("Player name: ");
        player[1].setName(s.nextLine());
        System.out.print("Player money: ");
        bet.setPlayerMoney(s.nextDouble());
        s.nextLine();
        
        /**
         * LOADER
         */
        System.out.println("Configuring the game...");
        try { TimeUnit.SECONDS.sleep(3); } catch(Exception e) {} 
        System.out.println("Randomizing the kittens. Meaouwww...");
        try { TimeUnit.SECONDS.sleep(2); } catch(Exception e) {}
        System.out.println("===================================");
        System.out.println("        LET THE GAME BEGINS        "); 
        System.out.println("===================================\n");
        
        /**
         * ROUND
         */
        game: while(true) {
            System.out.println("===================================");
            
            //Dealer bet
            int dealerBet = 1 + r.nextInt((int)bet.getDealerMoney());
            System.out.print("Dealer bet: " + dealerBet);
            bet.setDealerHandBet(dealerBet);
            
            // Player bet
            System.out.printf("\nYour bet: ");
            bet.setPlayerBet(s.nextDouble(), 1);
            s.nextLine();
            for (int i = 1; i <= currentPlayerHand; i++) {
                bet.setPlayerBet(bet.getPlayerBet(1), i);
            }
            
            //Show current dealer and player hand/s card
            showCards();
            
            //Player hand/s action
            for (int i = 1; i <= currentPlayerHand; i++) {
                //Check if deck is empty the end game
                if(d.isEmpty()) break game;
                
                playerAction(i);
            }
            
            //TODO: Show result
            System.out.println("The dealer's hand is "+Arrays.toString(player[0].getHandName())+" [Value: "+player[0].getHandValue()+"]");
            for (int i = 0; i < currentPlayerHand; i++) {
                System.out.println("Your hand: " + (i+1));
                switch(currentStatus[i]) {
                    case "bust-player":
                        System.out.println("YOU BUST!");
                        break;
                    case "win-player":
                        System.out.println("YOU WIN!");
                        break;
                    case "win-dealer":
                        System.out.println("DEALER WIN!");
                        break;
                    case "tie":
                        System.out.println("TIE!");
                        break;
                    default:
                        break;
                }
            }
            
            //Distribute cards
            newRound();
            
            /**
             * END GAME
             */
            
            //Check if both dealer and player has money to bet
            if(bet.getDealerMoney() <= 0) {
                System.out.println("***************************");
                System.out.println("YOU WIN THE GAME");
                break;
            } else if(bet.getPlayerMoney() <= 0) {
                System.out.println("***************************");
                System.out.println("DEALER WIN THE GAME");
                break;
            }
        }
        
        //Final result
        System.out.println("FINAL RESULT: ");
        System.out.println("Dealer score: " + player[0].getScore());
        System.out.println("Dealer money: " + bet.getDealerMoney());
        System.out.println("Your score: " + player[1].getScore());
        System.out.println("Your money: " + bet.getPlayerMoney());
    }
    
    private static String getCard(int n) {
        String name = "";
        switch((n % 52) % 13) {
            case 0 : name += "A"; break;
            case 10: name += "J"; break;
            case 11: name += "Q"; break;
            case 12: name += "K"; break;
            default: name += Integer.toString(((n%52)%13)+1);
        }
        switch((n%52)/13) {
            case 0: name+="♦"; break;
            case 1: name+="♣"; break;
            case 2: name+="♥"; break;
            case 3: name+="♠"; break;
        }
        return name;
    }
    
    public static void showCards() {
        //Show player and dealer cards
        System.out.println("The dealer's card is "+getCard(player[0].getHand(0))+" [Value: "+player[0].getCardValue(player[0].getHand(0))+"]");
        try {Thread.sleep(500);} catch(InterruptedException e){}

        for (int i = 1; i <= currentPlayerHand; i++) {
            System.out.printf("Your hand - %d is %s with value: %d\n", i, Arrays.toString(player[i].getHandName()), player[i].getHandValue());
            //System.out.println("Your hand is "+Arrays.toString(player[i].getHandName())+" [Value: "+player[i].getHandValue()+"]");
            try {Thread.sleep(500);} catch(InterruptedException e){}
        }

    }
    
    public static void playerAction(int hand) {
        String choice;
        boolean isStand = false;

        // choice loop: repeat until player calls stand.
        while (!isStand) {
            while (true) {
                System.out.print("Do you Hit, Stand or Split? ");
                choice = s.nextLine();
                if (choice.equalsIgnoreCase("hit") || choice.equalsIgnoreCase("stand") || choice.equalsIgnoreCase("split")) break;
                System.out.println(choice + " is not a valid choice.");
            }

            if (choice.equalsIgnoreCase("hit")) {
                System.out.println("You hit.");
                player[hand].addToHand(d.giveCard(1));
                System.out.println("Your hand is "+Arrays.toString(player[hand].getHandName())+" [Value: "+player[hand].getHandValue()+"]");
                if (player[hand].getHandValue() > 21) {
                    //System.out.println("You bust.");
                    player[0].addScore(1);
                    isStand = true;
                    bet.winRound(0);
                    currentStatus[hand-1] = "bust-player";
                }
            } else if (choice.equalsIgnoreCase("stand")) {
                System.out.println("You stand.");
                System.out.println("Your hand's value is "+player[hand].getHandValue());
                //System.out.println("The dealer's hand is "+Arrays.toString(player[0].getHandName())+" [Value: "+player[0].getHandValue()+"]");
                if (player[hand].getHandValue() > player[0].getHandValue()) {
                    //System.out.println("You win this round.");
                    player[hand].addScore(1);
                    bet.winRound(hand);
                    currentStatus[hand-1] = "win-player";
                }
                else if (player[0].getHandValue() > player[hand].getHandValue()) {
                    //System.out.println("Dealer wins this round.");
                    player[0].addScore(1);
                    bet.winRound(0);
                    currentStatus[hand-1] = "win-dealer";
                }
                else {
                    //System.out.println("It's a tie.");
                    bet.winRound(-1);
                    currentStatus[hand-1] = "tie";
                }
                isStand = true;
            } else if (choice.equalsIgnoreCase("split")) {
                if(player[hand].isCardSplittable()) split();
                else System.out.println("Card is not splittable");
            }
        }
    }
    
    public static void newRound() {
        System.out.println("Current money:");
        System.out.println(bet.toString());
        
        for (int i = 0; i <= currentPlayerHand; i++) {
            player[i].clearHand();
            player[i].addToHand(d.giveCard(2));
        }

        System.out.println("Next round...");
    }
    
    private static void split() {
        ++currentPlayerHand;
        player[currentPlayerHand] = new Player(d.giveCard(2));
        player[currentPlayerHand].setName(player[1].getName());
        bet.setPlayerBet(bet.getPlayerBet(0), currentPlayerHand);
        playerAction(currentPlayerHand);
    }
}