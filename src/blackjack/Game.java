package blackjack;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Game {
    // Maximum possible hand size in blackjack: 11 cards
    public static void main(String[] args) {
        Deck d = new Deck();
        Scanner s = new Scanner(System.in);
        Player dealer = new Player(d.giveCard(2));
        Player p = new Player(d.giveCard(2));
        Betting bet = new Betting();
        
        //Dealer and Player
        System.out.print("Dealer name:");
        dealer.setName(s.nextLine());
        System.out.print("Dealer money:");
        bet.setDealerMoney(s.nextDouble());
        s.nextLine();
        System.out.print("Player name:");
        p.setName(s.nextLine());
        System.out.print("Player money:");
        bet.setPlayerMoney(s.nextDouble());
        s.nextLine();
        
        System.out.println("Configuring the game...");
        try { TimeUnit.SECONDS.sleep(3); } catch(Exception e) {} 
        System.out.println("Randomizing the kittens. Meaouwww...");
        try { TimeUnit.SECONDS.sleep(2); } catch(Exception e) {}
        System.out.println("===================================");
        System.out.println("        LET THE GAME BEGINS        "); 
        System.out.println("===================================\n");
        
        do {
            System.out.println("===================================");
            System.out.print("Your bet: ");
            bet.setPlayerHandOneBet(s.nextDouble());
            System.out.print("Dealer bet: ");
            bet.setDealerHandBet(s.nextDouble());
            s.nextLine();
            
            System.out.println("The dealer's card is "+getCard(dealer.getHand(0))+" [Value: "+dealer.getCardValue(dealer.getHand(0))+"]");
            try {Thread.sleep(500);} catch(InterruptedException e){}
            System.out.println("Your hand is "+Arrays.toString(p.getHandName())+" [Value: "+p.getHandValue()+"]");
            try {Thread.sleep(500);} catch(InterruptedException e){}
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
                    p.addToHand(d.giveCard(1));
                    System.out.println("Your hand is "+Arrays.toString(p.getHandName())+" [Value: "+p.getHandValue()+"]");
                    if (p.getHandValue() > 21) {
                        System.out.println("You bust.");
                        dealer.addScore(1);
                        isStand = true;
                        bet.winRound("dealer");
                    }
                } else if (choice.equalsIgnoreCase("stand")) {
                    System.out.println("You stand.");
                    System.out.println("Your hand's value is "+p.getHandValue());
                    System.out.println("The dealer's hand is "+Arrays.toString(dealer.getHandName())+" [Value: "+dealer.getHandValue()+"]");
                    if (p.getHandValue() > dealer.getHandValue()) {
                        System.out.println("You win this round.");
                        p.addScore(1);
                        bet.winRound("playerOne");
                    }
                    else if (dealer.getHandValue() > p.getHandValue()) {
                        System.out.println("Dealer wins this round.");
                        dealer.addScore(1);
                        bet.winRound("dealer");
                    }
                    else {
                        System.out.println("It's a tie.");
                        bet.winRound("tie");
                    }
                    isStand = true;
                } else if (choice.equalsIgnoreCase("split")) {
                    // PROBLEM: how to split?
                }
            }
            System.out.println("Current money:");
            System.out.println(bet.toString());
            p.clearHand();
            dealer.clearHand();
            
            //Check if both dealer and player has money to bet
            if(bet.getDealerMoney() <= 0) {
                System.out.println("YOU WIN THE GAME");
                break;
            } else if(bet.getPlayerMoney() <= 0) {
                System.out.println("DEALER WIN THE GAME");
                break;
            }
            
            System.out.println("Next round...");
            p.addToHand(d.giveCard(2));
            dealer.addToHand(d.giveCard(2));
        } while (!d.isEmpty());
        System.out.println("FINAL RESULT: ");
        System.out.println("Dealer: "+dealer.getScore());
        System.out.println("Dealer money: " + bet.getDealerMoney());
        System.out.println("You: "+p.getScore());
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
}