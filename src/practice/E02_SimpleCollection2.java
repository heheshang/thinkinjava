package practice;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class E02_SimpleCollection2 {
    public static void main(String[] args){
        Collection<Integer> c = new HashSet<>();
        for ( int i = 0; i < 10; i++ ) {
            c.add(i);
        }
        for ( Integer integer : c ) {
            System.out.println(integer);
        }
    }
}
