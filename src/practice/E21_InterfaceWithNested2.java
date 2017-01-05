package practice;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
interface I {
    void f( );

    void g( );

    class Nested {
        static void call(I impl){
            System.out.println("Callling I.f");
            impl.f();
            System.out.println("Callling I.g");
            impl.g();
        }
    }
}

public class E21_InterfaceWithNested2 {
    public static void main(String[] args){
        I impl = new I() {
            @Override
            public void f( ){

            }

            @Override
            public void g( ){

            }
        };
        I.Nested.call(impl);
    }

}
