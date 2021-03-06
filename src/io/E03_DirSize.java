package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class E03_DirSize {
    public static void main(final String[] args) {
        File path = new File("F:\\think\\thinkinjava\\src\\io\\");
        String[] list;
        if(args.length == 0)
            list = path.list();
        else
            list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile(args[0]);
                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            });
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        long total = 0;
        long fs;
        for(String dirItem : list) {
            fs = new File(path, dirItem).length();
            print(dirItem + ", " + fs + " byte(s)");
            total += fs;
        }
        print("=======================");
        print(list.length + " file(s), " + total + " bytes");
    }
}
