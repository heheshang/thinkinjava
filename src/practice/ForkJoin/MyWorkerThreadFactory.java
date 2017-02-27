package practice.ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * 类MyWorkerThreadFactory.java的功能描述://7.创建一个实现ForkJoinWorkerThreadFactory接口的MyWorkerThreadFactory类。实现newThread()方法，创建和返回一个MyWorkerThread对象
 *
 * @author ssk 2017-02-27 下午 5:18 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
public class MyWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {
    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool){
        return new MyWorkerThread(pool);
    }
}
