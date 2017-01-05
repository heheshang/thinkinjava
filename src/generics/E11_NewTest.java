package generics;

import net.mindview.util.FiveTuple;
import net.mindview.util.New;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
class SixTuple1<A, B, C, D, E, F> extends FiveTuple<A, B, C, D, E> {
    public final F sixth;

    public SixTuple1(A a, B b, C c, D d, E e, F f){
        super(a, b, c, d, e);
        sixth = f;
    }

    public String toString( ){
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ", " + fifth + ", " +
                sixth + ")";
    }
}

public class E11_NewTest {
    public static void main(String[] args){
        List<SixTuple1<Byte, Short, String, Float, Double, Integer>> list = New.list();
        list.add(new SixTuple1<Byte, Short, String, Float, Double, Integer>(
                ( byte ) 1, ( short ) 1, "A", 1.0F, 1.0, 1));
        System.out.println(list);
        Set<Sequence<String>> set = New.set();
        set.add(new Sequence<String>(5));
        System.out.println(set);
    }
}
