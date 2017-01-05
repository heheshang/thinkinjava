package practice;

import generics.RandomList;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generator;

public class E06_RandomListTest {
    private static void dump(RandomList<?> rl){
        for ( int i = 0; i < 11; i++ )
            System.out.print(rl.select() + " ");
        System.out.println();
    }

    public static void main(String[] args){
        RandomList<String> rs = new RandomList<String>();
        for ( String s : ( "The quick brown fox jumped over " +
                "the lazy brown dog" ).split(" ") )
            rs.add(s);
        dump(rs);
        RandomList<Integer> ri = new RandomList<Integer>();
        Generator<Integer> gi = new CountingGenerator.Integer();
        for ( int i = 0; i < 11; i++ )
            ri.add(gi.next());
        dump(ri);
        RandomList<Character> rc = new RandomList<Character>();
        Generator<Character> gc = new CountingGenerator.Character();
        for ( int i = 0; i < 11; i++ )
            rc.add(gc.next());
        dump(rc);
    }
} /* Output:
brown over fox quick quick dog brown The brown lazy brown
8 1 9 10 0 0 1 4 5 2 9
i b j k a a b e f c j
*///:~
/**
 * We use the Generator interface and the CountingGenerator class to
 produce sequences of integers and characters, respectively. You’ll learn more
 about these later in TIJ4. Notice the parameter type of the dump( ) method:
 RandomList<?> accepts any RandomList parameterized with an unknown
 type.
 * */