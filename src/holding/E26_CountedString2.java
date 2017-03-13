package holding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
class CountedString2 {
    private static List<String> created = new ArrayList<>();
    private String s;
    private char c;
    private int id;

    public CountedString2(String s, char c){
        this.s = s;
        this.c = c;
        created.add(s);
        for ( String s1 : created ) {
            if ( s1.equals(s) )
                id++;
        }
    }

//    @Override
//    public boolean equals(Object o){
//        return o instanceof CountedString2 &&
//                s.equals(( ( CountedString2 ) o ).s) &&
//                id == ( ( CountedString2 ) o ).id &&
//                c == ( ( CountedString2 ) o ).c;
//
//    }

//    @Override
//    public int hashCode( ){
//        // The very simple approach:
//// return s.hashCode() * id;
//// Using Joshua Bloch's recipe:
//        int result = 17;
//        result = 37 * result + s.hashCode();
//        result = 37 * result + id;
//        result = 37 * result + c;
//        return result;
//    }

    @Override
    public boolean equals(Object o){
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        CountedString2 that = ( CountedString2 ) o;

        if ( c != that.c ) return false;
        if ( id != that.id ) return false;
        return s != null ? s.equals(that.s) : that.s == null;

    }

    @Override
    public int hashCode( ){
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result +  c;
        result = 37 * result + id;
        return result;
    }

    @Override
    public String toString( ){
        return "String: " + s + " id: " + id + " char: " + c +
                " hashCode(): " + hashCode();
    }
}

public class E26_CountedString2 {
    public static void main(String[] args){
        Map<CountedString2, Integer> map = new HashMap<CountedString2, Integer>();
        CountedString2[] cs = new CountedString2[5];
        for ( int i = 0; i < cs.length; i++ ) {
            cs[i] = new CountedString2("hi", 'c');
            map.put(cs[i], i); // Autobox int -> Integer
        }
        print(map);
        for ( CountedString2 cstring : cs ) {
            print("Looking up " + cstring);
            print(map.get(cstring));
        }
    }
}
