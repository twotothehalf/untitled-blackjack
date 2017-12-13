package blackjack;

public class Betting {
    private double dealerMoney;
    private double playerMoney;
    private double dealerHandBet;
    private double playerHandBetOne;
    private double playerHandBetTwo;

    public double getDealerMoney() {
        return dealerMoney;
    }

    public void setDealerMoney(double dealerMoney) {
        this.dealerMoney = dealerMoney;
    }

    public double getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(double playerMoney) {
        this.playerMoney = playerMoney;
    }

    public double getDealerHandBet() {
        return dealerHandBet;
    }

    public void setDealerHandBet(double dealerHandBet) {
        this.dealerHandBet = dealerHandBet;
    }

    public double getPlayerHandBetOne() {
        return playerHandBetOne;
    }

    public void setPlayerHandBetOne(double playerHandBetOne) {
        this.playerHandBetOne = playerHandBetOne;
    }

    public double getPlayerHandBetTwo() {
        return playerHandBetTwo;
    }

    public void setPlayerHandBetTwo(double playerHandBetTwo) {
        this.playerHandBetTwo = playerHandBetTwo;
    }
    
    public boolean isBettable(int betAmount, String who) {
        if(who == "player") {
            return betAmount < this.playerMoney;
        } else if (who == "dealer") {
            return betAmount < this.dealerMoney;
        } else {
            Error a = new Error("Unknown person");
            throw a;
        }
    }
    
    public void winRound(String hand) {
        if(hand.equalsIgnoreCase("playerOne")) {
             this.playerMoney += this.playerHandBetOne;
             this.dealerMoney -= this.playerHandBetOne;
        } else if(hand.equalsIgnoreCase("playerTwo")) {
             this.playerMoney += this.playerHandBetTwo;
             this.dealerMoney -= this.playerHandBetTwo;
        } else if (hand.equalsIgnoreCase("dealer")) {
             this.dealerMoney += this.dealerHandBet;
             this.playerMoney -= this.dealerHandBet;
        }
        
        this.playerHandBetOne = 0.0;
        this.playerHandBetTwo = 0.0;
        this.dealerHandBet = 0.0;
    }
}
