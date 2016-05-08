package pro.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Near on 2015/11/27.
 */
public class TestFrame extends JFrame {
    public void init(){
        this.setTitle("Play Game!!!");
        this.setSize(500, 500);
        this.setLocation(500, 150);
        this.setVisible(true);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        // super.paint(g);
        // g.draw3DRect(50, 50, 30, 30, true);
        g.setColor(Color.green);
        g.fill3DRect(50, 50, 30, 30, true);
        g.setColor(Color.yellow);
        g.fillOval(100, 100, 50, 50);
        g.setColor(Color.black);
        g.drawString("Hello", 300, 300);
    }

    public static void main(String []args){
        TestFrame gameFrame = new TestFrame();
        gameFrame.init();
    }
}
