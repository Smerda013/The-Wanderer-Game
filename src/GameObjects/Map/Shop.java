package GameObjects.Map;

public class Shop {
    protected int shieldPrice;
    protected int swordPrice;
    protected int healthPotionPrice;
    protected int vitalityTrainingPrice;
    protected boolean open;
    protected int shieldUpgrade;
    protected int swordUpgrade;
    protected int healthPointUpgrade;
    protected int vitalityUpgrade;

    public Shop() {
        this.shieldPrice = 2;
        this.swordPrice = 2;
        this.vitalityTrainingPrice = 2;
        this.healthPotionPrice = 10;
        this.open=false;
        this.healthPointUpgrade = 0;
        this.shieldUpgrade = 0;
        this.swordUpgrade = 0;
        this.vitalityUpgrade = 0;
    }

    public int getShieldPrice() {
        return shieldPrice;
    }

    public int getSwordPrice() {
        return swordPrice;
    }

    public int getHealthPotionPrice() {
        return healthPotionPrice;
    }

    public int getVitalityTrainingPrice() {
        return vitalityTrainingPrice;
    }

    public boolean isOpen() {
        return open;
    }

    public int getShieldUpgrade() {
        return shieldUpgrade;
    }

    public int getSwordUpgrade() {
        return swordUpgrade;
    }

    public int getHealthPointUpgrade() {
        return healthPointUpgrade;
    }

    public int getVitalityUpgrade() {
        return vitalityUpgrade;
    }

    public int buyPotion (int coins){
        if (coins >= this.healthPotionPrice){
            this.healthPointUpgrade++;
            coins = coins - this.healthPotionPrice;
            this.healthPotionPrice = this.healthPotionPrice * 2;
            return coins;
        }
        return coins;
    }
    public int buyShield (int coins){
        if (coins >= this.shieldPrice){
            this.shieldUpgrade++;
            coins = coins - this.shieldPrice;
            this.shieldPrice = this.shieldPrice * 2;
            return coins;
        }
        return coins;
    }
    public int buySword (int coins) {
        if (coins >= this.swordPrice) {
            this.swordUpgrade++;
            coins = coins - this.swordPrice;
            this.swordPrice = this.swordPrice * 2;
            return coins;
        }
        return coins;
    }

    public int buyVitality (int coins) {
        if (coins >= this.vitalityTrainingPrice) {
            this.vitalityUpgrade+=10;
            coins = coins - this.vitalityTrainingPrice;
            this.vitalityTrainingPrice = this.vitalityTrainingPrice * 2;
            return coins;
        }
        return coins;
    }
    public void setShopStatus(boolean status){
        this.open = status;
    }
}
