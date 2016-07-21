package pro.solar_system.util;

import java.awt.*;

/**
 * Created by Near on 2015/11/27.
 */
public class GameUtil {
    private GameUtil() {
    }

    public static Image getImage(String context) {
        Image image = Toolkit.getDefaultToolkit().getImage(GameUtil.class.getClassLoader().getResource(context));
        return image;
    }
}
