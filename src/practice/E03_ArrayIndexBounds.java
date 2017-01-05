package practice;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class E03_ArrayIndexBounds {
    public static void main(String[] args){
        char[] array = new char[10];
        try {
            array[10] = 'x';
        } catch ( ArrayIndexOutOfBoundsException e ) {
            e.printStackTrace();
            System.out.println("e = " + e);
        }
    }
}
