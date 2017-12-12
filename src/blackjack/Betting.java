package blackjack;

public class Betting {
    private double dealerMoney;
    private double playerMoney;
    private double currentBetDealer;
    private double currentBetPlayer;

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

    public double getCurrentBetDealer() {
        return currentBetDealer;
    }

    public void setCurrentBetDealer(double currentBetDealer) {
        this.currentBetDealer = currentBetDealer;
    }

    public double getCurrentBetPlayer() {
        return currentBetPlayer;
    }

    public void setCurrentBetPlayer(double currentBetPlayer) {
        this.currentBetPlayer = currentBetPlayer;
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
    
    public void winBet(String who) {
        if(who == "player") {
             this.playerMoney += this.currentBetPlayer;
             this.dealerMoney -= this.currentBetPlayer;
        } else if (who == "dealer") {
             this.dealerMoney += this.currentBetDealer;
             this.playerMoney -= this.currentBetDealer;
        }
        
        this.currentBetPlayer = 0.0;
        this.currentBetDealer = 0.0;
    }
}
