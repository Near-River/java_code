package pro.solar_system.main;

import java.awt.*;

/**
 * Created by Near on 2015/11/27.
 */
public class Planet extends Sun{
    private double longAxis, shortAxis;
    private double speed;
    private double degree;
    Sun center;

    double ovalX,ovalY,ovalWidth,ovalHeight;

    public Planet() {
    }

    public Planet(double longAxis, double shortAxis, double speed, double degree, Sun center, Image image) {
        this.center = center;
        x = x + longAxis;
        y = center.y;

        this.longAxis = longAxis;
        this.shortAxis = shortAxis;
        this.degree = degree;
        this.speed = speed;

        this.image = image;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public void draw(Graphics g){
        super.draw(g);
        move();
        if(center.getClass().getName().endsWith("Sun")){
            drawTrace(g);
        }
    }

	public void drawTrace(Graphics g){
        ovalWidth = longAxis*2;
        ovalHeight = shortAxis*2;
        ovalX = (center.x+center.width/2)-longAxis;
        ovalY = (center.y+center.height/2)-shortAxis;

		Color c =g.getColor();
		g.setColor(Color.blue);
		g.drawOval((int)ovalX, (int)ovalY, (int)ovalWidth, (int)ovalHeight);
		g.setColor(c);
	}

	public void move(){
		// 椭圆运行轨迹
		x = (center.x+center.width/2) + longAxis*Math.cos(degree);
		y = (center.y+center.height/2)+ shortAxis*Math.sin(degree);
		degree += speed;
	}
}
