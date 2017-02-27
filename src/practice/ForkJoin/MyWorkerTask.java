package practice.ForkJoin;

import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

//1.   创建一个类，名为 MyWorkerTask，并特别扩展 ForkJoinTask 类参数化 Void type。
public abstract class MyWorkerTask extends ForkJoinTask<Void> {

    //2.   声明一个私有 String 属性，名为 name，用来储存任务的名字。
    private String name;

    //3.   实现类的构造函数，初始化它的属性。
    public MyWorkerTask(String name){
        this.name = name;
    }

    //4.   实现 getRawResult() 方法。这是 ForkJoinTask 类的抽象方法之一。由于任务不会返回任何结果，此方法返回的一定是null值。
    @Override
    public Void getRawResult( ){
        return null;
    }

    //5.   实现 setRawResult() 方法。这是 ForkJoinTask 类的另一个抽象方法。由于任务不会返回任何结果，方法留白即可。
    @Override
    protected void setRawResult(Void value){

    }

//6.   实现 exec() 方法。这是任务的主要方法。在这个例子，把任务的算法委托给 compute() 方法。计算方法的运行时间并写入操控台。

    @Override
    protected boolean exec( ){
        Date startDate = new Date();
        compute();
        Date finishDate = new Date();
        long diff = finishDate.getTime() - startDate.getTime();
        System.out.printf("MyWorkerTask: %s : %d Milliseconds to complete.\n", name, diff);
        return true;
    }

    //7.	实现 getName() 方法来返回任务的名字。
    public String getName( ){
        return name;
    }

    //8.   声明抽象方法 compute()。像我们之前提到的，此方法实现任务的算法，必须是由 MyWorkerTask 类的子类实现。
    protected abstract void compute( );

    //9.   创建一个类，名为 Task，延伸 MyWorkerTask 类。
    static class Task extends MyWorkerTask {

        //10. 声明一个私有 int 值 array，名为 array。
        private int array[];
        private int start, end;

        //11. 实现类的构造函数，初始化它的属性值。
        public Task(String name, int array[], int start, int end){
            super(name);
            this.array = array;
            this.start = start;
            this.end = end;
        }

        //15. 创建例子的主类通过创建一个类，名为 Main 并添加 main()方法。
        public static void main(String[] args) throws Exception{

//16. 创建一个10，000元素的 int array。
            int array[] = new int[10000];

//17.  创建一个 ForkJoinPool 对象，名为 pool。
            ForkJoinPool pool = new ForkJoinPool();

//18. Create a 创建一个 Task 对象来增加array的全部元素。构造函数的参数是：任务的名字 Task，array对象，和0 和10000来向这个任务表示要处整个array.
            Task task = new Task("Task", array, 0, array.length);

//19.  使用 execute() 方法发送任务给池。
            pool.invoke(task);

//20. 使用 shutdown() 方法关闭池。
            pool.shutdown();

//21. 在操控台写个信息表明程序结束。
            System.out.printf("Main: End of the program.\n");
        }

        //12. 实现 compute() 方法。此方法通过 start 和 end 属性来决定增加array的元素块。如果元素块的元素超过100个，把它分成2部分，并创建2个Task对象来处理各个部分。再使用 invokeAll() 方法把这些任务发送给池。
        protected void compute( ){
            if ( end - start > 100 ) {
                int mid = ( end + start ) / 2;
                Task task1 = new Task(this.getName() + "1", array, start, mid);
                Task task2 = new Task(this.getName() + "2", array, mid, end);
                invokeAll(task1, task2);

//13.如果元素块的元素少于100，使用for循环增加全部的元素。
            } else {
                for ( int i = start; i < end; i++ ) {
                    array[i]++;
                }

//14. 最后，让正在执行任务的线程进入休眠50毫秒。
                try {
                    Thread.sleep(50);
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }

    }
}