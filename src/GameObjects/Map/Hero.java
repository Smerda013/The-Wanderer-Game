package GameObjects.Map;

import java.util.Random;

public class Hero extends Creature {
    protected boolean key;
    protected int potions;
    private String firstName;
    private String secondName;

    public Hero( Shop shop) {
        String[] firstName = {"Dick", "John", "George", "Mel", "Jack", "William", "Harry", "Phillip", "David", "Jackob", "Edward", "Francis", "Luke", "Lee", "Larry",
                "Simon", "Cameron", "Logan", "Shawn", "Josh", "Karl", "Michael", "Scott",};
        String[] secondName = {" Baker", " Blackbeard", " Gilmore", " Smith", " Johnson", " Williams", " Jordan", " Brown", " McDonald", " Jones", " Garcia", " Miller",
                " Davies", " Rodriguez", " Lopez", " Drake", " Thomas", " Anderson", " Lee", " Martinez", " Gonzalez", " Young", " Allen"};
        Random randomNumber = new Random();
        int name = randomNumber.nextInt(firstName.length);
        this.firstName = firstName[name];
        name = randomNumber.nextInt(secondName.length);
        this.secondName = secondName[name];
        this.stepable = false;
        this.level = 1;
        this.maxHP = 20 + (3 * (1 + randomNumber.nextInt(7))) + shop.getVitalityUpgrade();
        this.currentHP = this.maxHP;
        this.DP = 2 * (1 + randomNumber.nextInt(7)) + shop.getShieldUpgrade();
        this.SP = 5 + (1 + randomNumber.nextInt(7)) + shop.getSwordUpgrade();
        this.key=false;
        this.potions = shop.healthPointUpgrade;
    }
    public void maxHPSetter(int number){
        this.maxHP += number;
        this.currentHP = this.maxHP;
    }

    public void SPSetter(int number){
        this.SP += number;
    }

    public void DPSetter(int number){
        this.DP += number;
    }

    public void PotionSetter(int number){
        this.potions += number;
    }

    public void usePotion (){
        if (this.potions > 0){
            this.currentHP += 10;
            this.potions--;
            if (this.currentHP > this.maxHP){
                this.currentHP = this.maxHP;
            }
        }
    }

    public boolean isKey() {
        return key;
    }

    public void getKey(boolean key){
        this.key = key;
    }

    public String Status(){
        return "Wanderer " + firstName + secondName + "(Level " + this.level + ") HP: " + this.currentHP + "/" + this.maxHP + " | DP:" + this.DP + " | SP:" + this.SP;
    }

    public int getDP(){
        return this.DP;
    }

    public int getPotions() {
        return potions;
    }

    public int Strike(Enemy enemy){
        Random randomNumber = new Random();
        int SV = this.SP + (1 +randomNumber.nextInt(7) * 2);
        if (SV > enemy.getDP()){
            return enemy.getStriked(SV - enemy.getDP());
        }
        return 100;
    }

    public int getStriked(int damage){
       return this.currentHP = this.currentHP - damage;
    }

    public void levelUp(){
        Random randomNumber = new Random();
        this.level++;
        this.SP = this.SP + (1+randomNumber.nextInt(7));
        this.DP = this.DP + (1+randomNumber.nextInt(7));
        int newHP = 1 + randomNumber.nextInt(7);
        this.maxHP += newHP;
    }

    public int getCurrentHP(){
        return this.currentHP;
    }

    public void healing(){
        Random randomNumber = new Random();
        int healing = randomNumber.nextInt(101);
        if (healing <= 10 ){
            this.currentHP = this.maxHP;
        } else if (healing <= 50){
            this.currentHP = currentHP +(int) Math.ceil((double) maxHP/3);
            if (this.currentHP > this.maxHP){
                this.currentHP = this.maxHP;
            }
        }else {
            this.currentHP = currentHP + (int) Math.ceil((double) this.currentHP/ 10);
            if (this.currentHP > this.maxHP){
                this.currentHP = this.maxHP;
            }
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
