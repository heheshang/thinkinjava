package practice.ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 类SearchNumberTask.java的功能描述:7.实现SearchNumberTask类，指定它继承参数化为Integer类型的RecursiveTask类。这个类将查找在整数数组的元素块中的数。
 *
 * @author ssk 2017-02-27 下午 3:57 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
public class SearchNumberTask extends RecursiveTask<Integer> {
    //12.声明一个私有的、int类型的常量并初始化它为值-1。当任务没有找到这个数时，它将作为任务的返回值。
    private final static int NOT_FOUND = -1;
    //8.声明一个私有的、int类型的数字数组。
    private int numbers[];
    //9.声明两个私有的、int类型的属性start和end。这些属性将决定任务要处理的数组的元素。
    private int start, end;
    //10.声明一个私有的、int类型的属性number，它将存储你将要查找的数。
    private int number;
    //11.声明一个私有的、TaskManager类型的属性manager。你将使用这个对象来取消所有任务。
    private TaskManager manager;

    //13.实现这个类的构造器来初始化它的属性
    public SearchNumberTask(int[] numbers, int start, int end, int number, TaskManager manager){
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.manager = manager;
    }

    public static void main(String[] args){
        //28.使用ArrayGenerator类，创建一个有1000个数字的数组。
        ArrayGenerator generator = new ArrayGenerator();
        int array[] = generator.generateArray(1000);
        //29.创建一个TaskManager对象。
        TaskManager manager = new TaskManager();

        //30.使用默认的构造器创建一个ForkJoinPool对象。
        ForkJoinPool pool = new ForkJoinPool();

        //31.创建一个Task对象来处理前面生成的数组。
        SearchNumberTask task = new SearchNumberTask(array, 0, 1000, 5, manager);
        //32.使用execute()方法，在池中异步执行任务
        pool.execute(task);
        //33.使用shutdown()方法关闭这个池。
        pool.shutdown();
        //34.使用ForkJoinPool类的awaitTermination()方法，等待任务的结束。
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        System.out.printf("Main: The program has finished\n");

    }

    //14.实现compute()方法。写入一条信息（start和end属性值）到控制台表明这个方法的开始。
    @Override
    protected Integer compute( ){
        System.out.println("Task: " + start + ":" + end);
        //15.如果start和end之差大于10（这个任务将处理超过10个元素的数组），调用launchTasks()方法，将这个任务的工作拆分成两个任务。
        int ret;
        if ( end - start > 10 ) {
            ret = launchTasks();
        } else {
            ret = lookForNumber();
        }
        return ret;
    }

    //18.实现lookForNumber()方法。
    private int lookForNumber( ){
        //19.对于任务要处理的元素块中的所有元素，将你想要查找的数与存储在元素中的值进行比较。如果他们相等，写入一条信息到控制台表明这种情形，使用TaskManager对象的cancelTasks()方法来取消所有任务，并返回你已经找到的这个数对应元素的位置。
        for ( int i = start; i < end; i++ ) {
            if ( numbers[i] == number ) {
                System.out.printf("Task: Number %d found in position %d\n", number, i);
                manager.cancleTasks(this);
                return i;
            }
            //20.在循环的内部，令任务睡眠1秒。
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
        return NOT_FOUND;
    }

    private int launchTasks( ){
        int mid = ( start + end ) / 2;
        SearchNumberTask task1 = new SearchNumberTask(numbers, start, mid, number, manager);
        SearchNumberTask task2 = new SearchNumberTask(numbers, mid, end, number, manager);
        //23.添加这个任务到TaskManager对象中。
        manager.addTask(task1);
        manager.addTask(task2);
        //24.使用fork()方法异步执行这两个任务。
        task1.fork();
        task2.fork();
        //25.等待这个任务的结束，返回第一个任务的结果（如果它不等于1），或第二个任务的结果。
        int returnValue;
        returnValue = task1.join();
        if ( returnValue != -1 ) {
            return returnValue;
        }
        returnValue = task2.join();
        return returnValue;

    }

    //26.实现writeCancelMessage()方法，当任务取消时，写一条信息到控制台。
    public void writeCancelMessage( ){
        System.out.printf("Task: Canceled task from %d to %d \n", start, end);
    }
}
