package practice;

import generics.Fibonacci;

import java.util.Iterator;

//: generics/E07_IterableFibonacci2.java
/****************** Exercise 7 *****************
 * Use composition instead of inheritance to adapt
 * Fibonacci to make it Iterable.
 ************************************************/
class IterableFibonacci implements Iterable<Integer> {
    private Fibonacci fibonacci = new Fibonacci();
    private int n;

    public IterableFibonacci(int n){
        this.n = n;
    }

    @Override
    public Iterator<Integer> iterator( ){
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext( ){
                return n > 0;
            }

            @Override
            public Integer next( ){
                --n;
                return fibonacci.next();
            }

            @Override
            public void remove( ){
                throw new UnsupportedOperationException();
            }
        };
    }
}

public class E07_IterableFibonacci2 {
    public static void main(String[] args){
        for ( int i : new IterableFibonacci(18) ) {
            System.out.println(i+" ");
        }
    }
}
/*

 *///:~