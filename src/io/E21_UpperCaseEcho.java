package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public class E21_UpperCaseEcho {
    public static void main(String[] args) throws IOException{
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));
        String s;
        while ( ( s = stdin.readLine() ) != null && s.length() != 0 )
            System.out.println(s.toUpperCase());
// An empty line or Ctrl-Z terminates the program
    }
}
