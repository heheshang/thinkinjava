package containers;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static net.mindview.util.Countries.capitals;
import static net.mindview.util.Countries.names;

/**
 * Created by Administrator on 2017/3/1 0001.
 */
public class E02_ACountries {
    public static void main(String[] args){
        TreeMap<String, String> map =  new TreeMap<String, String>(capitals());
        TreeSet<String> set = new TreeSet<String>(names());
        String b = null;
        for ( String s : map.keySet() )
            if ( s.startsWith("B") ) {
                b = s;
                break;
            }
        Map<String, String> aMap = map.headMap(b);
        Set<String> aSet = set.headSet(b);
        System.out.println("aMap = " + aMap);
        System.out.println("aSet = " + aSet);
    }
}
