package blackjack;

public class Betting {
    private double dealerMoney;
    private double playerMoney;
    private double dealerHandBet;
    private double playerBet;

    public double getPlayerBet() {
        return playerBet;
    }

    public void setPlayerBet(double playerBet) {
        this.playerBet = playerBet;
    }
    
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
    
    public void winRound(int hand) {
        
        if(hand >= 1 && hand <= 4) {
            this.playerMoney += this.playerBet;
            this.dealerMoney -= this.playerBet;
        } else if (hand == 0) {
            this.dealerMoney += this.dealerHandBet;
            this.playerMoney -= this.dealerHandBet;
        }
        
        this.playerBet = 0.0;
        this.dealerHandBet = 0.0;
    }

    @Override
    public String toString() {
        return "Betting{" + "dealerMoney=" + dealerMoney + ", playerMoney=" + playerMoney + '}';
    }  
}
