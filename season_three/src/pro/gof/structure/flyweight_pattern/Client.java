package pro.gof.structure.flyweight_pattern;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by near on 2015/12/12.
 */
public class Client {
    @Test
    public void test() {
        Chess chess = ChessFactory.getChess("black");
        Chess chess2 = ChessFactory.getChess("black");
        Chess chess3 = ChessFactory.getChess("white");

        System.out.println(chess.hashCode());
        System.out.println(chess2.hashCode());
        System.out.println(chess3.hashCode());

        chess.setCoordinate(new Coordinate(1, 2));
        chess2.setCoordinate(new Coordinate(2, 1));
        chess3.setCoordinate(new Coordinate(2, 2));

        // 外部状态相对独立，不影响内部状态
        System.out.println(chess.hashCode());
        System.out.println(chess2.hashCode());
        System.out.println(chess3.hashCode());
    }
}

/**
 * FlyWeightFactory 享元工厂类
 * 创建并管理享元对象, 享元池一般设计成键值对
 */
class ChessFactory {
    // 享元池
    private static Map<String, Chess> map = new HashMap<>();

    public static Chess getChess(String chessColor) {
        Chess chess;
        if ((chess = map.get(chessColor)) == null) {
            chess = new ConcreateChess(chessColor);
            map.put(chessColor, chess);
        }
        return chess;
    }
}

/**
 * FlyWeight 抽象享元类
 * 声明公共方法，这些方法可以向外界提供对象的内部状态，同时设置外部状态
 */
interface Chess {
    String getColor();

    void setCoordinate(Coordinate coordinate);
}

/**
 * ConcreateFlyWeight 具体享元类
 * 为内部状态提供成员变量进行存储
 */
class ConcreateChess implements Chess {
    private String color;

    public ConcreateChess(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        System.out.println("棋子颜色：" + color);
        System.out.println("棋子坐标：(" + coordinate.getX() + ", " + coordinate.getY() + ")");
    }
}

/**
 * UnsharedConcreteFlyWeight 非共享享元类
 * 不能被共享的子类
 */
class Coordinate {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
