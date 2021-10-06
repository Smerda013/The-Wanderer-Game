package GameObjects.Map;

import java.util.Random;

public class Hero extends Creature {
    protected boolean key;

    public Hero() {
        Random randomNumber = new Random();
        this.stepable = false;
        this.level = 1;
        this.maxHP = 20 + (3 * (1 + randomNumber.nextInt(7)));
        this.currentHP = this.maxHP;
        this.DP = 2 * (1 + randomNumber.nextInt(7));
        this.SP = 5 + (1 + randomNumber.nextInt(7));
        this.key=false;
    }

    public boolean isKey() {
        return key;
    }

    public void getKey(){
        this.key = true;
    }

    public String Status(){
        return "Hero (Level " + this.level + ") HP: " + this.currentHP + "/" + this.maxHP + " | DP:" + this.DP + " | SP:" + this.SP;
    }

    public int getDP(){
        return this.DP;
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
        this.currentHP +=newHP;
    }

    public int getCurrentHP(){
        return this.currentHP;
    }

}
