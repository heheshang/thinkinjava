package practice.ForkJoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 类FolderProcessor.java的功能描述:在Fork/Join框架中，提交任务的时候，有同步和异步两种方式。以前使用的invokeAll()的方法是同步的，也就是任
 * 务提交后，这个方法不会返回直到所有的任务都处理完了。而还有另一种方式，就是使用fork方法，这个是异步的。也
 * 就是你提交任务后，fork方法立即返回，可以继续下面的任务。这个线程也会继续运行。
 * <p>
 * 下面我们以一个查询磁盘的以log结尾的文件的程序例子来说明异步的用法。
 *
 * @author ssk 2017-02-24 下午 7:51 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
//创建FolderProcessor类，指定它继承RecursiveTask类，并参数化为List<String>类型。
public class FolderProcessor extends RecursiveTask<List<FolderProcessor>> {
    //声明这个类的序列号版本UID。这个元素是必需的，因为RecursiveTask类的父类，ForkJoinTask类实现了Serializable接口。
    private static final long serialVersionUID = 1L;
    //声明一个私有的、String类型的属性path。这个属性将存储任务将要处理的文件夹的全路径
    private String path;
    //声明一个私有的、String类型的属性extension。这个属性将存储任务将要查找的文件的扩展名。
    private String extension;
    private String fileName;
    //实现这个类的构造器，初始化它的属性。
    public FolderProcessor(String path, String extension){
        super();
        this.path = path;
        this.extension = extension;
    }

    public FolderProcessor(String path, String extension, String fileName){
        this(path,extension);
        this.fileName = fileName;
    }

    public static void main(String[] args){
        //18.使用默认构造器创建ForkJoinPool。
        ForkJoinPool pool = new ForkJoinPool();
        // 19.创建3个FolderProcessor任务。用不同的文件夹路径初始化每个任务。
        FolderProcessor system = new FolderProcessor("C:\\Windows", "log");
        FolderProcessor apps = new FolderProcessor("D:\\output", "log");
        FolderProcessor documents = new FolderProcessor("C:\\Documents And Settings", "log");
        // 20.在池中使用execute()方法执行这3个任务。
        pool.execute(system);
        pool.execute(apps);
        pool.execute(documents);
        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.
                    getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.
                    getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.
                    getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.
                    getStealCount());
            System.out.printf("***************************************** *\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        } while ( ( !system.isDone() ) || ( !apps.isDone() ) || ( !documents.isDone() ) );

        pool.shutdown();

        List<FolderProcessor> results;
        results = system.join();
        System.out.printf("System: %d %s files found.\n", results.size(),results.toString());
        results = apps.join();
        System.out.printf("Apps: %d %s files found.\n", results.size(),results.toString());
        results = documents.join();
        System.out.printf("Documents: %d %s files found.\n", results.size(),results.toString());

//        pool.shutdown();
//
//        List<FolderProcessor> results = null;
//        results = system.join();
//        System.out.printf("System: %d files found.\n", results.size());
//        System.out.println(results.toString());
//
//        results = apps.join();
//
//        System.out.printf("Apps: %d files found.\n", results.size());
//        System.out.println(results.toString());
    }

    public String getPath( ){
        return path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public String getExtension( ){
        return extension;
    }

    public void setExtension(String extension){
        this.extension = extension;
    }

    @Override
    public String toString( ){
        return "FolderProcessor{" +
                "path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    //实现compute()方法。正如你用List<FolderProcessor>类型参数化RecursiveTask类，这个方法将返回这个类型的一个对象。
    @Override
    protected List<FolderProcessor> compute( ){
        //7.声明一个FolderProcessor对象的数列，用来保存存储在文件夹中的文件。
        List<FolderProcessor> list = new ArrayList<>();
        List<FolderProcessor> tasks = new ArrayList<>();
        //9.获取文件夹的内容。
        File file = new File(path);
        File content[] = file.listFiles();
        //10.对于文件夹里的每个元素，如果是子文件夹，则创建一个新的FolderProcessor对象，并使用fork()方法异步地执行它。
        if ( null != content ) {
            for ( int i = 0; i < content.length; i++ ) {
                if ( content[i].isDirectory() ) {
                    FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(),extension, content[i].getName());
                    //异步方式提交任务
                    task.fork();
                    tasks.add(task);
                } else {
//                    11.否则，使用checkFile()方法比较这个文件的扩展名和你想要查找的扩展名，如果它们相等，在前面声明的字符串数列中存储这个文件的全路径。
                    if ( checkFile(content[i].getName()) ) {

                        FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(),extension, content[i].getName());
                        list.add(task);
                    }
                }

            }
        }
        //如果FolderProcessor子任务的数列超过50个元素，写入一条信息到控制台表明这种情况。
        if ( tasks.size() > 50 ) {
            System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
        }
//13.调用辅助方法addResultsFromTask()，将由这个任务发起的子任务返回的结果添加到文件数列中。传入参数：字符串数列和FolderProcessor子任务数列。
        addResultsFromTasks(list, tasks);

        //14.返回字符串数列。
        return list;
    }

    /**
     * 功能描述:15.实现addResultsFromTasks()方法。对于保存在tasks数列中的每个任务，调用join()方法，这将等待任务执行的完成，并且返回任务的结果。使用addAll()方法将这个结果添加到字符串数列。
     *
     * @param
     * @return
     * @throws
     * @since v1.0
     */
    private void addResultsFromTasks(List<FolderProcessor> list, List<FolderProcessor> tasks){
        for ( FolderProcessor task : tasks ) {
            list.addAll(task.join());
        }

    }

    /**
     * 功能描述:16.实现checkFile()方法。这个方法将比较传入参数的文件名的结束扩展是否是你想要查找的。如果是，这个方法返回true，否则，返回false。
     *
     * @param
     * @return
     * @throws
     * @since v1.0
     */
    private boolean checkFile(String name){
        return name.endsWith(extension);
    }
}
