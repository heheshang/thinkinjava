package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/27 0027.
 */
class NonTask {
    static void longMethod() throws InterruptedException {
        TimeUnit.SECONDS.sleep(50);
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        try {
            NonTask.longMethod();
        } catch ( InterruptedException e ) {
            System.out.println(e.toString());
        } finally {
        }
    }
}

public class E18_Interruption {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Task());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}
