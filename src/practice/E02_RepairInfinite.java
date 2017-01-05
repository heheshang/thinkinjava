package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class E02_RepairInfinite {
    public static void main(String[] args){
        List<E02_RepairInfinite> v = new ArrayList<E02_RepairInfinite>();
        for ( int i = 0; i < 10; i++ )
            v.add(new E02_RepairInfinite());
        System.out.println(v);
    }

    @Override
    public String toString( ){
        return " E02_RepairInfinite address: " + super.toString() + "\n";
    }
}
