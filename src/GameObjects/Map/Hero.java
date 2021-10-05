package GameObjects.Map;

import java.util.Random;

public class Hero extends Creature {

    public Hero() {
        Random randomNumber = new Random();
        this.stepable = false;
        this.level = 1;
        this.maxHP = 20 + (3 * (1 + randomNumber.nextInt(7)));
        this.currentHP = this.maxHP;
        this.DP = 2 * (1 + randomNumber.nextInt(7));
        this.SP = 5 + (1 + randomNumber.nextInt(7));
    }

    public String Status(){
        return "Hero (Level " + this.level + ") HP: " + this.currentHP + "/" + this.maxHP + " | DP:" + this.DP + " | SP:" + this.SP;
    }

}
