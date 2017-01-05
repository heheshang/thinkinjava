package practice;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
class ResumerException extends Exception {
}

class Resumer {
    static int count = 3;

    static void f( ) throws ResumerException{
        if ( --count > 0 )
            throw new ResumerException();
    }
}

public class E05_Resumption {
    public static void main(String args[]){
        while ( true ) {
            try {
                Resumer.f();
            } catch ( ResumerException e ) {
                System.out.println("Caught " + e);
                continue;
            }
            System.out.println("Got through...");
            break;
        }
        System.out.println("Successful execution");
    }
}
