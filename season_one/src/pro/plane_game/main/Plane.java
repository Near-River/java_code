package pro.plane_game.main;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Near on 2015/11/27.
 */
public class Plane extends GameObject {
    private boolean left, up, right, down;

    private boolean isAlive = true;

    private Image image;

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void draw(Graphics g) {
        if (isAlive) {
            g.drawImage(image, (int) x, (int) y, null);
            move();
        }
    }

    public void move() {
        if (left) {
            x -= speed;
        }
        if (right) {
            x += speed;
        }
        if (up) {
            y -= speed;
        }
        if (down) {
            y += speed;
        }
    }

    public void changeDirection(KeyEvent e, boolean status) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = status;
                break;
            case KeyEvent.VK_UP:
                up = status;
                break;
            case KeyEvent.VK_RIGHT:
                right = status;
                break;
            case KeyEvent.VK_DOWN:
                down = status;
                break;
        }
    }

    public Plane() {
    }

    public Plane(double x, double y, int speed, Image image) {
        super(x, y, speed);
        this.image = image;
        height = image.getHeight(null);
        width = image.getWidth(null);
    }
}
