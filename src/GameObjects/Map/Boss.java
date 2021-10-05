package GameObjects.Map;

import java.util.Random;

public class Boss extends Enemy {

    public Boss() {
        Random randomNumber = new Random();
        this.stepable = false;
        this.level = 1;
        this.maxHP = 2 * this.level * (1 + randomNumber.nextInt(7)) + (1 + randomNumber.nextInt(7));
        this.currentHP = this.maxHP;
        this.DP = (int) Math.ceil((double) this.level/2)*(1 + randomNumber.nextInt(7)) +((int) Math.ceil((double) 1 + randomNumber.nextInt(7))/2);
        this.SP = this.level * (1 + randomNumber.nextInt(7)) + this.level;
    }

    @Override
    public String Status() {
        return "Boss (Level " + this.level + ") HP: " + this.currentHP + "/" + this.maxHP + " | DP:" + this.DP + " | SP:" + this.SP;
    }
}
