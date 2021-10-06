package GameObjects.Map;

import java.util.Random;

public abstract class Enemy extends Creature{

    public  int getDP() {

        return this.DP;
    }

    public int Strike(Hero hero){
        Random randomNumber = new Random();
        int SV = this.SP + (1 +randomNumber.nextInt(7) * 2);
        if (SV > hero.getDP()){
           return hero.getStriked(SV- hero.getDP());
        }
        return 100;
    }

    public int getStriked (int damage){
        return this.currentHP = this.currentHP - damage;
    }

    public abstract boolean isKeyHolder();
}


