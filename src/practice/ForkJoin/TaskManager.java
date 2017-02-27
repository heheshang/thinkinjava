package practice.ForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * 类TaskManager.java的功能描述:2.创建一个TaskManager类。我们将使用这个类来存储在ForkJoinPool中执行的所有任务。由于ForkJoinPool和ForkJoinTask类的局限性，你将使用这个类来取消ForkJoinPool类的所有任务。
 *
 * @author ssk 2017-02-27 下午 3:51 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
public class TaskManager {
    //3.声明一个对象参数化为ForkJoinTask类型的数列，其中ForkJoinTask类参数化为Integer类型。
    private List<ForkJoinTask<Integer>> tasks;

    //4.实现这个类的构造器，它初始化任务数列。
    public TaskManager( ){
        tasks = new ArrayList<>();
    }

    //5.实现addTask()方法。它添加ForkJoinTask对象到任务数列。
    public void addTask(ForkJoinTask<Integer> task){
        tasks.add(task);
    }

    //6.实现cancelTasks()方法。它将使用cancel()方法取消在数列中的所有ForkJoinTask对象。它接收一个想要取消剩余任务的ForkJoinTask对象作为参数。这个方法取消所有任务。
    public void cancleTasks(ForkJoinTask<Integer> cancleTask){
        for ( ForkJoinTask<Integer> task : tasks ) {
            if ( task != cancleTask ) {
                task.cancel(true);
                ( ( SearchNumberTask ) task ).writeCancelMessage();

            }
        }
    }
}
