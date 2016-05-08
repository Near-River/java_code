package pro.plane_game.main;

import pro.plane_game.util.Constant;

import java.awt.*;

/**
 * Created by Near on 2015/11/27.
 */
public class Bullet extends GameObject {
    private double degree;

    public Bullet() {
        degree = Math.random() * Math.PI * 2;

        x = Constant.WINDOW_WIDTH / 2;
        y = Constant.WINDOW_HEIGHT / 2;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.fillOval((int) x, (int) y, width, height);

        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);

        if (y > Constant.WINDOW_HEIGHT - height || y < 30) {
            degree = -degree;
        }

        if (x < 0 || x > Constant.WINDOW_WIDTH - width) {
            degree = Math.PI - degree;
        }

        g.setColor(c);
    }

    public Bullet(int height, int width, int speed) {
        this();
        this.height = height;
        this.width = width;
        this.speed = speed;
    }

}
