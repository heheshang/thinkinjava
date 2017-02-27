package practice.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/2/27 0027.
 */
public class Main {
    public static void main(String[] args){
        //16.创建一个名为factory的MyWorkerThreadFactory对象。
        MyWorkerThreadFactory factory = new MyWorkerThreadFactory();
        //17.创建一个名为pool的ForkJoinPool对象，将前面创建的factory对象作为参数传给它的构造器。
        ForkJoinPool pool = new ForkJoinPool(4, factory, null, false);
        //18.创建一个大小为100000的整数数组，将所有元素初始化为值1。
        int array[] = new int[100];
        for ( int i = 0; i < array.length; i++ ) {
            array[i] = i;
        }
        //19.创建一个新的Task对象，用来合计数组中的所有元素。
        MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);
        //20.使用execute()方法，将这个任务提交给池。
        pool.execute(task);
        //21.使用join()方法，等待这个任务的结束。
        task.join();
        //22.使用shutdown()方法，关闭这个池。
        pool.shutdown();
        //23.使用awaitTermination()方法，等待这个执行者的结束。
        try {
            pool.awaitTermination(1, TimeUnit.MINUTES);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        //24.使用get()方法，将任务的结束写入到控制台。
        try {
            System.out.printf("Main: Result: %d\n", task.get());
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        } catch ( ExecutionException e ) {
            e.printStackTrace();
        }
        // 25.写入一条信息到控制台，表明程序的结束。
        System.out.printf("Main: End of the program\n");

    }
}
