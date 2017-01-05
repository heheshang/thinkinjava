package practice;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class E01_SimpleException {
    public static void main(String[] args){
        try {
            throw new Exception("An exception in main");
        } catch ( Exception e ) {
            System.out.println("e.getMessage() = " + e.getMessage());
        } finally {
            System.out.println("in finally case");
        }
    }
}
