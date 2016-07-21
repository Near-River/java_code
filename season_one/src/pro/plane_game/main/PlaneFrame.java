package pro.plane_game.main;

import pro.plane_game.util.Constant;
import pro.plane_game.util.GameUtil;
import pro.plane_game.util.MyFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Near on 2015/11/27.
 */
public class PlaneFrame extends MyFrame {
    private Image background = null;
    private Plane plane = null;
    private Explode explode = null;
    private ArrayList<Bullet> bulletList = null;
    private Date startTime, endTime;

    public PlaneFrame() {
        background = GameUtil.getImage(Constant.BgImage);
        Image planeImg = GameUtil.getImage(Constant.PlaneImage);
        plane = new Plane(60, 60, 12, planeImg);
        bulletList = new ArrayList<Bullet>();

        // 初始化一些子弹对象
        for (int i = 0; i < 21; i++) {
            Bullet bullet = new Bullet(10, 10, 8);
            bulletList.add(bullet);
        }

        startTime = new Date();

        this.addKeyListener(new KeyMonitor());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, null);
        if (plane.isAlive()) {
            plane.draw(g);
        }

        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            bullet.draw(g);

            boolean isAttach = false;
            if (plane.isAlive()) {
                isAttach = bullet.getRect().intersects(plane.getRect());
            }

            if (isAttach) {
                System.out.println(isAttach);
                plane.setIsAlive(false);

                endTime = new Date();
                explode = new Explode(plane.x, plane.y);
                explode.draw(g);
                break;
            }

            if (!plane.isAlive()) {
                int period = (int) ((endTime.getTime() - startTime.getTime()) / 1000);
                printInfo(g, "时间：" + period + "秒", 20, 120, 260, Color.white);

                switch (period / 10) {
                    case 0:
                    case 1:
                        printInfo(g, "菜鸟", 50, 100, 200, Color.white);
                        break;
                    case 2:
                        printInfo(g, "大师", 50, 100, 200, Color.white);
                }
            }
        }
    }

    public void printInfo(Graphics g, String str, int size, int x, int y, Color color) {
        Color c = g.getColor();
        g.setColor(color);
        Font f = new Font("宋体", Font.BOLD, size);
        g.setFont(f);
        g.drawString(str, x, y);
        g.setColor(c);
    }

    class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            plane.changeDirection(e, true);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.changeDirection(e, false);
        }

    }

    public static void main(String[] args) {
        PlaneFrame planeFrame = new PlaneFrame();
        Thread thread = new Thread(planeFrame);
        planeFrame.init();
        thread.start();
    }
}
