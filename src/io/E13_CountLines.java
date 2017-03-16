package io;

import java.io.*;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class E13_CountLines {
    static String file = "F:\\think\\thinkinjava\\src\\io\\E13_CountLines.out";

    public static void main(String[] args) throws IOException{
        LineNumberReader in = new LineNumberReader(new FileReader("F:\\think\\thinkinjava\\src\\io\\E13_CountLines.java"));
        PrintWriter out = new PrintWriter(new FileWriter(file));
        String s;
        while ( ( s = in.readLine() ) != null )
            out.println(in.getLineNumber() + ":" + s);
        out.close();
        System.out.println(E07_FileIntoList.read(file));
    }
}
