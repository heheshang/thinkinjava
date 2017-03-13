//: net/mindview/util/CountingIntegerList.java
// List of any length, containing sample data.
package net.mindview.util;

import java.util.AbstractList;

public class CountingIntegerList extends AbstractList<Integer> {
    private int size;

    public CountingIntegerList(int size){
        this.size = size < 0 ? 0 : size;
    }

    public static void main(String[] args){
        CountingIntegerList a = new CountingIntegerList(1);
        CountingIntegerList b = new CountingIntegerList(1);
        if ( a.get(0).equals(b.get(0)) ) {
            System.out.println(a.get(0));
            System.out.println(a);
        }
        System.out.println(a);
        System.out.println(b);
    }

    public Integer get(int index){
        return Integer.valueOf(index);
    }

    public int size( ){
        return size;
    }
} /* Output:
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
*///:~
