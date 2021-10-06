package Main;

import GameObjects.Map.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

    int testBoxX;
    int testBoxY;
    int saveX;
    int saveY;
    MapLvl mapLvl = new MapLvl();
    Map map = new Map(mapLvl.getLvl());
    int heroPosX;
    int heroPosY;

    public Board() {
        testBoxX = 0;
        testBoxY = 0;
        this.heroPosX = 0;
        this.heroPosY = 0;

        // set the size of your draw board
        setPreferredSize(new Dimension(720, 800));
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
        
    }

    private void drawKey(Graphics graphics, Hero hero) {
            if (hero.isKey()) {
                PositionedImage image = new PositionedImage("src/Models/Key.jpg", 680, 730);
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
        }
    }

    private void battle(Hero hero, Enemy enemy,int y, int x) {
       if (hero.Strike(enemy) < 1){
           if (enemy.isKeyHolder()){
               hero.getKey();
           }
           hero.levelUp();
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
                graphics.drawString(this.map.get(this.heroPosY - 1, this.heroPosX).Status(), 10, 790);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (this.map.get(this.heroPosY + 1, this.heroPosX) instanceof Boss) {
                graphics.drawString(this.map.get(this.heroPosY + 1, this.heroPosX).Status(), 10, 790);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (this.map.get(this.heroPosY, this.heroPosX - 1) instanceof Boss) {
                graphics.drawString(this.map.get(this.heroPosY, this.heroPosX - 1).Status(), 10, 790);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (this.map.get(this.heroPosY, this.heroPosX + 1) instanceof Boss) {
                graphics.drawString(this.map.get(this.heroPosY, this.heroPosX + 1).Status(), 10, 790);
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
                    battle((Hero) this.map.get(this.heroPosY, this.heroPosX), (Enemy) this.map.get(this.heroPosY - 1, this.heroPosX),this.heroPosY - 1,this.heroPosX);
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
            }
            try {
                if (this.map.get(this.heroPosY + 1, this.heroPosX) instanceof Enemy) {
                    battle((Hero) this.map.get(this.heroPosY, this.heroPosX), (Enemy) this.map.get(this.heroPosY + 1, this.heroPosX),this.heroPosY + 1,this.heroPosX);
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
                    battle((Hero) this.map.get(this.heroPosY, this.heroPosX), (Enemy) this.map.get(this.heroPosY, this.heroPosX + 1),this.heroPosY,this.heroPosX + 1);
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
            }
        } else if (e.getKeyCode() == KeyEvent.VK_R)
            if (map.isHeroDeath()){
                this.mapLvl = new MapLvl();
                this.map = new Map(mapLvl.getLvl());
                this.testBoxY =0;
                this.testBoxX =0;
                this.heroPosX =0;
                this.heroPosY =0;
                this.saveX =0;
                this.saveY = 0;
            }

            // and redraw to have a new picture with the new coordinates
            repaint();
        }

    }



