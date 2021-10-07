package GameObjects.Map;

import java.util.Random;

public class Skeleton extends Enemy{
    protected boolean keyHolder;

    public Skeleton(int mapLvl) {
        Random randomNumber = new Random();
        int monsterLvl =randomNumber.nextInt(101);
        if (monsterLvl <= 10){
            this.level = mapLvl + 2;
        } else if (monsterLvl <= 50){
            this.level = mapLvl + 1;
        } else {
            this.level = mapLvl;
        }
        this.stepable = false;
        this.maxHP = 2 * this.level * (1 + randomNumber.nextInt(7)) + (1 + randomNumber.nextInt(7));
        this.currentHP = this.maxHP;
        this.DP = (int) Math.ceil((double) this.level/2)*(1 + randomNumber.nextInt(7)) + (int) Math.ceil((double) 1 + randomNumber.nextInt(7))/2;
        this.SP = this.level * (1 + randomNumber.nextInt(7));
        this.keyHolder = false;
    }

    public Skeleton(int mapLvl, boolean keyHolder) {
        Random randomNumber = new Random();
        int monsterLvl =randomNumber.nextInt(101);
        if (monsterLvl <= 10){
            this.level = mapLvl + 2;
        } else if (monsterLvl <= 50){
            this.level = mapLvl + 1;
        } else {
            this.level = mapLvl;
        }
        this.stepable = false;
        this.maxHP = 2 * this.level * (1 + randomNumber.nextInt(7)) + (1 + randomNumber.nextInt(7));
        this.currentHP = this.maxHP;
        this.DP = (int) Math.ceil((double) this.level/2)*(1 + randomNumber.nextInt(7)) + (int) Math.ceil((double) 1 + randomNumber.nextInt(7))/2;
        this.SP = this.level * (1 + randomNumber.nextInt(7));
        this.SP = 0;
        this.keyHolder = keyHolder;
    }

    @Override
    public String Status() {
        return "Skeleton (Level " + this.level + ") HP: " + this.currentHP + "/" + this.maxHP + " | DP:" + this.DP + " | SP:" + this.SP;
    }

    @Override
    public int getCurrentHP() {
        return this.currentHP;
    }

    public boolean isKeyHolder(){
        return this.keyHolder;
    }
}
