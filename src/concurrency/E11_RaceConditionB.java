package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
class SafeTank extends Tank {
    public synchronized void validate() {
        System.out.println("super.validate()");
        super.validate();
    }

    public synchronized void fill() {
        System.out.println("super.fill()");
        super.fill();
    }

    public synchronized void drain() {
        System.out.println("super.drain()");
        super.drain();
    }
}

public class E11_RaceConditionB {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        SafeTank tank = new SafeTank();
        for ( int i = 0; i < 10; i++ ) {
            exec.execute(new ConsistencyChecker(tank));
        }
        Thread.yield();
        exec.shutdown();
    }
}
