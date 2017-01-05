package practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class E17_GerbilMap {
    public static void main(String[] args){
        Map<String, Gerbil> map = new HashMap<>();
        map.put("Fuzzy", new Gerbil(1));
        map.put("Spot", new Gerbil(2));
        map.put("Joe", new Gerbil(3));
        map.put("Ted", new Gerbil(4));
        map.put("Heather", new Gerbil(5));
        Iterator<Map.Entry<String, Gerbil>> it = map.entrySet().iterator();
        while ( it.hasNext() ) {
            Map.Entry<String, Gerbil> entry = it.next();
            System.out.println(entry.getKey() + ": ");
            entry.getValue().hop();
        }
    }
}
