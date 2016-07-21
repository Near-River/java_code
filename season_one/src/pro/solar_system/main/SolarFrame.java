package pro.solar_system.main;

import pro.solar_system.util.Constant;
import pro.solar_system.util.GameUtil;
import pro.solar_system.util.MyFrame;

import java.awt.*;

/**
 * Created by Near on 2015/11/27.
 */
public class SolarFrame extends MyFrame {
    private Image background = null;
    private Sun sun = null;
    private Planet earth = null;
    private Planet moon = null;
    private Planet mars = null;

    public SolarFrame() {
        background = GameUtil.getImage(Constant.BgImage);

        sun = new Sun(Constant.WINDOW_WIDTH / 2, Constant.WINDOW_HEIGHT / 2, GameUtil.getImage(Constant.SunImage));
        earth = new Planet(150, 100, 0.1, 0, sun, GameUtil.getImage(Constant.EarthImage));
        moon = new Planet(30, 20, 0.3, 0, earth, GameUtil.getImage(Constant.MoonImage));
        mars = new Planet(200, 130, 0.2, 0, sun, GameUtil.getImage(Constant.MarsImage));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, null);
        sun.draw(g);
        earth.draw(g);
        moon.draw(g);
        mars.draw(g);
    }

    public static void main(String[] args) {
        SolarFrame solarFrame = new SolarFrame();
        Thread thread = new Thread(solarFrame);
        solarFrame.init();
        thread.start();
    }
}
