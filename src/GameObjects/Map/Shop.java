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
            this.healthPotionPrice = this.healthPotionPrice * this.healthPotionPrice;
            return coins;
        }
        return 0;
    }
    public int buyShield (int coins){
        if (coins >= this.shieldPrice){
            this.shieldUpgrade++;
            coins = coins - this.shieldPrice;
            this.shieldPrice = this.shieldPrice * this.shieldPrice;
            return coins;
        }
        return 0;
    }
    public int buySword (int coins) {
        if (coins >= this.swordPrice) {
            this.swordUpgrade++;
            coins = coins - this.swordPrice;
            this.shieldPrice = this.swordPrice * this.swordPrice;
            return coins;
        }
        return 0;
    }

    public int buyVitality (int coins) {
        if (coins >= this.vitalityTrainingPrice) {
            this.vitalityUpgrade++;
            coins = coins - this.vitalityTrainingPrice;
            this.vitalityTrainingPrice = this.vitalityTrainingPrice * this.vitalityTrainingPrice;
            return coins;
        }
        return 0;
    }
}
