package pro.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Near on 2015/11/27.
 */
public class GameFrame extends JFrame implements Runnable{
    private double x = 100, y = 100;
    private int speed = 8;
    private double degree=Math.PI/3;
    private Image image = null;

    private int _x = 1, _y = 1;

    public GameFrame() throws HeadlessException {
        image = GameUtil.getImage("pro/gui/sun.jpg");
    }

    public void init(){
        this.setTitle("Welcome");
        this.setSize(500, 500);
        this.setLocation(500, 150);
        this.setVisible(true);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        /*g.setColor(Color.yellow);
        g.fillOval(x, y, 50, 50);*/
        g.drawImage(image, (int)x, (int)y, null);
        changePosition();
    }

    public void changePosition(){
        // 做往复运动
        /*if(x >= 500-30){
            _x = -1;
        }
        if(x <= 50){
            _x = 1;
        }
        if(y >= 500-30){
            _y = -1;
        }
        if(y <= 50){
            _y = 1;
        }

        x += _x*speed;
        y += _y*speed;*/

        // 做椭圆形运转
       /* x =100+ 100*Math.cos(degree);
        y =200+ 50*Math.sin(degree);

        degree +=0.1;*/

        // 做弹珠运动
        x += speed*Math.cos(degree);
        y += speed*Math.sin(degree);

        if(y>500-30 || y<30){
            degree = -degree;
        }

        if(x<0 || x>500-30){
            degree = Math.PI-degree;
        }
    }

    @Override
    public void run() {
        while(true){
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String []args){
        GameFrame gameFrame = new GameFrame();
        Thread thread = new Thread(gameFrame);
        gameFrame.init();
        thread.start();
    }

}
