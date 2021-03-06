package generics;

import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/1/6 0006.
 */
class ClassTypeCapture2 {
    Map<String, Class<?>> types = new HashMap<String, Class<?>>();

    public Object createNew(String typename){
        Class<?> cl = types.get(typename);
        try {
            return cl.newInstance();
        } catch ( NullPointerException e ) {
            print("Not a registered typename: " + typename);
        } catch ( Exception e ) {
            print(e.toString());
        }
        return null;
    }

    public void addType(String typename, Class<?> kind){
        types.put(typename, kind);
    }
}

public class E21_ClassTypeCapture2 {
    public static void main(String[] args){
        ClassTypeCapture2 ctt = new ClassTypeCapture2();
        ctt.addType("Building", Building.class);
        ctt.addType("House", House.class);
        ctt.addType("Product", Product.class);
        print(ctt.createNew("Building").getClass());
        print(ctt.createNew("House").getClass());
        ctt.createNew("Product");
        ctt.createNew("Car");
    }
}
