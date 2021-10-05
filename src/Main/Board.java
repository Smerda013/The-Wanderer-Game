package Main;

import GameObjects.Map.Floor;
import GameObjects.Map.Map;
import GameObjects.Map.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

    int testBoxX;
    int testBoxY;
    int saveX;
    int saveY;
    Map map = new Map();
    int heroPosX;
    int heroPosY;

    public Board() {
        testBoxX = 0;
        testBoxY = 0;
        this.heroPosX = 0;
        this.heroPosY = 0;

        // set the size of your draw board
        setPreferredSize(new Dimension(720, 720));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        // here you have a 720x720 canvas
        // you can create and draw an image using the class below e.g.
        int x = 0, y = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.map.get(i,j) instanceof Floor) {
                    PositionedImage image = new PositionedImage("src/Models/gif/floor.gif", x, y);
                    image.draw(graphics);
                } else if (this.map.get(i,j) instanceof Wall){
                    PositionedImage image = new PositionedImage("src/Models/gif/wall.gif", x, y);
                    image.draw(graphics);
                } else {
                    PositionedImage image = new PositionedImage("src/Models/gif/floor.gif", x, y);
                    image.draw(graphics);
                    drawHero(graphics);
                }
                x+=72;
            }
            x=0;
            y+=72;
        }
    }

    private void drawHero(Graphics graphics) {
        if (this.saveX< this.testBoxX) {
            PositionedImage hero = new PositionedImage("src/Models/gif/hero-right.gif", testBoxX, testBoxY);
            hero.draw(graphics);
            this.saveY = this.testBoxY;
            this.saveX = this.testBoxX;
        } else if (this.saveX> this.testBoxX) {
            PositionedImage hero = new PositionedImage("src/Models/gif/hero-left.gif", testBoxX, testBoxY);
            hero.draw(graphics);
            this.saveY = this.testBoxY;
            this.saveX = this.testBoxX;
        }else if (this.saveY > this.testBoxY) {
            PositionedImage hero = new PositionedImage("src/Models/gif/hero-up.gif", testBoxX, testBoxY);
            hero.draw(graphics);
            this.saveY = this.testBoxY;
            this.saveX = this.testBoxX;
        }else {
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
                this.map.moveUp(this.heroPosY,this.heroPosX);
                this.heroPosY -= 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            if (testBoxY < 648 && this.map.get(this.heroPosY + 1, this.heroPosX) instanceof Floor) {
                testBoxY += 72;
                this.map.moveDown(this.heroPosY,this.heroPosX);
                this.heroPosY +=1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            if (testBoxX > 0 && this.map.get(this.heroPosY, this.heroPosX - 1) instanceof Floor) {
                testBoxX -= 72;
                this.map.moveLeft(this.heroPosY,this.heroPosX);
                this.heroPosX -=1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            if (testBoxX < 648 && this.map.get(this.heroPosY, this.heroPosX +1) instanceof Floor) {
                testBoxX += 72;
                this.map.moveRight(this.heroPosY,this.heroPosX);
                this.heroPosX +=1;
            }
        }
        // and redraw to have a new picture with the new coordinates
        repaint();

    }
}