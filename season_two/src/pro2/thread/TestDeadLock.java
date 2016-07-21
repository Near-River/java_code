package pro2.thread;

/**
 * 生产者-消费者模式
 * wait()：等待，释放锁
 * notify() / notifyAll()：唤醒
 * <p>
 * Created by Near on 2015/12/4.
 */
public class TestDeadLock {
    public static void main(String args[]) {
        Movie movie = new Movie();
        Player player = new Player(movie);
        Watcher watcher = new Watcher(movie);
        Thread thread = new Thread(player);
        Thread thread2 = new Thread(watcher);

        thread.start();
        thread2.start();
    }
}

class Movie {
    private String movie;
    /**
     * 信号灯
     * true-->消费者    false-->生产者
     */
    private boolean full = false;

    public synchronized void play(String movie) {
        if (full) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.movie = movie;
        System.out.println("Play: " + this.movie);

        full = true;
        this.notify();
    }

    public synchronized void watch() {
        if (!full) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Watch: " + this.movie);

        full = false;
        this.notify();
    }
}

class Player implements Runnable {
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Player(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void run() {
        while (true) {
            movie.play("《肖申克的救赎》");
        }
    }
}

class Watcher implements Runnable {
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Watcher(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void run() {
        while (true) {
            movie.watch();
        }
    }
}



