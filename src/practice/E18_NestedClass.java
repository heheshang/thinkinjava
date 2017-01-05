package practice;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class E18_NestedClass {
    static class Nested {
        public static void main(String[] args){
            Nested n = new Nested();
            n.f();
        }

        void f( ){
            System.out.println("Nested.f");
        }
    }

    class Other {
        void f( ){
            E18_NestedClass.Nested ne = new Nested();
        }
    }
}
