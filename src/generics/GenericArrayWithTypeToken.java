package generics;//: generics/GenericArrayWithTypeToken.java

import java.lang.reflect.*;

public class GenericArrayWithTypeToken<T> {
    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> type, int sz){
        array = ( T[] ) Array.newInstance(type, sz);
    }

    public static void main(String[] args){
        GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<Integer>(
                Integer.class, 10);
        // This now works:
        Integer[] ia = gai.rep();
        for ( int i = 0; i < 10; i++ ) {
            gai.put(i, i);
        }
        for ( Integer integer : ia ) {
            System.out.println(integer);
        }
    }

    public void put(int index, T item){
        array[index] = item;
    }

    public T get(int index){
        return array[index];
    }

    // Expose the underlying representation:
    public T[] rep( ){
        return array;
    }
} ///:~
