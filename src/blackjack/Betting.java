package blackjack;

public class Betting {
    private double dealerMoney;
    private double playerMoney;
    private double dealerHandBet;
    private double playerHandOneBet;
    private double playerHandTwoBet;
    private double playerHandThreeBet;
    private double playerHandFourBet;

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

    public double getPlayerHandOneBet() {
        return playerHandOneBet;
    }

    public void setPlayerHandOneBet(double playerHandOneBet) {
        this.playerHandOneBet = playerHandOneBet;
    }

    public double getPlayerHandTwoBet() {
        return playerHandTwoBet;
    }

    public void setPlayerHandTwoBet(double playerHandTwoBet) {
        this.playerHandTwoBet = playerHandTwoBet;
    }

    public double getPlayerHandThreeBet() {
        return playerHandThreeBet;
    }

    public void setPlayerHandThreeBet(double playerHandThreeBet) {
        this.playerHandThreeBet = playerHandThreeBet;
    }

    public double getPlayerHandFourBet() {
        return playerHandFourBet;
    }

    public void setPlayerHandFourBet(double playerHandFourBet) {
        this.playerHandFourBet = playerHandFourBet;
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
             this.playerMoney += this.playerHandOneBet;
             this.dealerMoney -= this.playerHandOneBet;
        } else if(hand.equalsIgnoreCase("playerTwo")) {
             this.playerMoney += this.playerHandTwoBet;
             this.dealerMoney -= this.playerHandTwoBet;
        } else if(hand.equalsIgnoreCase("playerThree")) {
             this.playerMoney += this.playerHandThreeBet;
             this.dealerMoney -= this.playerHandThreeBet;
        } else if(hand.equalsIgnoreCase("playerFour")) {
             this.playerMoney += this.playerHandFourBet;
             this.dealerMoney -= this.playerHandFourBet;
        } else if (hand.equalsIgnoreCase("dealer")) {
             this.dealerMoney += this.dealerHandBet;
             this.playerMoney -= this.dealerHandBet;
        }
        
        this.playerHandOneBet = 0.0;
        this.playerHandTwoBet = 0.0;
        this.dealerHandBet = 0.0;
    }

    @Override
    public String toString() {
        return "Betting{" + "dealerMoney=" + dealerMoney + ", playerMoney=" + playerMoney + '}';
    }    
}
