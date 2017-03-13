package containers;

import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.TreeSet;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
public class E09_RandTreeSet {
    public static void main(String[] args){
        TreeSet<String> ts = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        ts.addAll(CollectionData.list(new RandomGenerator.String(), 10));
        System.out.println("ts:" + ts);

    }
}
