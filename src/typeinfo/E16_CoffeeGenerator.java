package typeinfo;

import net.mindview.util.Generator;
import typeinfo.coffee2.Coffee;

import java.util.Iterator;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class E16_CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private int size = 0;

    public E16_CoffeeGenerator( ){
    }

    public E16_CoffeeGenerator(int sz){
        size = sz;
    }

    public static void main(String[] args){
        for ( Coffee c : new E16_CoffeeGenerator(10) )
            System.out.println(c);
    }

    public Coffee next( ){
        return Coffee.createRandom();
    }



    public Iterator<Coffee> iterator( ){
        return new CoffeeIterator();
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        public boolean hasNext( ){
            return count > 0;
        }

        public Coffee next( ){
            count--;
            return E16_CoffeeGenerator.this.next();
        }

        public void remove( ){
        } // Not implemented
    }
}
