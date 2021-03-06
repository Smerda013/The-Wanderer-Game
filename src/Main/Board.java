package Main;

import GameObjects.Map.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Board extends JComponent implements KeyListener {

    int testBoxX;
    int testBoxY;
    int saveX;
    int saveY;
    int coins;
    MapLvl mapLvl = new MapLvl();
    Shop shop = new Shop();
    Map map = new Map(mapLvl.getLvl(),this.shop,true);
    int heroPosX;
    int heroPosY;
    int cutsceneCounter;
    String firstName;
    String seCondName;

    public Board() {
        coins = 0;
        testBoxX = 0;
        testBoxY = 0;
        this.heroPosX = 0;
        this.heroPosY = 0;
        this.firstName = this.map.getHero().getFirstName();
        this.seCondName = this.map.getHero().getSecondName();

        // set the size of your draw board
        setPreferredSize(new Dimension(720, 810));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        // here you have a 720x720 canvas
        // you can create and draw an image using the class below e.g.
        drawMap(graphics);
        drawHeroStatus(graphics);
        drawBossStatus(graphics);
        drawSkeletonStatus(graphics);
        gameOver(graphics);
        try {
            drawKey(graphics,(Hero) this.map.get(this.map.heroY(),this.map.heroX()));
        } catch (Exception e){}
        try {
            drawNextLvl(graphics,(Hero) this.map.get(this.map.heroY(),this.map.heroX()));
        }catch (ClassCastException e){}
            drawCoins(graphics);
        try {
            drawPotion(graphics);
        }catch (NullPointerException e){}
        drawShop(graphics);
        drawCutscene(graphics);
        drawDialogue(graphics);

    }

    private void drawDialogue(Graphics graphics) {
        if (this.map.isTitle() && !this.shop.isOpen()){
            graphics.setColor(Color.WHITE);
            graphics.fillRect(25,685,650,30);
            Random randomNumber = new Random();
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Times Romans", Font.PLAIN, 15));
            if (this.mapLvl.getLvl() == 1){
                int chooser = randomNumber.nextInt(10);
                if (chooser == 0){
                    graphics.drawString("Let me introduce you " +this.firstName + this.seCondName + ". He is wanderer of the high quality. " ,30,705);
                } else if (chooser == 1){
                    graphics.drawString("Wanderer " + this.firstName + this.seCondName + " came to smash it! Just stay out of his way!",30,705);
                } else if (chooser == 2){
                    graphics.drawString("I know, that the other wanderers was disappointment, but " + this.firstName + this.seCondName + " is different!",30,705);
                }else if (chooser == 3){
                    graphics.drawString("Here he is! The best of the bests :" + this.firstName + this.seCondName+ ". Please clap your hands!", 30,705);
                }else if (chooser == 4){
                    graphics.drawString("I hope, that " + this.firstName + this.seCondName + " is not just another weak coward.",30,705);
                }else if (chooser == 5){
                    graphics.drawString("Oh my god! Is it him? " + this.firstName + this.seCondName + " Is real celebrity!",30,705);
                }else if (chooser == 6){
                    graphics.drawString("I hope that this unmoving enemies wil not hurt " + this.firstName + this.seCondName + " much.",30,705);
                }else if (chooser == 7){
                    graphics.drawString("And the story of wanderer " + this.firstName + this.seCondName + " begins! I'm so excited!",30,705);
                }else if (chooser == 8){
                    graphics.drawString("Bring your beer and popcorn! " + this.firstName + this.seCondName + " wil be on the fire!",30,705);
                }else {
                    graphics.drawString("It is another one? I already forget to count... Lets go" + this.firstName + this.seCondName + "!",30,705);
                }
            } else if (this.mapLvl.getLvl() == 5) {
                int chooser = randomNumber.nextInt(6);
                if (chooser == 0) {
                    graphics.drawString("And " + this.firstName + this.seCondName + " is on the half way. I'm curious if he can make it! ", 30, 705);
                } else if (chooser == 1) {
                    graphics.drawString("Wanderer " + this.firstName + this.seCondName + " is doing great so far! I'm betting that he will make it!", 30, 705);
                } else if (chooser == 2) {
                    graphics.drawString("Sorry what is happening? I was on the bathroom. Is " + this.firstName + this.seCondName + " already dead?", 30, 705);
                } else if (chooser == 3) {
                    graphics.drawString("One beast after another! " + this.firstName + this.seCondName + " will have beautiful collection of heads.", 30, 705);
                } else if (chooser == 4) {
                    graphics.drawString("Yes! Yes! Common my beloved " + this.firstName + this.seCondName + ". Hope you can give me an autograph!", 30, 705);
                } else {
                    graphics.drawString(this.firstName + this.seCondName + " is really promising and i feel really good energy from him.", 30, 705);
                }

            }else if (this.mapLvl.getLvl() == 7) {
                int chooser = randomNumber.nextInt(4);
                if (chooser == 0) {
                    graphics.drawString(this.firstName + this.seCondName + " is really close to the end of the crypt! Can he make it? Stay tuned!", 30, 705);
                } else if (chooser == 1) {
                    graphics.drawString("Did you see that? " + this.firstName + this.seCondName + " just smash them like god dammit flies!", 30, 705);
                } else if (chooser == 2) {
                    graphics.drawString("And the story of wanderer " + this.firstName + this.seCondName + " ends... wait what, he is still alive!?", 30, 705);
                } else {
                    graphics.drawString("I'm your father " + this.firstName + this.seCondName + "! Just kidding! Did you see his face? LOL!", 30, 705);
                }
            } else if (this.mapLvl.getLvl() == 10){
                graphics.drawString(this.firstName + this.seCondName + " made it to the final floor of the crypt! Can he face the last challenge?", 30, 705);
            }
        }
    }

    private void drawCutscene(Graphics graphics) {
        if (this.map.isCutScene()) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 720, 720, 90);
            if (this.mapLvl.getLvl() == 1) {
                if (cutsceneCounter == 0) {
                    PositionedImage image = new PositionedImage("src/Models/forest.jpg", 0, 0);
                    image.draw(graphics);
                    graphics.setColor(Color.WHITE);
                    graphics.setFont(new Font("Times Roman", Font.PLAIN, 25));
                    graphics.drawString("In the dark deep forest in the middle of no man's land...", 40, 705);
                } else if (cutsceneCounter == 1) {
                    PositionedImage image = new PositionedImage("src/Models/Crypt.jpg", 0, 0);
                    image.draw(graphics);
                    graphics.setColor(Color.WHITE);
                    graphics.setFont(new Font("Times Roman", Font.PLAIN, 25));
                    graphics.drawString("...lies the dangerous crypt, where none leaves alive...", 45, 705);
                } else if (cutsceneCounter == 2) {
                    PositionedImage image = new PositionedImage("src/Models/title.jpg", 0, 0);
                    image.draw(graphics);
                    graphics.setColor(Color.WHITE);
                    graphics.setFont(new Font("Times Roman", Font.PLAIN, 25));
                    graphics.drawString("... until new hero comes to the scene!", 130, 705);
                    graphics.setColor(Color.BLACK);
                    graphics.fillRect(130, 120, 380, 20);
                    graphics.setColor(Color.YELLOW);
                    graphics.setFont(new Font("Times Roman", Font.PLAIN, 50));
                    graphics.drawString("Wanderer", 220, 80);
                }
            } else if (this.mapLvl.getLvl() == 11) {
                if (cutsceneCounter == 0) {
                    PositionedImage image = new PositionedImage("src/Models/Death.jpg", 0, 0);
                    image.draw(graphics);
                    graphics.setColor(Color.BLACK);
                    graphics.setFont(new Font("Times Roman", Font.PLAIN, 25));
                    graphics.drawString("Wherever " + this.firstName + this.seCondName + " comes,", 5, 60);
                    graphics.drawString("he brought just dead.", 5, 90);
                    graphics.drawString("Why? Asking I. Is he hero or villain? ", 5, 120);
                    graphics.drawString("Will we ever know?", 5, 150);
                } else if (cutsceneCounter == 1) {
                    PositionedImage image = new PositionedImage("src/Models/treasure.jpg", 0, 0);
                    image.draw(graphics);
                    graphics.setColor(Color.WHITE);
                    graphics.setFont(new Font("Times Roman", Font.PLAIN, 25));
                    graphics.drawString("What we know, that he got a big treasure.", 5, 60);
                    graphics.drawString("And he is taking it back to home!", 5, 90);
                    graphics.setColor(Color.RED);
                    graphics.drawString("Wait! Did you hear that! Sound of the opening doors...", 5, 700);
                } else if (cutsceneCounter == 2) {
                    graphics.setColor(Color.BLACK);
                    graphics.fillRect(0,0,720,720);
                    graphics.setFont(new Font("Times Roman", Font.PLAIN, 70));
                    graphics.setColor(Color.WHITE);
                    graphics.drawString("CONGRATULATION",30,140);
                    graphics.setFont(new Font("Times Roman", Font.PLAIN, 30));
                    graphics.drawString("You just finish the game!",190,200);
                    graphics.drawString("And " + this.firstName + this.seCondName + " become a master of the crypt.",30,250);
                    graphics.drawString("But don't worry, his path is not at the end",80,550);
                    graphics.drawString("You can continue to move into secret entrance.",50,600);


                }
            }
        }
    }

    private void drawShop(Graphics graphics) {
        if (this.shop.isOpen()){
            graphics.setColor(Color.BLACK);
            graphics.fillRect(60, 100, 600, 500);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 120));
            graphics.drawString("STORE",150,205);
            PositionedImage image1 = new PositionedImage("src/Models/sword.jpg", 100, 270);
            image1.draw(graphics);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 18));
            graphics.drawString("SWORD (SP+1)",82,400);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 15));
            graphics.drawString("Price: " + this.shop.getSwordPrice() + " Coins",93,425);
            graphics.drawString("For buy press \"1\"",93,450);
            if (this.coins < this.shop.getSwordPrice()){
                graphics.setFont(new Font("Times Roman", Font.PLAIN, 12));
                graphics.setColor(Color.RED);
                graphics.drawString("NOT ENOUGH MONEY!",75,475);
            }
            PositionedImage image2 = new PositionedImage("src/Models/shield.jpg", 240, 270);
            image2.draw(graphics);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 18));
            graphics.drawString("SHIELD (DP+1)",225,400);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 15));
            graphics.drawString("Price: " + this.shop.getShieldPrice() + " Coins",233,425);
            graphics.drawString("For buy press \"2\"",233,450);
            if (this.coins < this.shop.getShieldPrice()){
                graphics.setFont(new Font("Times Roman", Font.PLAIN, 12));
                graphics.setColor(Color.RED);
                graphics.drawString("NOT ENOUGH MONEY!",220,475);
            }
            PositionedImage image3 = new PositionedImage("src/Models/hearth.jpg", 380, 270);
            image3.draw(graphics);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 18));
            graphics.drawString("VITALITY (HP+1)",360,400);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 15));
            graphics.drawString("Price: " + this.shop.getVitalityTrainingPrice() + " Coins",373,425);
            graphics.drawString("For buy press \"3\"",373,450);
            if (this.coins < this.shop.getVitalityTrainingPrice()) {
                graphics.setFont(new Font("Times Roman", Font.PLAIN, 12));
                graphics.setColor(Color.RED);
                graphics.drawString("NOT ENOUGH MONEY!", 360, 475);
            }
            PositionedImage image4 = new PositionedImage("src/Models/PotionBigger.png", 520, 270);
            image4.draw(graphics);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 18));
            graphics.drawString("POTION",535,400);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 15));
            graphics.drawString("Price: " + this.shop.getHealthPotionPrice() + " Coins",513,425);
            graphics.drawString("For buy press \"4\"",513,450);
            if (this.coins < this.shop.getHealthPotionPrice()) {
                graphics.setFont(new Font("Times Roman", Font.PLAIN, 12));
                graphics.setColor(Color.RED);
                graphics.drawString("NOT ENOUGH MONEY!", 500, 475);
            }
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 30));
            graphics.drawString("Press ENTER to exit the shop",150,550);
        }

    }

    private void drawPotion(Graphics graphics) {
        PositionedImage image = new PositionedImage("src/Models/Potion.jpg", 100, 768);
        image.draw(graphics);
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Times Roman", Font.PLAIN, 25));
        graphics.drawString("X" + this.map.getHero().getPotions(),145,790);
    }

    private void drawCoins(Graphics graphics) {
        PositionedImage image = new PositionedImage("src/Models/coin.jpg", 5, 768);
        image.draw(graphics);
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Times Roman", Font.PLAIN, 25));
        graphics.drawString("X" + this.coins,50,790);
    }

    private void drawNextLvl(Graphics graphics, Hero hero) {
        if (hero.isKey() && this.map.isBossDeath()){
            graphics.setColor(Color.BLACK);
            graphics.fillRect(120,680,450,30);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Times Roman", Font.PLAIN, 20));
            graphics.drawString("Congratulation! For move to the next level press X ", 130, 700);
        }
    }

    private void drawKey(Graphics graphics, Hero hero) {
            if (hero.isKey()) {
                PositionedImage image = new PositionedImage("src/Models/Key.jpg", 680, 740);
                image.draw(graphics);
            }
        }


    private void drawHeroStatus(Graphics graphics){
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Times Roman", Font.PLAIN, 20));
        try {
            graphics.drawString(map.get(this.heroPosY, this.heroPosX).Status(), 10, 740);
        } catch (NullPointerException e){}
    }

    private void drawMap (Graphics graphics){
        int x = 0, y = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.map.get(i, j) instanceof Floor) {
                    PositionedImage image = new PositionedImage("src/Models/gif/floor.gif", x, y);
                    image.draw(graphics);
                } else if (this.map.get(i, j) instanceof Wall) {
                    PositionedImage image = new PositionedImage("src/Models/gif/wall.gif", x, y);
                    image.draw(graphics);
                } else if (this.map.get(i, j) instanceof Hero) {
                    PositionedImage image = new PositionedImage("src/Models/gif/floor.gif", x, y);
                    image.draw(graphics);
                    drawHero(graphics);
                } else if (this.map.get(i, j) instanceof Skeleton) {
                    PositionedImage image = new PositionedImage("src/Models/gif/floor.gif", x, y);
                    image.draw(graphics);
                    drawSkeleton(graphics, x, y);
                } else {
                    PositionedImage image = new PositionedImage("src/Models/gif/floor.gif", x, y);
                    image.draw(graphics);
                    drawBoss(graphics, x, y);
                }
                x += 72;
            }
            x = 0;
            y += 72;
        }
    }

    private void gameOver (Graphics graphics){
        if (this.map.isHeroDeath()) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(60, 300, 600, 145);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Times Romans", Font.PLAIN, 80));
            graphics.drawString("Game Over!", 140, 375);
            graphics.setFont(new Font("Times Romans", Font.PLAIN, 35));
            graphics.drawString("For new game press R", 180, 420);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(25,685,650,30);
            Random randomNumber = new Random();
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Times Romans", Font.PLAIN, 15));
            int chooser = randomNumber.nextInt(10);
            if (chooser == 0){
                graphics.drawString("Wait what happened? Wanderer " +this.firstName + this.seCondName + " should survive!",30,705);
            } else if (chooser == 1){
                graphics.drawString("Wanderer " + this.firstName + this.seCondName + " was real disappointment!",30,705);
            } else if (chooser == 2){
                graphics.drawString("Another dead? I'm not going to tell this to wanderer's " + this.firstName + this.seCondName + " family!",30,705);
            }else if (chooser == 3){
                graphics.drawString("Ou really? I put my money on wanderer " + this.firstName + this.seCondName,30,705);
            }else if (chooser == 4){
                graphics.drawString("Yes I understand... Wanderer " + this.firstName + this.seCondName + " was just the weakeling.",30,705);
            }else if (chooser == 5){
                graphics.drawString("Oh my god! Wanderer " + this.firstName + this.seCondName + " was so strong and he still died?",30,705);
            }else if (chooser == 6){
                graphics.drawString("Ouch! That must hurt... I don't want to be in the skin of wanderer " + this.firstName + this.seCondName + "!",30,705);
            }else if (chooser == 7){
                graphics.drawString("And the story of wanderer " + this.firstName + this.seCondName + " is over! What a shame...",30,705);
            }else if (chooser == 8){
                graphics.drawString("Hopefully next one will be better than wanderer " + this.firstName + this.seCondName + ", because this was horrible!",30,705);
            }else {
                graphics.drawString("Booooooooooring! Wanderer " + this.firstName + this.seCondName + " deserves to die!",30,705);
            }
        }
    }

    private void battle(Hero hero, Enemy enemy,int y, int x) {
       if (hero.Strike(enemy) < 1){
           if (enemy.isKeyHolder()){
               hero.getKey(true);
           }
           Random randomNumber = new Random();
           coins = coins + (1 + randomNumber.nextInt(4));
           map.enemyDeath(y,x);
       }
        if (enemy.Strike(hero) < 1){
            map.heroDeath(this.heroPosY,this.heroPosX);
        }


    }


    private void drawSkeletonStatus(Graphics graphics) {
        try {
            if (this.map.get(this.heroPosY - 1, this.heroPosX) instanceof Skeleton) {
                graphics.drawString(this.map.get(this.heroPosY - 1, this.heroPosX).Status(), 10, 765);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (this.map.get(this.heroPosY + 1, this.heroPosX) instanceof Skeleton) {
                graphics.drawString(this.map.get(this.heroPosY + 1, this.heroPosX).Status(), 10, 765);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (this.map.get(this.heroPosY, this.heroPosX - 1) instanceof Skeleton) {
                graphics.drawString(this.map.get(this.heroPosY, this.heroPosX - 1).Status(), 10, 765);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (this.map.get(this.heroPosY, this.heroPosX + 1) instanceof Skeleton) {
                graphics.drawString(this.map.get(this.heroPosY, this.heroPosX + 1).Status(), 10, 765);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    private void drawBossStatus(Graphics graphics) {
        try {
            if (this.map.get(this.heroPosY - 1, this.heroPosX) instanceof Boss) {
                graphics.drawString(this.map.get(this.heroPosY - 1, this.heroPosX).Status(), 10, 765);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (this.map.get(this.heroPosY + 1, this.heroPosX) instanceof Boss) {
                graphics.drawString(this.map.get(this.heroPosY + 1, this.heroPosX).Status(), 10, 765);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (this.map.get(this.heroPosY, this.heroPosX - 1) instanceof Boss) {
                graphics.drawString(this.map.get(this.heroPosY, this.heroPosX - 1).Status(), 10, 765);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (this.map.get(this.heroPosY, this.heroPosX + 1) instanceof Boss) {
                graphics.drawString(this.map.get(this.heroPosY, this.heroPosX + 1).Status(), 10, 765);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }


    }


    private void drawBoss(Graphics graphics, int x, int y) {
        PositionedImage skeleton = new PositionedImage("src/Models/boss.png", x, y);
        skeleton.draw(graphics);
    }

    private void drawSkeleton(Graphics graphics, int x, int y) {
        PositionedImage skeleton = new PositionedImage("src/Models/skeleton.png", x, y);
        skeleton.draw(graphics);
    }

    private void drawHero(Graphics graphics) {
        if (this.saveX < this.testBoxX) {
            PositionedImage hero = new PositionedImage("src/Models/gif/hero-right.gif", testBoxX, testBoxY);
            hero.draw(graphics);
            this.saveY = this.testBoxY;
            this.saveX = this.testBoxX;
        } else if (this.saveX > this.testBoxX) {
            PositionedImage hero = new PositionedImage("src/Models/gif/hero-left.gif", testBoxX, testBoxY);
            hero.draw(graphics);
            this.saveY = this.testBoxY;
            this.saveX = this.testBoxX;
        } else if (this.saveY > this.testBoxY) {
            PositionedImage hero = new PositionedImage("src/Models/gif/hero-up.gif", testBoxX, testBoxY);
            hero.draw(graphics);
            this.saveY = this.testBoxY;
            this.saveX = this.testBoxX;
        } else {
            PositionedImage hero = new PositionedImage("src/Models/gif/hero-down.gif", testBoxX, testBoxY);
            hero.draw(graphics);
            this.saveY = this.testBoxY;
            this.saveX = this.testBoxX;
        }
    }


    public static void main(String[] args) {
        // Here is how you set up a new window and adding our board to it
        JFrame frame = new JFrame("RPG Game");
        Board board = new Board();
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        // Here is how you can add a key event listener
        // The board object will be notified when hitting any key
        // with the system calling one of the below 3 methods
        frame.addKeyListener(board);
        // Notice (at the top) that we can only do this
        // because this Board class (the type of the board object) is also a KeyListener
    }

    // To be a KeyListener the class needs to have these 3 methods in it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    // But actually we can use just this one for our goals here
    @Override
    public void keyReleased(KeyEvent e) {
        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            if (testBoxY > 0 && this.map.get(this.heroPosY - 1, this.heroPosX) instanceof Floor) {
                testBoxY -= 72;
                this.map.moveUp(this.heroPosY, this.heroPosX);
                this.heroPosY -= 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            if (testBoxY < 648 && this.map.get(this.heroPosY + 1, this.heroPosX) instanceof Floor) {
                testBoxY += 72;
                this.map.moveDown(this.heroPosY, this.heroPosX);
                this.heroPosY += 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            if (testBoxX > 0 && this.map.get(this.heroPosY, this.heroPosX - 1) instanceof Floor) {
                testBoxX -= 72;
                this.map.moveLeft(this.heroPosY, this.heroPosX);
                this.heroPosX -= 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            if (testBoxX < 648 && this.map.get(this.heroPosY, this.heroPosX + 1) instanceof Floor) {
                testBoxX += 72;
                this.map.moveRight(this.heroPosY, this.heroPosX);
                this.heroPosX += 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            try {
                if (this.map.get(this.heroPosY - 1, this.heroPosX) instanceof Enemy) {
                    battle((Hero) this.map.get(this.heroPosY, this.heroPosX), (Enemy) this.map.get(this.heroPosY - 1, this.heroPosX), this.heroPosY - 1, this.heroPosX);
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
            }
            try {
                if (this.map.get(this.heroPosY + 1, this.heroPosX) instanceof Enemy) {
                    battle((Hero) this.map.get(this.heroPosY, this.heroPosX), (Enemy) this.map.get(this.heroPosY + 1, this.heroPosX), this.heroPosY + 1, this.heroPosX);
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
            }
            try {
                if (this.map.get(this.heroPosY, this.heroPosX - 1) instanceof Enemy) {
                    battle((Hero) this.map.get(this.heroPosY, this.heroPosX), (Enemy) this.map.get(this.heroPosY, this.heroPosX - 1), this.heroPosY, this.heroPosX - 1);
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
            }
            try {
                if (this.map.get(this.heroPosY, this.heroPosX + 1) instanceof Enemy) {
                    battle((Hero) this.map.get(this.heroPosY, this.heroPosX), (Enemy) this.map.get(this.heroPosY, this.heroPosX + 1), this.heroPosY, this.heroPosX + 1);
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
            }
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            if (map.isHeroDeath()) {
                this.mapLvl = new MapLvl();
                this.map = new Map(mapLvl.getLvl(),this.shop,false);
                this.firstName = this.map.getHero().getFirstName();
                this.seCondName = this.map.getHero().getSecondName();
                this.map.setTitle(true);
                this.testBoxY = 0;
                this.testBoxX = 0;
                this.heroPosX = 0;
                this.heroPosY = 0;
                this.saveX = 0;
                this.saveY = 0;
                this.shop.setShopStatus(true);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_X){
            if (this.map.isBossDeath() && this.map.getHero().isKey()) {
                this.map.getHero().levelUp();
                this.map.getHero().healing();
                this.map.getHero().getKey(false);
                this.mapLvl.setLvl(this.mapLvl.getLvl() + 1);
                if (this.mapLvl.getLvl() == 10){
                    this.map = new FinalMap(mapLvl.getLvl(), this.map.saveHero(this.heroPosY, this.heroPosX));
                } else {
                    this.map = new Map(mapLvl.getLvl(), this.map.saveHero(this.heroPosY, this.heroPosX));
                }
                if (this.mapLvl.getLvl() == 5 || this.mapLvl.getLvl() == 7 || this.mapLvl.getLvl() == 10){
                    this.map.setTitle(true);
                }
                if (this.mapLvl.getLvl() == 2){
                    this.map.setCutScene(true);
                }
                if (this.mapLvl.getLvl() == 10){
                    this.testBoxY = 5*72;
                } else {
                    this.testBoxY = 0;
                }
                this.testBoxX = 0;
                this.heroPosX = 0;
                if (this.mapLvl.getLvl() == 10) {
                    this.heroPosY = 5;
                } else {
                    this.heroPosY = 0;
                }
                this.saveX = 0;
                this.saveY = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_P){
            if (!this.map.isHeroDeath()){
                this.map.getHero().usePotion();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.shop.isOpen()) {
                this.shop.setShopStatus(false);
            }else if (this.map.isCutScene()){
                cutsceneCounter++;
                if (cutsceneCounter == 3){
                    cutsceneCounter = 0;
                    this.map.setCutScene(false);
                    this.map.setTitle(true);

                }
            }else if (this.map.isTitle()){
                this.map.setTitle(false);
            }

        } else if (e.getKeyCode() == KeyEvent.VK_1){
            if (this.shop.isOpen()){
                if (coins >= this.shop.getSwordPrice()) {
                    coins = this.shop.buySword(this.coins);
                    this.map.getHero().SPSetter(1);
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_2){
            if (this.shop.isOpen()){
                if (coins >= this.shop.getShieldPrice()) {
                    coins = this.shop.buyShield(coins);
                    this.map.getHero().DPSetter(1);
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            if (this.shop.isOpen()) {
                if (coins >= this.shop.getVitalityTrainingPrice()) {
                    coins = this.shop.buyVitality(coins);
                    this.map.getHero().maxHPSetter(10);
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            if (this.shop.isOpen()) {
                if (coins >= this.shop.getHealthPotionPrice()) {
                    coins = this.shop.buyPotion(coins);
                    this.map.getHero().PotionSetter(1);
                }
            }
        }
            // and redraw to have a new picture with the new coordinates
            repaint();
        }

    }



