package pro.plane_game.main;

import java.awt.*;

/**
 * 提取出游戏中的飞机对象和子弹对象的共有属性和方法
 * Created by Near on 2015/11/27.
 */
public class GameObject {
    double x, y;
    int height, width;
    int speed;

    public Rectangle getRect(){
        return  new Rectangle((int)x,(int)y, width, height);
    }

    public GameObject() {
    }

    public GameObject(double x, double y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
}
