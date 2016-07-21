package pro.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 使用 Collections 工具类：
 * -- sort(List)  sort(List, Comparator)
 * -- reverse(List)  shuffle(List)
 * <p>
 * Created by Near on 2015/11/30.
 */
public class TestCollections {
    @Test
    public void test() {
        List<String> list = new ArrayList<String>();
        list.add("haha");
        list.add("xixi");
        list.add("gaga");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("*****************************************");

        // Collections.sort(list);
        // Collections.sort(list, new TestComparator<String>());

        // Collections.reverse(list);

        // 重新洗牌
        Collections.shuffle(list);

        iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        playGame();
    }

    /**
     * 模拟斗地主游戏
     */
    private void playGame() {
        List<Integer> cards = new ArrayList<Integer>();
        for (int i = 0; i < 54; i++) {
            cards.add(i + 1);
        }
        List<Integer> player1 = new ArrayList<Integer>();
        List<Integer> player2 = new ArrayList<Integer>();
        List<Integer> player3 = new ArrayList<Integer>();
        List<Integer> last = new ArrayList<Integer>();

        Collections.shuffle(cards);

        int count = 0;
        for (; count < 51; count += 3) {
            player1.add(cards.get(count));
            player2.add(cards.get(count + 1));
            player3.add(cards.get(count + 2));
        }
        while (count < 54) {
            last.add(cards.get(count++));
        }
        System.out.println("玩家一牌组：" + player1);
        System.out.println("玩家二牌组：" + player2);
        System.out.println("玩家三牌组：" + player3);
        System.out.println("底牌：" + last);
    }
}
