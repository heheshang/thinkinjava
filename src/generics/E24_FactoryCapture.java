package generics;

import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/1/6 0006.
 */
class FactoryCapture {
    Map<String, FactoryL<?>> factories = new HashMap<String, FactoryL<?>>();

    public Object createNew(String factoryname, int arg){
        FactoryL<?> f = factories.get(factoryname);
        try {
            return f.create(arg);
        } catch ( NullPointerException e ) {
            print("Not a registered factoryname: " + factoryname);
            return null;
        }
    }

    public void addFactory(String factoryname, FactoryL<?> factory){
        factories.put(factoryname, factory);
    }
}

public class E24_FactoryCapture {
    public static void main(String[] args){
        FactoryCapture fc = new FactoryCapture();
        fc.addFactory("Integer", new IntegerFactoryL());
        fc.addFactory("Widget", new Widght.Factory());
        print(fc.createNew("Integer", 1));
        print(fc.createNew("Widget", 2));
        fc.createNew("Product", 3);
    }
}
