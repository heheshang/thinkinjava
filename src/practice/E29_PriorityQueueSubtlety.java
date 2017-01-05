package practice;

import java.util.PriorityQueue;

/**
 * 类Dummy.java的功能描述:TODO
 * @author ssk 2016-11-30 下午 3:29 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
class Dummy{}
public class E29_PriorityQueueSubtlety {
    public static void main(String[] args){
        PriorityQueue<Dummy> priorityQueue = new PriorityQueue<>();
        System.out.println("Adding 1st instance");
        priorityQueue.offer(new Dummy());
        System.out.println("Adding 2nd instance");
        priorityQueue.offer(new Dummy());
    }
}
