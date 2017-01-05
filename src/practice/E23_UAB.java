package practice;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
interface U {
    void f( );

    void g( );

    void h( );
}

class A1 {
    public U getU( ){
        return new U() {

            @Override
            public void f( ){
                System.out.println("A1.f");
            }

            @Override
            public void g( ){
                System.out.println("A1.g");
            }

            @Override
            public void h( ){
                System.out.println("A1.h");
            }
        };
    }
}

class B1 {
    U[] ua;

    public B1(int size){
        ua = new U[size];
    }

    public boolean add(U elem){
        for ( int i = 0; i < ua.length; i++ ) {
            if ( ua[i] == null ) {
                ua[i] = elem;
                return true;
            }
        }
        return false;
    }

    public boolean setNull(int i){
        if ( i < 0 || i > ua.length )
            return false;
        ua[i] = null;
        return true;
    }

    public void callMethods( ){
        for ( int i = 0; i < ua.length; i++ ) {
            if ( ua[i] != null ) {
                ua[i].f();
                ua[i].g();
                ua[i].h();
            }

        }
    }
}

public class E23_UAB {
    public static void main(String[] args){
        A1[] aa = {new A1(), new A1(), new A1()};
        B1 b = new B1(3);
        for ( A1 anAa : aa ) {
            b.add(anAa.getU());
        }
        b.callMethods();
        System.out.println("******");
        b.setNull(0);
//        b.setNull(1);
//        b.setNull(2);
        b.callMethods();
    }
}
