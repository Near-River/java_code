package pro.plane_game.main;

import pro.plane_game.util.GameUtil;

import java.awt.*;

/**
 * Created by Near on 2015/11/27.
 */
public class Explode {
    double x, y;
    int count;

    static Image[] imgs = new Image[16];

    static {
        for (int i = 0; i < 16; i++) {
            imgs[i] = GameUtil.getImage("pro/plane_game/imgs/explode/e" + (i + 1) + ".gif");
            // 防止懒加载
            imgs[i].getWidth(null);
        }
    }

    public void draw(Graphics g) {
        if (count < imgs.length) {
            g.drawImage(imgs[count], (int) x, (int) y, null);
            count += 1;
        }
    }

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
