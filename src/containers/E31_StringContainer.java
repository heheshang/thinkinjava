package containers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
class IntContainer {
    private static final int INCR = 255;
    private int[] array;
    private int index;

    public IntContainer( ){
        array = new int[10];
    }

    public IntContainer(int sz){
        array = new int[sz];
    }

    public void add(int i){
        if ( index >= array.length ) {
            int[] tmp = new int[array.length + INCR];
            for ( int j = 0; j < array.length; j++ )
                tmp[j] = array[j];
            index = array.length;
            array = tmp;
        }
        array[index++] = i;
    }

    public int get(int i){
        return array[i];
    }

    public void set(int i, int val){
        array[i] = val;
    }

    public int size( ){
        return index;
    }
}

class StringContainer {
    private static final int INCR = 255;
    private String[] array;
    private int index;

    public StringContainer( ){
        array = new String[10];
    }

    public StringContainer(int sz){
        array = new String[sz];
    }

    public void add(String s){
        if ( index >= array.length ) {
            String[] tmp = new String[array.length + INCR];
            for ( int i = 0; i < array.length; i++ )
                tmp[i] = array[i];
            index = array.length;
            array = tmp;
        }
        array[index++] = s;
    }

    public String get(int i){
        return array[i];
    }

    public int size( ){
        return index;
    }
}

public class E31_StringContainer {
    static final int LOOPS = 10000;
    static List<Test<List<String>>> alTests = new ArrayList<Test<List<String>>>();
    static List<Test<StringContainer>> scTests = new ArrayList<Test<StringContainer>>();
    static List<Test<IntContainer>> icTests = new ArrayList<Test<IntContainer>>();

    static{
        alTests.add(new Test<List<String>>("addget") {
            int test(List<String> list, TestParam tp){
                for ( int i = 0; i < LOOPS; i++ ) {
                    list.add(Integer.toString(i));
                    list.get(i);
                }
                return LOOPS;
            }
        });
        scTests.add(new Test<StringContainer>("addget") {
            int test(StringContainer sc, TestParam tp){
                for ( int i = 0; i < LOOPS; i++ ) {
                    sc.add(Integer.toString(i));
                    sc.get(i);
                }
                return LOOPS;
            }
        });

        icTests.add(new Test<IntContainer>("addget") {
            int test(IntContainer ic, TestParam tp){
                for ( int i = 0; i < LOOPS; i++ ) {
                    ic.add(i);
                    ic.set(i, ic.get(i) + 1);
                }
                return LOOPS;
            }
        });
    }

    public static void main(String[] args){
        // Parameters are also hard-coded inside tests.
        Tester.defaultParams = TestParam.array(LOOPS, 1);
        Tester.run(new ArrayList<String>(LOOPS), alTests);
        Tester.run(new StringContainer(LOOPS), scTests);
        Tester.run(new IntContainer(LOOPS), icTests);
    }
}