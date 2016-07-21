package pro.plane_game.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Near on 2015/11/27.
 */
public class MyFrame extends JFrame implements Runnable {
    private Image offScreenImage = null;

    /**
     * 双缓冲技术解决屏幕闪烁
     *
     * @param g
     */
    public void update(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void init() {
        this.setTitle(Constant.Title);
        this.setSize(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        this.setLocation(420, 120);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(360);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
