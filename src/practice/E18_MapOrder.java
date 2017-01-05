package practice;

import net.mindview.util.Countries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class E18_MapOrder {
    public static void main(String[] args){
        Map<String, String> m1 = new HashMap<String, String>(Countries.capitals(25));
        System.out.println(m1);
        String[] keys = m1.keySet().toArray(new String[10]);
        Arrays.sort(keys);
        Map<String, String> m2 = new LinkedHashMap<>();
        for ( String key : keys ) {
            m2.put(key, m1.get(key));
            System.out.println(m2);
        }
    }
}
