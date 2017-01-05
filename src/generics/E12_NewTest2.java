package generics;

import net.mindview.util.New;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class E12_NewTest2 {
    static void f(List<SixTuple1<Byte, Short, String, Float, Double, Integer>> l){
        l.add(new SixTuple1<Byte, Short, String, Float, Double, Integer>(
                ( byte ) 1, ( short ) 1, "A", 1.0F, 1.0, 1));
        System.out.println(l);
    }

    static void g(Set<Sequence<String>> s){
        s.add(new Sequence<String>(5));
        System.out.println(s);
    }

    public static void main(String[] args){
        f(New.<SixTuple1<Byte, Short, String, Float, Double, Integer>>list());
        g(New.<Sequence<String>>set());
    }
}
