package containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static net.mindview.util.Countries.names;
import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/1 0001.
 */
public class E01_CountryList {
    private static Random random = new Random(47);

    public static void main(String[] args){
        List<String> l = new ArrayList<>(names(8));
        Collections.sort(l);
        print("sorted:" + l);
        for ( int i = 0; i < 5; i++ ) {
            Collections.shuffle(l, random);
            print("shuffled (" + i + "): " + l);
        }
    }
}
