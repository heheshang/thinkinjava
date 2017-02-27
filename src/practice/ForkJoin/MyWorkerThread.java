package practice.ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * 类MyWorkerThread.java的功能描述:Fork/Join框架是Java7中最有趣的特征之一。它是Executor和ExecutorService接口的一个实现，允许你执行Callable和Runnable任务而不用管理这些执行线程。
 * <p>
 * 这个执行者面向执行能被拆分成更小部分的任务。主要组件如下：
 * <p>
 * 一个特殊任务，实现ForkJoinTask类
 * 两种操作，将任务划分成子任务的fork操作和等待这些子任务结束的join操作
 * 一个算法，优化池中线程的使用的work-stealing算法。当一个任务正在等待它的子任务（结束）时，它的执行线程将执行其他任务（等待执行的任务）。
 * ForkJoinPool类是Fork/Join的主要类。在它的内部实现，有如下两种元素：
 * <p>
 * 一个存储等待执行任务的列队。
 * 一个执行任务的线程池
 * 在这个指南中，你将学习如何实现一个在ForkJoinPool类中使用的自定义的工作者线程，及如何使用一个工厂来使用它。
 *
 * @author ssk 2017-02-27 下午 5:02 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
public class MyWorkerThread extends ForkJoinWorkerThread {
    //2.声明和创建一个参数化为Integer类的ThreadLocal属性，名为taskCounter。
    private static ThreadLocal<Integer> taskCounter = new ThreadLocal<Integer>();
    //3.实现这个类的构造器。

    /**
     * Creates a ForkJoinWorkerThread operating in the given pool.
     *
     * @param pool the pool this thread works in
     * @throws NullPointerException if pool is null
     */
    protected MyWorkerThread(ForkJoinPool pool){
        super(pool);
    }

    //4.重写onStart()方法。调用父类的这个方法，写入一条信息到控制台。设置当前线程的taskCounter属性值为0。
    @Override
    protected void onStart( ){
        super.onStart();
        System.out.printf("MyWorkerThread %d: Initializing task counter.\n", getId());
        taskCounter.set(0);
    }

    //5.重写onTermination()方法。写入当前线程的taskCounter属性值到控制台。
    @Override
    protected void onTermination(Throwable exception){
        System.out.printf("MyWorkerThread %d: %d\n", getId(), taskCounter.get());
        super.onTermination(exception);
    }

    //6.实现addTask()方法。递增taskCounter属性值。
    public void addTask( ){
        int counter = taskCounter.get().intValue();
        counter++;
        taskCounter.set(counter);
    }


}

