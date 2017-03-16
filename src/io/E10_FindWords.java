package io;

import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class E10_FindWords {
    public static void main(String[] args)
            throws java.io.IOException{
        if ( args.length < 2 ) {
            System.err.println(
                    "Usage: java E10_FindWords file words");
            return;
        }

        Set<String> words = new HashSet<>();
        for ( int i = 0; i < args.length; i++ ) {
            words.add(args[i]);

        }
        List<String> list = E07_FileIntoList.read(args[0]);
        for ( ListIterator<String> iterator = list.listIterator(list.size()); iterator.hasPrevious(); ) {
            String candidate = iterator.previous();
            for ( String word : words ) {
                if ( candidate.indexOf(word) != -1 ) {
                    System.out.println(candidate);
                    break;
                }
            }

        }
    }
}
