package practice.ForkJoin;

import EDU.oswego.cs.dl.util.concurrent.FJTask;
import EDU.oswego.cs.dl.util.concurrent.FJTaskRunnerGroup;

/**
 * 类TestForkJoinTask.java的功能描述:这个版本在第4节中所提到的平台上的运行速度至少比每个任务都在Thread类中运行快30倍。在保持性能的同时这个程序仍然维持着Java多线程程序的可移植性。对程序员来说通常有两个参数值的他们关注：
 * <p>
 * 对于工作线程的创建数量，通常情况下可以与平台所拥有的处理器数量保持一致（或者更少，用于处理其他相关的任务，或者有些情况下更多，来提升非计算密集型任务的性能）。
 * 一个粒度参数代表了创建任务的代价会大于并行化所带来的潜在的性能提升的临界点。这个参数更多的是取决于算法而不是平台。通常在单处理器上运行良好的临界点，在多处理器平台上也会发挥很好的效果。作为一种附带的效益，这种方式能够与Java虚拟机的动态编译机制很好的结合，而这种机制在对小块方法的优化方面相对于单块的程序来说要好。这样，加上数据本地化的优势，fork/join算法的性能即使在单处理器上面的性能都较其他算法要好
 *
 * @author ssk 2017-02-27 下午 1:13 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
public class TestForkJoinTask extends FJTask {
    static final int threashold = 13;
//    当要求使用volatile 声明的变量的值的时候，系统总是重新从它所在的内存读取数据，即使它前面的指令刚刚从该处读取过数据。而且读取的数据立刻被保存。
     volatile  int number;
    public TestForkJoinTask(int n){
        number = n;
    }

    public static void main(String[] args){
        try {
            int groupSize = 3; // for example
            FJTaskRunnerGroup group = new FJTaskRunnerGroup(groupSize);
            TestForkJoinTask f = new TestForkJoinTask(35); // for example
            group.invoke(f);
            int result = f.getAnswer();
            System.out.println("Answer: " + result);
        } catch ( InterruptedException ex ) {
        }

    }

    int getAnswer( ){
        if ( !isDone() ) {
            throw new IllegalStateException();
        }
        return number;
    }

    @Override
    public void run( ){
        int n = number;
        if ( n <= threashold )
            number = seqTestForkJoinTask(n);
        else {
            TestForkJoinTask t1 = new TestForkJoinTask(n - 1);
            TestForkJoinTask t2 = new TestForkJoinTask(n - 2);
//            TestForkJoinTask t3 = new TestForkJoinTask(n - 3);
            FJTask[] tasks = new FJTask[]{t1, t2};
            coInvoke(tasks);
            number = t1.number + t2.number/*+t3.number*/;
        }
    }

    int seqTestForkJoinTask(int n){
        if ( n <= 1 )
            return n;
        else
            return seqTestForkJoinTask(n - 1) + seqTestForkJoinTask(n - 2)/*+seqTestForkJoinTask(n-3)*/;
    }
}

