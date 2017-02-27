package practice.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 类MyRecursiveTask.java的功能描述:8.创建MyRecursiveTask类，它继承一个参数化为Integer类的RecursiveTask类。
 * MyRecursiveTask 按照字面意思的理解就递归执行的任务，按照这个思路，
 * 要计算数组1-10000的和，可以用二分法依次执行子数组的和。
 * 而每一个和运算，都可以在单独的线程中执行，fork（）通知executor运行task的compute方法，join()等待运行结束并返回运行结果，
 * 相当于把一个任务分散到多个任务中执行，再进行聚合。例子中ForkJoinPool一共用到了四个线程，
 * 当又新的MyRecursiveTask需要执行时，这四个线程就会被重复利用。
 * @author ssk 2017-02-27 下午 5:07 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {
    //9.声明一个私有的、int类型的属性array。
    private int array[];
    //10.声明两个私有的、int类型的属性start和end。
    private int start, end;

    //11.实现这个类的构造器，初始化它的属性。
    public MyRecursiveTask(int[] array, int start, int end){
        this.array = array;
        this.start = start;
        this.end = end;
    }



    //12.实现compute()方法，用来合计数组中在start和end位置之间的所有元素。首先，将执行这个任务的线程转换成一个MyWorkerThread对象，然后使用addTask()方法来增长这个线程的任务计数器。
    @Override
    protected Integer compute( ){
        MyWorkerThread thread = ( MyWorkerThread ) Thread.currentThread();
        thread.addTask();
        if ( start == end ) {
            return array[start];
        }
        int middle = start + ( end - start ) / 2;
        MyRecursiveTask task1 = new MyRecursiveTask(array, start, middle);
        MyRecursiveTask task2 = new MyRecursiveTask(array, middle + 1, end);
        int result = addResults(task1, task2);
        return result;
    }

    private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2){
        int value;
//        task1.fork();
//        task2.fork();
//        value = task1.join() + task2.join();
        try {
            value = task1.get().intValue() + task2.get().intValue();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
            value = 0;
        } catch ( ExecutionException e ) {
            e.printStackTrace();
            value = 0;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        return value;
    }
}
