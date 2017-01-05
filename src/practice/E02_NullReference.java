package practice;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class E02_NullReference {
    public static void main(String[] args){
        String s = null;
        try {
            s.toString();
        } catch ( Exception e ) {
            System.out.println("Caught exception " + e);
            e.printStackTrace();
        }

    }
}
