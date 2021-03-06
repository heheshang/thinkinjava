package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
class SortedDirList {
    private File path;

    public SortedDirList( ){
        path = new File("F:\\think\\thinkinjava\\src\\io\\");
    }


    public SortedDirList(File path){
        this.path = path;
    }

    public String[] list( ){
        String[] list = path.list();
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        System.out.println(list);
        return list;
    }

    public String[] list(final String fn_regex){
        String[] list =
                path.list(new FilenameFilter() {
                    private Pattern pattern = Pattern.compile(fn_regex);

                    public boolean accept(File dir, String name){
                        return pattern.matcher(name).matches();
                    }
                });
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        return list;
    }
}

public class E02_SortedDirList {
    public static void main(String args[]){
// Default constructor == current directory
        SortedDirList dir = new SortedDirList();
        print(Arrays.asList(dir.list("E0[12]_.*\\.java")));
        print(Arrays.asList(dir.list()));
    }
} /* Output:
[E01_SearchWords.java, E02_SortedDirList.java]
*///:~
