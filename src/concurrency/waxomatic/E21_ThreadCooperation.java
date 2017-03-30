package concurrency.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

class Coop1 implements Runnable {
    public Coop1() {
        print("Constructed coop1");
    }

    @Override
    public void run() {
        print("coop1 going into wait");
        synchronized ( this ) {
            try {
                wait();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
        print("coop1 exited wait");
    }
}

class Coop2 implements Runnable {
    Runnable otherTask;

    public Coop2(Runnable otherTask) {
        this.otherTask = otherTask;
        print("Constructed Coop2");
    }

    @Override
    public void run() {
        print("Coop2 pausing  5 seconds");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        print("Coop2 Calling notifyAll");
        synchronized ( otherTask ) {
            otherTask.notifyAll();
        }
    }
}

public class E21_ThreadCooperation {
    public static void main(String[] args) {
        Runnable coop1 = new Coop1(),
                coop2 = new Coop2(coop1);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(coop1);
        exec.execute(coop2);
        Thread.yield();
        exec.shutdown();
    }
}
