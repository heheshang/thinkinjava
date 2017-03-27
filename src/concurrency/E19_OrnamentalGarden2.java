package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/**
 * Created by Administrator on 2017/3/27 0027.
 */
class Entrance2 implements Runnable {
    private static Count count = new Count();
    private static List<Entrance2> entrance2s = new ArrayList<>();
    private final int id;
    private int number;

    public Entrance2(int id) {
        this.id = id;
        entrance2s.add(this);
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for ( Entrance2 entrance2 : entrance2s ) {
            sum += entrance2.getValue();
        }
        return sum;
    }

    @Override
    public void run() {
        for ( ; ; ) {
            synchronized ( this ) {
                ++number;
            }

            print(this + "Total:" + count.increment());

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch ( InterruptedException e ) {
                System.out.println("Stoping " + this);
                return;
            }
        }
    }

    public synchronized int getValue() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance2{" +
                "number=" + getValue() +
                ", id=" + id +
                '}';
    }
}

public class E19_OrnamentalGarden2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for ( int i = 0; i < 5; i++ ) {
            exec.execute(new Entrance2(i));
        }
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
        if ( !exec.awaitTermination(250, TimeUnit.MILLISECONDS) ) {
            print("some task were not terminated!");
        }
        print("Total :" + Entrance2.getTotalCount());
        print("Sum of Entrances:" + Entrance2.sumEntrances());
    }
}
