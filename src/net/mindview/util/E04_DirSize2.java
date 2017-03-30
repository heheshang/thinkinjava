package net.mindview.util;

import java.io.File;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class E04_DirSize2 {
    public static void main(String[] args){
        Directory.TreeInfo ti;
        if(args.length == 0)
            ti = Directory.walk("../object");
        else
            ti = Directory.walk("../object", args[0]);
        long total = 0;
        for(File file : ti)
            total += file.length();
        System.out.println(
                ti.files.size() + " file(s), " + total + " bytes");
    }
}
