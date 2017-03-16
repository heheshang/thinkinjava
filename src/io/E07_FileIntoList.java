package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class E07_FileIntoList {
    public static List<String> read(String filename) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        List<String> list = new LinkedList<>();
        while ( ( s = in.readLine() ) != null ) {
            list.add(s);

        }
        in.close();
        return list;
    }

    public static void main(String[] args) throws IOException{
        List<String> list = read("F:\\think\\thinkinjava\\src\\io\\E07_FileIntoList.java");
        for ( ListIterator<String> iterator = list.listIterator(list.size()); iterator.hasPrevious(); ) {
            System.out.println(iterator.previous());
        }

    }
}
