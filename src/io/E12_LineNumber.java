package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;

/**
 * {Args: F:\think\thinkinjava\src\io\E12_LineNumber.java F:\think\thinkinjava\src\io\E12_LineNumber.txt}
 * Created by Administrator on 2017/3/15 0015.
 */
public class E12_LineNumber {
    public static void main(String[] args) throws IOException{
        if ( args.length != 2 ) {
            System.out.println("Usage: java E12_LineNumber infile outfile");
            return;
        }
        List<String> list = E07_FileIntoList.read(args[0]);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
        int line = list.size();
        for ( ListIterator<String> iterator = list.listIterator(list.size()); iterator.hasPrevious(); ) {
            out.println(line-- + ":" + iterator.previous());
        }
        out.close();
    }
}
