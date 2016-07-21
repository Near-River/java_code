package pro.sort;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 对新闻进行排序：
 * 发布日期 --> 点击量 --> 新闻标题
 * <p>
 * Created by Near on 2015/11/30.
 */
public class NewsDemo {
    @Test
    public void test() {
        List<News> list = new ArrayList<News>();
        list.add(new News("Java", 101, new Date(1000)));
        list.add(new News("C++", 10, new Date()));
        list.add(new News("Java", 201, new Date(1000)));
        list.add(new News("Java", 201, new Date(1000 * 60 * 60 * 24)));
        list.add(new News("JavaScript", 201, new Date(1000)));

        Collections.sort(list);

        Iterator<News> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        /*
        TreeSet<News> treeSet= new TreeSet<News>();
        treeSet.add(new News("Java", 101, new Date(1000)));
        treeSet.add(new News("C++", 10, new Date()));
        treeSet.add(new News("JavaScript", 201, new Date(1000)));
        treeSet.add(new News("Java", 201, new Date(1000)));
        treeSet.add(new News("Java", 201, new Date(1000*60*60*24)));

        iterator = treeSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        */
    }
}

class News implements Comparable<News> {
    private String title;
    private int count;
    private Date publish;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getPublish() {
        return publish;
    }

    public void setPublish(Date publish) {
        this.publish = publish;
    }

    public News() {
    }

    public News(String title, int count, Date publish) {
        this.title = title;
        this.count = count;
        this.publish = publish;
    }

    @Override
    public String toString() {
      /*  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(publish);*/
        return "News{" +
                "title='" + title + '\'' +
                ", count=" + count +
                ", publish=" + new SimpleDateFormat("yyyy-MM-dd").format(publish) +
                '}';
    }

    @Override
    public int compareTo(News news) {
        int result = 0;
        result = news.getPublish().compareTo(this.getPublish());
        if (result == 0) {
            result = news.getCount() - this.getCount();
            if (result == 0) {
                result = this.getTitle().compareTo(news.getTitle());
            }
        }
        return result;
    }
}
