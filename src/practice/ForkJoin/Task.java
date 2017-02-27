package practice.ForkJoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 类Task.java的功能描述:在Java7中，JDK提供对多线程开发提供了一个非常强大的框架，就是Fork/Join框架。这个是对原来的Executors更
 * 进一步，在原来的基础上增加了并行分治计算中的一种Work-stealing策略，就是指的是。当一个线程正在等待他创建的
 * 子线程运行的时候，当前线程如果完成了自己的任务后，就会寻找还没有被运行的任务并且运行他们，这样就是和
 * Executors这个方式最大的区别，更加有效的使用了线程的资源和功能。所以非常推荐使用Fork/Join框架。
 * <p>
 * 下面我们以一个例子来说明这个框架如何使用，主要就是创建一个含有10000个资源的List，分别去修改他的内容。
 * <p>
 * 在这个指南中，你将继续实现一个任务来修改产品列表的价格。任务最初是负责更新一个队列中的所有元素。你将会使用10作为参考大小，如果一个任务必须更新超过10个元素，这些元素将被划分成两个部分，并创建两个任务来更新每个部分中的产品的价格。
 *
 * @author ssk 2017-02-24 下午 7:52 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
public class Task extends RecursiveAction {
    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment){
        super();
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    public static void main(String[] args){
        //使用ProductListGenerator类创建一个包括10000个产品的数列
        ProductListGenerator productListGenerator = new ProductListGenerator();
        List<Product> products = productListGenerator.generate(10000);
        //创建一个新的Task对象，用来更新产品队列中的产品。first参数使用值0，last参数使用值10000（产品数列的大小）。
        Task task = new Task(products, 0, products.size(), 0.2);
        //使用无参构造器创建ForkJoinPool对象。
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        //实现一个显示关于每隔5毫秒池中的变化信息的代码块。将池中的一些参数值写入到控制台，直到任务完成它的执行。
        do {
            System.out.printf("Main: Thread Count: %d \n", pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d \n", pool.getStealCount());
            System.out.printf("Main: Parallelism: %d  \n", pool.getParallelism());

            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        } while ( !task.isDone() );
        // 使用shutdown()方法关闭这个池。
        pool.shutdown();
        //使用isCompletedNormally()方法检查假设任务完成时没有出错，在这种情况下，写入一条信息到控制台
        if ( task.isCompletedNormally() ) {
            System.out.printf("Main: The process has completed normally.\n");
        }
        //在增长之后，所有产品的价格应该是12。将价格不是12的所有产品的名称和价格写入到控制台，用来检查它们错误地增长它们的价格。
        for ( Product product : products ) {
            if ( product.getPrice() != 12 ) {
                System.out.println("Product " + product.toString());
            }
        }
        //写入一条信息到控制台表明程序的结束。
        System.out.println("Main: End of the program.\n");
    }

    @Override
    protected void compute( ){
        if ( last - first < 10 ) {
            updatePrices();
        } else {
            //如果last和first的差大于或等于10，则创建两个新的Task对象，一个处理产品的前半部分，另一个处理产品的后半部分，然后在ForkJoinPool中，使用invokeAll()方法执行它们
            int middle = ( first + last ) / 2;
            System.out.printf("Task: Pending tasks:%s\n", getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            invokeAll(t1, t2);

        }
    }

    /**
     * 功能描述:实现updatePrices()方法。这个方法更新产品队列中位于first值和last值之间的产品。
     *
     * @param
     * @return
     * @throws
     * @since v1.0
     */
    private void updatePrices( ){
        for ( int i = first; i < last; i++ ) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * ( 1 + increment ));

        }
    }

    /**
     * 它是如何工作的…

     在这个示例中，你已经创建一个ForkJoinPool对象和一个在池中执行的ForkJoinTask类的子类。为了创建ForkJoinPool对象，你已经使用了无参构造器，所以它会以默认的配置来执行。它创建一个线程数等于计算机处理器数的池。当ForkJoinPool对象被创建时，这些线程被创建并且在池中等待，直到有任务到达让它们执行。

     由于Task类没有返回结果，所以它继承RecursiveAction类。在这个指南中，你已经使用了推荐的结构来实现任务。如果这个任务更新超过10产品，它将被分解成两部分，并创建两个任务，一个任务执行一部分。你已经在Task类中使用first和last属性，用来了解这个任务要更新的产品队列的位置范围。你已经使用first和last属性，只复制产品数列一次，而不是为每个任务创建不同的数列。

     它调用invokeAll()方法，执行每个任务所创建的子任务。这是一个同步调用，这个任务在继续（可能完成）它的执行之前，必须等待子任务的结束。当任务正在等待它的子任务（结束）时，正在执行它的工作线程执行其他正在等待的任务。在这种行为下，Fork/Join框架比Runnable和Callable对象本身提供一种更高效的任务管理。

     ForkJoinTask类的invokeAll()方法是执行者（Executor）和Fork/Join框架的一个主要区别。在执行者框架中，所有任务被提交给执行者，而在这种情况下，这些任务包括执行和控制这些任务的方法都在池内。你已经在Task类中使用invokeAll()方法，它是继承了继承ForkJoinTask类的RecursiveAction类。

     你使用execute()方法提交唯一的任务给这个池，用来所有产品数列。在这种情况下，它是一个异步调用，而主线程继续它的执行。

     你已经使用ForkJoinPool类的一些方法，用来检查正在运行任务的状态和变化。基于这个目的，这个类包括更多的方法。参见有这些方法完整列表的监控一个Fork/Join池指南。
     */

    /**
     *
     */
}
