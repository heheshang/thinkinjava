package practice;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class E28_PriorityQueue {
    static Random rand = new Random(47);

    public static void printQ(Queue<?> queue){
        for ( Object data = queue.poll(); data != null; data = queue.poll() ) {
            System.out.println(data + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        PriorityQueue<Double> priorityQueue = new PriorityQueue<>();
        for ( int i = 0; i < 10; i++ ) {
            priorityQueue.offer(rand.nextDouble());
        }
        printQ(priorityQueue);
    }
}
