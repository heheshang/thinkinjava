package practice;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.TypeCounter;

import java.util.Iterator;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public class E12_CoffeeCount {
    public static void main(String[] args){
        TypeCounter counter = new TypeCounter(Coffee.class);
        for ( Iterator<Coffee> iterator = new CoffeeGenerator(20).iterator(); iterator.hasNext(); ) {
            Coffee coffee =  iterator.next();
            printnb(coffee.getClass().getSimpleName() + " ");
            counter.count(coffee);
        }
        print();
        print(counter);
    }
}
