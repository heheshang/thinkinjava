package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class E04_Fibonacci2 {
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        for ( int i = 1; i <= 5; i++ )
            exec.execute(new Fibonacci(i));
        Thread.yield();
        exec.shutdown();
        System.out.println("----------------------");
        exec = Executors.newFixedThreadPool(5);
        for ( int i = 1; i <= 5; i++ )
            exec.execute(new Fibonacci(i));
        Thread.yield();
        exec.shutdown();
        System.out.println("----------------------");
        exec = Executors.newSingleThreadExecutor();
        for ( int i = 1; i <= 5; i++ )
            exec.execute(new Fibonacci(i));
        Thread.yield();
        exec.shutdown();
    }

}
