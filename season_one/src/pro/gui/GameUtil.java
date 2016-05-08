package pro.gui;

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

        Image image = Toolkit.getDefaultToolkit().getImage(GameUtil.class.getClassLoader().getResource(context));
        return image;

        /*
        BufferedImage bufferedImage = null;

        try {
            URL url = GameUtil.class.getClassLoader().getResource(context);
            System.out.println(url);
            bufferedImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
        */
    }
}
