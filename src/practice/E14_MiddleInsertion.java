package practice;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class E14_MiddleInsertion {
    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<>();
        ListIterator<Integer> it = list.listIterator();
        for ( int i = 1; i < 3; i++ ) {
            it.add(i);
            if ( i % 2 == 0 ) {
                it.previous();
            }
        }
        System.out.println(list);

    }
}
