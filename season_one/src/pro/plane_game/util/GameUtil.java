package pro.plane_game.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Near on 2015/11/27.
 */
public class GameUtil {
    private GameUtil() {
    }

    public static Image getImage(String context){
        BufferedImage bufferedImage = null;

        try {
            URL url = GameUtil.class.getClassLoader().getResource(context);
            bufferedImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
