package practice;

import generics.CountedObject;
import net.mindview.util.BasicGenerator;
import net.mindview.util.Generator;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class E14_BasicGeneratorDemo2 {
    public static void main(String[] args){
        Generator<CountedObject> gen = new BasicGenerator<CountedObject>(CountedObject.class);
        for ( int i = 0; i < 5; i++ )
            System.out.println(gen.next());
    }
}
