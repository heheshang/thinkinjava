package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class E03_Runnable2 {
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        for ( int i = 0; i < 5; i++ )
            exec.execute(new Printer());
        Thread.yield();
        exec.shutdown();
        exec = Executors.newFixedThreadPool(5);
        for ( int i = 0; i < 5; i++ )
            exec.execute(new Printer());
        Thread.yield();
        exec.shutdown();
        exec = Executors.newSingleThreadExecutor();
//        Thinking in Java, 4 th Edition Annotated Solution Guide  600
        for ( int i = 0; i < 5; i++ )
            exec.execute(new Printer());
        Thread.yield();
        exec.shutdown();
    }
}
