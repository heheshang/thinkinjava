package practice.ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/2/27 0027.
 */
public class TaskMonitor {
    public static void main(String[] args){
        // 8. 创建 ForkJoinPool 对象，名为 pool。
        ForkJoinPool pool = new ForkJoinPool();

        // 9. 创建 10，000个元素的整数 array ，名为 array。
        int array[] = new int[10000];

        // 10. 创建新的 Task 对象来处理整个array。
        Task task1 = new Task(array, 0, array.length);

        // 11. 使用 execute() 方法 把任务发送到pool里执行。
        pool.execute(task1);

        // 12. 当任务还未结束执行，调用 showLog() 方法来把 ForkJoinPool 类的状态信息写入，然后让线程休眠一秒。
        while ( !task1.isDone() ) {
            showLog(pool);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        // 13. 使用 shutdown() 方法关闭pool。
        pool.shutdown();

        // 14. 使用 awaitTermination() 方法 等待pool的终结。
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        // 15. 调用 showLog() 方法写关于 ForkJoinPool 类状态的信息并写一条信息到操控台表明结束程序。
        showLog(pool);
        System.out.printf("Main: End of the program.\n");
    }

    // 16. 实现 showLog() 方法。它接收 ForkJoinPool 对象作为参数和写关于线程和任务的执行的状态的信息。
    private static void showLog(ForkJoinPool pool){
        System.out.printf("**********************\n");
        System.out.printf("Main: Fork/Join Pool log\n");
        System.out.printf("Main: Fork/Join Pool: Parallelism:%d\n", pool.getParallelism());
        System.out.printf("Main: Fork/Join Pool: Pool Size:%d\n",
                pool.getPoolSize());
        System.out.printf("Main: Fork/Join Pool: Active Thread Count:%d\n",
                pool.getActiveThreadCount());
        System.out.printf("Main: Fork/Join Pool: Running Thread Count:%d\n",
                pool.getRunningThreadCount());
        System.out.printf("Main: Fork/Join Pool: Queued Submission:%d\n",
                pool.getQueuedSubmissionCount());
        System.out.printf("Main: Fork/Join Pool: Queued Tasks:%d\n",
                pool.getQueuedTaskCount());
        System.out.printf("Main: Fork/Join Pool: Queued Submissions:%s\n",
                pool.hasQueuedSubmissions());
        System.out.printf("Main: Fork/Join Pool: Steal Count:%d\n",
                pool.getStealCount());
        System.out.printf("Main: Fork/Join Pool: Terminated :%s\n",
                pool.isTerminated());
        System.out.printf("**********************\n");
    }

    static class Task extends RecursiveAction {

        // 2. 声明一个私有 int array 属性，名为 array，用来储存你要增加的 array 的元素。
        private int[] array;

        // 3. 声明2个私有 int 属性，名为 start 和 end，用来储存 此任务已经处理的元素块的头和尾的位置。
        private int start;
        private int end;

        // 4. 实现类的构造函数，初始化属性值。
        public Task(int array[], int start, int end){
            this.array = array;
            this.start = start;
            this.end = end;
        }

        // 5. 用任务的中心逻辑来实现 compute()
        // 方法。如果此任务已经处理了超过100任务，那么把元素集分成2部分，再创建2个任务分别来执行这些部分，使用 fork() 方法开始执行，并使用
        // join() 方法等待它的终结。
        protected void compute( ){
            if ( end - start > 100 ) {
                int mid = ( start + end ) / 2;
                Task task1 = new Task(array, start, mid);
                Task task2 = new Task(array, mid, end);

                task1.fork();
                task2.fork();

                task1.join();
                task2.join();

                // 6. 如果任务已经处理了100个元素或者更少，那么在每次操作之后让线程进入休眠5毫秒来增加元素。
            } else {
                int count = 0;
                for ( int i = start; i < end; i++ ) {
                    array[i]++;
                    count++;
                    System.out.println("如果任务已经处理了100个元素或者更少，那么在每次操作之后让线程进入休眠5毫秒来增加元素 start :" + start + " end :" + end + "差值：" + ( end - start ));

                    try {
                        Thread.sleep(5);
                    } catch ( InterruptedException e ) {
                        e.printStackTrace();
                    }
                }
                System.out.println("如果任务已经处理了100个元素或者更少，那么在每次操作之后让线程进入休眠5毫秒来增加元素 start :" + start + " end :" + end + " count：" + count);
            }
        }
    }
}
/**
 * 在这个指南，你实现了任务 使用 ForkJoinPool 类和一个扩展 RecursiveAction 类的 Task 类来增加array的元素；它是 ForkJoinPool 类执行的任务种类之一。当任务还在处理array时，你就把关于 ForkJoinPool 类的状态信息打印到操控台。
 * 你使用了ForkJoinPool类中的以下方法：
 * <p>
 * getPoolSize(): 此方法返回 int 值，它是ForkJoinPool内部线程池的worker线程们的数量。
 * getParallelism(): 此方法返回池的并行的级别。
 * getActiveThreadCount(): 此方法返回当前执行任务的线程的数量。
 * getRunningThreadCount():此方法返回没有被任何同步机制阻塞的正在工作的线程。
 * getQueuedSubmissionCount(): 此方法返回已经提交给池还没有开始他们的执行的任务数。
 * getQueuedTaskCount(): 此方法返回已经提交给池已经开始他们的执行的任务数。
 * hasQueuedSubmissions(): 此方法返回 Boolean 值，表明这个池是否有queued任务还没有开始他们的执行。
 * getStealCount(): 此方法返回 long 值，worker 线程已经从另一个线程偷取到的时间数。
 * isTerminated(): 此方法返回 Boolean 值，表明 fork/join 池是否已经完成执行。
 * 参见
 */
