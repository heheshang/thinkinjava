package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
class Tank {
    private State state = State.EMPTY;
    private int current_load = 0;

    public void validate() {
        System.out.println("validate");
        if ( ( state == State.EMPTY && current_load != 0 )
                || ( state == State.LOADED && current_load == 0 ) ) {
            throw new IllegalStateException();
        }
    }

    public void fill() {
        System.out.println("fill");
        state = State.LOADED;
        Thread.yield();
        current_load = 10;
    }

    public void drain() {
        System.out.println("drain");
        state = State.EMPTY;
        Thread.yield();
        current_load = 0;
    }

    enum State {
        EMPTY, LOADED
    }
}

class ConsistencyChecker implements Runnable {
    private static Random rnd = new Random();
    private Tank tank;

    public ConsistencyChecker(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void run() {
        for ( ; ; ) {
            if ( rnd.nextBoolean() ) {
                tank.fill();
            } else {
                tank.drain();
            }
            tank.validate();
        }
    }
}

public class E11_RaceCondition {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        Tank tank = new Tank();
        for ( int i = 0; i < 10; i++ ) {
            exec.execute(new ConsistencyChecker(tank));
        }
        Thread.yield();
        exec.shutdown();
    }
}
