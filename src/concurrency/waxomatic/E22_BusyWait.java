package concurrency.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class E22_BusyWait {
    private static volatile boolean flag;
    private static int spring;

    public static void main(String[] args) throws Exception {
        Runnable r1 = () -> {
            for ( ; ; ) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch ( InterruptedException e ) {
//                    e.printStackTrace();
                    return;
                }
                flag = true;
            }
        };

        Runnable r2 = () -> {
            for ( ; ; ) {
                while ( !flag && !Thread.currentThread().isInterrupted() ) {
                    spring++;
                }
                System.out.println("Spun " + spring + " times");
                spring = 0;
                flag = false;
                if ( Thread.interrupted() ) {
                    return;
                }
            }
        };

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(r1);
        exec.execute(r2);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }
}
