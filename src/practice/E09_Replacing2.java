package practice;

import strings.Splitting;

/**
 * Created by Administrator on 2016/12/26 0026.
 */
public class E09_Replacing2 {
    public static void main(String[] args) {
        System.out.println(Splitting.knights);
        System.out.println(Splitting.knights.replaceAll(
                "(?i)[aeiou]","_"));
    }
}
