package practice.ForkJoin;

import java.util.Random;

/**
 * 类ArrayGenerator.java的功能描述:取消任务
 * <p>
 * 当你在一个ForkJoinPool类中执行ForkJoinTask对象，在它们开始执行之前，你可以取消执行它们。ForkJoinTask类提供cancel()方法用于这个目的。当你想要取消一个任务时，有一些点你必须考虑一下，这些点如下：
 * <p>
 * ForkJoinPool类并没有提供任何方法来取消正在池中运行或等待的所有任务。
 * 当你取消一个任务时，你不能取消一个已经执行的任务。
 * 在这个指南中，你将实现取消ForkJoinTask对象的例子。你将查找数在数组中的位置。第一个找到这个数的任务将取消剩下的任务（未找到这个数的任务）。由于Fork/Join框架并没有提供这种功能，所以，你将实现一个辅助类来做这个取消功能。
 *
 * @author ssk 2017-02-27 下午 3:48 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
//1.创建ArrayGenerator类。这个类将产生一组随机的、指定大小的整数数字。实现generateArray()方法。它将产生一组数字，它接收数组大小作为参数。
public class ArrayGenerator {
    public int[] generateArray(int size){
        int array[] = new int[size];
        Random random = new Random();
        for ( int i = 0; i < size; i++ ) {
            array[i] = random.nextInt(10);
        }
        return array;
    }
}
