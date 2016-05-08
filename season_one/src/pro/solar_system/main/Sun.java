package pro.solar_system.main;

import java.awt.*;

/**
 * Created by Near on 2015/11/27.
 */
public class Sun {
    double height, width;

    double x, y;

    Image image;

    public Sun() {
    }

    public Image getImage() {
        return image;
    }

    public Sun(double x, double y, Image image) {
        height = image.getHeight(null);
        width = image.getWidth(null);
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void draw(Graphics g){
        g.drawImage(image, (int)x, (int)y, null);
    }
}
