package containers;

import net.mindview.util.CollectionData;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generator;

import java.util.*;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
public class E29_ListPerformance2 {
    static Generator<String> gen = new CountingGenerator.String();
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<String>>> tests = new ArrayList<>();
    static List<Test<LinkedList<String>>> qTests = new ArrayList<>();

    static{
        tests.add(new Test<List<String>>("add") {
            @Override
            int test(List<String> container, TestParam tp){
                int loops = tp.loops;
                int listSize = tp.size;
                for ( int i = 0; i < loops; i++ ) {
                    container.clear();
                    for ( int j = 0; j < listSize; j++ ) {
                        container.add(gen.next());
                    }
                }
                return loops * listSize;
            }
        });
        tests.add(new Test<List<String>>("get") {
            @Override
            int test(List<String> container, TestParam tp){
                int loops = tp.loops * reps;
                int listSize = container.size();
                for ( int i = 0; i < loops; i++ ) {
                    container.get(rand.nextInt(listSize));
                }
                return loops;
            }
        });
        tests.add(new Test<List<String>>("set") {
            @Override
            int test(List<String> container, TestParam tp){
                int loops = tp.loops * reps;
                int listSize = container.size();
                for ( int i = 0; i < loops; i++ ) {
                    container.set(rand.nextInt(listSize), "Hello");
                }
                return loops;

            }
        });

        tests.add(new Test<List<String>>("iteradd") {
            @Override
            int test(List<String> container, TestParam tp){
                final int LOOPS = 100000;
                int half = container.size() / 2;
                ListIterator<String> it = container.listIterator(half);
                for ( int i = 0; i < LOOPS; i++ ) {
                    it.add("Hello");

                }
                return LOOPS;
            }
        });

        tests.add(new Test<List<String>>("remove") {
            @Override
            int test(List<String> container, TestParam tp){
                int loops = tp.loops;
                int size = tp.size;
                for ( int i = 0; i < loops; i++ ) {
                    container.clear();
                    container.addAll(CollectionData.list(new CountingGenerator.String(), size));
                    while ( container.size() > 5 ) {
                        container.remove(5);
                    }

                }
                return loops * size;
            }
        });
        qTests.add(new Test<LinkedList<String>>("addFirst") {
            @Override
            int test(LinkedList<String> container, TestParam tp){
                int loops = tp.loops;
                int size = tp.size;
                for ( int i = 0; i < loops; i++ ) {
                    container.clear();
                    for ( int j = 0; j < size; j++ ) {
                        container.addFirst("Hello");
                    }

                }
                return loops * size;

            }
        });
        qTests.add(new Test<LinkedList<String>>("addLast") {
            @Override
            int test(LinkedList<String> container, TestParam tp){
                int loops = tp.loops;
                int size = tp.size;
                for ( int i = 0; i < loops; i++ ) {
                    container.clear();
                    for ( int j = 0; j < size; j++ ) {
                        container.addLast("Hello");
                    }
                }
                return loops * size;
            }
        });

        qTests.add(new Test<LinkedList<String>>("rmFirst") {
            @Override
            int test(LinkedList<String> container, TestParam tp){
                int loops = tp.loops;
                int size = tp.size;
                for ( int i = 0; i < loops; i++ ) {
                    container.clear();
                    container.addAll(CollectionData.list(new CountingGenerator.String(), size));
                    while ( container.size() > 0 )
                        container.removeFirst();
                }
                return loops * size;
            }
        });
        qTests.add(new Test<LinkedList<String>>("rmLast") {
            @Override
            int test(LinkedList<String> container, TestParam tp){
                int loops =tp.loops;
                int size = tp.size;
                for ( int i = 0; i < loops; i++ ) {
                    container.clear();
                }
                return 0;
            }
        });
    }
}
