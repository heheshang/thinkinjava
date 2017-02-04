package generics;

/**
 * Created by Administrator on 2017/1/6 0006.
 */
interface LOW {
    void c( );

    void d( );
}

interface TOP {
    void a( );

    void b( );
}

class TopLowImpl implements LOW, TOP {

    @Override
    public void c( ){
        System.out.println("LOW::c()");
    }

    @Override
    public void d( ){
        System.out.println("LOW::d()");
    }

    @Override
    public void a( ){
        System.out.println("Top::a()");
    }

    @Override
    public void b( ){
        System.out.println("Top::b()");
    }
}

public class E25_Bounds {
    static <T extends TOP> void top(T object){
        object.a();
        object.b();
    }

    static <T extends LOW> void low(T object){
        object.c();
        object.d();
    }

    public static void main(String[] args){
        TopLowImpl topLow = new TopLowImpl();
        top(topLow);
        low(topLow);
    }
}
