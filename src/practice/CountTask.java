package practice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 类CountTask.java的功能描述:3. Fork/Join框架的介绍
 * <p>
 * 我们已经很清楚Fork/Join框架的需求了，那么我们可以思考一下，如果让我们来设计一个Fork/Join框架，该如何设计？这个思考有助于你理解Fork/Join框架的设计。
 * <p>
 * 第一步分割任务。首先我们需要有一个fork类来把大任务分割成子任务，有可能子任务还是很大，所以还需要不停的分割，直到分割出的子任务足够小。
 * <p>
 * 第二步执行任务并合并结果。分割的子任务分别放在双端队列里，然后几个启动线程分别从双端队列里获取任务执行。子任务执行完的结果都统一放在一个队列里，启动一个线程从队列里拿数据，然后合并这些数据。
 * <p>
 * Fork/Join使用两个类来完成以上两件事情：
 * <p>
 * ForkJoinTask：我们要使用ForkJoin框架，必须首先创建一个ForkJoin任务。它提供在任务中执行fork()和join()操作的机制，通常情况下我们不需要直接继承ForkJoinTask类，而只需要继承它的子类，Fork/Join框架提供了以下两个子类：
 * RecursiveAction：用于没有返回结果的任务。
 * RecursiveTask ：用于有返回结果的任务。
 * ForkJoinPool ：ForkJoinTask需要通过ForkJoinPool来执行，任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头部。当一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列的尾部获取一个任务。
 * 4. 使用Fork/Join框架
 * <p>
 * 让我们通过一个简单的需求来使用下Fork／Join框架，需求是：计算1+2+3+4的结果。
 * <p>
 * 使用Fork／Join框架首先要考虑到的是如何分割任务，如果我们希望每个子任务最多执行两个数的相加，那么我们设置分割的阈值是2，由于是4个数字相加，所以Fork／Join框架会把这个任务fork成两个子任务，子任务一负责计算1+2，子任务二负责计算3+4，然后再join两个子任务的结果。
 * <p>
 * 因为是有结果的任务，所以必须继承RecursiveTask，实现代码如下：
 *
 * @author ssk 2017-02-24 下午 4:27 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute( ){
        int sum = 0;
        //如果任务足够小就计算任务
        boolean canCompute = ( end - start ) <= THRESHOLD;
        if ( canCompute ) {
            for ( int i = start; i < end; i++ ) {
                sum += i;
            }
        } else {
            //如果任务大于阈值，就分裂成两个子任务
            int middle = ( start + end ) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            //等待子任务执行完，并得到结果

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            //合并子任务
            sum = leftResult + rightResult;

        }
        return sum;
    }

    public static void main(String[] args){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生成一个计算任务负责计算1+2+3+4
        CountTask task = new CountTask(1,4);
        //执行任务
        Future<Integer> result = forkJoinPool.submit(task);

        try {
            System.out.println(result.get());
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        } catch ( ExecutionException e ) {
            e.printStackTrace();
        }
    }
}
